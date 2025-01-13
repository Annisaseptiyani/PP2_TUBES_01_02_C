package model;

public class Jadwal {
    private int id;
    private String nama;
    private String tanggal;
    private String lokasi;


    public Jadwal(int id, String nama, String tanggal, String lokasi) {
        this.id = id;
        this.nama = nama;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
    }

    public Jadwal(String nama, String tanggal, String lokasi) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public String toString() {
        return "Jadwal{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", tanggal='" + tanggal + '\'' +
                ", lokasi='" + lokasi + '\'' +
                '}';
    }
}
