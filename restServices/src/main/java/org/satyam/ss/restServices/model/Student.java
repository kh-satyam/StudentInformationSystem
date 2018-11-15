package org.satyam.ss.restServices.model;

import java.text.DecimalFormat;

import java.time.LocalDate;
import java.time.Period;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Student {
	private String Name;
	private LocalDate DOB;
	private int rollNumber;
	private double physicsMarks;
	private double chemistryMarks;
	private double mathematicsMarks;
	private int age;
	private double totalMarks;
	private double avgMarks;
	private char grade;

	//ensure model class have no op constructor
	public Student() {
	}
	
	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public Student(String Name, LocalDate DOB, int rollNumber) {
		this.Name = Name;
		this.DOB = DOB;
		this.rollNumber = rollNumber;
	}
	
	public Student(String Name, LocalDate DOB, int rollNumber, 
			double physicsMarks, double chemistryMarks, double mathematicsMarks) {
		this.Name = Name; this.DOB = DOB; this.rollNumber = rollNumber;
		this.physicsMarks = physicsMarks; this.chemistryMarks = chemistryMarks;
		this.mathematicsMarks = mathematicsMarks;
	}

	public String getName() {
		return this.Name;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDOB() {
		return this.DOB;
	}
	
	public int getAge() {
		LocalDate today = LocalDate.now();
		Period p = Period.between(DOB, today);
		this.age = p.getYears();
		return this.age;
	}

	public void setAge(int age) {
		this.age = this.getAge();
	}

	public void setTotalMarks(double totalMarks) {
		this.totalMarks = this.getTotalMarks();
	}

	public void setAvgMarks(double avgMarks) {
		this.avgMarks = this.getAvgMarks();
	}

	public double getAvgMarks() {
		double total = this.getTotalMarks();
		this.avgMarks = Double.valueOf(new DecimalFormat("#.##").format(total / 3.0));
		return this.avgMarks;
	}
	
	public double getTotalMarks() {
		this.totalMarks = this.physicsMarks + this.chemistryMarks + this.mathematicsMarks;
		if (this.totalMarks > 90) this.setGrade('A');
		else if (this.totalMarks <= 90 && this.totalMarks > 80)
			this.setGrade('B');
		else if (this.totalMarks <= 80 && this.totalMarks > 70)
			this.setGrade('C');
		else 
			this.setGrade('D');
		return this.totalMarks;
	}

	public int getRollNumber() {
		return this.rollNumber;
	}

	public double getPhysicsMarks() {
		return this.physicsMarks;
	}

	public double getChemistryMarks() {
		return this.chemistryMarks;
	}

	public double getMathematicsMarks() {
		return this.mathematicsMarks;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public void setDOB(LocalDate dOB) {
		this.DOB = dOB;
		this.age = this.getAge();
	}

	public void setPhysicsMarks(double physicsMarks) {
		this.physicsMarks = physicsMarks;
		this.updateMarks();
	}

	public void setChemistryMarks(double chemistryMarks) {
		this.chemistryMarks = chemistryMarks;
		this.updateMarks();
	}

	public void setMathematicsMarks(double mathematicsMarks) {
		this.mathematicsMarks = mathematicsMarks;
		this.updateMarks();
	}
	
	private void updateMarks() {
		this.totalMarks = this.getTotalMarks();
		this.avgMarks = this.getAvgMarks();
	}

	public String toString() {
		return "Name: " + this.Name + ", Roll Number: " + this.rollNumber 
				+ "DOB: " + this.DOB + ", Age: " + this.getAge()
				+ ", Marks: " + this.physicsMarks + "(Phy.), " 
				+ this.chemistryMarks + "(Chem.), " + this.mathematicsMarks 
				+ "(Maths)." + " Total: " + this.totalMarks 
				+ ", Avg: " + this.avgMarks;
	}

}