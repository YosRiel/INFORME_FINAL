package VISTA;

import MODELO.CarritoCompra;
import MODELO.Item;
import MODELO.Usuario;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Vista {
    private Scanner scanner = new Scanner(System.in);

    public String[] obtenerNombreUsuario() {
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su apellido:");
        String apellido = scanner.nextLine();

        String dni = "";
        boolean dniValido = false;
        while (!dniValido) {
            try {
                System.out.println("Ingrese su DNI (exactamente 8 dígitos numéricos):");
                dni = scanner.nextLine();
                if (!dni.matches("\\d{8}")) {
                    throw new Exception("El DNI debe tener exactamente 8 caracteres numéricos.");
                }
                dniValido = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return new String[]{nombre, apellido, dni};
    }

    public int mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Pedido");
        System.out.println("2. Carrito");
        System.out.println("3. Boleta");
        System.out.println("0. Salir");
        return scanner.nextInt();
    }

    public void mostrarItems(List<Item> items) {
        System.out.println("Seleccione el ítem a agregar al carrito:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getNombre() + " - Precio: S/ " + items.get(i).getPrecio());
        }
        System.out.println("Ingrese el número del ítem (0 para terminar):");
    }

    public int solicitarOpcion() {
        System.out.println("Ingrese el número del ítem (0 para terminar):");
        return scanner.nextInt();
    }

    public int solicitarCantidad() {
        System.out.println("Ingrese la cantidad:");
        return scanner.nextInt();
    }

    public void opcionInvalida() {
        System.out.println("Opción inválida. Intente de nuevo.");
    }

    public void mostrarCarrito(CarritoCompra carrito) {
        List<Item> items = carrito.getItems();
        List<Integer> cantidades = carrito.getCantidades();

        System.out.println("Carrito de Compras:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int cantidad = cantidades.get(i);
            double subtotal = item.getPrecio() * cantidad;

            System.out.println(item.getNombre() + " - Precio: S/ " + item.getPrecio() +
                    " - Cantidad: " + cantidad + " - Subtotal: s/ " + subtotal);
        }
    }

    public void mostrarBoleta(Usuario usuario, CarritoCompra carrito) {
        List<Item> items = carrito.getItems();
        List<Integer> cantidades = carrito.getCantidades();
        double subtotal = carrito.calcularSubtotal();
        Date fechaActual = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formatter.format(fechaActual);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("boleta.txt"))) {
            writer.write("======================================\n");
            writer.write("Empresa: FedEx\n");
            writer.write("Usuario: " + usuario.getNombre() + " " + usuario.getApellido() + "\n");
            writer.write("DNI: " + usuario.getDNI() + "\n");
            writer.write("Fecha: " + fechaFormateada + "\n");
            writer.write("======================================\n");
            writer.write("Resumen de Carrito:\n");

            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                int cantidad = cantidades.get(i);
                double subtotalItem = item.getPrecio() * cantidad;

                writer.write(item.getNombre() + " - Precio: S/ " + item.getPrecio() +
                        " - Cantidad: " + cantidad + " - Subtotal: S/ " + subtotalItem + "\n");
            }
            double igv = subtotal * 0.18;
            double totalConIGV = subtotal + igv;
            writer.write("======================================\n");
            writer.write("Subtotal: S/ " + subtotal + "\n");
            writer.write("IGV 18%: S/ " + igv + "\n");
            writer.write("Total: S/ " + totalConIGV + "\n");
            writer.write("======================================\n");
            writer.write("Gracias por su compra.\n");

            System.out.println("La boleta se ha guardado en el archivo 'boleta.txt'.");
        } catch (IOException e) {
            System.out.println("Error al escribir la boleta en el archivo.");
            e.printStackTrace();
        }
        leerBoleta();// Leer la boleta después de mostrarla
    }

    private void leerBoleta() {
        try (BufferedReader reader = new BufferedReader(new FileReader("boleta.txt"))) {
            String line;
            System.out.println("Contenido de la boleta:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer la boleta del archivo.");
            e.printStackTrace();
        }
    }
}
