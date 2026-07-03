package org.example.materias.service;
import org.example.materias.dto.MateriaForm;
import org.example.materias.model.Materia;
import org.example.materias.repository.MateriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MateriaService {
    private final MateriaRepository repo;
    public MateriaService(MateriaRepository repo) {
        this.repo = repo;
    }
    public List<Materia> listarActivas() {
        return repo.findAll();
    }
    public Materia obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Materia no encontrada"));
    }
    @Transactional
    public Materia crear(MateriaForm form) {
        if (repo.existsByCodigo(form.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una materia con ese código");
        }
        Materia m = new Materia();
        mapearDatos(m, form);
        return repo.save(m);
    }
    @Transactional
    public Materia editar(Long id, MateriaForm form) {
        if (repo.existsByCodigoAndIdNot(form.getCodigo(), id)) {
            throw new IllegalArgumentException("Ese código ya lo usa otra materia");
        }
        Materia m = obtenerPorId(id);
        mapearDatos(m, form);
        return repo.save(m);
    }
    @Transactional
    public void eliminarLogico(Long id) {
        Materia m = obtenerPorId(id);
        m.setActiva(false);
        repo.save(m);
    }
    private void mapearDatos(Materia m, MateriaForm form) {
        m.setCodigo(form.getCodigo());
        m.setNombre(form.getNombre());
        m.setCreditos(form.getCreditos());
        m.setSemestre(form.getSemestre());
    }
}