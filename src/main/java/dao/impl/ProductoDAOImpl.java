package dao.impl;

import conexion.Conexion;
import Daoo.ProductoDAO;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public boolean guardar(Producto p) {
        String sql = "INSERT INTO Productos (nombre, tipo, marca, material, forma, precio, stock) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getTipo());
                ps.setString(3, p.getMarca());
                ps.setString(4, p.getMaterial());
                ps.setString(5, p.getForma());
                ps.setDouble(6, p.getPrecio());
                ps.setInt(7, p.getStock());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Producto> listar() {
        return consultar("SELECT * FROM Productos ORDER BY id DESC", null);
    }

    @Override
    public Producto buscarPorId(int id) {
        List<Producto> r = consultar("SELECT * FROM Productos WHERE id = ?", String.valueOf(id));
        return r.isEmpty() ? null : r.get(0);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return consultar("SELECT * FROM Productos WHERE nombre LIKE ?", "%" + nombre + "%");
    }

    @Override
    public List<Producto> listarStockBajo() {
        return consultar("SELECT * FROM Productos WHERE stock <= 5 ORDER BY stock", null);
    }

    @Override
    public boolean actualizar(Producto p) {
        String sql = "UPDATE Productos SET nombre=?, tipo=?, marca=?, material=?, forma=?, "
                   + "precio=?, stock=? WHERE id=?";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, p.getNombre());
                ps.setString(2, p.getTipo());
                ps.setString(3, p.getMarca());
                ps.setString(4, p.getMaterial());
                ps.setString(5, p.getForma());
                ps.setDouble(6, p.getPrecio());
                ps.setInt(7, p.getStock());
                ps.setInt(8, p.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Productos WHERE id = ?")) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // Ejecuta un SELECT con 0 o 1 parámetro (texto o número como String)
    private List<Producto> consultar(String sql, String param) {
        List<Producto> lista = new ArrayList<>();
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                if (param != null) {
                    if (sql.contains("id = ?")) ps.setInt(1, Integer.parseInt(param));
                    else ps.setString(1, param);
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        lista.add(new Producto(
                            rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"),
                            rs.getString("marca"), rs.getString("material"),
                            rs.getString("forma"), rs.getDouble("precio"), rs.getInt("stock")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar productos: " + e.getMessage());
        }
        return lista;
    }
}