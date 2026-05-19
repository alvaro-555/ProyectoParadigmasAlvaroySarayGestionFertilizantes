
package com.alvarosaray.Controlador;

import com.alvarosaray.App;
import com.alvarosaray.Session;
import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controlador de la vista de Registro de la aplicación.
 *
 * <p>Gestiona la lógica de presentación y eventos del formulario de registro de una
 * nueva empresa agrícola junto con su usuario responsable. Valida todos los campos
 * del formulario, crea los objetos {@link com.alvarosaray.Model.Usuario} y
 * {@link com.alvarosaray.Model.EmpresaAgricola}, los agrega al arreglo de datos
 * de la sesión y persiste la información mediante serialización.</p>
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
 * @see com.alvarosaray.Model.Usuario
 */

public class RegistroController {

    /**
     * Campo de texto para ingresar el nombre de la empresa agrícola.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtNombreEmpresa;

    /**
     * Campo de texto para ingresar el año de fundación de la empresa agrícola.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtAnioFundacion;

    /**
     * Campo de texto para ingresar la ubicación de la empresa agrícola.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtUbicacion;

    /**
     * Campo de texto para ingresar el nombre del usuario responsable de la empresa.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtNombreUsuario;

    /**
     * Campo de contraseña para ingresar la clave de acceso del usuario.
     * El texto es enmascarado automáticamente por el componente {@link PasswordField}.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private PasswordField txtContrasena;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtCorreo;

    /**
     * Campo de texto para ingresar el número de documento de identidad del usuario.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtNumDocumento;

    /**
     * Maneja el evento de registro al presionar el botón correspondiente.
     *
     * <p>Flujo de ejecución:</p>
     * <ol>
     *   <li>Obtiene y valida los datos de la empresa: nombre, ubicación y año de fundación.</li>
     *   <li>Obtiene y valida los datos del usuario: nombre, correo, contraseña y número de documento.</li>
     *   <li>Crea el objeto {@link com.alvarosaray.Model.Usuario} con sus datos y le asigna un id generado.</li>
     *   <li>Crea el objeto {@link com.alvarosaray.Model.EmpresaAgricola} con sus datos y el usuario, y le asigna un id generado.</li>
     *   <li>Agrega la empresa al arreglo de datos de la sesión.</li>
     *   <li>Serializa el arreglo actualizado para persistir los cambios.</li>
     *   <li>Muestra una alerta de éxito y redirige al menú principal.</li>
     * </ol>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void registrar() throws IOException {
        Session session = Session.getInstancia();

        String nombreEmpresa = txtNombreEmpresa.getText().trim();
        String anio = txtAnioFundacion.getText().trim();
        String ubicacion = txtUbicacion.getText().trim();
        String nombreUsuario = txtNombreUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();
        String correo = txtCorreo.getText().trim();
        String numDoc = txtNumDocumento.getText().trim();

        // Validaciones empresa
        if (!session.getOp().validarTexto(nombreEmpresa)) {
            mostrarAlerta("Error", "El nombre de la empresa no puede estar vacio.", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarTexto(ubicacion)) {
            mostrarAlerta("Error", "La ubicacion no puede estar vacia.", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarAniofundacion(anio)) {
            mostrarAlerta("Error", "Año invalido. Ingrese un valor entre 1900 y 2025.", Alert.AlertType.ERROR);
            return;
        }

        // Validaciones usuario
        if (!session.getOp().validarTexto(nombreUsuario)) {
            mostrarAlerta("Error", "El nombre del usuario no puede estar vacio.", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarCorreo(correo)) {
            mostrarAlerta("Error", "Correo invalido. Debe contener @ y .", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarContrasena(contrasena)) {
            mostrarAlerta("Error", "La contraseña debe tener al menos 6 caracteres.", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarNumeroEntero(numDoc)) {
            mostrarAlerta("Error", "El numero de documento debe ser numerico.", Alert.AlertType.ERROR);
            return;
        }

        // Crear usuario y empresa
        Usuario usuario = new Usuario(correo, contrasena, nombreUsuario, Integer.parseInt(numDoc));
        String idUsuario = session.getOp().generarIdUsuario(session.getOp().getArreglo());
        usuario.setId(idUsuario);

        EmpresaAgricola empresa = new EmpresaAgricola(nombreEmpresa, ubicacion, anio, usuario);
        String idEmpresa = session.getOp().generarIdEmpresa(session.getOp().getArreglo());
        empresa.setId(idEmpresa);

        session.getOp().getArreglo().add(empresa);
        session.getOp().Serializar(session.getOp().getArreglo(), session.getPath(), session.getFile());

        mostrarAlerta("Exito", "Registro exitoso. Bienvenido " + nombreUsuario + "!", Alert.AlertType.INFORMATION);
        App.setRoot("Menu1regisyiniciS");
    }

    /**
     * Maneja el evento de cancelación del registro.
     *
     * <p>Descarta cualquier dato ingresado y redirige al usuario
     * de vuelta al menú principal sin realizar ninguna operación.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void cancelar() throws IOException {
        App.setRoot("Menu1regisyiniciS");
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
