package com.mycompany.sibisatuh;

public class Cemilan extends KonsumsiHarian{
    private double kadarGula;

    public Cemilan(String namaItem, double kalori, double kadarGula) {
        super(namaItem, kalori);
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
}
