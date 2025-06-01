package bleier.nightsky;

import com.andrewoid.apikeys.ApiKey;

import javax.swing.*;
import java.awt.*;

public class NightskyFrame extends JFrame {

    private JTextField location = new JTextField(20);
    private GeocodingService geocodingService;
    private PositionsService positionsService;
    private NightskyController controller;
    private NightskyPanel panel;
    private JPanel locationPanel;


    public NightskyFrame() {
        setTitle("Sky");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        NightskyPanel panel = new NightskyPanel();
        add(panel, BorderLayout.CENTER);
        locationPanel = new JPanel();
        locationPanel.add(location);
        add(locationPanel, BorderLayout.SOUTH);



        geocodingService = new GeocodingServiceFactory().getGeocodingservice();
        positionsService = new PositionsServiceFactory().getPositionsService();
        ApiKey apiKey = new ApiKey();
        ApiKey appId = new ApiKey("applicationid");
        ApiKey appSecret = new ApiKey("applicationsecret");

        controller = new NightskyController(geocodingService, positionsService,
                panel, location, apiKey, appId, appSecret);



    }

    public static void main(String[] args) {
        NightskyFrame frame = new NightskyFrame();
        frame.setVisible(true);
    }
}
