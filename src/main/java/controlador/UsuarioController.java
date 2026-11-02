/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */


import Daoo.UsuarioDAO;
import modelo.Usuario;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuariosDAO;

    public void setUsuariosDAO(UsuarioDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public boolean guardarUsuario(Usuario usuario) {
        if (usuario == null || usuariosDAO == null) {
            return false;
        }

        if (usuario.getUsuario() == null ||
            usuario.getUsuario().trim().isEmpty()) {
            return false;
        }

        if (usuario.getContrasena() == null ||
            usuario.getContrasena().trim().isEmpty()) {
            return false;
        }

        return usuariosDAO.guardar(usuario);
    }

    public Usuario buscarUsuario(int id) {
        if (id <= 0 || usuariosDAO == null) {
            return null;
        }

        return usuariosDAO.buscarPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        if (usuariosDAO == null) {
            return null;
        }

        return usuariosDAO.listar();
    }

    public boolean actualizarUsuario(Usuario usuario) {
        if (usuario == null || usuariosDAO == null ||
            usuario.getId() <= 0) {
            return false;
        }

        return usuariosDAO.actualizar(usuario);
    }

    public boolean eliminarUsuario(int id) {
        if (id <= 0 || usuariosDAO == null) {
            return false;
        }

        return usuariosDAO.eliminar(id);
    }

    
}
