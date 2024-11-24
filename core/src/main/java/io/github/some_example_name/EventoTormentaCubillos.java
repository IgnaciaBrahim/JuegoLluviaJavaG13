package io.github.some_example_name;

import java.util.Timer;
import java.util.TimerTask;



public class EventoTormentaCubillos extends EventoJuego {
    private Timer timer;
    private int contador = 0;

    public EventoTormentaCubillos(ControladorJuego controlador, Jugador jugador) {
        super(controlador, jugador);
    }

    @Override
    protected void configurarEvento() {
        System.out.println("Configurando tormenta de Cubillos...");
        timer = new Timer();
        ejecutarEvento();
    }

    @Override
    protected void ejecutarEvento() {
        System.out.println("Ejecutando tormenta de Cubillos.");
        TimerTask tareaTormenta = new TimerTask() {
            @Override
            public void run() {
                if (contador < 10 + controlador.getTiempoJugado() / 60) {
                    System.out.println("Aparece un Cubillos"); // Confirmación
                    controlador.agregarProfesor(
                        new ProfesorBuilder<ProfesorVillano>()
                            .setNombre("Cubillos")
                            .setFrecuencia(20)
                            .setProbabilidadAparicion(50)
                            .setTipoProfesor(ProfesorVillano.class)
                            .setArea(100, 480, 60, 60)
                            .build()
                    );
                    contador++;
                } else {
                    finalizarEvento();
                }
            }
        };
        int intervaloAparicion = Math.max(500 - (int) (controlador.getTiempoJugado() * 2), 100);
        timer.schedule(tareaTormenta, 0, intervaloAparicion);
    }
    

    @Override
    protected void finalizarEvento() {
        System.out.println("Finalizando tormenta de Cubillos.");
        if (timer != null) {
            timer.cancel();
        }
    }
}
