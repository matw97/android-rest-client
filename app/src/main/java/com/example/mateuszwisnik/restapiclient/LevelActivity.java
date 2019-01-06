package com.example.mateuszwisnik.restapiclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class LevelActivity extends AppCompatActivity {

    private TextView level;
    private InputStream responseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        level = findViewById(R.id.level);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL apiEndpoint = new URL("https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/Egirl%20Seeker?api_key=RGAPI-c68b3fb7-b072-4233-a655-7519f93148b6");
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) apiEndpoint.openConnection();
                    if(httpsURLConnection.getResponseCode() == 200) {
                        responseBody = httpsURLConnection.getInputStream();
                        httpsURLConnection.disconnect();
                        populateTextViews();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void populateTextViews() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginObject();
                    while(jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        if(key.equals("summonerLevel"))
                        {
                            String value = jsonReader.nextString();
                            level.setText(value);
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
