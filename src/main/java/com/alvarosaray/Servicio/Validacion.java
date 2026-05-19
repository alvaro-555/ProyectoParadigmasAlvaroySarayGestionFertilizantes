package com.alvarosaray.Servicio;

import java.util.ArrayList;
import com.alvarosaray.Model.EmpresaAgricola;

/**
 * Interfaz publica que define las operaciones de validacion de datos,
 * generacion de identificadores e inicio de sesion del sistema.
 *
 * <p>Tipo: Interfaz publica ({@code public interface}).</p>
 * <p>Implementada por: {@link ImplementacionOperacion}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
public interface Validacion {

    /**
     * Verifica que un texto no sea nulo ni este vacio.
     *
     * @param valor Texto a validar.
     * @return {@code true} si el texto tiene contenido valido, {@code false} en caso contrario.
     */
    boolean validarTexto(String valor);

    /**
     * Verifica que un valor sea un numero entero valido.
     *
     * @param valor Cadena de texto que representa el numero a validar.
     * @return {@code true} si el valor es un entero valido, {@code false} en caso contrario.
     */
    boolean validarNumeroEntero(String valor);

    /**
     * Verifica que un correo electronico tenga un formato basico valido,
     * es decir, que contenga los caracteres '@' y '.'.
     *
     * @param valor Correo electronico a validar.
     * @return {@code true} si el correo tiene formato valido, {@code false} en caso contrario.
     */
    boolean validarCorreo(String valor);

    /**
     * Verifica que una contrasena tenga al menos 6 caracteres.
     *
     * @param valor Contrasena a validar.
     * @return {@code true} si la contrasena cumple el minimo requerido, {@code false} en caso contrario.
     */
    boolean validarContrasena(String valor);

    /**
     * Verifica que el año de fundacion sea un numero entero entre 1900 y 2025.
     *
     * @param valor Cadena de texto que representa el año a validar.
     * @return {@code true} si el año esta dentro del rango permitido, {@code false} en caso contrario.
     */
    boolean validarAniofundacion(String valor);

    /**
     * Verifica que una cantidad sea un numero entero mayor a cero.
     *
     * @param valor Cadena de texto que representa la cantidad a validar.
     * @return {@code true} si la cantidad es valida, {@code false} en caso contrario.
     */
    boolean validarCantidad(String valor);

    /**
     * Verifica que una fecha tenga el formato DD/MM/AAAA.
     *
     * @param valor Cadena de texto que representa la fecha a validar.
     * @return {@code true} si la fecha tiene el formato correcto, {@code false} en caso contrario.
     */
    boolean validarFecha(String valor);

    /**
     * Genera un identificador unico para un nuevo usuario basado en el
     * tamaño actual de la lista de empresas.
     *
     * @param arreglo Lista actual de empresas agricolas registradas.
     * @return Identificador generado con el formato "U{numero}" (ej. "U1", "U2").
     */
    String generarIdUsuario(ArrayList<EmpresaAgricola> arreglo);

    /**
     * Genera un identificador unico para una nueva empresa agricola basado en el
     * tamaño actual de la lista de empresas.
     *
     * @param arreglo Lista actual de empresas agricolas registradas.
     * @return Identificador generado con el formato "EA{numero}" (ej. "EA1", "EA2").
     */
    String generarIdEmpresa(ArrayList<EmpresaAgricola> arreglo);

    /**
     * Genera un identificador unico para un nuevo fertilizante basado en el
     * numero de fertilizantes que ya tiene registrados la empresa.
     *
     * @param a Empresa agricola a la que pertenecera el nuevo fertilizante.
     * @return Identificador generado con el formato "F{numero}" (ej. "F1", "F2").
     */
    String generarIdFertilizante(EmpresaAgricola a);

    /**
     * Verifica las credenciales de un usuario buscando en la lista de empresas
     * una que coincida con el correo y la contrasena proporcionados.
     *
     * @param arreglo    Lista de empresas agricolas registradas en el sistema.
     * @param correo     Correo electronico ingresado por el usuario.
     * @param contrasena Contrasena ingresada por el usuario.
     * @return El objeto {@link EmpresaAgricola} cuyo usuario coincide con las credenciales,
     *         o {@code null} si no se encontro coincidencia.
     */
    EmpresaAgricola iniciarSesion(ArrayList<EmpresaAgricola> arreglo, String correo, String contrasena);
}
