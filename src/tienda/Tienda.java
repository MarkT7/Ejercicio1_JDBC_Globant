package tienda;
import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import tienda.entidades.Producto;
import tienda.servicios.FabricanteService;
import tienda.servicios.ProductoService;

public class Tienda {

    public static void main(String[] args) {
        
        //Hecho por Marcos Tejerina
        Scanner lector = new Scanner(System.in);
        FabricanteService fabricanteServicio = new FabricanteService();
        ProductoService productoServicio = new ProductoService();
        
        int choice=0;
        int codigo;
        String nombre;
        double precio;
        int codigoFabricante;
    
        do{
            System.out.println("-----  MENU  -----");
            System.out.println("Ingrese el numero de la opcion deseada");
            System.out.println("");
            System.out.println("1) Lista el nombre de todos los productos que hay en la tabla producto");
            System.out.println("2) Lista los nombres y los precios de todos los productos de la tabla producto");
            System.out.println("3) Listar aquellos productos que su precio esté entre 120 y 202");
            System.out.println("4) Buscar y listar todos los Portátiles de la tabla producto");
            System.out.println("5) Listar el nombre y el precio del producto más barato");
            System.out.println("6) Ingresar un producto a la base de datos");
            System.out.println("7) Ingresar un fabricante a la base de datos");
            System.out.println("8) Editar un producto con datos a elección");
            System.out.println("9) Terminar y salir");
            
            choice=lector.nextInt();
            lector.nextLine();
            
            try {
                switch(choice){
                case 1:
                    Collection<Producto> productos = productoServicio.listarProductos();
                    System.out.println("");
                    System.out.println("Listando todos los nombres de productos");
                    for(Producto a : productos){
                        System.out.println(a.getNombre());
                    }
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 2:
                    Collection<Producto> productos2 = productoServicio.listarProductos();
                    System.out.println("");
                    System.out.println("Listando todos los nombres y precios de productos");
                    for(Producto a : productos2){
                        System.out.println(a.getNombre()+ "     $"+a.getPrecio());
                    }
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 3:
                    Collection<Producto> productos3 = productoServicio.listarProductos();
                    System.out.println("");
                    System.out.println("Listando los nombres de productos cuyo precio esta entre 120 y 202");
                    for(Producto a : productos3){
                        if(a.getPrecio()>119 && a.getPrecio()<203){
                            System.out.println(a.getNombre());
                        }
                    }
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 4:
                    Collection<Producto> portatiles = productoServicio.listarPortatiles();
                    System.out.println("");
                    System.out.println("Listando nombres de portátiles");
                    for(Producto a: portatiles){
                        System.out.println(a.getNombre());
                    }
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 5:
                    Producto barato = productoServicio.listarBarato();
                    System.out.println("");
                    System.out.println("Listando nombre y precio del producto mas barato");
                    System.out.println(barato.getNombre()+ "    "+barato.getPrecio());
                    System.out.println("");
                    TimeUnit.SECONDS.sleep(4);
                    break;
                case 6:
                    System.out.println("Ingresando un producto");
                    System.out.println("Ingrese el numero de codigo");
                    codigo = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Ingrese el nombre");
                    nombre = lector.nextLine();
                    System.out.println("Ingrese el precio");
                    precio = lector.nextDouble();
                    System.out.println("Ingrese el numero de codigo de fabricante");
                    codigoFabricante = lector.nextInt();
                    lector.nextLine();
                    
                    productoServicio.crearProducto(codigo, nombre, precio, codigoFabricante);
                    break;
                case 7:
                    System.out.println("Ingresando un fabricante");
                    System.out.println("Ingrese el numero de codigo");
                    codigo = lector.nextInt();
                    lector.nextLine();
                    
                    System.out.println("Ingrese el nombre");
                    nombre = lector.nextLine();
                    
                    fabricanteServicio.crearFabricante(codigo, nombre);
                    break;
                case 8:
                    System.out.println("Editando un producto");
                    System.out.println("Ingrese el numero de codigo");
                    codigo = lector.nextInt();
                    lector.nextLine();
                    System.out.println("Ingrese el nuevo nombre");
                    nombre = lector.nextLine();
                    System.out.println("Ingrese el nuevo precio");
                    precio = lector.nextDouble();
                    System.out.println("Ingrese el nuevo numero de codigo de fabricante");
                    codigoFabricante = lector.nextInt();
                    lector.nextLine();
                    
                    productoServicio.editarProducto(codigo, nombre, precio, codigoFabricante);
                    break;
                } 
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }while(choice!=9);
            System.out.println("Terminando programa");
        }
    
}
