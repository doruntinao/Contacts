package com.doruntinaosaj.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "avatar";
    public static final String EXTRA_EMRI ="first_name";
    public static final String EXTRA_MBIEMRI ="last_name";
    public static final String EXTRA_EMAIL ="email";
    public static final String EXTRA_ID ="id";
    public static final String EXTRA_USER ="users";


    private RecyclerView mRecyclerView;
    private ContactsAdapter mContactsAdapter;
    private ArrayList<Contacts> mContactList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON(){
        String url = "https://reqres.in/api/users?per_page=12";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for(int i = 0; i<jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);

                                String emri = data.getString("first_name");
                                String mbiemri = data.getString("last_name");
                                String imageUrl = data.getString("avatar");
                                String email = data.getString("email");
                                int id = data.getInt("id");

                                mContactList.add(new Contacts(imageUrl, emri, mbiemri,email,id));
                            }
                            mContactsAdapter = new ContactsAdapter(MainActivity.this, mContactList);
                            mRecyclerView.setAdapter(mContactsAdapter);
                            mContactsAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    private void parseContact(){}

    @Override
    public void onItemClick(int position) {
        Contacts clickedContact = mContactList.get(position);
        Intent detailIntent = new Intent(this,DetailActivity.class);

        detailIntent.putExtra(EXTRA_URL, clickedContact.getmImageUrl());
        detailIntent.putExtra(EXTRA_EMRI, clickedContact.getmEmri());
        detailIntent.putExtra(EXTRA_MBIEMRI, clickedContact.getmMbiemri());
        detailIntent.putExtra(EXTRA_EMAIL, clickedContact.getmEmail());
        detailIntent.putExtra(EXTRA_ID, clickedContact.getmID());

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_USER, mContactList);
        detailIntent.putExtras(bundle);

        startActivity(detailIntent);
    }

    public void getUsers(){
    }
}
