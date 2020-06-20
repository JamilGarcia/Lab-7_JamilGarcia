package lab.pkg7_jamilgarcia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class AdminDestacados {
    
    private static final long SerialVersionUID = 4444L;
    private ArrayList listaDestacado = new ArrayList();
    private File archivo = null;

    public AdminDestacados(String path) {
        archivo = new File(path);
    }
    
    public ArrayList getListaMiUnidad() {
        return listaDestacado;
    }

    public void setListaMiUnidad(ArrayList listaDestacado) {
        this.listaDestacado = listaDestacado;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "listaPersonas=" + listaDestacado;
    }

    //extra mutador
    public void setCarpeta(Carpeta p) {
        this.listaDestacado.add(p);
    }

    public void cargarArchivo() {
        try {            
            listaDestacado = new ArrayList();
            Carpeta temp;
            if (archivo.exists()) {
                FileInputStream entrada
                    = new FileInputStream(archivo);
                ObjectInputStream objeto
                    = new ObjectInputStream(entrada);
                try {
                    while ((temp = (Carpeta) objeto.readObject()) != null) {
                        listaDestacado.add(temp);
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
            for (Iterator it = listaDestacado.iterator(); it.hasNext();) {
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
