package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @EmbeddedId
    private PersonId personId;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city_of_living")
    private String cityOfLiving;
    public Person (){}
    public PersonId getPersonId(){
        return personId;
    }
    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getCityOfLiving() {
        return cityOfLiving;
    }
    public void setCityOfLiving(String cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }
}
