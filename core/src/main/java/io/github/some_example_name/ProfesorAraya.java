package io.github.some_example_name;

public class ProfesorAraya extends Profesor {
    private static final int puntosRacha = 3; // Número de recolecciones para activar el efecto
    private static int contadorRacha = 0; // Contador de racha compartido entre todas las instancias

    public ProfesorAraya() {
        super("Araya", 10, 10);
        this.area.set(100, 480, 60, 60); // Posición y tamaño inicial del área de colisión
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
