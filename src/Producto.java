public class Producto{

    private int codigo;
    private String decripcion;
    private float peso;

    public Producto(int codigo, String decripcion, float peso) {
        this.codigo = codigo;
        this.decripcion = decripcion;
        this.peso = peso;
    }

    public Producto() {
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public float getPeso() {
        return peso;
    }
}
