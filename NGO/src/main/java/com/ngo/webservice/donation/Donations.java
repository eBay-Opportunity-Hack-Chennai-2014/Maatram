package com.ngo.webservice.donation;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.ngo.database.DatabaseConnection;

@Component
@Path("/donations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Donations {
	@GET  
	@Path("/{param}")  
	public Response getMessage(@PathParam("param") String message) {  
		String output = "Jersey say Hello World!!! : " + message;  
		return Response.status(200).entity(output).build();  
	} 

	@POST
	@Path("/donate")
	@Consumes(MediaType.APPLICATION_JSON)
	public void postDonation(Contribution c){

	}

	@GET
	@Path("/student")
	public Response  getMessage(){
		String output = "";
		//	Build Json from response from sql
		// String output = "Jersey say Hello World!!! : " + ;  
		Connection c=	 (Connection) DatabaseConnection.getInstance().getConnection();
		String selectStudents = "SELECT * FROM students";
		Statement statement;
		JSONArray stud = new JSONArray();
		try {
			statement = c.createStatement();
			ResultSet rs = statement.executeQuery(selectStudents);
			while(rs.next()){
				Students s = new Students();
				s.setName(rs.getString("name"));
				s.setId(rs.getInt("id"));
				s.setDescription(rs.getString("description"));
				s.setEducation(rs.getString("education"));
				s.setMoneyRequired(rs.getInt("moneyRequired"));
				s.setMoneyRaised(rs.getInt("moneyRaised"));			

				stud.put(s.getJSON());	

			}
			System.out.println(stud.toString());
			//	c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(stud.toString());
		return Response.status(200).entity(stud.toString()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	private void makeDonation (Contribution contribution){
		Connection c=	 (Connection) DatabaseConnection.getInstance().getConnection();
		Statement statement;
		try {
			statement = c.createStatement();
			String insertQuery= "	INSERT INTO contributions ('eventId', 'userId', 'amount', 'date', 'email_id', 'address') "
					+ "				VALUES ('"+
					contribution.getEventId()+"', "+
					contribution.getUserId()+", '"+
					contribution.getAmount()+"', '"+
					contribution.getEmail_id()+"', '"+
					contribution.getAddress()+"')";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

}