package controller;

import model.Jadwal;
import model.JadwalMapper;

import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtil;
import view.JadwalView;

import javax.swing.*;
import java.util.List;

public class JadwalController {
    private JadwalView view;

    public JadwalController(JadwalView view) {
        this.view = view;

        loadTableData();

        view.btnTambah.addActionListener(e -> {
            String nama = view.textNama.getText().trim();
            String tanggal = view.textTanggal.getText().trim();
            String lokasi = view.textLokasi.getText().trim();

            if (validateInput(nama, tanggal, lokasi)) {
                try (SqlSession session = MyBatisUtil.openSession()) { // Ganti ke openSession()
                    JadwalMapper mapper = session.getMapper(JadwalMapper.class);
                    Jadwal jadwal = new Jadwal(nama, tanggal, lokasi);
                    if (mapper.addJadwal(jadwal) > 0) {
                        session.commit();
                        JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan!");
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(view, "Gagal menambahkan data!");
                    }
                }
            }
        });

        view.btnUpdate.addActionListener(e -> {
            int selectedRow = view.table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan diupdate!");
                return;
            }

            int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
            String nama = view.textNama.getText().trim();
            String tanggal = view.textTanggal.getText().trim();
            String lokasi = view.textLokasi.getText().trim();

            if (validateInput(nama, tanggal, lokasi)) {
                try (SqlSession session = MyBatisUtil.openSession()) { // Ganti ke openSession()
                    JadwalMapper mapper = session.getMapper(JadwalMapper.class);
                    Jadwal jadwal = new Jadwal(id, nama, tanggal, lokasi);
                    if (mapper.updateJadwal(jadwal) > 0) {
                        session.commit();
                        JOptionPane.showMessageDialog(view, "Data berhasil diupdate!");
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(view, "Gagal mengupdate data!");
                    }
                }
            }
        });

        view.btnDelete.addActionListener(e -> {
            int selectedRow = view.table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus!");
                return;
            }

            int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
            try (SqlSession session = MyBatisUtil.openSession()) { // Ganti ke openSession()
                JadwalMapper mapper = session.getMapper(JadwalMapper.class);
                if (mapper.deleteJadwal(id) > 0) {
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menghapus data!");
                }
            }
        });
    }

    private void loadTableData() {
        view.tableModel.setRowCount(0);
        try (SqlSession session = MyBatisUtil.openSession()) { // Ganti ke openSession()
            JadwalMapper mapper = session.getMapper(JadwalMapper.class);
            List<Jadwal> jadwalList = mapper.getAllJadwal();
            for (Jadwal jadwal : jadwalList) {
                view.tableModel.addRow(new Object[]{
                        jadwal.getId(),
                        jadwal.getNama(),
                        jadwal.getTanggal(),
                        jadwal.getLokasi()
                });
            }
        }
    }

    private boolean validateInput(String nama, String tanggal, String lokasi) {
        if (nama.isEmpty() || tanggal.isEmpty() || lokasi.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!");
            return false;
        }
        if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(view, "Format tanggal harus YYYY-MM-DD!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JadwalView view = new JadwalView();
        new JadwalController(view);
        JFrame testFrame = new JFrame("Test Jadwal");
        testFrame.setContentPane(view);
        testFrame.setSize(400, 300);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    }
}
