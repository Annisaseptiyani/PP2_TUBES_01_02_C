// src/controller/JadwalController.java
package controller;

import model.JadwalModel;
import model.ArmadaModel;
import model.PemesanModel;
import view.JadwalView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JadwalController {
    private JadwalModel model;
    private JadwalView view;
    private ArmadaModel armadaModel;
    private PemesanModel pemesanModel;

    public JadwalController(JadwalModel model, JadwalView view, ArmadaModel armadaModel, PemesanModel pemesanModel) {
        this.model = model;
        this.view = view;
        this.armadaModel = armadaModel;
        this.pemesanModel = pemesanModel;

        loadTableData();
        loadComboBoxData();

        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.textNama.getText().trim();
                String tanggal = view.textTanggal.getText().trim();
                String lokasi = view.textLokasi.getText().trim();
                Object selectedArmada = view.comboArmada.getSelectedItem();
                Object selectedPemesan = view.comboPemesan.getSelectedItem();

                // Validate input
                if (nama.isEmpty() || tanggal.isEmpty() || lokasi.isEmpty() || selectedArmada == null
                        || selectedPemesan == null) {
                    JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!");
                    return;
                }

                // Validate date format
                if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(view, "Format tanggal harus YYYY-MM-DD!");
                    return;
                }

                int armadaId = Integer.parseInt(selectedArmada.toString().split(" - ")[0]);
                int pemesanId = Integer.parseInt(selectedPemesan.toString().split(" - ")[0]);

                if (model.addJadwal(nama, tanggal, lokasi, armadaId, pemesanId)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menambahkan data!");
                }
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih data yang akan diupdate!");
                    return;
                }

                Object selectedArmada = view.comboArmada.getSelectedItem();
                Object selectedPemesan = view.comboPemesan.getSelectedItem();

                int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
                String nama = view.textNama.getText().trim();
                String tanggal = view.textTanggal.getText().trim();
                String lokasi = view.textLokasi.getText().trim();

                // Validate input
                if (nama.isEmpty() || tanggal.isEmpty() || lokasi.isEmpty() || selectedArmada == null
                        || selectedPemesan == null) {
                    JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!");
                    return;
                }

                // Validate date format
                if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(view, "Format tanggal harus YYYY-MM-DD!");
                    return;
                }

                int armadaId = Integer.parseInt(selectedArmada.toString().split(" - ")[0]);
                int pemesanId = Integer.parseInt(selectedPemesan.toString().split(" - ")[0]);

                if (model.updateJadwal(id, nama, tanggal, lokasi, armadaId, pemesanId)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil diupdate!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal mengupdate data!");
                }
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus!");
                    return;
                }

                int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
                if (model.deleteJadwal(id)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menghapus data!");
                }
            }
        });
    }

    private void loadComboBoxData() {
        System.out.println("Loading combobox data...");
        view.comboArmada.removeAllItems();
        view.comboPemesan.removeAllItems();

        List<String[]> armadaList = armadaModel.getAllArmada(null);
        System.out.println("Armada data retrieved: " + armadaList.size() + " records");
        for (String[] armada : armadaList) {
            System.out.println("Adding armada to combobox: " + armada[0] + " - " + armada[1]);
            view.comboArmada.addItem(armada[0] + " - " + armada[1]); // Display "id - nomor_kendaraan"
        }

        List<String[]> pemesanList = pemesanModel.getAllPemesan();
        System.out.println("Pemesan data retrieved: " + pemesanList.size() + " records");
        for (String[] pemesan : pemesanList) {
            System.out.println("Adding pemesan to combobox: " + pemesan[0] + " - " + pemesan[1]);
            view.comboPemesan.addItem(pemesan[0] + " - " + pemesan[1]); // Display "id - nama"
        }
        System.out.println("Finished loading combobox data.");
    }

    private void loadTableData() {
        view.tableModel.setRowCount(0);
        for (String[] row : model.getAllJadwal()) {
            view.tableModel.addRow(new Object[] {
                    row[0], // ID
                    row[1], // Nama
                    row[2], // Tanggal
                    row[3], // Lokasi
                    row[6], // Nomor Kendaraan
                    row[7] // Nama Pemesan
            });
        }
    }
}