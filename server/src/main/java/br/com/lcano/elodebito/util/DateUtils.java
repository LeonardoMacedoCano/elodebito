package br.com.lcano.elodebito.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.sql.Date;

@Component
public final class DateUtils {

    public static final long UM_DIA_EM_MILISEGUNDOS = 1000L * 60 * 60 * 24;

    public static Date getDataAtual() {
        return Date.valueOf(LocalDate.now());
    }
}
