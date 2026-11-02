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
    import Daoo.ClientesDAO;
    import modelo.Clientes;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class ClientesDAOImpl implements ClientesDAO {

        @Override
        public boolean guardar(Clientes cliente) {

            String sql = "INSERT INTO Clientes (nombre, dni, telefono, correo) "
                       + "VALUES (?, ?, ?, ?)";

            try (Connection con = Conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getDni());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getCorreo());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println("Error al guardar cliente: " + e.getMessage());
                return false;
            }
        }

        @Override
        public List<Clientes> listar() {

            List<Clientes> lista = new ArrayList<>();
            String sql = "SELECT * FROM Clientes";

            try (Connection con = Conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(obtenerCliente(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error al listar clientes: " + e.getMessage());
            }

            return lista;
        }

        @Override
        public Clientes buscarPorId(int id) {

            String sql = "SELECT * FROM Clientes WHERE id = ?";

            try (Connection con = Conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return obtenerCliente(rs);
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error al buscar cliente: " + e.getMessage());
            }

            return null;
        }

        @Override
        public boolean actualizar(Clientes cliente) {

            String sql = "UPDATE Clientes SET nombre=?, dni=?, "
                       + "telefono=?, correo=? WHERE id=?";

            try (Connection con = Conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getDni());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getCorreo());
                ps.setInt(5, cliente.getId());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
                return false;
            }
        }

        @Override
        public boolean eliminar(int id) {

            String sql = "DELETE FROM Clientes WHERE id = ?";

            try (Connection con = Conexion.conectar();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
                return false;
            }
        }

        private Clientes obtenerCliente(ResultSet rs) throws SQLException {

            Clientes cliente = new Clientes();

            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setDni(rs.getString("dni"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setCorreo(rs.getString("correo"));

            return cliente;
        }
    }
