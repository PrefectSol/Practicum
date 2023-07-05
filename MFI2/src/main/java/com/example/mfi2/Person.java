package com.example.mfi2;

public class Person {
    private String fio;
    private String phone;
    private String service;

    public Person(String fio, String phone, String service) {
        this.fio = fio;
        this.phone = phone;
        this.service = service;
    }
    public String getFio(){return fio;}
    public String getPhone(){return phone;}
    public String getService(){return service;}
    public void setFio(String fio){this.fio = fio;}
    public void setPhone(String phone){this.phone = phone;}
    public void setService(String service){this.service = service;}
}
