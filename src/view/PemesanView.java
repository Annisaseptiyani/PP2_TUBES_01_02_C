package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PemesanView extends JPanel {
    public JTextField textNama, textAlamat;
    public JComboBox<String> comboJenisSampah;
    public JButton btnTambah, btnUpdate, btnDelete;
    public JTable table;
    public DefaultTableModel tableModel;

    public PemesanView() {
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Jenis Sampah"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Nama:"));
        textNama = new JTextField(20);
        namePanel.add(textNama);
        panel.add(namePanel);

        JPanel alamatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        alamatPanel.add(new JLabel("Alamat:"));
        textAlamat = new JTextField(20);
        alamatPanel.add(textAlamat);
        panel.add(alamatPanel);

        JPanel sampahPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sampahPanel.add(new JLabel("Jenis Sampah:"));
        comboJenisSampah = new JComboBox<>(new String[]{"Perangkat komputer", "Peralatan rumah tangga", "Perangkat jaringan"});
        sampahPanel.add(comboJenisSampah);
        panel.add(sampahPanel);

        JPanel buttonPanel = new JPanel();
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel);

        return panel;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
