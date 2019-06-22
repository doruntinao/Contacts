package com.doruntinaosaj.contacts;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.doruntinaosaj.contacts.MainActivity.EXTRA_EMAIL;
import static com.doruntinaosaj.contacts.MainActivity.EXTRA_EMRI;
import static com.doruntinaosaj.contacts.MainActivity.EXTRA_ID;
import static com.doruntinaosaj.contacts.MainActivity.EXTRA_URL;
import static com.doruntinaosaj.contacts.MainActivity.EXTRA_MBIEMRI;
import static com.doruntinaosaj.contacts.MainActivity.EXTRA_USER;

public class DetailActivity extends AppCompatActivity {

    Button next;
    Button prev;
    int id;

    String imageUrl;
    String emri;
    String mbiemri;
    String email;

    ImageView imageView;
    TextView textViewEmriMbiemri;
    TextView textViewEmail;
    TextView textViewId;

    private ArrayList<Contacts> myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundleObject = getIntent().getExtras();

        myList = (ArrayList<Contacts>) getIntent().getSerializableExtra(EXTRA_USER);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra(EXTRA_URL);
        emri = intent.getStringExtra(EXTRA_EMRI);
        mbiemri = intent.getStringExtra(EXTRA_MBIEMRI);
        email = intent.getStringExtra(EXTRA_EMAIL);
        id = intent.getIntExtra(EXTRA_ID,0);

        imageView = findViewById(R.id.image_view_detail);
        textViewEmriMbiemri = findViewById(R.id.emriMbiemri_view_detail);
        textViewEmail = findViewById(R.id.email_view_detail);
        textViewId = findViewById(R.id.id_view_detail);

        next= (Button)findViewById(R.id.next_button);
        prev= (Button)findViewById(R.id.prev_button);


        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewEmriMbiemri.setText(emri+" "+mbiemri);
        textViewEmail.setText(email);
        textViewId.setText("ID: "+id);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id<myList.size()){
                    prevNextClick(id);
                }
                else{
                    id = id-1;
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id-2;
                if(id >= 0 && id<myList.size()){
                    prevNextClick(id);
                }
                else{
                    id = 1;
                }
            }
        });

    }
        public void prevNextClick(int item) {
            Contacts c = myList.get(item);
            imageUrl = c.getmImageUrl();
            emri = c.getmEmri();
            mbiemri = c.getmMbiemri();
            email = c.getmEmail();
            id = c.getmID();

    Picasso.with(DetailActivity.this).load(imageUrl).fit().centerInside().into(imageView);
    textViewEmriMbiemri.setText(emri + " " + mbiemri);
    textViewEmail.setText(email);
    textViewId.setText("ID: " + id);
}
}
