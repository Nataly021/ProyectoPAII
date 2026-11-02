/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */




import Daoo.VentaDAO;
import modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class VentaController {

    private VentaDAO ventasDAO;

    public void setVentaDAO(VentaDAO ventasDAO) {
        this.ventasDAO = ventasDAO;
    }

    public boolean guardarVenta(Venta venta) {
        if (venta == null || ventasDAO == null) {
            return false;
        }

        return ventasDAO.guardar(venta);
    }

    public Venta buscarVenta(int id) {
        if (id <= 0 || ventasDAO == null) {
            return null;
        }

        return ventasDAO.buscarPorId(id);
    }

    public List<Venta> listaVenta() {
        if (ventasDAO == null) {
            return new ArrayList<>();
        }

        return ventasDAO.listar();
    }

    public boolean actualizarVenta(Venta venta) {
        if (venta == null || ventasDAO == null
                || venta.getId() <= 0) {
            return false;
        }

        return ventasDAO.actualizar(venta);
    }

    public boolean eliminarVenta(int id) {
        if (id <= 0 || ventasDAO == null) {
            return false;
        }

        return ventasDAO.eliminar(id);
    }
}