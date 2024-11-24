package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;
public class ProfesorPuntos extends Profesor {
    private int puntos;

    public ProfesorPuntos(String nombre, int frecuencia, int probabilidad, int puntos, Rectangle area) {
        super(nombre, frecuencia, probabilidad, area);
        this.puntos = puntos;
    }

    //unused
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.cambiarPuntaje(puntos);
        System.out.println(nombre + " otorga " + puntos + " puntos.");
    }
 
    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        aplicarEfecto(jugador);  
    }
    @Override
    public String getTipo() {
        return "ProfesorPuntos";  
    }
}
