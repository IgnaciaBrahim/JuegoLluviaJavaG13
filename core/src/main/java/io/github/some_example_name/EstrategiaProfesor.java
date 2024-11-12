package io.github.some_example_name;

//Nacha: El jugador es afectado por el profesor de formas distintas cuando coalisionan
//Según el tipo de este (Cubillos, Laura, Araya). Se utiliza el patrón Strategy.
public interface EstrategiaProfesor {
	public abstract void efectoProfesor(Jugador jugador, Tarro tarro, ProfesorAraya aa, ProfesoraLaurita ll);
	
}
