package MODELO;
public class Usuario {
    private String nombre;
    private String apellido;
    private String DNI;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) throws Exception {
        if (DNI.matches("\\d{1,8}")) {
            this.DNI = DNI;
        } else {
            throw new Exception("El DNI debe tener máximo 8 caracteres numéricos.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}