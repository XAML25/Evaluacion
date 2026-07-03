package org.example.materias.dto;
import jakarta.validation.constraints.*;
public class MateriaForm {
    @NotBlank(message = "El código es obligatorio")
    @Size(min = 6, max = 6, message = "El código debe tener exactamente 6 caracteres")
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    private String nombre;
    @NotNull(message = "Los créditos son obligatorios")
    @Min(value = 1, message = "Los créditos deben estar entre 1 y 6")
    @Max(value = 6, message = "Los créditos deben estar entre 1 y 6")
    private Short creditos;
    @NotNull(message = "El semestre es obligatorio")
    @Min(value = 1, message = "El semestre debe estar entre 1 y 10")
    @Max(value = 10, message = "El semestre debe estar entre 1 y 10")
    private Short semestre;
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Short getCreditos() { return creditos; }
    public void setCreditos(Short creditos) { this.creditos = creditos; }
    public Short getSemestre() { return semestre; }
    public void setSemestre(Short semestre) { this.semestre = semestre; }
}