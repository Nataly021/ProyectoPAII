/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

/**
 *
 * @author User
 */



import conexion.Conexion;
import Daoo.DetalleVentaDAO;
import modelo.DetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAOImpl implements DetalleVentaDAO {

    @Override
    public boolean guardar(DetalleVenta d) {
        String sql = "INSERT INTO DetalleVenta "
                + "(venta_id, producto_id, cantidad, precio_unitario, subtotal) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, d.getVentaId());
                ps.setInt(2, d.getProductoId());
                ps.setInt(3, d.getCantidad());
                ps.setDouble(4, d.getPrecioUnitario());
                ps.setDouble(5, d.getSubtotal());

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al guardar detalle: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<DetalleVenta> listar() {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetalleVenta";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;

            try (PreparedStatement ps = cn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(new DetalleVenta(
                            rs.getInt("id"),
                            rs.getInt("venta_id"),
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario"),
                            rs.getDouble("subtotal")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public DetalleVenta buscarPorId(int id) {
        String sql = "SELECT * FROM DetalleVenta WHERE id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return null;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new DetalleVenta(
                                rs.getInt("id"),
                                rs.getInt("venta_id"),
                                rs.getInt("producto_id"),
                                rs.getInt("cantidad"),
                                rs.getDouble("precio_unitario"),
                                rs.getDouble("subtotal")
                        );
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar detalle: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<DetalleVenta> buscarPorVenta(int ventaId) {
        List<DetalleVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetalleVenta WHERE venta_id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, ventaId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new DetalleVenta(
                                rs.getInt("id"),
                                rs.getInt("venta_id"),
                                rs.getInt("producto_id"),
                                rs.getInt("cantidad"),
                                rs.getDouble("precio_unitario"),
                                rs.getDouble("subtotal")
                        ));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar detalles: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizar(DetalleVenta d) {
        String sql = "UPDATE DetalleVenta SET venta_id=?, producto_id=?, "
                + "cantidad=?, precio_unitario=?, subtotal=? WHERE id=?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, d.getVentaId());
                ps.setInt(2, d.getProductoId());
                ps.setInt(3, d.getCantidad());
                ps.setDouble(4, d.getPrecioUnitario());
                ps.setDouble(5, d.getSubtotal());
                ps.setInt(6, d.getId());

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM DetalleVenta WHERE id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
            return false;
        }
    }

    
}
