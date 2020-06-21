/*

Crea una aplicación en Java que permita almacenar la información de los productos de una empresa. La información que se desea almacenar de cada producto es: el código (entero), la descripción (texto) y el peso (valor real con parte decimal). El programa ofrecerá cinco opciones: 1. Introducir los productos; 2. Visualizarlos en pantalla; 3. Guardar archivo de texto; 4. Leer archivo; y 5. Salir.

Realiza las siguientes acciones:

1. Crea la clase producto con las propiedades y el constructor que permita inicializar con todos los campos.

2. Crea un método o función que visualice el menú.

3. Crea un método o función por cada opción del menú (introducirProds(), visualizaProds(), guardaProds(), leeProds()).

4. Crea el programa principal que use los métodos anteriores.

 */

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Almacen {

    private static ArrayList<Producto> listaProductos = new ArrayList<>();

    public static final  String INTROMENU =
            "\n-------Menu----------------------\n"+
            "| 1. Introducir los productos    |\n"+
            "| 2. Visualizarlos en pantalla   |\n"+
            "| 3. Guardar archivo de texto    |\n"+
            "| 4. Leer archivo                |\n"+
            "| 5. Salir                       |\n"+
            "----------------------------------\n";

    public static void main(String[] args) throws IOException { //Punto 4:

        try {
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException j){
            System.out.println("No has introducido el producto, prueba de nuevo.");
        }
    }

    private static void menu() throws IOException { //Punto 2:

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
                        System.out.println("Has escogido una opción incorrecta. Prueba de nuevo.");
                        isStoped = true;
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void introducirProds(BufferedReader reader) throws IOException { //Punto 3:

        int codigo;
        String descripcion;
        float peso;

        System.out.println("Introduce el código del producto.");

            try {
               codigo  = Integer.parseInt(reader.readLine());

                if(codigo==0||codigo<0) {
                    System.out.println("El código no puede ser 0 o menor que 0.");
                    return;
                }

            }catch (NumberFormatException e){
                System.out.println("No has introducido el codigo.");
                return;
            }

        System.out.println("Introduce la descripción del prooducto.");

                descripcion = reader.readLine();

                if(descripcion.isEmpty()) descripcion = "Desconocido";

        System.out.println("Introduce el peso del producto.");

        try {
            peso = Float.parseFloat(reader.readLine());

        }catch (NumberFormatException e){

            System.out.println("No has introducido una cantidad de peso.");
            return;
        }

            listaProductos.add(new Producto(codigo, descripcion, peso));
    }
    private static void visualizaProds(){ //Punto 3:

        if(!listaProductos.isEmpty()) {

            System.out.println("Todos los productos introducidos:\n");
            for(Producto x: listaProductos){

                    System.out.println("Código: "+x.getCodigo()+"\nDescripción: "+x.getDecripcion()+"\nPeso: "+x.getPeso()+"kg\n");
            }
        }else{
            System.out.println("No hay productos introducidos.");
        }

    }
    private static void guardaProds(){ //Punto 3:

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("archivador.txt",true))){

            if(!listaProductos.isEmpty()) {
                for (Producto x : listaProductos) {
                    writer.write("Código: " + x.getCodigo() + "\n");
                    writer.write("Descripción: " + x.getDecripcion() + "\n");
                    writer.write("Peso: " + x.getPeso() + "kg\n");
                    writer.write("\n");
                }
                System.out.println("Productos guardados.");

            }else{
                System.out.println("No hay productos introducidos.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void leeProds() throws IOException { //Punto 3:

        try (BufferedReader reader = new BufferedReader(new FileReader("archivador.txt"))){

            System.out.println("Todos los productos del Almacen:");

            while(true){
                try {
                    System.out.println(reader.readLine());
                    if (!reader.ready()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Primero deberias introducir los datos y guardar.");
        }
    }
}

