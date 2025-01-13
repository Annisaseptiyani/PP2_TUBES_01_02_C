// src/view/JadwalView.java
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JadwalView extends JPanel {
    public JTextField textNama, textTanggal, textLokasi;
    public JComboBox<String> comboArmada, comboPemesan;
    public JButton btnTambah, btnUpdate, btnDelete;
    public JTable table;
    public DefaultTableModel tableModel;

    public JadwalView() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // Panel Nama
        JPanel namaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namaPanel.add(new JLabel("Nama:"));
        textNama = new JTextField(15);
        namaPanel.add(textNama);
        inputPanel.add(namaPanel);

        // Panel Tanggal
        JPanel tanggalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tanggalPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        textTanggal = new JTextField(15);
        tanggalPanel.add(textTanggal);
        inputPanel.add(tanggalPanel);

        // Panel Lokasi
        JPanel lokasiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lokasiPanel.add(new JLabel("Lokasi:"));
        textLokasi = new JTextField(15);
        lokasiPanel.add(textLokasi);
        inputPanel.add(lokasiPanel);

        // Panel Armada
        JPanel armadaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        armadaPanel.add(new JLabel("Armada:"));
        comboArmada = new JComboBox<>();
        armadaPanel.add(comboArmada);
        inputPanel.add(armadaPanel);

        // Panel Pemesan
        JPanel pemesanPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pemesanPanel.add(new JLabel("Pemesan:"));
        comboPemesan = new JComboBox<>();
        pemesanPanel.add(comboPemesan);
        inputPanel.add(pemesanPanel);

        // Panel Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        inputPanel.add(buttonPanel);

        add(inputPanel, BorderLayout.NORTH);

        // Tabel Setup
        tableModel = new DefaultTableModel(
                new String[] { "ID", "Nama", "Tanggal", "Lokasi", "Nomor Kendaraan", "Armada", "Pemesan" }, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}