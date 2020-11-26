package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    TextView mTitleDet,mDescDet;
    ImageView  mImageDet;
    ImageButton mapButton;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        //Action Bar
        ActionBar actionBar=getSupportActionBar();
        //Action Bar title
        actionBar.setTitle("DestinationDetails");
        //setting the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        mTitleDet=(TextView)findViewById(R.id.titlePostDet);
        mDescDet=(TextView)findViewById(R.id.descPostDet);
        mImageDet=(ImageView)findViewById(R.id.imagePostDet);
        mapButton=(ImageButton)findViewById(R.id.button);

        //get data from intent
        String image=getIntent().getStringExtra("image");
        //byte[] bytes=getIntent().getByteArrayExtra("image");
         title=getIntent().getStringExtra("title");
       String desc=getIntent().getStringExtra("description");
        //Bitmap bmp=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        final String map= getIntent().getStringExtra("location");
        //set data to views
        mTitleDet.setText(title);
        mDescDet.setText(desc);
        //mImageDet.setImageURI(Uri.parse(image));
        Picasso.get().load(image).into(mImageDet);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(map);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }
    //Handle onBackPressed (return to previous activity)

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

