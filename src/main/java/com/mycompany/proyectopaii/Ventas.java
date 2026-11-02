/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.proyectopaii;

/**
 *
 * @author susyo
 */
import controlador.ClientesController;
import dao.impl.ClientesDAOImpl;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dao.impl.MedicionDAOImpl;
import modelo.Medicion;

public class Ventas extends javax.swing.JInternalFrame {
private List<modelo.Clientes> listaClientes;
    /**
     * Creates new form Ventas
     */
    public Ventas() {
        initComponents();
        configurarTabla();
        cargarClientes();
        cargarProductos();
        configurarEventosProductos();
         // CUANDO CAMBIA EL CLIENTE, CARGAR SUS MEDICIONES
    cbclientes.addActionListener(e -> {
        cargarMedicionesCliente();
         });
        javax.swing.ImageIcon iconoConfig = new javax.swing.ImageIcon(getClass().getResource("/imagenes/lentes.png"));
    java.awt.Image imagenConfig = iconoConfig.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
    venta.setIcon(new javax.swing.ImageIcon(imagenConfig)); 
    }

  private void cargarClientes() {

    cbclientes.removeAllItems();
    cbclientes.addItem("Seleccione un cliente");

    ClientesController controlador =
            new ClientesController(new ClientesDAOImpl());

    listaClientes = controlador.listarClientes();

    for (modelo.Clientes cliente : listaClientes) {
        cbclientes.addItem(cliente.getNombre());
    }
}
  private void cargarMedicionesCliente() {

    cbmedicion.removeAllItems();

    int indice = cbclientes.getSelectedIndex();

    if (indice <= 0) {
        cbmedicion.addItem("Seleccione un cliente");
        return;
    }

    try {

        modelo.Clientes clienteSeleccionado =
                listaClientes.get(indice - 1);

        int idCliente = clienteSeleccionado.getId();

        MedicionDAOImpl dao = new MedicionDAOImpl();

        List<Medicion> mediciones =
                dao.buscarPorCliente(idCliente);

        if (mediciones == null || mediciones.isEmpty()) {
            cbmedicion.addItem("Cliente sin mediciones");
            return;
        }

        cbmedicion.addItem("Seleccione una medición");

        for (Medicion m : mediciones) {

            cbmedicion.addItem(
                    "ID " + m.getId()
                    + " | " + m.getFechaMedicion()
                    + " | " + m.getTipoMedicion()
            );
        }

    } catch (Exception e) {

        cbmedicion.addItem("Error al cargar mediciones");

        System.out.println(
                "Error cargando mediciones: " + e.getMessage()
        );

        e.printStackTrace();
    }
}
    
    private void configurarTabla() {
    String[] columnas = {
   
        "Producto",
        "Cantidad",
        "Precio unitario",
        "Subtotal"
    };

    javax.swing.table.DefaultTableModel modelo =
        new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

    jtableventas.setModel(modelo);
    jtableventas.setRowHeight(25);
    jtableventas.getTableHeader().setReorderingAllowed(false);
}
    
    private void cargarProductos() {

    // Aros
    cbaro.removeAllItems();
    cbaro.addItem("Seleccione un aro");
    cbaro.addItem("Aro metálico");
    cbaro.addItem("Aro plástico");
    cbaro.addItem("Aro de acetato");
    cbaro.addItem("Aro de titanio");
    cbaro.addItem("Aro infantil");
    cbaro.addItem("Aro deportivo");

    // Cristales
    cbcristal.removeAllItems();
    cbcristal.addItem("Seleccione cristales (opcional)");
    cbcristal.addItem("Monofocal");
    cbcristal.addItem("Bifocal");
    cbcristal.addItem("Progresivo");
    cbcristal.addItem("Antirreflejo");
    cbcristal.addItem("Fotocromático");
    cbcristal.addItem("Filtro azul");

    // Accesorios
    cbacce.removeAllItems();
    cbacce.addItem("Seleccione accesorios (opcional)");
    cbacce.addItem("Estuche");
    cbacce.addItem("Paño de limpieza");
    cbacce.addItem("Cordón para lentes");
    cbacce.addItem("Spray limpiador");
    cbacce.addItem("Destornillador para lentes");
}
    
    private void agregarProducto(String nombre, double precio) {

    DefaultTableModel modelo =
            (DefaultTableModel) jtableventas.getModel();

    // Evitar agregar opciones vacías o de selección
    if (nombre == null || nombre.startsWith("Seleccione")) {
        return;
    }

    int cantidad = 1;
    double subtotal = cantidad * precio;

    modelo.addRow(new Object[]{
        nombre,
        cantidad,
        precio,
        subtotal
    });

    calcularTotal();
}
    private void calcularTotal() {

    DefaultTableModel modelo =
            (DefaultTableModel) jtableventas.getModel();

    double subtotal = 0;

    for (int i = 0; i < modelo.getRowCount(); i++) {
        subtotal += Double.parseDouble(
                modelo.getValueAt(i, 3).toString()
        );
    }

    double impuesto = subtotal * 0.15;
    double total = subtotal + impuesto;

    lblsubt.setText("Subtotal: L " + String.format("%.2f", subtotal));
    lblimp.setText("Impuesto (15%): L " + String.format("%.2f", impuesto));
    lbltotal.setText("Total: L " + String.format("%.2f", total));
}
   private void configurarEventosProductos() {

    // AROS
    cbaro.addActionListener(e -> {

        String aro = (String) cbaro.getSelectedItem();

        if ("Aro metálico".equals(aro)) agregarProducto(aro, 850);
        else if ("Aro plástico".equals(aro)) agregarProducto(aro, 650);
        else if ("Aro de acetato".equals(aro)) agregarProducto(aro, 1200);
        else if ("Aro de titanio".equals(aro)) agregarProducto(aro, 1800);
        else if ("Aro infantil".equals(aro)) agregarProducto(aro, 500);
        else if ("Aro deportivo".equals(aro)) agregarProducto(aro, 950);
    });

    // CRISTALES
    cbcristal.addActionListener(e -> {

        String cristal = (String) cbcristal.getSelectedItem();

        if ("Monofocal".equals(cristal)) agregarProducto(cristal, 500);
        else if ("Bifocal".equals(cristal)) agregarProducto(cristal, 900);
        else if ("Progresivo".equals(cristal)) agregarProducto(cristal, 1800);
        else if ("Antirreflejo".equals(cristal)) agregarProducto(cristal, 700);
        else if ("Fotocromático".equals(cristal)) agregarProducto(cristal, 1100);
        else if ("Filtro azul".equals(cristal)) agregarProducto(cristal, 600);
    });

    // ACCESORIOS
    cbacce.addActionListener(e -> {

        String accesorio = (String) cbacce.getSelectedItem();

        if ("Estuche".equals(accesorio)) agregarProducto(accesorio, 150);
        else if ("Paño de limpieza".equals(accesorio)) agregarProducto(accesorio, 50);
        else if ("Cordón para lentes".equals(accesorio)) agregarProducto(accesorio, 80);
        else if ("Spray limpiador".equals(accesorio)) agregarProducto(accesorio, 120);
        else if ("Destornillador para lentes".equals(accesorio)) agregarProducto(accesorio, 100);
    });
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbclientes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbaro = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbcristal = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbacce = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbmedicion = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableventas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblsubt = new javax.swing.JLabel();
        lblimp = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        venta = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Cliente:");

        cbclientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un cliente" }));

        jLabel4.setText("Aro:");

        cbaro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un aro" }));
        cbaro.addActionListener(this::cbaroActionPerformed);

        jLabel5.setText("Cristal:");

        cbcristal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione cristales (opcional)" }));

        jLabel6.setText("Accesorios:");

        cbacce.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione accesorios (opcional)" }));

        jLabel10.setText("Medicion:");

        cbmedicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un cliente" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbmedicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbacce, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbcristal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbaro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbclientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbclientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbmedicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbaro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbcristal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbacce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Procesar Venta");

        jLabel2.setText("Registra una nueva venta y se actualiza el inventario automaticamente");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle de la venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jtableventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableventas);

        jLabel7.setText("Subtotal:");

        jLabel8.setText("Impuesto (15%)");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Total:");

        jButton1.setText("Registrar Venta");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Cancelar");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        lbltotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblimp)
                    .addComponent(lblsubt)
                    .addComponent(lbltotal))
                .addGap(69, 69, 69))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblsubt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblimp))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbltotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(venta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(venta)
                .addGap(110, 110, 110)
                .addComponent(jLabel1))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel2))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(venta)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbaroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbaroActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_cbaroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         // Validar que se haya seleccionado un cliente
    // VALIDAR CLIENTE
    if (cbclientes.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this,
                "Seleccione un cliente.");
        return;
    }

    // VALIDAR MEDICIÓN
    if (cbmedicion.getSelectedIndex() <= 0) {
        JOptionPane.showMessageDialog(this,
                "Seleccione una medición.");
        return;
    }

    // VALIDAR PRODUCTOS
    DefaultTableModel modelo =
            (DefaultTableModel) jtableventas.getModel();

    if (modelo.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this,
                "Agregue productos a la venta.");
        return;
    }

    try {

        // =========================
        // OBTENER ID DEL CLIENTE
        // =========================
        int posicionCliente =
                cbclientes.getSelectedIndex() - 1;

        modelo.Clientes clienteSeleccionado =
                listaClientes.get(posicionCliente);

        int clienteId = clienteSeleccionado.getId();

        // =========================
        // OBTENER ID DE MEDICIÓN
        // =========================
        String medicionSeleccionada =
                cbmedicion.getSelectedItem().toString();

        // Ejemplo:
        // ID 4 | 2026-12-10 | Bifocal

        String parteId =
                medicionSeleccionada.split("\\|")[0].trim();

        int medicionId = Integer.parseInt(
                parteId.replace("ID", "").trim()
        );

        // =========================
        // CALCULAR SUBTOTAL
        // =========================
        double subtotal = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {

            subtotal += Double.parseDouble(
                    modelo.getValueAt(i, 3).toString()
            );
        }

        double impuesto = subtotal * 0.15;
        double total = subtotal + impuesto;

        // =========================
        // GUARDAR VENTA
        // =========================
        String sql =
                "INSERT INTO Ventas "
                + "(clienteId, medicionId, fecha, "
                + "subtotal, impuesto, total) "
                + "VALUES (?, ?, GETDATE(), ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ps.setInt(2, medicionId);
            ps.setDouble(3, subtotal);
            ps.setDouble(4, impuesto);
            ps.setDouble(5, total);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "¡Venta registrada correctamente!");

            // LIMPIAR TABLA
            modelo.setRowCount(0);

            // REINICIAR COMBOS
            cbclientes.setSelectedIndex(0);

            cbmedicion.removeAllItems();
            cbmedicion.addItem("Seleccione un cliente");

            cbaro.setSelectedIndex(0);
            cbcristal.setSelectedIndex(0);
            cbacce.setSelectedIndex(0);

            // REINICIAR TOTALES
            lblsubt.setText("Subtotal: L 0.00");
            lblimp.setText("Impuesto (15%): L 0.00");
            lbltotal.setText("Total: L 0.00");
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Error al guardar la venta: "
                + e.getMessage());

        e.printStackTrace();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage());

        e.printStackTrace();
    }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        // Reiniciar los totales
        lblsubt.setText("Subtotal: L 0.00");
        lblimp.setText("Impuesto (15%): L 0.00");
        lbltotal.setText("Total: L 0.00");
        
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbacce;
    private javax.swing.JComboBox<String> cbaro;
    private javax.swing.JComboBox<String> cbclientes;
    private javax.swing.JComboBox<String> cbcristal;
    private javax.swing.JComboBox<String> cbmedicion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableventas;
    private javax.swing.JLabel lblimp;
    private javax.swing.JLabel lblsubt;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JLabel venta;
    // End of variables declaration//GEN-END:variables
}
