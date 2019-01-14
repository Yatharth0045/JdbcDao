package magAd.dao;

import magAd.models.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    private static Connection connection;
    private String query;

    public Student getStudent(int rollNo) throws Exception {
        Student student = new Student();
        student.setId(rollNo);
        JConnection jConnection = JConnection.getInstance();
        connection=jConnection.getConection();
        query="select * from student where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, rollNo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student.setName(rs.getString(2));
        }
        rs.close();
        stmt.close();
        jConnection.closeConnection(connection);
        return student;
    }

    public boolean addStudent(Student s) throws Exception {
        JConnection jConnection = JConnection.getInstance();
        connection=jConnection.getConection();
        query="insert into student values (?,?);";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, s.getId());
        stmt.setString(2, s.getName());
        stmt.execute();
        stmt.close();
        jConnection.closeConnection(connection);
        return true;
    }

    public void updateStudent(int id, String newName) throws Exception {
        JConnection jConnection = JConnection.getInstance();
        connection=jConnection.getConection();
        query="update student set name = ? where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, newName);
        stmt.setInt(2, id);
        stmt.execute();
        stmt.close();
        jConnection.closeConnection(connection);
    }

    public void deleteStudent(int id) throws Exception {
        JConnection jConnection = JConnection.getInstance();
        connection=jConnection.getConection();
        query="delete from student where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        connection.close();
    }

    public ArrayList<Student> getStudents() throws Exception {

        ArrayList<Student> student = new ArrayList<Student>();
        Student s;
        JConnection jConnection = JConnection.getInstance();
        connection=jConnection.getConection();
        query="select * from student order by id;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            s = new Student();
            s.setId(rs.getInt(1));
            s.setName(rs.getString(2));
            student.add(s);
        }

        rs.close();
        stmt.close();
        jConnection.closeConnection(connection);

        return student;
    }
}
