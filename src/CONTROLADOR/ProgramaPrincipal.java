package CONTROLADOR;

import MODELO.CarritoCompra;
import MODELO.Item;
import MODELO.Usuario;
import VISTA.Vista;

import java.util.ArrayList;
import java.util.List;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Usuario usuario = new Usuario("");
        CarritoCompra carrito = new CarritoCompra();

        List<Item> items = new ArrayList<>();
        items.add(new Item("Libro", 10.0));
        items.add(new Item("Mochila", 15.0));
        items.add(new Item("Cartuchera", 5.0));
        items.add(new Item("Plumones", 10.0));
        items.add(new Item("Colores", 15.0));
        items.add(new Item("Lapiz", 3.0));
        items.add(new Item("Papelotes", 15.0));
        items.add(new Item("Tajador", 2.0));
        items.add(new Item("Borrador", 5.0));
        items.add(new Item("Plumon para pizzara", 15.0));
        items.add(new Item("Goma Grande", 12.0));
        Controlador controlador = new Controlador(vista, usuario, carrito, items);

        controlador.iniciar();
    }
}
