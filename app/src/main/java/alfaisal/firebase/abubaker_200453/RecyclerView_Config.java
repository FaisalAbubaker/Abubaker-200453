package alfaisal.firebase.abubaker_200453;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private StudentsAdapter mStudentsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Students> students, List<String> keys){
        mContext = context;
        mStudentsAdapter = new StudentsAdapter(students, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mStudentsAdapter);
    }

    class StudentItemView extends RecyclerView.ViewHolder {
        private TextView mId;
        private TextView mName;
        private TextView mSurname;
        private TextView mNatID;
        private TextView mGender;
        private TextView mDob;

        private String key;

        public StudentItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.student_list_item, parent, false));
            mId = (TextView) itemView.findViewById(R.id.id_txtview);
            mName = (TextView) itemView.findViewById(R.id.name_txtview);
            mSurname = (TextView) itemView.findViewById(R.id.surname_txtview);
            mNatID = (TextView) itemView.findViewById(R.id.natid_txtview);
            mGender = (TextView) itemView.findViewById(R.id.gender_txtview);
            mDob = (TextView) itemView.findViewById(R.id.dob_txtview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,MainActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("id",mId.getText().toString());
                    intent.putExtra("name",mName.getText().toString());
                    intent.putExtra("surname",mSurname.getText().toString());
                    intent.putExtra("natID",mNatID.getText().toString());
                    intent.putExtra("gender",mGender.getText().toString());
                    intent.putExtra("dob",mDob.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Students students, String key){
            mId.setText(students.getId());
            mName.setText(students.getName());
            mSurname.setText(students.getSurname());
            mNatID.setText(students.getNatID());
            mGender.setText(students.getGender());
            mDob.setText(students.getDob());
            this.key = key;
        }
    }
    class StudentsAdapter extends RecyclerView.Adapter<StudentItemView>{
        private List<Students> mStudentsList;
        private List<String> mKeys;

        public StudentsAdapter(List<Students> mStudentsList, List<String> mKeys) {
            this.mStudentsList = mStudentsList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public StudentItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StudentItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentItemView holder, int position) {
            holder.bind(mStudentsList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mStudentsList.size();
        }
    }
}
