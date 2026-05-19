package com.alvarosaray.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa una empresa agrícola dentro del sistema de gestión.
 *
 * <p>Esta clase modela la entidad principal del dominio: una empresa agrícola,
 * la cual tiene asociada información de identificación, ubicación, año de fundación,
 * un {@link Usuario} responsable y una lista de {@link Fertilizante}s registrados.</p>
 *
 * <p>Implementa {@link java.io.Serializable} para permitir la serialización del objeto,
 * lo que facilita su almacenamiento persistente y transmisión entre capas del sistema.</p>
 *
 * <p><b>Visibilidad:</b> Clase pública concreta (no abstracta, no interfaz).</p>
 * <p><b>Herencia:</b> No hereda de ninguna clase de dominio (hereda implícitamente de {@link Object}).</p>
 * <p><b>Implementa:</b> {@link java.io.Serializable}.</p>
 *
 * <p><b>Composición:</b>
 * <ul>
 *   <li>Contiene un objeto de tipo {@link Usuario} (relación de composición/asociación).</li>
 *   <li>Contiene una lista {@link java.util.ArrayList} de objetos {@link Fertilizante}
 *       (relación de composición con cardinalidad uno a muchos).</li>
 * </ul>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 * @see Usuario
 * @see Fertilizante
 */
public class EmpresaAgricola implements Serializable {

    /**
     * Identificador de versión para la serialización.
     * Garantiza la compatibilidad entre versiones serializadas del objeto.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la empresa agrícola.
     * Generalmente asignado por el sistema o la base de datos.
     */
    private String id;

    /**
     * Nombre de la empresa agrícola.
     */
    private String nombre;

    /**
     * Ubicación geográfica de la empresa agrícola
     * (ciudad, municipio, departamento o dirección).
     */
    private String ubicacion;

    /**
     * Año de fundación de la empresa agrícola.
     * Se almacena como {@link String} para mayor flexibilidad de formato.
     */
    private String aniofundacion;

    /**
     * Usuario responsable o propietario de la empresa agrícola.
     * Establece una relación de asociación con la clase {@link Usuario}.
     */
    private Usuario usuario;

    /**
     * Lista de fertilizantes registrados en la empresa agrícola.
     * Establece una relación de composición uno a muchos con la clase {@link Fertilizante}.
     * Se inicializa como una lista vacía en los constructores.
     */
    private ArrayList<Fertilizante> fertilizante;

    /**
     * Constructor vacío de la clase {@code EmpresaAgricola}.
     *
     * <p>Crea una instancia sin inicializar los atributos de texto,
     * pero inicializa la lista de {@link Fertilizante}s como una lista vacía.
     * Requerido para procesos de deserialización y frameworks que instancian
     * objetos mediante reflexión.</p>
     */
    public EmpresaAgricola() {
        this.fertilizante = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de la clase {@code EmpresaAgricola}.
     *
     * <p>Crea una instancia de {@code EmpresaAgricola} con los datos principales y
     * el usuario asociado. La lista de fertilizantes se inicializa vacía.</p>
     *
     * @param nombre Nombre de la empresa agrícola.
     * @param ubicacion Ubicación geográfica de la empresa.
     * @param aniofundacion Año de fundación de la empresa.
     * @param usuario {@link Usuario} responsable o propietario de la empresa.
     */
    public EmpresaAgricola(String nombre, String ubicacion, String aniofundacion, Usuario usuario) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.aniofundacion = aniofundacion;
        this.usuario = usuario;
        this.fertilizante = new ArrayList<>();
    }

    /**
     * Obtiene el identificador único de la empresa agrícola.
     *
     * @return {@link String} con el id de la empresa, o {@code null} si no ha sido asignado.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la empresa agrícola.
     *
     * @param id {@link String} con el nuevo id de la empresa.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la empresa agrícola.
     *
     * @return {@link String} con el nombre de la empresa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la empresa agrícola.
     *
     * @param nombre {@link String} con el nuevo nombre de la empresa.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ubicación de la empresa agrícola.
     *
     * @return {@link String} con la ubicación de la empresa.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación de la empresa agrícola.
     *
     * @param ubicacion {@link String} con la nueva ubicación de la empresa.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el año de fundación de la empresa agrícola.
     *
     * @return {@link String} con el año de fundación de la empresa.
     */
    public String getAniofundacion() {
        return aniofundacion;
    }

    /**
     * Establece el año de fundación de la empresa agrícola.
     *
     * @param aniofundacion {@link String} con el nuevo año de fundación.
     */
    public void setAniofundacion(String aniofundacion) {
        this.aniofundacion = aniofundacion;
    }

    /**
     * Obtiene el usuario responsable o propietario de la empresa agrícola.
     *
     * @return Objeto {@link Usuario} asociado a la empresa.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario responsable o propietario de la empresa agrícola.
     *
     * @param usuario Objeto {@link Usuario} a asociar con la empresa.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la lista de fertilizantes registrados en la empresa agrícola.
     *
     * @return {@link ArrayList} de objetos {@link Fertilizante} asociados a la empresa.
     *         La lista puede estar vacía pero nunca será {@code null}.
     */
    public ArrayList<Fertilizante> getFertilizante() {
        return fertilizante;
    }

    /**
     * Establece la lista de fertilizantes de la empresa agrícola.
     *
     * <p>Reemplaza completamente la lista actual de fertilizantes por la proporcionada.</p>
     *
     * @param fertilizante {@link ArrayList} de objetos {@link Fertilizante} a asignar.
     */
    public void setFertilizante(ArrayList<Fertilizante> fertilizante) {
        this.fertilizante = fertilizante;
    }

    /**
     * Retorna una representación en cadena de texto del objeto {@code EmpresaAgricola}.
     *
     * <p>Incluye los campos: {@code id}, {@code nombre}, {@code ubicacion},
     * {@code aniofundacion}, {@code usuario} (mediante su propio {@code toString()})
     * y {@code fertilizantes} (lista completa).</p>
     *
     * @return {@link String} con los datos de la empresa en formato legible.
     */
    @Override
    public String toString() {
        return "EmpresaAgricola{id='" + id + "', nombre='" + nombre + "', ubicacion='" + ubicacion +
               "', aniofundacion='" + aniofundacion + "', usuario=" + usuario +
               ", fertilizantes=" + fertilizante + "}";
    }
}