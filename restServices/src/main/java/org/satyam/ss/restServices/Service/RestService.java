package org.satyam.ss.restServices.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.satyam.ss.restServices.model.Student;

public class RestService {
	private Connection connection;
	public RestService(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/studentproject?autoReconnect=true&useSSL=false","root","root");
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
			String rollno=obj.getRollNumber();
			LocalDate dob=obj.getDOB();
			Date date=new Date(dob.getYear(),dob.getMonthValue(),dob.getDayOfMonth());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			double phy=obj.getPhysicsMarks();
			double chem=obj.getMathematicsMarks();
			double maths=obj.getChemistryMarks();
			PreparedStatement stmt=connection.prepareStatement("insert into student values(?,?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setDate(2, sqlDate);
			stmt.setString(3, rollno);
			stmt.setDouble(4, phy);
			stmt.setDouble(4, chem);
			stmt.setDouble(4, maths);
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
}
