package ca.mcgill.ecse321.managementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Staff extends AppCompatActivity {

    EditText name;
    EditText role;
    EditText staffRelated;
    EditText progress;
    EditText staffRelated2;
    EditText staffRelated3;

    Button listStaff;
    Button addStaff;
    Button addProgress;
    Button removeStaff;
    Button listProgress;

    aTask connection = new aTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        name = (EditText)findViewById(R.id.staffName);
        role = (EditText)findViewById(R.id.staffRole);
        staffRelated = (EditText)findViewById(R.id.staffrelated);
        progress = (EditText)findViewById(R.id.progressdescription);
        staffRelated2 = (EditText)findViewById(R.id.staffrelated2);
        staffRelated3 = (EditText)findViewById(R.id.staffrelated3);

        listStaff = (Button)findViewById(R.id.liststaff);
        addStaff = (Button)findViewById(R.id.addStaff);
        addProgress = (Button)findViewById(R.id.addprogress);
        removeStaff = (Button)findViewById(R.id.removestaff);
        listProgress = (Button)findViewById(R.id.listprogress);

        listStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listStaff();
            }
        });
        addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStaff();
            }
        });
        addProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProgress();
            }
        });
        removeStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStaff();
            }
        });
        listProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProgress();
            }
        });


    }

    void listProgress(){
        try{
            //validate input
            if (staffRelated2.getText().toString().isEmpty()){
                staffRelated2.setError("please enter a staff name");
                staffRelated2.requestFocus();
                return;
            }

            //fetch data
            String[] data = connection.exec("checkprogress_"+staffRelated2.getText().toString());
            connection.join();

            //check for error
            if (data[0].contains("false")){
                staffRelated2.setError(data[1]);
                staffRelated2.requestFocus();
                return;
            }

            //format data
            Listing.Data = "Progress of "+staffRelated2.getText().toString()+"\n";
            for (int i = 0; i < data.length; i++) {
                Listing.Data+=data[i];
            }

            //display data
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);
        }catch (Exception e){

        }
    }

    void listStaff(){
        try{
            //fetch data
            String[] data = connection.exec("checkstaffs");
            connection.join();

            //check for error
            if (data[0].contains("false")){
                name.setError(data[1]);
                name.requestFocus();
                return;
            }

            //format data
            Listing.Data = "Staff list: \n";

            for (int i = 0; i < data.length; i++) {
                if (i%2==0){
                    Listing.Data+=data[i]+" : ";
                }else{
                    Listing.Data+=data[i]+"\n";
                }
            }

            //display data
            Intent newframe =  new Intent(getApplicationContext(),Listing.class);
            startActivity(newframe);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void addStaff(){
        try{
            //validate inputs
            if(name.getText().length()<1){
                name.setError("please enter a name");
                name.requestFocus();
                return;
            }
            if (role.getText().length()<1){
                role.setError("please enter a role");
                role.requestFocus();
                return;
            }

            //process command
            String[] success = connection.exec("addstaff_"+name.getText().toString()+"_"+role.getText().toString());
            connection.join();

            //display success or error
            if (success[0].contains("true")){
                name.setError(name.getText().toString()+" succefully added!");
                name.requestFocus();
                name.setText("");
                role.setText("");
            }else{
                name.setError(success[1]);
                name.requestFocus();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void addProgress(){
        try{
            //validate inputs
            if(progress.getText().length()<1){
                progress.setError("please enter a progress");
                progress.requestFocus();
                return;
            }
            if (staffRelated.getText().length()<1){
                staffRelated.setError("please enter a staff name");
                staffRelated.requestFocus();
                return;
            }

            //process command
            String[] success = connection.exec("addprogress_"+progress.getText().toString()+"_"+staffRelated.getText().toString());
            connection.join();

            //display success or error
            if (success[0].contains("true")){
                staffRelated.setError("progress successfully added");
                staffRelated.requestFocus();
                staffRelated.setText("");
                progress.setText("");
            }else {
                staffRelated.setError(success[1]);
                staffRelated.requestFocus();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void removeStaff(){
        try{
            //validate input
            if (staffRelated3.getText().toString().isEmpty()){
                staffRelated3.setError("please enter a staff name");
                staffRelated3.requestFocus();
                return;
            }

            //execute
            String[] success = connection.exec("removestaff_"+staffRelated3.getText().toString());
            connection.join();

            //check for error
            if (success[0].contains("true")){
                staffRelated3.setError(staffRelated.getText().toString()+" successfully removed");
                staffRelated3.requestFocus();
                staffRelated3.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
