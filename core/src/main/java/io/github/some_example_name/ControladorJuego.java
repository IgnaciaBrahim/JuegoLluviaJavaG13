package io.github.some_example_name;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.badlogic.gdx.math.MathUtils;

public class ControladorJuego {
    private static ControladorJuego instancia; // Atributo estático para la instancia única
    private List<Profesor> profesoresEnJuego;
    private long tiempoJugado;
    private Jugador jugador;
    private CaidaProfesores caidaProfesores;

    // Constructor privado para evitar instanciación directa
    private ControladorJuego(Jugador jugador, CaidaProfesores caidaProfesores) {
        this.profesoresEnJuego = new ArrayList<>();
        this.tiempoJugado = 0;
        this.jugador = jugador;
        this.caidaProfesores = caidaProfesores; // Asigna la referencia de CaidaProfesores
        iniciarContadorTiempo();
    }

    // Método estático para obtener la instancia única
    public static ControladorJuego getInstance(Jugador jugador, CaidaProfesores caidaProfesores) {
        if (instancia == null) {
            synchronized (ControladorJuego.class) {
                if (instancia == null) {
                    instancia = new ControladorJuego(jugador, caidaProfesores);
                }
            }
        }
        return instancia;
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
        System.out.println("Profesor " + profesor.getNombre() + " agregado al juego");
        caidaProfesores.crearGotaDeLluvia(profesor);
    }

    public long getTiempoJugado() {
        return tiempoJugado;
    }

    public void activarEventoEspecial(String tipoEvento) {
        EventoJuego evento;

        switch (tipoEvento) {
            case "TormentaCubillos":
                evento = new EventoTormentaCubillos(this, jugador);
                break;
            default:
                throw new IllegalArgumentException("Tipo de evento desconocido: " + tipoEvento);
        }

        evento.activarEvento();
    }

    public void recolectarProfesor(Profesor profesor) {
        profesor.aplicarEfecto(jugador, this);
        profesoresEnJuego.remove(profesor);

        if (profesor instanceof ProfesorVillano) {
            System.out.println("Iniciando Tormenta de Cubillos");
            activarEventoEspecial("TormentaCubillos");
        }
    }

    public void iniciarTormentaCubillos() {
        EventoTormentaCubillos tormenta = new EventoTormentaCubillos(this, jugador);
        tormenta.configurarEvento();
    }

    public void activarCaidaProfesores() {
        if (MathUtils.random(1, 100) <= 10) {
            agregarProfesor(
                new ProfesorBuilder<ProfesorAraya>()
                    .setNombre("Araya")
                    .setFrecuencia(15)
                    .setProbabilidadAparicion(10)
                    .setTipoProfesor(ProfesorAraya.class)
                    .setArea(100, 480, 60, 60)
                    .build()
            );
        }
    }

    public void activarCaidaProfesorVillano() {
        int probabilidadVillano = calcularProbabilidadVillano();
        if (MathUtils.random(1, 100) <= probabilidadVillano) {
            ProfesorVillano villano = (ProfesorVillano) new ProfesorBuilder()
                .setNombre("Cubillos")
                .setFrecuencia(20)
                .setProbabilidadAparicion(probabilidadVillano)
                .setTipoProfesor(ProfesorVillano.class)
                .setArea(100, 480, 60, 60) // Coordenadas y tamaño
                .build();
    
            agregarProfesor(villano);
        }
    }

    private int calcularProbabilidadVillano() {
        return Math.min(10 + (int) (tiempoJugado / 30), 50);
    }
}
