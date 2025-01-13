package controller;

import model.Pemesan;
import model.PemesanMapper;
import util.MyBatisUtil;
import view.PemesanView;

import org.apache.ibatis.session.SqlSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

public class PemesanController {
    private PemesanView view;

    public PemesanController(PemesanView view) {
        this.view = view;

        loadTableData();
        setupListeners();
    }

    private void setupListeners() {
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
            try (SqlSession session = MyBatisUtil.openSession()) {
                PemesanMapper mapper = session.getMapper(PemesanMapper.class);

                Pemesan pemesan = new Pemesan();
                pemesan.setNama(nama);
                pemesan.setAlamat(alamat);
                pemesan.setJenisSampah(jenis);

                if (mapper.addPemesan(pemesan) > 0) {
                    session.commit();
                    view.showMessage("Data berhasil ditambahkan!");
                    loadTableData();
                } else {
                    view.showError("Gagal menambahkan data!");
                }
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
            try (SqlSession session = MyBatisUtil.openSession()) {
                PemesanMapper mapper = session.getMapper(PemesanMapper.class);

                Pemesan pemesan = new Pemesan();
                pemesan.setId(id);
                pemesan.setNama(nama);
                pemesan.setAlamat(alamat);
                pemesan.setJenisSampah(jenis);

                if (mapper.updatePemesan(pemesan) > 0) {
                    session.commit();
                    view.showMessage("Data berhasil diupdate!");
                    loadTableData();
                } else {
                    view.showError("Gagal mengupdate data!");
                }
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

        try (SqlSession session = MyBatisUtil.openSession()) {
            PemesanMapper mapper = session.getMapper(PemesanMapper.class);

            if (mapper.deletePemesan(id) > 0) {
                session.commit();
                view.showMessage("Data berhasil dihapus!");
                loadTableData();
            } else {
                view.showError("Gagal menghapus data!");
            }
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
        view.tableModel.setRowCount(0);
        try (SqlSession session = MyBatisUtil.openSession()) {
            PemesanMapper mapper = session.getMapper(PemesanMapper.class);
            List<Pemesan> pemesananList = mapper.getAllPemesan();

            for (Pemesan pemesanan : pemesananList) {
                view.tableModel.addRow(new Object[]{
                        pemesanan.getId(),
                        pemesanan.getNama(),
                        pemesanan.getAlamat(),
                        pemesanan.getJenisSampah()
                });
            }
        }
    }

        public static void main(String[] args) {
        PemesanView view = new PemesanView();
        new PemesanController(view);
        JFrame testFrame = new JFrame("Test Pemesan");
        testFrame.setContentPane(view);
        testFrame.setSize(400, 300);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    }

}
