// src/model/ArmadaModel.java
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DatabaseConnection;

public class ArmadaModel {
    private static final Logger LOGGER = Logger.getLogger(ArmadaModel.class.getName());
    private Connection conn;

    public ArmadaModel() {
        conn = DatabaseConnection.getConnection();
    }

    public List<String[]> getAllArmada(String filter) {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT * FROM armada";
        System.out.println("Executing query: " + query);
        if (filter != null && !filter.isEmpty()) {
            query += " WHERE tipe_kendaraan LIKE ?";
        }

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (filter != null && !filter.isEmpty()) {
                stmt.setString(1, "%" + filter + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Query executed successfully.");
                while (rs.next()) {
                    data.add(new String[] {
                            rs.getString("id"),
                            rs.getString("nomor_kendaraan"),
                            rs.getString("tipe_kendaraan"),
                            rs.getString("kapasitas")
                    });
                }
                System.out.println("Data retrieved: " + data.size() + " records");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving armada data", e);
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return data;
    }

    public boolean addArmada(String nomorKendaraan, String tipeKendaraan, int kapasitas) {
        String query = "INSERT INTO armada (nomor_kendaraan, tipe_kendaraan, kapasitas) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nomorKendaraan);
            pst.setString(2, tipeKendaraan);
            pst.setInt(3, kapasitas);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding new armada", e);
            return false;
        }
    }

    public boolean updateArmada(int id, String nomorKendaraan, String tipeKendaraan, int kapasitas) {
        String query = "UPDATE armada SET nomor_kendaraan = ?, tipe_kendaraan = ?, kapasitas = ? WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nomorKendaraan);
            pst.setString(2, tipeKendaraan);
            pst.setInt(3, kapasitas);
            pst.setInt(4, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating armada with ID: " + id, e);
            return false;
        }
    }

    public boolean deleteArmada(int id) {
        String query = "DELETE FROM armada WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting armada with ID: " + id, e);
            return false;
        }
    }
}