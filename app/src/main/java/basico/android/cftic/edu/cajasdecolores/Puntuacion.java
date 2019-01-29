package basico.android.cftic.edu.cajasdecolores;

public class Puntuacion {

    private String nombre;
    private long tiempo;
    private int id;


    /**
     * Construcctores
     * */
    public Puntuacion(String nombre, long tiempo, int id) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.id = id;
    }

    public Puntuacion() {
    }


    /**
     * Propiedades
     * */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * Metodo que sobreescribe el metodo toString()
     * Boton dereche Generate.. toString seleccionando
     **/
    @Override
    public String toString() {
        return "Puntuacion{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                ", id=" + id +
                '}';
    }
}
