package com.mycompany.proyectopaii;

import controlador.ApartadoController;
import dao.impl.ApartadoDAOImpl;
import dao.impl.ClientesDAOImpl;
import dao.impl.ProductoDAOImpl;
import modelo.Apartado;
import modelo.Clientes;
import modelo.Producto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Apartados extends javax.swing.JInternalFrame {

    private final ApartadoController controller = new ApartadoController();
    private List<Clientes> clientes = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private int apartadoId = 0;

    public Apartados() {
        initComponents();
        configurarTabla();
        controller.setApartadosDAO(new ApartadoDAOImpl());

        txtCodigoA.setEditable(false);
        txtSaldoP.setEditable(false);
        txtNFechaA.setText(LocalDate.now().toString());   // formato yyyy-MM-dd

        cargarCombos();

        jsbProducto.addActionListener(e -> {
            int i = jsbProducto.getSelectedIndex();
            if (apartadoId == 0 && i >= 0 && i < productos.size()) {
                txtPrecioT.setText(String.valueOf(productos.get(i).getPrecio()));
                calcularSaldo();
            }
        });
        txtAbonoI.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyReleased(java.awt.event.KeyEvent e) { calcularSaldo(); }
        });
        txtPrecioT.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyReleased(java.awt.event.KeyEvent e) { calcularSaldo(); }
        });
        btnGuardar.addActionListener(e -> guardar());
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccion();
        });

        if (!productos.isEmpty()) {   // precio inicial del primer producto
            txtPrecioT.setText(String.valueOf(productos.get(0).getPrecio()));
            calcularSaldo();
        }
        cargarTabla();
    }

    private void configurarTabla() {
        String[] columnas = {"Código de apartado", "Cliente", "Producto", "Fecha de apartado",
            "Precio total", "Abono inicial", "Saldo pendiente", "Fecha límite de pago", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        jTable1.setModel(modelo);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarCombos() {
        clientes = new ClientesDAOImpl().listar();
        productos = new ProductoDAOImpl().listar();

        jsbCliente.removeAllItems();
        for (Clientes c : clientes) jsbCliente.addItem(c.getNombre());

        jsbProducto.removeAllItems();
        for (Producto p : productos) jsbProducto.addItem(p.getNombre());

        jsbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Pendiente", "Pagado", "Cancelado"}));
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        List<Apartado> lista = controller.listarApartados();
        if (lista == null) return;
        for (Apartado a : lista) {
            modelo.addRow(new Object[]{a.getId(), a.getClienteNombre(), a.getProductoNombre(),
                a.getFecha(), a.getPrecioTotal(), a.getAbonoInicial(), a.getSaldoPendiente(),
                a.getFechaLimite(), a.getEstado()});
        }
    }

    private void cargarSeleccion() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0) return;
        int id = (int) jTable1.getValueAt(fila, 0);
        Apartado a = controller.buscarApartado(id);
        if (a == null) return;

        apartadoId = a.getId();
        txtCodigoA.setText(String.valueOf(a.getId()));
        for (int i = 0; i < clientes.size(); i++)
            if (clientes.get(i).getId() == a.getClienteId()) jsbCliente.setSelectedIndex(i);
        for (int i = 0; i < productos.size(); i++)
            if (productos.get(i).getId() == a.getProductoId()) jsbProducto.setSelectedIndex(i);
        jsbEstado.setSelectedItem(a.getEstado());
        txtNFechaA.setText(String.valueOf(a.getFecha()));
        txtPrecioT.setText(String.valueOf(a.getPrecioTotal()));
        txtAbonoI.setText(String.valueOf(a.getAbonoInicial()));
        txtSaldoP.setText(String.valueOf(a.getSaldoPendiente()));
        txtFechaL.setText(a.getFechaLimite() == null ? "" : a.getFechaLimite().toString());
    }

    private void calcularSaldo() {
        try {
            double precio = Double.parseDouble(txtPrecioT.getText().trim().replace(",", "."));
            String ab = txtAbonoI.getText().trim().replace(",", ".");
            double abono = ab.isEmpty() ? 0 : Double.parseDouble(ab);
            txtSaldoP.setText(String.format("%.2f", precio - abono).replace(",", "."));
        } catch (NumberFormatException ex) {
            txtSaldoP.setText("");
        }
    }

    private void guardar() {
        int ic = jsbCliente.getSelectedIndex();
        int ip = jsbProducto.getSelectedIndex();
        if (ic < 0 || ip < 0) {
            JOptionPane.showMessageDialog(this,
                "Debe haber al menos un cliente y un producto registrados.");
            return;
        }

        Date fecha, limite = null;
        double precio, abono;
        try {
            fecha = Date.valueOf(txtNFechaA.getText().trim());
            String fl = txtFechaL.getText().trim();
            if (!fl.isEmpty()) limite = Date.valueOf(fl);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Las fechas deben tener el formato AAAA-MM-DD (ej. 2026-11-15).");
            return;
        }
        try {
            precio = Double.parseDouble(txtPrecioT.getText().trim().replace(",", "."));
            String ab = txtAbonoI.getText().trim().replace(",", ".");
            abono = ab.isEmpty() ? 0 : Double.parseDouble(ab);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y abono deben ser números válidos.");
            return;
        }
        if (precio <= 0 || abono < 0 || abono > precio) {
            JOptionPane.showMessageDialog(this, "El abono no puede ser negativo ni mayor al precio total.");
            return;
        }
        if (limite != null && limite.before(fecha)) {
            JOptionPane.showMessageDialog(this, "La fecha límite no puede ser anterior a la fecha del apartado.");
            return;
        }

        Apartado a = new Apartado();
        a.setId(apartadoId);
        a.setClienteId(clientes.get(ic).getId());
        a.setProductoId(productos.get(ip).getId());
        a.setFecha(fecha);
        a.setPrecioTotal(precio);
        a.setAbonoInicial(abono);
        a.setSaldoPendiente(precio - abono);
        a.setFechaLimite(limite);
        a.setEstado(String.valueOf(jsbEstado.getSelectedItem()));

        boolean ok = (apartadoId == 0) ? controller.guardarApartado(a) : controller.actualizarApartado(a);
        if (ok) {
            JOptionPane.showMessageDialog(this, apartadoId == 0 ? "Apartado guardado." : "Apartado actualizado.");
            limpiar();
            cargarTabla();
            if (ok) {

    JOptionPane.showMessageDialog(this,
            apartadoId == 0
            ? "Apartado guardado."
            : "Apartado actualizado.");

    limpiar();
    cargarTabla();

    if (Inicio.instancia != null) {
        Inicio.instancia.actualizarApartadosActivos();
    }

} else {

    JOptionPane.showMessageDialog(this,
            "No se pudo guardar. Revisa la conexión a la base de datos.");
}
       
    }
    }

    private void limpiar() {
        apartadoId = 0;
        txtCodigoA.setText("");
        txtNFechaA.setText(LocalDate.now().toString());
        txtAbonoI.setText("");
        txtFechaL.setText("");
        jTable1.clearSelection();
        if (!productos.isEmpty()) {
            jsbProducto.setSelectedIndex(0);
            txtPrecioT.setText(String.valueOf(productos.get(0).getPrecio()));
            calcularSaldo();
        }
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
        lblCodigoA = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        lblFechaA = new javax.swing.JLabel();
        lblPrecioT = new javax.swing.JLabel();
        lblAbonoI = new javax.swing.JLabel();
        lblSaldoP = new javax.swing.JLabel();
        lblFechaL = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtFechaL = new javax.swing.JTextField();
        txtSaldoP = new javax.swing.JTextField();
        txtCodigoA = new javax.swing.JTextField();
        txtPrecioT = new javax.swing.JTextField();
        txtNFechaA = new javax.swing.JTextField();
        txtAbonoI = new javax.swing.JTextField();
        jsbProducto = new javax.swing.JComboBox<>();
        jsbEstado = new javax.swing.JComboBox<>();
        jsbCliente = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        lblCodigoA.setText("Codigo de apartado:");

        jLabel2.setText("Cliente:");

        lblProducto.setText("Producto:");

        lblFechaA.setText("Fecha del apartado:");

        lblPrecioT.setText("Precio Total:");

        lblAbonoI.setText("Abono Inicial:");

        lblSaldoP.setText("Saldo Pendiente:");

        lblFechaL.setText("Fecha limite de pago:");

        lblEstado.setText("Estado:");

        jsbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jsbProducto.addActionListener(this::jsbProductoActionPerformed);

        btnGuardar.setText("Guardar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPrecioT)
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioT, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addComponent(lblSaldoP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(txtSaldoP, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCodigoA)
                                    .addComponent(lblFechaA)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFechaL)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnGuardar)
                                                .addGap(28, 28, 28)))
                                        .addGap(39, 39, 39))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jsbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNFechaA, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jsbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblEstado)
                                .addGap(81, 81, 81)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaL, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(jsbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAbonoI)
                        .addGap(64, 64, 64)
                        .addComponent(txtAbonoI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProducto))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoA)
                    .addComponent(txtCodigoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSaldoP)
                    .addComponent(txtSaldoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jsbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaL)
                            .addComponent(txtFechaL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(jsbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(jsbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaA)
                    .addComponent(txtNFechaA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioT)
                    .addComponent(txtPrecioT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAbonoI)
                    .addComponent(txtAbonoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsbProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jsbProductoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jsbCliente;
    private javax.swing.JComboBox<String> jsbEstado;
    private javax.swing.JComboBox<String> jsbProducto;
    private javax.swing.JLabel lblAbonoI;
    private javax.swing.JLabel lblCodigoA;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaA;
    private javax.swing.JLabel lblFechaL;
    private javax.swing.JLabel lblPrecioT;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblSaldoP;
    private javax.swing.JTextField txtAbonoI;
    private javax.swing.JTextField txtCodigoA;
    private javax.swing.JTextField txtFechaL;
    private javax.swing.JTextField txtNFechaA;
    private javax.swing.JTextField txtPrecioT;
    private javax.swing.JTextField txtSaldoP;
    // End of variables declaration//GEN-END:variables
}
