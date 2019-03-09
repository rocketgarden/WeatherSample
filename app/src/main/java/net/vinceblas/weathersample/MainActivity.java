package net.vinceblas.weathersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText zipcodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.button_submit);
        zipcodeInput = findViewById(R.id.zipcode_input);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String zipcode = zipcodeInput.getText().toString();

                final int zipCodeNumber = Integer.parseInt(zipcode);


                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(WeatherActivity.EXTRA_ZIP, zipCodeNumber);
                startActivity(intent);

            }
        });
    }

}
