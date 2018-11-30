package org.satyam.ss.restServices.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class comment {
int rollno;
String comment;
String name;

public comment(int rollno, String comment,String name) {
	super();
	this.rollno = rollno;
	this.comment = comment;
	this.name=name;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
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
