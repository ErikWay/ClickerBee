package com.example.sperma3;

import static com.example.sperma3.MainActivity.BalanceTextView;
import static com.example.sperma3.MainActivity.FactorTextView;
import static com.example.sperma3.MainActivity.counter;
import static com.example.sperma3.MainActivity.factor;

import android.annotation.SuppressLint;
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

import org.w3c.dom.Text;

public class ScrollingFragmentUpgrade extends Fragment {

    TextView myTextView1, myTextView5, myTextView40, myTextView100;
    TextView levelTextView;
    TextView myTextViewUpgrade1, myTextViewUpgrade5, myTextViewUpgrade40, myTextViewUpgrade100;
    Button myButton, myButton1, myButton5, myButton40, myButton100,myButtontest;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling_upgrade, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация переменных
        myTextView1 = view.findViewById(R.id.textView5);
        myTextView5 = view.findViewById(R.id.textView6);
        myTextView40 = view.findViewById(R.id.textView7);
        myTextView100 = view.findViewById(R.id.textViewX100);

        myTextViewUpgrade1 = view.findViewById(R.id.textViewX1_30);
        myTextViewUpgrade5 = view.findViewById(R.id.textViewX5_30);
        myTextViewUpgrade40 = view.findViewById(R.id.textViewX40_30);
        myTextViewUpgrade100 = view.findViewById(R.id.textViewX100_30);

        myButton1 = view.findViewById(R.id.button2); myButton1.setOnClickListener(oMyButton1);
        myButton5 = view.findViewById(R.id.button3); myButton5.setOnClickListener(oMyButton5);
        myButton40 = view.findViewById(R.id.button4); myButton40.setOnClickListener(oMyButton40);
        myButton100 = view.findViewById(R.id.buttonX100); myButton100.setOnClickListener(oMyButton100);
        myButtontest = view.findViewById(R.id.button6); myButtontest.setOnClickListener(oMyButtonTest);

    }

    @Override
    public void onPause() {
        super.onPause();
        // Получение объекта SharedPreferences для фрагмента
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        // Получение редактора для внесения изменений
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Уровень 01", lvl_upgrade01); editor.putFloat("Улучшение 01", (float) price01);
        editor.putInt("Уровень 05", lvl_upgrade05); editor.putFloat("Улучшение 05", (float) price05);
        editor.putInt("Уровень 4", lvl_upgrade4); editor.putFloat("Улучшение 4", (float) price4);
        editor.putInt("Уровень 10", lvl_upgrade10); editor.putFloat("Улучшение 10", (float) price10);

        editor.apply();
    }

    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        lvl_upgrade01 = sharedPreferences.getInt("Уровень 01",0); price01 = sharedPreferences.getFloat("Улучшение 01",(float)initial_price01);
        lvl_upgrade05 = sharedPreferences.getInt("Уровень 05", 0); price05 = sharedPreferences.getFloat("Улучшение 05",(float)initial_price05);
        lvl_upgrade4 = sharedPreferences.getInt("Уровень 4", 0); price4 = sharedPreferences.getFloat("Улучшение 4",(float)initial_price4);
        lvl_upgrade10 = sharedPreferences.getInt("Уровень 10", 0); price10 = sharedPreferences.getFloat("Улучшение 10",(float)initial_price10);
        UpdateUI();
    }

    private void UpdateUI(){
        UpdatePartUI(price01, lvl_upgrade01, myTextView1, myTextViewUpgrade1, myButton1);
        UpdatePartUI(price05, lvl_upgrade05, myTextView5, myTextViewUpgrade5, myButton5);
        UpdatePartUI(price4, lvl_upgrade4, myTextView40, myTextViewUpgrade40, myButton40);
        UpdatePartUI(price10, lvl_upgrade10, myTextView100, myTextViewUpgrade100, myButton100);
    }
    private int LvlUp(double _price, int _lvl_upgrade, TextView myTextViewUpgrade){
        if (counter >= _price && _lvl_upgrade < 30){
            _lvl_upgrade++;
            myTextViewUpgrade.setText(String.format("%d / 30", _lvl_upgrade));

        }
        return _lvl_upgrade;
    }
    private String FormatDecimal1(double number) {
        String new_number = String.format("%.1f", number);
        return new_number;
    }
    double performUpgrade(double price, double xFactor, int lvl_upgrade, TextView myTextView) {

        double count = 1;
        if (counter >= price && lvl_upgrade < 30){
            counter = counter - price;
            factor = factor + xFactor;
            BalanceTextView.setText(FormatDecimal1(counter));
            FactorTextView.setText(FormatDecimal1(factor));
            ++count;
            price = price * Math.pow(1.05, count);
            myTextView.setText(FormatDecimal1(price));
        }
        return price;
    }

    private void checkFullUp(int lvl_upgrade, Button buttonGENERAL, TextView myTextViewGENERAL, TextView myTextViewUpgradeGENERAL){
        if (lvl_upgrade == 30){
            buttonGENERAL.setClickable(false);
            buttonGENERAL.setEnabled(false);
            myTextViewGENERAL.setText("Куплено!");
            myTextViewUpgradeGENERAL.setText("");
        }
    }

    private void UpdatePartUI(double price, int lvl_upgrade, TextView myTextView, TextView myTextViewUpgrade, Button buttonGENERAL){
        myTextView.setText(FormatDecimal1(price));
        myTextViewUpgrade.setText(String.format("%d / 30", lvl_upgrade));
        checkFullUp(lvl_upgrade, buttonGENERAL,myTextView, myTextViewUpgrade);
    }
    final double initial_price01 = 15;
    double price01 = initial_price01;
    public int lvl_upgrade01 = 0;
    View.OnClickListener oMyButton1 = new View.OnClickListener() {
        final double xFactor = 0.1;
        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            lvl_upgrade01 = LvlUp(price01, lvl_upgrade01, myTextViewUpgrade1);
            price01 = performUpgrade(price01, xFactor, lvl_upgrade01, myTextView1);
            checkFullUp(lvl_upgrade01, myButton1, myTextView1, myTextViewUpgrade1);
        }
    };

    final double initial_price05 = 100;
    double price05 = initial_price05;
    public int lvl_upgrade05 = 0;
    View.OnClickListener oMyButton5 = new View.OnClickListener() {
        final double xFactor = 0.5;
        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            lvl_upgrade05 = LvlUp(price05, lvl_upgrade05, myTextViewUpgrade5);
            price05 = performUpgrade(price05, xFactor, lvl_upgrade05, myTextView5);
            checkFullUp(lvl_upgrade05, myButton5, myTextView5, myTextViewUpgrade5);


        }
    };
    final double initial_price4 = 500;
    double price4 = initial_price4;
    public int lvl_upgrade4 = 0;
    View.OnClickListener oMyButton40 = new View.OnClickListener() {
        final double xFactor = 4;
        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            lvl_upgrade4 = LvlUp(price4, lvl_upgrade4, myTextViewUpgrade40);
            price4 = performUpgrade(price4, xFactor, lvl_upgrade4, myTextView40);
            checkFullUp(lvl_upgrade4, myButton40, myTextView40, myTextViewUpgrade40);

        }
    };
    final double initial_price10 = 3000;
    double price10 = initial_price10;
    public int lvl_upgrade10 = 0;
    View.OnClickListener oMyButton100 = new View.OnClickListener() {

        final double xFactor = 10;

        @SuppressLint("DefaultLocale")
        @Override
        public void onClick(View view) {
            lvl_upgrade10 = LvlUp(price10, lvl_upgrade10, myTextViewUpgrade100);
            price10 = performUpgrade(price10, xFactor, lvl_upgrade10, myTextView100);
            checkFullUp(lvl_upgrade10, myButton100, myTextView100, myTextViewUpgrade100);

        }
    };
    View.OnClickListener oMyButtonTest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            myButtontest.setClickable(false);
            myButtontest.setEnabled(false);
        }
    };

    public void CLEARDATAUPACTIVITY(){
        lvl_upgrade01 = 0;
        price01 = initial_price01;
        lvl_upgrade05 = 0;
        price05 = initial_price05;
        lvl_upgrade4 = 0;
        price4 = initial_price4;
        lvl_upgrade10 = 0;
        price10 = initial_price10;
        UpdateUI();
    }


    }