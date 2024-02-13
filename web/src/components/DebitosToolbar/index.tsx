import { stringToDate } from '../../utils/DateUtils';
import * as C from './styles';

type Props = {
    filtroDataInicio: string;
    filtroDataFim: string;
    filtroNomePessoa: string;
    filtroCpfPessoa: string;
    idSelecionado: number | null;
    onFiltroDataInicioChange: (value: Date) => void;
    onFiltroDataFimChange: (value: Date) => void;
    onFiltroNomePessoaChange: (value: string) => void;
    onFiltroCpfPessoaChange: (value: string) => void;
};

export const DebitosToolbar = ({
    filtroDataInicio,
    filtroDataFim,
    filtroNomePessoa,
    filtroCpfPessoa,
    idSelecionado,
    onFiltroDataInicioChange,
    onFiltroDataFimChange,
    onFiltroNomePessoaChange,
    onFiltroCpfPessoaChange
}: Props) => {
  
    const handleFiltroDataInicioChange = (value: string) => {
        onFiltroDataInicioChange(stringToDate(value));
    };

    const handleFiltroDataFimChange = (value: string) => {
        onFiltroDataFimChange(stringToDate(value));
    };

    const handleFiltroNomePessoaChange = (value: string) => {
        onFiltroNomePessoaChange(value);
    };

    const handleFiltroCpfPessoaChange = (value: string) => {
        onFiltroCpfPessoaChange(value);
    };

    const handleGerarDebitoEvent = () => {
    };

    const handleVisualizarDebitoEvent = () => {
    };

    return (
        <C.Container>
            <C.Column>
                <C.FirstRow>
                    Data Lançamento
                </C.FirstRow>
                <C.SecondRow>
                    <C.Input
                        type={'date'}
                        value={filtroDataInicio}
                        onChange={(e) => handleFiltroDataInicioChange(e.target.value)}
                    />
                    <C.Label> até </C.Label>
                    <C.Input
                        type={'date'}
                        value={filtroDataFim}
                        onChange={(e) => handleFiltroDataFimChange(e.target.value)}
                    />
                </C.SecondRow>
            </C.Column>
            <C.Column>
                <C.FirstRow>
                    Nome Pessoa
                </C.FirstRow>
                <C.SecondRow>
                    <C.Input
                        type={'text'}
                        value={filtroNomePessoa}
                        onChange={(e) => handleFiltroNomePessoaChange(e.target.value)}
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
                        value={filtroCpfPessoa}
                        onChange={(e) => handleFiltroCpfPessoaChange(e.target.value)}
                    />
                </C.SecondRow>
            </C.Column>
            <C.Column>
                <C.FirstRow>
                    &nbsp;  
                </C.FirstRow>
                <C.SecondRow>
                    {idSelecionado === null ? (
                        <C.Button onClick={handleGerarDebitoEvent}>Gerar Debito</C.Button>
                    ) : (
                        <C.Button onClick={handleVisualizarDebitoEvent}>Visualizar Debito</C.Button>
                    )}
                </C.SecondRow>
            </C.Column>
        </C.Container>
    );
};
