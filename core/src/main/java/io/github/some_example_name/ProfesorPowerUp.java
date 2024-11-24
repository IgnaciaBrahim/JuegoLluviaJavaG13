package io.github.some_example_name;

public class ProfesorPowerUp extends Profesor {
    private String tipoPowerUp;

    public ProfesorPowerUp(String nombre, int frecuencia, int probabilidad, String tipoPowerUp) {
        super(nombre, frecuencia, probabilidad);
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
