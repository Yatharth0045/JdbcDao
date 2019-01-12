package magAd.dao;

import magAd.models.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    public Student getStudent(int rollNo) throws Exception {
        Student student = new Student();
        student.setId(rollNo);
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres");
        PreparedStatement stmt = conn.prepareStatement("select * from student where id = ?;");
        stmt.setInt(1, rollNo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student.setName(rs.getString(2));
        }
        rs.close();
        stmt.close();
        conn.close();
        return student;
    }

    public boolean addStudent(Student s) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres");
        PreparedStatement stmt = conn.prepareStatement("insert into student values (?,?);");
        stmt.setInt(1, s.getId());
        stmt.setString(2, s.getName());
        stmt.execute();
        stmt.close();
        conn.close();
        return true;
    }

    public void updateStudent(int id, String newName) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres");
        PreparedStatement stmt = conn.prepareStatement("update student set name = ? where id = ?;");
        stmt.setString(1, newName);
        stmt.setInt(2, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public void deleteStudent(int id) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres");
        PreparedStatement stmt = conn.prepareStatement("delete from student where id = ?;");
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public ArrayList<Student> getStudents() throws Exception {

        ArrayList<Student> student = new ArrayList<Student>();
        Student s;
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student order by id;");

        while (rs.next()) {
            s = new Student();
            s.setId(rs.getInt(1));
            s.setName(rs.getString(2));
            student.add(s);
        }

        rs.close();
        stmt.close();
        conn.close();

        return student;
    }
}
