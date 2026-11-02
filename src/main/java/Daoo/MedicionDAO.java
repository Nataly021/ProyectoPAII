/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daoo;

/**
 *
 * @author User
 */



import modelo.Medicion;
import java.util.List;

public interface MedicionDAO {

    boolean guardar(Medicion medicion);

    List<Medicion> listar();

    Medicion buscarPorId(int id);

    List<Medicion> buscarPorCliente(int clienteId);

    boolean actualizar(Medicion medicion);

    boolean eliminar(int id);
}
