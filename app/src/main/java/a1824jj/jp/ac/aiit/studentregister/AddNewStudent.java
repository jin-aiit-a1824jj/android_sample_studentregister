package a1824jj.jp.ac.aiit.studentregister;

import a1824jj.jp.ac.aiit.studentregister.databinding.ActivityAddNewStudentBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
// import android.text.TextUtils;
import android.view.View;
// import android.widget.Button;
// import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudent extends AppCompatActivity {

//    private Button submitButton;
//    private EditText nameEditText;
//    private EditText emailEditText;
//    private EditText countryEditText;

    private ActivityAddNewStudentBinding activityAddNewStudentBinding;
    private AddNewStudentActivityClickHandlers addNewStudentActivityClickHandlers;
    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        activityAddNewStudentBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student);
        student = new Student();
        activityAddNewStudentBinding.setStudent(student);

        addNewStudentActivityClickHandlers = new AddNewStudentActivityClickHandlers(this);
        activityAddNewStudentBinding.setClickHandlers(addNewStudentActivityClickHandlers);

//        nameEditText = findViewById(R.id.et_name);
//        emailEditText = findViewById(R.id.et_email);
//        countryEditText = findViewById(R.id.et_country);
//        submitButton = findViewById(R.id.btnSubmit);
//
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(nameEditText.getText())){
//                    Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
//                }else{
//                    String name = nameEditText.getText().toString();
//                    String email = emailEditText.getText().toString();
//                    String country = countryEditText.getText().toString();
//
//                    Intent intent = new Intent();
//                    intent.putExtra("NAME", name);
//                    intent.putExtra("EMAIL", email);
//                    intent.putExtra("COUNTRY", country);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            }
//        });
    }

    public class AddNewStudentActivityClickHandlers{
        Context content;

        public AddNewStudentActivityClickHandlers(Context content) {
            this.content = content;
        }

        public void onFABClicked(View view){
            if(student.getName() == null){
                Toast.makeText(getApplicationContext(), "Name field cannot be empty", Toast.LENGTH_LONG).show();
            }else{
                String name = student.getName();
                String email = student.getEmail();
                String country = student.getCountry();

                Intent intent = new Intent();
                intent.putExtra("NAME", name);
                intent.putExtra("EMAIL", email);
                intent.putExtra("COUNTRY", country);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
