package io.github.some_example_name;


public interface FabricaProfesor {
    Profesor crearLaurita();
    Profesor crearAraya();
    Profesor crearPowerUp(String tipoPowerUp);
    Profesor crearPuntos(int puntos);
    Profesor crearVillano();
    Profesor crearAleatorio();
}
