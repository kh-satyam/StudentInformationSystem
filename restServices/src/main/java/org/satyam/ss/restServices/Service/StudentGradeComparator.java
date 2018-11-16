package org.satyam.ss.restServices.Service;
import java.util.Comparator;

import org.satyam.ss.restServices.model.Student;

public class StudentGradeComparator implements Comparator<Student> {
	public int compare(Student s1,Student s2) {
		return -1*Character.compare(s1.getGrade(), s2.getGrade());
	}
}
