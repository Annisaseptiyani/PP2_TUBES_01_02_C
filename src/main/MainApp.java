// src/main/MainApp.java
package main;

import model.*;
import view.*;
import controller.*;
import javax.swing.*;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Membuat frame utama
            JFrame mainFrame = new JFrame("Aplikasi Pengelolaan Data");
            mainFrame.setSize(800, 600);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Membuat tabbed pane untuk menampung semua view
            JTabbedPane tabbedPane = new JTabbedPane();

            // Pemesan
            PemesanModel pemesanModel = new PemesanModel();
            PemesanView pemesanView = new PemesanView();
            new PemesanController(pemesanModel, pemesanView);
            tabbedPane.addTab("Pemesan", pemesanView);

            // Armada
            ArmadaModel armadaModel = new ArmadaModel();
            ArmadaView armadaView = new ArmadaView();
            new ArmadaController(armadaView, armadaModel); // Perbaiki urutan parameter di sini
            tabbedPane.addTab("Armada", armadaView);

            // Jadwal
            JadwalModel jadwalModel = new JadwalModel();
            JadwalView jadwalView = new JadwalView();
            new JadwalController(jadwalModel, jadwalView, armadaModel, pemesanModel);
            tabbedPane.addTab("Jadwal", jadwalView);

            // Tracking
            TrackingView trackingView = new TrackingView();
            tabbedPane.addTab("Tracking", trackingView);

            // Menambahkan tabbed pane ke frame utama
            mainFrame.add(tabbedPane);
            mainFrame.setVisible(true);

            // Update tracking data every time the Jadwal tab is selected
            tabbedPane.addChangeListener(e -> {
                if (tabbedPane.getSelectedComponent() == jadwalView) {
                    List<String[]> jadwalData = jadwalModel.getAllJadwal();
                    trackingView.updateArmadaLocations(jadwalData);
                    trackingView.updateJadwalLocations(jadwalData);
                }

                if (tabbedPane.getSelectedComponent() == trackingView) {
                    List<String[]> jadwalData = jadwalModel.getAllJadwal();
                    trackingView.updateArmadaLocations(jadwalData);
                    trackingView.updateJadwalLocations(jadwalData);
                }
            });
        });
    }
}