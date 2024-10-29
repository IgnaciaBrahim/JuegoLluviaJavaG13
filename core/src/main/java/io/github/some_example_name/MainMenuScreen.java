package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;


public class MainMenuScreen implements Screen {

	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private float elapsedTime;
	//new color indigo :)
	private final Color[] rainbowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(0.29f, 0.0f, 0.51f, 1), Color.VIOLET};;
	private int currentColorIndex;
	private Color currentColor; // Color actual inicial
	private Color targetColor; // Primer color objetivo

	public MainMenuScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.elapsedTime = 0f;
		this.currentColorIndex = 0;
		this.currentColor = Color.RED.cpy();
		this.targetColor = Color.ORANGE.cpy();
		
	}

	@Override
	public void render(float delta) {
		 elapsedTime += delta;
	    // Cambia de color objetivo cada 0.5 segundos
	    if (elapsedTime >= 0.7f) {
	        elapsedTime = 0f;
	        currentColorIndex = (currentColorIndex + 1) % rainbowColors.length;
	        targetColor.set(rainbowColors[currentColorIndex]);
	    }

	    // Interpola suavemente entre el color actual y el color objetivo
	    currentColor.lerp(targetColor, 0.1f); // Cambia el valor de 0.1f para ajustar la velocidad

	    // Limpia la pantalla con el color interpolado
	    ScreenUtils.clear(currentColor.r, currentColor.g, currentColor.b, 1);

	    camera.update();
	    batch.setProjectionMatrix(camera.combined);

	    batch.begin();
	    font.getData().setScale(2, 2);
	    font.draw(batch, "Bienvenido a TeacherCatch IBC!", 100, camera.viewportHeight/2 + 50);
	    font.draw(batch, "Toca en cualquier lugar para comenzar...", 100, camera.viewportHeight/2 - 50);
	    batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
