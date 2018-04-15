package com.example.andresvelasquez.seccion_19_notificaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_message)
    TextView tv_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        setIntentValues();
    }

    private void setIntentValues(){
        if(getIntent() != null){
            tv_message.setText(getIntent().getStringExtra("message"));
            tv_title.setText(getIntent().getStringExtra("title"));
        }
    }
}
