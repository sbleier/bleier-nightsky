package bleier.nightsky;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeocodingServiceTest {


    @Test
    public void geocodingNow() {
        GeocodingService service = new GeocodingServiceFactory().getGeocodingService();

        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        List<GeocodingResponse> responses = service.geocodingNow("Edison", keyString).blockingGet();

        assertTrue(responses.get(0).lat.equals("40.538457"));
        assertTrue(responses.get(0).lon.equals("-74.39450186737218"));

    }
}