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
import javax.swing.*;
import java.awt.Image;
import java.sql.Connection;
import java.sql.SQLException;

public class Inicio extends javax.swing.JInternalFrame {
    public static Inicio instancia;
   private String[] rutasImagens = {
        "/imagenes/imagess.jpg",
        "/imagenes/proyectopaiii.jpg",
        "/imagenes/images.jpg",
        "/imagenes/lente.jpg"
    };
   private int indiceActual = 0;
    private Timer timerBanner;
    
    public Inicio() {
        initComponents();
        actualizarVentasMes();
        
      instancia = this;
       actualizarContadorClientes();
       actualizarApartadosActivos();
      try {
    Connection con = conexion.Conexion.conectar();

    if (con != null) {
        System.out.println("CONEXIÓN CORRECTA");
        con.close();
    }

} catch (SQLException e) {
    System.out.println("ERROR: " + e.getMessage());
}
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setClosable(true);
        mostrarImagenActual();       
        iniciarCarrusel();
     
     javax.swing.ImageIcon iconoConfig = new javax.swing.ImageIcon(getClass().getResource("/imagenes/lentes1.jpg"));
    java.awt.Image imagenConfig = iconoConfig.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
    lblimg1.setIcon(new javax.swing.ImageIcon(imagenConfig)); 


  
        lbl2.setText("");
        ImageIcon iconCli = new ImageIcon(getClass().getResource("/imagenes/usuario.png"));
        Image imgCli = iconCli.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lbl2.setIcon(new ImageIcon(imgCli));


        lbl4.setText("");
        ImageIcon iconApa = new ImageIcon(getClass().getResource("/imagenes/documento.png"));
        Image imgApa = iconApa.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lbl4.setIcon(new ImageIcon(imgApa));


        lbl5.setText("");
        ImageIcon iconVen = new ImageIcon(getClass().getResource("/imagenes/venta.png"));
        Image imgVen = iconVen.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        lbl5.setIcon(new ImageIcon(imgVen));
        

   
    }
    public void actualizarApartadosActivos() {

    String sql = "SELECT COUNT(*) AS total "
            + "FROM Apartados "
            + "WHERE estado = 'Pendiente'";

    try (java.sql.Connection con = conexion.Conexion.conectar();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {

            int total = rs.getInt("total");

            lblApartadosActivos.setText(
                    String.valueOf(total)
            );
        }

    } catch (java.sql.SQLException e) {

        System.out.println(
                "Error al contar apartados: "
                + e.getMessage()
        );
    }
}
    public void actualizarVentasMes() {

    String sql = "SELECT COUNT(*) AS total "
            + "FROM Ventas "
            + "WHERE MONTH(fecha) = MONTH(GETDATE()) "
            + "AND YEAR(fecha) = YEAR(GETDATE())";

    try (java.sql.Connection con = conexion.Conexion.conectar();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            int total = rs.getInt("total");
            lblVentaMes.setText(String.valueOf(total));
        }

    } catch (java.sql.SQLException e) {
        System.out.println(
                "Error al contar ventas del mes: " + e.getMessage()
        );
    }
}
 public void actualizarContadorClientes() {
    ClientesController controlador =
            new ClientesController(new ClientesDAOImpl());

    int cantidad = controlador.listarClientes().size();

    lblCantidadClientes.setText(String.valueOf(cantidad));

    lblCantidadClientes.revalidate();
    lblCantidadClientes.repaint();

    System.out.println("Clientes registrados: " + cantidad);
}

    private void mostrarImagenActual() {
        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(rutasImagens[indiceActual]));
            
            int ancho = lbl3.getWidth();
            int alto = lbl3.getHeight();
            
           
            if (ancho <= 0) ancho = 400; 
            if (alto <= 0) alto = 150;  

            Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            lbl3.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen del carrusel: " + e.getMessage());
        }
    }
    private void iniciarCarrusel() {
        timerBanner = new Timer(4000, e -> {
            indiceActual = (indiceActual + 1) % rutasImagens.length;
            mostrarImagenActual();
        });
        timerBanner.start();
    }
    
    private void reiniciarTimer() {
        if (timerBanner != null) {
            timerBanner.restart();
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
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblimg1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblCantidadClientes = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lblApartadosActivos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lblVentaMes = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        btna = new javax.swing.JButton();
        btns = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 803, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        jDesktopPane2.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("¡Bienvenido a Óptica VisionPlus!");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setText("Gestiona el inventario, clientes, mediciones, apartados y ventas desde un solo lugar");

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel4.setText("Clientes registrados");

        lblCantidadClientes.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbl2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblCantidadClientes)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbl2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblCantidadClientes)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel6.setText("Apartados activos");

        lblApartadosActivos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblApartadosActivos.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(lbl4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblApartadosActivos)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl4)
                .addGap(26, 26, 26)
                .addComponent(lblApartadosActivos)
                .addGap(63, 63, 63))
        );

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jLabel7.setText("Ventas del mes");

        lblVentaMes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl5)
                .addGap(59, 59, 59))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lblVentaMes))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addComponent(lbl5)
                .addGap(47, 47, 47)
                .addComponent(lblVentaMes)
                .addGap(39, 39, 39))
        );

        jLabel3.setFont(new java.awt.Font("Blackadder ITC", 0, 24)); // NOI18N
        jLabel3.setText("----- La mejor visión, empieza aqui -----");

        btna.setText("<");
        btna.addActionListener(this::btnaActionPerformed);

        btns.setText(">");
        btns.addActionListener(this::btnsActionPerformed);

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lblimg1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(lbl3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(btna, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(btns, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btna)
                        .addGap(17, 17, 17)
                        .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btns)))
                .addGap(883, 883, 883)
                .addComponent(lblimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btna)
                                    .addComponent(btns)))))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsActionPerformed
        // TODO add your handling code here:
        indiceActual = (indiceActual + 1) % rutasImagens.length; // Avanza a la siguiente
        mostrarImagenActual();
        reiniciarTimer();
    }//GEN-LAST:event_btnsActionPerformed

    private void btnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaActionPerformed
        // TODO add your handling code here:
        indiceActual--;
        if (indiceActual < 0) {
            indiceActual = rutasImagens.length - 1; // Si está en la primera, va a la última
        }
        mostrarImagenActual();
        reiniciarTimer();
    }//GEN-LAST:event_btnaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btna;
    private javax.swing.JButton btns;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lblApartadosActivos;
    private javax.swing.JLabel lblCantidadClientes;
    private javax.swing.JLabel lblVentaMes;
    private javax.swing.JLabel lblimg1;
    // End of variables declaration//GEN-END:variables
}
