package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private Tarro tarro;
    private Jugador jugador;
    private CaidaProfesores caidaProfes;
    private Texture fondoSpriteDia;
    private Music rainMusic;
    

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar recursos necesarios
        this.jugador = new Jugador(5); // Crea un jugador con 3 vidas o el valor deseado
        tarro = new Tarro();

        // Cargar otras texturas y sonidos
        Texture profeAlfaro = new Texture(Gdx.files.internal("fotoAlfaro.png"));
        Texture profeCubillos = new Texture(Gdx.files.internal("fotoCubillos.png"));
        Texture arayaTexture = new Texture(Gdx.files.internal("fotoAraya.png"));
        Texture lauritaTexture = new Texture(Gdx.files.internal("fotoDeLaurita.png"));
        //Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        this.rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        this.fondoSpriteDia = new Texture(Gdx.files.internal("fondoSpriteDia.jpg"));

        // Crear instancia de Lluvia
        caidaProfes = new CaidaProfesores(profeAlfaro, profeCubillos, arayaTexture, lauritaTexture);

        // Configurar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Inicializar el tarro y la lluvia
        tarro.crear();
        caidaProfes.crear();
        rainMusic.play();
        rainMusic.setLooping(true);
    }

	@Override
    public void render(float delta) {
        // Limpia la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
        //Verificar si el jugador quiere pausar el juego apretando Esc :D
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(new PausaScreen(game, this)); // Cambia a la pantalla de pausa
            return; // Termina el render actual para evitar dibujar el resto de elementos
        }

        // Actualiza la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        

        // Inicia el dibujo
        batch.begin();
        batch.draw(fondoSpriteDia, 0, 0, camera.viewportWidth, camera.viewportHeight);

        // Dibuja la información de puntos y vidas
        font.draw(batch, "Puntaje: " + jugador.getPuntaje(), 5, 475);
        font.draw(batch, "Vidas : " + jugador.getVida(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);

        // Llama a actualizarDibujoLluvia para dibujar las gotas (incluyendo Alfaro, Cubillos, Araya)
        caidaProfes.actualizarDibujoLluvia(batch);

        // Dibuja el tarro
        tarro.dibujar(batch);

        // Termina el dibujo
        batch.end();

        // Actualiza el estado del juego
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();
            if (!caidaProfes.actualizarMovimiento(tarro, jugador)) {
                // Si el jugador pierde todas las vidas, cambia a la pantalla de Game Over
                if (game.getHigherScore() < jugador.getPuntaje()) {
                    game.setHigherScore(jugador.getPuntaje());
                }
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }
    }

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
		//caidaProfes.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		rainMusic.pause();
		//caidaProfes.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {
		rainMusic.play();
		//caidaProfes.continuar();
	}

	@Override
	public void dispose() {
      tarro.destruir();
      rainMusic.dispose();
      //caidaProfes.destruir();

	}

}
