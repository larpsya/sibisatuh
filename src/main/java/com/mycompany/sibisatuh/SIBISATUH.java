package com.mycompany.sibisatuh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SIBISATUH {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Komposisi & Agregasi
        Sistem aplikasiUtama = new Sistem("Sistem Pencatat Gizi Terpadu");
        aplikasiUtama.bukaAplikasi();
        
        boolean isRunning = true;
        
        while(isRunning) {
            System.out.println("\n================ SIBISATUH =================");
            System.out.println("1. Tambah Data Asupan");
            System.out.println("2. Lihat Daftar Asupan");
            System.out.println("3. Cari Data Asupan");
            System.out.println("4. Kelola Pengguna");
            System.out.println("5. Catat Pencernaan");
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu: ");
            
            try {
                int menu = scanner.nextInt();
                scanner.nextLine();
                
                switch(menu) {
                    case 1 -> {
                        System.out.print("Pilih (1. Makanan / 2. Minuman / 3. Cemilan): ");
                        int tipe = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Nama Item: "); 
                        String nama = scanner.nextLine();
                        System.out.print("Kalori: "); 
                        double kalori = scanner.nextDouble(); 
                        scanner.nextLine();
                        
                        if (tipe == 1) {
                            System.out.print("Porsi (g): "); 
                            double porsi = scanner.nextDouble(); 
                            scanner.nextLine();
                            aplikasiUtama.tambahKonsumsi(new Makanan(nama, kalori, porsi));
                        } else if (tipe == 2) {
                            System.out.print("Volume (ml): "); 
                            double vol = scanner.nextDouble(); 
                            scanner.nextLine();
                            aplikasiUtama.tambahKonsumsi(new Minuman(nama, kalori, vol));
                        } else if (tipe == 3) {
                            System.out.print("Kadar Gula (g): "); 
                            double gula = scanner.nextDouble(); 
                            scanner.nextLine();
                            aplikasiUtama.tambahKonsumsi(new Cemilan(nama, kalori, gula));
                        } else {
                            System.out.println("Gagal: Pilihan tipe tidak valid.");
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- Daftar Asupan Tersimpan ---");
                        aplikasiUtama.lihatSemuaKonsumsi();
                    }
                    case 3 -> {
                        System.out.println("\n--- Fitur Cari Asupan ---");
                        System.out.print("Masukkan kata kunci: ");
                        try {
                            aplikasiUtama.cariKonsumsi(scanner.nextLine());
                        } catch (KonsumsiException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.println("\n--- Manajemen Pengguna ---");
                        System.out.println("1. Tambah Pengguna Baru | 2. Lihat Semua Pengguna");
                        System.out.print("Pilih: ");
                        int m = scanner.nextInt(); 
                        scanner.nextLine();
                        
                        if (m == 1) {
                            System.out.print("Masukkan ID: "); 
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama: "); 
                            String nama = scanner.nextLine();
                            aplikasiUtama.tambahPengguna(new Pengguna(id, nama));
                        } else if (m == 2) {
                            aplikasiUtama.lihatSemuaPengguna();
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    case 5 -> {
                        System.out.println("\n--- Catat Proses Pencernaan ---");
                        System.out.print("Masukkan ID Pengguna: ");
                        String idUser = scanner.nextLine();
                        
                        Pengguna userAktif = aplikasiUtama.getPengguna(idUser);
                        
                        if (userAktif == null) {
                            System.out.println("Gagal: Pengguna tidak terdaftar!");
                        } else if (aplikasiUtama.getJumlahKonsumsi() == 0) {
                            System.out.println("Gagal: Belum ada data asupan.");
                        } else {
                            aplikasiUtama.lihatSemuaKonsumsi();
                            System.out.print("Pilih Nomor Asupan yang dimakan: ");
                            int noMenu = scanner.nextInt() - 1; 
                            scanner.nextLine();
                            
                            if (noMenu >= 0 && noMenu < aplikasiUtama.getJumlahKonsumsi()) {
                                KonsumsiHarian itemDimakan = aplikasiUtama.getKonsumsiInt(noMenu);
                                // Eksekusi Asosiasi
                                AktivitasKonsumsi.prosesPencernaan(userAktif, itemDimakan);
                            } else {
                                System.out.println("Gagal: Nomor asupan tidak valid!");
                            }
                        }
                    }
                    case 6 -> {
                        System.out.println("Terima kasih telah menggunakan SIBISATUH!");
                        isRunning = false;
                    }
                    default -> System.out.println("Pilihan menu tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harus berupa angka!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}