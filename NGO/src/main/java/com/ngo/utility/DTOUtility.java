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
package com.ngo.utility;

import java.util.List;

import com.ngo.dto.MatchModel;
import com.ngo.dto.MatchWrapper;
import com.ngo.dto.PlayerModel;
import com.ngo.dto.PlayerWrapper;
import com.ngo.dto.TeamModel;
import com.ngo.dto.TeamWrapper;
import com.ngo.model.Match;
import com.ngo.model.Player;
import com.ngo.model.Team;

/**
 * Utility Class for transfer of attributes from Data Object (DO) to Data Transfer Object (DTO)
 * 
 * @author Sai Pranav
 *
 */

public class DTOUtility {

	public static MatchWrapper createMatchWrapper(List<Match> matches){
		MatchWrapper matchWrapper = new MatchWrapper();
		String teamName1 = "-";
		String teamName2 = "-";
		String teamRating1 = "-";
		String teamRating2 = "-";
		for(Match match: matches){
			if(match.getTeams().size()==1){
				teamName1 = match.getTeams().get(0).getName();
				teamRating1 = match.getTeams().get(0).getRating();
			}
			else if(match.getTeams().size()==2){
				teamName1 = match.getTeams().get(0).getName();
				teamRating1 = match.getTeams().get(0).getRating();
				teamName2 = match.getTeams().get(1).getName();
				teamRating2 = match.getTeams().get(1).getRating();
			}
			MatchModel model = new MatchModel.MatchModelBuilder(match.getId())
											 .gameName(match.getGame().getName())
											 .gameCategory(match.getGame().getCategory())
											 .teamName1(teamName1)
											 .teamRating1(teamRating1)
											 .score(match.getScore())
											 .teamName2(teamName2)
											 .teamRating2(teamRating2)
											 .round(match.getRound())
											 .scheduledTime(match.getScheduledTime())
											 .location(match.getLocation())
											 .status(match.getStatus())
											 .winner(match.getWinner())
											 .show(match.isShow())
											 .build();
			matchWrapper.getMatches().add(model);
		}
		return matchWrapper;
	}
	
	public static PlayerWrapper createPlayerWrapper(List<Player> players){
		PlayerWrapper playerWrapper = new PlayerWrapper();
		for(Player player: players){
			PlayerModel model= new PlayerModel.PlayerModelBuilder(player.getEmployeeId())
											  .build();
			playerWrapper.getPlayers().add(model);
		}
		return playerWrapper;
	}
	
	public static TeamWrapper createTeamWrapper(List<Team> teams){
		TeamWrapper teamWrapper = new TeamWrapper();
		String score = "-";
		for(Team team: teams){
			if(null != team.getScore()){
				score = team.getScore();
			}
			TeamModel model= new TeamModel.TeamModelBuilder(team.getId(), team.getName())
										  .score(score)
										  .show(team.isShow())
										  .round(team.getRound())
			  							  .build();
			teamWrapper.getTeams().add(model);
		}
		return teamWrapper;
	}
}
