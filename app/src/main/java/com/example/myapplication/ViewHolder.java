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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewHolder extends RecyclerView.ViewHolder {
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

    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        mView=itemView;
        ArrayList<Model> modelArrayList =new ArrayList<>();
        layout=mView.findViewById(R.id.parent_layout);
         mTitleView = (TextView) mView.findViewById(R.id.image_name);
         //mDescView = (TextView) mView.findViewById(R.id.descPost);
        mImageView = (ImageView) mView.findViewById(R.id.image);


        //item click
        /*temView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.onItemClick(v,getAdapterPosition());
                TextView mTitle = v.findViewById(R.id.titlePost);
                TextView mDesc=v.findViewById(R.id.descPost);
                ImageView mImage = v.findViewById(R.id.imagePost);
                //get data from views
                String title=mTitle.getText().toString(); //Not necessary to match with firebase name
                String desc = mDesc.getText().toString();
                Drawable mDrawable = mImage.getDrawable();
                Bitmap mBitmap=((BitmapDrawable)mDrawable).getBitmap();

                //Pass this data to new activity
                int position =getAdapterPosition();
                Intent intent=new Intent(v.getContext(),PostListActivtiy.class);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] bytes=stream.toByteArray();
                intent.putExtra("image",bytes);  //put bitmap image as array of bytes
                intent.putExtra("title",title); //put title
                intent.putExtra("description",desc);  //put desc
                v.getContext().startActivity(intent);
            }
        });

        //Item long click
        /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemlongClick(v,getAdapterPosition());
                return true;
            }
        }); */
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
                Intent i=new Intent(v.getContext(), DetailsActivity.class);
                i.putExtra("title",title);
                i.putExtra("description",desc);
                i.putExtra("image",imageUrl);
                i.putExtra("location",loc);
                v.getContext().startActivity(i);
            }
        });
    }

    //set  details to rec. view row
    public void setDetails(Context ctx,String title,String image,String description,String location)
    {
        imageUrl =image;
        Log.d("Tag",imageUrl);
        //set data to views
        mTitleView.setText(title);
        //mDescView.setText(description);
        desc=description;
        loc=location;
        Picasso.get().load(image).into(mImageView);
    }

    //private  ViewHolder.ClickListener mClickListener;

    //Interface to send callbacks
    /*public interface ClickListener
    {
        void onItemClick(View view,int position);
        void onItemlongClick(View view,int position);
    }*/
    /*public void setOnCLickListener(ViewHolder.ClickListener cLickListener)
    {
        mClickListener=cLickListener;

    }*/

}

