package com.example.soulcare;


public class Contact_Class {
    private int id;
    private String contact_name;
    private String contact_number;

    public Contact_Class(int id, String contact_name, String contact_number) {
        this.id = id;
        this.contact_name = contact_name;
        this.contact_number = contact_number;
    }
    //default constructor
    public Contact_Class() {
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if(contact_number.length() > 10 || contact_number.length() < 10)
        {
            return contact_name + "\n" + contact_number;
        }
        else {
            return contact_name + '\n' +
                    "(" + contact_number.charAt(0) + contact_number.charAt(1) + contact_number.charAt(2) + ")" + contact_number.charAt(3) + contact_number.charAt(4) +
                    contact_number.charAt(5) + "-" + contact_number.charAt(6) + contact_number.charAt(7) + contact_number.charAt(8) + contact_number.charAt(9);
        }
    }

}
