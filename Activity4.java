package trial.numads2020aishwaryamurali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;


public class Activity4 extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";

    private EditText inputCity;
    private TextView cityName, result;
    private Button checkWeather;
    private Handler handler = new Handler();
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        inputCity = (EditText) findViewById(R.id.inputCity);
        cityName = (TextView) findViewById(R.id.cityName);
        result = (TextView) findViewById(R.id.result);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        checkWeather = (Button) findViewById(R.id.checkWeather);
        checkWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager con=(ConnectivityManager)getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
                NetworkInfo net=con.getActiveNetworkInfo();
                if(net==null||!(net.isConnected()))
                {
                    Toast toast = Toast.makeText(Activity4.this, "No internet connection. Please connect to the Internet and try again.", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }


                String val = String.valueOf(inputCity.getText());
                Thread thread = new Thread(new MyRunnable(val));
                result.setText("City not found");
                progressBar.setVisibility(View.VISIBLE);
                thread.start();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("my_text",result.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(savedInstanceState.getString("my_text"));
    }

    public class MyRunnable implements Runnable{

        String city;

        MyRunnable(String s){
            city = s;
        }

            @Override
            public void run() {

                String domain = "https://api.openweathermap.org/data/2.5/weather?q=";
                String key = "&APPID=336507ea13dd6d6b2e39ed2db88e21d8";
                String uri = domain + city + key;

                URL url = null;
                try {

                    url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);


                    conn.connect();

                    // Read response.
                    InputStream inputStream = conn.getInputStream();
                    final String resp = readFromStream(inputStream);

                    JSONObject jObject = new JSONObject(resp);

                    final String weather;
                    JSONArray jsonArray = jObject.getJSONArray("weather");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    weather = jsonObject.getString("main");


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            result.setText(weather);
                        }
                    });

                } catch (MalformedURLException e) {
                    Log.e(TAG, "MalformedURLException");
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    Log.e(TAG, "ProtocolException");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e(TAG, "IOException");
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException");
                    e.printStackTrace();
                }
            }
        }



    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}

