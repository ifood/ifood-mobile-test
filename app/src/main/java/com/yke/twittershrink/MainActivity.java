package com.yke.twittershrink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yke.twittershrink.Search.SearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Buttons / Text:
        final Button searchButton = findViewById(R.id.button);
        final EditText editText = findViewById(R.id.editSearch);
        final ImageView searchIcon = findViewById(R.id.imageView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchButton.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
                searchIcon.setVisibility(View.VISIBLE);
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("User", text);
                if (!text.isEmpty()) {
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Search Input",
                                    Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
