package uy.edu.bios.obligatorio.bios_work.excepciones;

public class ExcepcionNoExiste extends ExcepcionBiosWork{
    public ExcepcionNoExiste() {

    }

    public ExcepcionNoExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionNoExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
