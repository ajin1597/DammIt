package com.example.dammit;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
//implements View.OnClickListener

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DamFlagmentA damFlagmentA;
    private DamFlagmentB damFlagmentB;

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_1);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_2);
            }
        });
    }


    private void FragmentView(int fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (fragment) {
            case 1:
                DamFlagmentA damFlagmentA = new DamFlagmentA();
                transaction.replace(R.id.frameLayout, damFlagmentA);
                transaction.commit();
                break;
            case 2:
                DamFlagmentB damFlagmentB = new DamFlagmentB();
                transaction.replace(R.id.frameLayout, damFlagmentB);
                transaction.commit();
                break;
        }
    }
}