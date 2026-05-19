package com.alvarosaray.Servicio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.alvarosaray.Model.EmpresaAgricola;
import com.alvarosaray.Model.Fertilizante;

/**
 * Clase publica concreta que implementa todas las operaciones del sistema:
 * CRUD de fertilizantes, serializacion/deserializacion de datos y validaciones.
 *
 * <p>Tipo: Clase publica ({@code public class}).</p>
 * <p>Visibilidad: Publica.</p>
 * <p>Implementa las interfaces:
 *   {@link OperacionArchivo},
 *   {@link OperacionCrud},
 *   {@link Validacion}.
 * </p>
 *
 * <p><b>Atributos:</b></p>
 * <ul>
 *   <li>{@code arreglo} - {@code private ArrayList<EmpresaAgricola>}:
 *       Lista de empresas agricolas cargadas en memoria.</li>
 * </ul>
 *
 * @author Alvaro Pachon
 * @author Saray Leon
 * @version 1.0
 * @since 2026-04-18
 */
public class ImplementacionOperacion implements OperacionArchivo, OperacionCrud, Validacion {

    /** Lista de empresas agricolas manejada en memoria por el sistema. */
    private ArrayList<EmpresaAgricola> arreglo;

    /**
     * Constructor por defecto. Inicializa la lista de empresas vacia.
     */
    public ImplementacionOperacion() {
        this.arreglo = new ArrayList<>();
    }

    /**
     * Constructor con parametros. Inicializa la lista con datos existentes.
     *
     * @param arreglo Lista de empresas agricolas a cargar.
     */
    public ImplementacionOperacion(ArrayList<EmpresaAgricola> arreglo) {
        this.arreglo = arreglo;
    }

    /**
     * Retorna la lista actual de empresas agricolas en memoria.
     *
     * @return Lista de objetos {@link EmpresaAgricola}.
     */
    public ArrayList<EmpresaAgricola> getArreglo() { return arreglo; }

    /**
     * Reemplaza la lista actual de empresas agricolas en memoria.
     *
     * @param arreglo Nueva lista de objetos {@link EmpresaAgricola}.
     */
    public void setArreglo(ArrayList<EmpresaAgricola> arreglo) { this.arreglo = arreglo; }

    // ─────────────────────────────────────────────────────────────
    // CRUD
    // ─────────────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Agrega el fertilizante a la lista de la empresa y retorna un mensaje de confirmacion.
     * En caso de excepcion retorna un mensaje de error con el detalle.
     */
    @Override
    public String crear(EmpresaAgricola a, Fertilizante f) {
        try {
            a.getFertilizante().add(f);
            return "Fertilizante '" + f.getNombre() + "' agregado correctamente.";
        } catch (Exception e) {
            return "Error al agregar fertilizante: " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     * Retorna directamente el ArrayList de fertilizantes de la empresa.
     */
    @Override
    public ArrayList<Fertilizante> listarTodos(EmpresaAgricola a) {
        return a.getFertilizante();
    }

    /**
     * {@inheritDoc}
     * Recorre la lista de fertilizantes de la empresa comparando el ID.
     * Retorna {@code null} si no encuentra coincidencia.
     */
    @Override
    public Fertilizante listarUno(EmpresaAgricola a, String id) {
        for (Fertilizante f : a.getFertilizante()) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * Recorre la lista de fertilizantes y reemplaza el que coincida con el ID
     * por el nuevo objeto proporcionado.
     */
    @Override
    public String modificar(EmpresaAgricola a, String id, Fertilizante f) {
        ArrayList<Fertilizante> lista = a.getFertilizante();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                lista.set(i, f);
                return "Fertilizante con id '" + id + "' modificado correctamente.";
            }
        }
        return "No se encontro el fertilizante con id '" + id + "'.";
    }

    /**
     * {@inheritDoc}
     * Recorre la lista de fertilizantes y elimina el que coincida con el ID.
     */
    @Override
    public String eliminar(EmpresaAgricola a, String id) {
        ArrayList<Fertilizante> lista = a.getFertilizante();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(id)) {
                lista.remove(i);
                return "Fertilizante con id '" + id + "' eliminado correctamente.";
            }
        }
        return "No se encontro el fertilizante con id '" + id + "'.";
    }

    // ─────────────────────────────────────────────────────────────
    // ARCHIVO
    // ─────────────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Utiliza {@link ObjectOutputStream} para escribir el objeto en un archivo binario.
     * El archivo se guarda en la ruta {@code path/name}.
     */
    @Override
    public String Serializar(ArrayList<EmpresaAgricola> empresaAgricola, String path, String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + "/" + name))) {
            oos.writeObject(empresaAgricola);
            return "Datos guardados correctamente en " + name;
        } catch (IOException e) {
            return "Error al guardar: " + e.getMessage();
        }
    }

    /**
     * {@inheritDoc}
     * Utiliza {@link ObjectInputStream} para leer el archivo binario y reconstruir
     * la lista de empresas. Si el archivo no existe o hay un error, retorna lista vacia.
     */
    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<EmpresaAgricola> deserializacion(String path, String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + "/" + name))) {
            return (ArrayList<EmpresaAgricola>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // ─────────────────────────────────────────────────────────────
    // VALIDACIONES
    // ─────────────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Verifica que el valor no sea nulo y que al quitarle espacios no quede vacio.
     */
    @Override
    public boolean validarTexto(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    /**
     * {@inheritDoc}
     * Intenta parsear el valor como entero con {@link Integer#parseInt(String)}.
     * Si lanza {@link NumberFormatException} retorna {@code false}.
     */
    @Override
    public boolean validarNumeroEntero(String valor) {
        try {
            Integer.parseInt(valor.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * Comprueba que el correo no sea nulo y contenga '@' y '.'.
     */
    @Override
    public boolean validarCorreo(String valor) {
        return valor != null && valor.contains("@") && valor.contains(".");
    }

    /**
     * {@inheritDoc}
     * Verifica que la contrasena no sea nula y tenga al menos 6 caracteres
     * despues de quitar espacios.
     */
    @Override
    public boolean validarContrasena(String valor) {
        return valor != null && valor.trim().length() >= 6;
    }

    /**
     * {@inheritDoc}
     * Parsea el valor como entero y verifica que este entre 1900 y 2025 inclusive.
     */
    @Override
    public boolean validarAniofundacion(String valor) {
        try {
            int anio = Integer.parseInt(valor.trim());
            return anio >= 1900 && anio <= 2025;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * Parsea el valor como entero y verifica que sea estrictamente mayor a cero.
     */
    @Override
    public boolean validarCantidad(String valor) {
        try {
            int cantidad = Integer.parseInt(valor.trim());
            return cantidad > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * Usa una expresion regular para verificar el patron DD/MM/AAAA.
     */
    @Override
    public boolean validarFecha(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        return valor.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    // ─────────────────────────────────────────────────────────────
    // GENERADORES DE ID
    // ─────────────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * El ID se genera como "U" seguido del numero de empresas registradas + 1.
     */
    @Override
    public String generarIdUsuario(ArrayList<EmpresaAgricola> arreglo) {
        return "U" + (arreglo.size() + 1);
    }

    /**
     * {@inheritDoc}
     * El ID se genera como "EA" seguido del numero de empresas registradas + 1.
     */
    @Override
    public String generarIdEmpresa(ArrayList<EmpresaAgricola> arreglo) {
        return "EA" + (arreglo.size() + 1);
    }

    /**
     * {@inheritDoc}
     * El ID se genera como "F" seguido del numero de fertilizantes de la empresa + 1.
     */
    @Override
    public String generarIdFertilizante(EmpresaAgricola a) {
        return "F" + (a.getFertilizante().size() + 1);
    }

    // ─────────────────────────────────────────────────────────────
    // INICIO DE SESION
    // ─────────────────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Recorre el arreglo de empresas comparando el correo y la contrasena
     * del usuario asociado a cada empresa.
     */
    @Override
    public EmpresaAgricola iniciarSesion(ArrayList<EmpresaAgricola> arreglo, String correo, String contrasena) {
        for (EmpresaAgricola empresa : arreglo) {
            if (empresa.getUsuario().getCorreo().equals(correo) &&
                empresa.getUsuario().getContrasena().equals(contrasena)) {
                return empresa;
            }
        }
        return null;
    }
}
