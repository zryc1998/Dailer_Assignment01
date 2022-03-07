package com.example.dailer_159336_assignment01_20008378;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LENGTH = "LENGTH";

    //store the length of textveiw, to make sure delete button appears correctly
    private int mLength = 0;

    private Button btnDelete;
    private EditText input;

    //restore textview's length variable, make sure delete button appears correctly after rotation
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(LENGTH, mLength);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if(savedInstanceState!=null) {
            mLength = savedInstanceState.getInt(LENGTH, 0);
        }

        input = findViewById(R.id.editTextNumber);

        //set buttons
        Button btnOne = findViewById(R.id.buttonOne);
        Button btnTwo = findViewById(R.id.buttonTwo);
        Button btnThree = findViewById(R.id.buttonThree);
        Button btnFour = findViewById(R.id.buttonFour);
        Button btnFive = findViewById(R.id.buttonFive);
        Button btnSix = findViewById(R.id.buttonSix);
        Button btnSeven = findViewById(R.id.buttonSeven);
        Button btnEight = findViewById(R.id.buttonEight);
        Button btnNine = findViewById(R.id.buttonNine);
        Button btnZero = findViewById(R.id.buttonZero);
        btnDelete = findViewById(R.id.buttonDelete);
        Button btnStar = findViewById(R.id.buttonStar);
        Button btnHashTag = findViewById(R.id.buttonHashTag);

        //check if delete button needs to be appeared
        if (mLength > 0) btnDelete.setVisibility(View.VISIBLE);

        btnOne.setOnClickListener(view->onButtonClick("1"));
        btnTwo.setOnClickListener(view->onButtonClick("2"));
        btnThree.setOnClickListener(view->onButtonClick("3"));
        btnFour.setOnClickListener(view->onButtonClick("4"));
        btnFive.setOnClickListener(view->onButtonClick("5"));
        btnSix.setOnClickListener(view->onButtonClick("6"));
        btnSeven.setOnClickListener(view->onButtonClick("7"));
        btnEight.setOnClickListener(view->onButtonClick("8"));
        btnNine.setOnClickListener(view->onButtonClick("9"));
        btnZero.setOnClickListener(view->onButtonClick("0"));
        btnStar.setOnClickListener(view->onButtonClick("*"));
        btnHashTag.setOnClickListener(view->onButtonClick("#"));

    }


//delete button's action when clicked
    public void deleteOnClick(View v){
        System.out.println(v);
        int length = input.getText().length();
        StringBuilder strDeleted = new StringBuilder(input.getText());
        if (length >= 1) {
            strDeleted.deleteCharAt(input.getText().length() - 1);
            input.setText(strDeleted.toString());
        }
        if (length <= 1){
            btnDelete.setVisibility(View.INVISIBLE);
        }
        mLength = length;
    }

    //dail button action when clicked
    public void dailOnClick(View v){
        String hash = input.getText().toString();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},0);
            return;
        } else {
            Intent sendIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + hash));
            startActivity(sendIntent);
        }
    }

    //numeric buttons action when clicked
    @SuppressLint("SetTextI18n")
    public void onButtonClick(String number){
        String cache = input.getText().toString();
        if (cache.length() >= 0){
            btnDelete.setVisibility(View.VISIBLE);
        }
        input.setText(cache + number);
        mLength = input.getText().length();
    }
}