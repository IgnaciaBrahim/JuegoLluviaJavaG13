package io.github.some_example_name;

public class ProfesorVillano extends Profesor {

    public ProfesorVillano(String nombre, int frecuencia) {
        super(nombre, frecuencia);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.reducirVida(10);  
        jugador.reducirPuntaje(20);  
        System.out.println(nombre + " reduce la vida y el puntaje.");
    }
}

