package net.simplifiedlearning.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_BirdList extends AppCompatActivity {

    Birds observation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__bird_list);



        Intent intent = getIntent();
        observation = (Birds) intent.getSerializableExtra("bird");

        TextView nameTextView = findViewById(R.id.observationBirdName);
        nameTextView.setText(observation.getNameDanish());
    }
}
