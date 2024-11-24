package io.github.some_example_name;
import io.github.some_example_name.EstrategiaProfesor;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import io.github.some_example_name.EstrategiaArayaMalo;
import io.github.some_example_name.EstrategiaArayaBueno;
import io.github.some_example_name.EstrategiaAlfaro;
import io.github.some_example_name.EstrategiaLaurita;

//Modela la forma en la que caen los profesores
public class CaidaProfesores {
    private Array<Rectangle> posicionProfesores;
    private Array<Integer> tipoProfesores;
    private long tiempoUltimoProfesor;
    private Texture profesorAlfaro;
    private Texture profesorCubillos;
    private Texture profesorAraya;
    private Texture fotoDeLaurita;
    private EstrategiaProfesor estrategiaProfesor;
    private ProfesorAraya profeAraya;
    private ProfesoraLaurita profeLaura;
    private FabricaProfesor fabricaProfesor; 
    
    
    //El profesor sabe que sonido hace en el juego
    //private Sound dropSound;
    //La música es intrinseca al escenario donde los profesores caen :)
    //private Music rainMusic;

    // Constructor modificado
    public CaidaProfesores(Texture profesorAlfaro, Texture profesorCubillos, Texture profesorAraya, Texture fotoDeLaurita, FabricaProfesor fabricaProfesor) {
        this.profesorAlfaro = profesorAlfaro;
        this.profesorCubillos = profesorCubillos;
        this.profesorAraya = profesorAraya;
        this.fotoDeLaurita = fotoDeLaurita; // Inicializa la textura de Laurita
        //this.rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        this.profeAraya = new ProfesorAraya();
        this.profeLaura = new ProfesoraLaurita();
        this.fabricaProfesor = fabricaProfesor;
        //Son profesores instanciados para regular sus rachas e inmunidades.
    }
    
    
    //Para aplicar patron strategy a la forma en la que los profes afectan a los 
    //jugadores o al tarro :)
    private void setearEstrategia(int tipo, boolean rachaAraya) {
        switch (tipo) {
            case 1: 
            	this.estrategiaProfesor = new EstrategiaCubillos();
            	break;
            case 2: 
            	this.estrategiaProfesor = new EstrategiaAlfaro();
            	break;
            case 3:
            	if (rachaAraya)
            	{
            		this.estrategiaProfesor = new EstrategiaArayaMalo();
            	}
            	else 
            	{
            		this.estrategiaProfesor = new EstrategiaArayaBueno();
            	}
            	break;
            case 4: 
            	this.estrategiaProfesor = new EstrategiaLaurita();
            	break;
            default: throw new IllegalArgumentException("Tipo de profesor no reconocido");
        }
    }


    private void crearGotaDeLluvia() {
        Profesor profesor = fabricaProfesor.crearAleatorio(); // Uso de la fábrica
        Rectangle profe = new Rectangle();
        profe.x = MathUtils.random(0, 800 - 64);
        profe.y = 480;

        // Asignar dimensiones y agregar el tipo de profesor
        if (profesor instanceof ProfesorVillano) {
            tipoProfesores.add(1); // Villano (Cubillos)
            profe.width = 50;
            profe.height = 50;
        } else if (profesor instanceof ProfesorAraya) {
            tipoProfesores.add(3); // Araya
            profe.width = 70;
            profe.height = 70;
        } else if (profesor instanceof ProfesoraLaurita) {
            tipoProfesores.add(4); // Laurita
            profe.width = 60;
            profe.height = 60;
        } else { // Alfaro 
            tipoProfesores.add(2); // Alfaro
            profe.width = 60;
            profe.height = 60;
        }

        posicionProfesores.add(profe);
        tiempoUltimoProfesor = TimeUtils.nanoTime();
    }

    // SOBREESCRITURA WUOUOUOU
	public void crearGotaDeLluvia(Profesor profesor) {
        Rectangle profe = new Rectangle();
        profe.x = MathUtils.random(0, 800 - 64);
        profe.y = 480;
    
        if (profesor instanceof ProfesorVillano) {
            tipoProfesores.add(1); // Identificador para Cubillos
            profe.width = 50;
            profe.height = 50;
        }
    
        posicionProfesores.add(profe);
        tiempoUltimoProfesor = TimeUtils.nanoTime();
    }
    

    public boolean actualizarMovimiento(Tarro tarro, Jugador jugador) 
    { 
        if (TimeUtils.nanoTime() - tiempoUltimoProfesor > 500000000) crearGotaDeLluvia();

    
        for (int i = 0; i < posicionProfesores.size; i++) 
        {
            Rectangle profe = posicionProfesores.get(i);
            //set y ?
            profe.y -= 150 * Gdx.graphics.getDeltaTime();
            
            //get y
            if (profe.y + 64 < 0) 
            {
            	posicionProfesores.removeIndex(i); 
                tipoProfesores.removeIndex(i);
                continue;
            }
            
            //Patrón Strategy: Cómo el profesor afecta al tarro y al jugador
            //Depende de qué profesor es.
            if (tarro.sobreponer(profe)) { 
            	boolean rachaProfesorAraya = profeAraya.obtenerRacha();
            	int tipoProfesor = tipoProfesores.get(i);
                setearEstrategia(tipoProfesor, rachaProfesorAraya);
                estrategiaProfesor.efectoProfesor(jugador, tarro, profeAraya, profeLaura);
                if (jugador.getVida() == 0)
                {
                	return false;
                }
                posicionProfesores.removeIndex(i);
                tipoProfesores.removeIndex(i);
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
