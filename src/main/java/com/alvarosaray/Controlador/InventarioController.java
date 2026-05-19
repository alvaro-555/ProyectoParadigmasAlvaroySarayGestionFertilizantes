package com.alvarosaray.Controlador;

import com.alvarosaray.App;
import com.alvarosaray.Session;
import com.alvarosaray.Model.Fertilizante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de Inventario de la aplicación.
 *
 * <p>Gestiona la lógica de presentación del inventario de fertilizantes de la empresa
 * agrícola activa en sesión. Permite listar, buscar por ID, agregar, editar y eliminar
 * fertilizantes, así como serializar manualmente los datos y cerrar la sesión del usuario.</p>
 *
 * <p>Implementa {@link javafx.fxml.Initializable} para ejecutar configuración inicial
 * de la tabla al momento de cargar la vista.</p>
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
 * @see EditarFertilizanteController
 */

public class InventarioController implements Initializable {

    /**
     * Tabla principal que muestra la lista de fertilizantes del inventario.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableView<Fertilizante> tablaFertilizantes;

    /**
     * Columna de la tabla que muestra el nombre del fertilizante.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableColumn<Fertilizante, String> colNombre;

    /**
     * Columna de la tabla que muestra la fecha de adquisición del fertilizante.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableColumn<Fertilizante, String> colFecha;

    /**
     * Columna de la tabla que muestra la cantidad disponible del fertilizante.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableColumn<Fertilizante, Integer> colCantidad;

    /**
     * Columna de la tabla que muestra el tipo del fertilizante.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableColumn<Fertilizante, String> colTipo;

    /**
     * Columna de la tabla que muestra el identificador único del fertilizante.
     * Inyectada por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TableColumn<Fertilizante, String> colId;

    /**
     * Campo de texto para ingresar el ID del fertilizante a buscar.
     * Inyectado por JavaFX desde el archivo FXML correspondiente.
     */
    @FXML private TextField txtBuscarId;

    /**
     * Inicializa el controlador tras cargar el archivo FXML.
     *
     * <p>Configura los {@link PropertyValueFactory} de cada columna de la tabla
     * enlazándolas con los atributos correspondientes del modelo {@link Fertilizante},
     * y carga los datos del inventario de la empresa activa en sesión.</p>
     *
     * @param url            URL utilizada para resolver rutas relativas del objeto raíz (puede ser {@code null}).
     * @param rb             Recursos de internacionalización asociados al controlador (puede ser {@code null}).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaAdquisicion"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cargarTabla();
    }

    /**
     * Carga todos los fertilizantes de la empresa activa en la tabla de inventario.
     *
     * <p>Obtiene la lista completa de fertilizantes a través de la capa de operaciones
     * y la asigna como {@link ObservableList} al {@link TableView}.</p>
     */
    private void cargarTabla() {
        Session session = Session.getInstancia();
        ObservableList<Fertilizante> lista = FXCollections.observableArrayList(
            session.getOp().listarTodos(session.getEmpresaActiva())
        );
        tablaFertilizantes.setItems(lista);
    }

    /**
     * Maneja el evento de búsqueda de un fertilizante por su ID.
     *
     * <p>Lee el ID ingresado en {@code txtBuscarId} y consulta la capa de operaciones.
     * Si se encuentra el fertilizante, muestra únicamente ese registro en la tabla.
     * Si no se encuentra, muestra una alerta de advertencia y recarga la tabla completa.</p>
     */
    @FXML
    private void buscarPorId() {
        String id = txtBuscarId.getText().trim();
        Session session = Session.getInstancia();
        Fertilizante f = session.getOp().listarUno(session.getEmpresaActiva(), id);
        if (f != null) {
            ObservableList<Fertilizante> resultado = FXCollections.observableArrayList(f);
            tablaFertilizantes.setItems(resultado);
        } else {
            mostrarAlerta("No encontrado", "No se encontro fertilizante con ID: " + id, Alert.AlertType.WARNING);
            cargarTabla();
        }
    }

    /**
     * Maneja el evento de navegación hacia la vista de añadir fertilizante.
     *
     * <p>Redirige a la vista {@code Añadir_Fertilizante} para registrar un nuevo fertilizante.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void irAAniadir() throws IOException {
        App.setRoot("Añadir_Fertilizante");
    }

    /**
     * Maneja el evento de navegación hacia la vista de editar fertilizante.
     *
     * <p>Verifica que haya un fertilizante seleccionado en la tabla. Si no hay selección,
     * muestra una alerta de advertencia. Si hay selección, lo pasa al
     * {@link EditarFertilizanteController} mediante su método estático y redirige
     * a la vista {@code Editar_Fertilizante}.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void irAEditar() throws IOException {
        Fertilizante seleccionado = tablaFertilizantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un fertilizante de la tabla para editar.", Alert.AlertType.WARNING);
            return;
        }
        EditarFertilizanteController.setFertilizanteAEditar(seleccionado);
        App.setRoot("Editar_Fertilizante");
    }

    /**
     * Maneja el evento de eliminación de un fertilizante seleccionado en la tabla.
     *
     * <p>Verifica que haya un fertilizante seleccionado. Si no hay selección,
     * muestra una alerta de advertencia. Si hay selección, lo elimina a través
     * de la capa de operaciones, serializa los cambios y recarga la tabla.</p>
     */
    @FXML
    private void eliminarFertilizante() {
        Fertilizante seleccionado = tablaFertilizantes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un fertilizante de la tabla para eliminar.", Alert.AlertType.WARNING);
            return;
        }
        Session session = Session.getInstancia();
        String resultado = session.getOp().eliminar(session.getEmpresaActiva(), seleccionado.getId());
        session.getOp().Serializar(session.getOp().getArreglo(), session.getPath(), session.getFile());
        mostrarAlerta("Resultado", resultado, Alert.AlertType.INFORMATION);
        cargarTabla();
    }

    /**
     * Maneja el evento de serialización manual de los datos del inventario.
     *
     * <p>Persiste el arreglo actual de empresas en el archivo de datos configurado
     * en la sesión y muestra el resultado de la operación mediante una alerta.</p>
     */
    @FXML
    private void serializar() {
        Session session = Session.getInstancia();
        String resultado = session.getOp().Serializar(
            session.getOp().getArreglo(), session.getPath(), session.getFile()
        );
        mostrarAlerta("Serializar", resultado, Alert.AlertType.INFORMATION);
    }

    /**
     * Maneja el evento de cierre de sesión del usuario.
     *
     * <p>Elimina la empresa activa de la sesión estableciéndola como {@code null}
     * y redirige al menú principal de la aplicación.</p>
     *
     * @throws IOException si ocurre un error al cambiar la vista raíz de la aplicación.
     */
    @FXML
    private void cerrarSesion() throws IOException {
        Session.getInstancia().setEmpresaActiva(null);
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
