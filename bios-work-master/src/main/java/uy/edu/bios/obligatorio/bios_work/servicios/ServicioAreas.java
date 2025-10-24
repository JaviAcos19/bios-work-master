package uy.edu.bios.obligatorio.bios_work.servicios;

import uy.edu.bios.obligatorio.bios_work.dominio.Area;
import uy.edu.bios.obligatorio.bios_work.especificaciones.EspecificacionesArea;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionBiosWork;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionNoExiste;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionTieneVinculos;
import uy.edu.bios.obligatorio.bios_work.excepciones.ExcepcionYaExiste;
import uy.edu.bios.obligatorio.bios_work.repositorios.IRepositorioAreas;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class ServicioAreas implements IServicioAreas{

    @Autowired
    private IRepositorioAreas repositorioAreas;

    @Override
    public List<Area> listar() {
        return repositorioAreas.findAll();
    }

    @Override
    public List<Area> buscar(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return repositorioAreas.findAll();
        }
        return repositorioAreas.findAll(EspecificacionesArea.buscar(criterio));
    }

    @Override
    public Area obtener(Long id) {
        return repositorioAreas.findById(id).orElse(null);
    }

    @Override
    public void agregar(Area area) throws ExcepcionBiosWork {
        Area areaExistente = repositorioAreas.findByNombre(area.getNombre());

        if (areaExistente != null) {
            throw new ExcepcionYaExiste("El área ingresada ya existe en el sistema.");
        }

        repositorioAreas.save(area);
    }

    @Override
    public void eliminar(Long id) throws ExcepcionBiosWork {
        Area areaExistente = repositorioAreas.findById(id).orElse(null);

        if (areaExistente == null) {
            throw new ExcepcionNoExiste("El área ingresada no se encuentra registrada en el sistema.");
        }

        try {
            repositorioAreas.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExcepcionTieneVinculos("No se puede eliminar un área que esté asignada a una oferta de trabajo.");
        }
    }

    @Override
    public void modificar(Area area) throws ExcepcionBiosWork {
        Area areaExistente = repositorioAreas.findById(area.getId()).orElse(null);

        if (areaExistente == null) {
            throw new ExcepcionNoExiste("El área que intenta modificar no se encuentra registrada en el sistema.");
        }

        // Verificar duplicado de nombre (si se cambió)
        Area otraAreaConMismoNombre = repositorioAreas.findByNombre(area.getNombre());
        if (otraAreaConMismoNombre != null && !otraAreaConMismoNombre.getId().equals(area.getId())) {
            throw new ExcepcionYaExiste("Ya existe otra área registrada con ese nombre.");
        }

        areaExistente.setNombre(area.getNombre());
        repositorioAreas.save(areaExistente);
    }
    
}