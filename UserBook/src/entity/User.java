package entity;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String email;
    private List<RoleField> roles;
    private List<PhoneField> phones;

    public User(String name, String surname, String email, List<RoleField> roles, List<PhoneField> phones) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleField> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleField> roles) {
        this.roles = roles;
    }

    public List<PhoneField> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneField> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return  name + ", " +
                surname + ", " +
                email + ", " +
                listToString(roles) + ", " +
                listToString(phones);
    }

    public String listToString(List<? extends UserField> userField){
        StringBuilder sb = new StringBuilder(userField.get(0).getTitle());
        for(UserField field : userField){
            if(userField.indexOf(field) != 0){
                sb.append(" : ").append(field.getTitle());
            }
        }
        return String.valueOf(sb);
    }

}

