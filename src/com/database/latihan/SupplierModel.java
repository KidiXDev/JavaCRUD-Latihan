package com.database.latihan;

import java.time.LocalDate;

public class SupplierModel {

    private String id;
    private String nama;
    private String alamat;
    private String telp;
    private String kota;
    private String email;
    private Float Bayar;
    private LocalDate tgl;
    private Float disc;
    private Float akhir;

    // Setter methods
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBayar(Float Bayar) {
        this.Bayar = Bayar;
    }

    public void setTanggal(LocalDate Tgl) {
        this.tgl = Tgl;
    }

    public void setDisc(Float Disc) {
        this.disc = Disc;
    }

    public void setAkhir(Float Akhir) {
        this.akhir = Akhir;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }

    public String getKota() {
        return kota;
    }

    public String getEmail() {
        return email;
    }

    public Float getBayar() {
        return Bayar;
    }

    public LocalDate getTgl() {
        return tgl;
    }

    public Float getDisc() {
        return disc;
    }

    public Float getAkhir() {
        return akhir;
    }

    public static float CalculateTotalPay(float pay, float disc) {
        if (disc < 1) {
            // return harga asli pas tidak ada diskon
            return pay;
        }

        float discValue = (disc / 100) * pay;
        return pay - discValue;
    }
}
