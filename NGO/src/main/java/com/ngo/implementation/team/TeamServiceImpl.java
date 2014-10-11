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
package com.ngo.implementation.team;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.player.PlayerDao;
import com.ngo.interfaces.registration.RegistrationDao;
import com.ngo.interfaces.team.TeamDao;
import com.ngo.interfaces.team.TeamService;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Player;
import com.ngo.model.Registration;
import com.ngo.model.Team;
import com.ngo.webservice.team.SimpleTeamForm;
import com.ngo.webservice.team.TeamForm;

/**
 * @author Sai Pranav
 *
 */

@Component
@Transactional
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	TeamDao teamDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	RegistrationDao registrationDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	public int addTeam(String ipAddress, TeamForm teamForm){
		Game game = utilityDao.getGame(teamForm.getGame().getGameName(), teamForm.getGame().getGameCategory());
		if(game!=null){
			Registration registration;
			if(ipAddress.trim().isEmpty()){
				registration = null;
			}
			else{
				registration = utilityDao.getRegistration(ipAddress, game);
			}
			if(registration == null){
				Team team = utilityDao.getTeam(teamForm.getName(), game);
				if(team==null){
					List<Player> playersList = utilityDao.getPlayerList(teamForm.getPlayers());
					if(playersList.size() == teamForm.getPlayers().size() && (playersList.size() >= game.getMinNoPlayers()) && (playersList.size() <= game.getMaxNoPlayers()) ){
						List<Team> teams = teamDao.getTeamsByGame(game,false);
						if(utilityDao.checkPlayers(teams,playersList)==true){
							for(Player player: playersList){
								boolean checkGameFlag = false;
								List<Game> games = player.getGames();
								for(Game checkGame: games){
									if(checkGame.getId() == game.getId()){
										checkGameFlag = true;
									}
								}
								if(checkGameFlag == false){
									playerDao.modifyPlayerAddGame(player, game);
								}
							}
							team = new Team();
							team.setGame(game);
							team.setName(teamForm.getName());
							team.setRating(teamForm.getRating());
							team.setPlayers(playersList);
							int teamId = teamDao.addTeam(team);
							//add a record in registrations table with the ip address
							if(teamId > 0){
								registration = new Registration();
								registration.setIpAddress(ipAddress);
								registration.setTimestamp(new Timestamp(new Date().getTime()));
								registration.setGame(game);
								registration.setTeam(team);
								registrationDao.addRegistration(registration);
								return teamId;
							}
						}
						else{
							throw new NGOException("Player(s) is playing same game for another team");
						}
					}
					else{
						throw new NGOException("Number of players mismatch with game's number of players<br>Minimum number of players "+game.getMinNoPlayers()+"<br>Maximum number of players "+game.getMaxNoPlayers());
					}
				}
				else{
					throw new NGOException("Someone else took the same team name, take another one");
				}
			}
			else{
				throw new NGOException("HEY! You are entangled!<br>You have already registered a team for same game from this computer<br>Contact Event organiser with a genuine reason");
			}
		}
		else{
			throw new NGOException("No such Game");
		}
		return -1;
	}

	public int modifyTeam(int id, TeamForm teamForm){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			return teamDao.modifyTeam(team, teamForm.getName(), teamForm.getRating(), teamForm.getScore(), teamForm.getRound());
		}
		else{
			throw new NGOException("No such team");
		}
	}
	
	public int modifyTeam(int id, SimpleTeamForm teamFormSimple){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			return teamDao.modifyTeam(team, teamFormSimple.getName(), teamFormSimple.getScore(), teamFormSimple.getRound());
		}
		else{
			throw new NGOException("No such team");
		}
	}

	public int modifyTeamAddPlayer(int id, String employeeId){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			Player player = utilityDao.getPlayer(Integer.parseInt(employeeId));
			List<Player> playersList = new ArrayList<Player>(1);
			playersList.add(player);
			if(utilityDao.checkPlayers(teamDao.getTeamsByGame(team.getGame(),false), playersList)){
				return teamDao.modifyTeamAddPlayer(team, player);
			}
			else{
				throw new NGOException("Player is playing the same game in another team");
			}
		}
		else{
			throw new NGOException("No such team");
		}
	}

	public int modifyTeamDeletePlayer(int id, String employeeId){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			Player player = utilityDao.getPlayer(Integer.parseInt(employeeId));
			if(player==null){
				throw new NGOException("No such Player");
			}
			if(!team.getPlayers().contains(player)){
				throw new NGOException("No such player in this team");
			}
			List<Player> playersList = new ArrayList<Player>(1);
			playersList.add(player);
			return teamDao.modifyTeamDeletePlayer(team, player);
		}
		else{
			throw new NGOException("No such team");
		}
	}

	public int deleteTeam(int id){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			return teamDao.deleteTeam(team);
		}
		else{
			throw new NGOException("No such team");
		}
	}
	
	public int modifyTeamShow(int id, boolean toShow){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			return teamDao.modifyShow(team, toShow);
		}
		else{
			throw new NGOException("No such team");
		}
	}
	
	public int modifyTeamFromAdmin(int id, TeamForm teamForm){
		Team team = utilityDao.getTeam(id);
		if(team!=null){
			List<Player> playersList = utilityDao.getPlayerList(teamForm.getPlayers());
			if(playersList.size() == teamForm.getPlayers().size()){
				List<Team> teams = teamDao.getTeamsByGame(team.getGame(),false);
				teams.remove(team);
				if(utilityDao.checkTeamName(teams, teamForm.getName())==true){
					boolean numberOfPlayersCheck = ( (playersList.size() >= team.getGame().getMinNoPlayers()) && (playersList.size() <= team.getGame().getMaxNoPlayers()));
					if(numberOfPlayersCheck){
						if(utilityDao.checkPlayers(teams,playersList)==true){
							return teamDao.modifyTeamFromAdmin(team, teamForm.getName(), teamForm.getRating(), teamForm.getScore(), teamForm.getRound(), playersList);						
						}
						else{
							throw new NGOException("Player(s) is playing the same game for another team");
						}
					}
					else{
						throw new NGOException("Number of players mismatch with game's number of players<br>Minimum number of players "+team.getGame().getMinNoPlayers()+"<br>Maximum number of players "+team.getGame().getMaxNoPlayers());
					}
				}
				else{
					throw new NGOException("Someone else took the same team name");
				}
			}
			else{
				throw new NGOException("Player(s) does not exists");
			}
		}
		else{
			throw new NGOException("No such Team");
		}
	}
	
	public List<Team> getTeamsByGame(String gameName, String gameCategory, boolean showCriteria){
		Game game = utilityDao.getGame(gameName, gameCategory);
		if(game!=null){
			return teamDao.getTeamsByGame(game,showCriteria);
		}
		else{
			throw new NGOException("Game does not exists");
		}
	}
	
	public List<Team> getTeamsByMatch(int matchId){
		Match match = utilityDao.getMatch(matchId);
		if(match!=null){
			List<Team> teams = teamDao.getTeamsByMatch(match);
			return teams;
		}
		else{
			throw new NGOException("Match does not exists");
		}
	}
	
	public Team getTeam(int teamId){
		Team team = utilityDao.getTeam(teamId);
		if(team!=null){
			team.getGame();
			if(team.getPlayers().size() >= 0){
				List<Player> players = team.getPlayers();
				for(Player player : players){
					player.getGames().size();
				}
			}
			return team;
		}
		else{
			throw new NGOException("No such team");
		}
	}

}
