package trial.numads2020aishwaryamurali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button,button8,myLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button8 = findViewById(R.id.button8);
        myLocation = findViewById(R.id.myLocation);


        button.setOnClickListener(this);
        button8.setOnClickListener(this);
        myLocation.setOnClickListener(this);


    }

    public void openActivity2(){
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }


    public void openActivity5(){
        Intent intent = new Intent(this,Activity5.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button : openActivity2();
            break;
            case R.id.button8 : openActivity3();
            break;
            case R.id.myLocation : openActivity5();
            break;
        }
    }
}



