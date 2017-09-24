package be.tarsos.tarsos.dsp.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Umatharsini on 21/09/2017.
 */

public class First extends ActionBarActivity {

    public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.button3);
        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent toy =new Intent(First.this, TarsosDSPActivity.class);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();
    }
}
