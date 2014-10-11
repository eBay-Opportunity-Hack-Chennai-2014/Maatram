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
package com.ngo.interfaces.utility;


import java.sql.Date;
import java.util.List;

import org.hibernate.classic.Session;

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
public interface UtilityDao {

	public Session getSession();
	
	public Game getGame(String name, String category);
	public Game getGame(int id);
	public Player getPlayer(int employeeId);
	public Team getTeam(String name, Game game);
	public Team getTeam(int id);
	public List<Player> getPlayerList(List<Integer> players);
	public boolean checkPlayers(List<Team> teams ,List<Player> playersList);
	public boolean checkTeamName(List<Team> teams, String name);
	public List<Team> getTeams(List<String> teams, List<Team> gameTeams);
	public Match getMatch(Game game, List<Team> teams, List<Match> matches);
	public Match getMatch(int id);
	public Registration getRegistration(String ipAddress, Game game);
	
	public List<Integer> getGameIds();
	
	
	public User getUser(String name, String dob, String address);
	public User getUser(int id);
	public List<User> getUsers();
	public User getUser(String name, String password);

}