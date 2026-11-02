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
import Daoo.VentaDAO;
import modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {


    public boolean guardar(Venta v) {
        String sql = "INSERT INTO Ventas "
                + "(cliente_id, medicion_id, fecha, subtotal, impuesto, total) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, v.getClienteId());
                ps.setInt(2, v.getMedicionId());
                ps.setTimestamp(3, v.getFecha());
                ps.setDouble(4, v.getSubtotal());
                ps.setDouble(5, v.getImpuesto());
                ps.setDouble(6, v.getTotal());

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
            return false;
        }
    }

  
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;

            try (PreparedStatement ps = cn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(new Venta(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getInt("medicion_id"),
                            rs.getTimestamp("fecha"),
                            rs.getDouble("subtotal"),
                            rs.getDouble("impuesto"),
                            rs.getDouble("total")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }

        return lista;
    }

  
    public Venta buscarPorId(int id) {
        String sql = "SELECT * FROM Ventas WHERE id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return null;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Venta(
                                rs.getInt("id"),
                                rs.getInt("cliente_id"),
                                rs.getInt("medicion_id"),
                                rs.getTimestamp("fecha"),
                                rs.getDouble("subtotal"),
                                rs.getDouble("impuesto"),
                                rs.getDouble("total")
                        );
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar venta: " + e.getMessage());
        }

        return null;
    }

   
    public List<Venta> buscarPorCliente(int clienteId) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ventas WHERE cliente_id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, clienteId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Venta(
                                rs.getInt("id"),
                                rs.getInt("cliente_id"),
                                rs.getInt("medicion_id"),
                                rs.getTimestamp("fecha"),
                                rs.getDouble("subtotal"),
                                rs.getDouble("impuesto"),
                                rs.getDouble("total")
                        ));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar ventas: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Venta v) {
        String sql = "UPDATE Ventas SET cliente_id=?, medicion_id=?, "
                + "fecha=?, subtotal=?, impuesto=?, total=? WHERE id=?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, v.getClienteId());
                ps.setInt(2, v.getMedicionId());
                ps.setTimestamp(3, v.getFecha());
                ps.setDouble(4, v.getSubtotal());
                ps.setDouble(5, v.getImpuesto());
                ps.setDouble(6, v.getTotal());
                ps.setInt(7, v.getId());

                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
            return false;
        }
    }

   
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Ventas WHERE id = ?";

        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
            return false;
        }
    }

    
}
