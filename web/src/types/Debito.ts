import { Parcela } from "./Parcela";
import { Pessoa } from "./Pessoa";

export type Debito = {
    id: number;
    dataLancamento: Date;
    pessoa: Pessoa;
    parcelas: Parcela[];
}