package org.testeposvenda.domain;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String gerarDataAtual() {
        LocalDate dataAtual = LocalDate.now();
        // Definir o formato desejado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Formatando a data no formato "aaaa-MM-dd"
        return dataAtual.format(formato);
    }

    public static int diasNoMes(){
            YearMonth mesAtual = YearMonth.now();
            return mesAtual.lengthOfMonth();
        }
}
