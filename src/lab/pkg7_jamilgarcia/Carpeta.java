package lab.pkg7_jamilgarcia;

import java.util.ArrayList;

public class Carpeta {
    
    private String nombre; 
    private String link; 
    private ArrayList lista = new ArrayList();

    public Carpeta(String nombre, String link) {
        this.nombre = nombre;
        this.link = link;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
