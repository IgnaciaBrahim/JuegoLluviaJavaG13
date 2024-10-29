package io.github.some_example_name;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ControladorJuego {
    private List<Profesor> profesoresEnJuego;

    public ControladorJuego() {
        profesoresEnJuego = new ArrayList<>();
    }

    public void agregarProfesor(Profesor profesor) {
        profesoresEnJuego.add(profesor);
    }

    public void recolectarProfesor(Profesor profesor, Jugador jugador) {
        profesor.aplicarEfecto(jugador);
        profesoresEnJuego.remove(profesor);

        if (profesor instanceof ProfesorVillano) {
            activarTormentaCubillos();
        }
    }

    public void activarTormentaCubillos() {
        System.out.println("¡Tormenta de Cubillos activada!");

        Timer timer = new Timer();
        TimerTask tareaTormenta = new TimerTask() {
            private int contador = 0;

            @Override
            public void run() {
                if (contador < 10) {
                    agregarProfesor(new ProfesorVillano("Cubillos", 20));
                    contador++;
                } else {
                    timer.cancel(); 
                    System.out.println("Fin de la tormenta de Cubillos.");
                }
            }
        };

        timer.schedule(tareaTormenta, 0, 500);
    }
}

