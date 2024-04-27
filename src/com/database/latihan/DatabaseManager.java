package com.database.latihan;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseManager {

    java.sql.Connection conn;

    public DatabaseManager() {
        var koneksi = new Connection();
        conn = koneksi.GetConnection();
    }

    public void FetchDatabase(DefaultTableModel model) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM mahasiswa";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                int id = res.getInt("id");
                String nama = res.getString("nama");
                model.addRow(new Object[]{id, nama});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteTable(DefaultTableModel model, JTable table) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            var selectedRow = table.getSelectedRow();
            var selectedId = model.getValueAt(selectedRow, 0);

            String query = "DELETE FROM mahasiswa WHERE id = " + selectedId;
            var res = stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddRow(String nama) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            String query = "INSERT INTO mahasiswa (nama) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Failed to add row to the table!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void EditValue(int selectedID, String value){
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "UPDATE mahasiswa SET nama = \"" + value + "\" WHERE id = " + selectedID;
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String ReadValue(int selectedID) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM mahasiswa WHERE id = " + selectedID;
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            while (res.next()) {
                return res.getString("nama");
            }

            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void FetchDatabaseSearchResult(DefaultTableModel model, String searchTarget) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM mahasiswa WHERE nama LIKE '%" + searchTarget + "%'";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                int id = res.getInt("id");
                String nama = res.getString("nama");
                model.addRow(new Object[]{id, nama});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
