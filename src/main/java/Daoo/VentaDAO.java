/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.Venta;
import java.util.List;


public interface VentaDAO {

    boolean guardar(Venta venta);

    List<Venta> listar();

    Venta buscarPorId(int id);

    List<Venta> buscarPorCliente(int clienteId);

    boolean actualizar(Venta venta);

    boolean eliminar(int id);
}

