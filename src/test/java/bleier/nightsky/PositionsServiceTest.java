package bleier.nightsky;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionsServiceTest {
    @Test
    public void positionNow() {
        PositionsService service = new PositionsServiceFactory().getPositionsService();

        ApiKey appId = new ApiKey("applicationid");
        ApiKey appSecret = new ApiKey("applicationsecret");
        String credentials = appId.get() + ":" + appSecret.get();
        String keyString = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        PositionsResponse response = service.positionsNow("40.538457", "-74.39450186737218", "0", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),  ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), keyString).blockingGet();

        assertTrue(response.data.table.rows[0].entry.name.equals("Sun"));

    }

}