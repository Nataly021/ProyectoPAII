/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */


import modelo.Producto;
import java.util.List;

public interface ProductoDAO {

    boolean guardar(Producto producto);

    List<Producto> listar();

    Producto buscarPorId(int id);

    boolean actualizar(Producto producto);

    boolean eliminar(int id);

    List<Producto> buscarPorNombre(String nombre);

    List<Producto> listarStockBajo();

    
}
