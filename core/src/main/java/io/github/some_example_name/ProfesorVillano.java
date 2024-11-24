package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;
public class ProfesorVillano extends Profesor {

    public ProfesorVillano(String nombre, int frecuencia, int probabilidad, Rectangle area) {
        super(nombre, frecuencia, probabilidad, area);
    }

    //unused
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.cambiarVida(-1);  
        jugador.cambiarPuntaje(-20);  
        System.out.println(nombre + " reduce la vida y el puntaje.");
    }

    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        aplicarEfecto(jugador); // Llama a la otra versión de aplicarEfecto
    }
    @Override
    public String getTipo() {
        return "Villano";
    }
}
