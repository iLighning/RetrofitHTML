package ilightning.retrofithtml.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ilightning.retrofithtml.R;
import ilightning.retrofithtml.service.LoadWeb;

public class LoadContentHTML extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_content_html);
        LoadWeb loadWeb = new LoadWeb(this);
        loadWeb.execute();
    }
}
