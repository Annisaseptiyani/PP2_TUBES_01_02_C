// src/view/TrackingView.java
package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackingView extends JPanel {

  private static final int WIDTH = 600;
  private static final int HEIGHT = 400;
  private static final int RADIUS = 15;
  private List<LocationData> armadaLocations = new ArrayList<>();
  private List<LocationData> jadwalLocations = new ArrayList<>();
  private Random random = new Random();
  private BufferedImage mapBackground;
  private static final String MAP_IMAGE_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9wTjoAwHaEOBIe3iYCidbeZgJyPPkB0kThg&s"; // Replace
                                                                                                                                              // with
                                                                                                                                              // your
                                                                                                                                              // URL
  private static final Logger LOGGER = Logger.getLogger(TrackingView.class.getName());

  public TrackingView() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    try {
      URL imageUrl = new URL(MAP_IMAGE_URL);
      mapBackground = ImageIO.read(imageUrl);
      if (mapBackground == null) {
        System.err.println("Could not load map image from url:" + MAP_IMAGE_URL);
      }

    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error loading map image", e);
      System.err.println("IOException while loading image: " + e.getMessage());
      mapBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create empty image
    }

    startLocationUpdates();
  }

  public void updateArmadaLocations(List<String[]> jadwalData) {
    armadaLocations.clear();
    for (String[] jadwal : jadwalData) {
      if (jadwal[3] != null) {
        armadaLocations.add(new LocationData(
            jadwal[0], // jadwal id
            jadwal[3], // location
            (jadwal[6] != null) ? jadwal[6] : "Unknown", // armada name
            random.nextInt(WIDTH - 2 * RADIUS) + RADIUS,
            random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS));
      }
    }
    repaint();
  }

  public void updateJadwalLocations(List<String[]> jadwalData) {
    jadwalLocations.clear();
    for (String[] jadwal : jadwalData) {
      if (jadwal[3] != null) {
        jadwalLocations.add(new LocationData(
            jadwal[0], // jadwal ID
            jadwal[3], // location
            (jadwal[1] != null) ? jadwal[1] : "Unknown", // jadwal name
            random.nextInt(WIDTH - 2 * RADIUS) + RADIUS,
            random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS));
      }
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Draw the map background
    if (mapBackground != null) {
      g2d.drawImage(mapBackground, 0, 0, WIDTH, HEIGHT, this);
    } else {
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, WIDTH, HEIGHT); // If no background is provided, draw a solid background
    }

    // Draw armada locations as circles
    for (LocationData data : armadaLocations) {
      g2d.setColor(new Color(0, 102, 204));
      Ellipse2D.Double circle = new Ellipse2D.Double(data.x - RADIUS / 2.0, data.y - RADIUS / 2.0, RADIUS, RADIUS);
      g2d.fill(circle);
      g2d.setColor(Color.BLACK);
      g2d.drawString("Armada: " + data.name, data.x + RADIUS, data.y);

    }

    // Draw jadwal locations as rectangles
    for (LocationData data : jadwalLocations) {
      g2d.setColor(new Color(204, 0, 0));
      g2d.fillRect(data.x - RADIUS / 2, data.y - RADIUS / 2, RADIUS, RADIUS);
      g2d.setColor(Color.BLACK);
      g2d.drawString("Jadwal: " + data.name, data.x + RADIUS, data.y);
    }
  }

  private void startLocationUpdates() {
    Timer timer = new Timer(1000, e -> {
      // You would normally get this from a GPS or location service
      for (LocationData data : armadaLocations) {
        data.x = random.nextInt(WIDTH - 2 * RADIUS) + RADIUS;
        data.y = random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS;
      }
      for (LocationData data : jadwalLocations) {
        data.x = random.nextInt(WIDTH - 2 * RADIUS) + RADIUS;
        data.y = random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS;
      }
      repaint();
    });
    timer.start();
  }

  private static class LocationData {
    String id;
    String location;
    String name;
    int x;
    int y;

    public LocationData(String id, String location, String name, int x, int y) {
      this.id = id;
      this.location = location;
      this.name = name;
      this.x = x;
      this.y = y;
    }
  }
}