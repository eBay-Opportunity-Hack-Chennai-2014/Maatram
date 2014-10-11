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
 * Match model will carry the details for every match. (DTO)
 * 
 * @author Sai Pranav
 * 
 */
public class MatchModel {

	private int matchId;
	private String gameName;
	private String gameCategory;
	private String teamName1;
	private String teamRating1;
	private String teamScore1;
	private String score;
	private String teamName2;
	private String teamRating2;
	private String teamScore2;
	private int round;
	private String scheduledTime;
	private String location;
	private String status;
	private String winner;
	private boolean show;
	
	private MatchModel(MatchModelBuilder matchModelBuilder){
		this.matchId = matchModelBuilder.matchId;
		this.gameName = matchModelBuilder.gameName;
		this.gameCategory = matchModelBuilder.gameCategory;
		this.teamName1 = matchModelBuilder.teamName1;
		this.teamRating1 = matchModelBuilder.teamRating1;
		this.teamScore1 = matchModelBuilder.teamScore1;
		this.score = matchModelBuilder.score;
		this.teamName2 = matchModelBuilder.teamName2;
		this.teamRating2 = matchModelBuilder.teamRating2;
		this.teamScore2 = matchModelBuilder.teamScore2;
		this.round = matchModelBuilder.round;
		this.scheduledTime = matchModelBuilder.scheduledTime;
		this.location = matchModelBuilder.location;
		this.status = matchModelBuilder.status;
		this.winner = matchModelBuilder.winner;
		this.show = matchModelBuilder.show;
	}
	
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameCategory() {
		return gameCategory;
	}
	public void setGameCategory(String gameCategory) {
		this.gameCategory = gameCategory;
	}
	public String getTeamName1() {
		return teamName1;
	}
	public void setTeamName1(String teamName1) {
		this.teamName1 = teamName1;
	}
	public String getTeamRating1() {
		return teamRating1;
	}
	public void setTeamRating1(String teamRating1) {
		this.teamRating1 = teamRating1;
	}
	public String getTeamScore1() {
		return teamScore1;
	}
	public void setTeamScore1(String teamScore1) {
		this.teamScore1 = teamScore1;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTeamName2() {
		return teamName2;
	}
	public void setTeamName2(String teamName2) {
		this.teamName2 = teamName2;
	}
	public String getTeamRating2() {
		return teamRating2;
	}
	public void setTeamRating2(String teamRating2) {
		this.teamRating2 = teamRating2;
	}
	public String getTeamScore2() {
		return teamScore2;
	}
	public void setTeamScore2(String teamScore2) {
		this.teamScore2 = teamScore2;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public String getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduled_time(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	
	public static class MatchModelBuilder{
		private int matchId;
		private String gameName;
		private String gameCategory;
		private String teamName1;
		private String teamRating1;
		private String teamScore1;
		private String score;
		private String teamName2;
		private String teamRating2;
		private String teamScore2;
		private int round;
		private String scheduledTime;
		private String location;
		private String status;
		private String winner;
		private boolean show;
		
		public MatchModelBuilder(int matchId){
			this.matchId = matchId;
		}
		
		public MatchModelBuilder gameName(String gameName){
			this.gameName = gameName;
			return this;
		}
		
		public MatchModelBuilder gameCategory(String gameCategory){
			this.gameCategory = gameCategory;
			return this;
		}
		
		public MatchModelBuilder teamName1(String teamName1){
			this.teamName1 = teamName1;
			return this;
		}
		
		public MatchModelBuilder teamRating1(String teamRating1){
			this.teamRating1 = teamRating1;
			return this;
		}
		
		public MatchModelBuilder teamScore1(String teamScore1){
			this.teamScore1 = teamScore1;
			return this;
		}
		
		public MatchModelBuilder score(String score){
			this.score = score;
			return this;
		}
		
		public MatchModelBuilder teamName2(String teamName2){
			this.teamName2 = teamName2;
			return this;
		}
		
		public MatchModelBuilder teamRating2(String teamRating2){
			this.teamRating2 = teamRating2;
			return this;
		}
		
		public MatchModelBuilder teamScore2(String teamScore2){
			this.teamScore2 = teamScore2;
			return this;
		}
		
		public MatchModelBuilder round(int round){
			this.round = round;
			return this;
		}
		
		public MatchModelBuilder scheduledTime(String scheduledTime){
			this.scheduledTime = scheduledTime;
			return this;
		}
		
		public MatchModelBuilder location(String location){
			this.location = location;
			return this;
		}
		
		public MatchModelBuilder status(String status){
			this.status = status;
			return this;
		}
		
		public MatchModelBuilder winner(String winner){
			this.winner = winner;
			return this;
		}
		
		public MatchModelBuilder show(boolean show){
			this.show = show;
			return this;
		}
		
		public MatchModel build(){
			return new MatchModel(this);
		}
		
	}
	
}
