package alfaisal.firebase.abubaker_200453;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;

public class MainActivity4 extends AppCompatActivity {

    private EditText id_edittxt2;
    private EditText name_edittxt2;
    private EditText surname_edittxt2;
    private EditText natid_edittxt2;
    private EditText gender_edittxt2;
    private EditText dob_edittxt2;
    ImageView w_icon3;
    private Button bttnAdd2;
    private Button bttnDel2;
    private Button bttnUp2;
    private Button bttnView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final DatabaseHelper myDB = new DatabaseHelper(this);
        id_edittxt2 = (EditText) findViewById(R.id.id_edittxt2);
        name_edittxt2 = (EditText) findViewById(R.id.name_edittxt2);
        surname_edittxt2 = (EditText) findViewById(R.id.surname_edittxt2);
        natid_edittxt2 = (EditText) findViewById(R.id.natid_edittxt2);
        gender_edittxt2 = (EditText) findViewById(R.id.gender_edittxt2);
        dob_edittxt2 = (EditText) findViewById(R.id.dob_edittxt2);
        bttnAdd2 = (Button) findViewById(R.id.bttnAdd2);
        bttnDel2 = (Button) findViewById(R.id.bttnDel2);
        bttnUp2 = (Button) findViewById(R.id.bttnUp2);
        bttnView2 = (Button) findViewById(R.id.bttnView2);
        w_icon3 = (ImageView) findViewById(R.id.w_icon3);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String iconval = sp.getString("ticon","");
        if(iconval == "sunny"){
            w_icon3.setImageResource(R.drawable.sunny);
        }
        else if(iconval == "cloudy"){
            w_icon3.setImageResource(R.drawable.cloudy);
        }
        else if(iconval == "rainy"){
            w_icon3.setImageResource(R.drawable.rainy);
        }
        else{
            w_icon3.setImageResource(R.drawable.notav);
        }

        bttnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id_edittxt2.getText().toString();
                String name_val = name_edittxt2.getText().toString();
                String surname_val = surname_edittxt2.getText().toString();
                String natid_val = natid_edittxt2.getText().toString();
                String gender_val = gender_edittxt2.getText().toString();
                String dob_val = dob_edittxt2.getText().toString();
                myDB.AddStudent(id_val,name_val,surname_val,natid_val,dob_val,gender_val);
                Toasty.success(getBaseContext(), "Student added successfully.", Toast.LENGTH_LONG,
                        true).show();}
        });

        bttnView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myDB.ViewStudents();
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext()) {
                    buffer.append("id: " + cur.getString(0)+ "\n");
                    buffer.append("Name: " + cur.getString(1)+ "\n");
                    buffer.append("Surname: " + cur.getString(2)+ "\n");
                    buffer.append("NatID: " + cur.getString(3)+ "\n");
                    buffer.append("D.O.B: " + cur.getString(4)+ "\n");
                    buffer.append("Gender: " + cur.getString(5)+ "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                builder.setCancelable(true);  // a dialog box that can be closed
                builder.setTitle("All Students");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        bttnDel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id_edittxt2.getText().toString();
                myDB.DeleteStudents(id_val);
                Toasty.success(getBaseContext(), "Student deleted successfully.", Toast.LENGTH_LONG,
                        true).show();}
        });
        bttnUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_val = id_edittxt2.getText().toString();
                String name_val = name_edittxt2.getText().toString();
                String surname_val = surname_edittxt2.getText().toString();
                String natid_val = natid_edittxt2.getText().toString();
                String gender_val = gender_edittxt2.getText().toString();
                String dob_val = dob_edittxt2.getText().toString();
                myDB.updateStudent(id_val, name_val, surname_val, natid_val, dob_val,gender_val);
                Toasty.success(getBaseContext(), "Student updated successfully.", Toast.LENGTH_LONG,
                        true).show();}
        });
    }
}