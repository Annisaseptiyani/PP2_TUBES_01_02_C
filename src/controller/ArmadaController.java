// src/controller/ArmadaController.java
package controller;

import model.ArmadaModel;
import view.ArmadaView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArmadaController {
    private ArmadaView view;
    private ArmadaModel model;
    private DefaultTableModel tableModel;

    public ArmadaController(ArmadaView view, ArmadaModel model) {
        this.view = view;
        this.model = model;
        this.tableModel = (DefaultTableModel) view.table.getModel();
        this.initialize();
        updateTable(); // Load the table data on initialization
    }

    public void initialize() {
        // Tambah Action Listener untuk tombol Tambah
        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomorKendaraan = view.textNomorKendaraan.getText();
                String tipeKendaraan = (String) view.comboTipeKendaraan.getSelectedItem();
                String kapasitas = (String) view.comboKapasitas.getSelectedItem();

                if (nomorKendaraan.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nomor Kendaraan harus diisi!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Panggil model untuk menambah data ke database
                boolean isAdded = model.addArmada(nomorKendaraan, tipeKendaraan, Integer.parseInt(kapasitas));
                if (isAdded) {
                    updateTable();
                    JOptionPane.showMessageDialog(view, "Data Armada berhasil ditambahkan!");
                } else {
                    JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat menambahkan data Armada!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tambah Action Listener untuk tombol Update
        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih baris untuk diupdate.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String nomorKendaraan = view.textNomorKendaraan.getText();
                String tipeKendaraan = (String) view.comboTipeKendaraan.getSelectedItem();
                String kapasitas = (String) view.comboKapasitas.getSelectedItem();
                int id = Integer.parseInt(view.table.getValueAt(selectedRow, 0).toString());

                // Panggil model untuk update data armada
                boolean isUpdated = model.updateArmada(id, nomorKendaraan, tipeKendaraan, Integer.parseInt(kapasitas));
                if (isUpdated) {
                    updateTable();
                    JOptionPane.showMessageDialog(view, "Data Armada berhasil diupdate!");
                } else {
                    JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat mengupdate data Armada!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tambah Action Listener untuk tombol Delete
        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih baris untuk dihapus.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(view.table.getValueAt(selectedRow, 0).toString());

                // Panggil model untuk menghapus data armada
                boolean isDeleted = model.deleteArmada(id);
                if (isDeleted) {
                    updateTable();
                    JOptionPane.showMessageDialog(view, "Data Armada berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(view, "Terjadi kesalahan saat menghapus data Armada!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tambah Action Listener untuk tombol Cari
        view.btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cariTipe = view.textCari.getText().toLowerCase();
                List<String[]> hasilCari = model.getAllArmada(cariTipe);
                updateTableWithSearchResults(hasilCari);
            }
        });
    }

    // Fungsi untuk mengupdate tabel
    private void updateTable() {
        List<String[]> armadaList = model.getAllArmada(null); // Ambil semua data armada
        tableModel.setRowCount(0); // Clear existing data in the table

        // Loop through the armadaList and add rows to the table
        for (int i = 0; i < armadaList.size(); i++) {
            String[] armada = armadaList.get(i);
            tableModel.addRow(new Object[] {
                    armada[0], // ID
                    armada[1], // Nomor Kendaraan
                    armada[2], // Tipe Kendaraan
                    armada[3] // Kapasitas
            });
        }
    }

    // Fungsi untuk mengupdate tabel dengan hasil pencarian
    private void updateTableWithSearchResults(List<String[]> hasilCari) {
        tableModel.setRowCount(0); // Clear existing data in the table

        // Loop through the search results and add rows to the table
        for (int i = 0; i < hasilCari.size(); i++) {
            String[] armada = hasilCari.get(i);
            tableModel.addRow(new Object[] {
                    armada[0], // ID
                    armada[1], // Nomor Kendaraan
                    armada[2], // Tipe Kendaraan
                    armada[3] // Kapasitas
            });
        }
    }
}