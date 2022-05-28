package alfaisal.firebase.abubaker_200453;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText id_edittxt;
    private EditText name_edittxt;
    private EditText surname_edittxt;
    private EditText natid_edittxt;
    private EditText gender_edittxt;
    private EditText dob_edittxt;
    private Button bttnAdd;
    private Button bttnDel;
    private Button bttnUp;
    private Button bttnView;
    private Button wBttn;
    private Button sqlBttn;
    ImageView w_icon2;

    private String key;
    private String id;
    private String name;
    private String surname;
    private String natID;
    private String gender;
    private String dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = getIntent().getStringExtra("key");
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        surname = getIntent().getStringExtra("surname");
        natID = getIntent().getStringExtra("natID");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");

        id_edittxt = (EditText) findViewById(R.id.id_edittxt);
        id_edittxt.setText(id);
        name_edittxt = (EditText) findViewById(R.id.name_edittxt);
        name_edittxt.setText(name);
        surname_edittxt = (EditText) findViewById(R.id.surname_edittxt);
        surname_edittxt.setText(surname);
        natid_edittxt = (EditText) findViewById(R.id.natid_edittxt);
        natid_edittxt.setText(natID);
        gender_edittxt = (EditText) findViewById(R.id.gender_edittxt);
        gender_edittxt.setText(gender);
        dob_edittxt = (EditText) findViewById(R.id.dob_edittxt);
        dob_edittxt.setText(dob);
        bttnAdd = (Button) findViewById(R.id.bttnAdd);
        bttnDel = (Button) findViewById(R.id.bttnDel);
        bttnUp = (Button) findViewById(R.id.bttnUp);
        bttnView = (Button) findViewById(R.id.bttnView);
        wBttn = (Button) findViewById(R.id.wBttn);
        sqlBttn = (Button) findViewById(R.id.sqlBttn);
        w_icon2 = (ImageView) findViewById(R.id.w_icon2);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String iconval = sp.getString("ticon","");
        if(iconval == "sunny"){
            w_icon2.setImageResource(R.drawable.sunny);
        }
        else if(iconval == "cloudy"){
            w_icon2.setImageResource(R.drawable.cloudy);
        }
        else if(iconval == "rainy"){
            w_icon2.setImageResource(R.drawable.rainy);
        }
        else{
            w_icon2.setImageResource(R.drawable.notav);
        }

        bttnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students students = new Students();
                students.setId(id_edittxt.getText().toString());
                students.setName(name_edittxt.getText().toString());
                students.setSurname(surname_edittxt.getText().toString());
                students.setNatID(natid_edittxt.getText().toString());
                students.setGender(gender_edittxt.getText().toString());
                students.setDob(dob_edittxt.getText().toString());

                new FirebaseDatabaseHelper().addStudent(students, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Students> student, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toasty.success(getBaseContext(), "Student inserted successfully.", Toast.LENGTH_LONG,
                                true).show();
                        }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });
        bttnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
        bttnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Students students = new Students();
                students.setId(id_edittxt.getText().toString());
                students.setName(name_edittxt.getText().toString());
                students.setSurname(surname_edittxt.getText().toString());
                students.setNatID(natid_edittxt.getText().toString());
                students.setGender(gender_edittxt.getText().toString());
                students.setDob(dob_edittxt.getText().toString());

                new FirebaseDatabaseHelper().updateStudent(key, students, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Students> student, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toasty.success(getBaseContext(), "Student updated successfully.", Toast.LENGTH_LONG,
                                true).show();
                        }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        bttnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteStudent(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Students> student, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toasty.success(getBaseContext(), "Student deleted successfully.", Toast.LENGTH_LONG,
                                true).show();
                       }
                });
            }
        });
        wBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity3.class));
            }
        });
        sqlBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity4.class));
            }
        });
    }
}