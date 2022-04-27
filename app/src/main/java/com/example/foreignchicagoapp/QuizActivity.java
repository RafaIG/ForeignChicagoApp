package com.example.foreignchicagoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    TextView txtView;
    TextView textTimer;
    ImageView imageNext;

    ArrayList<Question> questionList = new ArrayList<Question>();
    private boolean[] correctAnswers = new boolean[]{false, false, false, false, false};

    static int questionNum = 0;

    private RadioGroup radioQuestions;
    private RadioButton radioButton;
    private RatingBar rb;

    private boolean stop;
    private long start = System.currentTimeMillis();
    Thread thread;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        start = System.currentTimeMillis();
        textTimer = findViewById(R.id.textTimer);
        stop =false;
        thread=null;

        readQuestions();
        Collections.shuffle(questionList);

        correctAnswers = new boolean[]{questionList.get(0).isResult(),
                questionList.get(1).isResult(), questionList.get(2).isResult(),
                questionList.get(3).isResult(), questionList.get(4).isResult()};

        txtView = findViewById(R.id.textView1);
        txtView.setText(questionList.get(0).getQuestion());

        imageNext = findViewById(R.id.imageNext);

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
        startQuiz();
    }


    public void readQuestions() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("questions");
            questionList = new ArrayList<Question>();

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("QUIZ", jo_inside.getString("question"));
                String question = jo_inside.getString("question");
                Boolean result = jo_inside.getBoolean("result");

                //Add your values in your `ArrayList` as below:
                questionList.add(new Question(question, result));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = QuizActivity.this.getAssets().open("quiz_questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

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

                showResult(String.valueOf(questionList.get(questionNum).isResult()),
                        questionNum);
            }
        });
        imageListener();
    }//end buttonListener

    public void showResult(String result, int questionNum){
        if (radioButton.getText().toString().equalsIgnoreCase(result))
            Toast.makeText(QuizActivity.this,
                    " Right!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(QuizActivity.this,
                    " Wrong!", Toast.LENGTH_SHORT).show();
    }

    public void checkResult(String result, int questionNum){
        if (radioButton.getText().toString().equalsIgnoreCase(result))
            correctAnswers[questionNum] = true;
        else
            correctAnswers[questionNum] = false;
    }

    public void imageListener() {

        image = findViewById(R.id.imageNext);
        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (questionNum > 4) {
                    start = System.currentTimeMillis();
                    stop = false;
                    findViewById(R.id.ratingBar).setVisibility(View.INVISIBLE);
                    questionNum = 0;
                    Collections.shuffle(questionList);
                    txtView.setText(questionList.get(questionNum).getQuestion());
                    radioQuestions.check(R.id.radioTrue);
                    imageNext.setImageResource(R.drawable.quiz_next);
                }
                else {
                    int selectedId = radioQuestions.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    checkResult(String.valueOf(questionList.get(questionNum).isResult()),
                            questionNum);
                    // get new question for viewing
                    if (questionNum == 4) {
                        stop = true;
                        //reset count to -1 to start first question again
                        rb = findViewById(R.id.ratingBar);
                        rb.setRating(Arrays.toString(correctAnswers).replaceAll("[^t]",
                                "").length());
                        findViewById(R.id.ratingBar).setVisibility(View.VISIBLE);
                        textTimer.setText("You have finished with a time of : " +
                                new SimpleDateFormat("mm:ss").format(
                                        System.currentTimeMillis() - start) +
                                "\nPlease click in the next icon to start again the quiz.");
                        questionNum++;
                        imageNext.setImageResource(R.drawable.quiz_restart);
                    } else {
                        txtView.setText(questionList.get(++questionNum).getQuestion());
                        //reset radio button (radioTrue) to default
                        radioQuestions.check(R.id.radioTrue);
                    }
                }
            }
        });
    }

}

class Question {

    private String question;
    private boolean result;

    public Question(String question, boolean result){
        this.question = question;
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
