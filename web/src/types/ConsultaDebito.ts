import { Debito} from './Debito';

export type ConsultaDebito = {
    id: number;
    dataLancamento: Date;
    nomePessoa: string;
    qtParcelas: number;
    valorTotal: number;
};

export function converterParaConsultaDebito(debito: Debito): ConsultaDebito {
    const qtParcelas: number = debito.parcelas.length;
    const valorTotal: number = debito.parcelas.reduce((total, parcela) => total + parcela.valor, 0);

    const { id, dataLancamento, pessoa } = debito;
    const { nome } = pessoa;

    return {
        id,
        dataLancamento,
        nomePessoa: nome,
        qtParcelas,
        valorTotal,
    };
}