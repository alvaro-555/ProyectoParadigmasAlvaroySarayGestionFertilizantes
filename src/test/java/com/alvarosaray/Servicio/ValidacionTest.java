package com.alvarosaray.Servicio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para los metodos de validacion de {@link ImplementacionOperacion}.
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
@DisplayName("Pruebas de Validacion")
class ValidacionTest {

    private ImplementacionOperacion op;

    @BeforeEach
    void setUp() {
        op = new ImplementacionOperacion();
    }

    // ─────────────────────────────────────────────────────────────
    // validarTexto
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Texto valido retorna true")
    void testValidarTexto_valido() {
        assertTrue(op.validarTexto("Empresa ABC"));
    }

    @Test
    @DisplayName("Texto vacio retorna false")
    void testValidarTexto_vacio() {
        assertFalse(op.validarTexto(""));
    }

    @Test
    @DisplayName("Texto con solo espacios retorna false")
    void testValidarTexto_soloEspacios() {
        assertFalse(op.validarTexto("   "));
    }

    @Test
    @DisplayName("Texto nulo retorna false")
    void testValidarTexto_nulo() {
        assertFalse(op.validarTexto(null));
    }

    // ─────────────────────────────────────────────────────────────
    // validarCorreo
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Correo valido retorna true")
    void testValidarCorreo_valido() {
        assertTrue(op.validarCorreo("usuario@gmail.com"));
    }

    @Test
    @DisplayName("Correo sin arroba retorna false")
    void testValidarCorreo_sinArroba() {
        assertFalse(op.validarCorreo("usuariogmail.com"));
    }

    @Test
    @DisplayName("Correo sin punto retorna false")
    void testValidarCorreo_sinPunto() {
        assertFalse(op.validarCorreo("usuario@gmailcom"));
    }

    @Test
    @DisplayName("Correo nulo retorna false")
    void testValidarCorreo_nulo() {
        assertFalse(op.validarCorreo(null));
    }

    @Test
    @DisplayName("Correo vacio retorna false")
    void testValidarCorreo_vacio() {
        assertFalse(op.validarCorreo(""));
    }

    // ─────────────────────────────────────────────────────────────
    // validarContrasena
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Contrasena de 6 caracteres retorna true")
    void testValidarContrasena_exactamente6() {
        assertTrue(op.validarContrasena("abc123"));
    }

    @Test
    @DisplayName("Contrasena larga retorna true")
    void testValidarContrasena_larga() {
        assertTrue(op.validarContrasena("contrasena segura 2026"));
    }

    @Test
    @DisplayName("Contrasena de 5 caracteres retorna false")
    void testValidarContrasena_menosDe6() {
        assertFalse(op.validarContrasena("abc1"));
    }

    @Test
    @DisplayName("Contrasena vacia retorna false")
    void testValidarContrasena_vacia() {
        assertFalse(op.validarContrasena(""));
    }

    @Test
    @DisplayName("Contrasena nula retorna false")
    void testValidarContrasena_nula() {
        assertFalse(op.validarContrasena(null));
    }

    // ─────────────────────────────────────────────────────────────
    // validarNumeroEntero
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Numero entero valido retorna true")
    void testValidarNumeroEntero_valido() {
        assertTrue(op.validarNumeroEntero("12345678"));
    }

    @Test
    @DisplayName("Numero con letras retorna false")
    void testValidarNumeroEntero_conLetras() {
        assertFalse(op.validarNumeroEntero("123abc"));
    }

    @Test
    @DisplayName("Numero vacio retorna false")
    void testValidarNumeroEntero_vacio() {
        assertFalse(op.validarNumeroEntero(""));
    }

    @Test
    @DisplayName("Numero decimal retorna false")
    void testValidarNumeroEntero_decimal() {
        assertFalse(op.validarNumeroEntero("12.5"));
    }

    // ─────────────────────────────────────────────────────────────
    // validarAniofundacion
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Año dentro del rango retorna true")
    void testValidarAnio_valido() {
        assertTrue(op.validarAniofundacion("1990"));
    }

    @Test
    @DisplayName("Año limite inferior 1900 retorna true")
    void testValidarAnio_limiteInferior() {
        assertTrue(op.validarAniofundacion("1900"));
    }

    @Test
    @DisplayName("Año limite superior 2025 retorna true")
    void testValidarAnio_limiteSuperior() {
        assertTrue(op.validarAniofundacion("2025"));
    }

    @Test
    @DisplayName("Año menor a 1900 retorna false")
    void testValidarAnio_menorLimite() {
        assertFalse(op.validarAniofundacion("1899"));
    }

    @Test
    @DisplayName("Año mayor a 2025 retorna false")
    void testValidarAnio_mayorLimite() {
        assertFalse(op.validarAniofundacion("2026"));
    }

    @Test
    @DisplayName("Año con letras retorna false")
    void testValidarAnio_conLetras() {
        assertFalse(op.validarAniofundacion("dosmilaveinticinco"));
    }

    // ─────────────────────────────────────────────────────────────
    // validarCantidad
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Cantidad positiva retorna true")
    void testValidarCantidad_valida() {
        assertTrue(op.validarCantidad("10"));
    }

    @Test
    @DisplayName("Cantidad de 1 retorna true")
    void testValidarCantidad_uno() {
        assertTrue(op.validarCantidad("1"));
    }

    @Test
    @DisplayName("Cantidad cero retorna false")
    void testValidarCantidad_cero() {
        assertFalse(op.validarCantidad("0"));
    }

    @Test
    @DisplayName("Cantidad negativa retorna false")
    void testValidarCantidad_negativa() {
        assertFalse(op.validarCantidad("-5"));
    }

    @Test
    @DisplayName("Cantidad con letras retorna false")
    void testValidarCantidad_conLetras() {
        assertFalse(op.validarCantidad("diez"));
    }

    // ─────────────────────────────────────────────────────────────
    // validarFecha
    // ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Fecha en formato DD/MM/AAAA retorna true")
    void testValidarFecha_valida() {
        assertTrue(op.validarFecha("18/04/2026"));
    }

    @Test
    @DisplayName("Fecha con formato incorrecto retorna false")
    void testValidarFecha_formatoIncorrecto() {
        assertFalse(op.validarFecha("2026-04-18"));
    }

    @Test
    @DisplayName("Fecha vacia retorna false")
    void testValidarFecha_vacia() {
        assertFalse(op.validarFecha(""));
    }

    @Test
    @DisplayName("Fecha nula retorna false")
    void testValidarFecha_nula() {
        assertFalse(op.validarFecha(null));
    }

    @Test
    @DisplayName("Fecha con texto retorna false")
    void testValidarFecha_conTexto() {
        assertFalse(op.validarFecha("hoy/abril/2026"));
    }
}
