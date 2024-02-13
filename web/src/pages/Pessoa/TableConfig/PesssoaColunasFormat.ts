export const PessoaColunasFormat = {
    id: (value: string | number | boolean | Date): React.ReactNode => String(value),
    cpf: (value: string | number | boolean | Date): React.ReactNode => String(value),
    nome: (value: string | number | boolean | Date): React.ReactNode => String(value),
};