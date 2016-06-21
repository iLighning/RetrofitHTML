package ilightning.retrofithtml.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import ilightning.retrofithtml.R;
import ilightning.retrofithtml.api.api;
import ilightning.retrofithtml.app.MyApplication;
import ilightning.retrofithtml.model.StatusPost;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

///**
// * Created by Hoàng on 6/17/2016.
// */

public class PostFeedbackService extends AsyncTask<Void, Void, Void> {

    private Activity context;
    private ProgressDialog progress;
    private EditText edtEmail, edtFeedback;

    public PostFeedbackService(Activity activity) {
        this.context = activity;
        edtEmail = (EditText) context.findViewById(R.id.edt_email);
        edtFeedback = (EditText) context.findViewById(R.id.edt_feedback);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(context, "", "Loading...", true);
    }

    @SuppressWarnings("WrongThread")
    @Override
    protected Void doInBackground(Void... params) {

        Retrofit retrofit = MyApplication.getRetrofit();
        // Service setup
        api service = retrofit.create(api.class);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "email_id=" + edtEmail.getText().toString() + "&feedback=" + edtFeedback.getText().toString());
        Call<StatusPost> postCall = service.getStatusFeedback(body);
        postCall.enqueue(new Callback<StatusPost>() {
                             @Override
                             public void onResponse(Call<StatusPost> call, Response<StatusPost> response) {
                                 if (response.isSuccessful()) {
                                     Log.d("HoangTV", "response.isSuccessful()");
                                     StatusPost statusPost = response.body();
                                     Log.d("HoangTV", statusPost.getStatus());

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
                             public void onFailure(Call<StatusPost> call, Throwable t) {

                             }
                         }

        );
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progress.dismiss();
        super.onPostExecute(aVoid);
    }
}
