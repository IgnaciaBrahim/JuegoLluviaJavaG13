package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;

public interface Builder {
    Builder setNombre(String nombre);

    Builder setFrecuencia(int frecuencia);

    Builder setProbabilidadAparicion(int probabilidad);

    Builder setArea(Rectangle area);

    Profesor build();

    Profesor getProducto();
}
