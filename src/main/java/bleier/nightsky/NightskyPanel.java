package bleier.nightsky;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NightskyPanel extends JPanel {

    private List<Planet> planets;

    public NightskyPanel() {
        setBackground(Color.BLACK);
    }

    public void setPlanets(java.util.List<Planet> planets) {
        this.planets = planets;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Example: Draw a sky grid
        g.setColor(Color.YELLOW);
        for (int x = 0; x < width; x += width / 4) {
            g.drawLine(x, 0, x, height);
        }
        g.drawLine(0, height / 2, width, height / 2);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.PLAIN, 14));

        g.drawString("Horizon", width / 2 - 30, height / 2 - 5);

        g.drawString("South", 10, height - 5); // Bottom-left corner
        g.drawString("West", width / 4 - 20, height - 5); // Under 1st vertical line
        g.drawString("North", width / 2 - 20, height - 5); // Under 2nd vertical line
        g.drawString("East", width * 3 / 4 - 20, height - 5); // Under 3rd vertical line
        g.drawString("South", width - 45, height - 5); // Bottom-right corner

        if (planets != null) {
            for (Planet planet : planets) {
                // Map adjusted azimuth to x position
                int x = (int) ((planet.getAzimuth() / 360.0) * getWidth());
                int y = (int) ((90 - planet.getAltitude()) / 180.0 * getHeight());

                g.setColor(Color.WHITE);
                g.fillOval(x - 5, y - 5, 10, 10);
                g.setColor(Color.RED);
                g.drawString(planet.getName(), x - 10, y - 10);
            }
        }
    }




}

