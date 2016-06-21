package ilightning.retrofithtml.app;

import android.app.Application;
import android.util.Log;

import ilightning.retrofithtml.utils.Cons;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hoàng on 6/17/2016.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    private static Retrofit retrofit;

    public static MyApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        retrofit = new Retrofit.Builder()
                .baseUrl(Cons.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Log.d("HoangTV", "create Retrofit");
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
