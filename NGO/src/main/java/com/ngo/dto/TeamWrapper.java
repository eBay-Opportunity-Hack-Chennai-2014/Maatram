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
package com.ngo.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO Wrapper class for Team DO. It takes care of transferring the team names only.
 * 
 * @author Sai Pranav
 *
 */
public class TeamWrapper {

	private List<TeamModel> teams;
	
	public TeamWrapper(){
		teams = new ArrayList<TeamModel>();
	}
	
	public void setTeams(List<TeamModel> teams){
		this.teams = teams;
	}
	
	public List<TeamModel> getTeams(){
		return this.teams;
	}
	
}
