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
package com.ngo.implementation.utility;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Event;
import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Player;
import com.ngo.model.Registration;
import com.ngo.model.Team;
import com.ngo.model.User;

/**
 * @author Sai Pranav
 *
 */
@Repository
public class UtilityDaoImpl implements UtilityDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Game getGame(String name, String category){
		Session session = getSession();
		
		List<Game> gameList = session.createCriteria(Game.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("category", category)).setCacheable(true).list();
		
		if(gameList.size()==1){
			return gameList.get(0);
		}
		return null;
	}
	
	public Game getGame(int id){
		Session session = getSession();
		return (Game) session.get(Game.class, id);
	}

	public Player getPlayer(int employeeId) {
		Session session = getSession();
		return (Player) session.get(Player.class, employeeId);
	}
	
	public Team getTeam(String name, Game game){
		Session session = getSession();
		List<Team> teamList = session.createCriteria(Team.class)
				.add(Restrictions.eq("name", name))
				.list();
		
		for(Team team: teamList){
			if(team.getGame().equals(game)){
				return team;
			}
		}
		return null;
	}
	
	public Team getTeam(int id){
		Session session = getSession();
		return (Team) session.get(Team.class, id);
	}
	
	public List<Player> getPlayerList(List<Integer> players){
		Session session = getSession();
		List<Player> playerList = new ArrayList<Player>();
		for(int temp: players){
			Player player = (Player) session.get(Player.class, temp);
			if(player!=null){
				playerList.add(player);
			}
			else{
				throw new NGOException("No such player "+temp);
			}
		}
		return playerList;
	}
	
	public boolean checkPlayers(List<Team> teams, List<Player> players){
		for(Player player: players){
			for(Team team: teams){
				if(team.getPlayers().contains(player)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkTeamName(List<Team> teams, String name){
		for(Team team: teams){
			if(team.getName().equals(name)){
				return false;
			}
		}
		return true;
	}

	public List<Team> getTeams(List<String> teamNames, List<Team> gameTeams){
		List<Team> teams = new ArrayList<Team>();
		for(String teamName: teamNames){
			int i=0;
			for(i=0;i<gameTeams.size();i++){
				if(gameTeams.get(i).getName().equalsIgnoreCase(teamName)){
					teams.add(gameTeams.get(i));
					break;
				}
			}
			if(i==gameTeams.size()){
				throw new NGOException("Team "+teamName+" is not playing this game (OR) No such team");
			}
		}
		return teams;
	}

	public Match getMatch(Game game, List<Team> teams, List<Match> matches) {
		boolean teamDuplicate = false;
		for(Match match: matches){
			for(Team team: teams){
				if(checkTeam(team, matches)){
					teamDuplicate = true;
				}
			}
			if(teamDuplicate==true){
				return match;
			}
		}
		return null;
	}
	
	public boolean checkTeam(Team team, List<Match> matches){
		for(Match match: matches){
			if(match.getTeams().contains(team)){
				return true;
			}
		}
		return false;
	}
	
	public Match getMatch(int id) {
		Session session = getSession();
		return (Match) session.get(Match.class, id);	
	}
	
	public List<Integer> getGameIds(){
		Session session = getSession();
		List<Integer> ids = new ArrayList<Integer>();
		List<Game> gameList = session.createCriteria(Game.class).setCacheable(true).list();
		for(Game game: gameList){
			ids.add(game.getId());
		}
		
		return ids;
	}
	
	public User getUser(String name, String dob, String address) {
		Session session = getSession();
		List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("address", address)).add(Restrictions.eq("dob", dob)).list();
		
		if(userList.size()==1){
			return userList.get(0);
		}
		return null;
	}
	
	public User getUser(String name, String password) {
		Session session = getSession();
		if(password == null || password.isEmpty()){
			return null;
		}
		List<User> userList = session.createCriteria(User.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("password", password)).list();
		
		if(userList.size()==1){
			return userList.get(0);
		}
		return null;
	}
	
	public User getUser(int id){
		Session session = getSession();
		return (User) session.get(User.class, id);
	}
	
	public List<User> getUsers() {
		Session session = getSession();
		List<User> userList = session.createCriteria(User.class).list();
		return userList;
	}
	
	public Registration getRegistration(String ipAddress, Game game){
		Session session = getSession();
		List<Registration> registrationList = session.createCriteria(Registration.class).add(Restrictions.eq("ipAddress", ipAddress)).list();
		for(Registration registration : registrationList){
			if(registration.getGame().getId() == game.getId()){
				return registration;
			}
		}
		return null;
	}

	@Override
	public Event getEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
