package domain;

public class SailorMoonDTO {
    private String nombre;
    private String apellido;
    private String planeta;
    
    public SailorMoonDTO(){}

    public SailorMoonDTO(String nombre, String apellido, String planeta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.planeta = planeta;
    }

    public SailorMoonDTO(String planeta) {
        this.planeta = planeta;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPlaneta() {
        return planeta;
    }

    public void setPlaneta(String planeta) {
        this.planeta = planeta;
    }

    // agregar iterator para que los elementos de la lista no se vean con corchetes

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre);
        sb.append("\n Apellido: ").append(apellido);
        sb.append("\n Planeta: ").append(planeta);
        return sb.toString();
    }
    
    
    
}
