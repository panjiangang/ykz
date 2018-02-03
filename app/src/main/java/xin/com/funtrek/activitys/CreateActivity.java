package xin.com.funtrek.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;

public class CreateActivity extends AppCompatActivity {

    @BindView(R.id.create_cancel)
    TextView createCancel;
    @BindView(R.id.create_movie)
    LinearLayout createMovie;
    @BindView(R.id.create_satin)
    LinearLayout createSatin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcreate);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.create_cancel)
    public void onCreateCancelClicked() {
        finish();
    }

    @OnClick(R.id.create_movie)
    public void onCreateMovieClicked() {
        startActivity(new Intent(CreateActivity.this, MovieActivity.class));
    }

    @OnClick(R.id.create_satin)
    public void onCreateSatinClicked() {
        startActivity(new Intent(CreateActivity.this, SatinActivity.class));
        finish();
    }
}
