package uy.edu.bios.obligatorio.bios_work.utilidades;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class UtilidadesArchivos {

    public static void guardarImagen(byte[] datos, String directorio, String nombreArchivo, String formato) throws IOException {
        
        BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(datos));

        File archivo = new File(directorio, nombreArchivo + "." + formato.toLowerCase());
        archivo.createNewFile();

        ImageIO.write(imagen, formato.toLowerCase(), archivo);

    }

    public static void guardarPdf(byte[] datos, String directorio, String nombreArchivo) throws IOException {
        
        Path ruta = Paths.get(directorio, nombreArchivo + ".pdf");
        
        String rutaArchivo = ruta.toString();

        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
        
            fos.write(datos);

        }
    }

    public static void eliminarArchivo(String rutaArchivo) {
        
        File archivo = new File(rutaArchivo);

        archivo.delete();

    }

}