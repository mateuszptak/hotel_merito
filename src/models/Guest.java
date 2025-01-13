package models;

import java.time.LocalDate;

public class Guest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Guest(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // Lista getterów
    public String getFirstName(String firstName) {
        return firstName;
    }

    public String getLastName(String lastName) {
        return lastName;
    }

    public LocalDate getDateOfBirth(LocalDate dateOfBirth) {
        return dateOfBirth;
    }

}