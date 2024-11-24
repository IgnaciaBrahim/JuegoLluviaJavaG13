package io.github.some_example_name;

import com.badlogic.gdx.math.Rectangle;

public class ProfesorAraya extends Profesor {
    private static final int puntosRacha = 3; // Número de recolecciones para activar el efecto
    private static int contadorRacha = 0; // Contador de racha compartido entre todas las instancias


    public ProfesorAraya(String nombre, int frecuencia, int probabilidad, Rectangle area) {
        super(nombre, frecuencia, probabilidad, area);
    }

    // Método estático para construir una instancia usando el Builder
    public static ProfesorAraya crearInstancia() {
        return new ProfesorBuilder<ProfesorAraya>()
            .setNombre("Araya")
            .setFrecuencia(15)
            .setProbabilidadAparicion(10)
            .setTipoProfesor(ProfesorAraya.class)
            .setArea(100, 480, 60, 60)
            .build();
    }

    

    //unused
    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        contadorRacha++;
        if (contadorRacha >= puntosRacha) {
            /*if (!jugador.esInmune() && Math.random() < 0.9) { // 90% de probabilidad si no es inmune
                jugador.cambiarPuntaje( -1*jugador.getPuntaje() / 2); // Divide el puntaje a la mitad
            } */
        	//Se aplicó en el patrón Strategy
            contadorRacha = 0; // Reiniciar racha
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); 
    }
    
    public boolean obtenerRacha()
    {
    	return contadorRacha >= puntosRacha;
    }

    @Override
    public String getTipo() {
        return "Araya";
    }
}
