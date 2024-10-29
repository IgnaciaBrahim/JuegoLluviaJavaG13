package io.github.some_example_name;

public abstract class Profesor implements Recoleccionable {
    protected String nombre;
    protected int frecuencia;

    public Profesor(String nombre, int frecuencia) {
        this.nombre = nombre;
        this.frecuencia = frecuencia;
    }

    @Override
    public void caer() {
        // Lógica de movimiento de caída (se puede definir aquí o en las subclases)
    }

    @Override
    public boolean haSidoRecolectado() {
        // Definir la condición de recolección, por ahora como ejemplo:
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    // Método abstracto que las subclases deben implementar
    public abstract void aplicarEfecto(Jugador jugador);
}
