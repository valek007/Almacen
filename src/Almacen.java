/*

Crea una aplicación en Java que permita almacenar la información de los productos de una empresa. La información que se desea almacenar de cada producto es: el código (entero), la descripción (texto) y el peso (valor real con parte decimal). El programa ofrecerá cinco opciones: 1. Introducir los productos; 2. Visualizarlos en pantalla; 3. Guardar archivo de texto; 4. Leer archivo; y 5. Salir.

Realiza las siguientes acciones:

1. Crea la clase producto con las propiedades y el constructor que permita inicializar con todos los campos.

2. Crea un método o función que visualice el menú.

3. Crea un método o función por cada opción del menú (introducirProds(), visualizaProds(), guardaProds(), leeProds()).

4. Crea el programa principal que use los métodos anteriores.

 */

import java.io.*;

public class Almacen {

    public static Producto producto;

    public static final  String INTROMENU =
            "\n-------Menu----------------------\n"+
            "| 1. Introducir los productos    |\n"+
            "| 2. Visualizarlos en pantalla   |\n"+
            "| 3. Guardar archivo de texto    |\n"+
            "| 4. Leer archivo                |\n"+
            "| 5. Salir                       |\n"+
            "----------------------------------\n";

    public static void main(String[] args) throws IOException {

        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException j){
            System.out.println("No has introducido el producto, prueba de nuevo.");
        }
    }

    private static void menu() throws IOException {

        System.out.println(INTROMENU);
        boolean isStoped = false;
        int n = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){

            while(!isStoped) {
                n = Integer.parseInt(bufferedReader.readLine());

                switch (n) {
                    case 1:
                        introducirProds(bufferedReader);
                        System.out.println(INTROMENU);
                        break;
                    case 2:
                        visualizaProds();
                        System.out.println(INTROMENU);
                        break;
                    case 3:
                        guardaProds();
                        System.out.println(INTROMENU);
                        break;
                    case 4:
                        leeProds();
                        System.out.println(INTROMENU);
                        break;
                    case 5:
                        System.out.println("Programa finalizado.");
                        isStoped = true;
                        break;
                    default:
                        System.out.println("¡Has introducido un número incorrecto!");
                        isStoped = true;
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void introducirProds(BufferedReader reader) throws IOException {

            System.out.println("Introduce el código del producto.");

            int codigo = Integer.parseInt(reader.readLine());

            System.out.println("Introduce la descripción del prooducto.");

            String descripcion = reader.readLine();

            System.out.println("Introduce el peso del producto.");

            float peso = Float.parseFloat(reader.readLine());

            producto = new Producto(codigo,descripcion,peso);

    }
    private static void visualizaProds(){

            int cod = producto.getCodigo();

            if(cod!=0){
                System.out.println("Código: "+producto.getCodigo()+"\nDescripción: "+producto.getDecripcion()+"\nPeso: "+producto.getPeso()+"kg\n");
            }else{
                System.out.println("El producto no existe.");
            }

    }
    private static void guardaProds(){

        int cod = producto.getCodigo();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("archivador.txt",true))){

            if(cod!=0) {
                writer.write("Código: " + producto.getCodigo() + "\n");
                writer.write("Descripción: " + producto.getDecripcion() + "\n");
                writer.write("Peso: " + producto.getPeso() + "kg\n");
                writer.write("\n");
            }else{
                System.out.println("No hay nada por introducir");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Producto con el código "+producto.getCodigo()+" guardado.");
    }
    private static void leeProds() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader("archivador.txt"))){

            System.out.println("Todos los productos del Almacen:\n");

            while(true){
                try {
                    System.out.println(reader.readLine());
                    if (!reader.ready()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

