package com.mycompany.sibisatuh;

// Superclass
public class KonsumsiHarian {
    private String namaItem;
    private double kalori;
    public static int totalItem = 0;
    
    public KonsumsiHarian(String namaItem, double kalori) {
        this.namaItem = namaItem;
        setKalori(kalori);
        totalItem++;
    }
    
    public String getNamaItem() {
        return this.namaItem;
    }
    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }
    public double getKalori() {
        return this.kalori;
    }
    public void setKalori(double kalori) {
        if(kalori < 0) {
            System.out.println("Kalori Tidak Boleh 0 atau negatif.");
            this.kalori = 0;
        } else {
            this.kalori = kalori;
        }
    }
    public void tampilkanInfo() {
        System.out.printf("Item: %-20s | Kalori: %,.1f kcal%n", this.namaItem, this.kalori);
    }
}