package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public class ProfesorPowerUp extends Profesor {
    private String tipoPowerUp;

    // Modificación: Se debe pasar un área (Rectangle) además de los otros parámetros
    public ProfesorPowerUp(String nombre, int frecuencia, int probabilidad, String tipoPowerUp, Rectangle area) {
        super(nombre, frecuencia, probabilidad, area); // Llamada al constructor de Profesor
        this.tipoPowerUp = tipoPowerUp;
    }
    
    //unused
    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); 
    }

    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        jugador.aplicarPowerUp(tipoPowerUp);
        System.out.println(nombre + " otorga un power-up de tipo " + tipoPowerUp + ".");
    }

    @Override
    public String getTipo() {
        return "PowerUp";
    }
}
