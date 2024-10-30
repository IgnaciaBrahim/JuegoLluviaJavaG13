package io.github.some_example_name;
//import io.github.some_example_name.Jugador;

//import com.badlogic.gdx.math.Rectangle;

public class ProfesoraLaurita extends Profesor {

    private static final int DURACION_INMUNIDAD = 5; // duración en segundos

    public ProfesoraLaurita() {
        super("Laurita", 10, 5); // Frecuencia 10, probabilidad de aparición 5%
        this.area.set(100, 480, 60, 60); // Posición y tamaño inicial del área de colisión
    }

    // Implementación del método abstracto requerido por la interfaz
    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); // Llama a la versión con ControladorJuego, pasando null
    }

    @Override
public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
    jugador.otorgarInmunidad(DURACION_INMUNIDAD); // Este llamado debe usar "jugador"
    System.out.println(nombre + " ha otorgado inmunidad por " + DURACION_INMUNIDAD + " segundos.");
}



    @Override
    public String getTipo() {
        return "Laurita";
    }
}
