package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JadwalView extends JPanel {
    public JTextField textNama, textTanggal, textLokasi;
    public JButton btnTambah, btnUpdate, btnDelete;
    public JTable table;
    public DefaultTableModel tableModel;

    public JadwalView() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Jadwal"));

        // Panel Nama
        JPanel namaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namaPanel.add(new JLabel("Nama:"));
        textNama = new JTextField(20);
        namaPanel.add(textNama);
        inputPanel.add(namaPanel);

        // Panel Tanggal
        JPanel tanggalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tanggalPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        textTanggal = new JTextField(20);
        tanggalPanel.add(textTanggal);
        inputPanel.add(tanggalPanel);

        // Panel Lokasi
        JPanel lokasiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lokasiPanel.add(new JLabel("Lokasi:"));
        textLokasi = new JTextField(20);
        lokasiPanel.add(textLokasi);
        inputPanel.add(lokasiPanel);

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
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Tanggal", "Lokasi"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Jadwal"));
        add(scrollPane, BorderLayout.CENTER);
    }
}
