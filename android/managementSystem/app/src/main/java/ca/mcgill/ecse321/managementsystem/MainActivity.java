package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import ca.mcgill.ecse321.tcpclient.TCPclient;

public class MainActivity extends AppCompatActivity {

    public EditText StaffName;
    public EditText StaffRole;
    public EditText SupplyType;
    public EditText SupplyQuantity;
    public EditText EquipmentType;
    public EditText EquipmentQuantity;
    public EditText ExpenseReason;
    public EditText ExpenseAmount;
    public EditText ProgressDescription;
    public EditText ProgressRelatedStaff;

    public Button AddStaff;
    public Button ListStaff;
    public Button AddSupply;
    public Button ListSupply;
    public Button AddEquipment;
    public Button ListEquipment;
    public Button AddExpense;
    public Button ListExpense;
    public Button Addprogress;
    public Button ListProgress;

    public ProgressBar progressBar;

    //networking thread
    public task connection = new task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.main_progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        //get textfields
        StaffName = (EditText)findViewById(R.id.StaffName);
        StaffRole = (EditText)findViewById(R.id.StaffRole);
        SupplyType = (EditText)findViewById(R.id.supplytype);
        SupplyQuantity = (EditText)findViewById(R.id.supplyquantity);
        EquipmentType = (EditText)findViewById(R.id.equipmentname);
        EquipmentQuantity = (EditText)findViewById(R.id.equipmentquantity);
        ExpenseReason = (EditText)findViewById(R.id.expensereason);
        ExpenseAmount = (EditText)findViewById(R.id.expenseamount);
        ProgressDescription = (EditText)findViewById(R.id.progressdescription);
        ProgressRelatedStaff = (EditText)findViewById(R.id.staffrelated);

        //get buttons
        AddStaff = (Button)findViewById(R.id.AddStaff);
        ListStaff = (Button)findViewById(R.id.liststaff);
        AddSupply = (Button)findViewById(R.id.addsupply);
        ListSupply = (Button)findViewById(R.id.listsupply);
        AddEquipment = (Button)findViewById(R.id.addequipment);
        ListEquipment = (Button)findViewById(R.id.listequipment);
        AddExpense = (Button)findViewById(R.id.addexpense);
        ListExpense = (Button)findViewById(R.id.listexpense);
        Addprogress = (Button)findViewById(R.id.addprogress);
        ListProgress = (Button)findViewById(R.id.listprogress);

        //add listeners
        AddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStaff();
            }
        });
        ListStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListStaff();
            }
        });
        AddSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSupply();
            }
        });
        ListSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListSupply();
            }
        });
        AddEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEquipment();
            }
        });
        ListEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListEquipment();
            }
        });
        AddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpense();
            }
        });
        ListExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListExpense();
            }
        });
        Addprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProgress();
            }
        });
        ListProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListProgress();
            }
        });




    }

    //adders
    public void AddStaff(){
        try{
            String cmd = "addstaff_";
            cmd+=StaffName.getText().toString()+"_"+StaffRole.getText().toString();
            String[] back = connection.exec(cmd);
            if (back[0].equals("true")){
                StaffName.setError("adding success");
                StaffName.requestFocus();
            }
            else{
                StaffName.setError(back[0]);
                StaffName.requestFocus();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void AddSupply(){
        try{
            String cmd = "addsupplies_";
            cmd+=SupplyType.getText().toString()+"_"+SupplyQuantity.getText().toString();
            String[] back = connection.exec(cmd);
            if (back[0].equals("true")){
                SupplyType.setError("adding success");
                SupplyType.requestFocus();
            }
            else{
                SupplyQuantity.setError(back[0]);
                SupplyQuantity.requestFocus();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AddEquipment(){
        try{
            String cmd = "addequipments_";
            cmd += EquipmentType.getText().toString()+"_"+EquipmentQuantity.getText().toString();
            String[] back = connection.exec(cmd);
            if (back[0].equals("true")){
                EquipmentType.setError("adding success");
                EquipmentType.requestFocus();
            }
            else{
                EquipmentQuantity.setError(back[0]);
                EquipmentQuantity.requestFocus();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AddExpense(){
        try{
            String cmd = "addexpense";
            cmd+=ExpenseReason.getText().toString()+"_"+ExpenseAmount.getText().toString();
            String[] back = connection.exec(cmd);
            if (back[0].equals("true")){
                ExpenseReason.setError("adding success");
                ExpenseReason.requestFocus();
            }
            else{
                ExpenseAmount.setError(back[0]);
                ExpenseAmount.requestFocus();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void AddProgress(){
        try{
            String cmd = "addprogress_";
            cmd+=ProgressDescription.getText().toString()+"_"+ProgressRelatedStaff.getText().toString();
            String[] back = connection.exec(cmd);
            if (back[0].equals("true")){
                ProgressDescription.setError("adding success");
                ProgressDescription.requestFocus();
            }
            else{
                ProgressDescription.setError(back[0]);
                ProgressDescription.requestFocus();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //listers
    public void ListStaff(){
        try{
            String[] response = connection.exec("checkstaffs");
            Listing.Data = "Staff List\n";
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }

            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ListSupply(){
        try{
            String[] response = connection.exec("checksupplies");
            Listing.Data = "Supply List\n";
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ListEquipment(){
        try{
            String[] response = connection.exec("checkequipment");

            Listing.Data = "Equipment List\n";
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ListExpense(){
        try{

            String[] response = connection.exec("checkexpense");
            String[] sum = connection.exec("getbalance");
            Listing.Data = "Expense List\n";
            Listing.Data+= "Current balance : "+sum[0]+"\n";
            for (int i = 1; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ListProgress(){
        try{
            String[] response = connection.exec("checkprogress");
            Listing.Data = "Progress List\n";
            for (int i = 0; i < response.length; i++) {
                if (i%2==0){
                    Listing.Data+=response[i]+" ";
                }
                else{
                    Listing.Data+=response[i]+"\n";
                }
            }
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    class task extends Thread{

        String cmd;
        String[] response;

        public task(){
        }

        protected String[] exec(String command){
            try{
                progressBar.setVisibility(View.VISIBLE);
                response = TCPclient.exec(command);
                progressBar.setVisibility(View.INVISIBLE);
            }catch (Exception e){
                e.printStackTrace();
                response = null;
            }

            return response;
        }

    }

}
