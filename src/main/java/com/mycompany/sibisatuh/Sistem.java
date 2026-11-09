package com.mycompany.sibisatuh;

import java.util.ArrayList;
import java.util.HashMap;

public class Sistem {
    private String namaAplikasi;
    
    // Komposisi
    private Kalkulator mesinKalkulator;
    
    // Agregasi
    private ArrayList<KonsumsiHarian> daftarKonsumsi;
    private HashMap<String, Pengguna> dataPengguna;

    public Sistem(String namaAplikasi) {
        this.namaAplikasi = namaAplikasi;
        
        // Inisialisasi Komposisi
        this.mesinKalkulator = new Kalkulator("Metabolisme AI", 2500);
        
        // Inisialisasi Agregasi
        this.daftarKonsumsi = new ArrayList<>();
        this.dataPengguna = new HashMap<>();
    }

    public void bukaAplikasi() {
        System.out.println("\n Menginisialisasi " + this.namaAplikasi);
        mesinKalkulator.aktifkanKalkulator(); 
    }

    public void tambahKonsumsi(KonsumsiHarian k) {
        daftarKonsumsi.add(k);
        System.out.println("Sukses! Asupan ditambahkan. (Total: " + daftarKonsumsi.size() + ")");
    }

    public void tambahPengguna(Pengguna p) {
        dataPengguna.put(p.getIdPengguna(), p);
        System.out.println("Berhasil menambahkan pengguna!");
    }

    public void lihatSemuaKonsumsi() {
        if (daftarKonsumsi.isEmpty()) {
            System.out.println("Belum ada asupan yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarKonsumsi.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKonsumsi.get(i).tampilkanInfo();
        }
    }

    public void lihatSemuaPengguna() {
        System.out.println("Total Pengguna Terdaftar: " + dataPengguna.size());
        for (Pengguna p : dataPengguna.values()) {
            System.out.println("- ID: " + p.getIdPengguna() + " | Nama: " + p.getNama());
        }
    }

    public void cariKonsumsi(String kataKunci) throws KonsumsiException {
        System.out.println("Mencari Asupan dengan nama: " + kataKunci);
        boolean ditemukan = false;
        for (KonsumsiHarian k : daftarKonsumsi) {
            if (k.getNamaItem().toLowerCase().contains(kataKunci.toLowerCase())) {
                k.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new KonsumsiException("Gagal: Asupan '" + kataKunci + "' tidak terdaftar.");
        }
    }

    // Getter untuk relasi Asosiasi
    public Pengguna getPengguna(String id) { 
        return dataPengguna.get(id); }
    public KonsumsiHarian getKonsumsiInt(int index) { 
        return daftarKonsumsi.get(index); }
    public int getJumlahKonsumsi() { 
        return daftarKonsumsi.size(); }
}