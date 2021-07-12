package sg.edu.rp.c346.id19029863.demodatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    int counter = 0 ;
    Button btnInsert, btnGetTasks;
    TextView tvResults;
    EditText name, date;
    ListView LV;
    ArrayList<Task> tasklist;
    ArrayAdapter<Task> taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        name = findViewById(R.id.task);
        date = findViewById(R.id.date);
        LV = findViewById(R.id.lv);

        tasklist = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tasklist);

        LV.setAdapter(taskAdapter);
        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(name.getText().toString(), date.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                tasklist.clear();
                tasklist.addAll(db.getTasks(counter % 2));
                counter ++;
                taskAdapter.notifyDataSetChanged();

            }
        });
        }


    }