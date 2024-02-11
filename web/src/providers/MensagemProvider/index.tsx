import React, { ReactNode, createContext, useContext, useState } from 'react';
import * as styles from './styles';

interface TipoContextoMensagem {
  exibirErro: (mensagem: string) => void;
  exibirSucesso: (mensagem: string) => void;
}

const ContextoMensagem = createContext<TipoContextoMensagem | undefined>(undefined);

interface MensagemProviderProps {
  children: ReactNode;
}

const Mensagem: React.FC<{ tipo: 'erro' | 'sucesso'; mensagem: string }> = ({ tipo, mensagem }) => {
  const ComponenteMensagem = tipo === 'erro' ? styles.MensagemErro : styles.MensagemSucesso;

  return mensagem ? <ComponenteMensagem>{mensagem}</ComponenteMensagem> : null;
};

export const MensagemProvider: React.FC<MensagemProviderProps> = ({ children }) => {
  const [mensagemErro, setMensagemErro] = useState<string | null>(null);
  const [mensagemSucesso, setMensagemSucesso] = useState<string | null>(null);

  function limparMensagem() {
    setMensagemErro(null);
    setMensagemSucesso(null);
  }

  const exibirErro = (mensagem: string) => {
    limparMensagem();
    setMensagemErro(mensagem);
    setTimeout(() => setMensagemErro(null), 5000);
  };

  const exibirSucesso = (mensagem: string) => {
    limparMensagem();
    setMensagemSucesso(mensagem);
    setTimeout(() => setMensagemSucesso(null), 5000);
  };

  return (
    <ContextoMensagem.Provider value={{ exibirErro, exibirSucesso }}>
      {children}
      <Mensagem tipo="erro" mensagem={mensagemErro || ''} />
      <Mensagem tipo="sucesso" mensagem={mensagemSucesso || ''} />
    </ContextoMensagem.Provider>
  );
};

export const useMensagem = () => {
  const contexto = useContext(ContextoMensagem);

  if (!contexto) {
    throw new Error('useMensagem deve ser utilizado dentro de um MensagemProvider');
  }

  return contexto;
};