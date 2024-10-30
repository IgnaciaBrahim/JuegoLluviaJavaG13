package io.github.some_example_name;
public interface Recoleccionable {
    void caer();
    void aplicarEfecto(Jugador jugador);
    boolean haSidoRecolectado(Tarro tarro); 
    String getTipo(); 
    void aplicarEfecto(Jugador jugador, ControladorJuego controlador);
}
