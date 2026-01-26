package com.example.studentsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class NewStudentActivity extends AppCompatActivity {
    private EditText editName, editId, editPhone, editAddress;
    private CheckBox checkBox;
    private StudentsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.new_student);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = StudentsRepository.getInstance();

        ImageView studentPic = findViewById(R.id.student_pic);
        studentPic.setImageResource(R.drawable.student_pic);

        editName = findViewById(R.id.edit_name);
        editId = findViewById(R.id.edit_id);
        editPhone = findViewById(R.id.edit_phone);
        editAddress = findViewById(R.id.edit_address);
        checkBox = findViewById(R.id.checkbox);

        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnSave = findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String id = editId.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            boolean checked = checkBox.isChecked();

            if (name.isEmpty() || id.isEmpty()) {
                // Simple validation - you can add more sophisticated validation
                return;
            }

            Student newStudent = new Student(id, name, phone, address, checked);
            repository.addStudent(newStudent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
