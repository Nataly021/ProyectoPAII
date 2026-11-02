/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.DetalleVenta;
import java.util.List;

public interface DetalleVentaDAO {

    boolean guardar(DetalleVenta detalle);

    List<DetalleVenta> listar();

    DetalleVenta buscarPorId(int id);

    List<DetalleVenta> buscarPorVenta(int ventaId);

    boolean actualizar(DetalleVenta detalle);

    boolean eliminar(int id);
}
   

