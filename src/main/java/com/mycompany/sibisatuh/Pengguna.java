package com.mycompany.sibisatuh;

public class Pengguna {
    private String idPengguna;
    private String nama;

    public Pengguna(String idPengguna, String nama) {
        this.idPengguna = idPengguna;
        this.nama = nama;
    }

    public String getIdPengguna() { return idPengguna; }
    public String getNama() { return nama; }
}
