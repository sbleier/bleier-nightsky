package bleier.nightsky;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.util.List;

public interface PositionsService {
        @GET("/api/v2/bodies/positions")
        Single<PositionsResponse> positionsNow(
                @Query("latitude") String latitude,
                @Query("longitude") String longitude,
                @Query("elevation") String elevation,
                @Query("from_date") String from_date,
                @Query("to_date") String to_date,
                @Query("time") String time,
                @Header("Authorization") String Authorization
        );
}
