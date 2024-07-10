package com.practica.api.principal;

import com.practica.api.model.*;
import com.practica.api.repository.SerieRepository;
import com.practica.api.service.ConsumoAPI;
import com.practica.api.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=30d9d257";
    private List<DatosSerie> datosSeries = new ArrayList<>();
    private SerieRepository repository;

    public Principal(SerieRepository repository){
        this.repository = repository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1 - Buscar series
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }

//        System.out.println("Escribe nombre de serie: ");
//        var nombreSerie = teclado.nextLine().replace(" ","+");
//        var json = consumoApi.obtenerDatos(URL_BASE+nombreSerie+API_KEY);
//        //System.out.println(json);
//        var datos = conversor.obtenerDatos(json, DatosSerie.class);
//        System.out.println(datos);
//        List<DatosTemporadas> temporadas = new ArrayList<>();
//        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
//            json = consumoApi.obtenerDatos(URL_BASE+nombreSerie+"&Season="+i+API_KEY);
//            var datosTemporadas = conversor.obtenerDatos(json,DatosTemporadas.class);
//            temporadas.add(datosTemporadas);
//        }
        //temporadas.forEach(System.out::println);
        //temporadas.forEach(t -> t.listaEpisodios().forEach(e -> System.out.println(e.titulo())));

        /*<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.listaEpisodios().stream())
                .collect(Collectors.toList());
         */
        /*System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                .limit(5)
                .forEach(System.out::println);*/

//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.listaEpisodios().stream()
//                        .map(d -> new Episodio(t.numero(),d)))
//                .collect(Collectors.toList());
        //episodios.forEach(System.out::println);

        /*System.out.println("Año de busqueda: ");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        LocalDate fechaBusqueda = LocalDate.of(fecha,1,1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaLanzamiento() != null && e.getFechaLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada " + e.getTitulo()+"Episodio "+e.getTitulo()+"Fecha de Lanzamiento "+e.getFechaLanzamiento().format(dtf)
                ));
        */
        /*
        System.out.println("Escriba el titulo del episodio que desea ver: ");
        var pedazoTitulo = teclado.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado...");
            System.out.println("Los datos son: "+episodioBuscado.get());
        }else{
            System.out.println("Episode not found");
        }
         */
        /*
        Map<Integer,Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e->e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evaluacionesPorTemporada);
         */
        /*
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println(est);
        System.out.println("Media de evaluaciones: "+est.getAverage());
        */
        //Stream.iterate(0,n->n+1).limit(10).forEach(System.out::println);
        /*
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        Optional<Integer> resultado = numeros.stream().reduce(Integer::sum);
        resultado.ifPresent(System.out::println); // imprime 15
         */

    }

    private DatosSerie getDatosSerie(){
        System.out.println("Escribe el nombre de la serie que deseas buscar: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+nombreSerie.replace(" ","+")+API_KEY);
        System.out.println(json);
        DatosSerie datos = conversor.obtenerDatos(json,DatosSerie.class);
        return datos;
    }
    private void buscarEpisodioPorSerie(){
        DatosSerie datosSerie = getDatosSerie();
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for(int i=1;i<=datosSerie.totalDeTemporadas();i++){
            var json = consumoApi.obtenerDatos(URL_BASE+datosSerie.titulo().replace(" ","+"));
            DatosTemporadas datosTemporada = conversor.obtenerDatos(json,DatosTemporadas.class);
            temporadas.add(datosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
    private void buscarSerieWeb(){
        DatosSerie datos = getDatosSerie();
        //datosSeries.add(datos);
        Serie serie = new Serie(datos);
        repository.save(serie);
        System.out.println(datos);
    }
    private void mostrarSeriesBuscadas() {
        List<Serie> series = new ArrayList<>();
        series = datosSeries.stream()
                .map(d ->new Serie(d))
                .collect(Collectors.toList());

        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}
