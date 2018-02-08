 package a3101020604.airline_projecta3;

 import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

 public class Login extends AppCompatActivity {


    DatabaseHelperClass myDb;
    EditText Username, Password;
    public Button LoginBtn;
    public Button RegisterBtn;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Username = (EditText) findViewById(R.id.Username2);
        Password = (EditText) findViewById(R.id.Password);
        LoginBtn = (Button) findViewById(R.id.loginBtn);
        RegisterBtn = (Button) findViewById(R.id.registerBtn);




        LoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myDb = new DatabaseHelperClass(Login.this);
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                String StoredPassword = myDb.getregister(username);
                if( Username.getText().toString().trim().equals("")) {


                    Username.setError( " required!" );

                }
                if( Password.getText().toString().trim().equals("")) {


                    Password.setError( "required!" );

                }

                if (password.equals(StoredPassword)) {

                    Toast.makeText(getApplicationContext()," Login Successfully", Toast.LENGTH_LONG).show();

                    Intent Intents = new Intent(Login.this, HomePage.class); // <----- START ACTIVITY
                    Intents.putExtra("Username", Username.getText().toString());
                    startActivity(Intents);
                    setContentView(R.layout.activity_home_page);

                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    Username.setText("");
                    Password.setText("");
                }



            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }


 }