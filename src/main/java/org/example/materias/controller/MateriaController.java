package org.example.materias.controller;

import jakarta.validation.Valid;
import org.example.materias.dto.MateriaForm;
import org.example.materias.model.Materia;
import org.example.materias.service.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {
    private final MateriaService service;
    public MateriaController(MateriaService service) {
        this.service = service;
    }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materias", service.listarActivas());
        return "materias/list";
    }
    @GetMapping("/nueva")
    public String formNueva(Model model) {
        model.addAttribute("materiaForm", new MateriaForm());
        return "materias/form";
    }
    @PostMapping
    public String crear(@Valid @ModelAttribute MateriaForm materiaForm,
                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "materias/form";
        }
        try {
            service.crear(materiaForm);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorCodigo", e.getMessage());
            return "materias/form";
        }
        return "redirect:/materias";
    }
    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        Materia m = service.obtenerPorId(id);
        MateriaForm form = new MateriaForm();
        form.setCodigo(m.getCodigo());
        form.setNombre(m.getNombre());
        form.setCreditos(m.getCreditos());
        form.setSemestre(m.getSemestre());
        model.addAttribute("materiaForm", form);
        model.addAttribute("id", id);
        return "materias/form";
    }
    @PostMapping("/{id}")
    public String editar(@PathVariable Long id,
                         @Valid @ModelAttribute MateriaForm materiaForm,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("id", id);
            return "materias/form";
        }
        try {
            service.editar(id, materiaForm);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorCodigo", e.getMessage());
            model.addAttribute("id", id);
            return "materias/form";
        }
        return "redirect:/materias";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminarLogico(id);
        return "redirect:/materias";
    }
}