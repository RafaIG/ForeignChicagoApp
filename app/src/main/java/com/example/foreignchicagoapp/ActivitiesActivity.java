package com.example.foreignchicagoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ActivitiesActivity extends AppCompatActivity {

    private ImageView randomImage;
    private Button buttonReload;
    private TextView title, organization, category, capacity, url, description;
    JSONArray json;

    int limit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MyTask().execute("https://data.cityofchicago.org/resource/w22p-bfyb.json?program_type=workshop&min_age=18");

        setContentView(R.layout.activity_activities);

        title = (TextView) findViewById(R.id.textEvent);
        organization = (TextView) findViewById(R.id.textOrganization);
        category = (TextView) findViewById(R.id.textCategory);
        capacity = (TextView) findViewById(R.id.textCapacity);
        url = (TextView) findViewById(R.id.textURL);
        description = (TextView) findViewById(R.id.textDescription);

        randomImage = (ImageView) findViewById(R.id.imageRandom);
        randomImage.setVisibility(View.INVISIBLE);

        buttonReload = (Button) findViewById(R.id.buttonReload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomImage.setVisibility(View.INVISIBLE);
                try {
                    setRandomEvent();
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Toast.makeText(getApplicationContext(), "Loading events! Please wait", Toast.LENGTH_LONG).show();

        //SystemClock.sleep(6000);
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        while (limit == -1) {
        }

        try {
            setRandomEvent();
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }*/

    private class MyTask extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    json = getJsonObject("https://data.cityofchicago.org/resource/w22p-bfyb.json?program_type=workshop&min_age=18");
                    limit = json.length() - 1;
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(String result) {
                try {
                    setRandomEvent();
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }

        }

    private void setRandomEvent() throws JSONException, ParseException {
        int random = new Random().nextInt(limit);
        title.setText(json.getJSONObject(random).getString("program_name"));
        organization.setText("Organization: " + json.getJSONObject(random).getString("org_name"));
        category.setText("Category: " + json.getJSONObject(random).getString("category_name"));
        capacity.setText("Max capacity: " + json.getJSONObject(random).getInt("capacity"));
        url.setText("URL: " + json.getJSONObject(random).getJSONObject("program_url").getString("url"));
        description.setText("Description: " + json.getJSONObject(random).getString("description"));
        if (json.getJSONObject(random).has("image")) {
            Picasso.get().load(json.getJSONObject(random).getJSONObject("image").getString("url")).
                    error(R.drawable.image_error).
                    placeholder(R.drawable.image_placeholder).
                    into(randomImage);
            randomImage.setVisibility(View.VISIBLE);
        }
        else
            randomImage.setVisibility(View.INVISIBLE);
    }

    private JSONArray getJsonObject(String s) throws JSONException, IOException {
        JSONArray json = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = new URL(s);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() != 200)
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        else {
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();
            json = new JSONArray(inline);
        }
        return json;
    }
}
