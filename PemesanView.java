package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PemesanView extends JPanel {
    //mendeklarasikan komponen gui
    public JTextField textNama, textAlamat; //textNama: Input untuk nama pemesan.textAlamat: Input untuk alamat pemesan.
    public JComboBox<String> comboJenisSampah; //comboJenisSampah: Pilihan untuk jenis sampah.
    public JButton btnTambah, btnUpdate, btnDelete;//btnTambah: Tombol untuk menambah data.    btnUpdate: Tombol untuk memperbarui data.    btnDelete: Tombol untuk menghapus data
    public JTable table;//table: Tabel untuk menampilkan data pemesa
    public DefaultTableModel tableModel;//tableModel: Model tabel yang digunakan untuk mengelola data di tabel.

    public PemesanView() { //menampilkan tampilan grafis kepada pengguna
        setLayout(new BorderLayout(10, 10)); // Menambahkan ruang antara elemen di sekitar panel

        // Panel input
        JPanel inputPanel = createInputPanel();//JPanel sebagai wadah (container) yang mengelompokkan komponen-komponen seperti input teks, dropdown, dan tombol menjadi satu kesatuan yang terorganisasi.
        //Metode createInputPanel() didefinisikan secara terpisah untuk mengatur isi dari JPanel secara modular dan rapi.
        add(inputPanel, BorderLayout.NORTH);//Menambahkan panel inputPanel yang telah dibuat ke dalam tampilan utama dengan menggunakan tata letak BorderLayout.
        //BorderLayout.NORTH berarti panel inputPanel akan ditempatkan di bagian atas (utara) dari antarmuka pengguna.
      

        // Tabel dengan model
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Jenis Sampah"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();//Membuat objek JPanel baru yang akan digunakan sebagai wadah atau container untuk komponen-komponen input.
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));//mengatur tata letak komponen-komponen di dalam panel menggunakan BoxLayout Y_Axis yaitu susunan dari atas kebawah.
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//menambahkan pading atau ruang kosong di sekitar panel untuk memberikan jarak antar elemen 

        // Membuat elemen input untuk Nama
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));//embuat sebuah panel baru yang diberi nama namePanel.
        //Panel ini menggunakan FlowLayout sebagai layout manager dengan pengaturan FlowLayout.LEFT. FlowLayout.LEFT, elemen-elemen di dalam panel akan disusun secara horizontal dan rata kiri, seperti sebuah baris teks.
        namePanel.add(new JLabel("Nama:")); //Menambahkan sebuah JLabel (label teks) ke dalam namePanel.
        //Label ini akan menampilkan teks "Nama:" sebagai penanda untuk field input nama.
        textNama = new JTextField(20); //Membuat sebuah JTextField baru untuk menerima input teks dari pengguna.
        //20 adalah lebar (dalam jumlah karakter) dari JTextField, yang berarti field ini cukup lebar untuk menampung sekitar 20 karakter.
        namePanel.add(textNama);//Menambahkan JTextField (text field untuk input nama) ke dalam namePanel yang telah dibuat sebelumnya.
        //Sehingga di dalam panel ini, ada dua elemen: sebuah label ("Nama:") dan sebuah text field (textNama).
        panel.add(namePanel);//Menambahkan namePanel (yang berisi label dan text field untuk nama) ke dalam panel utama (panel).

        // Membuat elemen input untuk Alamat
        JPanel alamatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        alamatPanel.add(new JLabel("Alamat:"));
        textAlamat = new JTextField(20);
        alamatPanel.add(textAlamat);
        panel.add(alamatPanel);

        // Membuat elemen input untuk Jenis Sampah
        JPanel sampahPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sampahPanel.add(new JLabel("Jenis Sampah:"));
        comboJenisSampah = new JComboBox<>(new String[]{"Perangkat komputer", "peralatan rumah tangga", "Perangkat jaringan"});
        sampahPanel.add(comboJenisSampah);
        panel.add(sampahPanel);

        // Tombol-Tombol
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
