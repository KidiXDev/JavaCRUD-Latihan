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

            String query = "SELECT * FROM r_supplier";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                String id = res.getString("id");
                String nama = res.getString("nama");
                String cp = res.getString("cp");
                String alamat = res.getString("alamat");
                String telp = res.getString("telp");
                String kota = res.getString("kota");
                String fax = res.getString("fax");
                String email = res.getString("email");
                String jt = res.getString("jt");
                float disc = res.getFloat("disc");
                float awal = res.getFloat("AWAL");
                float hutang = res.getFloat("Hutang");
                float bayar = res.getFloat("Bayar");
                float akhir = res.getFloat("Akhir");
                String tgl = res.getString("tgl");
                String userID = res.getString("userid");

                model.addRow(new Object[]{id, nama, cp, alamat, telp, kota, fax, email, jt, disc, awal, hutang, bayar, akhir, tgl, userID});
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

            String query = "DELETE FROM r_supplier WHERE id = " + selectedId;
            var res = stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddRow(Object[] values) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            String query = "INSERT INTO r_supplier (id,nama,cp,alamat,telp,kota,fax,email,jt,disc,AWAL,Hutang,Bayar,Akhir,tgl,userid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Failed to add row to the table!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EditValue(String selectedID, Object[] value) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            String query = "UPDATE r_supplier SET id = ?, nama = ?, cp = ?, alamat = ?, telp = ?, kota = ?, fax = ?, email = ?, jt = ?, disc = ?, AWAL = ?, Hutang = ?, Bayar = ?, Akhir = ?, tgl = ?, userid = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (int i = 0; i < value.length; i++) {
                pstmt.setObject(i + 1, value[i]);
            }
            pstmt.setString(value.length + 1, selectedID);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String[] ReadValue(String selectedID) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM r_supplier WHERE id = '" + selectedID + "'";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] values = new String[columnCount];

            while (res.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = res.getString(i);
                }
            }

            return values;

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

            String query = "SELECT * FROM r_supplier WHERE nama LIKE '%" + searchTarget + "%'";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                String id = res.getString("id");
                String nama = res.getString("nama");
                String cp = res.getString("cp");
                String alamat = res.getString("alamat");
                String telp = res.getString("telp");
                String kota = res.getString("kota");
                String fax = res.getString("fax");
                String email = res.getString("email");
                String jt = res.getString("jt");
                float disc = res.getFloat("disc");
                float awal = res.getFloat("AWAL");
                float hutang = res.getFloat("Hutang");
                float bayar = res.getFloat("Bayar");
                float akhir = res.getFloat("Akhir");
                String tgl = res.getString("tgl");
                String userID = res.getString("userid");

                model.addRow(new Object[]{id, nama, cp, alamat, telp, kota, fax, email, jt, disc, awal, hutang, bayar, akhir, tgl, userID});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
