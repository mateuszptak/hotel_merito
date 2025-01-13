package models;

import java.time.LocalDate;

public class Guest {
    private String firstName;
    private String lastName;
    private LocalDate birthDay;

    public Guest(String firstName, String lastName, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
    }

    // getters list
    public String getFirstName(String firstName) {
        return firstName;
    }

    public String getLastName(String lastName) {
        return lastName;
    }

    public LocalDate getBirthDay(LocalDate birthDay) {
        return birthDay;
    }

    // setters list
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}