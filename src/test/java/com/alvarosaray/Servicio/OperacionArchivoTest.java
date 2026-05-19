package com.alvarosaray.Servicio;

import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Model.Fertilizante;
import com.alvarosaray.Model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para las operaciones de archivo (serializacion y deserializacion)
 * de {@link ImplementacionOperacion}.
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
@DisplayName("Pruebas de Operaciones de Archivo")
class OperacionArchivoTest {

    private ImplementacionOperacion op;
    private ArrayList<EmpresaAgricola> arreglo;

    private static final String PATH_TEST = "DatosTest";
    private static final String FILE_TEST = "test.dat";

    @BeforeEach
    void setUp() {
        op = new ImplementacionOperacion();
        new File(PATH_TEST).mkdirs();

        Usuario usuario = new Usuario("test@gmail.com", "123456", "Alvaro", 123456);
        usuario.setId("U1");

        EmpresaAgricola empresa = new EmpresaAgricola("Empresa Test", "Bogota", "2000", usuario);
        empresa.setId("EA1");

        Fertilizante fertilizante = new Fertilizante("Organico", "FertiTest", "18/04/2026", 10);
        fertilizante.setId("F1");
        empresa.getFertilizante().add(fertilizante);

        arreglo = new ArrayList<>();
        arreglo.add(empresa);
    }

    @AfterEach
    void tearDown() {
        // Elimina el archivo y carpeta de prueba al terminar
        File archivo = new File(PATH_TEST + "/" + FILE_TEST);
        if (archivo.exists()) archivo.delete();
        new File(PATH_TEST).delete();
    }

    // ─────────────────────────────────────────────────────────────
    // Serializar
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Serializar crea el archivo en disco")
    void testSerializar_creaArchivo() {
        op.Serializar(arreglo, PATH_TEST, FILE_TEST);
        File archivo = new File(PATH_TEST + "/" + FILE_TEST);
        assertTrue(archivo.exists());
    }

    @Test
    @DisplayName("Serializar retorna mensaje de exito")
    void testSerializar_retornaMensajeExito() {
        String resultado = op.Serializar(arreglo, PATH_TEST, FILE_TEST);
        assertTrue(resultado.contains("guardados correctamente"));
    }

    @Test
    @DisplayName("Serializar en ruta invalida retorna mensaje de error")
    void testSerializar_rutaInvalida() {
        String resultado = op.Serializar(arreglo, "RutaQueNoExiste/Subcarpeta", FILE_TEST);
        assertTrue(resultado.contains("Error al guardar"));
    }

    // ─────────────────────────────────────────────────────────────
    // Deserializacion
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Deserializar recupera los mismos datos que se guardaron")
    void testDeserializacion_recuperaDatos() {
        op.Serializar(arreglo, PATH_TEST, FILE_TEST);
        ArrayList<EmpresaAgricola> recuperado = op.deserializacion(PATH_TEST, FILE_TEST);

        assertFalse(recuperado.isEmpty());
        assertEquals("Empresa Test", recuperado.get(0).getNombre());
        assertEquals("EA1", recuperado.get(0).getId());
    }

    @Test
    @DisplayName("Deserializar recupera los fertilizantes correctamente")
    void testDeserializacion_recuperaFertilizantes() {
        op.Serializar(arreglo, PATH_TEST, FILE_TEST);
        ArrayList<EmpresaAgricola> recuperado = op.deserializacion(PATH_TEST, FILE_TEST);

        assertEquals(1, recuperado.get(0).getFertilizante().size());
        assertEquals("FertiTest", recuperado.get(0).getFertilizante().get(0).getNombre());
    }

    @Test
    @DisplayName("Deserializar archivo inexistente retorna lista vacia")
    void testDeserializacion_archivoInexistente() {
        ArrayList<EmpresaAgricola> resultado = op.deserializacion(PATH_TEST, "noexiste.dat");
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
