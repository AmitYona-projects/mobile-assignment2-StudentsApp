package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    private Student student;
    private StudentsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.student_details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = StudentsRepository.getInstance();

        String studentId = getIntent().getStringExtra("student_id");
        student = repository.getStudentById(studentId);

        if (student == null) {
            finish();
            return;
        }

        ImageView studentPic = findViewById(R.id.student_pic);
        studentPic.setImageResource(R.drawable.student_pic);

        TextView textName = findViewById(R.id.text_name);
        TextView textId = findViewById(R.id.text_id);
        TextView textPhone = findViewById(R.id.text_phone);
        TextView textAddress = findViewById(R.id.text_address);
        CheckBox checkBox = findViewById(R.id.checkbox);

        textName.setText("name: " + student.getName());
        textId.setText("id: " + student.getId());
        textPhone.setText("phone: " + student.getPhone());
        textAddress.setText("address: " + student.getAddress());
        checkBox.setChecked(student.isChecked());
        checkBox.setEnabled(false); // Read-only in details view

        Button btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(StudentDetailsActivity.this, EditStudentActivity.class);
            intent.putExtra("student_id", student.getId());
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
