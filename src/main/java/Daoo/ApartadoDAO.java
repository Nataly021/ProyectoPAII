/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.Apartado;
import java.util.List;

public interface ApartadoDAO {

    boolean guardar(Apartado apartado);

    List<Apartado> listar();

    Apartado buscarPorId(int id);

    List<Apartado> buscarPorCliente(int clienteId);

    boolean actualizar(Apartado apartado);

    boolean eliminar(int id);

    
}
