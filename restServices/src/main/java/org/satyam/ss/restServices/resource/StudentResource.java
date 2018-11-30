package org.satyam.ss.restServices.resource;


import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.satyam.ss.restServices.Service.RestService;
import org.satyam.ss.restServices.model.Student;
import org.satyam.ss.restServices.model.comment;

@Path("student")
public class StudentResource {
	private RestService service=new RestService();
	@GET
	@Path("get/{rollno}")
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("rollno") int roll) {
		Student obj=service.getStudent(roll);
		if(obj==null) {
			return null;
		}
		return obj;
	}
	
	// return 0 on if data already exist
	// return 1 on success 
	// return 2 on server side error
	@POST
	@Path("add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public int uploadFileAndJSON(FormDataMultiPart form) { 
		int res=0;
		try {
			FormDataBodyPart filePart = form.getField("file");
			InputStream is = filePart.getValueAs(InputStream.class);
			ContentDisposition cdh =  filePart.getContentDisposition();
			String fname=cdh.getFileName();
			System.out.println(fname);
			FormDataBodyPart jsonPart=form.getField("json");
			jsonPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
			Student obj=jsonPart.getValueAs(Student.class);
			System.out.println(jsonPart);
			// returns 0 if student already exists
			if ( service.getStudent(obj.getRollNumber()) != null ) return 0;
			
			System.out.println(obj);
			
			String path="/home/utkarsh/Desktop/pic/"+obj.getRollNumber()+".jpg";
			// service will return 1 on successful addition
			res = service.addStudentWithImage(obj, is, path);
		}catch(Exception e){
			System.out.println(e);
		}
	    return res;
	}
	
	@GET
	@Path("download/image/{rollno}")//  /home/utkarsh/Desktop
	@Produces({"image/png", "image/jpg", "image/gif"})
	public Response downloadImageFile(@PathParam("rollno") String roll) {
		String path="/home/utkarsh/Desktop/pic/"+roll+".jpg";
		File file = new File(path);
		ResponseBuilder responseBuilder = Response.ok((Object) file);
		roll=roll+".png";
		responseBuilder.header("Content-Disposition",
                "attachment; filename=roll");
        return responseBuilder.build();
	}
	@PUT
	@Path("update/{rollno}/{parameter}/{value}")
	public int updateStudent(@PathParam("rollno") int roll,
								@PathParam("parameter") String key,
								@PathParam("value") String value){
		int res=0;
		//System.out.println(key);
		if(key.compareTo("name")==0) {
			res=service.updateName(roll, value);
			//System.out.println(key);
		}
		if(key.compareTo("mathematicsMarks")==0) {
			double marks=Double.valueOf(value);
			res=service.updateMathematicsMarks(roll, marks);
		}
		if(key.compareTo("physicsMarks")==0) {
			double marks=Double.valueOf(value);
			res=service.updatePhysicsMarks(roll, marks);
		}
		if(key.compareTo("chemistryMarks")==0) {
			double marks=Double.valueOf(value);
			res=service.updateChemistryMarks(roll, marks);
		}
		if(key.compareTo("DOB")==0) {
			try {
				LocalDate localDate = LocalDate.parse(value);
				res=service.updateDOB(roll,localDate);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return res;
	}
	@GET
	@Path("getAll/{name}")
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Student> getStudent(@PathParam("name") String key) {
		ArrayList<Student> res=new ArrayList<Student>();
		if(key.compareTo("name")==0) {
			res=service.getAllByName();
		}
		if(key.compareTo("totalMarks")==0) {
			res=service.getAllByTotalMarks();
		}
		if(key.compareTo("grade")==0) {
			res=service.getAllByGrade();
		}
		return res;
	}
	@GET
	@Path("getAllRange/{from}/{to}/{key}")
	//@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Student> getStudent(@PathParam("from") int from,@PathParam("to") int to,@PathParam("key") String key) {
		ArrayList<Student> res=new ArrayList<Student>();
		res=service.getAllByNameRange(from, to, key);
		return res;
	}
	@GET
	@Path("comment/{cmt}/{roll}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<comment> g(@PathParam("cmt")String cmt,@PathParam("roll")int rollno)
	{
		ArrayList<comment> res=new ArrayList<comment>();
		
		System.out.println(cmt);
		res=service.getAndPostComments(cmt,rollno);
		for(int i=0;i<res.size();i++)
		{
			System.out.println(res.get(i).getRollno()+" "+res.get(i).getComment());
		}
		return res;
	}
	@GET
	@Path("login/{username}/{pwd}")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@PathParam("username")int a,@PathParam("pwd")String b)
	{
		return service.login(a,b);
	}

}
