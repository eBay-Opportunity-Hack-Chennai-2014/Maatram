/*	Heroes Persist
    Product which helps in organizing, broadcasting, celebrating events
    Copyright (C) 2014  Sai Pranav
    Email: rsaipranav92@gmail.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.lister.sports.webservice;

import javax.ws.rs.core.MediaType;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Sai Pranav
 *
 */
public class WebServiceTest {

	String hostNameAndPort = "10.106.50.32:8080";
	
	@Test
	public void testGame(){
		int id;
		String data = "{\"name\":\"Cricket\",\"category\":\"NotApplicable\",\"noPlayers\":\"10\"}";
		id = addGame(data);
		data = "{\"name\":\"Cricket\",\"category\":\"NotApplicable\",\"noPlayers\":\"15\"}";
		Assert.assertEquals(modifyGame(id, data),id);
		data = "{\"name\":\"Cricket\",\"category\":\"NotAppicable\",\"noPlayers\":\"15\"}";
		Assert.assertEquals(modifyGameFail(id, data),true);
		Assert.assertEquals(deleteGame(id), id);
	}
	
	@Test
	public void testPlayer(){
		//Prior data
		String priorData = "{\"name\":\"ThrowBall\",\"category\":\"NotApplicable\",\"noPlayers\":\"10\"}";
		int gameId = addGame(priorData);
		
		int id;
		String data = "{\"employeeId\":\"1\",\"employeeName\":\"SAI\",\"employeeEmail\":\"asd@test.com\",\"games\":[{\"gameName\":\"Carrom\",\"gameCategory\":\"Singles\"},{\"gameName\":\"Carrom\",\"gameCategory\":\"Doubles\"}]}";
		id = addPlayer(data);
		data = "{\"employeeId\":\"1\",\"employeeName\":\"SAI\",\"employeeEmail\":\"ad@test.com\",\"games\":[{\"gameName\":\"Carrom\",\"gameCategory\":\"Singles\"},{\"gameName\":\"Carrom\",\"gameCategory\":\"Doubles\"}]}";
		Assert.assertEquals(modifyPlayer(id, data), id);
		data = "{\"gameName\":\"ThrowBall\",\"gameCategory\":\"NotApplicable\"}";
		Assert.assertEquals(modifyPlayerAddGames(id, data), id);
		Assert.assertEquals(modifyPlayerAddGamesFail(id, data), true);
		//Assert.assertEquals(modifyPlayerDeleteGames(id, data), id);
		data = "{\"gameName\":\"NoGame\",\"gameCategory\":\"NoGame\"}";
		Assert.assertEquals(modifyPlayerAddGamesFail(id, data), true);
		Assert.assertEquals(deletePlayer(id), id);
		
		//Clear prior data
		deleteGame(gameId);
	}
	
	@Test
	public void testTeam(){
		//Prior data
		String priorData = "{\"name\":\"ThrowBall\",\"category\":\"NotApplicable\",\"noPlayers\":\"10\"}";
		int gameId = addGame(priorData);
		priorData = "{\"employeeId\":\"1\",\"employeeName\":\"SAI\",\"employeeEmail\":\"asd@test.com\",\"games\":[{\"gameName\":\"Carrom\",\"gameCategory\":\"Singles\"},{\"gameName\":\"Carrom\",\"gameCategory\":\"Doubles\"}]}";
		int playerId = addPlayer(priorData);
		
		int id;
		String data = "{\"name\":\"testTeam\",\"rating\":\"1\",\"players\":[\"1\",\"1210\"],\"game\":{\"gameName\":\"Carrom\",\"gameCategory\":\"Singles\"}}";
		id = addTeam(data);
		data = "{\"name\":\"test\",\"rating\":\"2\",\"players\":[\"1\",\"1210\"],\"game\":{\"gameName\":\"Carrom\",\"gameCategory\":\"NotApplicable\"}}";
		Assert.assertEquals(modifyTeam(id, data), id);
		data = "{\"name\":\"test\",\"rating\":\"2\",\"players\":[\"1\",\"1210\"],\"game\":{\"gameName\":\"Cricket\",\"gameCategory\":\"NotApplicable\"}}";
		Assert.assertEquals(modifyTeamFail(id, data), true);
		data = "1215";
		Assert.assertEquals(modifyTeamAddPlayer(id, data), id);
		//Duplicate
		Assert.assertEquals(modifyTeamAddPlayerFail(id, data), true);
		//Not available
		data = "0";
		Assert.assertEquals(modifyTeamAddPlayerFail(id, data), true);
		data = "1215";
		Assert.assertEquals(modifyTeamDeletePlayer(id, data), id);
		//Not available
		Assert.assertEquals(modifyTeamDeletePlayerFail(id, data), true);
		Assert.assertEquals(deleteTeam(id), id);
		
		//Clear prior data
		deleteGame(gameId);
		deletePlayer(playerId);
	}
	
	//@Test
	public void testMatch(){
		int id;
		String data = "{\"game\":{\"gameName\":\"Carrom\",\"gameCategory\":\"NotApplicable\"},\"teams\":[{\"testTeamA\"}],\"round\":\"2\",\"scheduledTime\":\"1234\",\"location\":\"daffidole\",\"status\":\"asdf\",\"winner\":\"qwert\",\"score\":\"asdzxc\",\"show\":\"true\"}";
		id = addMatch(data);
		data = "{\"game\":{\"gameName\":\"Carrom\",\"gameCategory\":\"NotApplicable\"},\"teams\":[{\"testTeamA\"},{\"testTeamB\"}],\"round\":\"5\",\"scheduledTime\":\"1234\",\"location\":\"de\",\"status\":\"asdf\",\"winner\":\"qwert\",\"score\":\"asdzxc\",\"show\":\"true\"}";
		Assert.assertEquals(modifyMatch(id, data), id);
		data = "{\"game\":{\"gameName\":\"Carrs\",\"gameCategory\":\"NotApplicable\"},\"teams\":[{\"testTeamA\"},{\"testTeamB\"}],\"round\":\"5\",\"scheduledTime\":\"1234\",\"location\":\"de\",\"status\":\"asdf\",\"winner\":\"qwert\",\"score\":\"asdzxc\",\"show\":\"true\"}";
		Assert.assertEquals(modifyMatchFail(id, data), true);
		data = "testTeamC";
		Assert.assertEquals(modifyMatchAddTeam(id, data), id);
		//House Full
		Assert.assertEquals(modifyMatchAddTeamFail(id, data), true);
		//Not available
		data = "noTeam";
		Assert.assertEquals(modifyMatchAddTeamFail(id, data), true);
		data = "testTeamC";
		Assert.assertEquals(modifyMatchDeleteTeam(id, data), id);
		//Not available
		Assert.assertEquals(modifyMatchDeleteTeamFail(id, data), true);
		Assert.assertEquals(deleteMatch(id), id);
	}
	
	public int addGame(String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/games");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int modifyGame(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/games/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyGameFail(int id, String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/games/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int deleteGame(int id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/games/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int addPlayer(String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int modifyPlayer(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int modifyPlayerAddGames(int id, String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players/"+id+"/games");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyPlayerAddGamesFail(int id, String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players/"+id+"/games");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int modifyPlayerDeleteGames(int id, String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players/"+id+"/games");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int deletePlayer(int id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/players/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int addTeam(String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int modifyTeam(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyTeamFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int modifyTeamAddPlayer(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id+"/players/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyTeamAddPlayerFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id+"/players/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int modifyTeamDeletePlayer(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id+"/players/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyTeamDeletePlayerFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id+"/players/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int deleteTeam(int id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/teams/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int addMatch(String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches");
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public int modifyMatch(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyMatchFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class,data);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int modifyMatchAddTeam(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id+"/teams/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyMatchAddTeamFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id+"/teams/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int modifyMatchDeleteTeam(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id+"/teams/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
	public boolean modifyMatchDeleteTeamFail(int id,String data){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id+"/teams/"+data);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
			return true;
		}
		return false;
	}
	
	public int deleteMatch(int id){
		Client client = Client.create();
		WebResource webResource = client.resource("http://"+hostNameAndPort+"/Sports/api/matches/"+id);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
		if(response.getStatus() != 200){
			System.out.println(response.getEntity(String.class));
		}
		return Integer.parseInt(response.getEntity(String.class));
	}
	
}
