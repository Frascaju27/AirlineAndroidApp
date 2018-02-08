package a3101020604.airline_projecta3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelperClass myDb;
    private RadioGroup HomeRadio;
    private RadioButton UpdateCustomerInfo;
    private RadioButton TravelPlans;
    private RadioButton ViewFlights;
    TextView Name_User;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        myDb = new DatabaseHelperClass(this);
        TextView nameView = (TextView) findViewById(R.id.Name_User);
        nameView.setText(getIntent().getExtras().getString("Username"));
        HomeRadio = (RadioGroup) findViewById(R.id.HomeRadio);
        UpdateCustomerInfo = (RadioButton) findViewById(R.id.UpdateUserInfo);
        TravelPlans = (RadioButton) findViewById(R.id.TravelPlans);
        ViewFlights = (RadioButton) findViewById(R.id.ViewProfile);
        Name_User = (TextView) findViewById(R.id.Name_User);
        final TextView et = (TextView) findViewById(R.id.Name_User);




        UpdateCustomerInfo.setOnClickListener(this);
        TravelPlans.setOnClickListener(this);
        ViewFlights.setOnClickListener(this);


    }

    public void onClick(View v){

        int checkedRadioButtonId = HomeRadio.getCheckedRadioButtonId();

        switch (checkedRadioButtonId){
            case R.id.UpdateUserInfo:
                if (UpdateCustomerInfo.isChecked()){
                    Intent Intents = new Intent(HomePage.this, UpdateInfo.class); // <----- START ACTIVITY
                    Intents.putExtra("Username", Name_User.getText().toString());
                    startActivity(Intents);
                    setContentView(R.layout.activity_home_page);

                }
                break;

            case R.id.TravelPlans:
                if (TravelPlans.isChecked()) {
                    Intent Intents = new Intent(HomePage.this, TravelPlan.class); // <----- START ACTIVITY
                    Intents.putExtra("Username", Name_User.getText().toString());
                    startActivity(Intents);
                    setContentView(R.layout.activity_travel_plan);

                }
                break;

            case R.id.ViewProfile:
                if (ViewFlights.isChecked()){
                    Cursor res = myDb.getAllData();
                    if (res.getCount() == 0) {
                        // show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Date Of Travel:   " + res.getString(9) + "\n\n");
                        buffer.append("Username: " + res.getString(1) + "\n\n");
                        buffer.append("Full Name: " + res.getString(4) + "\n\n");
                        buffer.append("Address: " + res.getString(5) + "\n\n");
                        buffer.append("Phone Number: " + res.getString(3) + "\n\n");
                        buffer.append("Depart From: " + res.getString(6) + "\n\n");
                        buffer.append("Destination: " + res.getString(7) + "\n\n");
                        buffer.append("Company and Cost:  " + res.getString(8) + "\n\n");

                    }

                    // Show all data
                    showMessage("Your Flight Receipt\n", buffer.toString());
                }
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInteface, int i) {
                dialogInteface.dismiss();
            }
        });
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            Intent Intents = new Intent(HomePage.this, Login.class); // <----- START ACTIVITY
            startActivity(Intents);
            setContentView(R.layout.activity_login);
        }
        return false;
    }

}
