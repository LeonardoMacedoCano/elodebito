package br.com.lcano.elodebito.advice;

import br.com.lcano.elodebito.util.MensagemUtils;
import br.com.lcano.elodebito.util.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String mensagem) {
        return ResponseEntity.status(status).body(Map.of("error", mensagem));
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleGenericException(Exception ex) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, MensagemUtils.ERRO_GENERICO);
    }

    @ExceptionHandler({CustomException.PessoaNaoEncontradaComIdException.class})
    protected ResponseEntity<Object> handlePessoaNaoEncontradaComIdException(CustomException.PessoaNaoEncontradaComIdException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({CustomException.PessoaCPFInvalidoException.class})
    protected ResponseEntity<Object> handlePessoaCPFInvalidoException(CustomException.PessoaCPFInvalidoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({CustomException.PessoaNomeInvalidoException.class})
    protected ResponseEntity<Object> handlePessoaNomeInvalidoException(CustomException.PessoaNomeInvalidoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({CustomException.DebitoNaoEncontradaComIdException.class})
    protected ResponseEntity<Object> handleDebitoNaoEncontradaComIdException(CustomException.DebitoNaoEncontradaComIdException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({CustomException.DebitoDataLancamentoInvalidoException.class})
    protected ResponseEntity<Object> handleDebitoDataLancamentoInvalidoException(CustomException.DebitoDataLancamentoInvalidoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({CustomException.ParcelaNumeroInvalidoDebitoException.class})
    protected ResponseEntity<Object> handleParcelaNumeroInvalidoDebitoException(CustomException.ParcelaNumeroInvalidoDebitoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({CustomException.ParcelaDataVencimentoInvalidoException.class})
    protected ResponseEntity<Object> handleParcelaDataVencimentoInvalidoException(CustomException.ParcelaDataVencimentoInvalidoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({CustomException.ParcelaValorInvalidoException.class})
    protected ResponseEntity<Object> handleParcelaValorInvalidoException(CustomException.ParcelaValorInvalidoException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
