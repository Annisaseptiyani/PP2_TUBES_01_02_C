package controller;

import model.PemesanModel;
import view.PemesanView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PemesanController {
    private PemesanModel model;
    private PemesanView view;

    public PemesanController(PemesanModel model, PemesanView view) {
        this.model = model;
        this.view = view;

        loadTableData();
        setupListeners();
    }

    private void setupListeners() {
        // Pastikan hanya menambahkan listener sekali
        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPemesan();
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePemesan();
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePemesan();
            }
        });
    }

    private void addPemesan() {
        String nama = view.textNama.getText();
        String alamat = view.textAlamat.getText();
        String jenis = view.comboJenisSampah.getSelectedItem().toString();

        if (validateInput(nama, alamat, jenis)) {
            if (model.addPemesan(nama, alamat, jenis)) {
                view.showMessage("Data berhasil ditambahkan!");
                loadTableData(); // Refresh table data setelah berhasil menambahkan data
            } else {
                view.showError("Gagal menambahkan data!");
            }
        }
    }

    private void updatePemesan() {
        int selectedRow = view.table.getSelectedRow();
        if (selectedRow == -1) {
            view.showError("Pilih data yang akan diupdate!");
            return;
        }

        int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
        String nama = view.textNama.getText();
        String alamat = view.textAlamat.getText();
        String jenis = view.comboJenisSampah.getSelectedItem().toString();

        if (validateInput(nama, alamat, jenis)) {
            if (model.updatePemesan(id, nama, alamat, jenis)) {
                view.showMessage("Data berhasil diupdate!");
                loadTableData(); // Refresh table data setelah berhasil mengupdate data
            } else {
                view.showError("Gagal mengupdate data!");
            }
        }
    }

    private void deletePemesan() {
        int selectedRow = view.table.getSelectedRow();
        if (selectedRow == -1) {
            view.showError("Pilih data yang akan dihapus!");
            return;
        }

        int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
        if (model.deletePemesan(id)) {
            view.showMessage("Data berhasil dihapus!");
            loadTableData(); // Refresh table data setelah berhasil menghapus data
        } else {
            view.showError("Gagal menghapus data!");
        }
    }

    private boolean validateInput(String nama, String alamat, String jenis) {
        if (nama.isEmpty() || alamat.isEmpty() || jenis.isEmpty()) {
            view.showError("Semua field harus diisi.");
            return false;
        }
        return true;
    }

    private void loadTableData() {
        view.tableModel.setRowCount(0); // Reset tabel sebelum menambahkan data
        for (String[] row : model.getAllPemesan()) {
            view.tableModel.addRow(row); // Menambahkan data ke tabel
        }
    }
}
