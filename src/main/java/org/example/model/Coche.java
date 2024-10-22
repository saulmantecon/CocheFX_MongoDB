package org.example.model;

import java.util.Objects;

public class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private String tipo;

    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coche coche = (Coche) o;
        return Objects.equals(matricula, coche.matricula) && Objects.equals(marca, coche.marca) && Objects.equals(modelo, coche.modelo) && Objects.equals(tipo, coche.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, marca, modelo, tipo);
    }
}
