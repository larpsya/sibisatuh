package com.mycompany.sibisatuh;

// Subclass + Implements Interface
public class Minuman extends KonsumsiHarian implements DapatDisimpan {
    private double volumeMl;
    
    // Nambah exception handling pada konstruktor
    public Minuman(String namaItem, double kalori, double volumeMl) throws Exception {
        super(namaItem, kalori);
        if (volumeMl <= 0) {
            throw new Exception("Volume minuman harus lebih dari 0");
        }
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