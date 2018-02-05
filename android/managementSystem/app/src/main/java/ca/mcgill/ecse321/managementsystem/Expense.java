package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Expense extends AppCompatActivity {

    EditText reason;
    EditText amount;
    TextView balance;

    Button listExpense;
    Button addExpense;

    aTask connection = new aTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        reason = (EditText)findViewById(R.id.expensereason);
        amount = (EditText)findViewById(R.id.expenseamount);
        balance = (TextView)findViewById(R.id.textView3);

        listExpense = (Button)findViewById(R.id.listexpense);
        addExpense = (Button)findViewById(R.id.addexpense);

        listExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listExpense();;
            }
        });
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExpense();
            }
        });

        try{
            String[] budget = connection.exec("getbalance");
            connection.join();
            balance.setText("Current balance:\n"+budget[0]);

        }catch (Exception e){

        }



    }
    void listExpense() {
        try{
            //fetch data
            String[] response= connection.exec("checkexpenses");
            connection.join();
            String[] total = connection.exec("getbalance");
            connection.join();

            //format data
            Listing.Data = "Current balance: "+total[0]+"\n";
            Listing.Data += "Past expenses: \n";
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" : ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }

            //display data
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);

        }catch (Exception e){

        }
    }

    void addExpense(){
        try{
            //validate input
            if (reason.getText().toString().isEmpty()){
                reason.setError("please specify a reason");
                reason.requestFocus();
                return;
            }
            if (amount.getText().toString().isEmpty()){
                amount.setError("please enter an amount");
                amount.requestFocus();
                return;
            }

            //process command
            String[] back =connection.exec("addexpense_"+reason.getText().toString()+"_"+amount.getText().toString());
            connection.join();

            //check for error
            if (Boolean.parseBoolean(back[0])){
                reason.setError("Adding Success");
                reason.requestFocus();
                reason.setText("");
                amount.setText("");
            }else{
                reason.setError(back[0]);
                reason.requestFocus();
            }

            //update current balance
            String[] budget = connection.exec("getbalance");
            connection.join();
            balance.setText("Current balance:\n"+budget[0]);

        }catch (Exception e){

        }
    }

}
