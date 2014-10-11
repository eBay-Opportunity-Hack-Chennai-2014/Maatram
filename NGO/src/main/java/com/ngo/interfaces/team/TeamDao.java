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
package com.ngo.interfaces.team;

import java.util.List;

import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Player;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */
public interface TeamDao {

	/*you cannot change game for a team*/
	public int addTeam(Team team);
	public int modifyTeam(Team team, String name, String rating, String score, int round);
	public int modifyTeam(Team team, String name, String score, int round);
	public int modifyTeamAddPlayer(Team team, Player player);
	public int modifyTeamDeletePlayer(Team team, Player player);
	public int deleteTeam(Team team);
	public int modifyShow(Team team, boolean toShow);
	public int modifyTeamFromAdmin(Team team, String name, String rating, String score, int round, List<Player> playersList);
	public List<Team> getTeamsByGame(Game game, boolean showCriteria);
	public List<Team> getTeamsByMatch(Match match);
	
}
