package Clases;

import Conexiones.Conexion;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Usuarios extends javax.swing.JInternalFrame {

    private Image data;
    FileInputStream Cargar_Archivo;
    int contador = 0;
    static ResultSet res;

    public Usuarios() {
        initComponents();
        vaciarTabla();
        verDatos();
        desabilitar();
        nuevo.setEnabled(true);
        guardar.setEnabled(false);
        eliminar.setEnabled(false);
        imagen.setEnabled(false);
        modificar.setEnabled(false);

    }

    public void desabilitar() {
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField6.setEnabled(false);
        jTextField7.setEnabled(false);
        jTextField8.setEnabled(false);
        jTextField9.setEnabled(false);
        jTextField10.setEnabled(false);
        jTextField11.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);

    }

    public void limpiar() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jLabel8.setIcon(null);
        jLabel7.setText("");
    }

    public void habilitar() {

        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jTextField6.setEnabled(true);
        jTextField7.setEnabled(true);
        jTextField8.setEnabled(true);
        jTextField9.setEnabled(true);
        jTextField10.setEnabled(true);
        jTextField11.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);

    }

    public void vaciarTabla() {
        DefaultTableModel Modelo = (DefaultTableModel) jTable1.getModel();
        String titulos[] = {"No. PRESTAMISTA", "ID prestamista", "ID USUARIOS", "FECHA VENCIMIENTO", "NOMBRE COMPLETO", "EDAD", "Dirección", "CP", "Telefono", "ESCUELA/TRABAJO", "FECHA EXPEDICION", "URL"};
        Modelo = new DefaultTableModel(null, titulos);
        jTable1.setModel(Modelo);
    }

    public void verDatos() {
        try {
            Connection con1 = null;
            DefaultTableModel miModelo = (DefaultTableModel) jTable1.getModel();
            Conexion conect1 = new Conexion();
            con1 = conect1.getConnection();
            String dts[] = new String[13];
            String sql = "select * from prestamista";
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dts[0] = rs.getString("no_prestamista");
                dts[1] = rs.getString("id_prestamista");
                dts[2] = rs.getString("id_usuarios");
                dts[3] = rs.getString("FechaVencimiento");
                dts[4] = rs.getString("nombreCompleto");
                dts[5] = rs.getString("Edad");
                dts[6] = rs.getString("direccion");
                dts[7] = rs.getString("codigoP");
                dts[8] = rs.getString("telefono");
                dts[9] = rs.getString("EscuelaTrabajo");
                dts[10] = rs.getString("fechaExpedicion");
                dts[11] = rs.getString("url");
                miModelo.addRow(dts);
            }
            jTable1.setModel(miModelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

    public Image recuperarfotos(String usuario) {
        try {
            Connection con = null;
            Conexion conect = new Conexion();
            con = conect.getConnection();
            Statement st = con.createStatement();
            PreparedStatement pst = con.prepareStatement("Select prestamista.foto From prestamista where prestamista.no_prestamista= ? ");
            pst.setString(1, usuario);
            res = pst.executeQuery();
            int i = 0;
            while (res.next()) {
                byte[] b = res.getBytes("foto");
                data = ConvertirImagen(b);
                i++;
            }
            res.close();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Clases.Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void cargarfoto(String id) {
        Image dtCat = recuperarfotos(id);
        ImageIcon icon = new ImageIcon(dtCat);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(399, 371, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newimg);
        if (newIcon == null) {
            JOptionPane.showMessageDialog(null, "EL EQUIPO NO TIENE FOTO", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            jLabel8.setIcon(newIcon);
            jLabel8.setSize(jLabel8.getWidth(), jLabel8.getWidth());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        nuevo = new javax.swing.JMenu();
        guardar = new javax.swing.JMenu();
        modificar = new javax.swing.JMenu();
        eliminar = new javax.swing.JMenu();
        imagen = new javax.swing.JMenu();

        setClosable(true);
        setIconifiable(true);

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel2.setText("ID Prestamista");

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel3.setText("ID Usuario");

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel4.setText("Fecha Vencimiento");

        jLabel5.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel5.setText("Nombre ");

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel6.setText("Edad");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID PRESTAMISTA", "NOMBRE", "APELLIDO", "DIRECCION", "TELEFONO", "URL"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel9.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel9.setText("NO. Prestamista");

        jLabel10.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel10.setText("Dirección");

        jLabel11.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel11.setText("Telefono");

        jLabel12.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel12.setText("Codigo Postal");

        jLabel13.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel13.setText("Escuela/Trabajo");

        jLabel14.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        jLabel14.setText("FechaExpedicion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField3)
                    .addComponent(jTextField4)
                    .addComponent(jTextField5)
                    .addComponent(jTextField6))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
        );

        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/business_application_addmale_useradd_insert_add_user_client_2312.png"))); // NOI18N
        nuevo.setText("Nuevo");
        nuevo.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        nuevo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoMouseClicked(evt);
            }
        });
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jMenuBar1.add(nuevo);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/businessapplication_accept_ok_male_man_you_negocio_2311.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarMouseClicked(evt);
            }
        });
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jMenuBar1.add(guardar);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/businessapplication_edit_male_user_thepencil_theclient_negocio_2321.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        modificar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarMouseClicked(evt);
            }
        });
        jMenuBar1.add(modificar);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete_delete_deleteusers_delete_male_user_maleclient_2348.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setFont(new java.awt.Font("Sitka Subheading", 0, 18)); // NOI18N
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarMouseClicked(evt);
            }
        });
        jMenuBar1.add(eliminar);

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/iconfinder-image-4341296_120549.png"))); // NOI18N
        imagen.setText("Añadir FOTO");
        imagen.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        imagen.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        imagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenMouseClicked(evt);
            }
        });
        imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenActionPerformed(evt);
            }
        });
        jMenuBar1.add(imagen);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(968, 1065, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoMouseClicked
        limpiar();
        habilitar();
        nuevo.setEnabled(false);
        guardar.setEnabled(true);
        vaciarTabla();
        verDatos();
    }//GEN-LAST:event_nuevoMouseClicked

    private void guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarMouseClicked
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR ID PRESTAMISTA", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
        } else if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR ID USUARIO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField3.requestFocus();
        } else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR FECHA DE VENCIMIENTO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField4.requestFocus();
        } else if (jTextField5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR NOMBRE COMPLETO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField5.requestFocus();
        } else if (jTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR EDAD", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField6.requestFocus();
        } else if (jTextField7.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR DIRECCION", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField7.requestFocus();
        } else if (jTextField8.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR CODIGO POSTAL", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField8.requestFocus();
        } else if (jTextField9.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR TELEFONO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField9.requestFocus();
        } else if (jTextField10.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR ESCUELA/TRABAJO", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField10.requestFocus();
        } else if (jTextField11.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR FECHA DE EXPEDICION", "Advertencia", JOptionPane.WARNING_MESSAGE);
            jTextField11.requestFocus();
        } else if (jLabel7.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "FALTA INGRESAR UNA IMAGEN", "Advertencia", JOptionPane.WARNING_MESSAGE);
            imagen.setEnabled(true);
        } else {
            try {
                Connection con = null;
                Conexion conect = new Conexion();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "insert into prestamista (id_prestamista,id_usuarios,FechaVencimiento,nombreCompleto,Edad,direccion,codigoP,telefono,EscuelaTrabajo,fechaExpedicion,url,foto) values (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, jTextField2.getText());
                pst.setString(2, jTextField3.getText());
                pst.setString(3, jTextField4.getText());
                pst.setString(4, jTextField5.getText());
                pst.setString(5, jTextField6.getText());
                pst.setString(6, jTextField7.getText());
                pst.setString(7, jTextField8.getText());
                pst.setString(8, jTextField9.getText());
                pst.setString(9, jTextField10.getText());
                pst.setString(10, jTextField11.getText());
                pst.setString(11, jLabel7.getText());
                Cargar_Archivo = new FileInputStream(jLabel7.getText());
                pst.setBinaryStream(12, Cargar_Archivo);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    verDatos();
                    nuevo.setEnabled(true);
                    guardar.setEnabled(false);
                    modificar.setEnabled(false);
                    eliminar.setEnabled(false);
                    desabilitar();
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_guardarMouseClicked

    private void imagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenMouseClicked
        FileFilter filter = new FileNameExtensionFilter("JPG", "jpg", "png");
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar Imagen De Entrada");
        chooser.setFileFilter(filter);
        int Open = chooser.showOpenDialog(null);
        if (Open == JFileChooser.APPROVE_OPTION) {
            File Archivo = chooser.getSelectedFile();
            jLabel7.setText(String.valueOf(Archivo));
            Image Imagen = getToolkit().getImage(jLabel7.getText());
            Imagen = Imagen.getScaledInstance(399, 371, Image.SCALE_SMOOTH);
            jLabel8.setIcon(new ImageIcon(Imagen));
        }
    }//GEN-LAST:event_imagenMouseClicked

    private void modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarMouseClicked
        try {
            Connection con = null;
            Conexion conect = new Conexion();
            con = conect.getConnection();
            Statement st = con.createStatement();
            String sql = "update prestamista set id_prestamista = ?, id_usuarios = ?, Fechavencimiento = ?, nombreCompleto = ?, Edad = ?, direccion = ?, codigoP = ?, telefono = ?, EscuelaTrabajo = ?, fechaExpedicion = ?, url = ?, foto = ? where no_prestamista = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, jTextField2.getText());
            pst.setString(2, jTextField3.getText());
            pst.setString(3, jTextField4.getText());
            pst.setString(4, jTextField5.getText());
            pst.setString(5, jTextField6.getText());
            pst.setString(6, jTextField7.getText());
            pst.setString(7, jTextField8.getText());
            pst.setString(8, jTextField9.getText());
            pst.setString(9, jTextField10.getText());
            pst.setString(10, jTextField11.getText());
            pst.setString(11, jLabel7.getText());
            Cargar_Archivo = new FileInputStream(jLabel7.getText());
            pst.setBinaryStream(12, Cargar_Archivo);
            pst.setInt(13, Integer.parseInt(jTextField1.getText()));
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS CORRECTAMENTE");
                limpiar();
                vaciarTabla();
                verDatos();
                nuevo.setEnabled(true);
                guardar.setEnabled(false);
                modificar.setEnabled(false);
                eliminar.setEnabled(false);
                imagen.setEnabled(false);
                desabilitar();
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modificarMouseClicked

    private void eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarMouseClicked
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            int opc = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR ESTE REGISTRO?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == JOptionPane.YES_OPTION) {
                try {
                    Connection con = null;
                    Conexion conect = new Conexion();
                    con = conect.getConnection();
                    Statement st = con.createStatement();
                    String sql = "delete from prestamista where no_prestamista = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(jTextField1.getText()));
                    int n = pst.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(this, "DATOS ELIMINADOS CORRECTAMENTE");
                        limpiar();
                        vaciarTabla();
                        verDatos();
                        nuevo.setEnabled(true);
                        guardar.setEnabled(false);
                        modificar.setEnabled(false);
                        eliminar.setEnabled(false);
                        desabilitar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "DATOS NO ELIMINADOS CORRECTAMENTE" + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_eliminarMouseClicked

    private void imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenActionPerformed

    }//GEN-LAST:event_imagenActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed

    }//GEN-LAST:event_guardarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int fila = jTable1.getSelectedRow();
            String id = jTable1.getValueAt(fila, 0).toString();
            jTextField1.setText(jTable1.getValueAt(fila, 0).toString());
            jTextField2.setText(jTable1.getValueAt(fila, 1).toString());
            jTextField3.setText(jTable1.getValueAt(fila, 2).toString());
            jTextField4.setText(jTable1.getValueAt(fila, 3).toString());
            jTextField5.setText(jTable1.getValueAt(fila, 4).toString());
            jTextField6.setText(jTable1.getValueAt(fila, 5).toString());
            jTextField7.setText(jTable1.getValueAt(fila, 6).toString());
            jTextField8.setText(jTable1.getValueAt(fila, 7).toString());
            jTextField9.setText(jTable1.getValueAt(fila, 8).toString());
            jTextField10.setText(jTable1.getValueAt(fila, 9).toString());
            jTextField11.setText(jTable1.getValueAt(fila, 10).toString());
            jLabel7.setText(jTable1.getValueAt(fila, 11).toString());
            habilitar();
            cargarfoto(id);

            modificar.setEnabled(true);
            eliminar.setEnabled(true);
            imagen.setEnabled(true);
            nuevo.setEnabled(false);
            guardar.setEnabled(false);

        } catch (Exception ex) {
            System.out.println("ERROR AL SELECCIONAR UN EQUIPO : " + ex.getMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu eliminar;
    private javax.swing.JMenu guardar;
    private javax.swing.JMenu imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JMenu modificar;
    private javax.swing.JMenu nuevo;
    // End of variables declaration//GEN-END:variables

}
