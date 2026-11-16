package com.mycompany.sibisatuh;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    
    // Membekukan ArrayList & HashMap ke file biner (.dat)
    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_sibisatuh.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);
            
            mesinPembeku.writeObject(this.daftarKonsumsi);
            mesinPembeku.writeObject(this.dataPengguna);
            
            mesinPembeku.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan ke Harddisk!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    // Menghidupkan kembali data dari file biner (.dat)
    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_sibisatuh.dat");
            if (!cekFile.exists()) return;

            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);
            
            this.daftarKonsumsi = (ArrayList<KonsumsiHarian>) mesinPembangkit.readObject();
            this.dataPengguna = (HashMap<String, Pengguna>) mesinPembangkit.readObject();
            
            mesinPembangkit.close();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang dari penyimpanan!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    // Membaca log dari file teks (.txt)
    public void bacaLogAktivitas() {
        System.out.println("\n--- Riwayat Log Konsumsi (.txt) ---");
        try {
            File fileLog = new File("log_konsumsi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada catatan log konsumsi.");
                return;
            }
            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log.");
        }
    }

    public Pengguna getPengguna(String id) { 
        return dataPengguna.get(id); }
    public KonsumsiHarian getKonsumsiInt(int index) { 
        return daftarKonsumsi.get(index); }
    public int getJumlahKonsumsi() { 
        return daftarKonsumsi.size(); }
}