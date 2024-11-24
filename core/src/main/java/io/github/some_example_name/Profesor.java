package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public abstract class Profesor implements Recoleccionable {
    protected String nombre;
    protected int frecuencia;
    protected int probabilidadAparicion;
    protected Rectangle area;

    /**
     * Constructor protegido, solo accesible desde el Builder.
     * 
     * @param nombre Nombre del profesor.
     * @param frecuencia Frecuencia de caída.
     * @param probabilidadAparicion Probabilidad de aparición en el juego.
     * @param area Área de colisión del profesor.
     */
    protected Profesor(String nombre, int frecuencia, int probabilidadAparicion, Rectangle area) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.probabilidadAparicion = probabilidadAparicion;
        this.area = area;
    }

    /**
     * Devuelve el nombre del profesor.
     * 
     * @return Nombre del profesor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la frecuencia de caída del profesor.
     * 
     * @return Frecuencia de caída.
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * Devuelve la probabilidad de aparición del profesor.
     * 
     * @return Probabilidad de aparición.
     */
    public int getProbabilidadAparicion() {
        return probabilidadAparicion;
    }

    /**
     * Devuelve el área de colisión del profesor.
     * 
     * @return Área de colisión.
     */
    public Rectangle getArea() {
        return area;
    }

    /**
     * Actualiza la posición del profesor.
     * 
     * @param x Nueva posición en X.
     * @param y Nueva posición en Y.
     */
    public void setPosition(float x, float y) {
        area.setPosition(x, y);
    }

    /**
     * Define el efecto que se aplicará al jugador cuando recoja al profesor.
     * Este método será implementado en las subclases.
     * 
     * @param jugador Jugador sobre el cual se aplica el efecto.
     * @param controlador Controlador del juego, para manejar eventos adicionales.
     */
    public abstract void aplicarEfecto(Jugador jugador, ControladorJuego controlador);

    /**
     * Verifica si el profesor ha sido recolectado por el tarro del jugador.
     * 
     * @param tarro Tarro del jugador con el que se verifica la colisión.
     * @return true si el área del profesor se superpone con el área del tarro, false en caso contrario.
     */
    @Override
    public boolean haSidoRecolectado(Tarro tarro) {
        return tarro.getArea().overlaps(area);
    }

    /**
     * Simula la caída del profesor actualizando su posición vertical.
     */
    @Override
    public void caer() {
        area.y -= frecuencia;
    }
}
