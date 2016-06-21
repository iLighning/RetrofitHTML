package ilightning.retrofithtml.service;

import ilightning.retrofithtml.utils.Cons;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hoàng on 6/17/2016.
 */
public class RetrofitSingleton {

    private static RetrofitSingleton sInstance = null;
    private static Retrofit retrofit;

    public RetrofitSingleton() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Cons.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitSingleton getsInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitSingleton();
        }
        return sInstance;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
