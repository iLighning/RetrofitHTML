package ilightning.retrofithtml.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ilightning.retrofithtml.R;
import ilightning.retrofithtml.service.PostFeedbackService;

public class PostFeedback extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.edt_feedback)
    EditText edtFeedback;
    @Bind(R.id.btn_send)
    Button btnSent;
    @Bind(R.id.btn_cancel_feedback)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feedback_sevices);
        ButterKnife.bind(this);
        btnSent.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_send:

                if (!isValidEmail(edtEmail.getText().toString())) {
                    Toast.makeText(this, "Please enter proper email address.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtFeedback.getText().toString().trim().length() == 0) {
                    Toast.makeText(this, "Please enter some feedback.", Toast.LENGTH_SHORT).show();
                    return;
                }

                PostFeedbackService postFeedback = new PostFeedbackService(PostFeedback.this);
                postFeedback.execute();

                break;

            case R.id.btn_cancel_feedback:
                break;

        }

    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
