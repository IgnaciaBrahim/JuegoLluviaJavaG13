package io.github.some_example_name;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.math.MathUtils;

public class ControladorJuego {
    private List<Profesor> profesoresEnJuego;
    private long tiempoJugado;   

    public ControladorJuego() {
        profesoresEnJuego = new ArrayList<>();
        tiempoJugado = 0;  // Inicializa el tiempo de juego
        iniciarContadorTiempo();
    }

    private void iniciarContadorTiempo() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tiempoJugado++;  
            }
        }, 0, 1000);  
    }

    public void agregarProfesor(Profesor profesor) {
        profesoresEnJuego.add(profesor);
    }

    public void activarTormentaCubillos() {
        System.out.println("¡Tormenta de Cubillos activada!");

        int intervaloAparicion = Math.max(500 - (int)(tiempoJugado * 2), 100); 

        Timer timer = new Timer();
        TimerTask tareaTormenta = new TimerTask() {
            private int contador = 0;

            @Override
            public void run() {
                if (contador < 10 + tiempoJugado / 60) {  
                    agregarProfesor(new ProfesorVillano("Cubillos", 20, 50));
                    contador++;
                } else {
                    timer.cancel();
                    System.out.println("Fin de la tormenta de Cubillos.");
                }
            }
        };

        timer.schedule(tareaTormenta, 0, intervaloAparicion); 
    }

    public void recolectarProfesor(Profesor profesor, Jugador jugador) {
        profesor.aplicarEfecto(jugador, this);
        profesoresEnJuego.remove(profesor);

        if (profesor instanceof ProfesorVillano) {
            activarTormentaCubillos();
        }
    }

    public void activarCaidaProfesores() {
        if (MathUtils.random(1, 100) <= 10) { // probabilidad para ProfesorAraya
        agregarProfesor(new ProfesorAraya());
        }    
    }

    public void activarCaidaProfesorVillano() {
        int probabilidadVillano = calcularProbabilidadVillano();
        if (MathUtils.random(1, 100) <= probabilidadVillano) { 
            agregarProfesor(new ProfesorVillano("Cubillos", 20, probabilidadVillano));
        }
    }

    private int calcularProbabilidadVillano() {
        return Math.min(10 + (int)(tiempoJugado / 30), 50);  
    }

}


