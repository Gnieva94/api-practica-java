package com.practica.api.principal;

import com.practica.api.model.DatosSerie;
import com.practica.api.model.DatosTemporadas;
import com.practica.api.service.ConsumoAPI;
import com.practica.api.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=30d9d257";
    public void muestraMenu(){
        System.out.println("Escribe nombre de serie: ");
        var nombreSerie = teclado.nextLine().replace(" ","+");
        var json = consumoApi.obtenerDatos(URL_BASE+nombreSerie+API_KEY);
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE+nombreSerie+"&Season="+i+API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json,DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        //temporadas.forEach(System.out::println);
        temporadas.forEach(t -> t.listaEpisodios().forEach(e -> System.out.println(e.titulo())));

    }
}
