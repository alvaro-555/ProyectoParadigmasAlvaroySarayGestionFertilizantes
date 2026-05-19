
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de Editar Fertilizante de la aplicación.
 *
 * <p>Gestiona la lógica de presentación y eventos del formulario para modificar
 * los datos de un {@link com.alvarosaray.Model.Fertilizante} existente en el inventario
 * de la empresa agrícola activa en sesión.</p>
 *
 * <p>Al inicializarse, precarga los campos del formulario con los datos actuales
 * del fertilizante seleccionado previamente desde la vista de inventario.
 * Tras validar los campos editados, actualiza el registro, persiste los cambios
 * mediante serialización y redirige de vuelta al inventario.</p>
 *
 * <p>Implementa {@link javafx.fxml.Initializable} para ejecutar la precarga de datos
 * al momento de cargar la vista.</p>
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
 * @see InventarioController
 */

public class EditarFertilizanteController implements Initializable {

    /**
     * Selector de fecha para modificar la fecha de adquisición del fertilizante.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private DatePicker dateFecha;

    /**
     * Campo de texto para modificar el nombre del fertilizante.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtNombre;

    /**
     * Selector de opciones para modificar el tipo del fertilizante.
     * Las opciones disponibles son: {@code Organico}, {@code Quimico},
     * {@code Biologico}, {@code Mineral} y {@code Otro}.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private ChoiceBox<String> choiceTipo;

    /**
     * Campo de texto para modificar la cantidad del fertilizante.
     * El valor debe ser un número entero mayor a 0.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtCantidad;

    /**
     * Fertilizante seleccionado desde la vista de inventario que será editado.
     *
     * <p>Atributo estático que permite transferir el objeto entre controladores
     * sin necesidad de parámetros de navegación. Es establecido externamente
     * por {@link InventarioController} antes de cargar esta vista,
     * mediante el método {@link #setFertilizanteAEditar(Fertilizante)}.</p>
     */
    private static Fertilizante fertilizanteAEditar;

    /**
     * Establece el fertilizante que será editado en este controlador.
     *
     * <p>Este método es llamado por {@link InventarioController} antes de navegar
     * a la vista de edición, para pasar el fertilizante seleccionado en la tabla.</p>
     *
     * @param f Objeto {@link Fertilizante} con los datos actuales a editar.
     */
    public static void setFertilizanteAEditar(Fertilizante f) {
        fertilizanteAEditar = f;
    }

    /**
     * Inicializa el controlador tras cargar el archivo FXML.
     *
     * <p>Agrega las opciones de tipo de fertilizante al {@link ChoiceBox} y, si
     * {@code fertilizanteAEditar} no es {@code null}, precarga los campos del formulario
     * con los datos actuales del fertilizante. La fecha de adquisición se parsea desde
     * el formato {@code dd/MM/yyyy}; si ocurre algún error de parseo, se establece la
     * fecha actual como valor por defecto.</p>
     *
     * @param url URL utilizada para resolver rutas relativas del objeto raíz (puede ser {@code null}).
     * @param rb  Recursos de internacionalización asociados al controlador (puede ser {@code null}).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceTipo.getItems().addAll("Organico", "Quimico", "Biologico", "Mineral", "Otro");

        // Prellenar campos con datos actuales del fertilizante
        if (fertilizanteAEditar != null) {
            txtNombre.setText(fertilizanteAEditar.getNombre());
            txtCantidad.setText(String.valueOf(fertilizanteAEditar.getCantidad()));
            choiceTipo.setValue(fertilizanteAEditar.getTipo());
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dateFecha.setValue(LocalDate.parse(fertilizanteAEditar.getFechaAdquisicion(), formatter));
            } catch (Exception e) {
                dateFecha.setValue(LocalDate.now());
            }
        }
    }

    /**
     * Maneja el evento de confirmación de la edición al presionar el botón correspondiente.
     *
     * <p>Flujo de ejecución:</p>
     * <ol>
     *   <li>Obtiene los nuevos valores de los campos nombre, cantidad y tipo.</li>
     *   <li>Valida que el nombre no esté vacío.</li>
     *   <li>Valida que la cantidad sea un entero mayor a 0.</li>
     *   <li>Valida que se haya seleccionado una fecha de adquisición.</li>
     *   <li>Formatea la fecha seleccionada en el patrón {@code dd/MM/yyyy}.</li>
     *   <li>Crea un nuevo objeto {@link Fertilizante} con los datos actualizados,
     *       conservando el id original del fertilizante editado.</li>
     *   <li>Llama al método {@code modificar} de la capa de operaciones para actualizar el registro.</li>
     *   <li>Serializa el arreglo actualizado para persistir los cambios.</li>
     *   <li>Muestra alerta de éxito y redirige a la vista {@code inventario}.</li>
     * </ol>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void editarFertilizante() throws IOException {
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

        Fertilizante editado = new Fertilizante(tipo, nombre, fecha, Integer.parseInt(cantidad));
        editado.setId(fertilizanteAEditar.getId());

        String resultado = session.getOp().modificar(session.getEmpresaActiva(), fertilizanteAEditar.getId(), editado);
        session.getOp().Serializar(session.getOp().getArreglo(), session.getPath(), session.getFile());

        mostrarAlerta("Exito", resultado, Alert.AlertType.INFORMATION);
        App.setRoot("inventario");
    }

    /**
     * Maneja el evento de cancelación de la edición.
     *
     * <p>Descarta cualquier cambio realizado en el formulario y redirige al usuario
     * de vuelta a la vista de inventario sin modificar ningún dato.</p>
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
