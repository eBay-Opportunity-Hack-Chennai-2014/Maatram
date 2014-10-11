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
package com.ngo.webservice.team;

import java.util.List;

import com.ngo.webservice.game.GameFormWithoutNumberOfPlayers;

/**
 * @author Sai Pranav
 *
 */
public class TeamForm {

	private String name;
	private String rating;
	private String score;
	private int round;
	private boolean show;
	private List<Integer> players;
	private GameFormWithoutNumberOfPlayers game;
	private String whoAmI;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public List<Integer> getPlayers() {
		return players;
	}
	public void setPlayers(List<Integer> players) {
		this.players = players;
	}
	public GameFormWithoutNumberOfPlayers getGame() {
		return game;
	}
	public void setGame(GameFormWithoutNumberOfPlayers game) {
		this.game = game;
	}
	public String getWhoAmI() {
		return whoAmI;
	}
	public void setWhoAmI(String whoAmI) {
		this.whoAmI = whoAmI;
	}
	
}
