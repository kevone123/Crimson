package c.example.owner.crimson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button mRegBtn;
    private Button mLegBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mRegBtn = (Button) findViewById(R.id.start_reg);
        //once clicekd send to login
        mLegBtn=(Button)findViewById(R.id.already_reg);
        mLegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leg_intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(leg_intent);
            }
        });
        //once user clicks on button you want to send to register activity
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg_intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(reg_intent);
            }
        });
    }
}
