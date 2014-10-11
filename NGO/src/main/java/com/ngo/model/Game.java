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

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Sai Pranav
 *
 */
@Entity
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="game")
public class Game {

	@Id
	@GenericGenerator(name="game_seq_gen" , strategy="increment")
	@GeneratedValue(generator="game_seq_gen")
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "min_no_players")
	private int minNoPlayers;
	
	@Column(name = "max_no_players")
	private int maxNoPlayers;
	
	public Game(){}
	
	public Game(String name, String category, int minNoPlayers, int maxNoPlayers){
		this.name = name;
		this.category = category;
		this.minNoPlayers = minNoPlayers;
		this.maxNoPlayers = maxNoPlayers;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getMinNoPlayers() {
		return minNoPlayers;
	}

	public void setMinNoPlayers(int minNoPlayers) {
		this.minNoPlayers = minNoPlayers;
	}
	
	public int getMaxNoPlayers() {
		return maxNoPlayers;
	}

	public void setMaxNoPlayers(int maxNoPlayers) {
		this.maxNoPlayers = maxNoPlayers;
	}
	
}
