package com.example.mfi2;

public class Person {
    private String fio;
    private String phone;
    private String service;
    private String service1;
    private String service2;
    private int price = 0;
    private int price1 = 0;
    private int price2 = 0;
    private int summPrice = 0;

    public Person(String fio, String phone, String service, String service1, String service2) {
        this.fio = fio;
        this.phone = phone;
        this.service = service;
        this.service1 = service1;
        this.service2 = service2;
        if (service.equals("Окрашивание")){     //1 услуга
            price = 3000;
        }
        if (service.equals("Стрижка")){
            price = 500;
        }
        if (service.equals("Макияж")){
            price = 1500;
        }
        if (service.equals("Инъекция")){
            price = 5000;
        }
        if (service.equals("Пилинг")){
            price = 1500;
        }
        if (service1.equals("Окрашивание")){        //2 услуга
            price1 = 3000;
        }
        if (service1.equals("Стрижка")){
            price1 = 500;
        }
        if (service1.equals("Макияж")){
            price1 = 1500;
        }
        if (service1.equals("Инъекция")){
            price1 = 5000;
        }
        if (service1.equals("Пилинг")){
            price1 = 1500;
        }
        if (service2.equals("Окрашивание")){        //3 услуга
            price2 = 3000;
        }
        if (service2.equals("Стрижка")){
            price2 = 500;
        }
        if (service2.equals("Макияж")){
            price2 = 1500;
        }
        if (service2.equals("Инъекция")){
            price2 = 5000;
        }
        if (service2.equals("Пилинг")){
            price2 = 1500;
        }
        summPrice = price + price1 + price2;
    }
    public String getFio(){return fio;}
    public String getPhone(){return phone;}
    public String getService(){return service;}
    public String getService1(){return service1;}
    public String getService2(){return service2;}
    public int getSummPrice(){return summPrice;}
    public void setSummPrice(int summPrice){this.summPrice = summPrice;}
    public void setFio(String fio){this.fio = fio;}
    public void setPhone(String phone){this.phone = phone;}
    public void setService(String service){this.service = service;}
}
