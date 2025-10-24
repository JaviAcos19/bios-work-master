package uy.edu.bios.obligatorio.bios_work.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExcepcionTieneVinculos extends ExcepcionBiosWork{
    public ExcepcionTieneVinculos() {

    }

    public ExcepcionTieneVinculos(String mensaje) {
        super(mensaje);
    }

    public ExcepcionTieneVinculos(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}
