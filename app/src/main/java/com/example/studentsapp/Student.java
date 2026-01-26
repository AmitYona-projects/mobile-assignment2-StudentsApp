package com.example.studentsapp;

public class Student {
    private String id;
    private String name;
    private String phone;
    private String address;
    private boolean checked;

    public Student(String id, String name, String phone, String address, boolean checked) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
