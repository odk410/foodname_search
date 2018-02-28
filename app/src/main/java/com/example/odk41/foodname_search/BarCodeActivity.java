package com.example.odk41.foodname_search;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.odk41.foodname_search.foodIngredients.FoodIngredients;
import com.example.odk41.foodname_search.productList.Repo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BarCodeActivity extends AppCompatActivity {

    TextView foodName;
    TextView allergie;
    ImageView thumbnail;
    ImageView imageView;
    ImageView pigImage;
    ImageView cowImage;
    ImageView chickenImage;
    Bitmap bitmap;
    LinearLayout materialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code);

        initView();


    }

    //레이아웃과 연결
    private void initView() {

        foodName = (TextView) findViewById(R.id.foodName);
        allergie = (TextView) findViewById(R.id.allergie);
        thumbnail = (ImageView) findViewById(R.id.thumbnail);

        // 이미지뷰 초기화
        pigImage = (ImageView) findViewById(R.id.pig);
        cowImage = (ImageView) findViewById(R.id.cow);
        chickenImage = (ImageView) findViewById(R.id.chicken);

        pigImage.setVisibility(View.GONE);
        cowImage.setVisibility(View.GONE);
        chickenImage.setVisibility(View.GONE);

    }


    //추가부분 바코드 인식 기능
    public void scanBarcode(View view) {

        IntentIntegrator integrator = new IntentIntegrator(this);
        //카메라 가로, 세로 설정
        integrator.setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        integrator.addExtra("PROMPT_MESSAGE", "바코드를 사각형 안에 비춰주세요");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();


            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String barcode = result.getContents();
                Log.e(this.getClass().getName(), "바코드 : " + barcode);
                getInfo(barcode);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getInfo(String scanBarcode) {
        //create OkHttp Client Log를 확인하기 위해
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .client(okhttpClientBuilder.build())
                .build();

        final ApiService apiService = retrofit.create(ApiService.class);
        Call<Repo> call = apiService.getInfo(Constants.YELP_TOKEN, "ALL", "barcode", scanBarcode, 0, 2);
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Log.e(this.getClass().getName(), "onResponse 까지 들어옴");
                if (response.isSuccessful()) {
                    //바코드 데이터를 받아온다.
                    Repo object = response.body();
                    Log.e(this.getClass().getName(), "바코드 데이터를 받아왔당");

                    if (object != null) {
                        //데이터가 null값이 아니라면 바코드 데이터를 텍스트뷰로 보여준다.
                        for (int i = 0; i < object.getItems().size(); i++) {
                            foodName.setText(object.getItems().get(i).getFoodName());
                            setFoodDetail(object.getItems().get(i).getFoodId(), apiService);
                            setThumbnail(object.getItems().get(i).getThumbnailUrl());
                            Log.e(this.getClass().getName(), "텍스트 뷰로 나와야함");

                        }

                    } else {
                        Log.e(this.getClass().getName(), "실패했다 ㅠㅠ");
                    }

                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });
    }

    private void setThumbnail(final String thumbnailURL){

        // 안드로이드에서 네트워크 관련 작업을 할 때는
        // 반드시 메인 스레드가 아닌 별도의 작업 스레드에서 작업해야 한다.

        Thread mThread = new Thread(){

            @Override
            public void run(){
                try{

                    // URL 주소를 이용해서 URL 객체 생성
                    URL url = new URL(thumbnailURL);

                    // 아래 코드는 웹에서 이미지를 가져온 뒤
                    // 이미지 뷰에서 지정할 Bitmap을 생성하는 과정
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (IOException ex){
                }
            }
        };

        // 웹에서 이미지를 가져오는 작업 스레드 실행
        mThread.start();


        try{
            // 메인 스레드는 작업 스레드가 이미지 작업을 가져올 때가지
            // 대기해야 하므로 작업스레드의 join() 메소드를 호출해서
            // 메인 스레드가 작업 스레드가 종료될때 까지 기다리도록 합니다.
            mThread.join();

            // 이제 작업 스레드에서 이미지를 불러오는 작업을 완료했기에
            // UI 작업을 할 수 있는 메인스레드에서 이미지뷰에 이미지를 지정합니다.
            thumbnail.setImageBitmap(bitmap);
        }catch (InterruptedException e) {

        }
    }

    private void setFoodDetail(String foodId, ApiService apiService){

        Call<FoodIngredients> foodDetailCall = apiService.getDetailInfo(foodId, Constants.YELP_TOKEN);
        foodDetailCall.enqueue(new Callback<FoodIngredients>() {
            @Override
            public void onResponse(Call<FoodIngredients> call, Response<FoodIngredients> response) {
                //식품 상세 데이터를 받아온다.
                FoodIngredients detailFoodObject = response.body();
                Log.e(this.getClass().getName(), "식품 상세 데이터를 받아왔당");

                if (detailFoodObject != null) {
                    //데이터가 null값이 아니라면 식품 상세 데이터를 텍스트뷰로 보여준다.
                    String strAllergie = detailFoodObject.getAllergyIngredientContent();
                    setClassification(strAllergie);
                    allergie.setText(strAllergie);
                    Log.e(this.getClass().getName(), "텍스트 뷰로 나와야함");
                } else {
                    Log.e(this.getClass().getName(), "실패했다 ㅠㅠ");
                }
            }

            @Override
            public void onFailure(Call<FoodIngredients> call, Throwable t) {

            }
        });

    }

    private void setClassification(String strAllergie){

        Log.e(this.getClass().getName(), "-------------------이미지 부분-------------------");

        String[] material = null;

        // ","를 기준으로 구분할 것
        material = strAllergie.split(",");

        List<String> materialList = Arrays.asList(material);

        // 원재료가 존재한다면
        if(!materialList.isEmpty()){

            // 부모 뷰
//            materialView = (LinearLayout) findViewById(R.id.materialView);

            // 이미지 뷰
            pigImage = (ImageView) findViewById(R.id.pig);
            cowImage = (ImageView) findViewById(R.id.cow);
            chickenImage = (ImageView) findViewById(R.id.chicken);

            pigImage.setVisibility(View.GONE);
            cowImage.setVisibility(View.GONE);
            chickenImage.setVisibility(View.GONE);


//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
//            imageView.setVisibility(View.GONE);

            if(materialList.contains("닭고기")){
                // 이미지뷰 생성
//                ImageView imageView = new ImageView(this);
//                imageView.setImageResource(R.drawable.chicken);
//                imageView.setLayoutParams(layoutParams);
//                materialView.addView(imageView);
                chickenImage.setVisibility(View.VISIBLE);
            }
            if(materialList.contains("돼지고기")){
                // 이미지뷰 생성
//                ImageView imageView = new ImageView(this);
//                imageView.setImageResource(R.drawable.pig);
//                imageView.setLayoutParams(layoutParams);
//                materialView.addView(imageView);
                pigImage.setVisibility(View.VISIBLE);
            }
            if(materialList.contains("쇠고기")){
                // 이미지뷰 생성
//                ImageView imageView = new ImageView(this);
//                imageView.setImageResource(R.drawable.cow);
//                imageView.setLayoutParams(layoutParams);
//                materialView.addView(imageView);
                cowImage.setVisibility(View.VISIBLE);
            }
        }



    }



}
