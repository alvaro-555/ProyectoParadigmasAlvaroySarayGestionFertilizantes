package com.alvarosaray.Servicio;

import java.util.ArrayList;
import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Model.Fertilizante;

/**
 * Interfaz publica que define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre los fertilizantes de una empresa agricola.
 *
 * <p>Tipo: Interfaz publica ({@code public interface}).</p>
 * <p>Implementada por: {@link ImplementacionOperacion}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
public interface OperacionCrud {

    /**
     * Agrega un nuevo fertilizante a la lista de fertilizantes de una empresa agricola.
     *
     * @param a Empresa agricola a la que se le agregara el fertilizante.
     * @param f Objeto {@link Fertilizante} que se desea agregar.
     * @return Mensaje indicando si el fertilizante fue agregado correctamente o si ocurrio un error.
     */
    String crear(EmpresaAgricola a, Fertilizante f);

    /**
     * Retorna la lista completa de fertilizantes registrados en una empresa agricola.
     *
     * @param a Empresa agricola de la que se desea obtener los fertilizantes.
     * @return Lista de objetos {@link Fertilizante} pertenecientes a la empresa.
     */
    ArrayList<Fertilizante> listarTodos(EmpresaAgricola a);

    /**
     * Busca y retorna un fertilizante especifico por su ID dentro de una empresa agricola.
     *
     * @param a  Empresa agricola en la que se realizara la busqueda.
     * @param id ID del fertilizante que se desea encontrar (ej. "F1", "F2").
     * @return El objeto {@link Fertilizante} encontrado, o {@code null} si no existe.
     */
    Fertilizante listarUno(EmpresaAgricola a, String id);

    /**
     * Modifica los datos de un fertilizante existente identificado por su ID.
     *
     * @param a  Empresa agricola que contiene el fertilizante a modificar.
     * @param id ID del fertilizante que se desea modificar.
     * @param f  Objeto {@link Fertilizante} con los nuevos datos a aplicar.
     * @return Mensaje indicando si la modificacion fue exitosa o si no se encontro el fertilizante.
     */
    String modificar(EmpresaAgricola a, String id, Fertilizante f);

    /**
     * Elimina un fertilizante de la lista de una empresa agricola segun su ID.
     *
     * @param a  Empresa agricola que contiene el fertilizante a eliminar.
     * @param id ID del fertilizante que se desea eliminar.
     * @return Mensaje indicando si la eliminacion fue exitosa o si no se encontro el fertilizante.
     */
    String eliminar(EmpresaAgricola a, String id);
}
