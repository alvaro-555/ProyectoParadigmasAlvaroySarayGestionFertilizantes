package com.alvarosaray.Model;

import java.io.Serializable;
/**
 * Representa un fertilizante registrado en el sistema de gestión agrícola.
 *
 * <p>Esta clase modela la entidad Fertilizante con sus características de tipo,
 * nombre, fecha de adquisición y cantidad disponible.
 * Implementa {@link java.io.Serializable} para permitir la serialización del objeto,
 * facilitando su almacenamiento y transmisión entre capas del sistema.</p>
 *
 * <p><b>Visibilidad:</b> Clase pública concreta (no abstracta, no interfaz).</p>
 * <p><b>Herencia:</b> No hereda de ninguna clase de dominio (hereda implícitamente de {@link Object}).</p>
 * <p><b>Implementa:</b> {@link java.io.Serializable}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
public class Fertilizante implements Serializable {

    /**
     * Identificador de versión para la serialización.
     * Garantiza la compatibilidad entre versiones serializadas del objeto.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del fertilizante.
     * Generalmente asignado por el sistema o la base de datos.
     */
    private String id;

    /**
     * Tipo de fertilizante (por ejemplo: orgánico, químico, foliar, etc.).
     */
    private String tipo;

    /**
     * Nombre comercial o descriptivo del fertilizante.
     */
    private String nombre;

    /**
     * Fecha en la que se adquirió el fertilizante.
     * Se almacena como {@link String} para flexibilidad en el formato de fecha.
     */
    private String fechaAdquisicion;

    /**
     * Cantidad disponible del fertilizante en inventario.
     * Representa unidades según el contexto de uso (kg, litros, bultos, etc.).
     */
    private int cantidad;

    /**
     * Constructor vacío de la clase {@code Fertilizante}.
     *
     * <p>Crea una instancia sin inicializar atributos.
     * Requerido para procesos de deserialización y frameworks que instancian
     * objetos mediante reflexión.</p>
     */
    public Fertilizante() {}

    /**
     * Constructor parametrizado de la clase {@code Fertilizante}.
     *
     * <p>Crea una instancia de {@code Fertilizante} con todos sus datos esenciales.</p>
     *
     * @param tipo             Tipo de fertilizante.
     * @param nombre           Nombre del fertilizante.
     * @param fechaAdquisicion Fecha en que fue adquirido el fertilizante.
     * @param cantidad         Cantidad disponible del fertilizante.
     */
    public Fertilizante(String tipo, String nombre, String fechaAdquisicion, int cantidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaAdquisicion = fechaAdquisicion;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el identificador único del fertilizante.
     *
     * @return {@link String} con el id del fertilizante, o {@code null} si no ha sido asignado.
     */
    public String getId() { return id; }

    /**
     * Establece el identificador único del fertilizante.
     *
     * @param id {@link String} con el nuevo id del fertilizante.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Obtiene el tipo del fertilizante.
     *
     * @return {@link String} con el tipo del fertilizante.
     */
    public String getTipo() { return tipo; }

    /**
     * Establece el tipo del fertilizante.
     *
     * @param tipo {@link String} con el nuevo tipo del fertilizante.
     */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * Obtiene el nombre del fertilizante.
     *
     * @return {@link String} con el nombre del fertilizante.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del fertilizante.
     *
     * @param nombre {@link String} con el nuevo nombre del fertilizante.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la fecha de adquisición del fertilizante.
     *
     * @return {@link String} con la fecha de adquisición.
     */
    public String getFechaAdquisicion() { return fechaAdquisicion; }

    /**
     * Establece la fecha de adquisición del fertilizante.
     *
     * @param fechaAdquisicion {@link String} con la nueva fecha de adquisición.
     */
    public void setFechaAdquisicion(String fechaAdquisicion) { this.fechaAdquisicion = fechaAdquisicion; }

    /**
     * Obtiene la cantidad disponible del fertilizante en inventario.
     *
     * @return {@code int} con la cantidad del fertilizante.
     */
    public int getCantidad() { return cantidad; }

    /**
     * Establece la cantidad disponible del fertilizante en inventario.
     *
     * @param cantidad {@code int} con la nueva cantidad del fertilizante.
     */
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    /**
     * Retorna una representación en cadena de texto del objeto {@code Fertilizante}.
     *
     * <p>Incluye los campos: {@code id}, {@code nombre}, {@code tipo},
     * {@code cantidad} y {@code fechaAdquisicion}.</p>
     *
     * @return {@link String} con los datos del fertilizante en formato legible.
     */
    @Override
    public String toString() {
        return "Fertilizante{id='" + id + "', nombre='" + nombre + "', tipo='" + tipo +
               "', cantidad=" + cantidad + ", fechaAdquisicion='" + fechaAdquisicion + "'}";
    }
}
