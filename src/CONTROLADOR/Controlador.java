package CONTROLADOR;
import MODELO.CarritoCompra;
import MODELO.Item;
import MODELO.Usuario;
import VISTA.Vista;
import java.util.List;
public class Controlador {
    private Vista vista;
    private Usuario usuario;
    private CarritoCompra carrito;
    private List<Item> items; // Agrega la lista de ítems aquí

    public Controlador(Vista vista, Usuario usuario, CarritoCompra carrito, List<Item> items) {
        this.vista = vista;
        this.usuario = usuario;
        this.carrito = carrito;
        this.items = items;
    }

    public void iniciar() {
        String[] datosUsuario = vista.obtenerNombreUsuario();
        usuario = new Usuario(datosUsuario[0]);
        usuario.setApellido(datosUsuario[1]);
        try {
            usuario.setDNI(datosUsuario[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return; // Terminar la ejecución si el DNI no es válido
        }

        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    manejarPedido(items); // Pasar la lista de ítems al manejarPedido()
                    break;
                case 2:
                    vista.mostrarCarrito(carrito);
                    break;
                case 3:
                    vista.mostrarBoleta(usuario, carrito);
                    return; // Terminar la ejecución después de mostrar la boleta
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private void manejarPedido(List<Item> items) {
        vista.mostrarItems(items);
        int opcion;
        do {
            opcion = vista.solicitarOpcion();
            if (opcion > 0 && opcion <= items.size()) {
                int cantidad = vista.solicitarCantidad();
                carrito.agregarItem(items.get(opcion - 1), cantidad);
            } else if (opcion != 0) {
                vista.opcionInvalida();
            }
        } while (opcion != 0);
    }
}
