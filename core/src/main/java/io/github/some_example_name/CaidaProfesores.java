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
    private Sound dropSound;
    private Music rainMusic;

    // Constructor modificado
    public CaidaProfesores(Texture profesorAlfaro, Texture profesorCubillos, Texture profesorAraya, Sound ss, Music mm) {
        this.profesorAlfaro = profesorAlfaro;
        this.profesorCubillos = profesorCubillos;
        this.profesorAraya = profesorAraya; 
        this.dropSound = ss;
        this.rainMusic = mm;
    }

    public void crear() {
        rainDropsPos = new Array<Rectangle>();
        rainDropsType = new Array<Integer>();
        crearGotaDeLluvia();
        // Iniciar la reproducción de la música de fondo inmediatamente
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
	
		int tipo = MathUtils.random(1, 100);
		if (tipo <= 15) { // 15% de probabilidad para Araya
			rainDropsType.add(3);
			raindrop.width = 70; // Tamaño específico para Araya
			raindrop.height = 70;
		} else if (tipo <= 30) { // 30% de probabilidad para Cubillos
			rainDropsType.add(1);
			raindrop.width = 50; // Tamaño específico para Cubillos
			raindrop.height = 50;
		} else { // 50% de probabilidad para Alfaro
			rainDropsType.add(2);
			raindrop.width = 60; // Tamaño específico para Alfaro
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
            }

            if (raindrop.overlaps(tarro.getArea())) { 
                if (rainDropsType.get(i) == 1) { // profesor cubillos
                    tarro.dañar();
                    tarro.sumarPuntos(-30);
                    if (tarro.getVidas() <= 0)
                        return false;
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
                } else if (rainDropsType.get(i) == 2) { // profe alfaro a recolectar (buena)
                    tarro.sumarPuntos(10);
                    dropSound.play();
                    rainDropsPos.removeIndex(i);
                    rainDropsType.removeIndex(i);
                } else if (rainDropsType.get(i) == 3) { // Araya
                        ProfesorAraya araya = new ProfesorAraya();
                        araya.aplicarEfecto(tarro.getJugador(), null); 
                        tarro.sumarPuntos(5);
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
			}
		}
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
