package ilightning.retrofithtml.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import ilightning.retrofithtml.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btnGetHtml)
    Button btnGetHtml;
    @Bind(R.id.btnPostFeedback)
    Button btnPostFeedback;
    @Bind(R.id.btnFollow)
    Button btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnGetHtml.setOnClickListener(this);
        btnPostFeedback.setOnClickListener(this);
        btnFollow.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetHtml:
                Intent intent = new Intent(this, LoadContentHTML.class);
                startActivity(intent);
                break;

            case R.id.btnPostFeedback:
                Intent intent1 = new Intent(this, PostFeedback.class);
                startActivity(intent1);
                break;
            case R.id.btnFollow:
                Intent intent2 = new Intent(this, Follow.class);
                startActivity(intent2);
                break;
        }

    }
}
