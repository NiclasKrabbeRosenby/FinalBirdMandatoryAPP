package net.simplifiedlearning.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class all_observationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    RequestQueue rq;

    TextView nameText, descriptionText;

    String name, description;

    String url="http://birdobservationservice.azurewebsites.net/Service1.svc/observations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_observation);

        mAuth = FirebaseAuth.getInstance();
        rq = Volley.newRequestQueue(this);

        nameText = (TextView) findViewById(R.id.textName);
        descriptionText = (TextView) findViewById(R.id.textDescription);

        sendjsonrequest();

    }

    public void sendjsonrequest()
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    name = response.getString("NameDanish");
                    description = response.getString("NameEnglish");


                    nameText.setText(name);
                    descriptionText.setText(description);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jsonObjectRequest);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, all_observationActivity.class));
        }
    }
}
