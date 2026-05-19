package com.alvarosaray;

import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Servicio.ImplementacionOperacion;

/**
 * Clase Singleton que gestiona el estado compartido de la sesión activa de la aplicación.
 *
 * <p>Provee un punto de acceso global único a los datos de sesión: la empresa agrícola
 * actualmente autenticada ({@link com.alvarosaray.Model.EmpresaAgricola}) y la instancia
 * del servicio de operaciones ({@link com.alvarosaray.Servicio.ImplementacionOperacion}).</p>
 *
 * <p>Al instanciarse por primera vez, inicializa el servicio de operaciones, crea el
 * directorio de datos si no existe y deserializa automáticamente la información persistida
 * en disco, dejando el sistema listo para operar.</p>
 *
 * <p><b>Patrón de diseño:</b> Singleton — garantiza que exista una única instancia de
 * {@code Session} durante todo el ciclo de vida de la aplicación, implementado mediante
 * inicialización perezosa (<i>lazy initialization</i>).</p>
 *
 * <p><b>Visibilidad:</b> Clase pública concreta (no abstracta, no interfaz).</p>
 * <p><b>Herencia:</b> No hereda de ninguna clase de dominio (hereda implícitamente de {@link Object}).</p>
 * <p><b>Implementa:</b> No implementa interfaces explícitas.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-05-18
 * @see com.alvarosaray.Model.EmpresaAgricola
 * @see com.alvarosaray.Servicio.ImplementacionOperacion
 */

public class Session {

    /**
     * Única instancia de la clase {@code Session}.
     * Inicializada de forma perezosa en {@link #getInstancia()}.
     */
    private static Session instancia;

    /**
     * Ruta del directorio donde se almacena el archivo de datos serializado.
     * Valor fijo: {@code "Datos"}.
     */
    private static final String PATH = "Datos";

    /**
     * Nombre del archivo de datos serializado.
     * Valor fijo: {@code "Informacion.dat"}.
     */
    private static final String FILE = "Informacion.dat";

    /**
     * Instancia del servicio de operaciones que provee la lógica de negocio:
     * CRUD de fertilizantes, validaciones, serialización y deserialización.
     */
    private ImplementacionOperacion op;

    /**
     * Empresa agrícola actualmente autenticada en la sesión.
     * Es {@code null} cuando no hay ningún usuario con sesión iniciada.
     */
    private EmpresaAgricola empresaActiva;

    /**
     * Constructor privado de la clase {@code Session}.
     *
     * <p>Restringe la instanciación externa para garantizar el patrón Singleton.
     * Al ejecutarse:</p>
     * <ol>
     *   <li>Crea una nueva instancia de {@link ImplementacionOperacion}.</li>
     *   <li>Crea el directorio {@value #PATH} en el sistema de archivos si no existe.</li>
     *   <li>Deserializa el archivo {@value #FILE} desde {@value #PATH} y carga
     *       el arreglo de empresas en memoria.</li>
     * </ol>
     */
    private Session() {
        op = new ImplementacionOperacion();
        new java.io.File(PATH).mkdirs();
        op.setArreglo(op.deserializacion(PATH, FILE));
    }

    /**
     * Retorna la única instancia de {@code Session} (patrón Singleton).
     *
     * <p>Si la instancia aún no ha sido creada, la inicializa invocando el
     * constructor privado. Las llamadas subsiguientes retornan siempre
     * la misma instancia ya existente.</p>
     *
     * @return La instancia única y global de {@code Session}.
     */
    public static Session getInstancia() {
        if (instancia == null) {
            instancia = new Session();
        }
        return instancia;
    }

    /**
     * Obtiene el servicio de operaciones de la sesión.
     *
     * @return Instancia de {@link ImplementacionOperacion} con la lógica de negocio disponible.
     */
    public ImplementacionOperacion getOp() { return op; }

    /**
     * Obtiene la empresa agrícola actualmente autenticada en la sesión.
     *
     * @return Objeto {@link EmpresaAgricola} de la sesión activa,
     *         o {@code null} si no hay ninguna sesión iniciada.
     */
    public EmpresaAgricola getEmpresaActiva() { return empresaActiva; }

    /**
     * Establece la empresa agrícola activa de la sesión.
     *
     * <p>Se asigna al iniciar sesión correctamente. Para cerrar sesión,
     * se invoca este método con {@code null} como argumento.</p>
     *
     * @param empresa Objeto {@link EmpresaAgricola} a establecer como sesión activa,
     *                o {@code null} para cerrar la sesión.
     */
    public void setEmpresaActiva(EmpresaAgricola empresa) { this.empresaActiva = empresa; }

    /**
     * Obtiene la ruta del directorio donde se almacena el archivo de datos.
     *
     * @return {@link String} con la ruta del directorio de datos ({@value #PATH}).
     */
    public String getPath() { return PATH; }

    /**
     * Obtiene el nombre del archivo de datos serializado.
     *
     * @return {@link String} con el nombre del archivo de datos ({@value #FILE}).
     */
    public String getFile() { return FILE; }
}
