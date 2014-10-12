package com.ngo.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;

@Component
@Path("/dashboard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DashBoard {
	
	@GET
	public Response getDashBoardInfo(){
		Connection c = (Connection) DatabaseConnection.getInstance().getConnection();
		String query = "SELECT count(*) FROM event";
		Statement stmt;
		JSONObject stud = new JSONObject();
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			stud.put("pending-events", rs.getInt(1));
			
			query = "select * from event";
			
			rs = stmt.executeQuery(query);
			long required = 0;
			long acquired = 0;
			while(rs.next()){
				acquired += Integer.parseInt(rs.getString("resourceacquired"));
				required += Integer.parseInt(rs.getString("resourceneeded"));
			}
			
			stud.put("total-donations", acquired);
			stud.put("requests", required);
			
			query = "SELECT count(*) FROM people";
			rs = stmt.executeQuery(query);
			rs.next();
			stud.put("new-volunteers", rs.getInt(1));
			System.out.println(stud.toString());
			//	c.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.status(200).entity(null).build();
		}

		System.out.println(stud.toString());
		return Response.status(200).entity(stud.toString()).build();
		
		
	}

}
