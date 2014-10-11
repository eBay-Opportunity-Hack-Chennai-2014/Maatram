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

/**
 * @author Sai Pranav
 *
 */
public class TeamModel {
	
	private int teamId;
	private String teamName;
	private String score;
	private int round;
	private boolean show;
	
	public TeamModel(TeamModelBuilder teamModelBuilder){
		this.teamId = teamModelBuilder.teamId;
		this.teamName = teamModelBuilder.teamName;
		this.score = teamModelBuilder.score;
		this.round = teamModelBuilder.round;
		this.show = teamModelBuilder.show;
	}
	
	public int getTeamId(){
		return teamId;
	}
	
	public void setTeamId(int teamId){
		this.teamId = teamId;
	}
	
	public String getTeamName(){
		return teamName;
	}
	
	public void setEmployeeId(String teamName){
		this.teamName = teamName;
	}
	
	public String getScore(){
		return score;
	}
	
	public void setScore(String score){
		this.score = score;
	}
	
	public int getRound(){
		return round;
	}
	
	public void setRound(int round){
		this.round = round;
	}
	
	public boolean isShow(){
		return show;
	}
	
	public void setShow(boolean show){
		this.show = show;
	}
	
	public static class TeamModelBuilder{
		
		private int teamId;
		private String teamName;
		private String score;
		private int round;
		private boolean show;
		
		public TeamModelBuilder(int teamId, String teamName){
			this.teamId = teamId;
			this.teamName = teamName;
		}
		
		public TeamModelBuilder score(String score){
			this.score = score;
			return this;
		}
		
		public TeamModelBuilder round(int round){
			this.round = round;
			return this;
		}
		
		public TeamModelBuilder show(boolean show){
			this.show = show;
			return this;
		}
		
		public TeamModel build(){
			return new TeamModel(this);
		}
	}
}
