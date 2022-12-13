package com.example.dammit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import androidx.fragment.app.Fragment;
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


//    public static String url = "http://61.103.243.140:3000/api/sensor";
//    public static String url1 = "http://61.103.243.188:8080";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("111111111111111111111");
                System.out.println("22222222222222");

                FragmentView(Fragment_1);
                System.out.println("3333333333333333");
            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ImageView sadImg = (ImageView) findViewById(R.id.sad_image);
//                DrawableImageViewTarget gifImage = new DrawableImageViewTarget(sadImg);
//                Glide.with(this).load(R.raw.sad_face).into(sadImg);


                FragmentView(Fragment_2);

            }
        });


//        FragmentView(Fragment_1);
//        fragmentManager = getSupportFragmentManager();
//        damFlagmentA = new DamFlagmentA();
//        damFlagmentB = new DamFlagmentB();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout,damFlagmentA).commitAllowingStateLoss();
//        fragmentTransaction.add(R.id.frameLayout,damFlagmentA);
//        fragmentTransaction.commit();


//        Button damBtn1 = findViewById(R.id.button1);
//        damBtn1.setOnClickListener(v -> {
//            FragmentManager fm1 = getSupportFragmentManager();
//            FragmentTransaction ft1 = fragmentManager.beginTransaction();
//            ft1.replace(R.id.frameLayout, damFlagmentA);
//            ft1.commit();
//
//
//        });
//
//        Button damBtn2 = findViewById(R.id.button2);
//        damBtn2.setOnClickListener(v -> {
//            FragmentManager fm2 = getSupportFragmentManager();
//            FragmentTransaction ft2 = fragmentManager.beginTransaction();
//            ft2.replace(R.id.frameLayout, damFlagmentB);
//
//            ImageView sadImg = (ImageView) findViewById(R.id.sad_image);
//            DrawableImageViewTarget gifImage = new DrawableImageViewTarget(sadImg);
//            Glide.with(this).load(R.raw.sad_face).into(sadImg);
//
//            ft2.commit();
//
//
//        });

//        Button damBtn3 = findViewById(R.id.button3);
//        damBtn3.setOnClickListener(this);

    }


    private void FragmentView(int fragment) {
        System.out.println("555555555555555555555555555555555555555555555555");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment) {
            case 1:

                DamFlagmentA damFlagmentA = new DamFlagmentA();
                transaction.replace(R.id.frameLayout, damFlagmentA);
                transaction.commit();
                System.out.println("444444444444444444444444444444444444444444");
                break;

            case 2:

                DamFlagmentB damFlagmentB = new DamFlagmentB();
                transaction.replace(R.id.frameLayout, damFlagmentB);
                transaction.commit();
                break;
        }
    }

//    @Override
//    public void clickHandler(View view) {
//        fragmentTransaction = fragmentManager.beginTransaction();
//
//        switch (view.getId()) {
//            case R.id.button1 :
//               fragmentTransaction.replace(R.id.frameLayout, damFlagmentA).commitAllowingStateLoss();
////                fragmentTransaction.add(R.id.frameLayout,damFlagmentA);
//                fragmentTransaction.commit();
////                FragmentManager fm1 = getSupportFragmentManager();
////                FragmentTransaction ft1 = fragmentManager.beginTransaction();
//
////                textView = findViewById(R.id.textView);
////                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        //응답
////                        textView.setText(response);
////                    }
////                }, new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        //에러
////                        textView.setText("에러: " + error.toString());
////                    }
////                });
////
////                rq.add(stringRequest);
//                break;
//
//            case R.id.button2 :
//                //
//                fragmentTransaction.replace(R.id.frameLayout, damFlagmentB).commitAllowingStateLoss();
////                fragmentTransaction.add(R.id.frameLayout,damFlagmentB);
//                fragmentTransaction.commit();
//
////                ImageView sadImg = (ImageView) findViewById(R.id.sad_image);
////                DrawableImageViewTarget gifImage = new DrawableImageViewTarget(sadImg);
////                Glide.with(this).load(R.raw.sad_face).into(sadImg);
//
//
//                break;
//
//            case R.id.button3 :
//                //
//                break;
//
//        }
//    }


}