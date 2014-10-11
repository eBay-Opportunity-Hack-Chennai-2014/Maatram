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
package com.lister.sports.implementation;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import com.ngo.interfaces.game.GameService;
import com.ngo.interfaces.match.MatchService;
import com.ngo.interfaces.player.PlayerService;
import com.ngo.interfaces.team.TeamService;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.webservice.game.GameForm;
import com.ngo.webservice.game.GameFormWithoutNumberOfPlayers;
import com.ngo.webservice.match.MatchForm;
import com.ngo.webservice.player.PlayerForm;
import com.ngo.webservice.team.TeamForm;

/**
 * @author aarti_rao
 *
 */
@ContextConfiguration("classpath:spring/application-config.xml")
@Transactional
public class TestServices extends AbstractTestNGSpringContextTests {

	@Autowired
	GameService gameService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	UtilityDao utilityDao;
	
	GameForm gameForm;
	
	GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers;
	
	MatchForm matchForm;
	
	TeamForm teamForm;
	
	PlayerForm playerForm;
	
	
	@BeforeClass
	public void beforeClass(){
		//clear all data in database
	}
	
	//Game Functions
	@Test
	public void testAddModifyDeleteGame() {
		try{
			
			gameForm.setName("Throwball");
			gameForm.setCategory("NotApplicable");
			gameForm.setMinNoPlayers(1);
			gameForm.setMaxNoPlayers(2);
			int id = gameService.addGame(gameForm);
			Assert.assertNotNull(utilityDao.getGame(id));
			Assert.assertEquals(gameService.modifyGame(id, gameForm),id);
			Assert.assertEquals(gameService.deleteGame(id),id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Player Functions
    @Test(dependsOnMethods={"testAddModifyDeleteGame"})
	public void testAddModifyDeletePlayer() {
		try{
			
			List<GameFormWithoutNumberOfPlayers> gameList = new ArrayList<GameFormWithoutNumberOfPlayers>();
			gameService.addGame(gameForm);
			gameFormWithoutNumberOfPlayers.setGameName("ThrowBall");
			gameFormWithoutNumberOfPlayers.setGameCategory("Not Applicable");
			
			
			gameList.add(gameFormWithoutNumberOfPlayers);
			
			playerForm.setEmployeeId(1215);
			playerForm.setEmployeeName("sai pranav");
			playerForm.setEmployeeEmail("player1@gmail.com");
			playerForm.setGames(gameList);
			int id = playerService.addPlayer(playerForm);
			playerForm.setEmployeeId(1216);
			playerForm.setEmployeeName("venkatr");
			playerForm.setEmployeeEmail("player2@gmail.com");
			Assert.assertNotNull(utilityDao.getPlayer(id));
			Assert.assertEquals(playerService.modifyPlayer(id, playerForm),id);
			Assert.assertEquals(playerService.deletePlayer(id),id);
			
			gameService.deleteGame(2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods={"testAddModifyDeletePlayer"})
	public void testModifyPlayerAddGamesDeleteGames() {
		try{
			gameService.addGame(gameForm);
			int id = playerService.addPlayer(playerForm);
			gameFormWithoutNumberOfPlayers.setGameName("Chess");
			gameFormWithoutNumberOfPlayers.setGameCategory("Single");
			Assert.assertNotNull(utilityDao.getPlayer(id));
			Assert.assertEquals(playerService.modifyPlayerAddGame(1216, gameFormWithoutNumberOfPlayers),id);
			Assert.assertEquals(playerService.modifyPlayerDeleteGame(1216, gameFormWithoutNumberOfPlayers),id);
			playerService.deletePlayer(1216);
			gameService.deleteGame(3);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// Team functions
	@Test(dependsOnMethods={"testModifyPlayerAddGamesDeleteGames"})
	public void testAddModifyDeleteTeam() {
		try{
			
			gameService.addGame(gameForm);
			
			int player1 = playerService.addPlayer(playerForm);
			playerForm.setEmployeeId(1216);
			playerForm.setEmployeeName("venkatr");
			playerForm.setEmployeeEmail("player2@gmail.com");
			int player2 = playerService.addPlayer(playerForm);
			List<Integer> playerList = new ArrayList<Integer>();
			teamForm.setName("team1");
			teamForm.setGame(gameFormWithoutNumberOfPlayers);
			teamForm.setPlayers(playerList);
			teamForm.setRating("");
			teamForm.setRound(0);
			teamForm.setScore("");
			teamForm.setShow(true);
			int id = teamService.addTeam("10.106.50.32", teamForm);
			Assert.assertNotNull(utilityDao.getTeam(id));
			teamForm.setName("team2");
			teamForm.setGame(gameFormWithoutNumberOfPlayers);
			teamForm.setPlayers(playerList);
			teamForm.setRating("");
			teamForm.setRound(0);
			teamForm.setScore("");
			teamForm.setShow(true);
			Assert.assertEquals(teamService.modifyTeam(id, teamForm),id);
			Assert.assertEquals(teamService.deleteTeam(id),id);
			playerService.deletePlayer(player1);
			playerService.deletePlayer(player2);
			gameService.deleteGame(4);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods={"testAddModifyDeleteTeam"})
	public void testModifyTeamAddDeletePlayers() {
		try{
			
			gameService.addGame(gameForm);
			/*int player1 = playerService.addPlayer(playerForm);
			playerForm.setEmployeeId(1215);
			playerForm.setEmployeeName("saip");
			playerForm.setEmployeeEmail("player1@gmail.com");
			int player2 = playerService.addPlayer(playerForm);*/
			int id = teamService.addTeam("10.106.50.32", teamForm);
			Assert.assertEquals(teamService.modifyTeamDeletePlayer(id, "1215"),id);
			Assert.assertEquals(teamService.modifyTeamAddPlayer(id, "1215"),id);
			teamService.deleteTeam(2);
			/*service.deletePlayer(1212);
			service.deletePlayer(1215);*/
			gameService.deleteGame(5);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*// Match functions
//	@Test(dependsOnMethods={"testModifyTeamAddDeletePlayers"})
	public void testAddModifyDeleteMatch() {
		try{
			gameService.addGame("ThrowBall", "NotApplicable", 2);
			service.addPlayer(1215, "sai pranav", "player1@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1212, "param", "player2@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1211, "sriraman", "player3@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1210, "akashya", "player4@gmail.com", "ThrowBall", "NotApplicable");
			service.addTeam("team1", 0, "1215-1212", "ThrowBall", "NotApplicable");
			service.addTeam("team2", 0, "1211-1210", "ThrowBall", "NotApplicable");
			service.addMatch(1, "12 00", "tulip", "not yet started", " ", " ", false, "team1-team2", "ThrowBall", "NotApplicable");
			service.modifyMatch(1, 2, "9 00", "orchid", "started", " ", " ", false);
			service.deleteMatch(1);
			service.deleteTeam(3);
			service.deleteTeam(4);
			service.deletePlayer(1212);
			service.deletePlayer(1215);
			service.deletePlayer(1211);
			service.deletePlayer(1210);
			gameService.deleteGame(6);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//	@Test(dependsOnMethods={"testAddModifyDeleteMatch"})
	public void testModifyMatchAddDeleteTeams() {
		try{
			gameService.addGame("ThrowBall", "NotApplicable", 2);
			service.addPlayer(1215, "sai pranav", "player1@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1212, "param", "player2@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1211, "sriraman", "player3@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1210, "akashya", "player4@gmail.com", "ThrowBall", "NotApplicable");
			service.addTeam("team1", 0, "1215-1212", "ThrowBall", "NotApplicable");
			service.addTeam("team2", 0, "1211-1210", "ThrowBall", "NotApplicable");
			service.addMatch(1, "12 00", "tulip", "not yet started", "-", "-", false, "team1-team2", "ThrowBall", "NotApplicable");
			service.modifyMatchDeleteTeams(2, "team2");
			service.modifyMatchAddTeams(2, "team2");
			service.deleteMatch(2);
			service.deleteTeam(5);
			service.deleteTeam(6);
			service.deletePlayer(1212);
			service.deletePlayer(1215);
			service.deletePlayer(1211);
			service.deletePlayer(1210);
			gameService.deleteGame(7);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testCheckMatchFullScores() {
		try{
			gameService.addGame("ThrowBall", "NotApplicable", 6);
			service.addPlayer(1215, "sai pranav", "player1@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1212, "param", "player2@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1211, "sriraman", "player3@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1210, "akashya", "player4@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1209, "dwarak", "player5@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1208, "pshyco", "player6@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1207, "baski", "player7@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1206, "ram", "player8@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1205, "harini", "player9@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1204, "fabrice", "player10@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1203, "av", "player11@gmail.com", "ThrowBall", "NotApplicable");
			service.addPlayer(1202, "ganesh", "player12@gmail.com", "ThrowBall", "NotApplicable");
			service.addTeam("team1", 0, "1215,1212,1211,1210,1209,1208", "ThrowBall", "NotApplicable");
			service.addTeam("team2", 0, "1207,1206,1205,1204,1203,1202", "ThrowBall", "NotApplicable");
			service.addMatch(1, "12 00", "tulip", "not yet started", "-", "-", false, "team1,team2", "ThrowBall", "NotApplicable");
			service.checkMatch(1);
			service.deleteMatch(3);
			service.deleteTeam(7);
			service.deleteTeam(8);
			service.deletePlayer(1212);
			service.deletePlayer(1215);
			service.deletePlayer(1211);
			service.deletePlayer(1210);
			gameService.deleteGame(8);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckMatchFullScores1() {
		try{
			gameService.addGame("Carrom", "Singles", 1);
			service.addTeam("team1", 0, "1215", "Carrom", "Singles");
			service.addTeam("team2", 0, "1211", "Carrom", "Singles");
			service.addMatch(1, "12 00", "tulip", "not yet started", "-", "-", false, "team1,team2", "Carrom", "Singles");
			service.checkMatch(2);
			service.deleteMatch(3);
			service.deleteTeam(7);
			service.deleteTeam(8);
			service.deletePlayer(1212);
			service.deletePlayer(1215);
			service.deletePlayer(1211);
			service.deletePlayer(1210);
			gameService.deleteGame(8);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCheckMatchFullScores2() {
		try{
			gameService.addGame("Carrom", "Doubles", 2);
			service.addTeam("team1", 0, "1215,1212", "Carrom", "Doubles");
			service.addTeam("team2", 0, "1211,1210", "Carrom", "Doubles");
			service.addMatch(1, "12 00", "tulip", "not yet started", "-", "-", false, "team1,team2", "Carrom", "Doubles");
			service.checkMatch(3);
			service.deleteMatch(3);
			service.deleteTeam(7);
			service.deleteTeam(8);
			service.deletePlayer(1212);
			service.deletePlayer(1215);
			service.deletePlayer(1211);
			service.deletePlayer(1210);
			gameService.deleteGame(8);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
}
