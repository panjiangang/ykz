package xin.com.funtrek.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import xin.com.funtrek.R;

public class MovieActivity extends AppCompatActivity {

    private ImageButton movie_mreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movie_mreturn = findViewById(R.id.movie_mreturn);
        movie_mreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
