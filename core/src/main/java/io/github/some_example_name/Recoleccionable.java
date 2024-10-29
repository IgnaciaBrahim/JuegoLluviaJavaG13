package io.github.some_example_name;

public interface Recoleccionable {

    /**
     * Maneja el movimiento de caída del objeto en pantalla.
     * Este método se llamará cada vez que el objeto deba actualizar su posición.
     */
    void caer();

    /**
     * Aplica el efecto correspondiente al objeto recolectado sobre el jugador.
     * Este método será llamado cuando el jugador recolecte el objeto.
     *
     * @param jugador el jugador sobre el cual se aplicará el efecto
     */
    void aplicarEfecto(Jugador jugador);

    /**
     * Verifica si el objeto ha sido recolectado.
     * Este método se puede usar para definir las condiciones de recolección del objeto.
     *
     * @return true si el objeto ha sido recolectado, false en caso contrario
     */
    boolean haSidoRecolectado();

}
