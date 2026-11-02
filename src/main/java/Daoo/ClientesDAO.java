/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.Clientes;
import java.util.List;

public interface ClientesDAO {

    boolean guardar(Clientes cliente);

    List<Clientes> listar();

    Clientes buscarPorId(int id);

    boolean actualizar(Clientes cliente);

    boolean eliminar(int id);
}
    

