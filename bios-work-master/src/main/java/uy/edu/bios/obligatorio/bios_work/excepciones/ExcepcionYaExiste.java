package uy.edu.bios.obligatorio.bios_work.excepciones;

public class ExcepcionYaExiste extends ExcepcionBiosWork{
     public ExcepcionYaExiste() {

    }

    public ExcepcionYaExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionYaExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
