package br.com.lcano.elodebito.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.sql.Date;

@Component
public final class DateUtils {
    public static Date getDataAtual() {
        return Date.valueOf(LocalDate.now());
    }
}
