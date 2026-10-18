package com.mycompany.sibisatuh;

// Subclass + Implements Interface
public class Minuman extends KonsumsiHarian implements DapatDisimpan {
    private double volumeMl;
    
    public Minuman(String namaItem, double kalori, double volumeMl) {
        super(namaItem, kalori);
        this.volumeMl = volumeMl;
    }
    public double getVolumeMl() {
        return this.volumeMl;
    }
    public void setVolumeMl(double volumeMl) {
        if (volumeMl < 0)
            this.volumeMl = 0;
        else
            this.volumeMl = volumeMl;
    }
    @Override
    public void tampilkanInfo() {
        System.out.printf("[Minuman] Item: %-18s | Kalori: %,6.1f kcal | Volume: %,5.1f ml%n", 
                super.getNamaItem(), super.getKalori(), this.volumeMl);
    }
    // Method Baru
    @Override
    public void caraKonsumsi() {
        System.out.println("-> Proses: Glek glek glek");
    }
    // kontrak DapatDisimpan
    @Override
    public void panduanPenyimpanan() {
        System.out.println("-> Simpan: Simpan minuman di lemari pendingin (chiller) agar segar.");
    }
}