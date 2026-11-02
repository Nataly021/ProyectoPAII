/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */


import Daoo.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class ProductoController {

    private ProductoDAO productosDAO;

    public void setProductosDAO(ProductoDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    public boolean guardarProducto(Producto producto) {
        if (producto == null || productosDAO == null) {
            return false;
        }

        if (producto.getNombre() == null ||
            producto.getNombre().trim().isEmpty()) {
            return false;
        }

        return productosDAO.guardar(producto);
    }

    public Producto buscarProducto(int id) {
        if (id <= 0 || productosDAO == null) {
            return null;
        }

        return productosDAO.buscarPorId(id);
    }

    public List<Producto> listarProductos() {
        if (productosDAO == null) {
            return null;
        }

        return productosDAO.listar();
    }

    public boolean actualizarProducto(Producto producto) {
        if (producto == null || productosDAO == null ||
            producto.getId() <= 0) {
            return false;
        }

        return productosDAO.actualizar(producto);
    }

    public boolean eliminarProducto(int id) {
        if (id <= 0 || productosDAO == null) {
            return false;
        }

        return productosDAO.eliminar(id);
    }

    
}
