
package com.alvarosaray.Controlador;

import com.alvarosaray.App;
import com.alvarosaray.Session;
import com.alvarosaray.Model.EmpresaAgricola;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controlador de la vista de Inicio de Sesión de la aplicación.
 *
 * <p>Gestiona la lógica de presentación y eventos del formulario de inicio de sesión.
 * Valida las credenciales ingresadas por el usuario (correo y contraseña), consulta
 * la sesión activa para autenticar contra el arreglo de empresas registradas y,
 * si el acceso es exitoso, redirige a la vista de inventario.</p>
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
 * @see com.alvarosaray.Model.EmpresaAgricola
 */

public class InicioSesionController {

    /**
     * Campo de texto donde el usuario ingresa su correo electrónico.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtCorreo;

    /**
     * Campo de contraseña donde el usuario ingresa su clave de acceso.
     * El texto es enmascarado automáticamente por el componente {@link PasswordField}.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private PasswordField txtContrasena;

    /**
     * Maneja el evento de inicio de sesión al presionar el botón correspondiente.
     *
     * <p>Flujo de ejecución:</p>
     * <ol>
     *   <li>Obtiene y valida el correo ingresado (formato con {@code @} y {@code .}).</li>
     *   <li>Obtiene y valida la contraseña (mínimo 6 caracteres).</li>
     *   <li>Llama al método {@code iniciarSesion} de la capa de operaciones con las credenciales.</li>
     *   <li>Si la autenticación falla, muestra una alerta de error.</li>
     *   <li>Si es exitosa, establece la empresa activa en la sesión y redirige a la vista {@code inventario}.</li>
     * </ol>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void iniciarSesion() throws IOException {
        String correo = txtCorreo.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        Session session = Session.getInstancia();

        if (!session.getOp().validarCorreo(correo)) {
            mostrarAlerta("Error", "Correo no valido. Debe contener @ y .", Alert.AlertType.ERROR);
            return;
        }

        if (!session.getOp().validarContrasena(contrasena)) {
            mostrarAlerta("Error", "La contraseña debe tener al menos 6 caracteres.", Alert.AlertType.ERROR);
            return;
        }

        EmpresaAgricola empresa = session.getOp().iniciarSesion(
            session.getOp().getArreglo(), correo, contrasena
        );

        if (empresa == null) {
            mostrarAlerta("Error", "Correo o contraseña incorrectos.", Alert.AlertType.ERROR);
            return;
        }

        session.setEmpresaActiva(empresa);
        App.setRoot("inventario");
    }

    /**
     * Maneja el evento de navegación hacia la vista de Registro.
     *
     * <p>Redirige al usuario a la vista {@code Registro} cuando presiona
     * el enlace o botón de registro.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void irARegistro() throws IOException {
        App.setRoot("Registro");
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
