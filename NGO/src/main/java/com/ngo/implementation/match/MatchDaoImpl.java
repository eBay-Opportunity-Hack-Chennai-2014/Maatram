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
package com.ngo.implementation.match;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ngo.exception.NGOException;
import com.ngo.interfaces.match.MatchDao;
import com.ngo.interfaces.utility.UtilityDao;
import com.ngo.model.Game;
import com.ngo.model.Match;
import com.ngo.model.Team;

/**
 * @author Sai Pranav
 *
 */

@Repository
public class MatchDaoImpl implements MatchDao {

	@Autowired
	UtilityDao utilityDao;
	
	public int addMatch(int round, String time, String location, String status, String winner, String score, boolean show, List<Team> teams, Game game) throws HibernateException{
		Session session = utilityDao.getSession();
		Match match;
		try{
			match = new Match();
			match.setGame(game);
			match.setTeams(teams);
			match.setRound(round);
			match.setScheduledTime(time);
			match.setLocation(location);
			match.setStatus(status);
			match.setWinner(winner);
			match.setScore(score);
			match.setShow(show);
			session.save(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public int modifyMatch(Match match, int round, String time, String location, String status, String winner, String score, boolean show) {
		Session session = utilityDao.getSession();
		try{
			match.setRound(round);
			match.setScheduledTime(time);
			match.setLocation(location);
			match.setStatus(status);
			match.setWinner(winner);
			match.setScore(score);
			match.setShow(show);
			session.save(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public int modifyMatchAddTeams(Match match, List<Team> teams) {
		Session session = utilityDao.getSession();
		try{
			if(teams.size()==1 && match.getTeams().size()==1){
				match.getTeams().addAll(teams);
				session.update(match);
			}
			else if(teams.size()==2 && match.getTeams().size()==0){
				match.getTeams().addAll(teams);
				session.update(match);
			}
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public int modifyMatchDeleteTeams(Match match, List<Team> teams) {
		Session session = utilityDao.getSession();
		try{
			match.getTeams().removeAll(teams);
			session.update(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public int deleteMatch(Match match) {
		Session session = utilityDao.getSession();
		try{
			session.delete(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public boolean checkNumberOfPlayers(Match match) {
		List<Team> teams = match.getTeams();
		boolean team1NumberOfPlayersCheck = ( (teams.get(0).getPlayers().size() >= match.getGame().getMinNoPlayers()) && (teams.get(0).getPlayers().size() <= match.getGame().getMaxNoPlayers()) );
		boolean team2NumberOfPlayersCheck = ( (teams.get(1).getPlayers().size() >= match.getGame().getMinNoPlayers()) && (teams.get(1).getPlayers().size() <= match.getGame().getMaxNoPlayers()) );
		if( team1NumberOfPlayersCheck && team2NumberOfPlayersCheck ){
				return true;
		}
		return false;
	}
	
	public boolean checkTeamsMash(Match match) {
		List<Team> teams = match.getTeams();
		if(teams.get(0).getId() != teams.get(1).getId()){
			return true;
		}
		return false;
	}

	public int modifyMatchModifyShow(Match match, boolean toShow) {
		Session session = utilityDao.getSession();
		try{
			match.setShow(toShow);
			session.update(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public int modifyMatchFromAdmin(Match match, List<Team> teams, int round, String time, String location, String status, String winner, String score) {
		Session session = utilityDao.getSession();
		try{
			match.setTeams(teams);
			match.setRound(round);
			match.setScheduledTime(time);
			match.setLocation(location);
			match.setStatus(status);
			match.setWinner(winner);
			match.setScore(score);
			session.save(match);
		}
		catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		return match.getId();
	}

	public List<Match> allMatches(boolean showCriteria) {
		Session session = utilityDao.getSession();
		List<Match> matches;
		try{
			if(showCriteria){
				matches = session.createCriteria(Match.class).add(Restrictions.in("game.id", utilityDao.getGameIds())).add(Restrictions.eq("show", true)).addOrder(Order.desc("round")).addOrder(Order.asc("id")).setCacheable(true).list();
			}
			else{
				matches = session.createCriteria(Match.class).add(Restrictions.in("game.id", utilityDao.getGameIds())).addOrder(Order.desc("round")).addOrder(Order.asc("id")).setCacheable(true).list();
			}
		}catch(HibernateException e){
			throw new NGOException("OOPS! There seems to be a problem with the database");
		}
		for(Match match: matches){
			match.getGame().getName();
			match.getTeams().size();
		}
		return matches;
	}
	
	public List<Match> getMatchesByGame(Game game) {
		Session session = utilityDao.getSession();
		List<Match> matches = session.createCriteria(Match.class).add(Restrictions.eq("game.id", game.getId())).addOrder(Order.desc("round")).list();
		return matches;
	}
	
	public List<Match> getMatchesByGame(Game game, int round) {
		Session session = utilityDao.getSession();
		List<Match> matches = session.createCriteria(Match.class).add(Restrictions.eq("round", round)).add(Restrictions.eq("game.id", game.getId())).addOrder(Order.desc("round")).list();
		return matches;
	}

}
