package br.com.lcano.elodebito.util;

public class CustomException extends RuntimeException {
    public static class PessoaNaoEncontradaComIdException extends RuntimeException {
        public PessoaNaoEncontradaComIdException(Long IDPessoa) {
            super(String.format(MensagemUtils.PESSOA_NAO_ENCONTRADA_COM_ID, IDPessoa));
        }
    }

    public static class PessoaCPFInvalidoException extends RuntimeException {
        public PessoaCPFInvalidoException() {
            super(String.format(MensagemUtils.PESSOA_CPF_INVALIDO));
        }
    }

    public static class PessoaNomeInvalidoException extends RuntimeException {
        public PessoaNomeInvalidoException() {
            super(String.format(MensagemUtils.PESSOA_NOME_INVALIDO));
        }
    }

    public static class DebitoNaoEncontradaComIdException extends RuntimeException {
        public DebitoNaoEncontradaComIdException(Long IDDebito) {
            super(String.format(MensagemUtils.DEBITO_NAO_ENCONTRADA_COM_ID, IDDebito));
        }
    }

    public static class DebitoDataLancamentoInvalidoException extends RuntimeException {
        public DebitoDataLancamentoInvalidoException() {
            super(String.format(MensagemUtils.DEBITO_DATA_LANCAMENTO_INVALIDO));
        }
    }

    public static class DebitoQuantidadeParcelasInvalidasException extends RuntimeException {
        public DebitoQuantidadeParcelasInvalidasException() {
            super(String.format(MensagemUtils.DEBITO_QUANTIDADE_PARCELAS_INVALIDAS));
        }
    }

    public static class ParcelaNaoEncontradaComIdException extends RuntimeException {
        public ParcelaNaoEncontradaComIdException(Long IDParcela) {
            super(String.format(MensagemUtils.PARCELA_NAO_ENCONTRADA_COM_ID, IDParcela));
        }
    }

    public static class ParcelaNumeroInvalidoDebitoException extends RuntimeException {
        public ParcelaNumeroInvalidoDebitoException() {
            super(String.format(MensagemUtils.PARCELA_NUMERO_INVALIDO_DEBITO));
        }
    }

    public static class ParcelaDataVencimentoInvalidoException extends RuntimeException {
        public ParcelaDataVencimentoInvalidoException() {
            super(String.format(MensagemUtils.PARCELA_DATA_VENCIMENTO_INVALIDO));
        }
    }

    public static class ParcelaValorInvalidoException extends RuntimeException {
        public ParcelaValorInvalidoException() {
            super(String.format(MensagemUtils.PARCELA_VALOR_INVALIDO));
        }
    }
}
