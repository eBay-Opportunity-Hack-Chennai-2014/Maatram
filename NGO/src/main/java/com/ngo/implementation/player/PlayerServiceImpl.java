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
package com.ngo.implementation.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.player.PlayerDao;
import com.ngo.interfaces.player.PlayerService;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Player;
import com.ngo.model.Team;
import com.ngo.webservice.game.GameFormWithoutNumberOfPlayers;
import com.ngo.webservice.player.PlayerForm;

/**
 * @author Sai Pranav
 *
 */

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	/*Player functions*/
	/**
	 * Add player with (or) without game, but if no such player exists.
	 */
	public int addPlayer(PlayerForm playerForm){
		Player player = utilityDao.getPlayer(playerForm.getEmployeeId());
		if(player != null){
			throw new NGOException("Player already exists");
		}
		if(playerForm.getGames().size()==0){
			player = new Player();
			player.setEmployeeId(playerForm.getEmployeeId());
			player.setEmployeeName(playerForm.getEmployeeName());
			player.setEmployeeEmail(playerForm.getEmployeeEmail());
			return playerDao.addPlayer(player);
		}
		if(playerForm.getGames().size()>=0){
			player = new Player();
			player.setEmployeeId(playerForm.getEmployeeId());
			player.setEmployeeName(playerForm.getEmployeeName());
			player.setEmployeeEmail(playerForm.getEmployeeEmail());
			int id = playerDao.addPlayer(player);
			player = utilityDao.getPlayer(id);
			for(GameFormWithoutNumberOfPlayers tempGame: playerForm.getGames()){
				Game game = utilityDao.getGame(tempGame.getGameName(), tempGame.getGameCategory());
				if(game == null){
					throw new NGOException("No such Game");
				}
				playerDao.modifyPlayerAddGame(player, game);
			}
		}
		return player.getEmployeeId();
	}

	public int modifyPlayer(int employeeId, PlayerForm playerForm){
		Player player = utilityDao.getPlayer(employeeId);
		if(player == null){
			throw new NGOException("Player does not exist");
		}
		player.setEmployeeName(playerForm.getEmployeeName());
		player.setEmployeeEmail(playerForm.getEmployeeEmail());
		return playerDao.modifyPlayer(player);
	}

	public int modifyPlayerAddGame(int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers){
		Player player = utilityDao.getPlayer(employeeId);
		if(player == null){
			throw new NGOException("Player does not exist");
		}
		
		Game game = utilityDao.getGame(gameFormWithoutNumberOfPlayers.getGameName(), gameFormWithoutNumberOfPlayers.getGameCategory());
		if(game == null){
			throw new NGOException("No such Game");
		}
		
		boolean checkGameFlag = false;
		List<Game> games = player.getGames();
		for(Game checkGame: games){
			if(checkGame.getId() == game.getId()){
				checkGameFlag = true;
			}
		}
		if(checkGameFlag == true){
			throw new NGOException("Player already playing this Game");
		}
		
		return playerDao.modifyPlayerAddGame(player, game);
	}

	public int modifyPlayerDeleteGame(int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers){
		Player player = utilityDao.getPlayer(employeeId);
		if(player == null){
			throw new NGOException("Player does not exist");
		}
		
		Game game = utilityDao.getGame(gameFormWithoutNumberOfPlayers.getGameName(), gameFormWithoutNumberOfPlayers.getGameCategory());
		if(game == null){
			throw new NGOException("No such Game");
		}
		
		boolean checkGameFlag = false;
		List<Game> games = player.getGames();
		for(Game checkGame: games){
			if(checkGame.getId() == game.getId()){
				checkGameFlag = true;
			}
		}
		if(checkGameFlag == false){
			throw new NGOException("Player is not playing this Game");
		}
		
		return playerDao.modifyPlayerDeleteGame(player, game);
	}

	public int deletePlayer(int employeeId){
		Player player = utilityDao.getPlayer(employeeId);
		if(player == null){
			throw new NGOException("Player does not exist");
		}
		return playerDao.deletePlayer(player);
	}
	
	public int modifyPlayerFromAdmin(int employeeId, PlayerForm playerForm){
		Player player = utilityDao.getPlayer(playerForm.getEmployeeId());
		if(player == null){
			throw new NGOException("Player does not exists");
		}
		if(playerForm.getGames().size()==0){
			player.setEmployeeName(playerForm.getEmployeeName());
			player.setEmployeeEmail(playerForm.getEmployeeEmail());
			return playerDao.modifyPlayer(player);
		}
		if(playerForm.getGames().size()>=0){
			player.setEmployeeName(playerForm.getEmployeeName());
			player.setEmployeeEmail(playerForm.getEmployeeEmail());
			int id = playerDao.modifyPlayer(player);
			player = utilityDao.getPlayer(id);
			List<Game> games = new ArrayList<Game>(1);
			for(GameFormWithoutNumberOfPlayers tempGame: playerForm.getGames()){
				Game game = utilityDao.getGame(tempGame.getGameName(), tempGame.getGameCategory());
				if(game == null){
					throw new NGOException("No such Game");
				}
				games.add(game);
			}
			playerDao.modifyPlayerReloadGames(player, games);
		}
		return player.getEmployeeId();
	}
	
	public List<Player> getPlayers(){
		return playerDao.getPlayers();
	}
	
	public List<String> getPlayerEmails(int teamId){
		Team team = utilityDao.getTeam(teamId);
		if(team!=null){
			List<String> emails = new ArrayList<String>();
			List<Player> players = team.getPlayers();
			for(Player player : players){
				emails.add(player.getEmployeeEmail());
			}
			return emails;
		}
		else{
			throw new NGOException("Team does not exist");
		}
	}

	public List<Player> getPlayersByTeam(int teamId){
		Team team = utilityDao.getTeam(teamId);
		if(team!=null){
			return playerDao.getPlayersByTeam(team);
		}
		else{
			throw new NGOException("Team does not exist");
		}
	}
	
	public List<Player> getPlayersByGame(String gameName, String gameCategory){
		Game game = utilityDao.getGame(gameName, gameCategory);
		if(game!=null){
			return playerDao.getPlayersByGame(game);
		}
		else{
			throw new NGOException("Game does not exist");
		}
	}

}
