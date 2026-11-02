/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User
 */



public class Medicion {

    private int id;
    private int idCliente;
    private String fechaMedicion;

    // Datos del ojo derecho (OD)
    private String esferaOD;
    private String cilindroOD;
    private String ejeOD;
    private String adicionOD;

    // Datos del ojo izquierdo (OI)
    private String esferaOI;
    private String cilindroOI;
    private String ejeOI;
    private String adicionOI;

    private String tipoMedicion;
    private String estado;

    public Medicion() {
    }

    public Medicion(int id, int idCliente, String fechaMedicion,
            String esferaOD, String cilindroOD, String ejeOD, String adicionOD,
            String esferaOI, String cilindroOI, String ejeOI, String adicionOI,
            String tipoMedicion, String estado) {

        this.id = id;
        this.idCliente = idCliente;
        this.fechaMedicion = fechaMedicion;

        this.esferaOD = esferaOD;
        this.cilindroOD = cilindroOD;
        this.ejeOD = ejeOD;
        this.adicionOD = adicionOD;

        this.esferaOI = esferaOI;
        this.cilindroOI = cilindroOI;
        this.ejeOI = ejeOI;
        this.adicionOI = adicionOI;

        this.tipoMedicion = tipoMedicion;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFechaMedicion() { return fechaMedicion; }
    public void setFechaMedicion(String fechaMedicion) { this.fechaMedicion = fechaMedicion; }

    public String getEsferaOD() { return esferaOD; }
    public void setEsferaOD(String esferaOD) { this.esferaOD = esferaOD; }

    public String getCilindroOD() { return cilindroOD; }
    public void setCilindroOD(String cilindroOD) { this.cilindroOD = cilindroOD; }

    public String getEjeOD() { return ejeOD; }
    public void setEjeOD(String ejeOD) { this.ejeOD = ejeOD; }

    public String getAdicionOD() { return adicionOD; }
    public void setAdicionOD(String adicionOD) { this.adicionOD = adicionOD; }

    public String getEsferaOI() { return esferaOI; }
    public void setEsferaOI(String esferaOI) { this.esferaOI = esferaOI; }

    public String getCilindroOI() { return cilindroOI; }
    public void setCilindroOI(String cilindroOI) { this.cilindroOI = cilindroOI; }

    public String getEjeOI() { return ejeOI; }
    public void setEjeOI(String ejeOI) { this.ejeOI = ejeOI; }

    public String getAdicionOI() { return adicionOI; }
    public void setAdicionOI(String adicionOI) { this.adicionOI = adicionOI; }

    public String getTipoMedicion() { return tipoMedicion; }
    public void setTipoMedicion(String tipoMedicion) { this.tipoMedicion = tipoMedicion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}