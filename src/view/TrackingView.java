// src/view/TrackingView.java
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import controller.TrackingController;

public class TrackingView extends JPanel {
  private TrackingController controller;

  public TrackingView() {
    controller = new TrackingController(this);
    setPreferredSize(new Dimension(controller.getWIDTH(), controller.getHEIGHT()));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    if (controller.getMapBackground() != null) {
      g2d.drawImage(controller.getMapBackground(), 0, 0, controller.getWIDTH(), controller.getHEIGHT(), this);
    } else {
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, controller.getWIDTH(), controller.getHEIGHT());
    }

    // Draw armada locations as circles
    for (TrackingController.LocationData data : controller.getArmadaLocations()) {
      g2d.setColor(new Color(0, 102, 204));
      Ellipse2D.Double circle = new Ellipse2D.Double(data.x - controller.getRADIUS() / 2.0,
          data.y - controller.getRADIUS() / 2.0, controller.getRADIUS(), controller.getRADIUS());
      g2d.fill(circle);
      g2d.setColor(Color.BLACK);
      g2d.drawString("Armada: " + data.name, data.x + controller.getRADIUS(), data.y);
    }

    for (TrackingController.LocationData data : controller.getJadwalLocations()) {
      g2d.setColor(new Color(204, 0, 0));
      g2d.fillRect(data.x - controller.getRADIUS() / 2, data.y - controller.getRADIUS() / 2, controller.getRADIUS(),
          controller.getRADIUS());
      g2d.setColor(Color.BLACK);
      g2d.drawString("Jadwal: " + data.name, data.x + controller.getRADIUS(), data.y);
    }
  }

  public void updateArmadaData(java.util.List<String[]> jadwalData) {
    controller.updateArmadaLocations(jadwalData);
  }

  public void updateJadwalData(java.util.List<String[]> jadwalData) {
    controller.updateJadwalLocations(jadwalData);
  }
}