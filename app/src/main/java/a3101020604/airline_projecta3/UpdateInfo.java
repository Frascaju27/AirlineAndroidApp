package a3101020604.airline_projecta3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateInfo extends AppCompatActivity {


    DatabaseHelperClass myDb;
    EditText Password, FullName, PhoneNum, Address;
    public Button UpdateUser;
    public Button button;
    TextView Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        myDb = new DatabaseHelperClass(this);
        Username = (TextView) findViewById(R.id.Username2);
        Password = (EditText) findViewById(R.id.Password);
        FullName = (EditText) findViewById(R.id.FullName);
        PhoneNum = (EditText) findViewById(R.id.PhoneNum);
        Address = (EditText) findViewById(R.id.Address);
        UpdateUser = (Button) findViewById(R.id.UpdateUser);
        button = (Button) findViewById(R.id.button);

        TextView nameView= (TextView) findViewById(R.id.Username2);
        nameView.setText(getIntent().getExtras().getString("Username"));

        viewAll();
        UpdateData();


    }

    public void UpdateData() {







        UpdateUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Password.length() == 0) {
                            Password.requestFocus();
                            Password.setError("required!");


                        }

                        if (FullName.length() == 0) {
                            FullName.requestFocus();
                            FullName.setError(" required!");


                        }
                        if (PhoneNum.length() == 0) {
                            PhoneNum.requestFocus();
                            PhoneNum.setError(" required!");


                        }
                        if (Address.length() == 0) {
                            Address.requestFocus();
                            Address.setError(" required!");

                        }
                        else {
                            boolean isUpdate = myDb.updateData(Username.getText().toString(),
                                    Password.getText().toString(), PhoneNum.getText().toString(),
                                    FullName.getText().toString(), Address.getText().toString());
                            Toast.makeText(UpdateInfo.this, "Profile Updated!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }



    public void viewAll() {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("" + res.getString(4) + "\n\n");
                            buffer.append("" + res.getString(1) + "\n\n");
                            buffer.append("" + res.getString(3) + "\n\n");
                            buffer.append("" + res.getString(5) + "\n\n");

                        }

                        // Show all data
                        showMessage("Your Profile", buffer.toString());
                    }
                }
        );
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
            Intent Intents = new Intent(UpdateInfo.this, HomePage.class); // <----- START ACTIVITY
            Intents.putExtra("Username", Username.getText().toString());
            startActivity(Intents);
            setContentView(R.layout.activity_home_page);
        }
        return false;
    }


}
