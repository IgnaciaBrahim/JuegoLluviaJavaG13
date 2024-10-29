package io.github.some_example_name;

public class ProfesorPowerUp extends Profesor {
    private String tipoPowerUp;

    public ProfesorPowerUp(String nombre, int frecuencia, String tipoPowerUp) {
        super(nombre, frecuencia);
        this.tipoPowerUp = tipoPowerUp;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aplicarPowerUp(tipoPowerUp);
        System.out.println(nombre + " otorga un power-up de tipo " + tipoPowerUp + ".");
    }
}
