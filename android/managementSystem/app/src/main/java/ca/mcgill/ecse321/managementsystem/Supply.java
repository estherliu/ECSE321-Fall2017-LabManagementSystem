package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;

public class Supply extends AppCompatActivity {

    EditText supplyType;
    EditText supplyQuantity;
    EditText supplyType2;

    Button listSupply;
    Button addSupply;
    Button removeSupply;

    aTask connection = new aTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);

        supplyType = (EditText)findViewById(R.id.supplyname);
        supplyQuantity = (EditText)findViewById(R.id.supplyquantity);
        supplyType2 = (EditText)findViewById(R.id.supplyname2);

        listSupply = (Button)findViewById(R.id.listsupply);
        addSupply = (Button)findViewById(R.id.addsupply);
        removeSupply = (Button)findViewById(R.id.removesupply);

        listSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listSupply();
            }
        });
        addSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSupply();
            }
        });
        removeSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeSupply();
            }
        });
    }

    void listSupply(){
        try{
            //fetch data
            String[] data = connection.exec("checksupply");
            connection.join();

            //format data
            Listing.Data = "Supply list: \n";
            for (int i = 0; i < data.length; i++) {
                if (i%2==0) Listing.Data+=data[i]+" : ";
                else        Listing.Data+=data[i]+"\n";
            }

            //display data
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch (Exception e){

        }
    }
    void addSupply(){
        try{
            //validate input
            if (supplyType.getText().toString().isEmpty()){
                supplyType.setError("please enter a supply name");
                supplyType.requestFocus();
                return;
            }
            if (supplyQuantity.getText().toString().isEmpty()){
                supplyQuantity.setError("please specify the quantity");
                supplyQuantity.requestFocus();
                return;
            }

            //execute command
            String[] data = connection.exec("addsupplies_"+supplyType.getText().toString()+"_"+supplyQuantity);
            connection.join();

            //check if success
            if (data[0].contains("true")){
                supplyType.setError(supplyQuantity.getText().toString()+" successfully added");
                supplyType.requestFocus();
                supplyType.setText("");
                supplyQuantity.setText("");
            }else{
                supplyType.setError(data[1]);
                supplyType.requestFocus();
            }
        }catch (Exception e){

        }

    }
    void removeSupply(){
        try{
            //validate input
            if (supplyType2.getText().toString().isEmpty()){
                supplyType2.setError("please specify a supply name");
                supplyType2.requestFocus();
                return;
            }

            //execute command
            String[] data = connection.exec("removesupply_"+supplyType2.getText().toString());
            connection.join();

            //check if success
            if (data[0].contains("true")){
                supplyType2.setError(supplyType2.getText().toString()+" successfully removed");
                supplyType2.setText("");
                supplyType2.requestFocus();
            }else{
                supplyType2.setError(data[1]);
                supplyType2.requestFocus();
            }

        }catch (Exception e){

        }
    }
}
