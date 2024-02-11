import axios, { AxiosResponse } from 'axios';
import { useMensagem } from '../providers/MensagemProvider';

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
    token?: string,
    data?: Record<string, any>
  ): Promise<T | undefined> => {
    try {
      const headers: Record<string, string> = token ? { Authorization: `Bearer ${token}` } : {};
      let response: AxiosResponse<ApiResponse>;

      switch (method) {
        case 'get':
          response = await api.get(url, { headers });
          break;
        case 'post':
          response = await api.post(url, data, { headers });
          break;
        case 'put':
          response = await api.put(url, data, { headers });
          break;
        case 'delete':
          response = await api.delete(url, { headers });
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
  };
};

export default useApi;