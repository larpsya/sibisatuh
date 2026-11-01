package com.mycompany.sibisatuh;

// Nambah library baru
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

// Main class
public class SIBISATUH {
    
    // Update method menggunakan arraylist dan exception
    public static void cariKonsumsi(String kataKunci, ArrayList<KonsumsiHarian> daftarKonsumsi) throws KonsumsiException {
        System.out.println("Mencari Asupan dengan Kata Kunci: " + kataKunci);
        boolean ketemu = false;
        
        for (int i = 0; i < daftarKonsumsi.size(); i++) {
            if (daftarKonsumsi.get(i).getNamaItem().toLowerCase().contains(kataKunci.toLowerCase())) {
                daftarKonsumsi.get(i).tampilkanInfo();
                ketemu = true;
            }
        }
        
        if (!ketemu) {
            throw new KonsumsiException("Gagal: Asupan dengan nama '" + kataKunci + "' tidak terdaftar di sistem.");
        }
    }
    
    // Method baru
    public static void simulasiProses(KonsumsiHarian item) {
        item.caraKonsumsi(); 
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Deklarasi ArrayList dan HashMap
        ArrayList<KonsumsiHarian> daftarKonsumsi = new ArrayList<>();
        HashMap<String, String> dataPengguna = new HashMap<>();
        boolean isRunning = true;
        
        while(isRunning) {
            System.out.println("\n================ SIBISATUH =================");
            System.out.println("=== Sistem Bimbingan Asupan Status Tubuh ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Simulasi Pencernaan");
            System.out.println("5. Kelola Pengguna");
            System.out.println("6. Keluar");
            System.out.print("Pilih: ");
            
            // Exception handling untuk menu
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();
                
                switch(pilihan) {
                    case 1 -> {
                        System.out.print("Pilih (1. Makanan/2. Minuman/3. Cemilan): ");
                        int tipe = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Nama Item: "); 
                        String nama = scanner.nextLine();
                        
                        System.out.print("Kalori: "); 
                        double kalori = scanner.nextDouble(); 
                        scanner.nextLine();
                        
                        if (kalori < 0) {
                            System.out.println("Gagal! Kalori tidak boleh negatif");
                        } else {
                            if (tipe == 1) {
                                System.out.print("Porsi (g): "); 
                                double porsi = scanner.nextDouble();
                                scanner.nextLine();
                                
                                if (porsi < 0) { 
                                    System.out.println("Gagal! Porsi tidak boleh negatif");
                                } else {
                                    // Tambah ke arraylist
                                    daftarKonsumsi.add(new Makanan(nama, kalori, porsi));
                                    System.out.println("Data berhasil ditambah!");
                                }
                                
                            } else if (tipe == 2) {
                                System.out.print("Volume (ml): "); 
                                double vol = scanner.nextDouble();
                                scanner.nextLine();
                                
                                if (vol < 0) { 
                                    System.out.println("Gagal! Volume tidak boleh negatif");
                                } else {
                                    daftarKonsumsi.add(new Minuman(nama, kalori, vol));
                                    System.out.println("Data berhasil ditambah!");
                                }
                                
                            } else if (tipe == 3) { 
                                System.out.print("Kadar Gula (g): "); 
                                double gula = scanner.nextDouble();
                                scanner.nextLine();
                                
                                if (gula < 0) 
                                    System.out.println("Gagal! Kadar gula tidak boleh negatif");
                                else {
                                    daftarKonsumsi.add(new Cemilan(nama, kalori, gula));
                                    System.out.println("Data berhasil ditambah!");
                                }
                            } else {
                                System.out.println("Gagal! Pilihan tipe tidak valid");
                            }
                        }
                    }
                    case 2 -> {
                        System.out.println("=== Rekapan Data ===");
                        if (daftarKonsumsi.isEmpty())
                            System.out.println("Belum ada data");
                            
                        for(int i = 0; i < daftarKonsumsi.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            daftarKonsumsi.get(i).tampilkanInfo();
                            System.out.println("");
                        }
                        System.out.println("Total Item: " + daftarKonsumsi.size());
                    }
                    case 3 -> {
                        System.out.print("Masukkan kata kunci pencarian: ");
                        String kataKunci = scanner.nextLine();
                        
                        // Try catch finally khusus method pencarian
                        try {
                            cariKonsumsi(kataKunci, daftarKonsumsi);
                        } catch (KonsumsiException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            System.out.println("Sesi pencarian selesai.");
                        }
                    }
                    case 4 -> {
                        System.out.println("\n=== SIMULASI PENCERNAAN TUBUH ===");
                        if (daftarKonsumsi.isEmpty()) {
                            System.out.println("Perut masih kosong");
                        } else {
                            System.out.println("Sistem tubuh sedang memproses " + daftarKonsumsi.size() + " asupan");
                            for(int i = 0; i < daftarKonsumsi.size(); i++) {
                                System.out.print("- " + daftarKonsumsi.get(i).getNamaItem() + " ");
                                
                                // Abstract method
                                simulasiProses(daftarKonsumsi.get(i));
                                
                                // Interface 1
                                if (daftarKonsumsi.get(i) instanceof DapatDisimpan) {
                                    DapatDisimpan itemSimpan = (DapatDisimpan) daftarKonsumsi.get(i);
                                    itemSimpan.panduanPenyimpanan();
                                }

                                // Interface 2
                                if (daftarKonsumsi.get(i) instanceof DapatDinilai) {
                                    DapatDinilai itemNilai = (DapatDinilai) daftarKonsumsi.get(i);
                                    itemNilai.beriRating(5); 
                                }
                            }
                            System.out.println("Simulasi selesai. Kalori telah diserap");
                        }
                    }
                    case 5 -> {
                        // Fitur HashMap
                        System.out.println("1. Tambah / 2. Cari / 3. Tampil Semua");
                        System.out.print("Pilih: ");
                        int menuHashMap = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (menuHashMap == 1) {
                            System.out.print("ID (Key): ");
                            String id = scanner.nextLine();
                            System.out.print("Nama (Value): ");
                            String nama = scanner.nextLine();
                            dataPengguna.put(id, nama);
                            System.out.println("Pengguna ditambahkan.");
                        } else if (menuHashMap == 2) {
                            System.out.print("Cari ID: ");
                            String id = scanner.nextLine();
                            if (dataPengguna.containsKey(id)) {
                                System.out.println("Ditemukan: " + dataPengguna.get(id));
                            } else {
                                System.out.println("ID tidak terdaftar.");
                            }
                        } else if (menuHashMap == 3) {
                            System.out.println("Total: " + dataPengguna.size());
                            for (String key : dataPengguna.keySet()) {
                                System.out.println("ID: " + key + " | Nama: " + dataPengguna.get(key));
                            }
                        }
                    }
                    case 6 -> isRunning = false;
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error sistem: " + e.getMessage());
            }
        }
        scanner.close();
    }
}