package com.database.latihan;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseManager extends SupplierModel {

    java.sql.Connection conn;

    public static int totalData;

    public DatabaseManager() {
        var koneksi = new Connection();
        conn = koneksi.GetConnection();
    }

    private void GetTotalData() {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT COUNT(*) AS total FROM r_supplier";

            var res = stmt.executeQuery(query);

            if (res.next()) {
                totalData = res.getInt("total");
            } else {
                throw new NullPointerException("Table not found or no rows!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void FetchDatabase(DefaultTableModel model, int page, int limits, String sortOrder) {
        int limit = limits;
        int offset = (page - 1) * limit;
        String orderDirection = sortOrder.equals("Ascending") ? "ASC" : "DESC";

        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT id, nama, alamat, telp, kota, email, bayar, tgl, disc, Akhir FROM r_supplier "
                    + "ORDER BY nama " + orderDirection
                    + " LIMIT " + limit + " OFFSET " + offset;

            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                String id = res.getString("id");
                String nama = res.getString("nama");
                String alamat = res.getString("alamat");
                String telp = res.getString("telp");
                String kota = res.getString("kota");
                String email = res.getString("email");
                float bayar = res.getFloat("bayar");
                String tgl = res.getString("tgl");
                float disc = res.getFloat("disc");
                float akhir = res.getFloat("Akhir");

                // Parsing dan format ulang tanggal
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = originalFormat.parse(tgl);
                String formattedDate = targetFormat.format(date);

                model.addRow(new Object[]{id, nama, alamat, telp, kota, email, bayar, formattedDate, disc, akhir});
            }

            GetTotalData();

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

            String query = "DELETE FROM r_supplier WHERE id = '" + selectedId + "'";
            var res = stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddRow(Object[] values) {
        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            SupplierModel supplierModel = createSupplierModelFromValues(values);

            String query = "INSERT INTO r_supplier (id,nama,alamat,telp,kota,email,Bayar,tgl,disc,Akhir) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

//            for (int i = 0; i < values.length; i++) {
//                pstmt.setObject(i + 1, values[i]);
//            }
            pstmt.setString(1, supplierModel.getId());
            pstmt.setString(2, supplierModel.getNama());
            pstmt.setString(3, supplierModel.getAlamat());
            pstmt.setString(4, supplierModel.getTelp());
            pstmt.setString(5, supplierModel.getKota());
            pstmt.setString(6, supplierModel.getEmail());
            pstmt.setObject(7, supplierModel.getBayar());
            pstmt.setObject(8, supplierModel.getTgl());
            pstmt.setObject(9, supplierModel.getDisc());
            pstmt.setObject(10, supplierModel.getAkhir());

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

            String query = "UPDATE r_supplier SET id = ?, nama = ?, alamat = ?, telp = ?, kota = ?, email = ?, Bayar = ?, tgl = ?, disc = ?, Akhir = ? WHERE id = ?";
            var pstmt = conn.prepareStatement(query);

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

            String query = "SELECT id, nama, alamat, telp, kota, email, bayar, tgl, disc FROM r_supplier WHERE id = '" + selectedID + "'";
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

    public void FetchDatabaseSearchResult(DefaultTableModel model, String searchTarget, int page, int limits, String sortOrder) {
        int limit = limits;
        int offset = (page - 1) * limit;
        String orderDirection = sortOrder.equals("Ascending") ? "ASC" : "DESC";

        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            String query = "SELECT * FROM r_supplier WHERE "
                    + "id LIKE ? OR "
                    + "nama LIKE ? OR "
                    + "alamat LIKE ? OR "
                    + "telp LIKE ? OR "
                    + "kota LIKE ? OR "
                    + "email LIKE ? OR "
                    + "Bayar LIKE ? OR "
                    + "tgl LIKE ? OR "
                    + "disc LIKE ? OR "
                    + "Akhir LIKE ? "
                    + "ORDER BY nama " + orderDirection + " "
                    + "LIMIT ? OFFSET ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            String searchPattern = "%" + searchTarget + "%";
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setString(i, searchPattern);
            }
            preparedStatement.setInt(11, limit);
            preparedStatement.setInt(12, offset);

            ResultSet res = preparedStatement.executeQuery();

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                String id = res.getString("id");
                String nama = res.getString("nama");
                String alamat = res.getString("alamat");
                String telp = res.getString("telp");
                String kota = res.getString("kota");
                String email = res.getString("email");
                float bayar = res.getFloat("Bayar");
                String tgl = res.getString("tgl");
                float disc = res.getFloat("disc");
                float akhir = res.getFloat("Akhir");

                // Parsing dan format ulang tanggal
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = originalFormat.parse(tgl);
                String formattedDate = targetFormat.format(date);

                model.addRow(new Object[]{id, nama, alamat, telp, kota, email, bayar, formattedDate, disc, akhir});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private SupplierModel createSupplierModelFromValues(Object[] values) {
        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setId((String) values[0]);
        supplierModel.setNama((String) values[1]);
        supplierModel.setAlamat((String) values[2]);
        supplierModel.setTelp((String) values[3]);
        supplierModel.setKota((String) values[4]);
        supplierModel.setEmail((String) values[5]);
        supplierModel.setBayar((Float) values[6]);

        if (values[7] instanceof String) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            supplierModel.setTanggal(LocalDate.parse((String) values[7], formatter));
        } else if (values[7] instanceof LocalDate) {
            supplierModel.setTanggal((LocalDate) values[7]);
        } else {
            throw new IllegalArgumentException("Format tanggal tidak dikenal: " + values[7]);
        }

        supplierModel.setDisc((Float) values[8]);
        supplierModel.setAkhir((Float) values[9]);

        return supplierModel;
    }

    public boolean hasNextPage(int page, int limits) {
        int limit = limits;
        int offset = (page - 1) * limit;

        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String countQuery = "SELECT COUNT(*) AS total FROM r_supplier";
            var countRes = stmt.executeQuery(countQuery);
            countRes.next();
            int totalRows = countRes.getInt("total");

            int totalPages = (int) Math.ceil((double) totalRows / limit);

            return page < totalPages;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean hasNextPageSearchResult(String searchTarget, int page, int limits) {
        int limit = limits;
        int offset = (page - 1) * limit;

        try {
            var koneksi = new Connection();
            conn = koneksi.GetConnection();

            String query = "SELECT COUNT(*) AS total FROM r_supplier WHERE "
                    + "id LIKE ? OR "
                    + "nama LIKE ? OR "
                    + "alamat LIKE ? OR "
                    + "telp LIKE ? OR "
                    + "kota LIKE ? OR "
                    + "email LIKE ? OR "
                    + "Bayar LIKE ? OR "
                    + "tgl LIKE ? OR "
                    + "disc LIKE ? OR "
                    + "Akhir LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            String searchPattern = "%" + searchTarget + "%";
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setString(i, searchPattern);
            }
            ResultSet countRes = preparedStatement.executeQuery();
            countRes.next();
            int totalRows = countRes.getInt("total");

            int totalPages = (int) Math.ceil((double) totalRows / limit);

            return page < totalPages;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
