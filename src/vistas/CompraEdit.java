/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas;

import dao.CompraDAO;
import dao.MedicinaDAO;
import dao.ProveedorDAO;
import model.Compra;
import model.Medicina;
import model.Proveedor;
import main.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.awt.Color;
import java.time.YearMonth;
import main.MainFrame;

/**
 *
 * @author Admin
 */
public class CompraEdit extends javax.swing.JPanel {

    /**
     * Creates new form MenuPrincipal
     */
    private MainFrame mainFrame;
    private CompraDAO compraDAO;
    private MedicinaDAO medicinaDAO;
    private ProveedorDAO proveedorDAO;
    private Compra compra;

    public CompraEdit(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.compraDAO = new CompraDAO();
        this.medicinaDAO = new MedicinaDAO();
        this.proveedorDAO = new ProveedorDAO();
        initComponents();
        cargarProveedores();
        cargarMedicinas();
        setFechaActual();
    }

    public CompraEdit(MainFrame mainFrame, Compra compra) {
        this.mainFrame = mainFrame;
        this.compraDAO = new CompraDAO();
        this.medicinaDAO = new MedicinaDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.compra = compra;
        initComponents();
        cargarProveedores();
        cargarMedicinas();
        cargarDatosCompra();
    }

    private void setFechaActual() {
        LocalDate hoy = LocalDate.now();
        comboAnio.setSelectedItem(String.valueOf(hoy.getYear()));
        comboMes.setSelectedIndex(hoy.getMonthValue() - 1);
        actualizarDiasComboBox();
        comboDia.setSelectedItem(String.valueOf(hoy.getDayOfMonth()));
    }

    private void cargarProveedores() {
        List<Proveedor> proveedores = proveedorDAO.obtenerTodosLosProveedores();
        for (Proveedor proveedor : proveedores) {
            comboProveedor.addItem(proveedor.getNombre());
        }
    }

    private void cargarMedicinas() {
        List<Medicina> medicinas = medicinaDAO.obtenerTodasLasMedicinas();
        for (Medicina medicina : medicinas) {
            comboMedicinas.addItem(medicina.getNombre());
        }
    }

    private void actualizarDiasComboBox() {
        int anio = Integer.parseInt((String) comboAnio.getSelectedItem());
        int mes = comboMes.getSelectedIndex() + 1;
        YearMonth yearMonth = YearMonth.of(anio, mes);
        int diasEnMes = yearMonth.lengthOfMonth();
        
        String[] dias = new String[diasEnMes];
        for (int i = 1; i <= diasEnMes; i++) {
            dias[i - 1] = String.valueOf(i);
        }
        
        comboDia.setModel(new DefaultComboBoxModel<>(dias));
    }

    private void cargarDatosCompra() {
        LocalDate fecha = compra.getFecha();
        comboAnio.setSelectedItem(String.valueOf(fecha.getYear()));
        comboMes.setSelectedIndex(fecha.getMonthValue() - 1);
        actualizarDiasComboBox();
        comboDia.setSelectedItem(String.valueOf(fecha.getDayOfMonth()));

        comboProveedor.setSelectedItem(proveedorDAO.obtenerNombreProveedorPorId(compra.getProveedorId()));
        Medicina medicina = medicinaDAO.obtenerMedicinaPorId(compra.getMedicinaId());
        comboMedicinas.setSelectedItem(medicina.getNombre());
        txtCantidad.setText(String.valueOf(compra.getCantidad()));
        txtPrecio.setText(String.valueOf(compra.getPrecioUnidad()));
        txtTotal.setText(String.valueOf(compra.getPrecioTotal()));
    }

    private void actualizarTotal() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            double total = cantidad * precio;
            txtTotal.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            txtTotal.setText("0");
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

        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox<>();
        comboMes = new javax.swing.JComboBox<>();
        comboAnio = new javax.swing.JComboBox<>();
        comboDia = new javax.swing.JComboBox<>();
        btnFinalizar = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnNuevoProveedor = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboMedicinas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(780, 446));
        setPreferredSize(new java.awt.Dimension(780, 466));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setText("Editar compra");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Medicina");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setText("Fecha de compra");

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setText("Proveedor");

        comboProveedor.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        comboMes.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        comboAnio.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034" }));

        comboDia.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        btnFinalizar.setBackground(new java.awt.Color(25, 118, 210));
        btnFinalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFinalizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizarMouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Finalizar");

        javax.swing.GroupLayout btnFinalizarLayout = new javax.swing.GroupLayout(btnFinalizar);
        btnFinalizar.setLayout(btnFinalizarLayout);
        btnFinalizarLayout.setHorizontalGroup(
            btnFinalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnFinalizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnFinalizarLayout.setVerticalGroup(
            btnFinalizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnFinalizarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNuevoProveedor.setBackground(new java.awt.Color(25, 118, 210));
        btnNuevoProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoProveedorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevoProveedorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevoProveedorMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nuevo Proveedor");

        javax.swing.GroupLayout btnNuevoProveedorLayout = new javax.swing.GroupLayout(btnNuevoProveedor);
        btnNuevoProveedor.setLayout(btnNuevoProveedorLayout);
        btnNuevoProveedorLayout.setHorizontalGroup(
            btnNuevoProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNuevoProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnNuevoProveedorLayout.setVerticalGroup(
            btnNuevoProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNuevoProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        comboMedicinas.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setText("Precio por unidad");

        txtPrecio.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setText("Cantidad");

        txtCantidad.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(617, 617, 617)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboMedicinas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgLayout.createSequentialGroup()
                                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(bgLayout.createSequentialGroup()
                                            .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(6, 6, 6)
                                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(6, 6, 6)
                                    .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidad)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bgLayout.createSequentialGroup()
                                        .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(13, 13, 13)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(comboMedicinas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarMouseEntered
        btnFinalizar.setBackground(new Color(73,157,240));
    }//GEN-LAST:event_btnFinalizarMouseEntered

    private void btnFinalizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarMouseExited
        btnFinalizar.setBackground(new Color(25,118,210));
    }//GEN-LAST:event_btnFinalizarMouseExited

    private void btnNuevoProveedorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProveedorMouseEntered
        btnNuevoProveedor.setBackground(new Color(73,157,240));
    }//GEN-LAST:event_btnNuevoProveedorMouseEntered

    private void btnNuevoProveedorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProveedorMouseExited
        btnNuevoProveedor.setBackground(new Color(25,118,210));
    }//GEN-LAST:event_btnNuevoProveedorMouseExited

    private void btnNuevoProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoProveedorMouseClicked
        mainFrame.ShowJPanel(new ProveedorEdit(mainFrame));
    }//GEN-LAST:event_btnNuevoProveedorMouseClicked

    private void btnFinalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFinalizarMouseClicked
        LocalDate fecha = LocalDate.of(
                Integer.parseInt((String) comboAnio.getSelectedItem()),
                comboMes.getSelectedIndex() + 1,
                Integer.parseInt((String) comboDia.getSelectedItem())
        );

        int proveedorId = proveedorDAO.obtenerIdProveedorPorNombre((String) comboProveedor.getSelectedItem());
        int medicinaId = medicinaDAO.obtenerIdMedicinaPorNombre((String) comboMedicinas.getSelectedItem());
        int cantidad = Integer.parseInt(txtCantidad.getText());
        double precioUnidad = Double.parseDouble(txtPrecio.getText());
        double precioTotal = Double.parseDouble(txtTotal.getText());

        if (compra == null) {
            compra = new Compra();
            compra.setFecha(fecha);
            compra.setMedicinaId(medicinaId);
            compra.setCantidad(cantidad);
            compra.setPrecioUnidad(precioUnidad);
            compra.setPrecioTotal(precioTotal);
            compra.setProveedorId(proveedorId);
            compraDAO.insertarCompra(compra);
        } else {
            compra.setFecha(fecha);
            compra.setMedicinaId(medicinaId);
            compra.setCantidad(cantidad);
            compra.setPrecioUnidad(precioUnidad);
            compra.setPrecioTotal(precioTotal);
            compra.setProveedorId(proveedorId);
            compraDAO.actualizarCompra(compra);
        }

        JOptionPane.showMessageDialog(this, "Compra registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        mainFrame.ShowJPanel(new CompraMenu(mainFrame));
    }//GEN-LAST:event_btnFinalizarMouseClicked

    private void bgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseClicked
        actualizarTotal();
    }//GEN-LAST:event_bgMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel btnFinalizar;
    private javax.swing.JPanel btnNuevoProveedor;
    private javax.swing.JComboBox<String> comboAnio;
    private javax.swing.JComboBox<String> comboDia;
    private javax.swing.JComboBox<String> comboMedicinas;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboProveedor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
