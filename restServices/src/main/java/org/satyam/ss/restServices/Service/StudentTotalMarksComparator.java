package org.satyam.ss.restServices.Service;
import java.util.Comparator;

import org.satyam.ss.restServices.model.Student;

public class StudentTotalMarksComparator implements Comparator<Student> {
	public int compare(Student s1,Student s2) {
		return -1*Double.compare(s1.getTotalMarks(), s2.getTotalMarks());
	}
}
