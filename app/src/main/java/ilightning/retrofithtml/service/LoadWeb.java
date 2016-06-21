package ilightning.retrofithtml.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ilightning.retrofithtml.R;
import ilightning.retrofithtml.api.api;
import ilightning.retrofithtml.app.MyApplication;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

///**
// * Created by Hoàng on 6/17/2016.
// */

public class LoadWeb extends AsyncTask<Void, Void, Void> {

    private StringBuffer result = null;
    private WebView webView;

    private Activity mContext;
    private ProgressDialog progress;
    private String contentHtml = null;
//    private RetrofitSingleton retrofitSingleton;

    public LoadWeb(Activity context) {
        this.mContext = context;
        webView = (WebView) mContext.findViewById(R.id.webLayout);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(mContext, "",
                "Loading...", true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        getHtmlViaRetrofit();
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

    public void getHtmlViaRetrofit() {

        // Retrofit setup
        // dung class singleton de khoi tao 1 lan duy nhat
//        Retrofit retrofit = retrofitSingleton.getsInstance().getRetrofit();
        Retrofit retrofit = MyApplication.getRetrofit();

        // Service setup
        api service = retrofit.create(api.class);

        webView = (WebView) mContext.findViewById(R.id.webLayout);
        Call<ResponseBody> call = service.getResponseBodyCall();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body().toString());
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
                    contentHtml = result.toString();
                    webView.loadData(parseHtml(contentHtml), "text/html", "UTF-8");
                } else {
                    int sc = response.code();
                    switch (sc) {
                        case 400:
                            Log.e("Error 400", "Bad Request");
                            break;
                        case 404:
                            Log.e("Error 404", "Not Found");
                            break;
                        default:
                            Log.e("Error", "Generic Error");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public String parseHtml(String html) {

        String searchStringForMainStart = "<!-- MAINCONTENTSTART -->";
        String searchStringForMainEnd = "<!-- MAINCONTENTEND -->";
        String searchStringForCSSStart = "<!-- MAINCSSSTART -->";
        String searchStringForCSSEnd = "<!-- MAINCSSEND -->";

        String cssData = html.substring(html.indexOf(searchStringForCSSStart), html.indexOf(searchStringForCSSEnd));
        String content = html.substring(html.indexOf(searchStringForMainStart), html.indexOf(searchStringForMainEnd));
        return String.format("<html><head><meta charset=\"utf-8\"> %s </head><body> %s </body></html>", cssData, content);
    }
}
