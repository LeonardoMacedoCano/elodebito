import { FormParcela } from "./FormParcela";

export type FormDebito = {
    idPessoa: number;
    dataLancamento: Date;
    parcelas: FormParcela[];
}