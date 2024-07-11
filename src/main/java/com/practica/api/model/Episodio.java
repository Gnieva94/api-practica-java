package com.practica.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name="episodios")
public class Episodio {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaLanzamiento;
    @ManyToOne
    private Serie serie;
    public Episodio(){}
    public Episodio(Integer numero,DatosEpisodio datosEpisodio){
        this.temporada = numero;
        this.titulo = datosEpisodio.titulo();
        this.numeroEpisodio = datosEpisodio.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(datosEpisodio.evaluacion());
            this.fechaLanzamiento = LocalDate.parse(datosEpisodio.fechaLanzamiento());
        }catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }catch (DateTimeParseException e){
            this.fechaLanzamiento = null;
        }

    }

    public Long getId() {return Id;}

    public void setId(Long id) {Id = id;}

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Serie getSerie() {return serie;}

    public void setSerie(Serie serie) {this.serie = serie;}

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", evaluacion=" + evaluacion +
                ", fechaLanzamiento=" + fechaLanzamiento;
    }
}
