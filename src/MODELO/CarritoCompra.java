package MODELO;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompra {
    private List<Item> items = new ArrayList<>();
    private List<Integer> cantidades = new ArrayList<>();

    public void agregarItem(Item item, int cantidad) {
        items.add(item);
        cantidades.add(cantidad);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int cantidad = cantidades.get(i);
            subtotal += item.getPrecio() * cantidad;
        }
        return subtotal;
    }
}

