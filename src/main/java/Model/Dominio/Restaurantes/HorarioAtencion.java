package Model.Dominio.Restaurantes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.util.Locale;

public class HorarioAtencion {
    private final DayOfWeek diaSemana;
    private final LocalTime horaApertura;
    private final LocalTime horaCierre;

    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    public HorarioAtencion(String diaSemana, String horaApertura, String horaCierre) {
        if (diaSemana == null || horaApertura == null || horaCierre == null) {
            throw new IllegalArgumentException("Ninguno de los parámetros puede ser nulo.");
        }

        this.diaSemana = parseDia(diaSemana);
        this.horaApertura = LocalTime.parse(horaApertura, FORMATO_HORA);
        this.horaCierre = LocalTime.parse(horaCierre, FORMATO_HORA);
    }

    public boolean estaAbiertoAhora(String horaActualStr, String diaActualStr) {
        LocalTime horaActual = LocalTime.parse(horaActualStr, FORMATO_HORA);
        DayOfWeek diaActual = parseDia(diaActualStr);

        if (!diaActual.equals(diaSemana)) {
            return false;
        }

        if (horaApertura.isBefore(horaCierre)) {
            return !horaActual.isBefore(horaApertura) && horaActual.isBefore(horaCierre);
        } else {
            return !horaActual.isBefore(horaApertura) || horaActual.isBefore(horaCierre);
        }
    }

    private DayOfWeek parseDia(String dia) {
        switch (dia.toLowerCase(Locale.ROOT)) {
            case "lunes": return DayOfWeek.MONDAY;
            case "martes": return DayOfWeek.TUESDAY;
            case "miercoles": return DayOfWeek.WEDNESDAY;
            case "jueves": return DayOfWeek.THURSDAY;
            case "viernes": return DayOfWeek.FRIDAY;
            case "sabado": return DayOfWeek.SATURDAY;
            case "domingo": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Día inválido: " + dia);
        }
    }

    @Override
    public String toString() {
        return diaSemana + ": " + horaApertura + " - " + horaCierre;
    }
}
