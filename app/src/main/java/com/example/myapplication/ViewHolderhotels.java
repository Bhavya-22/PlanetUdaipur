package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewHolderhotels extends RecyclerView.ViewHolder{
    View mView;
    RelativeLayout layout;
    TextView mTitleView;
    TextView mDescView;
    ImageView mImageView;
    Drawable mDrawable;
    Bitmap mBitmap;
    String imageUrl;
    String desc;
    String loc;
    String urlsite;


    public ViewHolderhotels(@NonNull View itemView) {
        super(itemView);

        mView=itemView;
        ArrayList<Model> modelArrayList =new ArrayList<>();
        layout=mView.findViewById(R.id.parent_layout);
        mTitleView = (TextView) mView.findViewById(R.id.image_name);
        //mDescView = (TextView) mView.findViewById(R.id.descPost);
        mImageView = (ImageView) mView.findViewById(R.id.image);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title= mTitleView.getText().toString().trim();
                //String desc=mDescView.getText().toString();
                mDrawable = mImageView.getDrawable();
                /*mBitmap=((BitmapDrawable)mDrawable).getBitmap();
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytes=stream.toByteArray(); */
                Log.d("tag","title is " + title);
                Intent i=new Intent(v.getContext(), HotelsActivity.class);
                i.putExtra("title",title);
                i.putExtra("description",desc);
                i.putExtra("image",imageUrl);
                i.putExtra("location",loc);
                i.putExtra("url",urlsite);
                v.getContext().startActivity(i);
            }
        });
    }

    //set  details to rec. view row
    public void sethotels(Context ctx, String title, String image, String description, String location,String url)
    {
        imageUrl =image;
        Log.d("Tag",imageUrl);
        //set data to views
        mTitleView.setText(title);
        //mDescView.setText(description);
        desc=description;
        loc=location;
        urlsite=url;
        Picasso.get().load(image).into(mImageView);
    }
}
