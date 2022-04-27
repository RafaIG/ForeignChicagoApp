package com.example.foreignchicagoapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class PhotoActivity  extends AppCompatActivity {

    private ImageView randomImage;
    private Button buttonReload;
    private TextView title;

    private String limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        try {
            checkLimit();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        title = (TextView) findViewById(R.id.textEvent);

        randomImage = (ImageView) findViewById(R.id.imageRandom);
        randomImage.setImageResource(R.drawable.image_placeholder);
        try {
            setRandomImage();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonReload = (Button) findViewById(R.id.buttonReload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomImage.setImageResource(R.drawable.image_placeholder);
                try {
                    setRandomImage();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setRandomImage() throws JSONException, IOException {
        JSONObject json = getJsonObject("https://api.artic.edu/api/v1/artworks?fields=id,title,image_id&page="+
                String.valueOf(new Random().nextInt(Integer.parseInt(limit))+1) + "&limit=1");
        title.setText(json.getJSONArray("data").getJSONObject(0).getString("title"));
        Picasso.get().load("https://www.artic.edu/iiif/2/"+
                        json.getJSONArray("data").getJSONObject(0).getString("image_id") +
                "/full/843,/0/default.jpg").
                error(R.drawable.image_error).
                placeholder(R.drawable.image_placeholder).
                into(randomImage);
    }

    private void checkLimit() throws JSONException, IOException {
        JSONObject json = getJsonObject("https://api.artic.edu/api/v1/artworks?fields=id,title,image_id&limit=1");
        limit = json.getJSONObject("pagination").getString("total");
    }

    private JSONObject getJsonObject(String s) throws JSONException, IOException {
        JSONObject json = null;
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
            json = new JSONObject(inline);
        }
        return json;
    }

}
