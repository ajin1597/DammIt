package com.example.dammit;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue rq = null;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DamFlagmentA damFlagmentA;
    private DamFlagmentB damFlagmentB;
    private DamFlagmentC damFlagmentC;

//    private long mLastClickTime = 0;

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RequestQueue가 NULL값일 때 다시 생성
        if (rq == null) {
            rq = Volley.newRequestQueue(this);
        }

        FragmentView(Fragment_1);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Utils.prevent
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//

                FragmentView(Fragment_1);
            }


        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();

                FragmentView(Fragment_2);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();

                FragmentView(Fragment_3);
            }
        });
    }

    // 버튼에서 클릭한 fragment 화면을 보여주기 위한 코드
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
            case 3:
                DamFlagmentC damFlagmentC = new DamFlagmentC();
                transaction.replace(R.id.frameLayout, damFlagmentC);
                transaction.commit();
                break;
        }
    }
}