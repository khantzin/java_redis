package org.example;

public class TestData {

    private String name;
    private String email;
    private String phNo;
    private String address;

    public TestData(String name, String email, String phNo, String address) {
        this.name = name;
        this.email = email;
        this.phNo = phNo;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phNo='" + phNo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
