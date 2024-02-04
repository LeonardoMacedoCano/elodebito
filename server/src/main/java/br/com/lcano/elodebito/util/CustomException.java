package br.com.lcano.elodebito.util;

public class CustomException extends RuntimeException {
    public static class PessoaNaoEncontradaComIdException extends RuntimeException {
        public PessoaNaoEncontradaComIdException(Long IDPessoa) {
            super(String.format(MensagemUtils.PESSOA_NAO_ENCONTRADA_COM_ID, IDPessoa));
        }
    }

    public static class DebitoNaoEncontradaComIdException extends RuntimeException {
        public DebitoNaoEncontradaComIdException(Long IDDebito) {
            super(String.format(MensagemUtils.DEBITO_NAO_ENCONTRADA_COM_ID, IDDebito));
        }
    }
}
