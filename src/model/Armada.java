package model;

public class Armada {
    private int id;
    private String nomorKendaraan;
    private String tipeKendaraan;
    private int kapasitas;

    // Constructor tanpa ID (untuk tambah data baru)
    public Armada(String nomorKendaraan, String tipeKendaraan, int kapasitas) {
        this.nomorKendaraan = nomorKendaraan;
        this.tipeKendaraan = tipeKendaraan;
        this.kapasitas = kapasitas;
    }

    // Constructor lengkap dengan ID (untuk update atau representasi data dari DB)
    public Armada(int id, String nomorKendaraan, String tipeKendaraan, int kapasitas) {
        this.id = id;
        this.nomorKendaraan = nomorKendaraan;
        this.tipeKendaraan = tipeKendaraan;
        this.kapasitas = kapasitas;
    }

    // Getters dan setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomorKendaraan() {
        return nomorKendaraan;
    }

    public void setNomorKendaraan(String nomorKendaraan) {
        this.nomorKendaraan = nomorKendaraan;
    }

    public String getTipeKendaraan() {
        return tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }
}
