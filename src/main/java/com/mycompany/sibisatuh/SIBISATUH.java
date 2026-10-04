package com.mycompany.sibisatuh;
import java.util.Scanner;

// Main class
public class SIBISATUH {
    
    // Method cari berdasarkan nama makanan/minuman
    public static void cariKonsumsi(String kataKunci, KonsumsiHarian[] daftar, int jumlah) {
        boolean ketemu = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNamaItem().toLowerCase().contains(kataKunci.toLowerCase())) {
                daftar[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu)
            System.out.println("Item tidak ditemukan.");
    }
    // Method cari berdasarkan jumlah kalori
    public static void cariKonsumsi(double minKalori, KonsumsiHarian[] daftar, int jumlah) {
        boolean ketemu = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getKalori() >= minKalori) {
                daftar[i].tampilkanInfo();
                ketemu = true;
            }
        }
        if (!ketemu) System.out.println("Item tidak ditemukan.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KonsumsiHarian[] logHarian = new KonsumsiHarian[50]; //Kapasitas 50
        int jumlahData = 0;
        boolean isRunning = true;
        
        while(isRunning) {
            System.out.println("\n================ SIBISATUH =================");
            System.out.println("=== Sistem Bimbingan Asupan Status Tubuh ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch(pilihan) {
                case 1 -> {
                    System.out.print("Pilih (1. Makanan/2. Minuman): ");
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
                                logHarian[jumlahData++] = new Makanan(nama, kalori, porsi);
                                System.out.println("Data berhasil ditambah!");
                            }
                            
                        } else if (tipe == 2) {
                            System.out.print("Volume (ml): "); 
                            double vol = scanner.nextDouble();
                            scanner.nextLine();
                            
                            if (vol < 0) {
                                System.out.println("Gagal! Volume tidak boleh negatif");
                            } else {
                                logHarian[jumlahData++] = new Minuman(nama, kalori, vol);
                                System.out.println("Data berhasil ditambah!");
                            }
                            
                        } else {
                            System.out.println("Gagal! Pilihan tipe tidak valid");
                        }
                    }
                }
                case 2 -> {
                    if (jumlahData == 0)
                        System.out.println("Belum ada data");
                    for(int i = 0; i < jumlahData; i++) {
                        System.out.print((i + 1) + ". ");
                        logHarian[i].tampilkanInfo();
                    }
                    System.out.println("Total Item: " + KonsumsiHarian.totalItem);
                }
                case 3 -> {
                    System.out.print("Cari berdasarkan (1. Nama/2. Kalori): ");
                    int mode = scanner.nextInt();
                    scanner.nextLine();
                    if (mode == 1) {
                        System.out.print("Masukkan kata kunci: ");
                        cariKonsumsi(scanner.nextLine(), logHarian, jumlahData);
                    } else if (mode == 2) {
                        System.out.print("Minimal Kalori: ");
                        cariKonsumsi(scanner.nextDouble(), logHarian, jumlahData);
                    }
                }
                case 4 -> isRunning = false;
            }
        }
        scanner.close();
    }
}