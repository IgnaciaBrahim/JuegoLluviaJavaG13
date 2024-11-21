package io.github.some_example_name;

public abstract class EventoJuego {
    protected ControladorJuego controlador;
    protected Jugador jugador;

    public EventoJuego(ControladorJuego controlador, Jugador jugador) {
        this.controlador = controlador;
        this.jugador = jugador;
    }
    // PATRON DE DISEÑO - TEMPLATE
    // Método plantilla para definir el flujo de un evento de juego
    public final void activarEvento() {
        configurarEvento();
        ejecutarEvento();
        finalizarEvento();
    }

    protected abstract void configurarEvento();
    protected abstract void ejecutarEvento();
    protected abstract void finalizarEvento();
}
