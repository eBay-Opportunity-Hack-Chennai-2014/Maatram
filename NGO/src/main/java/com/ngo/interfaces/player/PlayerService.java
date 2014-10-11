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

import com.ngo.model.Player;
import com.ngo.webservice.game.GameFormWithoutNumberOfPlayers;
import com.ngo.webservice.player.PlayerForm;

/**
 * @author Sai Pranav
 *
 */
public interface PlayerService {
	
	public int addPlayer(PlayerForm playerform);
	public int modifyPlayer(int employeeId, PlayerForm playerForm);
	public int modifyPlayerAddGame(int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers);
	public int modifyPlayerDeleteGame(int employeeId, GameFormWithoutNumberOfPlayers gameFormWithoutNumberOfPlayers);
	public int deletePlayer(int employeeId);
	public int modifyPlayerFromAdmin(int employeeId, PlayerForm playerForm);

	public List<Player> getPlayers();
	public List<String> getPlayerEmails(int teamId);
	public List<Player> getPlayersByTeam(int teamId);
	public List<Player> getPlayersByGame(String gameName, String gameCategory);
}
