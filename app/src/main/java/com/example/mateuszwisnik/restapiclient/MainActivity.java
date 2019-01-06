package com.example.mateuszwisnik.restapiclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView masteryLevelTextView;
    private TextView masteryPoints;
    private TextView chest;
    private TextView current;
    private Button level;
    private Button league;
    private Button trynd;
    private Button vlad;
    private InputStream responseBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masteryLevelTextView = findViewById(R.id.masteryLevel);
        masteryPoints = findViewById(R.id.points);
        chest = findViewById(R.id.chest);
        level = findViewById(R.id.buttonLevel);
        league = findViewById(R.id.buttonLeague);
        trynd = findViewById(R.id.trynd);
        vlad = findViewById(R.id.vlad);
        current = findViewById(R.id.current);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL apiEndpoint = new URL("https://eun1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/QBu3MO2kU1CC3FO4oTupJ6dIEX_j_OWVDnWQPSUvpOJAGlw/by-champion/35?api_key=RGAPI-c68b3fb7-b072-4233-a655-7519f93148b6");
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

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent);
            }
        });

        league.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeagueActivity.class);
                startActivity(intent);
            }
        });

        trynd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL apiEndpoint = new URL("https://eun1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/QBu3MO2kU1CC3FO4oTupJ6dIEX_j_OWVDnWQPSUvpOJAGlw/by-champion/23?api_key=RGAPI-c68b3fb7-b072-4233-a655-7519f93148b6");
                            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) apiEndpoint.openConnection();
                            if(httpsURLConnection.getResponseCode() == 200) {
                                final InputStream responseBody = httpsURLConnection.getInputStream();
                                httpsURLConnection.disconnect();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                                            JsonReader jsonReader = new JsonReader(responseBodyReader);
                                            jsonReader.beginObject();
                                            while(jsonReader.hasNext()) {
                                                String key = jsonReader.nextName();
                                                if(key.equals("championLevel"))
                                                {
                                                    String value = jsonReader.nextString();
                                                    masteryLevelTextView.setText(value);
                                                }
                                                else if(key.equals("chestGranted"))
                                                {
                                                    String value = String.valueOf(jsonReader.nextBoolean());
                                                    chest.setText(value);
                                                }
                                                else if(key.equals("championPoints"))
                                                {
                                                    String value = jsonReader.nextString();
                                                    masteryPoints.setText(value);
                                                }
                                                else if(key.equals("championId")) {
                                                    String value = jsonReader.nextString();
                                                    current.setText("Current champion id: " + value);
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        vlad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL apiEndpoint = new URL("https://eun1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/QBu3MO2kU1CC3FO4oTupJ6dIEX_j_OWVDnWQPSUvpOJAGlw/by-champion/8?api_key=RGAPI-c68b3fb7-b072-4233-a655-7519f93148b6");
                            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) apiEndpoint.openConnection();
                            if(httpsURLConnection.getResponseCode() == 200) {
                                final InputStream responseBody = httpsURLConnection.getInputStream();
                                httpsURLConnection.disconnect();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                                            JsonReader jsonReader = new JsonReader(responseBodyReader);
                                            jsonReader.beginObject();
                                            while(jsonReader.hasNext()) {
                                                String key = jsonReader.nextName();
                                                if(key.equals("championLevel"))
                                                {
                                                    String value = jsonReader.nextString();
                                                    masteryLevelTextView.setText(value);
                                                }
                                                else if(key.equals("chestGranted"))
                                                {
                                                    String value = String.valueOf(jsonReader.nextBoolean());
                                                    chest.setText(value);
                                                }
                                                else if(key.equals("championPoints"))
                                                {
                                                    String value = jsonReader.nextString();
                                                    masteryPoints.setText(value);
                                                }
                                                else if(key.equals("championId")) {
                                                    String value = jsonReader.nextString();
                                                    current.setText("Current champion id: " + value);
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
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
                        if(key.equals("championLevel"))
                        {
                            String value = jsonReader.nextString();
                            masteryLevelTextView.setText(value);
                        }
                        else if(key.equals("chestGranted"))
                        {
                            String value = String.valueOf(jsonReader.nextBoolean());
                            chest.setText(value);
                        }
                        else if(key.equals("championPoints"))
                        {
                            String value = jsonReader.nextString();
                            masteryPoints.setText(value);
                        }
                        else if(key.equals("championId")) {
                            String value = jsonReader.nextString();
                            current.setText("Current champion id: " + value);
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
