package com.mycompany.sibisatuh;

// Subclass
public class Makanan extends KonsumsiHarian {
    private double porsiGram;
    
    public Makanan(String namaItem, double kalori, double porsiGram) {
        super(namaItem, kalori);
        this.porsiGram = porsiGram;
    }
    public double getPorsiGram() {
        return this.porsiGram;
    }
    public void setPorsiGram(double porsiGram) {
        if(porsiGram < 0)
            this.porsiGram = 0;
        else
            this.porsiGram = porsiGram;
    }
    @Override
    public void tampilkanInfo() {
        System.out.printf("[Makanan] Item: %-18s | Kalori: %,6.1f kcal | Porsi: %,5.1f g%n",
                super.getNamaItem(), super.getKalori(), this.porsiGram);
    }
}