package com.mycompany.sibisatuh;

// Subclass + Implements Interface
public class Cemilan extends KonsumsiHarian implements DapatDinilai {
    private double kadarGula;

    // Nambah exception handling pada konstruktor
    public Cemilan(String namaItem, double kalori, double kadarGula) throws Exception {
        super(namaItem, kalori);
        if (kadarGula < 0) {
            throw new Exception("Kadar gula harus lebih dari 0");
        }
        this.kadarGula = kadarGula;
    }

    public double getKadarGula() { return this.kadarGula; }
    public void setKadarGula(double kadarGula) { 
        if (kadarGula < 0) this.kadarGula = 0;
        else this.kadarGula = kadarGula;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("[CEMILAN] Item: %-18s | Kalori: %,6.1f kcal | Gula  : %,5.1f g%n",
            super.getNamaItem(), super.getKalori(), this.kadarGula);
    }
    // Method Baru
    @Override
    public void caraKonsumsi() {
        System.out.println("-> Proses: Enak kali euyyy");
    }
    // kontrak DapatDinilai
    @Override
    public void beriRating(int bintang) {
        System.out.println("-> Rating: Cemilan manis ini mendapat " + bintang + " Bintang.");
    }
}
