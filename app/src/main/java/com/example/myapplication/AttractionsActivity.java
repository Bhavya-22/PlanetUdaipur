package com.example.myapplication;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AttractionsActivity extends AppCompatActivity {

    DatabaseReference mReference;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        context=this;
      //Action bar
        ActionBar actionBar=getSupportActionBar();
        //set Title
        actionBar.setTitle("Destinations");


        mRecyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        //mRecyclerView.setHasFixedSize(true);

        //set layout as linear layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send Query to firebase
        mFirebaseDatabase =FirebaseDatabase.getInstance();
        mReference=mFirebaseDatabase.getReference("Attractions");


    }

    //Search Data
    private void FirebaseSearch(String searchText)
    {
        String query=searchText.toLowerCase();

        Query firebaseSearchQuery=mReference.orderByChild("search").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(firebaseSearchQuery, Model.class)
                        .build();

        firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {

                        holder.setDetails(context,model.getTitle(),model.getImage(),model.getDescription(),model.getLocation());

                    }

                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.list_items, parent, false);
                        ViewHolder viewHolder=new ViewHolder(view);
                               /* viewHolder.setOnCLickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitle = view.findViewById(R.id.titlePost);
                                TextView mDesc=view.findViewById(R.id.descPost);
                                ImageView mImage = view.findViewById(R.id.imagePost);
                                //get data from views
                                String title=mTitle.getText().toString(); //Not necessary to match with firebase name
                                String desc = mDesc.getText().toString();
                                Drawable mDrawable = mImage.getDrawable();
                                Bitmap mBitmap=((BitmapDrawable)mDrawable).getBitmap();

                                //Pass this data to new activity
                                Intent intent=new Intent(view.getContext(),PostListActivtiy.class);
                                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[] bytes=stream.toByteArray();
                                intent.putExtra("image",bytes);  //put bitmap image as array of bytes
                                intent.putExtra("title",title); //put title
                                intent.putExtra("description",desc);  //put desc
                                startActivity(intent);
                            }

                            @Override
                            public void onItemlongClick(View view, int position) {
                                //TODO Can do our own implementation
                            }
                        });*/
                        return viewHolder;

                    }

                };
        firebaseRecyclerAdapter.startListening();
        //set adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    //load data on recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(mReference, Model.class)
                        .build();
        firebaseRecyclerAdapter=
              new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
                  @Override
                  protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {

                      holder.setDetails(context,model.getTitle(),model.getImage(),model.getDescription(),model.getLocation());
                  }

                  @NonNull
                  @Override
                  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                      View view = LayoutInflater.from(parent.getContext())
                              .inflate(R.layout.list_items, parent, false);
                      ViewHolder viewHolder=new ViewHolder(view);
                     /* viewHolder.setOnCLickListener(new ViewHolder.ClickListener() {
                          @Override
                          public void onItemClick(View view, int position) {
                              //Views
                              TextView mTitle = view.findViewById(R.id.titlePost);
                              TextView mDesc=view.findViewById(R.id.descPost);
                              ImageView mImage = view.findViewById(R.id.imagePost);
                              //get data from views
                              String title=mTitle.getText().toString(); //Not necessary to match with firebase name
                              String desc = mDesc.getText().toString();
                              Drawable mDrawable = mImage.getDrawable();
                              Bitmap mBitmap=((BitmapDrawable)mDrawable).getBitmap();

                              //Pass this data to new activity
                              Intent intent=new Intent(view.getContext(),PostListActivtiy.class);
                              ByteArrayOutputStream stream=new ByteArrayOutputStream();
                              mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                              byte[] bytes=stream.toByteArray();
                              intent.putExtra("image",bytes);  //put bitmap image as array of bytes
                              intent.putExtra("title",title); //put title
                              intent.putExtra("description",desc);  //put desc.
                              startActivity(intent);
                          }

                          @Override
                          public void onItemlongClick(View view, int position) {
                              //TODO Can do our own implementation
                          }
                      }); */
                      return viewHolder;

                  }

              };
        firebaseRecyclerAdapter.startListening();
      //set adapter to recycler view
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu;this adds item to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FirebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as we type
                FirebaseSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        //handle other action bar item clicks here
        if(id==R.id.action_settings)
        {
            //TODO
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    // Now we will handle item clicks-through which we can open a new activity and show the details(title,img,desc) on click of card view


}