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
package com.ngo.interfaces.player;

import java.util.List;

import com.ngo.model.Game;
import com.ngo.model.Player;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */
public interface PlayerDao {

	public int addPlayer(Player player);
	public int modifyPlayer(Player player);
	public int modifyPlayerAddGame(Player player, Game game);
	public int modifyPlayerDeleteGame(Player player, Game game);
	public void modifyPlayerReloadGames(Player player, List<Game> games);
	public int deletePlayer(Player player);
	//public int modifyPlayerFromAdmin(int employeeId, String employeeName, String employeeEmail, String games) throws SportsException;
	public List<Player> getPlayers();
	public List<Player> getPlayersByGame(Game game);
	public List<Player> getPlayersByTeam(Team team);
	
}
