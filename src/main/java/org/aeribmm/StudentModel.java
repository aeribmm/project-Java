package org.aeribmm;

public class StudentModel {

    private String id;
    private String name;
    private String lastName;
    private int age;
    private double grade;
    private DAO dao;

    public StudentModel(DAO dao) {
        this.dao = dao;
    }

    public StudentModel(String id, String name, String lastName, int age, double grade) {
        this(new DAO());
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
    }

    public StudentModel(String name, String lastName, int age) {
        this.id = dao.generateFreeId();
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.grade = 0;
    }

    public StudentModel() {
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
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
        System.out.println("id = " + getId() + " name = " + getName() + "last name: " + getLastName() + " age = " + getAge() + " grade = " + getGrade());
    }
}
