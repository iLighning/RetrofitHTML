package ilightning.retrofithtml.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ilightning.retrofithtml.R;
import ilightning.retrofithtml.api.api;
import ilightning.retrofithtml.app.MyApplication;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

///**
// * Created by Hoàng on 6/20/2016.
// */

public class FollowService extends AsyncTask<Void, Void, Void> {
    private Activity mContext;
    private ProgressDialog progress;
    private WebView webView;
    private StringBuffer result = null;

    public FollowService(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        webView = (WebView) mContext.findViewById(R.id.webLayoutFollow);
        progress = new ProgressDialog(mContext);
        progress.setMessage("Loading...");
        progress.setIndeterminate(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        postData("abcd1234");
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progress.dismiss();
    }


    @SuppressLint("SetJavaScriptEnabled")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void postData(String query) {

        webView.getSettings().setAppCachePath(mContext.getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollbarFadingEnabled(true);
        webView.getSettings().setBlockNetworkLoads(false);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        Retrofit retrofit = MyApplication.getRetrofit();
        // Service setup
        api service = retrofit.create(api.class);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType, "search_key="+query);
        Call<ResponseBody> requestBodyCall = service.getFollow(requestBody);
        requestBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("HoangTV Follow 1", response.body().toString());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    try {
                        result = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("Result", result.toString());
                    String temp = result.toString();
                    webView.loadData(temp, "text/html", "UTF-8");
                } else {
                    int sc = response.code();
                    switch (sc) {
                        case 400:
                            Log.d("HoangTV Follow", "Error 400 - Bab Request");
                            progress.dismiss();
                            break;
                        case 404:
                            Log.d("HoangTV Follow", "Not Found");
                            progress.dismiss();
                            break;
                        default:
                            Log.d("HoangTV Follow", "Generic Error");
                            progress.dismiss();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
