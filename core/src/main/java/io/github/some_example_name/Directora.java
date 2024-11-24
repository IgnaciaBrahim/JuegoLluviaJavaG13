package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;

public class Directora {
    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public Profesor construir(String nombre, int frecuencia, int probabilidad, Rectangle area) {
        return builder
                .setNombre(nombre)
                .setFrecuencia(frecuencia)
                .setProbabilidadAparicion(probabilidad)
                .setArea(area)
                .build();
    }
}
