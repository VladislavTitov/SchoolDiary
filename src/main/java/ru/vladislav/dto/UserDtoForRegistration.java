package ru.vladislav.dto;

public class UserDtoForRegistration {

    private String name;
    private String password;

    public UserDtoForRegistration() {
    }

    public UserDtoForRegistration(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDtoForRegistration{\n\t" +
                "name='" + name + '\'' +
                ", \n\tpassword='" + password + '\'' +
                '}';
    }
}
