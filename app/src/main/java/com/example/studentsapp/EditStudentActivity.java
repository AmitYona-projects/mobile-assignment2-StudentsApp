package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {
    private Student student;
    private StudentsRepository repository;
    private EditText editName, editId, editPhone, editAddress;
    private CheckBox checkBox;
    private String originalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.edit_student);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = StudentsRepository.getInstance();

        String studentId = getIntent().getStringExtra("student_id");
        student = repository.getStudentById(studentId);

        if (student == null) {
            finish();
            return;
        }

        originalId = student.getId();

        ImageView studentPic = findViewById(R.id.student_pic);
        studentPic.setImageResource(R.drawable.student_pic);

        editName = findViewById(R.id.edit_name);
        editId = findViewById(R.id.edit_id);
        editPhone = findViewById(R.id.edit_phone);
        editAddress = findViewById(R.id.edit_address);
        checkBox = findViewById(R.id.checkbox);

        // Pre-fill with current student data
        editName.setText(student.getName());
        editId.setText(student.getId());
        editPhone.setText(student.getPhone());
        editAddress.setText(student.getAddress());
        checkBox.setChecked(student.isChecked());

        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnSave = findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete this student?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        repository.deleteStudent(originalId);
                        finish();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String id = editId.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String address = editAddress.getText().toString().trim();
            boolean checked = checkBox.isChecked();

            if (name.isEmpty() || id.isEmpty()) {
                // Simple validation
                return;
            }

            // Create updated student
            Student updatedStudent = new Student(id, name, phone, address, checked);
            
            // Update in repository (handles ID change if needed)
            repository.updateStudent(originalId, updatedStudent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
