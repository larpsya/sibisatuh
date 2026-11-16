package com.mycompany.sibisatuh;

import java.io.Serializable;

public class Pengguna implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idPengguna;
    private String nama;

    public Pengguna(String idPengguna, String nama) {
        this.idPengguna = idPengguna;
        this.nama = nama;
    }

    public String getIdPengguna() { return idPengguna; }
    public String getNama() { return nama; }
}
