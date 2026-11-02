package com.mycompany.proyectopaii;

import dao.impl.ConfiguracionDAOImpl;
import Daoo.ConfiguracionDAO;
import modelo.ConfiguracionSistema;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Configuracion extends javax.swing.JInternalFrame {

    private final ConfiguracionDAO dao = new ConfiguracionDAOImpl();
    private int configId = 0;

    public Configuracion() {
        initComponents();
        configurarTabla();

        jsbMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[]{"Lempira (L)", "Dólar (USD)"}));

        btnGuardar.addActionListener(e -> guardar());
        btnEditar.addActionListener(e -> editar());
        btnRestablecer.addActionListener(e -> restablecer());
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarSeleccion();
        });

        cargarTabla();
    }

    private void configurarTabla() {
        String[] columnas = {"ID", "Nombre de la óptica", "RTN", "Teléfono", "Dirección", "Correo",
            "Moneda", "Cashbox", "Impuesto (%)", "Administrador", "Mensaje de factura"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) { return false; }
        };
        jTable1.setModel(modelo);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        List<ConfiguracionSistema> lista = dao.listar();
        for (ConfiguracionSistema c : lista) {
            modelo.addRow(new Object[]{c.getId(), c.getNombreOptica(), c.getRtn(), c.getTelefono(),
                c.getDireccion(), c.getCorreo(), c.getMoneda(), c.getCashbox(), c.getImpuesto(),
                c.getAdministrador(), c.getMensajeFactura()});
        }
    }

    private void cargarSeleccion() {
        int fila = jTable1.getSelectedRow();
        if (fila < 0) return;
        configId = (int) jTable1.getValueAt(fila, 0);
        txtNombreO.setText(valor(fila, 1));
        txtRTN.setText(valor(fila, 2));
        txtTelefono.setText(valor(fila, 3));
        txtDireccion.setText(valor(fila, 4));
        txtCorreo.setText(valor(fila, 5));
        jsbMoneda.setSelectedItem(valor(fila, 6));
        txtImpuesto.setText(valor(fila, 8));
        txtNombreA.setText(valor(fila, 9));
        txtMensajeF.setText(valor(fila, 10));
    }

    private String valor(int fila, int col) {
        Object o = jTable1.getValueAt(fila, col);
        return o == null ? "" : o.toString();
    }

    // Devuelve null si hay un dato inválido (ya mostró el mensaje)
    private ConfiguracionSistema leerFormulario() {
        if (txtNombreO.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la óptica es obligatorio.");
            return null;
        }
        double impuesto;
        try {
            String t = txtImpuesto.getText().trim().replace(",", ".");
            impuesto = t.isEmpty() ? 0 : Double.parseDouble(t);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El impuesto debe ser un número (ej. 15).");
            return null;
        }
        if (impuesto < 0 || impuesto > 100) {
            JOptionPane.showMessageDialog(this, "El impuesto debe estar entre 0 y 100.");
            return null;
        }
        ConfiguracionSistema c = new ConfiguracionSistema();
        c.setId(configId);
        c.setNombreOptica(txtNombreO.getText().trim());
        c.setRtn(txtRTN.getText().trim());
        c.setTelefono(txtTelefono.getText().trim());
        c.setDireccion(txtDireccion.getText().trim());
        c.setCorreo(txtCorreo.getText().trim());
        c.setMoneda(String.valueOf(jsbMoneda.getSelectedItem()));
        c.setCashbox("Caja principal"); // el formulario no tiene campo de cashbox
        c.setImpuesto(impuesto);
        c.setAdministrador(txtNombreA.getText().trim());
        c.setMensajeFactura(txtMensajeF.getText().trim());
        return c;
    }

    private void guardar() {
        ConfiguracionSistema c = leerFormulario();
        if (c == null) return;
        c.setId(0);
        if (dao.guardar(c)) {
            JOptionPane.showMessageDialog(this, "Configuración guardada.");
            restablecer();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar. Revisa la conexión a la base de datos.");
        }
    }

    private void editar() {
        if (configId == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un registro de la tabla para editarlo.");
            return;
        }
        ConfiguracionSistema c = leerFormulario();
        if (c == null) return;
        if (dao.actualizar(c)) {
            JOptionPane.showMessageDialog(this, "Configuración actualizada.");
            restablecer();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar.");
        }
    }

    private void restablecer() {
        configId = 0;
        txtNombreO.setText("");
        txtRTN.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtImpuesto.setText("");
        txtNombreA.setText("");
        txtMensajeF.setText("");
        jsbMoneda.setSelectedIndex(0);
        jTable1.clearSelection();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        RTN = new javax.swing.JPanel();
        lblNombreO = new javax.swing.JLabel();
        lblRTN = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombreO = new javax.swing.JTextField();
        txtRTN = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblMoneda = new javax.swing.JLabel();
        lblPorcentajeIm = new javax.swing.JLabel();
        lblNombreA = new javax.swing.JLabel();
        lblMensajeF = new javax.swing.JLabel();
        jsbMoneda = new javax.swing.JComboBox<>();
        txtImpuesto = new javax.swing.JTextField();
        txtNombreA = new javax.swing.JTextField();
        txtMensajeF = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRestablecer = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Óptica");

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("VISIONPLUS");

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 2, 14)); // NOI18N
        jLabel12.setText("Tu mirada, nuestra prioridad");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(512, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));

        RTN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Optica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblNombreO.setText("Nombre de la Optica:");

        lblRTN.setText("RTN");

        lblTelefono.setText("Telefono:");

        lblDireccion.setText("Direccion:");

        lblCorreo.setText("Correo:");

        javax.swing.GroupLayout RTNLayout = new javax.swing.GroupLayout(RTN);
        RTN.setLayout(RTNLayout);
        RTNLayout.setHorizontalGroup(
            RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RTNLayout.createSequentialGroup()
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCorreo)
                            .addComponent(lblDireccion)
                            .addComponent(lblTelefono)))
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lblRTN))
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblNombreO)))
                .addGap(43, 43, 43)
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion)
                    .addComponent(txtCorreo)
                    .addComponent(txtNombreO, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRTN)
                    .addComponent(txtTelefono))
                .addGap(50, 50, 50))
        );
        RTNLayout.setVerticalGroup(
            RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RTNLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreO, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRTN)
                    .addComponent(txtRTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefono)
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(RTNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCorreo)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(RTNLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Configuracion");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion del Sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        lblMoneda.setText("Moneda:");

        lblPorcentajeIm.setText("Porcentaje de Impuesto:");

        lblNombreA.setText("Nombre Administrador:");

        lblMensajeF.setText("Mensaje de Facturas:");

        btnGuardar.setText("Guardar");

        btnEditar.setText("Editar");

        btnRestablecer.setText("Restablecer");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensajeF))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorcentajeIm)
                    .addComponent(lblNombreA)
                    .addComponent(lblMoneda))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMensajeF)
                    .addComponent(txtImpuesto)
                    .addComponent(txtNombreA)
                    .addComponent(jsbMoneda, 0, 125, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRestablecer)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardar)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMensajeF)
                .addGap(76, 76, 76))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMoneda)
                            .addComponent(jsbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnGuardar)))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorcentajeIm)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombreA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(btnRestablecer)
                .addGap(26, 26, 26)
                .addComponent(txtMensajeF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
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

        jDesktopPane2.setLayer(RTN, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(RTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RTN;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRestablecer;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jsbMoneda;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblMensajeF;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombreA;
    private javax.swing.JLabel lblNombreO;
    private javax.swing.JLabel lblPorcentajeIm;
    private javax.swing.JLabel lblRTN;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtImpuesto;
    private javax.swing.JTextField txtMensajeF;
    private javax.swing.JTextField txtNombreA;
    private javax.swing.JTextField txtNombreO;
    private javax.swing.JTextField txtRTN;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
