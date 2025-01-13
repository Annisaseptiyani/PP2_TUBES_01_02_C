package controller;

import model.Armada;
import model.ArmadaMapper;

import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtil;
import view.ArmadaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ArmadaController {
    private final ArmadaView view;
    private final DefaultTableModel tableModel;

    public ArmadaController(ArmadaView view) {
        this.view = view;
        this.tableModel = (DefaultTableModel) view.table.getModel();
        initialize();
        updateTable();
    }

    private void initialize() {
        view.btnTambah.addActionListener(e -> tambahArmada());
        view.btnUpdate.addActionListener(e -> updateArmada());
        view.btnDelete.addActionListener(e -> deleteArmada());
        view.btnCari.addActionListener(e -> cariArmada());
    }

    private void tambahArmada() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ArmadaMapper mapper = session.getMapper(ArmadaMapper.class);

            String nomorKendaraan = view.textNomorKendaraan.getText();
            String tipeKendaraan = (String) view.comboTipeKendaraan.getSelectedItem();
            int kapasitas = Integer.parseInt((String) view.comboKapasitas.getSelectedItem());

            Armada armada = new Armada(nomorKendaraan, tipeKendaraan, kapasitas);
            int result = mapper.addArmada(armada);

            if (result > 0) {
                session.commit();
                updateTable();
                JOptionPane.showMessageDialog(view, "Armada berhasil ditambahkan!");
            } else {
                showError("Gagal menambahkan armada!");
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void updateArmada() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ArmadaMapper mapper = session.getMapper(ArmadaMapper.class);

            int selectedRow = view.table.getSelectedRow();
            if (selectedRow == -1) {
                showError("Pilih data yang akan diupdate!");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String nomorKendaraan = view.textNomorKendaraan.getText();
            String tipeKendaraan = (String) view.comboTipeKendaraan.getSelectedItem();
            int kapasitas = Integer.parseInt((String) view.comboKapasitas.getSelectedItem());

            Armada armada = new Armada(id, nomorKendaraan, tipeKendaraan, kapasitas);
            int result = mapper.updateArmada(armada);

            if (result > 0) {
                session.commit();
                updateTable();
                JOptionPane.showMessageDialog(view, "Armada berhasil diupdate!");
            } else {
                showError("Gagal mengupdate armada!");
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void deleteArmada() {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ArmadaMapper mapper = session.getMapper(ArmadaMapper.class);

            int selectedRow = view.table.getSelectedRow();
            if (selectedRow == -1) {
                showError("Pilih data yang akan dihapus!");
                return;
            }

            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int result = mapper.deleteArmada(id);

            if (result > 0) {
                session.commit();
                updateTable();
                JOptionPane.showMessageDialog(view, "Armada berhasil dihapus!");
            } else {
                showError("Gagal menghapus armada!");
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void cariArmada() {
        String filter = view.textCari.getText();
        updateTable(filter);
    }

    private void updateTable() {
        updateTable(null);
    }

    private void updateTable(String filter) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            ArmadaMapper mapper = session.getMapper(ArmadaMapper.class);
            List<Armada> armadaList = (filter == null || filter.isEmpty())
                    ? mapper.getAllArmada()
                    : mapper.getAllArmadaByFilter(filter);

            tableModel.setRowCount(0);

            for (Armada armada : armadaList) {
                tableModel.addRow(new Object[]{
                        armada.getId(),
                        armada.getNomorKendaraan(),
                        armada.getTipeKendaraan(),
                        armada.getKapasitas()
                });
            }
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
