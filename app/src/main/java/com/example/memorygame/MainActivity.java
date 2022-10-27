package com.example.memorygame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] imagesRes = {
            R.drawable.cara1,
            R.drawable.cara2,
            R.drawable.cara3,
            R.drawable.cara4,
            R.drawable.cara5,
            R.drawable.cara6
    };
    ImageButton[] imgButtons;

    int vidas = 5;
    int puntuaje = 0;
    boolean needCheck;
    int checkedButton = -1;
    ImageButton[] checkedButton2 =  new ImageButton[1];

    int[] randomImgButtons = new int[12];

    int defaultImage = R.drawable.dice;

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

        imgButtons = buttons;

        //On Click functions
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                int whichClicked = -1;
                ImageButton[] var = new ImageButton[1];

                for(int i = 0; i < randomImgButtons.length; i++){
                    if(imgButtons[i].getId() == view.getId()){
                        buttons[i].setImageResource(imagesRes[randomImgButtons[i]]);
                        whichClicked = i;
                        var[0] = buttons[i];
                    }
                }

                if(checkedButton == -1){
                    checkedButton = whichClicked;
                    checkedButton2 = var;
                }else{
                    if(randomImgButtons[whichClicked] == randomImgButtons[checkedButton]){
                        Toast.makeText(MainActivity.this, "worked", Toast.LENGTH_SHORT).show();
                        puntuaje += 100;
                        checkedButton = -1;
                        checkedButton2[0] = null;
                    }else {
                        Handler h = new Handler();
                        h.postDelayed( () -> {
                            vidas--;
                            checkedButton2[0].setImageResource(defaultImage);
                            var[0].setImageResource(defaultImage);
                            checkedButton = -1;
                            checkedButton2[0] = null;
                        } ,500);
                    }

                }

                if(puntuaje == 500) {
                    Toast.makeText(MainActivity.this, "Has ganado!", Toast.LENGTH_SHORT).show();
                    restoreGame();
                }
                if(vidas == 0){
                    Toast.makeText(MainActivity.this, "Perdiste, has conseguido " + puntuaje + " puntos, empieza desde cero", Toast.LENGTH_SHORT).show();
                    restoreGame();
                }
            }
        }));

        //Default image
        Arrays.stream(buttons).sequential().forEach(btn -> btn.setImageResource(defaultImage));
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setAdjustViewBounds(true));
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setMaxWidth(300));
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setMaxHeight(300));
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setPadding(5,5,5,5));
        Arrays.stream(buttons).parallel().forEach(btn -> btn.setBackgroundColor(Color.TRANSPARENT));

        imagesSetter(buttons);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void restoreGame(){
        Arrays.stream(imgButtons).sequential().forEach(btn -> btn.setImageResource(defaultImage));
        Arrays.stream(imgButtons).parallel().forEach(btn -> btn.setAdjustViewBounds(true));
        Arrays.stream(imgButtons).parallel().forEach(btn -> btn.setMaxWidth(300));
        Arrays.stream(imgButtons).parallel().forEach(btn -> btn.setMaxHeight(300));
        Arrays.stream(imgButtons).parallel().forEach(btn -> btn.setPadding(5,5,5,5));
        Arrays.stream(imgButtons).parallel().forEach(btn -> btn.setBackgroundColor(Color.TRANSPARENT));

        imagesSetter(imgButtons);
    }



    //SetImages functions
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void imagesSetter(ImageButton[] buttons){
        int[] valueBtns = new int[buttons.length];

        for(int i = 1; i <= buttons.length; i++){
            int r = new Random().nextInt((6 - 1) + 1);
            try{
                int count = 0;

                for (int j = 0; j < valueBtns.length - 1; j++){
                    if(valueBtns[j] == r){
                        count++;
                    }
                }
                if(count >= 2){
                    i--;
                }else{

                    valueBtns[i] = r;
                    count = 0;
                }
            }catch(Exception e){
                //
            }

        }
        Arrays.stream(valueBtns).sequential().forEach(System.out::println);

        randomImgButtons = valueBtns;
        /*
        for(int i = 0; i <= buttons.length - 1; i++ ){
            buttons[i].setImageResource(imagesRes[valueBtns[i]]);
        }
         */
    }
}