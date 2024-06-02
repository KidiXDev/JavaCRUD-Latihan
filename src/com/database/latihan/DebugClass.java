/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.database.latihan;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dikhi
 */
public class DebugClass {

    public static void StartCalculateTotalBayar() {
        try {
            var koneksi = new Connection();
            var conn = koneksi.GetConnection();
            var stmt = conn.createStatement();

            String query = "SELECT id, bayar, disc, Akhir FROM r_supplier";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            while (res.next()) {
                String id = res.getString("id");
                float bayar = res.getFloat("bayar");
                float disc = res.getFloat("disc");

                float akhir = bayar - (bayar * disc / 100);

                String updateQuery = "UPDATE r_supplier SET Akhir = ? WHERE id = ?";
                var updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setFloat(1, akhir);
                updateStmt.setString(2, id);
                updateStmt.executeUpdate();
            }

            res.close();
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
