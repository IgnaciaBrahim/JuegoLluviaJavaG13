package io.github.some_example_name;
//import io.github.some_example_name.Jugador;

import com.badlogic.gdx.math.Rectangle;

public class ProfesoraLaurita extends Profesor {

    private static final int DURACION_INMUNIDAD = 5; // duración en segundos
    
    public ProfesoraLaurita(String nombre, int frecuencia, int probabilidad, Rectangle area) {
        super(nombre, frecuencia, probabilidad, area);
    }

    
    // Método estático para construir una instancia usando el Builder
    public static ProfesoraLaurita crearInstancia() {
        return (ProfesoraLaurita) new ProfesorBuilder()
            .setNombre("Laurita")
            .setFrecuencia(10)
            .setProbabilidadAparicion(5)
            .setTipoProfesor(ProfesoraLaurita.class)
            .setArea(100, 480, 60, 60)
            .build();
    }

    
    // Implementación del método abstracto requerido por la interfaz
    //unused
    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); // Llama a la versión con ControladorJuego, pasando null
    }

    @Override
public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
    jugador.otorgarInmunidad(DURACION_INMUNIDAD); // Este llamado debe usar "jugador"
    
}



    @Override
    public String getTipo() {
        return "Laurita";
    }
}
