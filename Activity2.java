package trial.numads2020aishwaryamurali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    private Button button2,button3,button4,button5,button6,button7,button9;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button9 = findViewById(R.id.button9);
        tv = findViewById(R.id.textView2);

        /*button = findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });*/

        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button9.setOnClickListener(this);

    }


    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button2:
                tv.setText("Pressed : A");
                break;
            case R.id.button3:
                tv.setText("Pressed : B");
                break;
            case R.id.button4:
                tv.setText("Pressed : C");
                break;
            case R.id.button5:
                tv.setText("Pressed : D");
                break;
            case R.id.button6:
                tv.setText("Pressed : E");
                break;
            case R.id.button7:
                tv.setText("Pressed : F");
                break;
            case R.id.button9:
                openMainActivity();
                break;
        }
    }
}
