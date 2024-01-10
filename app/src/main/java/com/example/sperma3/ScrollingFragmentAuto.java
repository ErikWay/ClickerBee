package com.example.sperma3;

import static com.example.sperma3.MainActivity.auto_factor;
import static com.example.sperma3.MainActivity.counter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ScrollingFragmentAuto extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling_auto, container, false);
    }

    Button button01auto, button05auto, button4auto, button10auto, button40auto;
    TextView myTextView01, myTextView05, myTextView4, myTextView10, myTextView40;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация переменных
        myTextView01 = view.findViewById(R.id.textView16);
        myTextView05 = view.findViewById(R.id.textView17);
        myTextView4 = view.findViewById(R.id.textView18);
        myTextView10 = view.findViewById(R.id.textView19);
        myTextView40 = view.findViewById(R.id.textView20);

        button01auto = view.findViewById(R.id.button01auto); button01auto.setOnClickListener(oMyButton01auto);
        button05auto = view.findViewById(R.id.button05auto); button05auto.setOnClickListener(oMyButton05auto);
        button4auto = view.findViewById(R.id.button4auto); button4auto.setOnClickListener(oMyButton4auto);
        button10auto = view.findViewById(R.id.button10auto); button10auto.setOnClickListener(oMyButton10auto);
        //button40auto = view.findViewById(R.id.button40auto); button40auto.setOnClickListener(oMyButton40auto);

    }
    @Override
    public void onPause() {
        super.onPause();
        // Получение объекта SharedPreferences для фрагмента
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        // Получение редактора для внесения изменений
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("Куплено01", maybutton01auto);
        editor.putBoolean("Куплено05", maybutton05auto);
        editor.putBoolean("Куплено4", maybutton4auto);
        editor.putBoolean("Куплено10", maybutton10auto);


        editor.apply();
    }    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        maybutton01auto = sharedPreferences.getBoolean("Куплено01", true);
        maybutton05auto = sharedPreferences.getBoolean("Куплено05", true);
        maybutton4auto = sharedPreferences.getBoolean("Куплено4", true);
        maybutton10auto = sharedPreferences.getBoolean("Куплено10", true);
        UpdateUI();
    }

    boolean maybutton01auto = true;
    boolean maybutton05auto = true;
    boolean maybutton4auto = true;
    boolean maybutton10auto = true;

    private void UpdateUI(){
        UpdateGENERALUI(maybutton01auto, button01auto, myTextView01);
        UpdateGENERALUI(maybutton05auto, button05auto, myTextView05);
        UpdateGENERALUI(maybutton4auto, button4auto, myTextView4);
        UpdateGENERALUI(maybutton10auto, button10auto, myTextView10);

    }
    private void UpdateGENERALUI(boolean maybuttonGENERALauto, Button buttonGENERALauto, TextView myTextViewGENERAL){
       if (!maybuttonGENERALauto){
           buttonGENERALauto.setClickable(false);
           buttonGENERALauto.setEnabled(false);
           myTextViewGENERAL.setText("Куплено!");
       }
    }

    private boolean BuyAutoPart(int price, boolean maybuttonGENERALauto, Button buttonGENERALauto, TextView myTextViewGENERAL, double auto_factorX){
        if (counter >= price && maybuttonGENERALauto){
            auto_factor = auto_factor + auto_factorX;
            counter = counter - price;
            buttonGENERALauto.setClickable(false);
            buttonGENERALauto.setEnabled(false);
            maybuttonGENERALauto = false;
            myTextViewGENERAL.setText("Куплено!");
        }
        return maybuttonGENERALauto;
    }
    View.OnClickListener oMyButton01auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 150;
            double auto_factorX = 0.1;
            maybutton01auto = BuyAutoPart(price, maybutton01auto, button01auto, myTextView01, auto_factorX);
        }
    };
    View.OnClickListener oMyButton05auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 1000;
            double auto_factorX = 0.5;

            maybutton05auto = BuyAutoPart(price, maybutton05auto, button05auto, myTextView05, auto_factorX);
        }
    };
    View.OnClickListener oMyButton4auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 5000;
            double auto_factorX = 4;
            maybutton4auto = BuyAutoPart(price, maybutton4auto, button4auto, myTextView4, auto_factorX);
        }
    };
    View.OnClickListener oMyButton10auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 30000;
            double auto_factorX = 10;
            maybutton10auto = BuyAutoPart(price, maybutton10auto, button10auto, myTextView10, auto_factorX);
        }
    };



}