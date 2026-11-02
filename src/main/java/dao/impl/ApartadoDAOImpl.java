package dao.impl;

import conexion.Conexion;
import Daoo.ApartadoDAO;
import modelo.Apartado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApartadoDAOImpl implements ApartadoDAO {

    private static final String SELECT_BASE =
        "SELECT a.*, c.nombre AS clienteNombre, p.nombre AS productoNombre "
      + "FROM Apartados a "
      + "JOIN Clientes c ON a.clienteId = c.id "
      + "JOIN Productos p ON a.productoId = p.id ";

    @Override
    public boolean guardar(Apartado a) {
        String sql = "INSERT INTO Apartados (clienteId, productoId, fechaApartado, "
                + "precioTotal, abonoInicial, saldoPendiente, fechaLimitePago, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, a.getClienteId());
                ps.setInt(2, a.getProductoId());
                ps.setDate(3, a.getFecha());
                ps.setDouble(4, a.getPrecioTotal());
                ps.setDouble(5, a.getAbonoInicial());
                ps.setDouble(6, a.getSaldoPendiente());
                ps.setDate(7, a.getFechaLimite());
                ps.setString(8, a.getEstado());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al guardar apartado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Apartado> listar() {
        List<Apartado> lista = new ArrayList<>();
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;
            try (PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "ORDER BY a.id DESC");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (Exception e) {
            System.out.println("Error al listar apartados: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Apartado buscarPorId(int id) {
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return null;
            try (PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "WHERE a.id = ?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return mapear(rs);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar apartado: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Apartado> buscarPorCliente(int clienteId) {
        List<Apartado> lista = new ArrayList<>();
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;
            try (PreparedStatement ps = cn.prepareStatement(SELECT_BASE + "WHERE a.clienteId = ?")) {
                ps.setInt(1, clienteId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) lista.add(mapear(rs));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar apartados: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Apartado a) {
        String sql = "UPDATE Apartados SET clienteId=?, productoId=?, fechaApartado=?, "
                + "precioTotal=?, abonoInicial=?, saldoPendiente=?, fechaLimitePago=?, estado=? "
                + "WHERE id=?";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, a.getClienteId());
                ps.setInt(2, a.getProductoId());
                ps.setDate(3, a.getFecha());
                ps.setDouble(4, a.getPrecioTotal());
                ps.setDouble(5, a.getAbonoInicial());
                ps.setDouble(6, a.getSaldoPendiente());
                ps.setDate(7, a.getFechaLimite());
                ps.setString(8, a.getEstado());
                ps.setInt(9, a.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar apartado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Apartados WHERE id = ?")) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar apartado: " + e.getMessage());
            return false;
        }
    }

    private Apartado mapear(ResultSet rs) throws java.sql.SQLException {
        Apartado a = new Apartado();
        a.setId(rs.getInt("id"));
        a.setClienteId(rs.getInt("clienteId"));
        a.setProductoId(rs.getInt("productoId"));
        a.setFecha(rs.getDate("fechaApartado"));
        a.setPrecioTotal(rs.getDouble("precioTotal"));
        a.setAbonoInicial(rs.getDouble("abonoInicial"));
        a.setSaldoPendiente(rs.getDouble("saldoPendiente"));
        a.setFechaLimite(rs.getDate("fechaLimitePago"));
        a.setEstado(rs.getString("estado"));
        a.setClienteNombre(rs.getString("clienteNombre"));
        a.setProductoNombre(rs.getString("productoNombre"));
        return a;
    }
}