package com.example.odk41.foodname_search;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odk41.foodname_search.pojo.Item;
import com.example.odk41.foodname_search.pojo.Repo;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

public class MainActivity extends AppCompatActivity {

    TextView foodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
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
        if(result != null) {
            if(result.getContents() == null) {
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

    private void initView(){
        foodInfo = (TextView) findViewById(R.id.foodInfo);
    }

    private void getInfo(String scanBarcode){
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

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Repo> call = apiService.getInfo(Constants.YELP_TOKEN, "ALL", "barcode", scanBarcode, 0,2);
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {

                Log.e(this.getClass().getName(), "onResponse 까지 들어옴");
                if(response.isSuccessful()){
                    //바코드 데이터를 받아온다.
                    Repo object = response.body();
                    Log.e(this.getClass().getName(), "바코드 데이터를 받아왔당");

                    if(object != null) {
                        //데이터가 null값이 아니라면 바코드 데이터를 텍스트뷰로 보여준다.
                        for(int i=0; i<object.getItems().size(); i++){
                            foodInfo.setText(object.getItems().get(i).getFoodName());
                            Log.e(this.getClass().getName(), "텍스트 뷰로 나와야함");
                        }

                    }
                    else{
                        Log.e(this.getClass().getName(), "실패했다 ㅠㅠ");
                    }
                }

            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {

            }
        });
    }
}
