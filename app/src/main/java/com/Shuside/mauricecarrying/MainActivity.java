package com.Shuside.mauricecarrying;

import static android.view.View.INVISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Clock 1
    TextView clock1text;
    Button input1;
    Button start1;
    Button stop1;
    Button reset1;



    // Clock 2
    TextView clock2text;
    Button input2;
    Button start2;
    Button stop2;
    Button reset2;

    // Penalty 1
    TextView penaltyclock1;
    Button padd1;
    Button preset1;

    // Penalty 2
    TextView penaltyclock2;
    Button padd2;
    Button preset2;

    TextView finalTime1;
    TextView finalTime2;

    EditText popupText;
    ImageView popupImage;

    private Handler handler;
    private Runnable runnable;
    private int decisecond = 0;

    @SuppressLint({"MissingInflatedId", "SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // setup timer

        handler = new Handler(Looper.getMainLooper());




        // Do other ids
        popupText=(EditText)findViewById(R.id.popupText);
        popupImage=(ImageView)findViewById(R.id.popupImage);

        // Do Clock ids
        clock1text =(TextView)findViewById(R.id.ClockT1);
        clock2text =(TextView)findViewById(R.id.ClockT2);
        input1=(Button)findViewById(R.id.Inputt1);
        input2=(Button)findViewById(R.id.Inputt2);
        start1=(Button)findViewById(R.id.Startt1);
        start2=(Button)findViewById(R.id.Startt2);
        stop1=(Button)findViewById(R.id.Stopt1);
        stop2=(Button)findViewById(R.id.Stopt2);
        reset1=(Button)findViewById(R.id.Resett1);
        reset2=(Button)findViewById(R.id.Resett2);

        // Do Penalty Clock ids
        penaltyclock1=(TextView)findViewById(R.id.trackerTextt1);
        penaltyclock2=(TextView)findViewById(R.id.trackerTextt2);
        padd1=(Button)findViewById(R.id.trackerAddt1);
        preset1=(Button)findViewById(R.id.trackerResett1);
        padd2=(Button)findViewById(R.id.trackerAddt2);
        preset2=(Button)findViewById(R.id.trackerResett2);

        // Do Final Time Clock ids
        finalTime1=(TextView)findViewById(R.id.finalTimet1);
        finalTime2=(TextView)findViewById(R.id.finalTimet2);

        popupText.setVisibility(INVISIBLE);
        popupImage.setVisibility(INVISIBLE);


        // CLOCK ONE

        final float[] clocktime = {0, 0};

        input1.setOnClickListener(v -> {
            // Get the input from the EditText
            String input = popupText.getText().toString();

            // Check if input is not empty
            if (!input.isEmpty()) {
                try {
                    clocktime[0] = Float.parseFloat(input);

                    // Update the clock text display
                    clock1text.setText(String.format("%.1f", clocktime[0]));
                    // Hide the popup elements
                    popupText.setVisibility(INVISIBLE);
                    popupImage.setVisibility(INVISIBLE);

                    //Clear the EditText for next time
                    popupText.setText("");
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    popupText.setError("Please enter a valid number");
                }
            } else {
                popupText.setError("Please enter a time");
            }
        });
        start1.setOnClickListener(v -> {
            runnable = new Runnable() {
                @Override
                public void run() {
                    decisecond++;
                    clocktime[0] += decisecond;
                    clock1text.setText(String.valueOf(clocktime[0]));

                    // Schedule the runnable to run again after .1 second
                    handler.postDelayed(this, 100);
                }
            };

            // Start the timer
            handler.post(runnable);

        });
        stop1.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);

        });
        reset1.setOnClickListener(v -> {
            // Your code to execute when the button is clicked
            clocktime[0] = 0;
            clock1text.setText(String.format("%.1f", clocktime[0]));

        });


        // CLOCK 2


        input2.setOnClickListener(v -> {
            // Get the input from the EditText
            String input = popupText.getText().toString();

            // Check if input is not empty
            if (!input.isEmpty()) {
                try {
                    clocktime[1] = Float.parseFloat(input);

                    // Update the clock text display
                    clock1text.setText(String.format("%.1f", clocktime[1]));
                    // Hide the popup elements
                    popupText.setVisibility(INVISIBLE);
                    popupImage.setVisibility(INVISIBLE);

                    //Clear the EditText for next time
                    popupText.setText("");
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    popupText.setError("Please enter a valid number");
                }
            } else {
                popupText.setError("Please enter a time");
            }
        });
        start2.setOnClickListener(v -> {
            runnable = new Runnable() {
                @Override
                public void run() {
                    decisecond++;
                    clocktime[1] += decisecond;
                    clock1text.setText(String.valueOf(decisecond));

                    // Schedule the runnable to run again after .1 second
                    handler.postDelayed(this, 100);
                }
            };

            // Start the timer
            handler.post(runnable);

        });
        stop2.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);

        });
        reset2.setOnClickListener(v -> {
            clocktime[1] = 0;
            clock1text.setText(String.format("%.1f", clocktime[1]));

        });

        // Penalty Clock 2

        final float[] penaltytime = {0, 0};

        padd1.setOnClickListener(v -> {
            penaltytime[0] += 5;
            penaltyclock1.setText(String.format("%.1f", penaltytime[0]));
        });

        preset1.setOnClickListener(v -> {
            penaltytime[0] = 0;
            penaltyclock1.setText(String.format("%.1f", penaltytime[0]));
        });

        // Penalty Clock 2

        padd1.setOnClickListener(v -> {
            penaltytime[1] += 5;
            penaltyclock1.setText(String.format("%.1f", penaltytime[0]));
        });

        preset1.setOnClickListener(v -> {
            penaltytime[1] = 0;
            penaltyclock1.setText(String.format("%.1f", penaltytime[0]));
        });

        // FINAL TIMES!!!!!!!!
        Runnable finalTimer = new Runnable() {
            @Override
            public void run() {
                finalTime1.setText(clocktime[0] + penaltytime[0] + "");
                finalTime1.setText(clocktime[1] + penaltytime[1] + "");
            }
        };
        handler.post(finalTimer);

    }
}