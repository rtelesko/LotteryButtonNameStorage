package com.example.lotterybuttonnamestorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Constants and object for Shared Preferences
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    // GUI controls
    private EditText etName;
    private Button btSave;
    private Button btRetrieve;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get references to the EditText, two Buttons and TextView
        etName = findViewById(R.id.etName);
        btSave = findViewById(R.id.btSave);
        btRetrieve = findViewById(R.id.btRetrieve);
        tvName = findViewById(R.id.tvName);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear old entry
                tvName.setText("");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (TextUtils.isEmpty(etName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_SHORT).show();
                else {
                    editor.putString(Name, etName.getText().toString());
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Name is saved!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = sharedpreferences.getString(Name, null);
                if (name != null)
                    tvName.setText(name);
                else
                    Toast.makeText(MainActivity.this, "Nothing to retrieve!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
