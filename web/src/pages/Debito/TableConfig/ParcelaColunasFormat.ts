import { dateToString, formatarData } from "../../../utils/DateUtils";

export const ParcelaColunasFormat = {
    id: (value: string | number | boolean | Date): React.ReactNode => String(value),
    numero: (value: string | number | boolean | Date): React.ReactNode => String(value),
    dataVencimento: (value: string | number | boolean | Date): React.ReactNode => {
        if (typeof value === 'string') {
            return formatarData(value);       
        } else if (value instanceof Date) {
            return formatarData(dateToString(value));
        } else {
            return String(value);
        }
    },
    valor: (value: string | number | boolean | Date): React.ReactNode => {
        if (typeof value === 'number') {
            return Number(value).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }
        return null;
    },
};