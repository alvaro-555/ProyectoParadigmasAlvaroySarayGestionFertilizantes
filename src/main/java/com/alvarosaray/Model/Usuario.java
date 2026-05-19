package com.alvarosaray.Model;

import java.io.Serializable;
/**
 * Representa a un usuario del sistema de gestión agrícola.
 *
 * <p>Esta clase modela la entidad Usuario con sus datos personales y de acceso.
 * Implementa {@link java.io.Serializable} para permitir la serialización del objeto,
 * lo que facilita su almacenamiento y transmisión.</p>
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
public class Usuario implements Serializable {

    /**
     * Identificador de versión para la serialización.
     * Garantiza la compatibilidad entre versiones serializadas del objeto.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del usuario.
     * Generalmente asignado por el sistema o la base de datos.
     */
    private String id;

    /**
     * Correo electrónico del usuario.
     * Se utiliza como identificador de acceso al sistema.
     */
    private String correo;

    /**
     * Contraseña de acceso del usuario.
     */
    private String contrasena;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Número de documento de identidad del usuario.
     */
    private int numDocumento;

    /**
     * Constructor vacío de la clase {@code Usuario}.
     *
     * <p>Crea una instancia sin inicializar atributos.
     * Requerido para procesos de deserialización y frameworks que instancian
     * objetos mediante reflexión.</p>
     */
    public Usuario() {}

    /**
     * Constructor parametrizado de la clase {@code Usuario}.
     *
     * <p>Crea una instancia de {@code Usuario} con todos sus datos esenciales.</p>
     *
     * @param correo       Correo electrónico del usuario.
     * @param contrasena   Contraseña de acceso del usuario.
     * @param nombre       Nombre completo del usuario.
     * @param numDocumento Número de documento de identidad del usuario.
     */
    public Usuario(String correo, String contrasena, String nombre, int numDocumento) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.numDocumento = numDocumento;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return {@link String} con el id del usuario, o {@code null} si no ha sido asignado.
     */
    public String getId() { return id; }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id {@link String} con el nuevo id del usuario.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return {@link String} con el correo del usuario.
     */
    public String getCorreo() { return correo; }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo {@link String} con el nuevo correo del usuario.
     */
    public void setCorreo(String correo) { this.correo = correo; }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return {@link String} con la contraseña del usuario.
     */
    public String getContrasena() { return contrasena; }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena {@link String} con la nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return {@link String} con el nombre del usuario.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombre {@link String} con el nuevo nombre del usuario.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene el número de documento de identidad del usuario.
     *
     * @return {@code int} con el número de documento del usuario.
     */
    public int getNumDocumento() { return numDocumento; }

    /**
     * Establece el número de documento de identidad del usuario.
     *
     * @param numDocumento {@code int} con el nuevo número de documento del usuario.
     */
    public void setNumDocumento(int numDocumento) { this.numDocumento = numDocumento; }

    /**
     * Retorna una representación en cadena de texto del objeto {@code Usuario}.
     *
     * <p>Incluye los campos: {@code id}, {@code nombre}, {@code correo} y {@code numDocumento}.
     * La contraseña es excluida intencionalmente por seguridad.</p>
     *
     * @return {@link String} con los datos del usuario en formato legible.
     */
    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nombre='" + nombre + "', correo='" + correo + "', numDocumento=" + numDocumento + "}";
    }
}
