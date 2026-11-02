/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.proyectopaii;
import controlador.ClientesController;
import dao.impl.ClientesDAOImpl;
import javax.swing.JOptionPane;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author susyo
 */
public class Clientes extends javax.swing.JInternalFrame {

     DefaultTableModel tableclientes = new DefaultTableModel();
 

     private int filaSeleccionadaParaEditar = -1;
   
    public Clientes() {
    initComponents();
    configurarTabla();
  
}
  private void cargarTablaClientes() {

    ClientesController controlador =
            new ClientesController(new ClientesDAOImpl());

    DefaultTableModel modelo =
            (DefaultTableModel) jtableclientes.getModel();

    modelo.setRowCount(0);

    List<modelo.Clientes> lista = controlador.listarClientes();

    for (modelo.Clientes cliente : lista) {

        modelo.addRow(new Object[]{
            cliente.getId(),
            cliente.getNombre(),
            cliente.getDni(),
            cliente.getTelefono(),
            cliente.getCorreo()
        });
    }
}
    private void configurarTabla() {
    String[] columnas = {
      
        "Cliente",
        "DNI",
        "Telefono",
        "Correo"
    };

    javax.swing.table.DefaultTableModel modelo =
        new javax.swing.table.DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

    jtableclientes.setModel(modelo);
    jtableclientes.setRowHeight(25);
    jtableclientes.getTableHeader().setReorderingAllowed(false);
}
    @SuppressWarnings("unchecked")
    public void agregarfila()
    {
      //Funcion try-catch encapsular errores
       try
       {
           DefaultTableModel modelo = (DefaultTableModel) jtableclientes.getModel();


       
     
           String[] datos=new String[4];
     
        //Asignar los valores al arreglo
  
        datos[0]=this.txtNombre.getText();
        datos[1]=this.txtDNI.getText();
        datos[2]=this.txtTelefono.getText();
        datos[3]=this.txtCorreo.getText();
      
        //Asignar al modelo virtual las filas 
        this.tableclientes.addRow(datos);

 
       }
       catch(Exception e)
        {
           //Colocar el msj de error que vera el usuario
           JOptionPane.showMessageDialog(null, "ERROR NO ES POSIBLE AGREGAR LA FILA" + e);
        }
            
        
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableclientes = new javax.swing.JTable();
        btnNuevocliente = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Gestion de clientes");

        jLabel2.setText("Registra nuevos clientes y consulta la informacion de sus cuentas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblNombre.setText("Nombre:");

        lblDNI.setText("DNI:");

        lblTelefono.setText("Telefono:");

        lblCorreo.setText("Correo:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblDNI)
                            .addComponent(lblTelefono)
                            .addComponent(lblCorreo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtDNI)
                            .addComponent(txtTelefono)
                            .addComponent(txtCorreo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnGuardar)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNI)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(btnGuardar)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lista de clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jtableclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableclientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnNuevocliente.setText("Nuevo cliente");
        btnNuevocliente.addActionListener(this::btnNuevoclienteActionPerformed);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnNuevocliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btnEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnNuevocliente)
                .addGap(39, 39, 39)
                .addComponent(btnEditar)
                .addGap(28, 28, 28)
                .addComponent(btnEliminar))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevocliente)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addGap(6, 6, 6)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
     // Validar campos vacíos
    if (txtNombre.getText().trim().isEmpty() ||
        txtDNI.getText().trim().isEmpty() ||
        txtTelefono.getText().trim().isEmpty() ||
        txtCorreo.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Completa todos los campos.");
        return;
    }

    modelo.Clientes cliente = new modelo.Clientes();

    cliente.setNombre(txtNombre.getText().trim());
    cliente.setDni(txtDNI.getText().trim());
    cliente.setTelefono(txtTelefono.getText().trim());
    cliente.setCorreo(txtCorreo.getText().trim());

    ClientesController controlador =
            new ClientesController(new ClientesDAOImpl());

    try {
        if (controlador.guardarCliente(cliente)) {

            JOptionPane.showMessageDialog(this,
                    "Cliente registrado correctamente.");

            cargarTablaClientes();

            txtNombre.setText("");
            txtDNI.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");

           if (Inicio.instancia != null) {
    Inicio.instancia.actualizarContadorClientes();
}

        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo guardar el cliente. Revisa la conexión y los datos.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage());
        e.printStackTrace();
    }
  
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoclienteActionPerformed
        // TODO add your handling code here:
      
        txtNombre.setText("");
        txtDNI.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
   

        
    }//GEN-LAST:event_btnNuevoclienteActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        try {
        // Obtenemos la fila seleccionada en la tabla
        filaSeleccionadaParaEditar = jtableclientes.getSelectedRow();
        
        if (filaSeleccionadaParaEditar >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) jtableclientes.getModel();
            
            // Pasamos los datos de la tabla a las cajas de texto (Columnas 1, 2, 3 y 4)
            txtNombre.setText(modelo.getValueAt(filaSeleccionadaParaEditar, 1).toString());
            txtDNI.setText(modelo.getValueAt(filaSeleccionadaParaEditar, 2).toString());
            txtTelefono.setText(modelo.getValueAt(filaSeleccionadaParaEditar, 3).toString());
            txtCorreo.setText(modelo.getValueAt(filaSeleccionadaParaEditar, 4).toString());
            
            // Ponemos el foco en el nombre para empezar a editar
            txtNombre.requestFocus();
            
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un cliente de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR CLIENTE: " + e.getMessage());
    }
 

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
      


    int fila = jtableclientes.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecciona un cliente de la tabla.");
        return;
    }

    int id = Integer.parseInt(
            jtableclientes.getValueAt(fila, 0).toString()
    );

    int confirmar = JOptionPane.showConfirmDialog(
            this,
            "¿Estás segura de eliminar este cliente?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
    );

    if (confirmar == JOptionPane.YES_OPTION) {

        ClientesController controlador =
                new ClientesController(new ClientesDAOImpl());

        if (controlador.eliminarClientes(id)) {

            JOptionPane.showMessageDialog(this,
                    "Cliente eliminado correctamente.");

            cargarTablaClientes();

         if (Inicio.instancia != null) {
    Inicio.instancia.actualizarContadorClientes();
}

        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo eliminar el cliente. Revisa la conexión.");
        }
    }



    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevocliente;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableclientes;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
