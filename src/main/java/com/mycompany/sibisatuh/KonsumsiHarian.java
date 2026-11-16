package com.mycompany.sibisatuh;

import java.io.Serializable;

public abstract class KonsumsiHarian implements Serializable {
    private static final long serialVersionUID = 1L;
    private String namaItem;
    private double kalori;

    public KonsumsiHarian(String namaItem, double kalori) throws Exception {
        if (kalori < 0) {
            throw new Exception("Nilai kalori tidak valid (tidak boleh kurang dari 0)!");
        }
        this.namaItem = namaItem;
        this.kalori = kalori; 
    }

    public String getNamaItem() { return this.namaItem; }
    public double getKalori() { return this.kalori; }

    public void tampilkanInfo() {
        System.out.printf("Item: %-20s | Kalori: %,.1f kcal%n", this.namaItem, this.kalori);
    }

    public abstract void caraKonsumsi();
}
