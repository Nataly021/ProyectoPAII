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
import Daoo.MedicionDAO;
import modelo.Medicion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicionDAOImpl implements MedicionDAO {

    @Override
    public boolean guardar(Medicion m) {

        String sql = "INSERT INTO Mediciones "
                + "(clienteId, fecha, esferaOD, esferaOI, "
                + "cilindroOD, cilindroOI, ejeOD, ejeOI, "
                + "adicionOD, adicionOI, tipoMedicion, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getIdCliente());
            ps.setString(2, m.getFechaMedicion());
            ps.setString(3, m.getEsferaOD());
            ps.setString(4, m.getEsferaOI());
            ps.setString(5, m.getCilindroOD());
            ps.setString(6, m.getCilindroOI());
            ps.setString(7, m.getEjeOD());
            ps.setString(8, m.getEjeOI());
            ps.setString(9, m.getAdicionOD());
            ps.setString(10, m.getAdicionOI());
            ps.setString(11, m.getTipoMedicion());
            ps.setString(12, m.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar medición: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Medicion buscarPorId(int id) {

        String sql = "SELECT * FROM Mediciones WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return obtenerMedicion(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar medición: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Medicion> listar() {

        List<Medicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Mediciones";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(obtenerMedicion(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mediciones: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Medicion> buscarPorCliente(int clienteId) {

        List<Medicion> lista = new ArrayList<>();

        String sql = "SELECT * FROM Mediciones WHERE clienteId = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(obtenerMedicion(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar mediciones por cliente: "
                    + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizar(Medicion m) {

        String sql = "UPDATE Mediciones SET "
                + "clienteId=?, fecha=?, esferaOD=?, esferaOI=?, "
                + "cilindroOD=?, cilindroOI=?, ejeOD=?, ejeOI=?, "
                + "adicionOD=?, adicionOI=?, tipoMedicion=?, estado=? "
                + "WHERE id=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getIdCliente());
            ps.setString(2, m.getFechaMedicion());
            ps.setString(3, m.getEsferaOD());
            ps.setString(4, m.getEsferaOI());
            ps.setString(5, m.getCilindroOD());
            ps.setString(6, m.getCilindroOI());
            ps.setString(7, m.getEjeOD());
            ps.setString(8, m.getEjeOI());
            ps.setString(9, m.getAdicionOD());
            ps.setString(10, m.getAdicionOI());
            ps.setString(11, m.getTipoMedicion());
            ps.setString(12, m.getEstado());
            ps.setInt(13, m.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar medición: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM Mediciones WHERE id = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar medición: " + e.getMessage());
            return false;
        }
    }

    private Medicion obtenerMedicion(ResultSet rs) throws SQLException {

        Medicion m = new Medicion();

        m.setId(rs.getInt("id"));
        m.setIdCliente(rs.getInt("clienteId"));
        m.setFechaMedicion(rs.getString("fecha"));

        m.setEsferaOD(rs.getString("esferaOD"));
        m.setEsferaOI(rs.getString("esferaOI"));

        m.setCilindroOD(rs.getString("cilindroOD"));
        m.setCilindroOI(rs.getString("cilindroOI"));

        m.setEjeOD(rs.getString("ejeOD"));
        m.setEjeOI(rs.getString("ejeOI"));

        m.setAdicionOD(rs.getString("adicionOD"));
        m.setAdicionOI(rs.getString("adicionOI"));

        m.setTipoMedicion(rs.getString("tipoMedicion"));
        m.setEstado(rs.getString("estado"));

        return m;
    }
}
