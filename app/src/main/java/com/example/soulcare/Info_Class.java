package com.example.soulcare;


public class Info_Class {
    private int id;
    private String password;
    private String contact_name;
    private String contact_number;
    private boolean next_of_kin;

    public Info_Class(int id, String password, String contact_name, String contact_number, boolean next_of_kin) {
        this.id = id;
        this.password = password;
        this.contact_name = contact_name;
        this.contact_number = contact_number;
        this.next_of_kin = next_of_kin;
    }
    //default constructor
    public Info_Class() {
    }

    public boolean getNextOfKin()
    {
        return next_of_kin;
    }

    public void setNextOfKin(boolean next_of_kin)
    {
        this.next_of_kin = next_of_kin;
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

    public String getPass(){return password;}

    @Override
    public String toString() {
        if(contact_number.length() > 10 || contact_number.length() < 10)
        {
            return contact_name + '\n' + contact_number;
        }
        else {
            return
                    contact_name + '\n' +
                            "(" + contact_number.charAt(0) + contact_number.charAt(1) + contact_number.charAt(2) +
                            ") " + contact_number.charAt(3) + contact_number.charAt(4) + contact_number.charAt(5) + "-"
                            + contact_number.charAt(6) + contact_number.charAt(7) + contact_number.charAt(8) + contact_number.charAt(9);
        }
    }
}
