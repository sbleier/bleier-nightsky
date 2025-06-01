package bleier.nightsky;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
        @GET("/geo/1.0/direct?")
        Single<java.util.List<GeocodingResponse>> geocodingNow(
                @Query("q") String query,
                @Query("appid") String apikey
        );
}
