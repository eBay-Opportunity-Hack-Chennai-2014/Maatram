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

import com.ngo.model.Team;
import com.ngo.webservice.team.SimpleTeamForm;
import com.ngo.webservice.team.TeamForm;

/**
 * @author Sai Pranav
 *
 */
public interface TeamService {
	
	/*Team Functions*/
	public int addTeam(String ipAddress, TeamForm teamForm);
	public int modifyTeam(int id, TeamForm teamForm);
	public int modifyTeam(int id, SimpleTeamForm teamForm);
	public int modifyTeamAddPlayer(int id, String employeeId);
	public int modifyTeamDeletePlayer(int id, String employeeId);
	/*you cannot change game for a team*/
	public int deleteTeam(int id);
	public int modifyTeamShow(int id, boolean toShow);
	public int modifyTeamFromAdmin(int id, TeamForm teamForm);

	/*Utility functions*/
	public List<Team> getTeamsByGame(String gameName, String gameCategory, boolean showCriteria);
	public List<Team> getTeamsByMatch(int matchId);
	public Team getTeam(int teamId);
}
