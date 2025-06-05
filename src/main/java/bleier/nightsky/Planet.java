package bleier.nightsky;

public class Planet {

    private String id;
    private String name;
    private double altitude;
    private double azimuth;

    public Planet(String id, String name, String altitude, String azimuth) {
        this.id = id;
        this.name = name;
        this.altitude = Double.parseDouble(altitude);
        this.azimuth = Double.parseDouble(azimuth);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
