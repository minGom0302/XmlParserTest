package com.example.xml_parser_test_withretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView testTv, testComTv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL = "https://apis.data.go.kr/1262000/CountryBasicService/";
        String serviceKey = "IigVgrA8KY3lQIG3RDCY3MPQRvX6mP5LfayEe7bv5LaX9RgF%2F%2FtcJQB1Bg6C2xGAvlwl5yTNRAckKCL2kUuSOw%3D%3D";
        String country = "가나";
        String TAG = String.valueOf(getClass());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
                .build();

        ContextAPI api = retrofit.create(ContextAPI.class);

        testTv = findViewById(R.id.testTv);
        testComTv = findViewById(R.id.testComTv);
        findViewById(R.id.btn2).setOnClickListener(v -> {
            Intent intent = new Intent(this, XmlPullParserTest.class);
            startActivity(intent);
        });

        findViewById(R.id.btn).setOnClickListener(v -> {
            findViewById(R.id.btn).setVisibility(View.INVISIBLE);
            api.getData(serviceKey, "200", "1", country).enqueue(new Callback<dataOPOJ>() {
                @Override
                public void onResponse(@NonNull Call<dataOPOJ> call, @NonNull Response<dataOPOJ> response) {
                    if(response.isSuccessful()) {
                        if(response.body() != null) {
                            dataOPOJ data = response.body();

                            if(data.getHeader().getResultCode().equals("00")) {
                                List<dataOPOJ.Item> itemList = new ArrayList<>(data.getBody().getItems().getItem());
                                StringBuffer stringBuffer = new StringBuffer();
                                for (int i = 0; i < itemList.size(); i++) {
                                    stringBuffer.append(itemList.get(i).getCountryName());
                                }
                                testTv.setText(stringBuffer);

                                testComTv.setText(itemList.get(0).getBasic());
                            } else {
                                Log.e(TAG, "result Error : " + data.getHeader().getResultCode());
                                Log.e(TAG, "result Error Msg : " + data.getHeader().getResultMsg());
                            }
                        } else {
                            Log.e(TAG, "response body is null");
                        }
                    } else {
                        Log.e(TAG, "response is not successful");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<dataOPOJ> call, @NonNull Throwable t) {
                    Log.e(TAG, "api communication is failure : " + t.getMessage());
                }
            });
        });
    }
}