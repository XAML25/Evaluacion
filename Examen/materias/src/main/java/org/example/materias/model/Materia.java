package org.example.materias.model;

import jakarta.persistence.*;

@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 6)
    private String codigo;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column(nullable = false)
    private Short creditos;
    @Column(nullable = false)
    private Short semestre;
    @Column(nullable = false)
    private Boolean activa = true;
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Short getCreditos() { return creditos; }
    public void setCreditos(Short creditos) { this.creditos = creditos; }
    public Short getSemestre() { return semestre; }
    public void setSemestre(Short semestre) { this.semestre = semestre; }
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
