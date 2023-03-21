package com.example.xml_parser_test_withretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class XmlPullParserTest extends AppCompatActivity {
    TextView testTv, testComTv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_pull_parser);

        String firstURL = "https://apis.data.go.kr/1262000/CountryBasicService/getCountryBasicList?serviceKey=";
        String serviceKey = "발급받은 서비스키";
        String secURL = "&numOfRows=200&pageNo=1&countryName=가나";
        String TAG = String.valueOf(getClass());

        Runnable task = () -> {
            try {
                URL url = new URL(firstURL+serviceKey+secURL);

                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = xmlPullParserFactory.newPullParser();

                InputStream is = url.openStream();
                parser.setInput(new InputStreamReader(is, StandardCharsets.UTF_8));

                int eventType = parser.getEventType();
                String tagName = "";
                String countryName = "countryName";
                String basic = "basic";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            tagName = parser.getName();
                            // Log.d(TAG, tagName);
                            break;

                        case XmlPullParser.TEXT:
                            switch (tagName) {
                                case "basic" :
                                    basic = parser.getText();
                                    Log.d(TAG, parser.getText());
                                    break;
                                case "countryName" :
                                    countryName = parser.getText();
                                    Log.d(TAG, parser.getText());
                                    break;
                                case "resultMsg" :
                                    Log.d(TAG, parser.getText());
                                    break;
                            }
                            break;
                    }
                    eventType = parser.next();
                }

                String finalCountryName = countryName;
                String finalBasic = basic;
                runOnUiThread(() -> {
                    testTv.setText(finalCountryName);
                    testComTv.setText(finalBasic);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(task);

        testTv = findViewById(R.id.xml_testTv);
        testComTv = findViewById(R.id.xml_testComTv);

        findViewById(R.id.xml_btn).setOnClickListener(v ->
            thread.start()
        );
    }
}