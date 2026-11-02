/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {

    boolean guardar(Usuario usuario);

    List<Usuario> listar();

    Usuario buscarPorId(int id);

    Usuario buscarPorUsuario(String usuario);

    boolean actualizar(Usuario usuario);

    boolean eliminar(int id);
}
    

