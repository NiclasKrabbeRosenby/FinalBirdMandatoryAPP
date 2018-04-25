package net.simplifiedlearning.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class all_observationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    private ArrayAdapter adapter;

    //RequestQueue rq;

    //TextView nameText, descriptionText;

    //ListView listview;

    //String name, description;

    //String URL="http://birdobservationservice.azurewebsites.net/Service1.svc/birds";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_observation);

        mAuth = FirebaseAuth.getInstance();


        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Birds>> call = api.getBirds();

        call.enqueue(new Callback<List<Birds>>() {
            @Override
            public void onResponse(Call<List<Birds>> call, Response<List<Birds>> response) {
                final List<Birds> tools = response.body();

                String[] toolDescriptions = new String[tools.size()];

                for (int i = 0; i < tools.size(); i++) {
                    toolDescriptions[i] = tools.get(i).getNameDanish();
                }

                adapter = new ArrayAdapter(all_observationActivity.this, android.R.layout.simple_list_item_1, toolDescriptions);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getBaseContext(), activity_BirdList.class);
                            Birds thebird = tools.get(i);
                            intent.putExtra("bird", thebird);
                            startActivity(intent);
                        }
                });

                /*
                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                toolDescriptions
                        )
                );
                */



            }

            @Override
            public void onFailure(Call<List<Birds>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

        //final ArrayList arrayList = new ArrayList<>();
        //final ArrayList<String> arrayList = new ArrayList<>();
        //rq = Volley.newRequestQueue(this);

        //nameText = (TextView) findViewById(R.id.textName);
        //descriptionText = (TextView) findViewById(R.id.textDescription);
        //listview = (ListView) findViewById(R.id.AllBirds);


        //sendjsonrequest();

        /*
        String URL="http://birdobservationservice.azurewebsites.net/Service1.svc/birds";

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        JsonArrayRequest objectRequest = new JsonArrayRequest (Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Rest Response",response.toString());

                try{
                    //JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<response.length();i++){
                        JSONObject jresponse = response.getJSONObject(i);
                        String NameDanish = jresponse.getString("NameDanish");
                        arrayList.add(NameDanish);
                        Log.d("NameDanish", NameDanish);

                        //nameText.setText(NameDanish);

                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Rest Response",error.toString());
            }
        });

        requestQueue.add(objectRequest);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_all_observation, arrayList);

        ListView list = (ListView) findViewById(R.id.AllBirds);
        list.setAdapter(adapter);
        */




/*    public void sendjsonrequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Rest Response",response.toString());

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
    }*/



    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, all_observationActivity.class));
        }
    }
}
