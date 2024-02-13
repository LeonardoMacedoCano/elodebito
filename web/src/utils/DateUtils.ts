export const dateToString = (data: Date | undefined): string => {
  if (!data) return '';

  const dataObj = new Date(data);
  const dia = dataObj.getDate().toString().padStart(2, '0');
  const mes = (dataObj.getMonth() + 1).toString().padStart(2, '0');
  const ano = dataObj.getFullYear().toString();

  return `${ano}-${mes}-${dia}`;
};

export const stringToDate = (value: string): Date => {
  const [ano, mes, dia] = value.split('-').map(Number);
  return new Date(ano, mes - 1, dia);
};

export const getDataAtual = (): Date => {
  const now = new Date();
  return now;
};
