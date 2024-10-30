package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public abstract class Profesor implements Recoleccionable {
    protected String nombre;
    protected int frecuencia;
    protected int probabilidadAparicion;
    protected Rectangle area;  

    /**
     * Constructor para la clase Profesor.
     * 
     * @param nombre Nombre del profesor.
     * @param frecuencia Frecuencia de aparición.
     * @param probabilidad Probabilidad de aparición en el juego.
     */
    public Profesor(String nombre, int frecuencia, int probabilidad) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
        this.probabilidadAparicion = probabilidad;
        this.area = new Rectangle();  
    }
    
    @Override
    public void caer() {
        // Lógica de caída común (opcional, puede ser sobreescrita en subclases)
        // Puedes definir la velocidad de caída y actualizar la posición en "y"
        area.y -= frecuencia; // Ejemplo básico de movimiento en caída
    }

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
     * Devuelve la probabilidad de aparición del profesor en el juego.
     * 
     * @return Probabilidad de aparición.
     */
    public int getProbabilidadAparicion() {
        return probabilidadAparicion;
    }

    /**
     * Define el efecto que se aplicará al jugador cuando recoja al profesor.
     * Este método debe ser implementado en cada subclase para definir el efecto específico.
     * 
     * @param jugador Jugador sobre el cual se aplica el efecto.
     * @param controlador Controlador del juego, que puede gestionar eventos adicionales.
     */
    public abstract void aplicarEfecto(Jugador jugador, ControladorJuego controlador);

    /**
     * Permite actualizar la posición del área de colisión del profesor.
     * Este método es útil para mover la posición del profesor en el juego.
     * 
     * @param x Nueva posición en X.
     * @param y Nueva posición en Y.
     */
    public void setPosition(float x, float y) {
        area.setPosition(x, y);
    }

    /**
     * Devuelve el área de colisión del profesor para verificaciones adicionales en otras clases si es necesario.
     * 
     * @return Área de colisión del profesor.
     */
    public Rectangle getArea() {
        return area;
    }
}
