import { useEffect, useState } from "react";
import * as C from './styles';
import useApi from "../../../hooks/useApi";
import { TableArea } from "../../../components/Table/TableArea";
import { ConsultaDebitosColunasConfig } from "../TableConfig/ConsultaDebitosColunasConfig";
import { ConsultaDebitosColunasFormat } from "../TableConfig/ConsultaDebitosColunasFormat";
import { ConsultaDebito, converterParaConsultaDebito } from "../../../types/ConsultaDebito";

const ConsultaDebitos: React.FC = () => {
    const [debitos, setDebitos] = useState<ConsultaDebito[]>([]); 
    const [carregando, setCarregando] = useState(true);
    const [idSelecionado, setIdSelecionado] = useState<number | null>(null);
    const api = useApi();

    useEffect(() => {
        const fetchDebitos = async () => {
            if (carregando) {
                try {
                    const result = await api.findDebitos();
                    if (result) {
                        const debitosConvertidos = result.map(converterParaConsultaDebito);
                        setDebitos(debitosConvertidos);
                        setCarregando(false);
                    }
                } catch (error) {
                    console.error("Erro ao carregar os débitos:", error);
                }
            }
        };

        fetchDebitos();
    }, [carregando, api]);

    const handleItemClick = (id: number | null) => {
        setIdSelecionado(id);
    }; 

    return (
        <C.Container>
            <C.Body>
                <TableArea
                    lista={debitos}
                    colunasConfig={ConsultaDebitosColunasConfig}
                    colunasFormat={ConsultaDebitosColunasFormat}
                    onItemClick={handleItemClick}
                    itemIdSelecionado={idSelecionado}
                />
            </C.Body>
        </C.Container>
    );
};

export default ConsultaDebitos;
