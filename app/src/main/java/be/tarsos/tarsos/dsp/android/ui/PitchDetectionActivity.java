package be.tarsos.tarsos.dsp.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Umatharsini on 24/09/2017.
 */

public class PitchDetectionActivity extends ActionBarActivity {
    public Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitch_detection);

        b1 = (Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent =new Intent(getBaseContext(), TarsosDSPActivity.class);
                startActivity(intent);
            }
        });
    }
}
