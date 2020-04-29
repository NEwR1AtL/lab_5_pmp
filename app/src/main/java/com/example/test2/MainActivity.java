package com.example.test2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button rotate = findViewById(R.id.rotate);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.rotate();
            }
        });
    }

    private void rotate() {
        int orientation = getRequestedOrientation();
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onMenuNextActivityClicked(MenuItem item) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void onMenuFlipClicked(MenuItem item) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @SuppressLint("SetTextI18n")
    public void onMenuFillFormClicked(MenuItem item) {
        EditText name = findViewById(R.id.name);
        name.setText("Serhii");
        EditText group = findViewById(R.id.group);
        group.setText("IPZ");
        EditText course = findViewById(R.id.course);
        course.setText("3");
    }

    public void onMenuColorClicked(MenuItem item) {
        @SuppressLint("WrongViewCast") RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rel_layout);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        relativeLayout.setBackgroundColor(color);
    }

    public void onClick(View v) {
        final EditText nameText = findViewById(R.id.name);
        final EditText groupText = findViewById(R.id.group);
        final EditText courseText = findViewById(R.id.course);

        String name = nameText.getText().toString();
        String group = groupText.getText().toString();
        int course = Integer.parseInt(courseText.getText().toString());

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("group", group);
        intent.putExtra("course", course);
        startActivity(intent);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Перехід до наступної активності", Toast.LENGTH_SHORT).show();
                onMenuNextActivityClicked(item);
                return true;
            case R.id.item2:
                Toast.makeText(this, "Картинка", Toast.LENGTH_SHORT).show();
                getToastImg().show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Введено дані", Toast.LENGTH_SHORT).show();
                onMenuFillFormClicked(item);
                return true;
            default:
                return false;
        }
    }
    public Toast getToastImg(){
        ImageView img = new ImageView(getApplicationContext());
        img.setImageResource(R.drawable.toast);
        Toast toast = Toast.makeText(getApplicationContext(),"",
                Toast.LENGTH_LONG);
        toast.setView(img);
        return toast;
    }
}
