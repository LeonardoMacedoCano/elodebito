import { useEffect, useState } from 'react';
import { Pessoa } from '../../../types/Pessoa';
import { FormPessoa } from '../../../types/FormPessoa';
import * as C from './styles';
import useApi from '../../../hooks/useApi';
import { PessoaColunasConfig } from '../TableConfig/PessoaColunasConfig';
import { PessoaColunasFormat } from '../TableConfig/PesssoaColunasFormat';
import { TableArea } from '../../../components/Table/TableArea';

const ConsultaPessoas: React.FC = () => {
    const [pessoas, setPessoas] = useState<Pessoa[]>([]); 
    const [idSelecionado, setIdSelecionado] = useState<number | null>(null);
    const [nomePessoa, setNomePessoa] = useState<string>("");
    const [cpfPessoa, setCpfPessoa] = useState<string>("");
    const [carregando, setCarregando] = useState(true);
    const api = useApi();

    useEffect(() => {
        const fetchPessoas = async () => {
            if (carregando) {
                const result = await api.findPessoas(0, 10);
                if (result) {
                    setPessoas(result);
                }
                setCarregando(false);
            }
        };

        fetchPessoas();
    }, [carregando]);

    const handleItemClick = (idItem: number | null) => {
        const pessoaSelecionada = pessoas.find((pessoa) => pessoa.id === idItem); 

        setNomePessoa(pessoaSelecionada?.nome || '');
        setCpfPessoa(pessoaSelecionada?.cpf || '');

        setIdSelecionado(idItem);
    }; 

    const handleNomePessoaChange = (value: string) => {
        setNomePessoa(value);
    }; 

    const handleCpfPessoaChange = (value: string) => {
        setCpfPessoa(value);
    }; 

    const handleAddPessoaEvent = async () => {
        const novaPessoa: FormPessoa ={
            cpf: cpfPessoa,
            nome: nomePessoa,   
        }

        await api.addPessoa(novaPessoa);
        setCarregando(true);
    }; 

    const handleExcluirPessoaEvent = async () => {
        if (idSelecionado !== null){
            await api.excluirPessoa(idSelecionado);
            setCarregando(true);
        }
    }; 

    return (
        <C.Container>
            <C.Body>
                <C.ToolbarArea>
                    <C.Column>
                        <C.FirstRow>
                            Nome Pessoa
                        </C.FirstRow>
                        <C.SecondRow>
                            <C.Input
                                type={'text'}
                                value={nomePessoa}
                                onChange={(e) => handleNomePessoaChange(e.target.value)}
                            />
                        </C.SecondRow>
                    </C.Column>

                    <C.Column>
                        <C.FirstRow>
                            CPF Pessoa
                        </C.FirstRow>
                        <C.SecondRow>
                            <C.Input
                                type={'text'}
                                value={cpfPessoa}
                                onChange={(e) => handleCpfPessoaChange(e.target.value)}
                            />
                        </C.SecondRow>
                    </C.Column>

                    <C.Column>
                        <C.FirstRow>
                            &nbsp;  
                        </C.FirstRow>
                        <C.SecondRow>
                            {idSelecionado === null ? (
                                <C.Button onClick={handleAddPessoaEvent}>Adicionar Pessoa</C.Button>
                            ) : (
                                <C.Button onClick={handleExcluirPessoaEvent}>Excluir Pessoa</C.Button>
                            )}
                        </C.SecondRow>
                    </C.Column>
                </C.ToolbarArea>
                <TableArea
                    lista={pessoas}
                    colunasConfig={PessoaColunasConfig}
                    colunasFormat={PessoaColunasFormat}
                    onItemClick={handleItemClick}
                    itemIdSelecionado={idSelecionado}
                />
            </C.Body>
        </C.Container>
    );
};

export default ConsultaPessoas;
