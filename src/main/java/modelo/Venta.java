/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User
 */


import java.sql.Timestamp;

public class Venta {

    private int id;
    private int clienteId;
    private int medicionId;
    private Timestamp fecha;
    private double subtotal;
    private double impuesto;
    private double total;

    public Venta() {
    }

    public Venta(int id, int clienteId, int medicionId,
                 Timestamp fecha, double subtotal,
                 double impuesto, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.medicionId = medicionId;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getMedicionId() {
        return medicionId;
    }

    public void setMedicionId(int medicionId) {
        this.medicionId = medicionId;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
