package modelo;

import java.sql.Date;

public class Apartado {
    private int id;
    private int clienteId;
    private int productoId;
    private Date fecha;
    private double precioTotal;
    private double abonoInicial;
    private double saldoPendiente;
    private Date fechaLimite;
    private String estado;
    // Solo para mostrar en la tabla (vienen del JOIN)
    private String clienteNombre;
    private String productoNombre;

    public Apartado() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
    public double getAbonoInicial() { return abonoInicial; }
    public void setAbonoInicial(double abonoInicial) { this.abonoInicial = abonoInicial; }
    public double getSaldoPendiente() { return saldoPendiente; }
    public void setSaldoPendiente(double saldoPendiente) { this.saldoPendiente = saldoPendiente; }
    public Date getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(Date fechaLimite) { this.fechaLimite = fechaLimite; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
}