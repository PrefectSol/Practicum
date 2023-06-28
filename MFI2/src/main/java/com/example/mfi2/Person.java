package com.example.mfi2;

public class Person {
    private String fio;
    private String phone;

    public Person(String fio, String phone) {
        this.fio = fio;
        this.phone = phone;
    }
    public String getFio(){return fio;}
    public String getPhone(){return phone;}
    public void setFio(String fio){this.fio = fio;}
    public void setPhone(String phone){this.phone = phone;}
}
