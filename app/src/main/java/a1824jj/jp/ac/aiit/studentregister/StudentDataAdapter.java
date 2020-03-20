package a1824jj.jp.ac.aiit.studentregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDataAdapter extends RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder>{

    private ArrayList<Student> students;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentStudent = students.get(position);
        holder.name.setText(currentStudent.getName());
        holder.email.setText(currentStudent.getEmail());
        holder.country.setText(currentStudent.getCountry());
        holder.date.setText(currentStudent.getRegisteredTime());
    }

    @Override
    public int getItemCount() {
        if(students == null) return 0;
        return students.size();
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView name, email, country, date;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            email = itemView.findViewById(R.id.tvEmail);
            country = itemView.findViewById(R.id.tvCountry);
            date = itemView.findViewById(R.id.tvTime);
        }
    }
}
