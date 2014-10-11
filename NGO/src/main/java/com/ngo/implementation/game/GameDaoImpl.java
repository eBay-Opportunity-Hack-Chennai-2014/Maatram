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
package com.ngo.implementation.game;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.SportsException;
import com.ngo.interfaces.game.GameDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Player;

/**
 * @author Sai Pranav
 *
 */
@Repository
public class GameDaoImpl implements GameDao {

	@Autowired
	UtilityDao utilityDao;
	
	public int addGame(Game game){
		Session session = utilityDao.getSession();
		try {
			session.save(game);
		} catch (HibernateException e) {
			throw new SportsException("OOPS! There seems to be a problem with database");
		}
		return game.getId();
	}

	public int modifyGame(Game game){
		Session session = utilityDao.getSession();
		try {
			session.saveOrUpdate(game);
		} catch (HibernateException e) {
			throw new SportsException("OOPS! There seems to be a problem with database");
		}
		return game.getId();
	}

	public int deleteGame(Game game){
		Session session = utilityDao.getSession();
		try {
			session.delete(game);
		} catch (HibernateException e) {
			throw new SportsException("OOPS! There seems to be a problem with database");
		}
		return game.getId();
	}

	public List<Game> getGames() {
		Session session = utilityDao.getSession();
		List<Game> games;
		try{
			games = session.createCriteria(Game.class).setCacheable(true).list();
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with database");
		}
		return games;
	}

	public List<Game> getGamesByPlayer(Player player) {
		List<Game> games = player.getGames();
		games.size();
		return games;
	}

}
