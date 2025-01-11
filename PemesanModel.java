package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PemesanModel {
    private static final Logger LOGGER = Logger.getLogger(PemesanModel.class.getName());
    //private menunjukkan bahwa variabel LOGGER tidak dapat diakses dari luar kelas PemesanModel.
    //static berarti bahwa variabel LOGGER adalah milik kelas (PemesanModel) dan bukan merupakan bagian dari instance (objek) individu dari kelas tersebut.
    //final menunjukkan bahwa setelah LOGGER diinisialisasi, nilainya tidak dapat diubah
    //Logger adalah kelas dari Java Util Logging (JUL) yang menyediakan mekanisme untuk mencatat (logging) informasi dalam aplikasi.
    //Logger.getLogger(PemesanModel.class.getName()) adalah metode statis yang digunakan untuk mendapatkan instance dari logger untuk kelas tertentu.
    //PemesanModel.class.getName(): Mengambil nama lengkap dari kelas PemesanModel, yang akan digunakan sebagai penanda (namespace) untuk log yang dihasilkan oleh logger ini. Ini berguna untuk mengidentifikasi dari mana log tersebut berasal, terutama dalam aplikasi yang besar dengan banyak kelas.
    private Connection conn; //Variabel conn digunakan untuk menyimpan koneksi ke database.

    public PemesanModel() {
        conn = DatabaseConnection.getConnection();
    }//Tujuan dari konstruktor PemesanModel ini adalah untuk memastikan bahwa setiap kali sebuah objek PemesanModel dibuat, ia sudah memiliki koneksi aktif ke database yang siap untuk digunakan.


    public List<String[]> getAllPemesan() {//Metode ini mengembalikan daftar (List) berupa array (String[])
        //getAllPemesan: Nama ini menjelaskan bahwa metode ini ditujukan untuk mengambil semua data pemesan.
        List<String[]> data = new ArrayList<>();//Membuat sebuah list baru bernama data untuk menyimpan hasil query.
        String query = "SELECT * FROM pemesan";//Query yang digunakan untuk memilih semua baris (*) dari tabel pemesan.
        
        try (Statement stmt = conn.createStatement();//Try-with-resources: Ini adalah fitur Java yang memastikan bahwa sumber daya (dalam hal ini, Statement dan ResultSet) ditutup secara otomatis setelah digunakan.
        //Statement stmt = conn.createStatement(): Membuat statement SQL menggunakan koneksi ke database yang sudah diinisialisasi.
             ResultSet rs = stmt.executeQuery(query)) {//        //ResultSet rs = stmt.executeQuery(query): Mengeksekusi query SQL yang telah disusun dan menyimpan hasilnya dalam objek ResultSet

            while (rs.next()) {
                data.add(new String[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("jenis_sampah")
                });
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all pemesan data", e);
        }

        return data;
    }

    public boolean addPemesan(String nama, String alamat, String jenisSampah) {
        String query = "INSERT INTO pemesan (nama, alamat, jenis_sampah) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, jenisSampah);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding pemesan", e);
            return false;
        }
    }

    public boolean updatePemesan(int id, String nama, String alamat, String jenisSampah) {
        String query = "UPDATE pemesan SET nama = ?, alamat = ?, jenis_sampah = ? WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nama);
            pst.setString(2, alamat);
            pst.setString(3, jenisSampah);
            pst.setInt(4, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating pemesan with ID: " + id, e);
            return false;
        }
    }

    public boolean deletePemesan(int id) {
        String query = "DELETE FROM pemesan WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting pemesan with ID: " + id, e);
            return false;
        }
    }
}