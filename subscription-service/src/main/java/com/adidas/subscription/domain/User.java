package com.adidas.subscription.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="user")
@Table(name="user")
public class User {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="gender")
    private String gender;

    @Column(name="birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date birthDate;

    
    /**
     * Default constructor
     */
    public User() {
        super();
    }

    /**
     * 
     * @param email
     * @param firstName
     * @param gender
     * @param birthDate
     */
    public User(String email, String firstName, String gender, Date birthDate) {
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
    }
    
    /**
     * Full constructor
     * @param id
     * @param email
     * @param firstName
     * @param gender
     * @param birthDate
     */
    public User(Long id, String email, String firstName, String gender, Date birthDate) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
       
        if (email == null) {
            if (other.email != null){
                return false;
            }
        } else if (!email.equals(other.email)){
            return false;
        }    
        return true;
    }
    
}
