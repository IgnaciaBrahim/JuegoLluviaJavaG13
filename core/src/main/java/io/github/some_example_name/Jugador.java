package io.github.some_example_name;

public class Jugador {
    private int puntaje;
    private int vida;
    private long tiempoInmunidadRestante;
    private boolean inmune; // Declaración de la variable inmune

    public Jugador(int vidaInicial) {
        this.vida = vidaInicial;
        this.puntaje = 0;
        this.inmune = false; // Inicializa inmune como false
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
    
    public void otorgarInmunidad(int duracionSegundos) {
        this.inmune = true;
        this.tiempoInmunidadRestante = System.currentTimeMillis() + duracionSegundos * 1000;
    }
    
    

    public boolean esInmune() {
        if (inmune && System.currentTimeMillis() > tiempoInmunidadRestante) {
            inmune = false; // Desactiva inmunidad después de la duración
        }
        return inmune;
    }

    public int getVida() {
        return vida;
    }

    public void reducirVida(int cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
    }

    public void incrementarVida(int cantidad) {
        this.vida += cantidad;
    }

    // Método aplicarPowerUp, que puedes modificar según lo que quieras que haga el power-up
    public void aplicarPowerUp(String tipoPowerUp) {
        // Implementa la lógica para aplicar el power-up al jugador
        System.out.println("Power-up aplicado: " + tipoPowerUp);
    }
}
