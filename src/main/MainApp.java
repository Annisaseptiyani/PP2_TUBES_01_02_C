package main;

import view.*;
import controller.*;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Aplikasi Pengelolaan Data");
            mainFrame.setSize(800, 600);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTabbedPane tabbedPane = new JTabbedPane();

            PemesanView pemesanView = new PemesanView();
            new PemesanController(pemesanView);
            tabbedPane.addTab("Pemesan", pemesanView);

            JadwalView jadwalView = new JadwalView();
            new JadwalController(jadwalView);
            tabbedPane.addTab("Jadwal", jadwalView);

            ArmadaView armadaView = new ArmadaView();
            new ArmadaController(armadaView);
            tabbedPane.addTab("Armada", armadaView);

            mainFrame.add(tabbedPane);
            mainFrame.setVisible(true);
        });
    }
}