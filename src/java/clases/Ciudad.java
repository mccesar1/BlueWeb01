
package clases;

//en esta clase definimos el objeto ciudad 
public class Ciudad {
    
    private int id;
    private  String descripcion;
    private String codigo;
    private int lada;
 
    public Ciudad() {
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getLada() {
        return lada;
    }

    public void setLada(int lada) {
        this.lada = lada;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

 

}
