package bleier.nightsky;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class NightskyController {
    private JTextField location = new JTextField();
    private GeocodingService geocodingService;
    private PositionsService positionsService;
    private ApiKey apiKey;
    private ApiKey appId;
    private ApiKey appSecret;
    private NightskyPanel panel;

    public NightskyController(GeocodingService service, PositionsService positionsService,
                              NightskyPanel panel, JTextField location, ApiKey apiKey,
                              ApiKey appId, ApiKey appSecret) {
        this.geocodingService = service;
        this.positionsService = positionsService;
        this.panel = panel;
        this.apiKey = apiKey;
        this.appId = appId;
        this.appSecret = appSecret;
        this.location = location;


        location.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display();
            }
        });
    }

    public void display() {

        String keyString = apiKey.get();
        Disposable disposable = geocodingService.geocodingNow(location.getText().trim(), keyString)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        responseList -> {
                            GeocodingResponse response = responseList.get(0);
                            // Call astronomy API with lat/lon
                            fetchPositionsData(response.lat, response.lon);
                        },
                            Throwable::printStackTrace);
    }

    private void fetchPositionsData(String lat, String lon) {
        String authHeader = createAuthHeader();

        ZonedDateTime now = ZonedDateTime.now();

        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));


        Disposable psDisposable = positionsService.positionsNow(lat, lon, "0", date, date, time, authHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        positionsResponse -> {
                            handleResponse(positionsResponse);
                        },
                        Throwable::printStackTrace
                );
    }

    private void handleResponse(PositionsResponse response) {
        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < response.data.table.rows.length; i++) {
            Planet planet = new Planet(response.data.table.rows[i].entry.id,
                    response.data.table.rows[i].entry.name,
                    response.data.table.rows[i].cells[0].position.horizontal.altitude.degrees,
                    response.data.table.rows[i].cells[0].position.horizontal.azimuth.degrees);

            planets.add(planet);
            String id = response.data.table.rows[i].entry.id;
            String name = response.data.table.rows[i].entry.name;
            String altitude = response.data.table.rows[i].cells[0].position.horizontal.altitude.degrees;
            String azimuth = response.data.table.rows[i].cells[0].position.horizontal.azimuth.degrees;

            System.out.println("Planet from API response:");
            System.out.println("  ID:       " + id);
            System.out.println("  Name:     " + name);
            System.out.println("  Altitude: " + altitude);
            System.out.println("  Azimuth:  " + azimuth);
        }
        panel.setPlanets(planets);
        panel.repaint();



    }

    private String createAuthHeader() {
        String credentials = appId.get() + ":" + appSecret.get();
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
