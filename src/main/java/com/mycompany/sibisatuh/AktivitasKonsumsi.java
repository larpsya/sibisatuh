package com.mycompany.sibisatuh;

public class AktivitasKonsumsi {
    
    // Asosiasi antara Pengguna dan KonsumsiHarian
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
    }
}

