/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */

import Daoo.DetalleVentaDAO;
import modelo.DetalleVenta;
import java.util.List;

public class DetalleVentaController {

    private DetalleVentaDAO detalleVentasDAO;

    public void setDetalleVentasDAO(DetalleVentaDAO detalleVentasDAO) {
        this.detalleVentasDAO = detalleVentasDAO;
    }

    public boolean guardarDetalle(DetalleVenta detalle) {
        if (detalle == null || detalleVentasDAO == null) {
            return false;
        }

        return detalleVentasDAO.guardar(detalle);
    }

    public DetalleVenta buscarDetalle(int id) {
        if (id <= 0 || detalleVentasDAO == null) {
            return null;
        }

        return detalleVentasDAO.buscarPorId(id);
    }

    public List<DetalleVenta> listarDetalles() {
        if (detalleVentasDAO == null) {
            return null;
        }

        return detalleVentasDAO.listar();
    }

    public boolean actualizarDetalle(DetalleVenta detalle) {
        if (detalle == null || detalleVentasDAO == null ||
            detalle.getId() <= 0) {
            return false;
        }

        return detalleVentasDAO.actualizar(detalle);
    }

    public boolean eliminarDetalle(int id) {
        if (id <= 0 || detalleVentasDAO == null) {
            return false;
        }

        return detalleVentasDAO.eliminar(id);
    }

}
