package org.example.util;

public class Validar {
    /**
     * Valida si una cadena es una matricula europea (<2000)
     *
     * @param matricula String que contiene el valor a validar
     * @return True = es una matricula europea (<2000)
     */
    public static boolean validarMatriculaEuropea_Exp(String matricula) {

        return matricula.matches("^[0-9]{4}[A-Z]{3}$");

    }
}
