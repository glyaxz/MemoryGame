package com.example.memorygame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton[] buttons = {
                findViewById(R.id.imgbtn1),
                findViewById(R.id.imgbtn2),
                findViewById(R.id.imgbtn3),
                findViewById(R.id.imgbtn4),
                findViewById(R.id.imgbtn5),
                findViewById(R.id.imgbtn6),
                findViewById(R.id.imgbtn7),
                findViewById(R.id.imgbtn8),
                findViewById(R.id.imgbtn9),
                findViewById(R.id.imgbtn10),
                findViewById(R.id.imgbtn11),
                findViewById(R.id.imgbtn12)
        };


        //On Click functions
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        }));


    }

    //SetImages functions
    public void imagesSetter(ImageButton[] buttons){

    }
}