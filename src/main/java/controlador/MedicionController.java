/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author User
 */



import Daoo.MedicionDAO;
import modelo.Medicion;
import java.util.List;

public class MedicionController {

    private MedicionDAO medicionesDAO;

    public void setMedicionDAO(MedicionDAO medicionesDAO) {
        this.medicionesDAO = medicionesDAO;
    }

    public boolean guardarMedicion(Medicion medicion) {
        if (medicion == null || medicionesDAO == null) {
            return false;
        }

        return medicionesDAO.guardar(medicion);
    }

    public Medicion buscarMedicion(int id) {
        if (id <= 0 || medicionesDAO == null) {
            return null;
        }

        return medicionesDAO.buscarPorId(id);
    }

    public List<Medicion> listarMediciones() {
        if (medicionesDAO == null) {
            return null;
        }

        return medicionesDAO.listar();
    }

    public boolean actualizarMedicion(Medicion medicion) {
        if (medicion == null || medicionesDAO == null
                || medicion.getId() <= 0) {
            return false;
        }

        return medicionesDAO.actualizar(medicion);
    }

    public boolean eliminarMedicion(int id) {
        if (id <= 0 || medicionesDAO == null) {
            return false;
        }

        return medicionesDAO.eliminar(id);
    }
}