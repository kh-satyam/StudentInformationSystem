package org.satyam.ss.restServices.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

import org.satyam.ss.restServices.model.Student;
import org.satyam.ss.restServices.Service.StudentNameComparator;

import javafx.scene.chart.PieChart.Data;

public class RestService {
	private Connection connection;
	public RestService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(
					DatabaseConfiguration.dataBaseUrl,
					DatabaseConfiguration.userName,
					DatabaseConfiguration.password);
		}catch(Exception e) {
			System.out.println("Error occurred");
			System.out.println(e);
		}
	}
	public int addStudentWithImage(Student obj,InputStream is,String location) {
		int success=0;
		try {
			System.out.println("herer");
			String name=obj.getName();
			int rollno=obj.getRollNumber();
			LocalDate dob=obj.getDOB();
			//Date date=new Date(dob.getYear(),dob.getMonthValue(),dob.getDayOfMonth());
			double phy=obj.getPhysicsMarks();
			double chem=obj.getMathematicsMarks();
			double maths=obj.getChemistryMarks();
			PreparedStatement stmt=connection.prepareStatement("insert into student values(?,?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setDate(2,Date.valueOf(dob));
			stmt.setInt(3, rollno);
			stmt.setDouble(4, phy);
			stmt.setDouble(5, chem);
			stmt.setDouble(6, maths);
			success=stmt.executeUpdate();
			saveFile(is,location);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public void saveFile(InputStream uploadedInputStream,
            String serverLocation) {
			try {
				OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
				int read = 0;
				byte[] bytes = new byte[1024];
				outpuStream = new FileOutputStream(new File(serverLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					outpuStream.write(bytes, 0, read);
				}
				outpuStream.flush();
				outpuStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public Student getStudent(int roll) {
		Student student=new Student();
		boolean found=false;
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from student where rollNumber=?");
			stmt.setInt(1, roll);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				found=true;
				student.setName(rs.getString(1));
				student.setRollNumber(rs.getInt(3));
				java.sql.Date sqlDate=rs.getDate(2);
				LocalDate date=sqlDate.toLocalDate();
				student.setDOB(date);
				student.setChemistryMarks(rs.getDouble(5));
				student.setPhysicsMarks(rs.getDouble(4));
				student.setMathematicsMarks(rs.getDouble(6));
			}
			//System.out.println(res.getName());
		}catch(Exception e) {
			System.out.println(e);
		}
		if(found==false) {
			return null;
		}
		return student;
	}
	public int updateName(int roll,String value) {
		int success=0;
		try {
			String sql = "update student set name=? where rollnumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,value);
		    stmt.setInt(2, roll);
		    success=stmt.executeUpdate();
		    System.out.println(success);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public int updateMathematicsMarks(int roll,double value) {
		int success=0;
		try {
			String sql = "update student set mathematicsMarks=? where rollNumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, value);
			stmt.setInt(2, roll);
		    success=stmt.executeUpdate();
		    System.out.println(success);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public int updateChemistryMarks(int roll,double value) {
		int success=0;
		try {
			String sql = "update student set chemistryMarks=? where rollNumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, value);
			stmt.setInt(2, roll);
		    success=stmt.executeUpdate();
		    System.out.println(success);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public int updatePhysicsMarks(int roll,double value) {
		int success=0;
		try {
			String sql = "update student set physicsMarks=? where rollnumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, value);
			stmt.setInt(2, roll);
		    success=stmt.executeUpdate();
		    System.out.println(success);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public int updateDOB(int roll,LocalDate date) {
		int success=0;
		try {
			//java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sql = "update student set DOB=? where rollnumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1,Date.valueOf(date));
			stmt.setInt(2, roll);
			success=stmt.executeUpdate();
		    System.out.println(success);
		}catch(Exception e) {
			System.out.println(e);
		}
		return success;
	}
	public ArrayList<Student> getAllByName(){
		ArrayList<Student> students=new ArrayList<Student>();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from student;");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Student student=new Student();
				student.setName(rs.getString(1));
				student.setRollNumber(rs.getInt(3));
				java.sql.Date sqlDate=rs.getDate(2);
				LocalDate date=sqlDate.toLocalDate();
				student.setDOB(date);
				student.setChemistryMarks(rs.getDouble(5));
				student.setPhysicsMarks(rs.getDouble(4));
				student.setMathematicsMarks(rs.getDouble(6));
				student.updateMarks();
				students.add(student);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		Collections.sort(students, new StudentNameComparator());
		return students;
	}
	public ArrayList<Student> getAllByTotalMarks(){
		ArrayList<Student> students=new ArrayList<Student>();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from student;");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Student student=new Student();
				student.setName(rs.getString(1));
				student.setRollNumber(rs.getInt(3));
				java.sql.Date sqlDate=rs.getDate(2);
				LocalDate date=sqlDate.toLocalDate();
				student.setDOB(date);
				student.setChemistryMarks(rs.getDouble(5));
				student.setPhysicsMarks(rs.getDouble(4));
				student.setMathematicsMarks(rs.getDouble(6));
				student.updateMarks();
				students.add(student);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		Collections.sort(students, new StudentTotalMarksComparator());
		return students;
	}
	public ArrayList<Student> getAllByGrade(){
		ArrayList<Student> students=new ArrayList<Student>();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from student;");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Student student=new Student();
				student.setName(rs.getString(1));
				student.setRollNumber(rs.getInt(3));
				java.sql.Date sqlDate=rs.getDate(2);
				LocalDate date=sqlDate.toLocalDate();
				student.setDOB(date);
				student.setChemistryMarks(rs.getDouble(5));
				student.setPhysicsMarks(rs.getDouble(4));
				student.setMathematicsMarks(rs.getDouble(6));
				student.updateMarks();
				students.add(student);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		Collections.sort(students, new StudentGradeComparator());
		return students;
	}
	public ArrayList<Student> getAllByNameRange(int from,int to,String key){
		ArrayList<Student> students=new ArrayList<Student>();
		try {
			PreparedStatement stmt=connection.prepareStatement("select * from student where rollnumber between ? and ?;");
			stmt.setInt(1,from);
			stmt.setInt(2,to);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Student student=new Student();
				student.setName(rs.getString(1));
				student.setRollNumber(rs.getInt(3));
				java.sql.Date sqlDate=rs.getDate(2);
				LocalDate date=sqlDate.toLocalDate();
				student.setDOB(date);
				student.setChemistryMarks(rs.getDouble(5));
				student.setPhysicsMarks(rs.getDouble(4));
				student.setMathematicsMarks(rs.getDouble(6));
				student.updateMarks();
				students.add(student);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		if(key.compareTo("name")==0) {
			Collections.sort(students, new StudentNameComparator());
		}
		else if(key.compareTo("totalMarks")==0) {
			Collections.sort(students, new StudentTotalMarksComparator());
		}
		else if(key.compareTo("grade")==0) {
			Collections.sort(students, new StudentGradeComparator());
		}
		else if(key.compareTo("rollNumber")==0) {
			Collections.sort(students,new StudentComparator());
		}
		return students;
	}
}
