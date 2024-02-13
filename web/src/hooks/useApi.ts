import axios, { AxiosResponse } from 'axios';
import { useMensagem } from '../providers/MensagemProvider';
import { Debito } from '../types/Debito';
import { Pessoa } from '../types/Pessoa';
import { FormPessoa } from '../types/FormPessoa';

interface ApiResponse {
  success?: string;
  error?: string;
}

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

const useApi = () => {
  const mensagem = useMensagem();

  const handleMensagemErro = (error: any, mensagemPadrao: string) => {
    const mensagemErro = error.response?.data?.error || mensagemPadrao;
    if (mensagemErro) {
        mensagem.exibirErro(mensagemErro);
    }    
  };

  const handleMensagemSucesso = (response: AxiosResponse<ApiResponse>) => {
    const mensagemSucesso = response?.data?.success;
    if (mensagemSucesso) {
        mensagem.exibirSucesso(mensagemSucesso);
    }
  };

  const request = async <T>(
    method: 'get' | 'post' | 'put' | 'delete',
    url: string,
    data?: Record<string, any>
  ): Promise<T | undefined> => {
    try {
      let response: AxiosResponse<ApiResponse>;

      switch (method) {
        case 'get':
          response = await api.get(url);
          break;
        case 'post':
          response = await api.post(url, data);
          break;
        case 'put':
          response = await api.put(url, data);
          break;
        case 'delete':
          response = await api.delete(url);
          break;
        default:
          throw `Método ${method} não configurado.`;
      }

      handleMensagemSucesso(response);
      return response.data as T;
    } catch (error: any) {
      handleMensagemErro(error, `Erro na requisição ${method.toUpperCase()} para ${url}`);
      return undefined;
    }
  };

  return {
    findPessoas: async (page: number, size: number) =>
      request<{ content: Pessoa[] }>('get', `/api/pessoas?page=${page}&size=${size}`).then(response => response?.content),
    findPessoaById: async (idPessoa: number) =>
      request<Pessoa>('get', `/api/pessoas/${idPessoa}`),
    addPessoa: async (data: FormPessoa) =>
      request<Pessoa>('post', '/api/pessoas', data),
    excluirPessoa: async (id: number) =>
      request<boolean>('delete', `/api/pessoas/${id}`),
    findDebitos: async (page: number, size: number, dtLancInicio: string, dtLancFim: string, cpf: string, nomePessoa: string) =>
      request<{ content: Debito[] }>('get', 
      `/api/debitos?page=${page}&size=${size}&dataLancamentoInicio=${dtLancInicio}&dataLancamentoFim=${dtLancFim}&cpf=${cpf}&nomePessoa=${nomePessoa}`).then(response => response?.content),
    findDebitoById: async (idDebito: number) =>
      request<Debito>('get', `/api/debitos/${idDebito}`),
  };
};

export default useApi;
