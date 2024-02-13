import { useEffect, useState } from "react";
import * as C from './styles';
import useApi from "../../../hooks/useApi";
import { TableArea } from "../../../components/Table/TableArea";
import { ConsultaDebitosColunasConfig } from "../TableConfig/ConsultaDebitosColunasConfig";
import { ConsultaDebitosColunasFormat } from "../TableConfig/ConsultaDebitosColunasFormat";
import { ConsultaDebito, converterParaConsultaDebito } from "../../../types/ConsultaDebito";
import { DebitosToolbar } from "../../../components/DebitosToolbar";
import { dateToString, getDataAtual } from "../../../utils/DateUtils";

const ConsultaDebitos: React.FC = () => {
    const [debitos, setDebitos] = useState<ConsultaDebito[]>([]); 
    const [idSelecionado, setIdSelecionado] = useState<number | null>(null);
    const [filtroDataInicio, setFiltroDataInicio] = useState<Date>(getDataAtual);
    const [filtroDataFim, setFiltroDataFim] = useState<Date>(getDataAtual);
    const [filtroNomePessoa, setFiltroNomePessoa] = useState<string>("");
    const [filtroCpfPessoa, setFiltroCpfPessoa] = useState<string>("");
    const api = useApi();

    useEffect(() => {
        const fetchDebitos = async () => {
            try {
                const result = await api.findDebitos(0, 
                    10, 
                    dateToString(filtroDataInicio), 
                    dateToString(filtroDataFim),
                    filtroCpfPessoa,
                    filtroNomePessoa);
                if (result) {
                    const debitosConvertidos = result.map(converterParaConsultaDebito);
                    setDebitos(debitosConvertidos);
                }
            } catch (error) {
                console.error("Erro ao carregar os débitos:", error);
            }
        };

        fetchDebitos();
    }, [filtroDataInicio, filtroDataFim, filtroCpfPessoa, filtroNomePessoa]);

    const handleItemClick = (id: number | null) => {
        setIdSelecionado(id);
    }; 

    const handleFiltroDataInicioChange = (value: Date) => {
        setFiltroDataInicio(value);
    }; 

    const handleFiltroDataFimChange = (value: Date) => {
        setFiltroDataFim(value);
    }; 

    const handleFiltroNomePessoaChange = (value: string) => {
        setFiltroNomePessoa(value);
    }; 

    const handleFiltroCpfPessoaChange = (value: string) => {
        setFiltroCpfPessoa(value);
    }; 
    

    return (
        <C.Container>
            <C.Body>
                <DebitosToolbar
                    filtroDataInicio={dateToString(filtroDataInicio)}
                    filtroDataFim={dateToString(filtroDataFim)}
                    filtroNomePessoa={filtroNomePessoa}
                    filtroCpfPessoa={filtroCpfPessoa}
                    idSelecionado={idSelecionado}
                    onFiltroDataInicioChange={handleFiltroDataInicioChange}
                    onFiltroDataFimChange={handleFiltroDataFimChange}
                    onFiltroNomePessoaChange={handleFiltroNomePessoaChange}
                    onFiltroCpfPessoaChange={handleFiltroCpfPessoaChange}
                />
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
