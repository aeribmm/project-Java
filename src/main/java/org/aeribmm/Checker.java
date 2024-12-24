package org.aeribmm;

import javax.swing.*;

public class Checker {
    private DAO dao;
    public Checker(){
        this(new DAO());
    }

    public Checker(DAO dao) {
        this.dao = dao;
    }

    public void isFull(JTextField name1, JTextField lastName1, JTextField age1){
        int a = -1;
        String name = name1.getText().trim();
        String lastName = lastName1.getText().trim();
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter your name please","Warning",JOptionPane.WARNING_MESSAGE);
        }if(lastName.isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter your last name please","Warning",JOptionPane.WARNING_MESSAGE);
        }
        try{
            String temp = age1.getText().trim();
            int age = Integer.parseInt(temp);
            if(age < 0 || age > 100){
                JOptionPane.showMessageDialog(null,"Cannot create a student with this age","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            a = age;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Not a number","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        StudentModel student = new StudentModel(name,lastName,a);
        boolean exist = dao.checkIfStudentExists(student.getName(), student.getLastName(),student.getAge());
        if(!exist){
            dao.addStudent(student);
        }else {
            JOptionPane.showMessageDialog(null,"Student already exist","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
