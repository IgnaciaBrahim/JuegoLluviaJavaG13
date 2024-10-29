package io.github.some_example_name;

public class ProfesorPuntos extends Profesor {
    private int puntos;

    public ProfesorPuntos(String nombre, int frecuencia, int puntos) {
        super(nombre, frecuencia);
        this.puntos = puntos;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.incrementarPuntaje(puntos);
        System.out.println(nombre + " otorga " + puntos + " puntos.");
    }
}
