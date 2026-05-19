package com.alvarosaray.Servicio;

import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Model.Fertilizante;
import com.alvarosaray.Model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para las operaciones CRUD de {@link ImplementacionOperacion}.
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
@DisplayName("Pruebas de Operaciones CRUD")
class OperacionCrudTest {

    private ImplementacionOperacion op;
    private EmpresaAgricola empresa;
    private Fertilizante fertilizante;

    @BeforeEach
    void setUp() {
        op = new ImplementacionOperacion();

        Usuario usuario = new Usuario("test@gmail.com", "123456", "Alvaro", 123456);
        usuario.setId("U1");

        empresa = new EmpresaAgricola("Empresa Test", "Bogota", "2000", usuario);
        empresa.setId("EA1");

        fertilizante = new Fertilizante("Organico", "FertiTest", "18/04/2026", 10);
        fertilizante.setId("F1");
    }

    // ─────────────────────────────────────────────────────────────
    // crear
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Crear fertilizante lo agrega a la lista")
    void testCrear_agregaFertilizante() {
        op.crear(empresa, fertilizante);
        assertEquals(1, empresa.getFertilizante().size());
    }

    @Test
    @DisplayName("Crear fertilizante retorna mensaje de exito")
    void testCrear_retornaMensajeExito() {
        String resultado = op.crear(empresa, fertilizante);
        assertTrue(resultado.contains("agregado correctamente"));
    }

    // ─────────────────────────────────────────────────────────────
    // listarTodos
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Listar todos retorna lista vacia si no hay fertilizantes")
    void testListarTodos_listaVacia() {
        ArrayList<Fertilizante> lista = op.listarTodos(empresa);
        assertTrue(lista.isEmpty());
    }

    @Test
    @DisplayName("Listar todos retorna el fertilizante agregado")
    void testListarTodos_conFertilizante() {
        op.crear(empresa, fertilizante);
        ArrayList<Fertilizante> lista = op.listarTodos(empresa);
        assertEquals(1, lista.size());
        assertEquals("FertiTest", lista.get(0).getNombre());
    }

    // ─────────────────────────────────────────────────────────────
    // listarUno
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Listar uno por ID existente retorna el fertilizante")
    void testListarUno_encontrado() {
        op.crear(empresa, fertilizante);
        Fertilizante encontrado = op.listarUno(empresa, "F1");
        assertNotNull(encontrado);
        assertEquals("FertiTest", encontrado.getNombre());
    }

    @Test
    @DisplayName("Listar uno por ID inexistente retorna null")
    void testListarUno_noEncontrado() {
        Fertilizante encontrado = op.listarUno(empresa, "F99");
        assertNull(encontrado);
    }

    // ─────────────────────────────────────────────────────────────
    // modificar
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Modificar fertilizante existente actualiza los datos")
    void testModificar_exitoso() {
        op.crear(empresa, fertilizante);

        Fertilizante editado = new Fertilizante("Quimico", "FertiEditado", "20/04/2026", 20);
        editado.setId("F1");

        String resultado = op.modificar(empresa, "F1", editado);
        assertTrue(resultado.contains("modificado correctamente"));
        assertEquals("FertiEditado", op.listarUno(empresa, "F1").getNombre());
    }

    @Test
    @DisplayName("Modificar fertilizante inexistente retorna mensaje de error")
    void testModificar_noEncontrado() {
        Fertilizante editado = new Fertilizante("Quimico", "FertiEditado", "20/04/2026", 20);
        editado.setId("F99");

        String resultado = op.modificar(empresa, "F99", editado);
        assertTrue(resultado.contains("No se encontro"));
    }

    // ─────────────────────────────────────────────────────────────
    // eliminar
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Eliminar fertilizante existente lo quita de la lista")
    void testEliminar_exitoso() {
        op.crear(empresa, fertilizante);
        String resultado = op.eliminar(empresa, "F1");
        assertTrue(resultado.contains("eliminado correctamente"));
        assertEquals(0, empresa.getFertilizante().size());
    }

    @Test
    @DisplayName("Eliminar fertilizante inexistente retorna mensaje de error")
    void testEliminar_noEncontrado() {
        String resultado = op.eliminar(empresa, "F99");
        assertTrue(resultado.contains("No se encontro"));
    }

    // ─────────────────────────────────────────────────────────────
    // iniciarSesion
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Inicio de sesion con credenciales correctas retorna empresa")
    void testIniciarSesion_correcto() {
        op.getArreglo().add(empresa);
        EmpresaAgricola resultado = op.iniciarSesion(op.getArreglo(), "test@gmail.com", "123456");
        assertNotNull(resultado);
        assertEquals("Empresa Test", resultado.getNombre());
    }

    @Test
    @DisplayName("Inicio de sesion con correo incorrecto retorna null")
    void testIniciarSesion_correoIncorrecto() {
        op.getArreglo().add(empresa);
        EmpresaAgricola resultado = op.iniciarSesion(op.getArreglo(), "mal@gmail.com", "123456");
        assertNull(resultado);
    }

    @Test
    @DisplayName("Inicio de sesion con contrasena incorrecta retorna null")
    void testIniciarSesion_contrasenaIncorrecta() {
        op.getArreglo().add(empresa);
        EmpresaAgricola resultado = op.iniciarSesion(op.getArreglo(), "test@gmail.com", "wrongpass");
        assertNull(resultado);
    }
}
