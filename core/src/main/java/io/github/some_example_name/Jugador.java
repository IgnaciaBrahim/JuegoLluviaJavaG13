package io.github.some_example_name;

public class Jugador {
    private int puntaje;
    private int vida;
    private long tiempoInmunidadRestante;
    private boolean inmune; 
    
    private ControladorJuego controlador;

    public Jugador(int vidaInicial, ControladorJuego controlador) {
        this.vida = vidaInicial;
        this.puntaje = 0;
        this.inmune = false; // Inicializa inmune como false
        this.controlador = controlador; // Guarda la referencia al controlador
    }

    public void activarTormentaCubillos() {
        controlador.iniciarTormentaCubillos();
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void cambiarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    /*public void reducirPuntaje(int puntos) {
        this.puntaje = Math.max(0, this.puntaje - puntos); 
    }
     */
    
    public void otorgarInmunidad(int duracionSegundos) {
        this.inmune = true;
        this.tiempoInmunidadRestante = System.currentTimeMillis() + duracionSegundos * 1000;
    }
    
    

    public boolean esInmune() {
        if (inmune && System.currentTimeMillis() > tiempoInmunidadRestante) {
            inmune = false; // Desactiva la inmunidad después de la duración
            System.out.println("Inmunidad desactivada");
        }
        return inmune;
    }
    
    
    

    public int getVida() {
        return vida;
    }

    public void cambiarVida(int cantidad) {
        this.vida += cantidad;
    }
    
    /*
    public void incrementarVida(int cantidad) {
        this.vida += cantidad;
    }
    */

    // Método aplicarPowerUp
    public void aplicarPowerUp(String tipoPowerUp) {
        System.out.println("Power-up aplicado: " + tipoPowerUp);
    }
}
