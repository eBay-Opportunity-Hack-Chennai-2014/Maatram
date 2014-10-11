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
package com.ngo.interfaces.match;

import java.util.List;

import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */
public interface MatchDao {

	/*you cannot change game for a match*/
	public int addMatch(int round, String time, String location, String status, String winner, String score, boolean show, List<Team> teams, Game game);
	public int modifyMatch(Match match, int round, String time, String location, String status, String winner, String score, boolean show);
	public int modifyMatchAddTeams(Match match, List<Team> teams);
	public int modifyMatchDeleteTeams(Match match, List<Team> teams);
	public int deleteMatch(Match match);
	public boolean checkNumberOfPlayers(Match match);
	public boolean checkTeamsMash(Match match);
	public int modifyMatchModifyShow(Match match, boolean toShow);
	public int modifyMatchFromAdmin(Match match, List<Team> teams,int round, String time, String location, String status, String winner, String score);
	public List<Match> allMatches(boolean showCriteria);
	public List<Match> getMatchesByGame(Game game);
	public List<Match> getMatchesByGame(Game game, int round);
	
}
