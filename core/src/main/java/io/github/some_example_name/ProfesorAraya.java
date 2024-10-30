package io.github.some_example_name;

public class ProfesorAraya extends Profesor {
    private static final int PUNTOS_RACHA = 3; // Número de recolecciones para activar el efecto
    private static int contadorRacha = 0; // Contador de racha compartido entre todas las instancias

    public ProfesorAraya() {
        super("Araya", 10, 10);
        this.area.set(100, 480, 60, 60); // Posición y tamaño inicial del área de colisión
    }

    @Override
    public void aplicarEfecto(Jugador jugador, ControladorJuego controlador) {
        contadorRacha++; // Incrementa el contador de racha
        if (contadorRacha >= PUNTOS_RACHA) { // Verifica si se alcanzó la racha de 3
          if (Math.random() < 0.95) { // 95% de probabilidad de activar el efecto
            jugador.reducirPuntaje(jugador.getPuntaje() / 2); // Divide el puntaje a la mitad
            System.out.println(nombre + " ha dividido el puntaje a la mitad.");
          } else {
            System.out.println(nombre + " no afectó el puntaje esta vez.");
          }
          contadorRacha = 0; // Reinicia el contador de racha
    }
}


    @Override
    public void aplicarEfecto(Jugador jugador) {
        aplicarEfecto(jugador, null); 
    }


    @Override
    public String getTipo() {
        return "Araya";
    }

}
