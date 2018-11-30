package org.satyam.ss.restServices.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class comment {
int rollno;
String comment;


public comment(int rollno, String comment) {
	super();
	this.rollno = rollno;
	this.comment = comment;
}
public int getRollno() {
	return rollno;
}
public void setRollno(int rollno) {
	this.rollno = rollno;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}


}
