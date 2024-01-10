package com.example.sperma3;

import static com.example.sperma3.MainActivity.img_value;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RolletActivity extends AppCompatActivity{
    Button setbutton;
    ImageButton standartButton, redButton, blueButton, bitButton;
    TextView TextLvlCount, TextStandart, TextRed, TextBlue, TextBit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollet);
        standartButton = findViewById(R.id.imageButton2);
        redButton = findViewById(R.id.imageButton3);
        blueButton = findViewById(R.id.imageButton4);
        bitButton = findViewById(R.id.imageButton5);
        TextLvlCount = findViewById(R.id.textView8);
        TextRed = findViewById(R.id.textView10);
        TextBlue = findViewById(R.id.textView11);
        TextBit = findViewById(R.id.textView21);
        TextStandart = findViewById(R.id.textView22);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            receiveLvl = bundle.getInt("ключ уровня");
        }
        TextLvlCount.setText("Уровень: " + receiveLvl);
        UpdateUI();
    }


    private void UpdateUI() {
        UpdatePartUI(standartButton, TextStandart, 0, 0);
        UpdatePartUI(redButton, TextRed, 1, 3);
        UpdatePartUI(blueButton, TextBlue, 2, 6);
        UpdatePartUI(bitButton, TextBit, 3, 12);
    }

    private void UpdatePartUI(ImageButton GENERALButton, TextView GENERALText, int GENERALimg_value, int GENERALLvl) {
        GENERALButton.setClickable(true);
        GENERALButton.setEnabled(true);
        GENERALButton.setImageTintList(ColorStateList.valueOf(0xA4AC6767));
        if (receiveLvl >= GENERALLvl) {
            GENERALText.setVisibility(View.GONE);
            GENERALButton.setClickable(true);
            GENERALButton.setEnabled(true);
        }
        if (receiveLvl < GENERALLvl){
            GENERALText.setVisibility(View.VISIBLE);
            GENERALButton.setClickable(false);
            GENERALButton.setEnabled(false);
        }
        if (img_value == GENERALimg_value){
            GENERALButton.setClickable(false);
            GENERALButton.setEnabled(false);
            GENERALButton.setImageTintList(null);
        }
    }

    int receiveLvl = 0;
    public void case_sota_standart (View v) {
        Toast.makeText(getApplicationContext(), "Установлены обычные соты", Toast.LENGTH_SHORT).show();
        img_value = 0;
        UpdateUI();
    }
    public void case_sota_red (View v) {
        Toast.makeText(getApplicationContext(), "Установлены красные соты", Toast.LENGTH_SHORT).show();
        img_value = 1;
        UpdateUI();
    }
    public void case_sota_blue (View v) {
       Toast.makeText(getApplicationContext(), "Установлены синие соты", Toast.LENGTH_SHORT).show();
        img_value = 2;
        UpdateUI();
    }
    public void case_sota_bitcoin (View v) {
        Toast.makeText(getApplicationContext(), "Установлен биткоин", Toast.LENGTH_SHORT).show();
        img_value = 3;
        UpdateUI();

    }

}