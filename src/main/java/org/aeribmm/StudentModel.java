package org.aeribmm;

public class StudentModel {

    private String id;
    private String name;
    private String lastName;
    private int age;
    private double grade;

    public StudentModel(String id, String name,String lastName, int age, double grade) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
    }

    public String getLastName(){
        return lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void show(){
        System.out.println("id = " + getId() + " name = " + getName() + " age = " + getAge() + " grade = " + getGrade());
    }
}
