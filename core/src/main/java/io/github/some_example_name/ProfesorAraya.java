package io.github.some_example_name;

public class ProfesorAraya extends Profesor {
    private static final int puntosRacha = 3; // Número de recolecciones para activar el efecto
    private static int contadorRacha = 1; // Contador de racha compartido entre todas las instancias

    public ProfesorAraya() {
        super("Araya", 10, 10);
        this.area.set(100, 480, 60, 60); // Posición y tamaño inicial del área de colisión
    }
    
    //unused
    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null);
    }
    
    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego nulo) {
    	System.out.println("Se aplica efecto en Strategy");
    }
    
    public boolean obtenerRacha()
    {
    	return (contadorRacha > puntosRacha);
    }
    
    public void incrementarRacha() {
        contadorRacha++;
    }

    public void reiniciarRacha() {
        contadorRacha = 1;
    }

    @Override
    public String getTipo() {
        return "Araya";
    }
}
