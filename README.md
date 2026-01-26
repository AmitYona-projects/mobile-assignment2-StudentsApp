# Students App - Assignment 2

A simple Android application for managing student information with in-memory data storage.

## Features

The app consists of 4 main screens:

1. **Students List** - Displays all students in a RecyclerView with:
   - Student picture (static image)
   - Student name
   - Student ID
   - Checkbox for check status
   - Floating Action Button (FAB) to add new students

2. **New Student** - Form to add a new student with:
   - Name field
   - ID field
   - Phone field
   - Address field
   - Checkbox for check status
   - Cancel and Save buttons

3. **Student Details** - View detailed information of a selected student:
   - Student picture
   - Name, ID, Phone, Address (read-only)
   - Check status (read-only)
   - Edit button to navigate to edit screen

4. **Edit Student** - Update or delete a student:
   - All fields are editable (including student ID)
   - Cancel, Delete, and Save buttons
   - Delete shows a confirmation dialog

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/studentsapp/
│   │   ├── Student.java                    # Student model class
│   │   ├── StudentsRepository.java         # In-memory data repository
│   │   ├── StudentAdapter.java             # RecyclerView adapter
│   │   ├── StudentsListActivity.java       # Main list screen
│   │   ├── NewStudentActivity.java         # Add new student screen
│   │   ├── StudentDetailsActivity.java     # View student details screen
│   │   └── EditStudentActivity.java        # Edit/delete student screen
│   ├── res/
│   │   ├── layout/                         # XML layout files
│   │   ├── drawable/                       # Student picture resource
│   │   └── values/                         # Strings, colors, themes
│   └── AndroidManifest.xml
```

## Key Components

### Student Model
- Simple POJO class with fields: id, name, phone, address, checked

### StudentsRepository
- Singleton pattern for in-memory data storage
- Methods: getAllStudents(), getStudentById(), addStudent(), updateStudent(), deleteStudent(), toggleCheckStatus()
- Data is lost when app is closed (as per requirements)

### Activities
- All screens are implemented as separate Activities
- Each activity has a Toolbar with back navigation support
- Proper intent passing between activities

### RecyclerView
- Custom adapter (StudentAdapter) for the student list
- Click listeners for row selection and checkbox toggling
- CardView layout for each student item

## Requirements Met

✅ 4 screens implemented as separate Activities  
✅ RecyclerView for student list  
✅ In-memory database (no real DB)  
✅ Static student picture for all students  
✅ Checkbox to mark check status  
✅ All values can be updated (including student ID)  
✅ Delete functionality with confirmation  
✅ Proper navigation flow between screens  

## Building the Project

1. Open the project in Android Studio
2. Sync Gradle files
3. Build and run on an emulator or device (minSdk 24)

## Notes

- The student picture is a static drawable resource (purple circle with person icon)
- Sample data is pre-populated in the repository for testing
- All data is stored in memory and will be lost when the app closes
- The app uses Material Design components for a modern UI
