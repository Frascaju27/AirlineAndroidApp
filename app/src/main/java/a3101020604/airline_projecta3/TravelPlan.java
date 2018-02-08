package a3101020604.airline_projecta3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class TravelPlan extends AppCompatActivity {


    DatabaseHelperClass myDb;
    EditText Depart, Destionation;
    public Button dateResults;
    public Button Find;
    public Button SubmitData;
    TextView mItemSelected;
    String[] listItems;
    TextView Username;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    boolean[] checkedItems;

    private static final String TAG = "TravelPlan";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_plan);
        myDb = new DatabaseHelperClass(this);
        Username = (TextView) findViewById(R.id.username2);
        Depart = (EditText) findViewById(R.id.Depart);
        Destionation = (EditText) findViewById(R.id.Destionation);
        dateResults = (Button) findViewById(R.id.dateResults);
        Find = (Button) findViewById(R.id.Find);
        SubmitData = (Button) findViewById(R.id.SubmitData);
        mItemSelected = (TextView) findViewById(R.id.mItemSelected2);// defining text view
        listItems = getResources().getStringArray(R.array.Travel);
        checkedItems = new boolean[listItems.length];
        mDisplayDate = (TextView) findViewById(R.id.mItemSelected);




        UpdateData2();

        TextView nameView = (TextView) findViewById(R.id.username2);
        nameView.setText(getIntent().getExtras().getString("Username"));



        dateResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TravelPlan.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }

    public void UpdateData2() {
        SubmitData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if (Username.length()==0) {
                                Username.requestFocus();
                                Username.setError("required!");


                            }
                            if (Depart.length()==0) {

                                Depart.requestFocus();
                                Depart.setError("required!");



                            }

                            if (Destionation.length()==0) {
                                Destionation.requestFocus();
                                Destionation.setError(" required!");


                            }
                            if (mItemSelected.length()==0) {
                                mItemSelected.requestFocus();
                                Find.setError(" required!");


                            }
                            if (mDisplayDate.length()==0) {
                                mDisplayDate.requestFocus();
                                dateResults.setError(" required!");

                            }

                            else {
                                boolean isUpdate = myDb.updateData2(Username.getText().toString(),
                                        Depart.getText().toString(), Destionation.getText().toString(), mItemSelected.getText().toString(), mDisplayDate.getText().toString());
                                        Toast.makeText(TravelPlan.this, "You Have Booked Your Destination!", Toast.LENGTH_LONG).show();
                            }

                    }
                }
        );

        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(TravelPlan.this);                 // bringing up the view of the checkboxs
                mBuilder.setTitle("Choose Your Items");                                               // title
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            mUserItems.add(position);
                        } else {
                            mUserItems.remove((Integer.valueOf(position)));
                        }

                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.Ok_Label, new DialogInterface.OnClickListener() {        // Submit Button
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";                                     //creting empty string to find  what checkbox is checked
                        for (int i = 0; i < mUserItems.size(); i++) {        //for loop array list
                            item = item + listItems[mUserItems.get(i)];     // pulling items contained in predefined list
                            if (i != mUserItems.size() - 1) {              // if last item in list Difined by ' i ' is not the last item in the list remove comma
                                item = item + "\n";      // here
                            }
                        }

                        mItemSelected.setText(item);   // calling text view
                    }
                });


                mBuilder.setNegativeButton(R.string.Dismiss_Label, new DialogInterface.OnClickListener() {       // dismissing dialog
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


                mBuilder.setNeutralButton(R.string.Clear_All_Label, new DialogInterface.OnClickListener() {   //clear all selected
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {                         // for loop looking at array
                            checkedItems[i] = false;                                            // unchecking items
                            mUserItems.clear();                                                 // clearing items  in list
                            mItemSelected.setText("");                                          // setting empty textview
                        }
                    }
                });

                android.support.v7.app.AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                // displaying checked items
            }
        });

    }



public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent Intents = new Intent(TravelPlan.this, HomePage.class); // <----- START ACTIVITY
            Intents.putExtra("Username", Username.getText().toString());
            startActivity(Intents);
            setContentView(R.layout.activity_login);
        }
        return false;

    }
}
