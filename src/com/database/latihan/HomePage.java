package com.database.latihan;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class HomePage extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private DefaultComboBoxModel comboBoxModel;
    private DefaultComboBoxModel comboBoxOrder;
    private DatabaseManager dm;

    private String orderBy = "Ascending";

    private int currentPage = 1;

    private int pageLimit = 30;

    private void SearchUpdate() {
        dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText(), 1, pageLimit, orderBy);
        currentPage = 1;
        tfPage.setText("1");
    }

    public HomePage() {
        initComponents();

        ((AbstractDocument) tfPage.getDocument()).setDocumentFilter(new NumericDocumentFilters());

        comboBoxModel = (DefaultComboBoxModel) cbMaxPage.getModel();
        comboBoxOrder = (DefaultComboBoxModel) cbOrder.getModel();

        dm = new DatabaseManager();

        tableModel = (DefaultTableModel) tableData.getModel();
        new DatabaseManager().FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
        tableData.setModel(tableModel);

        tableData.getTableHeader().setReorderingAllowed(false);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchUpdate();
            }

            public void removeUpdate(DocumentEvent e) {
                SearchUpdate();
            }

            public void insertUpdate(DocumentEvent e) {
                SearchUpdate();
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

        tfPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(tfPage.getText()) > 1) {
                    currentPage = Integer.parseInt(tfPage.getText());
                    dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
                }
            }
        });

        comboBoxModel.removeAllElements();
        comboBoxModel.addElement(30);
        comboBoxModel.addElement(50);
        comboBoxModel.addElement(100);

        cbMaxPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageLimit = (int) cbMaxPage.getSelectedItem();
                if (tfSearch.equals("") && dm.hasNextPage(currentPage + 1, pageLimit)) {
                    dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
                } else if (dm.hasNextPageSearchResult(tfSearch.getText(), currentPage + 1, pageLimit)) {
                    dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText(), currentPage, pageLimit, orderBy);
                }
            }
        });

        comboBoxOrder.removeAllElements();
        comboBoxOrder.addElement("Ascending");
        comboBoxOrder.addElement("Descending");
        //comboBoxOrder.addElement("Id Asc");
        //comboBoxOrder.addElement("Id Desc");

        cbOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBy = (String) cbOrder.getSelectedItem();
                if (tfSearch.equals("") && dm.hasNextPage(currentPage + 1, pageLimit)) {
                    dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
                } else if (dm.hasNextPageSearchResult(tfSearch.getText(), currentPage + 1, pageLimit)) {
                    dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText(), currentPage, pageLimit, orderBy);
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
        jLabel3 = new javax.swing.JLabel();
        cbOrder = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        tfPage = new javax.swing.JTextField();
        cbMaxPage = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Data Supplier Manager");

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

        tfSearch.setToolTipText("Type to search");

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel3.setText("Search:");

        cbOrder.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Order By:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh)
                    .addComponent(jLabel3))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Alamat", "Telp", "Kota", "Email", "Bayar", "Tgl"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableData.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableData);
        if (tableData.getColumnModel().getColumnCount() > 0) {
            tableData.getColumnModel().getColumn(1).setResizable(false);
            tableData.getColumnModel().getColumn(2).setResizable(false);
            tableData.getColumnModel().getColumn(3).setResizable(false);
            tableData.getColumnModel().getColumn(4).setResizable(false);
            tableData.getColumnModel().getColumn(5).setResizable(false);
            tableData.getColumnModel().getColumn(6).setResizable(false);
            tableData.getColumnModel().getColumn(7).setResizable(false);
        }

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        tfPage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfPage.setText("1");
        tfPage.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPageActionPerformed(evt);
            }
        });

        cbMaxPage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Show:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMaxPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(tfPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMaxPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        JDialog addDialog = new AddMenuDialog(this, rootPaneCheckingEnabled);
        addDialog.setLocationRelativeTo(null);
        addDialog.setTitle("Tambah data baru");
        addDialog.show();
        dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        EditItem();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DeleteItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (tfSearch.equals("") && dm.hasNextPage(currentPage + 1, pageLimit)) {
            currentPage++;
            tfPage.setText(Integer.toString(currentPage));
            dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
        } else if (dm.hasNextPageSearchResult(tfSearch.getText(), currentPage + 1, pageLimit)) {
            currentPage++;
            tfPage.setText(Integer.toString(currentPage));
            dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText(), currentPage, pageLimit, orderBy);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (currentPage > 1 && tfSearch.equals("")) {
            currentPage--;
            tfPage.setText(Integer.toString(currentPage));
            dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
        } else {
            if (currentPage > 1) {
                currentPage--;
                tfPage.setText(Integer.toString(currentPage));
                dm.FetchDatabaseSearchResult(tableModel, tfSearch.getText(), currentPage, pageLimit, orderBy);
            }

        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void tfPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPageActionPerformed

    }//GEN-LAST:event_tfPageActionPerformed

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
        dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
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
        dm.FetchDatabase(tableModel, currentPage, pageLimit, orderBy);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbMaxPage;
    private javax.swing.JComboBox<String> cbOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableData;
    private javax.swing.JTextField tfPage;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
