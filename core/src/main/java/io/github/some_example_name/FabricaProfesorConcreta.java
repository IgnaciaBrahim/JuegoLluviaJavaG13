package io.github.some_example_name;

import com.badlogic.gdx.math.MathUtils;

public class FabricaProfesorConcreta implements FabricaProfesor {

    @Override
    public Profesor crearLaurita() {
        return new ProfesoraLaurita();
    }

    @Override
    public Profesor crearAraya() {
        return new ProfesorAraya();
    }

    @Override
    public Profesor crearPowerUp(String tipoPowerUp) {
        return new ProfesorPowerUp("PowerUp", 15, 20, tipoPowerUp); // Ejemplo de valores genéricos
    }

    @Override
    public Profesor crearPuntos(int puntos) {
        return new ProfesorPuntos("Profesor de Puntos", 10, 15, puntos); // Ejemplo de valores genéricos
    }

    @Override
    public Profesor crearVillano() {
        return new ProfesorVillano("Villano Cubillos", 20, 30); // Ejemplo de valores genéricos
    }

    @Override
    public Profesor crearAleatorio() {
        int tipo = MathUtils.random(1, 100); // Probabilidad del tipo de profesor
        if (tipo <= 5) { // Laurita: 5%
            return new ProfesoraLaurita();
        } else if (tipo <= 20) { // Araya: 15%
            return new ProfesorAraya();
        } else if (tipo <= 70) { // Alfaro: 50%
            return new ProfesorAlfaro();
        } else { // Cubillos: otro%
            return new ProfesorVillano("Villano Cubillos", 15, 30);
        }
}
}

