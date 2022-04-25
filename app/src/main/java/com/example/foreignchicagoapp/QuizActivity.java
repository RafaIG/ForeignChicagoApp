package com.example.foreignchicagoapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity {

    TextView txtView;
    TextView textTimer;

    ArrayList<String> stringList = new ArrayList<String>();

    static int questionNum = 0;

    private RadioGroup radioQuestions;
    private RadioButton radioButton;
    private RatingBar rb;

    private boolean stop;
    private long start = System.currentTimeMillis();
    Thread thread;

    private boolean[] correctAnswers = {true,false,true,false,true};

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //BackgroundTask bt = new BackgroundTask();
        //bt.execute("http://www.papademas.net:81/sample.txt"); //grab url

        start = System.currentTimeMillis();
        textTimer = findViewById(R.id.textTimer);
        stop =false;
        thread=null;

        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");
        stringList.add("a");

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!stop)
                                    textTimer.setText("Current time: " +
                                            new SimpleDateFormat("mm:ss").format(
                                                    System.currentTimeMillis() - start));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }//end onCreate

    //background process to download the file from internet
    /*private class BackgroundTask extends AsyncTask<String, Integer, Void> {

        protected void onPreExecute() {
        }

        protected Void doInBackground(String... params) {
            URL url;
            String StringBuffer = null;
            try {
                //create url object to point to the file location on internet
                url = new URL(params[0]);
                //make a request to server
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //get InputStream instance
                InputStream is = con.getInputStream();
                //create BufferedReader object
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                //read content of the file line by line & add it to Stringbuffer
                int i = 0;
                while ((StringBuffer = br.readLine()) != null) {
                    Thread.sleep(500);
                    int prog = 40 + 10*++i;
                    stringList.add(StringBuffer);  //add to Arraylist
                }

                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            txtView = findViewById(R.id.textView1);
            //display read text in TextVeiw
            txtView.setText(stringList.get(0));
            pd.setMessage("Loading 100");
            pd.cancel();
            startQuiz();
        }
    }//end BackgroundTask class
*\

     */
    public void startQuiz() {
        buttonListener();
    }

    public void buttonListener() {
        Button btnDisplay;

        radioQuestions = findViewById(R.id.radioQuestions);
        btnDisplay = findViewById(R.id.btnDisplay);

        rb = findViewById(R.id.ratingBar);
        rb.setVisibility(View.INVISIBLE);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioQuestions.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = findViewById(selectedId);

                switch (questionNum) {
                    case 0:
                    case 2:
                        showResult("True",questionNum);
                        break;
                    case 1:
                    case 3:
                    case 4:
                        showResult("False",questionNum);
                        break;
                }//end switch
            }
        });
        imageListener();
    }//end buttonListener

    public void showResult(String result, int questionNum){
        if (radioButton.getText().equals(result)) {
            Toast.makeText(QuizActivity.this,
                    " Right!", Toast.LENGTH_LONG).show();
            correctAnswers[questionNum] = true;
        }
        else {
            Toast.makeText(QuizActivity.this,
                    " Wrong!", Toast.LENGTH_LONG).show();
            correctAnswers[questionNum] = false;
        }
    }

    public void imageListener() {

        image = findViewById(R.id.imageView1);
        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // get new question for viewing
                if (questionNum == 4) {
                    stop = true;
                    //reset count to -1 to start first question again
                    rb = findViewById(R.id.ratingBar);
                    rb.setRating(Arrays.toString(correctAnswers).replaceAll("[^t]",
                            "").length());
                    findViewById(R.id.ratingBar).setVisibility(View.VISIBLE);
                    textTimer.setText("You have finished with a time of : "+
                            new SimpleDateFormat("mm:ss").format(
                                    System.currentTimeMillis() - start)+
                            "\nPlease click in next to start again the quiz.");
                    questionNum++;
                }
                else if (questionNum > 4) {
                    start = System.currentTimeMillis();
                    stop = false;
                    findViewById(R.id.ratingBar).setVisibility(View.INVISIBLE);
                    questionNum = 0;
                    txtView.setText(stringList.get(++questionNum));
                    radioQuestions.check(R.id.radioTrue);
                }
                else {
                    txtView.setText(stringList.get(++questionNum));
                    //reset radio button (radioTrue) to default
                    radioQuestions.check(R.id.radioTrue);
                }
            }
        });
    }

}
