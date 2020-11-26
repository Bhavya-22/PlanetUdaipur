package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView attractions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attractions=(TextView) findViewById(R.id.attractions);
        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attractionIntent =new Intent(MainActivity.this,AttractionsActivity.class);
                startActivity(attractionIntent);
            }
        });
       // Find the View that shows the accommodation category
        TextView accommodation = (TextView) findViewById(R.id.accommodation);

// Set a click listener on that View
        accommodation.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the accommodation View is clicked on.
            @Override
            public void onClick(View view) {
                Intent accommodationIntent = new Intent(MainActivity.this, AccommodationActivity.class);
                startActivity(accommodationIntent);
            }
        });
        // Find the View that shows the restaurants category
        TextView restaurants = (TextView) findViewById(R.id.restaurants);

// Set a click listener on that View
        restaurants.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the restaurants View is clicked on.
            @Override
            public void onClick(View view) {
                Intent restaurantsIntent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(restaurantsIntent);
            }
        });
        // Find the View that shows the shopping places category
        TextView shopping = (TextView) findViewById(R.id.shopping_places);

// Set a click listener on that View
        shopping.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the shopping places View is clicked on.
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(MainActivity.this, ShoppingActivity.class);
                startActivity(shoppingIntent);
            }
        });
        // Find the View that shows the nearby places category
        TextView places = (TextView) findViewById(R.id.nearby_places);

// Set a click listener on that View
        places.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the shopping nearby places View is clicked on.
            @Override
            public void onClick(View view) {
                Intent placesIntent = new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(placesIntent);
            }
        });
    }

}

