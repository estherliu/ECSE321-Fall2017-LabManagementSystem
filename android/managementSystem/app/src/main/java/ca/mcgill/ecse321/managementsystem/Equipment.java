package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Equipment extends AppCompatActivity {

    EditText addEquipmentType;
    EditText addEquipmentQuantity;
    EditText removeEquipmentType;

    Button addEquipmentButton;
    Button listEquipmentButton;
    Button removeEquipmentButton;

    public aTask connection = new aTask();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        addEquipmentType = (EditText)findViewById(R.id.equipmentname);
        addEquipmentQuantity = (EditText)findViewById(R.id.equipmentquantity);
        removeEquipmentType = (EditText)findViewById(R.id.equipmentname2);

        addEquipmentButton = (Button)findViewById(R.id.addequipment);
        listEquipmentButton = (Button)findViewById(R.id.listequipment);
        removeEquipmentButton = (Button)findViewById(R.id.removeequipment);

        addEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEquipment();
            }
        });
        listEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listEquipment();
            }
        });
        removeEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeEquipment();
            }
        });
    }

    void addEquipment() {
        try{
            //validate input
            if (addEquipmentType.getText().toString().isEmpty()){
                addEquipmentType.setError("please input an equipment type");
                addEquipmentType.requestFocus();
                return;
            }
            if (addEquipmentQuantity.getText().toString().isEmpty()){
                addEquipmentQuantity.setError("please enter a number");
                addEquipmentQuantity.requestFocus();
                return;
            }

            //process command
            String[] back =connection.exec("addequipments_"+addEquipmentType.getText().toString()+"_"+addEquipmentQuantity.getText().toString());
            connection.join();
            if (back[0].contains("true")){
                addEquipmentType.setError("Adding Success");
                addEquipmentType.requestFocus();
                addEquipmentType.setText("");
                addEquipmentQuantity.setText("");
            }else{
                addEquipmentType.setError(back[1]);
                addEquipmentType.requestFocus();
            }
        }catch (Exception e){

        }
    }

    void listEquipment(){
        try{

            //fetch data
            String[] response= connection.exec("checkequipment");
            connection.join();
            Listing.Data = "Equipment List:\n";

            //check for error
            if (response[0].contains("false")){
                addEquipmentType.setError(response[1]);
                addEquipmentType.requestFocus();
                return;
            }

            //process data
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" : ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }

            //switch to listing page
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch (Exception e){}
    }

    void removeEquipment(){
        try {
            //validate input
            if (removeEquipmentType.getText().toString().isEmpty()){
                removeEquipmentType.setError("please specify an equipment type");
                removeEquipmentType.requestFocus();
                return;
            }

            //process command
            String[] back = connection.exec("removeequipment_"+removeEquipmentType.getText().toString());
            connection.join();
            if (back[0].contains("true")){
                removeEquipmentType.setError(removeEquipmentType.getText().toString()+" is removed.");
                removeEquipmentType.requestFocus();
                removeEquipmentType.setText("");
            }else{
                removeEquipmentType.setError(back[1]);
                removeEquipmentType.requestFocus();
            }
        }catch (Exception e){}
    }
}
