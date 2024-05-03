package com.database.latihan;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private DatabaseManager dm;

    public HomePage() {
        initComponents();

        dm = new DatabaseManager();

        tableModel = (DefaultTableModel) tableData.getModel();
        new DatabaseManager().FetchDatabase(tableModel);
        tableData.setModel(tableModel);

        tableData.getTableHeader().setReorderingAllowed(false);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText());
            }
        });

        // Context Menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");

        editItem.addActionListener(e -> {
            int row = tableData.getSelectedRow();
            if (row != -1) {
                EditItem();
            }
        });

        deleteItem.addActionListener(e -> {
            int row = tableData.getSelectedRow();
            if (row != -1) {
                DeleteItem();
            }
        });

        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        tableData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    if (tableData.getSelectedRow() != -1) {
                        popupMenu.show(tableData, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Data Mahasiswa Manager");

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tfSearch.setText("Search");

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnEdit)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addGap(18, 18, 18)
                        .addComponent(tfSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(273, 273, 273)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "CP", "Alamat", "Telp", "Kota", "Fax", "Email", "JT", "Disc", "AWAL", "Hutang", "Bayar", "Akhir", "Tgl", "User ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableData);
        if (tableData.getColumnModel().getColumnCount() > 0) {
            tableData.getColumnModel().getColumn(0).setResizable(false);
            tableData.getColumnModel().getColumn(1).setResizable(false);
            tableData.getColumnModel().getColumn(2).setResizable(false);
            tableData.getColumnModel().getColumn(3).setResizable(false);
            tableData.getColumnModel().getColumn(4).setResizable(false);
            tableData.getColumnModel().getColumn(5).setResizable(false);
            tableData.getColumnModel().getColumn(6).setResizable(false);
            tableData.getColumnModel().getColumn(7).setResizable(false);
            tableData.getColumnModel().getColumn(8).setResizable(false);
            tableData.getColumnModel().getColumn(9).setResizable(false);
            tableData.getColumnModel().getColumn(10).setResizable(false);
            tableData.getColumnModel().getColumn(11).setResizable(false);
            tableData.getColumnModel().getColumn(12).setResizable(false);
            tableData.getColumnModel().getColumn(13).setResizable(false);
            tableData.getColumnModel().getColumn(14).setResizable(false);
            tableData.getColumnModel().getColumn(15).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        JDialog addDialog = new AddMenuDialog(this, rootPaneCheckingEnabled);
        addDialog.setLocationRelativeTo(null);
        addDialog.setTitle("Tambah data baru");
        addDialog.show();
        dm.FetchDatabase(tableModel);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        EditItem();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        dm.FetchDatabase(tableModel);
    }//GEN-LAST:event_btnRefreshActionPerformed

    public static void main(String args[]) {
        FlatLightLaf.setup();
        try {
            UIManager.setLookAndFeel(new FlatCobalt2IJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                var home = new HomePage();
                home.setTitle("Data Mahasiswa Manager");
                home.setResizable(false);
                home.setLocationRelativeTo(null);

                home.setVisible(true);
            }
        });
    }

    private void DeleteItem() {
        if (tableData.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No item selected!", "Something went wrong", JOptionPane.ERROR_MESSAGE);
            return;
        }

        var msg = JOptionPane.showConfirmDialog(null, "Want to delete this item?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (msg == JOptionPane.CANCEL_OPTION) {
            return;
        }

        dm.DeleteTable(tableModel, tableData);
        dm.FetchDatabase(tableModel);
    }

    private void EditItem() {
        if (tableData.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No item selected!", "Something went wrong", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String selectedId = tableModel.getValueAt(tableData.getSelectedRow(), 0).toString();

        JDialog addDialog = new EditMenuDialog(this, rootPaneCheckingEnabled, selectedId);
        addDialog.setLocationRelativeTo(null);
        addDialog.setTitle("Edit data");
        addDialog.show();
        dm.FetchDatabase(tableModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableData;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
