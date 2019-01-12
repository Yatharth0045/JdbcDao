package magAd;

import magAd.dao.StudentDAO;
import magAd.models.Student;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws Exception {
        StudentDAO dao = new StudentDAO();
        Student s;
        ArrayList<Student> al;
        String name;

        System.out.println("Retrieve All \n");

        //retrieve-All
        al = dao.getStudents();
        for (Student std : al) {
            System.out.println(std.getId() + " -- " + std.getName());
        }

        System.out.println("\nInsert\n");

        //insertion
        Student s2 = new Student(9, "Megha");
        dao.addStudent(s2);

        System.out.println("\nRetrieve\n");

        //retrieve
        s = dao.getStudent(8);
        System.out.println("Retrieve student : " + s.getName());

        System.out.println("\nUpdate\n");

        //update
        dao.updateStudent(5, "Mayank");
        s = dao.getStudent(5);
        name = s.getName();
        System.out.println("Updated student : " + name);

        System.out.println("\nDelete\n");

        //delete
        dao.deleteStudent(3);
    }
}
