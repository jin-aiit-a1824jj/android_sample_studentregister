package a1824jj.jp.ac.aiit.studentregister;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private StudentAppDatabase studentAppDatabase;

    private ArrayList<Student> students;
    private StudentDataAdapter studentDataAdapter;
    public static final  int NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        studentDataAdapter = new StudentDataAdapter();
        recyclerView.setAdapter(studentDataAdapter);

        studentAppDatabase = Room.databaseBuilder(getApplicationContext(), StudentAppDatabase.class, "StudentDB").build();
        loadData();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Student studentToDelete = students.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDelete);
            }
        }).attachToRecyclerView(recyclerView);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewStudent.class);
                startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    private void loadData() {
        new GetAllStudentAsyncTask().execute();
    }

    private class GetAllStudentAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            students = (ArrayList<Student>) studentAppDatabase.getStudentDao().getAllStudent();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            studentDataAdapter.setStudents(students);
        }
    }

    private  void deleteStudent(Student student){
        new DeleteStudentAsyncTask().execute(student);
    }

    private class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void>{

        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.getStudentDao().delete(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    private void addNewStudent(Student student){
        new AddNewStudentAsyncTask().execute(student);
    }

    private class AddNewStudentAsyncTask extends AsyncTask<Student, Void, Void>{

        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.getStudentDao().insert(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");
            String country = data.getStringExtra("COUNTRY");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.JAPAN);
            String currentDate = simpleDateFormat.format(new Date());

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setCountry(country);
            student.setRegisteredTime(currentDate);

            addNewStudent(student);
        }
    }
}
