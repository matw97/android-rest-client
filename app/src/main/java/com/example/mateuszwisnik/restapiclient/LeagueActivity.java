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

public class LeagueActivity extends AppCompatActivity {

    private TextView rank;
    private TextView tier;
    private InputStream responseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        rank = findViewById(R.id.rank);
        tier = findViewById(R.id.tier);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL apiEndpoint = new URL("https://eun1.api.riotgames.com/lol/league/v4/positions/by-summoner/QBu3MO2kU1CC3FO4oTupJ6dIEX_j_OWVDnWQPSUvpOJAGlw?api_key=RGAPI-c68b3fb7-b072-4233-a655-7519f93148b6");
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
                    jsonReader.beginArray();
                    jsonReader.beginObject();
                    while(jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        if(key.equals("tier"))
                        {
                            String value = jsonReader.nextString();
                            tier.setText(value);
                        }
                        else if(key.equals("rank"))
                        {
                            String value = jsonReader.nextString();
                            rank.setText(value);
                        }
                        else {
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
