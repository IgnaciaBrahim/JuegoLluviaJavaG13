package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

//Modela la forma en la que caen los profesores
public class CaidaProfesores {
    private Array<Rectangle> posicionProfesores;
    private Array<Integer> tipoProfesores;
    private long tiempoUltimoProfesor;
    private Texture profesorAlfaro;
    private Texture profesorCubillos;
    private Texture profesorAraya;
    private Texture fotoDeLaurita; // Añadir la textura de Laurita
    //El profesor sabe que sonido hace en el juego
    //private Sound dropSound;
    //La música es intrinseca al escenario donde los profesores caen :)
    //private Music rainMusic;

    // Constructor modificado
    public CaidaProfesores(Texture profesorAlfaro, Texture profesorCubillos, Texture profesorAraya, Texture fotoDeLaurita) {
        this.profesorAlfaro = profesorAlfaro;
        this.profesorCubillos = profesorCubillos;
        this.profesorAraya = profesorAraya;
        this.fotoDeLaurita = fotoDeLaurita; // Inicializa la textura de Laurita
        //this.rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
    }

    private void crearGotaDeLluvia() {
        Rectangle profe = new Rectangle();
        profe.x = MathUtils.random(0, 800 - 64);
        profe.y = 480;

        int tipo = MathUtils.random(1, 100);
        if (tipo <= 5) { // 2% de probabilidad para Laurita
            tipoProfesores.add(4); // Identificador para Laurita
            profe.width = 60;
            profe.height = 60;
        } else if (tipo <= 15) { // Araya, 15% probabilidad
            tipoProfesores.add(3);
            profe.width = 70;
            profe.height = 70;
        } else if (tipo <= 30) { // Cubillos
            tipoProfesores.add(1);
            profe.width = 50;
            profe.height = 50;
        } else { // Alfaro
            tipoProfesores.add(2);
            profe.width = 60;
            profe.height = 60;
        }

        posicionProfesores.add(profe);
        tiempoUltimoProfesor = TimeUtils.nanoTime();
    }

    
	

    public boolean actualizarMovimiento(Tarro tarro, Jugador jugador) { 
        if (TimeUtils.nanoTime() - tiempoUltimoProfesor > 500000000) crearGotaDeLluvia();

    
        for (int i = 0; i < posicionProfesores.size; i++) {
            Rectangle profe = posicionProfesores.get(i);
            //set y ?
            profe.y -= 150 * Gdx.graphics.getDeltaTime();
            
            //get y
            if (profe.y + 64 < 0) {
            	posicionProfesores.removeIndex(i); 
                tipoProfesores.removeIndex(i);
                continue;
            }
    
            if (tarro.sobreponer(profe)) { 
    
                if (tipoProfesores.get(i) == 1) { // Profesor Cubillos
                    if (!jugador.esInmune()) { // Aplica efecto solo si no es inmune
                    	tarro.dañar();
                    	jugador.cambiarPuntaje(-30);
                        jugador.cambiarVida(-1);
                        if (jugador.getVida() == 0)
                        {
                 		   return false; //no sigue el juego
                        }
                    }
                    posicionProfesores.removeIndex(i);
                    tipoProfesores.removeIndex(i);
    
                } else if (tipoProfesores.get(i) == 2) { // Profesor Alfaro (beneficioso)
                    tarro.sonidoBueno();
                	jugador.cambiarPuntaje(10);
                    //dropSound.play(); se modifica a profesor.Sonido();
                    posicionProfesores.removeIndex(i);
                    tipoProfesores.removeIndex(i);
    
                } else if (tipoProfesores.get(i) == 3) { // Profesor Araya
                	tarro.sonidoExtrano();
                    ProfesorAraya araya = new ProfesorAraya();
                    araya.aplicarEfecto(jugador, null); // Aplica el efecto al jugador
                    posicionProfesores.removeIndex(i);
                    tipoProfesores.removeIndex(i);
                }
                 else if (tipoProfesores.get(i) == 4) { // Profesora Laurita
                	tarro.sonidoEspecial();
                    ProfesoraLaurita laurita = new ProfesoraLaurita();
                    laurita.aplicarEfecto(jugador, null); // Otorga inmunidad
                    jugador.cambiarPuntaje(15);
                    posicionProfesores.removeIndex(i);
                    tipoProfesores.removeIndex(i);
                }
            }
        }
        return true;
    }
    
    public void actualizarDibujoLluvia(SpriteBatch batch) { 
        for (int i = 0; i < posicionProfesores.size; i++) {
            Rectangle profe = posicionProfesores.get(i);
            if (tipoProfesores.get(i) == 1) { // gota dañina (Cubillos)
                batch.draw(profesorCubillos, profe.x, profe.y, 50, 50); 
            } else if (tipoProfesores.get(i) == 2) { // gota buena (Alfaro)
                batch.draw(profesorAlfaro, profe.x, profe.y, 60, 60); 
            } else if (tipoProfesores.get(i) == 3) { // Araya
                batch.draw(profesorAraya, profe.x, profe.y, 70, 70);  
            } else if (tipoProfesores.get(i) == 4) { // Laurita
                batch.draw(fotoDeLaurita, profe.x, profe.y, 60, 60); // Dibuja la textura de Laurita
            }
        }
    }
    
	public void crear() {
		posicionProfesores = new Array<Rectangle>();
        tipoProfesores = new Array<Integer>();
        crearGotaDeLluvia(); // Crea una primera gota de lluvia
    
        // Iniciar la reproducción de la música de fondo inmediatamente
        //rainMusic.setLooping(true);
        //rainMusic.play();
    }
    

	/*
    public void destruir() {
        //dropSound.dispose();
        //rainMusic.dispose();
    }

    public void pausar() {
        if (rainMusic.isPlaying()) {
            rainMusic.pause();
        }
    }

    public void continuar() {
        if (!rainMusic.isPlaying()) {
            rainMusic.play();
        }
    }
    */
}
