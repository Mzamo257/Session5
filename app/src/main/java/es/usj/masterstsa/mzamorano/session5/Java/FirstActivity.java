package es.usj.masterstsa.mzamorano.session5.Java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.usj.masterstsa.mzamorano.session5.R;

public class FirstActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnPing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Session 5 - Java");

        tvResult = findViewById(R.id.tvResult);
        btnPing = findViewById(R.id.btnPing);

        btnPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ping("8.8.8.8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                runnable.run();
            }
        });
    }

    StringBuilder builder;
    private void ping(String ip) throws IOException {
        Process process = Runtime.getRuntime().exec("ping -c 8 " + ip);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }

        runOnUiThread();
    }

    private void runOnUiThread() {
        tvResult.setText(builder.toString());
    }

}
