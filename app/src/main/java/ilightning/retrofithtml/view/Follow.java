package ilightning.retrofithtml.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ilightning.retrofithtml.R;
import ilightning.retrofithtml.service.FollowService;

public class Follow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        FollowService service = new FollowService(this);
        service.execute();
    }
}
