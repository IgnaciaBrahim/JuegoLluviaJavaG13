package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public class ProfesorBuilder<T extends Profesor> {
    private String nombre;
    private int frecuencia;
    private int probabilidad;
    private Rectangle area;
    private Class<T> tipoProfesor; // Clase genérica

    public ProfesorBuilder<T> setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProfesorBuilder<T> setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
        return this;
    }

    public ProfesorBuilder<T> setProbabilidadAparicion(int probabilidad) {
        this.probabilidad = probabilidad;
        return this;
    }

    public ProfesorBuilder<T> setArea(float x, float y, float width, float height) {
        this.area = new Rectangle(x, y, width, height);
        return this;
    }

    public ProfesorBuilder<T> setTipoProfesor(Class<T> tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
        return this;
    }

    public T build() {
        try {
            return tipoProfesor
                .getDeclaredConstructor(String.class, int.class, int.class, Rectangle.class)
                .newInstance(nombre, frecuencia, probabilidad, area);
        } catch (Exception e) {
            throw new RuntimeException("Error al construir el Profesor: " + e.getMessage(), e);
        }
    }
}
