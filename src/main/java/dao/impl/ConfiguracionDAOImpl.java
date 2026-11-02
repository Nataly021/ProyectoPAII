package dao.impl;

import conexion.Conexion;
import Daoo.ConfiguracionDAO;
import modelo.ConfiguracionSistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDAOImpl implements ConfiguracionDAO {

    @Override
    public boolean guardar(ConfiguracionSistema c) {
        String sql = "INSERT INTO Configuracion (nombreOptica, rtn, telefono, direccion, correo, "
                   + "moneda, cashbox, impuesto, administrador, mensajeFactura) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                llenar(ps, c);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar configuración: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ConfiguracionSistema> listar() {
        List<ConfiguracionSistema> lista = new ArrayList<>();
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return lista;
            try (PreparedStatement ps = cn.prepareStatement("SELECT * FROM Configuracion ORDER BY id");
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ConfiguracionSistema c = new ConfiguracionSistema();
                    c.setId(rs.getInt("id"));
                    c.setNombreOptica(rs.getString("nombreOptica"));
                    c.setRtn(rs.getString("rtn"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setCorreo(rs.getString("correo"));
                    c.setMoneda(rs.getString("moneda"));
                    c.setCashbox(rs.getString("cashbox"));
                    c.setImpuesto(rs.getDouble("impuesto"));
                    c.setAdministrador(rs.getString("administrador"));
                    c.setMensajeFactura(rs.getString("mensajeFactura"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar configuración: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(ConfiguracionSistema c) {
        String sql = "UPDATE Configuracion SET nombreOptica=?, rtn=?, telefono=?, direccion=?, "
                   + "correo=?, moneda=?, cashbox=?, impuesto=?, administrador=?, mensajeFactura=? "
                   + "WHERE id=?";
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                llenar(ps, c);
                ps.setInt(11, c.getId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar configuración: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        try (Connection cn = Conexion.conectar()) {
            if (cn == null) return false;
            try (PreparedStatement ps = cn.prepareStatement("DELETE FROM Configuracion WHERE id = ?")) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar configuración: " + e.getMessage());
            return false;
        }
    }

    private void llenar(PreparedStatement ps, ConfiguracionSistema c) throws SQLException {
        ps.setString(1, c.getNombreOptica());
        ps.setString(2, c.getRtn());
        ps.setString(3, c.getTelefono());
        ps.setString(4, c.getDireccion());
        ps.setString(5, c.getCorreo());
        ps.setString(6, c.getMoneda());
        ps.setString(7, c.getCashbox());
        ps.setDouble(8, c.getImpuesto());
        ps.setString(9, c.getAdministrador());
        ps.setString(10, c.getMensajeFactura());
    }
}