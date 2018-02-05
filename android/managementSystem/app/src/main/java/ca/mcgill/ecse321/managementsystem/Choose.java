package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Choose extends AppCompatActivity {

    //global variables
    TextView textView;
    Button staffButton;
    Button equipmentButton;
    Button supplyButton;
    Button expenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        textView = (TextView)findViewById(R.id.textView2);
        staffButton = (Button)findViewById(R.id.staff);
        equipmentButton = (Button)findViewById(R.id.equipment);
        supplyButton = (Button)findViewById(R.id.supply);
        expenseButton = (Button)findViewById(R.id.expense);

        staffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToStaff();
            }
        });
        equipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToEquipment();
            }
        });
        supplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToSupply();
            }
        });
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToExpense();
            }
        });

    }

    /**
     * switch view to staff page
     */
    void ToStaff(){
        Intent newframe =  new Intent(getApplicationContext(),Staff.class);
        startActivity(newframe);
    }
    /**
     * switch view to equipment page
     */
    void ToEquipment(){
        Intent newframe =  new Intent(getApplicationContext(),Equipment.class);
        startActivity(newframe);
    }
    /**
     * switch view to supply page
     */
    void ToSupply(){
        Intent newframe =  new Intent(getApplicationContext(),Supply.class);
        startActivity(newframe);
    }
    /**
     * switch view to expense page
     */
    void ToExpense(){
        Intent newframe =  new Intent(getApplicationContext(),Expense.class);
        startActivity(newframe);
    }
}
