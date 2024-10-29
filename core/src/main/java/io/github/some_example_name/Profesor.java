package io.github.some_example_name;

public abstract class Profesor {
    protected String nombre;
    protected int frecuencia;

    public Profesor(String nombre, int frecuencia) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
    }

    public abstract void aplicarEfecto(Jugador jugador);

    public String getNombre() {
        return nombre;
    }
}
