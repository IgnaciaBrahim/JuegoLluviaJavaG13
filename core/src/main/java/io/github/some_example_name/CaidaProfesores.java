package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class CaidaProfesores {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture profesorAlfaro;
    private Texture profesorCubillos;
    private Texture profesorAraya;
    private Texture fotoDeLaurita; // Añadir la textura de Laurita
    private Sound dropSound;
    private Music rainMusic;

    // Constructor modificado
    public CaidaProfesores(Texture profesorAlfaro, Texture profesorCubillos, Texture profesorAraya, Texture fotoDeLaurita, Sound ss, Music mm) {
        this.profesorAlfaro = profesorAlfaro;
        this.profesorCubillos = profesorCubillos;
        this.profesorAraya = profesorAraya;
        this.fotoDeLaurita = fotoDeLaurita; // Inicializa la textura de Laurita
        this.dropSound = ss;
        this.rainMusic = mm;
    }

    private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;

        int tipo = MathUtils.random(1, 100);
        if (tipo <= 2) { // 2% de probabilidad para Laurita
            rainDropsType.add(4); // Identificador para Laurita
            raindrop.width = 60;
            raindrop.height = 60;
        } else if (tipo <= 15) { // Araya, 15% probabilidad
            rainDropsType.add(3);
            raindrop.width = 70;
            raindrop.height = 70;
        } else if (tipo <= 30) { // Cubillos
            rainDropsType.add(1);
            raindrop.width = 50;
            raindrop.height = 50;
        } else { // Alfaro
            rainDropsType.add(2);
            raindrop.width = 60;
            raindrop.height = 60;
        }

        rainDropsPos.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    
	

    public boolean actualizarMovimiento(Tarro tarro) { 
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
    
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
    
            if (raindrop.y + 64 < 0) {
                rainDropsPos.removeIndex(i); 
                rainDropsType.removeIndex(i);
                continue;
            }
    
            if (raindrop.overlaps(tarro.getArea())) { 
                Jugador jugador = tarro.getJugador();
    
                if (rainDropsType.get(i) == 1) { // Profesor Cubillos
                    if (!jugador.esInmune()) { // Aplica efecto solo si no es inmune
                        tarro.dañar();
                        tarro.sumarPuntos(-30);
                        if (tarro.getVidas() <= 0)
                            return false;
                    }
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
    
                } else if (rainDropsType.get(i) == 2) { // Profesor Alfaro (beneficioso)
                    tarro.sumarPuntos(10);
                    dropSound.play();
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
    
                } else if (rainDropsType.get(i) == 3) { // Profesor Araya
                    ProfesorAraya araya = new ProfesorAraya();
                    araya.aplicarEfecto(tarro.getJugador(), null); // Aplica el efecto al jugador
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
                }
                 else if (rainDropsType.get(i) == 4) { // Profesora Laurita
                    ProfesoraLaurita laurita = new ProfesoraLaurita();
                    laurita.aplicarEfecto(jugador, null); // Otorga inmunidad
                    tarro.sumarPuntos(15);
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
                }
            }
        }
        return true;
    }
       
    

    public void actualizarDibujoLluvia(SpriteBatch batch) { 
        for (int i = 0; i < rainDropsPos.size; i++) {
            Rectangle raindrop = rainDropsPos.get(i);
            if (rainDropsType.get(i) == 1) { // gota dañina (Cubillos)
                batch.draw(profesorCubillos, raindrop.x, raindrop.y, 50, 50); 
            } else if (rainDropsType.get(i) == 2) { // gota buena (Alfaro)
                batch.draw(profesorAlfaro, raindrop.x, raindrop.y, 60, 60); 
            } else if (rainDropsType.get(i) == 3) { // Araya
                batch.draw(profesorAraya, raindrop.x, raindrop.y, 70, 70);  
            } else if (rainDropsType.get(i) == 4) { // Laurita
                batch.draw(fotoDeLaurita, raindrop.x, raindrop.y, 60, 60); // Dibuja la textura de Laurita
            }
        }
    }
    
	public void crear() {
        rainDropsPos = new Array<Rectangle>();
        rainDropsType = new Array<Integer>();
        crearGotaDeLluvia(); // Crea una primera gota de lluvia
    
        // Iniciar la reproducción de la música de fondo inmediatamente
        rainMusic.setLooping(true);
        rainMusic.play();
    }
    

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    }
}
