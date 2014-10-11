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
package com.ngo.implementation.team;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.SportsException;
import com.ngo.interfaces.team.TeamDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Player;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class TeamDaoImpl implements TeamDao {
	
	@Autowired
	UtilityDao utilityDao;

	public int addTeam(Team team) {
		Session session = utilityDao.getSession();
		try{
			session.save(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public int modifyTeam(Team team, String name, String rating, String score, int round) {
		Session session = utilityDao.getSession();
		try{
			team.setName(name);
			team.setRating(rating);
			team.setScore(score);
			team.setRound(round);
			session.save(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}
	
	public int modifyTeam(Team team, String name, String score, int round) {
		Session session = utilityDao.getSession();
		try{
			team.setName(name);
			team.setScore(score);
			team.setRound(round);
			session.save(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public int modifyTeamAddPlayer(Team team, Player player) {
		Session session = utilityDao.getSession();
		try{
			team.getPlayers().add(player);
			session.save(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public int modifyTeamDeletePlayer(Team team, Player player) {
		Session session = utilityDao.getSession();
		try{
			team.getPlayers().remove(player);
			session.save(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}
	
	public int modifyShow(Team team, boolean toShow) {
		Session session = utilityDao.getSession();
		try{
			team.setShow(toShow);
			session.update(team);
		}
		catch(HibernateException e)		{
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public int deleteTeam(Team team) {
		Session session = utilityDao.getSession();
		try{
			session.delete(team);
		}
		catch(HibernateException e)		{
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public int modifyTeamFromAdmin(Team team, String name, String rating, String score, int round, List<Player> playersList) {
		Session session = utilityDao.getSession();
		try{
			team.setName(name);
			team.setRating(rating);
			team.setScore(score);
			team.setRound(round);
			team.setPlayers(playersList);
			session.update(team);
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return team.getId();
	}

	public List<Team> getTeamsByGame(Game game, boolean showCriteria) {
		Session session = utilityDao.getSession();
		List<Team> teams;
		try{
			if(showCriteria){
				teams = session.createCriteria(Team.class).add(Restrictions.eq("game.id", game.getId())).add(Restrictions.eq("show", true)).addOrder(Order.desc("score")).setCacheable(true).list();
			}
			else{
				teams = session.createCriteria(Team.class).add(Restrictions.eq("game.id", game.getId())).addOrder(Order.desc("score")).setCacheable(true).list();
			}
		}
		catch(HibernateException e){
			throw new SportsException("OOPS! There seems to be a problem with the database");
		}
		return teams;
	}

	public List<Team> getTeamsByMatch(Match match) {
		List<Team> teams = match.getTeams();
		teams.size();
		return teams;
	}

}
