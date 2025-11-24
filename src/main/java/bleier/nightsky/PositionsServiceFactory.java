package bleier.nightsky;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PositionsServiceFactory {

    public PositionsService getPositionsService() {
        Retrofit retrofit = new Retrofit.Builder()
                //method calls
                //server that we can connect to
                .baseUrl("https://api.astronomyapi.com")
                // Configure Retrofit to use Gson to turn the Json into Objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(PositionsService.class);
    }
}
