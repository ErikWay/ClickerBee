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

    Button button1auto, button5auto, button40auto, button100auto, button400auto;
    TextView myTextView1, myTextView5, myTextView40, myTextView100, myTextView400;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация переменных
        myTextView1 = view.findViewById(R.id.textView16);
        myTextView5 = view.findViewById(R.id.textView17);
        myTextView40 = view.findViewById(R.id.textView18);
        myTextView100 = view.findViewById(R.id.textView19);
        myTextView400 = view.findViewById(R.id.textView20);

        button1auto = view.findViewById(R.id.button01auto); button1auto.setOnClickListener(oMyButton1auto);
        button5auto = view.findViewById(R.id.button05auto); button5auto.setOnClickListener(oMyButton5auto);
        button40auto = view.findViewById(R.id.button4auto); button40auto.setOnClickListener(oMyButton40auto);
        button100auto = view.findViewById(R.id.button10auto); button100auto.setOnClickListener(oMyButton100auto);
        //button400auto = view.findViewById(R.id.button40auto); button40auto.setOnClickListener(oMyButton40auto);

    }
    @Override
    public void onPause() {
        super.onPause();
        // Получение объекта SharedPreferences для фрагмента
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        // Получение редактора для внесения изменений
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("Куплено1", maybutton1auto);
        editor.putBoolean("Куплено5", maybutton5auto);
        editor.putBoolean("Куплено40", maybutton40auto);
        editor.putBoolean("Куплено100", maybutton100auto);


        editor.apply();
    }    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        maybutton1auto = sharedPreferences.getBoolean("Куплено1", true);
        maybutton5auto = sharedPreferences.getBoolean("Куплено5", true);
        maybutton40auto = sharedPreferences.getBoolean("Куплено40", true);
        maybutton100auto = sharedPreferences.getBoolean("Куплено100", true);
        UpdateUI();
    }

    boolean maybutton1auto = true;
    boolean maybutton5auto = true;
    boolean maybutton40auto = true;
    boolean maybutton100auto = true;

    private void UpdateUI(){
        UpdateGENERALUI(maybutton1auto, button1auto, myTextView1);
        UpdateGENERALUI(maybutton5auto, button5auto, myTextView5);
        UpdateGENERALUI(maybutton40auto, button40auto, myTextView40);
        UpdateGENERALUI(maybutton100auto, button100auto, myTextView100);

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
    View.OnClickListener oMyButton1auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 150;
            double auto_factorX = 1;
            maybutton1auto = BuyAutoPart(price, maybutton1auto, button1auto, myTextView1, auto_factorX);
        }
    };
    View.OnClickListener oMyButton5auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 1000;
            double auto_factorX = 5;

            maybutton5auto = BuyAutoPart(price, maybutton5auto, button5auto, myTextView5, auto_factorX);
        }
    };
    View.OnClickListener oMyButton40auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 5000;
            double auto_factorX = 40;
            maybutton40auto = BuyAutoPart(price, maybutton40auto, button40auto, myTextView40, auto_factorX);
        }
    };
    View.OnClickListener oMyButton100auto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int price = 30000;
            double auto_factorX = 100;
            maybutton100auto = BuyAutoPart(price, maybutton100auto, button100auto, myTextView100, auto_factorX);
        }
    };

    public void CLEARDATAAUTOACTIVITY(){
        maybutton1auto = true;
        maybutton5auto = true;
        maybutton40auto = true;
        maybutton100auto = true;
        UpdateUI();
    }

}