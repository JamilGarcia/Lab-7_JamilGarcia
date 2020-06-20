package lab.pkg7_jamilgarcia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class AdminMiUnidad {
    
    private static final long SerialVersionUID = 7770L;
    private ArrayList listaMiUnidad = new ArrayList();
    private File archivo = null;

    public AdminMiUnidad(String path) {
        archivo = new File(path);
    }

    public ArrayList getListaMiUnidad() {
        return listaMiUnidad;
    }

    public void setListaMiUnidad(ArrayList listaMiUnidad) {
        this.listaMiUnidad = listaMiUnidad;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "listaPersonas=" + listaMiUnidad;
    }

    //extra mutador
    public void setCarpeta(Carpeta p) {
        this.listaMiUnidad.add(p);
    }

    public void cargarArchivo() {
        try {            
            listaMiUnidad = new ArrayList();
            Carpeta temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Carpeta) objeto.readObject()) != null) {
                        listaMiUnidad.add(temp);
                    }
                } catch (EOFException e) {
                    //encontro el final del archivo
                }
                objeto.close();
                entrada.close();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void escribirArchivo() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Iterator it = listaMiUnidad.iterator(); it.hasNext();) {
                Carpeta t = (Carpeta) it.next();
                bw.writeObject(t);
            }
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }

}
