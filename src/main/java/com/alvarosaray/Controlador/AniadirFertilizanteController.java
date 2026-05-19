
package com.alvarosaray.Controlador;

import com.alvarosaray.App;
import com.alvarosaray.Session;
import com.alvarosaray.Model.Fertilizante;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de Añadir Fertilizante de la aplicación.
 *
 * <p>Gestiona la lógica de presentación y eventos del formulario para registrar
 * un nuevo {@link com.alvarosaray.Model.Fertilizante} en el inventario de la empresa
 * agrícola activa en sesión. Valida los campos ingresados, genera el identificador
 * del nuevo fertilizante, lo crea, lo persiste mediante serialización y redirige
 * de vuelta al inventario.</p>
 *
 * <p>Implementa {@link javafx.fxml.Initializable} para precargar las opciones
 * del selector de tipo al momento de cargar la vista.</p>
 *
 * <p><b>Visibilidad:</b> Clase pública concreta (no abstracta, no interfaz).</p>
 * <p><b>Herencia:</b> No hereda de ninguna clase de dominio (hereda implícitamente de {@link Object}).</p>
 * <p><b>Implementa:</b> {@link javafx.fxml.Initializable}.</p>
 * <p><b>Patrón:</b> Controlador MVC — actúa como intermediario entre la vista FXML
 * y la lógica de negocio accedida a través de {@link com.alvarosaray.Session}.</p>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-05-18
 * @see com.alvarosaray.Session
 * @see com.alvarosaray.Model.Fertilizante
 */

public class AniadirFertilizanteController implements Initializable {

    /**
     * Selector de fecha para ingresar la fecha de adquisición del fertilizante.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private DatePicker dateFecha;

    /**
     * Campo de texto para ingresar el nombre del fertilizante.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtNombre;

    /**
     * Selector de opciones para escoger el tipo del fertilizante.
     * Las opciones disponibles son: {@code Organico}, {@code Quimico},
     * {@code Biologico}, {@code Mineral} y {@code Otro}.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private ChoiceBox<String> choiceTipo;

    /**
     * Campo de texto para ingresar la cantidad del fertilizante.
     * El valor debe ser un número entero mayor a 0.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtCantidad;

    /**
     * Inicializa el controlador tras cargar el archivo FXML.
     *
     * <p>Agrega las opciones de tipo de fertilizante al {@link ChoiceBox}
     * y establece {@code "Organico"} como valor seleccionado por defecto.</p>
     *
     * @param url URL utilizada para resolver rutas relativas del objeto raíz (puede ser {@code null}).
     * @param rb  Recursos de internacionalización asociados al controlador (puede ser {@code null}).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceTipo.getItems().addAll("Organico", "Quimico", "Biologico", "Mineral", "Otro");
        choiceTipo.setValue("Organico");
    }

    /**
     * Maneja el evento de creación de un nuevo fertilizante al presionar el botón correspondiente.
     *
     * <p>Flujo de ejecución:</p>
     * <ol>
     *   <li>Obtiene los valores de los campos nombre, cantidad y tipo.</li>
     *   <li>Valida que el nombre no esté vacío.</li>
     *   <li>Valida que la cantidad sea un entero mayor a 0.</li>
     *   <li>Valida que se haya seleccionado una fecha de adquisición.</li>
     *   <li>Formatea la fecha seleccionada en el patrón {@code dd/MM/yyyy}.</li>
     *   <li>Genera un id único para el fertilizante usando la capa de operaciones.</li>
     *   <li>Crea el objeto {@link Fertilizante} y le asigna el id generado.</li>
     *   <li>Llama al método {@code crear} de la capa de operaciones para registrarlo en la empresa activa.</li>
     *   <li>Serializa el arreglo actualizado para persistir los cambios.</li>
     *   <li>Muestra alerta de éxito y redirige a la vista {@code inventario}.</li>
     * </ol>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void aniadirFertilizante() throws IOException {
        Session session = Session.getInstancia();

        String nombre = txtNombre.getText().trim();
        String cantidad = txtCantidad.getText().trim();
        String tipo = choiceTipo.getValue();

        if (!session.getOp().validarTexto(nombre)) {
            mostrarAlerta("Error", "El nombre no puede estar vacio.", Alert.AlertType.ERROR);
            return;
        }
        if (!session.getOp().validarCantidad(cantidad)) {
            mostrarAlerta("Error", "La cantidad debe ser un numero entero mayor a 0.", Alert.AlertType.ERROR);
            return;
        }
        if (dateFecha.getValue() == null) {
            mostrarAlerta("Error", "Seleccione una fecha de adquisicion.", Alert.AlertType.ERROR);
            return;
        }

        String fecha = dateFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String idFert = session.getOp().generarIdFertilizante(session.getEmpresaActiva());
        Fertilizante f = new Fertilizante(tipo, nombre, fecha, Integer.parseInt(cantidad));
        f.setId(idFert);

        String resultado = session.getOp().crear(session.getEmpresaActiva(), f);
        session.getOp().Serializar(session.getOp().getArreglo(), session.getPath(), session.getFile());

        mostrarAlerta("Exito", resultado, Alert.AlertType.INFORMATION);
        App.setRoot("inventario");
    }

    /**
     * Maneja el evento de cancelación del formulario.
     *
     * <p>Descarta cualquier dato ingresado y redirige al usuario
     * de vuelta a la vista de inventario sin realizar ninguna operación.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void cancelar() throws IOException {
        App.setRoot("inventario");
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
