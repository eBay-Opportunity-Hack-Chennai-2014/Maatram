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
package com.ngo.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Sai Pranav
 *
 */
@Entity
@Table(name="registration")
public class Registration {

	@Id
	@GenericGenerator(name="registration_seq_gen" , strategy="increment")
	@GeneratedValue(generator="registration_seq_gen")
	@Column(name = "id")
	private int id;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "timestamp")
	private Timestamp Timestamp;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="game_id")
	//@JoinTable(name="registrations_game",joinColumns={@JoinColumn(name="registrations_id")},inverseJoinColumns={@JoinColumn(name="game_id")})
	private Game game;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="team_id")
	//@JoinTable(name="registrations_team",joinColumns={@JoinColumn(name="registrations_id")},inverseJoinColumns={@JoinColumn(name="team_id")})
	private Team team;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		Timestamp = timestamp;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
