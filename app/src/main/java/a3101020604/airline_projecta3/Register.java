package a3101020604.airline_projecta3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelperClass myDb;
    EditText Username, Password, FullName, PhoneNum, Address;
    public Button LoginPage;
    public Button registerBtn;


    public void init() {

        LoginPage = (Button) findViewById(R.id.loginPage);
        LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2 = new Intent(Register.this, Login.class);
                startActivity(toy2);
                setContentView(R.layout.activity_login);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        myDb = new DatabaseHelperClass(this);
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        FullName = (EditText) findViewById(R.id.fullName);
        PhoneNum = (EditText) findViewById(R.id.PhoneNum);
        Address = (EditText) findViewById(R.id.Address);
        LoginPage = (Button) findViewById(R.id.loginPage);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        AddData();
    }

    public void AddData() {
        registerBtn.setOnClickListener(
                new View.OnClickListener() {

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

                    @Override
                    public void onClick(View v) {
                        if (Username.length()==0 ) {
                            Username.requestFocus();
                            Username.setError("Required");


                        }
                        if (Username.length()==0) {
                            Username.requestFocus();
                            Password.setError("Required!");
                        }


                        if (Password.length()==0) {

                            Password.requestFocus();
                            Password.setError("Required!");
                        }

                        if (FullName.length()==0) {
                            FullName.requestFocus();
                            FullName.setError(" Required!");


                        }
                        if (PhoneNum.length()==0) {
                            PhoneNum.requestFocus();
                            PhoneNum.setError("Required!");


                        }
                        if (Address.length()==0) {
                            Address.requestFocus();
                            Address.setError("Required!");

                        }

                        else {
                            boolean isInserted =
                                    myDb.insertData(Username.getText().toString(),
                                            Password.getText().toString(),
                                            FullName.getText().toString(),
                                            Address.getText().toString(),
                                            PhoneNum.getText().toString());
                            Toast.makeText(Register.this,"Registered",Toast.LENGTH_LONG).show();

                        }

                    }

                }
        );
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent Intents = new Intent(Register.this, Login.class); // <----- START ACTIVITY
            startActivity(Intents);
            setContentView(R.layout.activity_login);
        }
        return false;
    }
}

