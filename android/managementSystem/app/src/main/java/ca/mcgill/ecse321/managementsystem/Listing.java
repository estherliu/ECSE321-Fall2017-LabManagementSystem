package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * A class that simply display data
 */
public class Listing extends AppCompatActivity {

    public static String Data = "";
    public EditText textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        textView = (EditText) findViewById(R.id.listing);
        textView.setText(Data);

    }
}
