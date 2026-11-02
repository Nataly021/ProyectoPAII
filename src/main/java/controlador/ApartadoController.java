/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */


import Daoo.ApartadoDAO;
import modelo.Apartado;
import java.util.List;

public class ApartadoController {

    private ApartadoDAO apartadosDAO;

    public void setApartadosDAO(ApartadoDAO apartadosDAO) {
        this.apartadosDAO = apartadosDAO;
    }

    public boolean guardarApartado(Apartado apartado) {
        if (apartado == null || apartadosDAO == null) {
            return false;
        }

        return apartadosDAO.guardar(apartado);
    }

    public Apartado buscarApartado(int id) {
        if (id <= 0 || apartadosDAO == null) {
            return null;
        }

        return apartadosDAO.buscarPorId(id);
    }

    public List<Apartado> listarApartados() {
        if (apartadosDAO == null) {
            return null;
        }

        return apartadosDAO.listar();
    }

    public boolean actualizarApartado(Apartado apartado) {
        if (apartado == null || apartadosDAO == null ||
            apartado.getId() <= 0) {
            return false;
        }

        return apartadosDAO.actualizar(apartado);
    }

    public boolean eliminarApartado(int id) {
        if (id <= 0 || apartadosDAO == null) {
            return false;
        }

        return apartadosDAO.eliminar(id);
    }

    
}
