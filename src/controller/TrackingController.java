// src/controller/TrackingController.java
package controller;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import view.TrackingView;

public class TrackingController {

  private static final int WIDTH = 600;
  private static final int HEIGHT = 400;
  private static final int RADIUS = 15;
  private List<LocationData> armadaLocations = new ArrayList<>();
  private List<LocationData> jadwalLocations = new ArrayList<>();
  private Random random = new Random();
  private BufferedImage mapBackground;
  private static final String MAP_IMAGE_URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9wTjoAwHaEOBIe3iYCidbeZgJyPPkB0kThg&s";
  private static final Logger LOGGER = Logger.getLogger(TrackingController.class.getName());
  private TrackingView view;

  public TrackingController(TrackingView view) {
    this.view = view;
    loadMapImage();
    startLocationUpdates();
  }

  private void loadMapImage() {
    try {
      URL imageUrl = new URL(MAP_IMAGE_URL);
      mapBackground = ImageIO.read(imageUrl);
      if (mapBackground == null) {
        System.err.println("Could not load map image from url:" + MAP_IMAGE_URL);
      }
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE, "Error loading map image", e);
      System.err.println("IOException while loading image: " + e.getMessage());
      mapBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }
  }

  public BufferedImage getMapBackground() {
    return mapBackground;
  }

  public int getWIDTH() {
    return WIDTH;
  }

  public int getHEIGHT() {
    return HEIGHT;
  }

  public int getRADIUS() {
    return RADIUS;
  }

  public List<LocationData> getArmadaLocations() {
    return armadaLocations;
  }

  public List<LocationData> getJadwalLocations() {
    return jadwalLocations;
  }

  public void updateArmadaLocations(List<String[]> jadwalData) {
    armadaLocations.clear();
    for (String[] jadwal : jadwalData) {
      if (jadwal[3] != null) {
        armadaLocations.add(new LocationData(
            jadwal[0],
            jadwal[3],
            (jadwal[6] != null) ? jadwal[6] : "Unknown",
            random.nextInt(WIDTH - 2 * RADIUS) + RADIUS,
            random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS));
      }
    }
    view.repaint();
  }

  public void updateJadwalLocations(List<String[]> jadwalData) {
    jadwalLocations.clear();
    for (String[] jadwal : jadwalData) {
      if (jadwal[3] != null) {
        jadwalLocations.add(new LocationData(
            jadwal[0],
            jadwal[3],
            (jadwal[1] != null) ? jadwal[1] : "Unknown",
            random.nextInt(WIDTH - 2 * RADIUS) + RADIUS,
            random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS));
      }
    }
    view.repaint();
  }

  private void startLocationUpdates() {
    Timer timer = new Timer(1000, e -> {
      for (LocationData data : armadaLocations) {
        data.x = random.nextInt(WIDTH - 2 * RADIUS) + RADIUS;
        data.y = random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS;
      }
      for (LocationData data : jadwalLocations) {
        data.x = random.nextInt(WIDTH - 2 * RADIUS) + RADIUS;
        data.y = random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS;
      }
      view.repaint();
    });
    timer.start();
  }

  public static class LocationData {
    public String id;
    public String location;
    public String name;
    public int x;
    public int y;

    public LocationData(String id, String location, String name, int x, int y) {
      this.id = id;
      this.location = location;
      this.name = name;
      this.x = x;
      this.y = y;
    }
  }
}