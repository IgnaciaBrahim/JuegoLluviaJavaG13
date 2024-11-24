package io.github.some_example_name;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.badlogic.gdx.math.MathUtils;

public class ControladorJuego {
    private static ControladorJuego instancia; // Singleton
    private List<Profesor> profesoresEnJuego;
    private long tiempoJugado;
    private Jugador jugador;
    private CaidaProfesores caidaProfesores;
    private FabricaProfesor fabricaProfesor; // Nueva dependencia

    // Constructor privado
    private ControladorJuego(Jugador jugador, CaidaProfesores caidaProfesores, FabricaProfesor fabricaProfesor) {
        this.profesoresEnJuego = new ArrayList<>();
        this.tiempoJugado = 0;
        this.jugador = jugador;
        this.caidaProfesores = caidaProfesores;
        this.fabricaProfesor = fabricaProfesor; // Inicialización de la fábrica
        iniciarContadorTiempo();
    }

    // Método estático para obtener la instancia única
    public static ControladorJuego getInstance(Jugador jugador, CaidaProfesores caidaProfesores, FabricaProfesor fabricaProfesor) {
        if (instancia == null) {
            synchronized (ControladorJuego.class) {
                if (instancia == null) {
                    instancia = new ControladorJuego(jugador, caidaProfesores, fabricaProfesor);
                }
            }
        }
        return instancia;
    }

    public void iniciarTormentaCubillos() {
        EventoTormentaCubillos tormenta = new EventoTormentaCubillos(this, jugador);
        tormenta.configurarEvento();
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

    public void activarCaidaProfesores() {
        if (MathUtils.random(1, 100) <= 10) {
            agregarProfesor(fabricaProfesor.crearAraya());
        }
    }

    public void activarCaidaProfesorVillano() {
        int probabilidadVillano = calcularProbabilidadVillano();
        if (MathUtils.random(1, 100) <= probabilidadVillano) {
            agregarProfesor(new ProfesorVillano("Cubillos", 20, probabilidadVillano));
        }
    }

    private int calcularProbabilidadVillano() {
        return Math.min(10 + (int) (tiempoJugado / 30), 50);
    }
}
