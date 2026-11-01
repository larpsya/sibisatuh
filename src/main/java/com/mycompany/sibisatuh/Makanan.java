package com.mycompany.sibisatuh;

// Subclass + Implements Interface
public class Makanan extends KonsumsiHarian implements DapatDisimpan, DapatDinilai {
    private double porsiGram;
    
    // Nambah exception handling pada konstruktor
    public Makanan(String namaItem, double kalori, double porsiGram) throws Exception {
        super(namaItem, kalori);
        if (porsiGram <= 0) {
            throw new Exception("Porsi makanan harus lebih dari 0");
        }
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
    // Method baru
    @Override
    public void caraKonsumsi() {
        System.out.println("-> Proses: Am nyam nyam eum kenyang");
    }
    // kontrak DapatDisimpan
    @Override 
    public void panduanPenyimpanan() {
        System.out.println("-> Simpan: Simpan makanan di wadah kedap udara atau di dalam kulkas.");
    }
    // kontrak DapatDinilai
    @Override
    public void beriRating(int bintang) {
        System.out.println("-> Rating: Makanan ini mendapat " + bintang + " Bintang dari pengguna.");
    }
}