package com.example.videostreamapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.File;

public class MainMenuActivity extends AppCompatActivity {
    public NumberPicker picker;
    public String[] pickerVals = new String[] {"50", "20", "100", "0", "100","20","50"};
    public Button bttstart;
    public ListView videolist;
    public ArrayAdapter<String> adapter;
    String link,iotd;
    String[] listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        bttstart = findViewById(R.id.bt_Startview);
        picker = findViewById(R.id.iotd_picker);
        videolist = findViewById(R.id.video_list);
        picker.setMaxValue(6);
        picker.setMinValue(0);
        picker.setDisplayedValues(pickerVals);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files.length !=0)
        {
            listItem = new String[files.length];

            for (int i = 0; i < files.length; i++)
                {
                    Log.d("Files", "FileName:" + files[i].getName());
                    listItem[i] = files[i].getName();
                }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listItem);

        }else Log.e("Files", "Folder not found:");

        videolist.setAdapter(adapter);
        videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                link = (String) parent.getItemAtPosition(position);
                iotd = pickerVals[picker.getValue()];
                Log.i("Select:",link);
            }
        });

        bttstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuActivity.this, MainActivity.class);
                //Declaring a key which must be unique
                final String EXTRA_NAME = "com.coderefer.switchactivityandroid.name";
                //storing a name
                Log.i("Select:",iotd  + "  "+link);
                i.putExtra("link", link);
                i.putExtra("iotd", iotd);
                startActivity(i);

            }
        });
    };
}
