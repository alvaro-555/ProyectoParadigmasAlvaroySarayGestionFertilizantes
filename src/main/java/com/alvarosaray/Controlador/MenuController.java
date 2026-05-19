
package com.alvarosaray.Controlador;

import com.alvarosaray.App;
import com.alvarosaray.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Controlador de la vista del Menú principal de la aplicación.
 *
 * <p>Gestiona la navegación desde el menú de bienvenida hacia las vistas de
 * Inicio de Sesión y Registro, así como la deserialización de datos persistidos
 * y la salida de la aplicación.</p>
 *
 * <p><b>Visibilidad:</b> Clase pública concreta (no abstracta, no interfaz).</p>
 * <p><b>Herencia:</b> No hereda de ninguna clase de dominio (hereda implícitamente de {@link Object}).</p>
 * <p><b>Implementa:</b> No implementa interfaces explícitas.</p>
 * <p><b>Patrón:</b> Controlador MVC — actúa como intermediario entre la vista FXML
 * y la lógica de negocio accedida a través de {@link com.alvarosaray.Session}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-05-18
 * @see com.alvarosaray.Session
 * @see com.alvarosaray.App
 */

public class MenuController {

    /**
     * Maneja el evento de navegación hacia la vista de Inicio de Sesión.
     *
     * <p>Redirige al usuario a la vista {@code Inicio_sesion} al presionar
     * el botón de inicio de sesión en el menú.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void irAInicioSesion() throws IOException {
        App.setRoot("Inicio_sesion");
    }

    /**
     * Maneja el evento de navegación hacia la vista de Registro.
     *
     * <p>Redirige al usuario a la vista {@code Registro} al presionar
     * el botón de registro en el menú.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void irARegistro() throws IOException {
        App.setRoot("Registro");
    }

    /**
     * Maneja el evento de deserialización de datos desde el archivo persistido.
     *
     * <p>Carga el arreglo de empresas agrícolas previamente serializado en disco,
     * actualizando el arreglo en memoria de la sesión activa. Muestra una alerta
     * de éxito al finalizar la carga.</p>
     *
     * <p>Utiliza la ruta ({@code path}) y el nombre de archivo ({@code file})
     * configurados en la {@link com.alvarosaray.Session} actual.</p>
     */
    @FXML
    private void deserializar() {
        Session session = Session.getInstancia();
        session.getOp().setArreglo(
            session.getOp().deserializacion(session.getPath(), session.getFile())
        );
        mostrarAlerta("Exito", "Datos cargados correctamente.", Alert.AlertType.INFORMATION);
    }

    /**
     * Maneja el evento de salida de la aplicación.
     *
     * <p>Termina la ejecución del proceso de la JVM con código de salida {@code 0},
     * cerrando completamente la aplicación.</p>
     */
    @FXML
    private void salir() {
        System.exit(0);
    }

    /**
     * Muestra una ventana de diálogo ({@link Alert}) con un mensaje informativo,
     * de advertencia o de error al usuario.
     *
     * @param titulo  Título de la ventana de alerta.
     * @param mensaje Contenido del mensaje a mostrar en la alerta.
     * @param tipo    Tipo de alerta ({@link Alert.AlertType}):
     *                {@code ERROR}, {@code WARNING} o {@code INFORMATION}.
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
