package trial.numads2020aishwaryamurali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private String url = "https://www.google.com/";
    ListView listView;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        final ArrayList<String> list = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listview);
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position)));
                startActivity(intent);
            }
        });


        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Activity3.this);
                final EditText edittext = new EditText(Activity3.this);
                //alert.setMessage("Enter a link");
                alert.setTitle("Enter a link");

                alert.setView(edittext);

                alert.setPositiveButton("Add Link", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String myUrl = edittext.getText().toString();
                        if(myUrl.startsWith("https://www."))
                        {
                            list.add(myUrl);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            Snackbar.make(v, "New link successfully added", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else if(myUrl.startsWith("www."))
                        {
                            String s = "https://"+myUrl;
                            list.add(s);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            Snackbar.make(v, "New link successfully added", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                       else
                        {
                            Toast toast = Toast.makeText(Activity3.this,"Invalid URL. Please add a valid url",Toast.LENGTH_LONG);
                            toast.show();
                        }
                        //adapter.clear();
                        //adapter.addAll(list);


                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();
            }
        });

    }
}
