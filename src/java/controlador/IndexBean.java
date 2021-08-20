package controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import sesiones.Sesion;

@ManagedBean(name = "indexBean")
public class IndexBean {

    private List<String> images;    //declaramos las listas

    private String nombre;

    @PostConstruct
    public void init() {
        nombre = Sesion.sesionNombre();

        //------------esto es para la galeria-------------------------------
        images = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            images.add("nature" + i + ".jpg");
        }
        //------------------------------------------------------------------     

    }

//<editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public List<String> getImages() {
        return images;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//</editor-fold>

}
