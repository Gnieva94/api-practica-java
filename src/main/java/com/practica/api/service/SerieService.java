package com.practica.api.service;

import com.practica.api.dto.SerieDTO;
import com.practica.api.model.Serie;
import com.practica.api.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;
    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream()
                .map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),
                        s.getEvaluacion(),s.getPoster(),s.getGenero(),s.getActores(),s.getSinopsis()))
                .collect(Collectors.toList());
    }
    public List<SerieDTO> getSeries(){
        return convierteDatos(repository.findAll());
    }
    public List<SerieDTO> getTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }
    public List<SerieDTO> getLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzamientosMasRecientes());
    }
    public SerieDTO getSerieById(Long id) {
        Optional<Serie> serie =  repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),
                    s.getEvaluacion(),s.getPoster(),s.getGenero(),s.getActores(),s.getSinopsis());
        }else{
            return null;
        }
    }
}
