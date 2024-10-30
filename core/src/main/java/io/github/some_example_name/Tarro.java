package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Tarro {
    private Rectangle bucket;
    private Texture imagenTarro;
    private Sound sonidoHerido;
    private Sound sonidoBueno;
    //El jugador tiene vidas y puntos, no el tarro.
    //private int vidas = 5;
    //private int puntos = 0;
    private int velx;
    private boolean herido;
    private final int tiempoHeridoMax = 50;
    private int tiempoHerido;

    /*
    public Tarro(Texture tex, Sound ss, Jugador jugador) {
        this.bucketImage = tex;
        this.sonidoHerido = ss;
        this.jugador = jugador;
    }
     */
    
    //El tarro sabe que textura y sonido tiene, no el contexto.
    public Tarro() {
    	this.imagenTarro = new Texture (Gdx.files.internal("bucket.png"));
    	this.sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
    	this.sonidoBueno = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        this.herido = false;
        this.velx = 400;
    }
		public Rectangle getArea() {
			return bucket;
		}
		
		//Nacha: arreglar. la logica de incrementar el puntaje no la sabe el tarrooooooooo aaaaaaa
		/*public void sumarPuntos(int puntos) {
    		jugador.incrementarPuntaje(puntos); 
		}
		*/
	
	   public void crear() {
		      bucket = new Rectangle();
		      bucket.x = 800 / 2 - 64 / 2;
		      bucket.y = 20;
		      bucket.width = 64;
		      bucket.height = 64;
	   }
	   public void dañar() {
		  //vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void dibujar(SpriteBatch batch) {
		 if (!herido)  
		   batch.draw(imagenTarro, bucket.x, bucket.y);
		 else {
		
		   batch.draw(imagenTarro, bucket.x, bucket.y+ MathUtils.random(-5,5));
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   } 
	   public void detenerTemporalmente() {
		herido = true;  // Activa el modo de sacudida
		tiempoHerido = tiempoHeridoMax;  // Resetea el contador de tiempo para la sacudida
		}
	
	   
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(bucket.x < 0) bucket.x = 0;
		   if(bucket.x > 800 - 64) bucket.x = 800 - 64;
	   }
	   
	   public boolean sobreponer(Rectangle profesor)
	   {
		   return bucket.overlaps(profesor);
	   }
	    
	public void destruir() {
		    imagenTarro.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
   
   public void sonidoBueno()
   {
	   sonidoBueno.play();
   }  
}
