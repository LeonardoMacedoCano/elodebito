import { formatarData } from "../../../utils/DateUtils";

export const ConsultaDebitosColunasFormat = {
    id: (value: string | number | boolean | Date): React.ReactNode => String(value),
    dataLancamento: (value: string | number | boolean | Date): React.ReactNode => {
        if (typeof value === 'string') {
            return formatarData(value);       
        } else {
            return String(value);
        }
    },
    nomePessoa: (value: string | number | boolean | Date): React.ReactNode => String(value),
    qtParcelas: (value: string | number | boolean | Date): React.ReactNode => String(value),
    valorTotal: (value: string | number | boolean | Date): React.ReactNode => {
        if (typeof value === 'number') {
          return Number(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }
        return null;
      },
};