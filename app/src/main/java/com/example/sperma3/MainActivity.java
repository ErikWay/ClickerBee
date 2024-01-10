package com.example.sperma3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.animation.AnimationSet;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler autoClickHandler;
    private Runnable autoClickRunnable;
    ProgressBar progressBar;
    static TextView FactorTextView;
    static TextView BalanceTextView;
    TextView levelTextView;
    ImageButton MainButton;
    ImageView imageViewBeeToRight2, imageViewBeeToRight3, imageViewBeeToRight4, imageViewBeeToLeft5, imageViewBeeToLeft6, imageViewBeeToLeft7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BalanceTextView = findViewById(R.id.textView);
        BalanceTextView.setTextSize(50);
        FactorTextView = findViewById(R.id.textView2);
        levelTextView = findViewById(R.id.levelTextView);
        imageViewBeeToRight2 = findViewById(R.id.imageView2);
        imageViewBeeToRight3 = findViewById(R.id.imageView3);
        imageViewBeeToRight4 = findViewById(R.id.imageView4);
        imageViewBeeToLeft5 = findViewById(R.id.imageView5);
        imageViewBeeToLeft6 = findViewById(R.id.imageView6);
        imageViewBeeToLeft7 = findViewById(R.id.imageView7);

        MainButton = findViewById(R.id.imageButton); MainButton.setOnClickListener(oMainButton);

        progressBar = findViewById(R.id.progressBar);
        updateProgressBar();
        startAutoClick();



        // Получение объекта SharedPreferences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        // Загрузка данных
        counter = sharedPreferences.getFloat("счетчик", 0);
        factor = sharedPreferences.getFloat("множитель", 1);;
        clicks = sharedPreferences.getInt("клики для уровня", 0);;
        level = sharedPreferences.getInt("уровень", 1);;
        clicksNeededForNextLevel = sharedPreferences.getInt("клики для следующего уровня", 10);
        auto_factor = sharedPreferences.getFloat("множитель для автокликов", 0);
        img_value = sharedPreferences.getInt("номер картинки", 1);

        updateUI();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Получение объекта SharedPreferences
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Получение редактора для внесения изменений
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Сохранение данных
        editor.putFloat("счетчик", (float) counter);
        editor.putFloat("множитель", (float) factor);
        editor.putInt("клики для уровня", clicks);
        editor.putInt("уровень", level);
        editor.putInt("клики для следующего уровня",  clicksNeededForNextLevel);
        editor.putFloat("множитель для автокликов", (float) auto_factor);
        editor.putInt("номер картинки",  img_value);

        // Применение изменений
        editor.apply();
        autoClickHandler.removeCallbacks(autoClickRunnable);

    }
    @Override
    protected void onResume() {
        super.onResume();
        updateUI();

    }

    private void updateUI() {
        // Обновление интерфейса
        BalanceTextView.setText(FormatDecimal1(counter));
        FactorTextView.setText(FormatDecimal1(factor));
        levelTextView.setText("Уровень: " + level);
        int progress = (clicks * 100) / clicksNeededForNextLevel;
        progressBar.setProgress(progress);
        switch (img_value) {
            case 0:
                MainButton.setImageResource(R.drawable.png_img_sota);
                break;
            case 1:
                MainButton.setImageResource(R.drawable.png_img_sota_red);
                break;
            case 2:
                MainButton.setImageResource(R.drawable.png_img_sota_blue);
                break;
            case 3:
                MainButton.setImageResource(R.drawable.zolotaja_moneta_bitcoin);
                break;
        }
    }

    static public double counter = 1;
    static public double factor = 1;
    static public double auto_factor = 0;
    int clicks = 0;
    int level = 1;
    private int clicksNeededForNextLevel = 10;
    static public int img_value = 1;

    View.OnClickListener oMainButton = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            counter = counter + factor;
            BalanceTextView.setText(FormatDecimal1(counter));
            clicks++;
            if (clicks >= clicksNeededForNextLevel) {
                levelUp();
            }
            final Animation animationMainButton  = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.button_click);
            MainButton.startAnimation(animationMainButton);
            updateProgressBar();
            choiceBee ();
            Toast.makeText(getApplicationContext(), String.valueOf(img_value), Toast.LENGTH_SHORT).show();

        }
    };
    private long lastButtonClickTime2 = 0; private long lastButtonClickTime3 = 0; private long lastButtonClickTime4 = 0;
    private long lastButtonClickTime5 = 0; private long lastButtonClickTime6 = 0; private long lastButtonClickTime7 = 0;
    private void choiceBee (){
        Random random = new Random();
        int randomBee = random.nextInt(6) ; // Измените диапазон по вашему усмотрению
        switch (randomBee){
            case(0):
                lastButtonClickTime2 = appearBee(lastButtonClickTime2, imageViewBeeToRight2, 0.0f, 1.4f);
                break;
            case(1):
                lastButtonClickTime3 = appearBee(lastButtonClickTime3, imageViewBeeToRight3, 0.0f, 1.4f);
                break;
            case(2):
                lastButtonClickTime4 = appearBee(lastButtonClickTime4, imageViewBeeToRight4, 0.0f, 1.4f);
                break;
            case(3):
                lastButtonClickTime5 = appearBee(lastButtonClickTime5, imageViewBeeToLeft5, 0.0f, -1.4f);
                break;
            case(4):
                lastButtonClickTime6 = appearBee(lastButtonClickTime6, imageViewBeeToLeft6, 0.0f, -1.4f);
                break;
            case(5):
                lastButtonClickTime7 = appearBee(lastButtonClickTime7, imageViewBeeToLeft7, 0.0f, -1.4f);
                break;
        }
    }

    private static final long MIN_CLICK_INTERVAL =3000;
    private long appearBee(long lastButtonClickTime, ImageView imageViewBee, float valueFrom, float valueTo){
        long currentTime = SystemClock.elapsedRealtime();
        // Проверка, прошло ли достаточно времени с момента последнего нажатия
        if (currentTime - lastButtonClickTime > MIN_CLICK_INTERVAL) {
            Animation animation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, valueFrom,
                    Animation.RELATIVE_TO_PARENT, valueTo,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);

            // Установка параметров анимации
            animation.setDuration(3000); // Длительность анимации в миллисекундах (3 секунд)
            // Оставить картинку на месте после анимации

            AnimationSet animationSet = new AnimationSet(true);
            animationSet.addAnimation(animation);

            // Запуск анимации
            imageViewBee.startAnimation(animationSet);
            randomMarginTop(imageViewBee);
            lastButtonClickTime = currentTime;

        }
        return lastButtonClickTime;
    }
    private void randomMarginTop(ImageView imageViewBee){
        LayoutParams layoutParams = (LayoutParams) imageViewBee.getLayoutParams();

        // Генерация случайного значения для topMargin
        Random random = new Random();
        int randomMargin = random.nextInt(1500) +300; // Измените диапазон по вашему усмотрению

        // Обновление параметра topMargin
        layoutParams.topMargin = randomMargin;

        // Установка обновленных параметров макета для картинки
        imageViewBee.setLayoutParams(layoutParams);

    }
    private void levelUp() {
        level++;
        clicksNeededForNextLevel *= 2;
        clicks = 0;
        updateUIlvl();
    }
    private void updateUIlvl() {
        // Обновление интерфейса
        TextView levelTextView = findViewById(R.id.levelTextView);
        levelTextView.setText("Уровень: " + level);

    }
    private void updateProgressBar() {
        // Обновление прогресс-бара
        int progress = (clicks * 100) / clicksNeededForNextLevel;
        progressBar.setProgress(progress);
    }
    public void toRolletActivity(View view){

        Intent intent = new Intent(this, RolletActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ключ уровня", level);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void toUpgrateFragment(View view){
        openFrame();
        ScrollingFragmentUpgrade scrollingFragmentUpgrade = new ScrollingFragmentUpgrade();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, scrollingFragmentUpgrade);
        ft.commit();
    }
    public void toAutoFragment(View view){
        openFrame();
        ScrollingFragmentAuto scrollingFragmentAuto = new ScrollingFragmentAuto();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, scrollingFragmentAuto);
        ft.commit();
    }
    private void openFrame(){
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.setVisibility(View.VISIBLE);
        setParamsImageButton(800,800);
    }
    public void closeFrame(View view){
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        frameLayout.setVisibility(View.INVISIBLE);
        setParamsImageButton(1000,1000);
    }
    private void setParamsImageButton(int width, int height){
        ViewGroup.LayoutParams params = MainButton.getLayoutParams();
        params.width = width;
        params.height = height;
        MainButton.setLayoutParams(params);
    }
    private void startAutoClick() {
        autoClickHandler = new Handler();
        autoClickRunnable = new Runnable() {
            @Override
            public void run() {
                // Действие, выполняемое при автоматическом клике
                counter = counter + auto_factor;
                BalanceTextView.setText(FormatDecimal1(counter));
                autoClickHandler.postDelayed(this, 1000); // Интервал в миллисекундах (здесь 1000 миллисекунд = 1 секунда)
            }
        };

        // Запустить автоматические клики сразу после создания активности
        autoClickHandler.postDelayed(autoClickRunnable, 1000); // Интервал в миллисекундах (здесь 1000 миллисекунд = 1 секунда)
    }


    public void showUpgradeDialog(View view) {
        // Используем LayoutInflater для инфлейта макета всплывающего окна
        LayoutInflater inflater = LayoutInflater.from(this);
        // Создаем AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Настройки")
                .setPositiveButton("Сброс прогресса", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CLEARDATAMAINACTIVITY();
                    }
                });
        // Показываем AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int LvlUp(double _price, int _lvl_upgrade){
        if (counter >= _price && _lvl_upgrade < 30){
            _lvl_upgrade++;
        }
        return _lvl_upgrade;
    }
    private static String FormatDecimal1(double number) {
        String new_number = String.format("%.1f", number);
        return new_number;
    }

    private void CLEARDATAMAINACTIVITY(){
        counter = 1;
        factor = 1;
        clicks = 0;
        level = 1;
        clicksNeededForNextLevel = 10;
        img_value = 0;
        updateUI();
    }
}
