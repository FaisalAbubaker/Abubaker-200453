package alfaisal.firebase.abubaker_200453;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceStudents;
    private List<Students> student = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Students> student,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted ();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceStudents = mDatabase.getReference("student");
    }

    public void readStudents(final DataStatus dataStatus){
        mReferenceStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                student.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Students students = keyNode.getValue(Students.class);
                    student.add(students);
                }
                dataStatus.DataIsLoaded(student,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addStudent(Students students, final DataStatus dataStatus){
        String key = mReferenceStudents.push().getKey();
        mReferenceStudents.child(key).setValue(students).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInserted();
            }
        });
    }
    public void updateStudent(String key, Students students,final DataStatus dataStatus){
        mReferenceStudents.child(key).setValue(students).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteStudent(String key,final DataStatus dataStatus){
        mReferenceStudents.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsDeleted();
            }
        });
    }
}
