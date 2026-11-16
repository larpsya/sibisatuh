package com.mycompany.sibisatuh;

import java.io.FileWriter;
import java.io.IOException;

public class AktivitasKonsumsi {
    
    public static void prosesPencernaan(Pengguna user, KonsumsiHarian item) {
        System.out.println("\n===== STRUK PENCERNAAN =====");
        System.out.println("Pengguna : " + user.getNama() + " (" + user.getIdPengguna() + ")");
        System.out.println("Asupan   : " + item.getNamaItem());
        System.out.println("Kalori   : " + item.getKalori() + " kkal");
        System.out.println("Status   : Berhasil dicatat ke dalam sistem.");
        System.out.println("----------------------------");
        
        item.caraKonsumsi();
        
        if (item instanceof DapatDisimpan) {
            ((DapatDisimpan) item).panduanPenyimpanan();
        }
        
        if (item instanceof DapatDinilai) {
            ((DapatDinilai) item).beriRating(5);
        }
        System.out.println("============================");

        // Mencatat riwayat ke log_konsumsi.txt
        try {
            FileWriter pipaTulis = new FileWriter("log_konsumsi.txt", true);
            String log = "Pengguna: " + user.getNama() + " (" + user.getIdPengguna() + ") | Asupan: " + item.getNamaItem() + " (" + item.getKalori() + " kkal)\n";
            pipaTulis.write(log);
            pipaTulis.close();
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log aktivitas ke file. " + e.getMessage());
        }
    }
}