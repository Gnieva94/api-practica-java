package com.practica.api.controller;

import com.practica.api.dto.EpisodioDTO;
import com.practica.api.dto.SerieDTO;
import com.practica.api.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> getSeries(){
        return service.getSeries();
    }
    @GetMapping("/top5")
    public List<SerieDTO> getTop5(){
        return service.getTop5();
    }
    @GetMapping("/lanzamientos")
    public List<SerieDTO> getLanzamientosMasRecientes(){
        return service.getLanzamientosMasRecientes();
    }
    @GetMapping("/{id}")
    public SerieDTO getSerieById(@PathVariable Long id){
        return service.getSerieById(id);
    }
    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> getAllTemporadas(@PathVariable Long id){
        return service.getAllTemporadas(id);
    }
    @GetMapping("/{id}/temporadas/{numTemporada}")
    public List<EpisodioDTO> getTemporadasbyNumero(@PathVariable Long id,@PathVariable Long numTemporada){
        return service.getTemporadasbyNumero(id,numTemporada);
    }
    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> getSeriesByCategory(@PathVariable String nombreGenero){
        return service.getSeriesByCategory(nombreGenero);
    }

}
