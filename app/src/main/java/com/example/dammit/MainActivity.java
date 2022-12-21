package com.example.dammit;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.util.Util;

public class MainActivity extends AppCompatActivity {
    //implements View.OnClickListener
    public static RequestQueue rq = null;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DamFlagmentA damFlagmentA;
    private DamFlagmentB damFlagmentB;

    private long mLastClickTime = 0;

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (rq == null) {
            rq = Volley.newRequestQueue(this);
        }

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Utils.prevent

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                FragmentView(Fragment_1);
            }


        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

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