package org.satyam.ss.restServices.Service;
import java.util.Comparator;

import org.satyam.ss.restServices.model.Student;

public class StudentComparator implements Comparator<Student> {
	public int compare(Student s1,Student s2) {
		return Integer.compare(s1.getRollNumber(), s2.getRollNumber());
	}
}
