package lab.pkg7_jamilgarcia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class AdminPapelera {
    private static final long SerialVersionUID = 4404L;
    private ArrayList listaPapelera = new ArrayList();
    private File archivo = null;
    
    public AdminPapelera(String path) {
        archivo = new File(path);
    }

    public ArrayList getListaMiUnidad() {
        return listaPapelera;
    }

    public void setListaMiUnidad(ArrayList listaPapelera) {
        this.listaPapelera = listaPapelera;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "listaPersonas=" + listaPapelera;
    }

    //extra mutador
    public void setCarpeta(Carpeta p) {
        this.listaPapelera.add(p);
    }

    public void cargarArchivo() {
        try {            
            listaPapelera = new ArrayList();
            Carpeta temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Carpeta) objeto.readObject()) != null) {
                        listaPapelera.add(temp);
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
            for (Iterator it = listaPapelera.iterator(); it.hasNext();) {
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
