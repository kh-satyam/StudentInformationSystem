package org.satyam.ss.restServices.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class simple {
	public int abc;
	public simple() {
		
	}
	public simple(int a) {
		abc=a;
	}
	public int getAbc() {
		return abc;
	}
	public void setAbc(int abc) {
		this.abc = abc;
	}
}
