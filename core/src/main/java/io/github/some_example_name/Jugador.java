package io.github.some_example_name;
// ESTO ES SOLO UNA IDEA, CORREGIR OBVIO
public class Jugador {
    private int puntaje;
    private int vida;

    public Jugador(int vidaInicial) {
        this.vida = vidaInicial;
        this.puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void incrementarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    public void reducirPuntaje(int puntos) {
        this.puntaje = Math.max(0, this.puntaje - puntos);  
    }

    public int getVida() {
        return vida;
    }

    public void reducirVida(int cantidad) {
        this.vida = Math.max(0, this.vida - cantidad); // No vida negativa
    }

    public void incrementarVida(int cantidad) {
        this.vida += cantidad;
    }

    public void aplicarPowerUp(String tipoPowerUp) {
        // Lógica para aplicar un power-up, basada en el tipo
        System.out.println("Power-up aplicado: " + tipoPowerUp);
    }

    public void aplicarEfecto(String tipoPowerUp){
        // No se es para q no tire error
    }
}
