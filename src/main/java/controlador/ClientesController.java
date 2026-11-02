/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import Daoo.ClientesDAO;
import modelo.Clientes;
import java.util.List;

/**
 *
 * @author User
 */


public class ClientesController {

    private final ClientesDAO clientesDAO;

    public ClientesController(ClientesDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    public boolean guardarCliente(Clientes clientes) {

        if (clientes == null) {
            return false;
        }

        if (clientes.getNombre() == null ||
            clientes.getNombre().trim().isEmpty()) {
            return false;
        }

        if (clientes.getDni() == null ||
            clientes.getDni().trim().isEmpty()) {
            return false;
        }

        return clientesDAO.guardar(clientes);
    }

    public Clientes buscarClientes(int id) {

        if (id <= 0) {
            return null;
        }

        return clientesDAO.buscarPorId(id);
    }

    public List<Clientes> listarClientes() {
        return clientesDAO.listar();
    }

    public boolean actualizarCliente(Clientes clientes) {

        if (clientes == null || clientes.getId() <= 0) {
            return false;
        }

        return clientesDAO.actualizar(clientes);
    }

    public boolean eliminarClientes(int id) {

        if (id <= 0) {
            return false;
        }

        return clientesDAO.eliminar(id);
    }
}