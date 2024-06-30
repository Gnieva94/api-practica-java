package com.practica.api.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
