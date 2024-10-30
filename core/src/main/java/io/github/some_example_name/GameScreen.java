package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    private Lluvia lluvia;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar recursos necesarios
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        Jugador jugador = new Jugador(3); // Crea un jugador con 3 vidas o el valor deseado
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound, jugador);

        // Cargar otras texturas y sonidos
        Texture gota = new Texture(Gdx.files.internal("fotoAlfaro.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("fotoCubillos.png"));
        Texture arayaTexture = new Texture(Gdx.files.internal("fotoAraya.png"));
        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // Crear instancia de Lluvia
        lluvia = new Lluvia(gota, gotaMala, arayaTexture, dropSound, rainMusic);

        // Configurar la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Inicializar el tarro y la lluvia
        tarro.crear();
        lluvia.crear();
    }

	@Override
    public void render(float delta) {
        // Limpia la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualiza la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Inicia el dibujo
        batch.begin();

        // Dibuja la información de puntos y vidas
        font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);

        // Llama a actualizarDibujoLluvia para dibujar las gotas (incluyendo Alfaro, Cubillos, Araya)
        lluvia.actualizarDibujoLluvia(batch);

        // Dibuja el tarro
        tarro.dibujar(batch);

        // Termina el dibujo
        batch.end();

        // Actualiza el estado del juego
        if (!tarro.estaHerido()) {
            tarro.actualizarMovimiento();
            if (!lluvia.actualizarMovimiento(tarro)) {
                // Si el jugador pierde todas las vidas, cambia a la pantalla de Game Over
                if (game.getHigherScore() < tarro.getPuntos()) {
                    game.setHigherScore(tarro.getPuntos());
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
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      tarro.destruir();
      lluvia.destruir();

	}

}
