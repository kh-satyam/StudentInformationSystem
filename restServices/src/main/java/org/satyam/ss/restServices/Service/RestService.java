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
import org.satyam.ss.restServices.model.comment;
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
			
<<<<<<< HEAD
			
=======
>>>>>>> branch 'master' of https://github.com/kh-satyam/StudentInformationSystem.git
			
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
			String password=obj.getPassword();
			PreparedStatement stmt=connection.prepareStatement("insert into student(Name,DOB,rollNumber,physicsMarks,chemistryMarks,mathematicsMarks,password) values(?,?,?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setDate(2,Date.valueOf(dob));
			stmt.setInt(3, rollno);
			stmt.setDouble(4, phy);
			stmt.setDouble(5, chem);
			stmt.setDouble(6, maths);
			stmt.setString(7, password);
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
	public int updatePassword(int roll,String pwd) {
		int success=0;
		try {
			String sql = "update student set password=? where rollnumber=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pwd);
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
	public ArrayList<comment> getAndPostComments(String cmt,int roll)
	{
		ArrayList<comment> comment=new ArrayList<comment>();int success=0; 
		if(!cmt.equals("aal")) {
		try
		{
			PreparedStatement stmt=connection.prepareStatement("insert into comment (rollno,comment) values(?,?)");
			stmt.setInt(1, roll);
			stmt.setString(2,cmt);
			success=stmt.executeUpdate();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}}
		try 
		{
			PreparedStatement stmt=connection.prepareStatement("select * from comment;");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				
				int rn=rs.getInt(2);
				ResultSet rs2=null;
				try {
					PreparedStatement stmt2=connection.prepareStatement("select Name from student where rollNumber=?;");
					stmt2.setInt(1, rn);	
					rs2=stmt2.executeQuery();
					rs2.next();
					System.out.println(rs2.getString(2));
				}catch(Exception e) {			System.out.println(e);}
				
				
				
				comment.add(new comment(rs.getInt(2),rs.getString(3),rs2.getString(1)));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return comment;
	}
	public String login(int rollno,String password)
	{
		System.out.println("login validation");
		String pass="";
		
		try {
			PreparedStatement stmt=connection.prepareStatement("select password from student where rollNumber=?;");
			stmt.setInt(1, rollno);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				pass=rs.getString(1);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}	
		if(pass.equals(password))
		{
			return "success";
		}
		else return "failure";
	}
}
