import java.util.Arrays;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String email;
    private String[] roles;
    private String[] phones;

    public User(String name, String surname, String email, String[] roles, String[] phones){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public User(){}

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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return  name + ", " +
                surname + ", " +
                email + ", " +
                arrayToString(roles)+ ", " +
                arrayToString(phones);
    }

    private String arrayToString(String[] userField){
        StringBuilder sb = new StringBuilder(userField[0]);
        for (int i = 1; i < userField.length; i++) {
            sb.append(" : ").append(userField[i]);
        }
        return String.valueOf(sb);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(roles, user.roles) &&
                Arrays.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, email);
        result = 31 * result + Arrays.hashCode(roles);
        result = 31 * result + Arrays.hashCode(phones);
        return result;
    }
}
