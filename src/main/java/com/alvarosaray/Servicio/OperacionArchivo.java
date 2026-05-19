package com.alvarosaray.Servicio;

import java.util.ArrayList;
import com.alvarosaray.Model.EmpresaAgricola;

/**
 * Interfaz publica que define las operaciones de archivo del sistema.
 * Establece el contrato para la serializacion y deserializacion de datos
 * de empresas agricolas en archivos binarios.
 *
 * <p>Tipo: Interfaz publica ({@code public interface}).</p>
 * <p>Implementada por: {@link ImplementacionOperacion}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
public interface OperacionArchivo {

    /**
     * Serializa (guarda) la lista de empresas agricolas en un archivo binario.
     *
     * @param empresaAgricola Lista de objetos {@link EmpresaAgricola} a guardar.
     * @param path            Ruta de la carpeta donde se guardara el archivo.
     * @param name            Nombre del archivo de destino (ej. "Informacion.dat").
     * @return Mensaje indicando si la operacion fue exitosa o si ocurrio un error.
     */
    String Serializar(ArrayList<EmpresaAgricola> empresaAgricola, String path, String name);

    /**
     * Deserializa (carga) la lista de empresas agricolas desde un archivo binario.
     * Si el archivo no existe o ocurre un error, retorna una lista vacia.
     *
     * @param path Ruta de la carpeta donde se encuentra el archivo.
     * @param name Nombre del archivo a leer (ej. "Informacion.dat").
     * @return Lista de objetos {@link EmpresaAgricola} cargados desde el archivo,
     *         o una lista vacia si no se pudo leer.
     */
    ArrayList<EmpresaAgricola> deserializacion(String path, String name);
}
