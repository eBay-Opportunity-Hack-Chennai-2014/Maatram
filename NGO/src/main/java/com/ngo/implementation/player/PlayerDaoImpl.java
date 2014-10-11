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

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.SportsException;
import com.ngo.interfaces.player.PlayerDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Player;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class PlayerDaoImpl implements PlayerDao {
	
	@Autowired
	UtilityDao utilityDao;

	public int addPlayer(Player player) {
		Session session = utilityDao.getSession();
		try {
			session.save(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return player.getEmployeeId();
	}
	
	public int modifyPlayer(Player player) {
		Session session = utilityDao.getSession();
		try{
			session.update(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return player.getEmployeeId();
	}

	public int modifyPlayerAddGame(Player player, Game game) {
		Session session = utilityDao.getSession();
		try{
			player.getGames().add(game);
			session.update(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return player.getEmployeeId();
	}

	public int modifyPlayerDeleteGame(Player player, Game game) {
		Session session = utilityDao.getSession();
		try{
			player.getGames().remove(game);
			session.update(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return player.getEmployeeId();
	}
	
	public void modifyPlayerReloadGames(Player player, List<Game> games){
		Session session = utilityDao.getSession();
		try{
			player.setGames(games);
			session.update(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
	}

	public int deletePlayer(Player player) {
		Session session = utilityDao.getSession();
		try{
			session.delete(player);
		}catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return player.getEmployeeId();
	}
	
	/*public int modifyPlayerFromAdmin(int employeeId, String employeeName, String employeeEmail, String games) throws SportsException {
		You need to rollback the transaction if something fails so all done in same method.
		Session session = utilityDao.getSession();
		Player player;
		try{
			player = utilityDao.getPlayer(employeeId);
			String[] gamesArray = null;
			if(games.contains(",")){
				gamesArray  = games.split(",");
			}else{
				gamesArray = new String[1];
				gamesArray[0] = games;
			}
			if(player==null){
				player = new Player();
				player.setEmployeeId(employeeId);
			}
			player.setEmployeeName(employeeName);
			player.setEmployeeEmail(employeeEmail);
			List<Game> playerGames = new ArrayList<Game>(gamesArray.length);
			for(String tempGame: gamesArray){
				Game game = utilityDao.getGame(tempGame.split("-")[0], tempGame.split("-")[1]);
				if(game==null){
					throw new SportsException("No such game:"+tempGame);
				}
				playerGames.add(game);
			}
			player.setGames(playerGames);
			session.save(player);
		}catch(HibernateException e){
			throw e;
		}
		return player.getEmployeeId();
	}*/

	public List<Player> getPlayers() {
		Session session = utilityDao.getSession();
		List<Player> playersList = session.createCriteria(Player.class).list();
		return playersList;
	}

	public List<Player> getPlayersByGame(Game game) {
		Session session = utilityDao.getSession();
		List<Player> playersList = getPlayers();
		for(int i=0;i<playersList.size();i++){
			boolean checkGameFlag = true;
			List<Game> playerGames = playersList.get(i).getGames();
			for(Game checkGame: playerGames){
				if(checkGame.getId() == game.getId()){
					checkGameFlag = false;
				}
			}
			if(checkGameFlag==true){
				playersList.remove(i);
				i--;
			}
		}
		return playersList;
	}

	public List<Player> getPlayersByTeam(Team team) {
		List<Player> players = team.getPlayers();
		players.size();
		return players;
	}

}
