package modelo;

public class ConfiguracionSistema {
    private int id;
    private String nombreOptica, rtn, telefono, direccion, correo;
    private String moneda, cashbox, administrador, mensajeFactura;
    private double impuesto;

    public ConfiguracionSistema() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreOptica() { return nombreOptica; }
    public void setNombreOptica(String v) { this.nombreOptica = v; }
    public String getRtn() { return rtn; }
    public void setRtn(String v) { this.rtn = v; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String v) { this.telefono = v; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String v) { this.direccion = v; }
    public String getCorreo() { return correo; }
    public void setCorreo(String v) { this.correo = v; }
    public String getMoneda() { return moneda; }
    public void setMoneda(String v) { this.moneda = v; }
    public String getCashbox() { return cashbox; }
    public void setCashbox(String v) { this.cashbox = v; }
    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double v) { this.impuesto = v; }
    public String getAdministrador() { return administrador; }
    public void setAdministrador(String v) { this.administrador = v; }
    public String getMensajeFactura() { return mensajeFactura; }
    public void setMensajeFactura(String v) { this.mensajeFactura = v; }
}