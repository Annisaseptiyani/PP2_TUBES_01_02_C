package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ArmadaView extends JPanel {
    public JTextField textNomorKendaraan, textCari;
    public JComboBox<String> comboTipeKendaraan, comboKapasitas;
    public JButton btnTambah, btnUpdate, btnDelete, btnCari;
    public JTable table;
    public DefaultTableModel tableModel;

    public ArmadaView() {
        setLayout(new BorderLayout(10, 10)); // Menambahkan jarak antar komponen

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());  // Menggunakan GridBagLayout untuk kontrol yang lebih fleksibel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Menambahkan padding antar komponen

        // Menambahkan komponen input di panel
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form Armada"));

        JLabel lblNomor = new JLabel("Nomor Kendaraan:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  // Menjaga label berada di kiri
        inputPanel.add(lblNomor, gbc);

        textNomorKendaraan = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;  // Menjaga input tetap sejajar dengan label
        inputPanel.add(textNomorKendaraan, gbc);

        JLabel lblTipe = new JLabel("Tipe Kendaraan:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblTipe, gbc);

        // Dropdown untuk Tipe Kendaraan
        String[] tipeKendaraanOptions = {"Bus", "Truck", "Roda", "Sepeda Motor"};
        comboTipeKendaraan = new JComboBox<>(tipeKendaraanOptions);
        gbc.gridx = 1;
        inputPanel.add(comboTipeKendaraan, gbc);

        JLabel lblKapasitas = new JLabel("Kapasitas:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(lblKapasitas, gbc);

        // Dropdown untuk Kapasitas
        String[] kapasitasOptions = {"5", "10", "15", "20", "25"};
        comboKapasitas = new JComboBox<>(kapasitasOptions);
        gbc.gridx = 1;
        inputPanel.add(comboKapasitas, gbc);

        // Tombol-Tombol
        btnTambah = new JButton("Tambah");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(btnTambah, gbc);

        btnUpdate = new JButton("Update");
        gbc.gridx = 1;
        inputPanel.add(btnUpdate, gbc);

        btnDelete = new JButton("Delete");
        gbc.gridx = 2;
        inputPanel.add(btnDelete, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // Panel Tabel
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(new String[]{"ID", "Nomor Kendaraan", "Tipe Kendaraan", "Kapasitas"}, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    

        // Atur kolom agar lebih lebar dan rapi
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // Lebar kolom ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Lebar kolom Nomor Kendaraan
        table.getColumnModel().getColumn(2).setPreferredWidth(150);  // Lebar kolom Tipe Kendaraan
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Lebar kolom Kapasitas

        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel Pencarian (Search)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Cari Armada"));
        searchPanel.add(new JLabel("Tipe Kendaraan:"));
        textCari = new JTextField(15);  // Deklarasi textCari
        searchPanel.add(textCari);
        btnCari = new JButton("Cari");
        searchPanel.add(btnCari);

        tablePanel.add(searchPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }
}
