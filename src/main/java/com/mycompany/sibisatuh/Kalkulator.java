package com.mycompany.sibisatuh;

public class Kalkulator {
    private String jenisAlgoritma;
    private double targetMaksimal;

    public Kalkulator(String jenisAlgoritma, double targetMaksimal) {
        this.jenisAlgoritma = jenisAlgoritma;
        this.targetMaksimal = targetMaksimal;
    }

    public void aktifkanKalkulator() {
        System.out.println("-> [Kalkulator] Algoritma: " + jenisAlgoritma + " | Target Harian: " + targetMaksimal + " kkal.");
    }
}