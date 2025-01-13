package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DatabaseConnection;

public class PemesanModel {
    private static final Logger LOGGER = Logger.getLogger(PemesanModel.class.getName());
    private Connection conn;

    public PemesanModel() {
        conn = DatabaseConnection.getConnection();
    }

    public List<String[]> getAllPemesan() {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT * FROM pemesan";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                data.add(new String[] {
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