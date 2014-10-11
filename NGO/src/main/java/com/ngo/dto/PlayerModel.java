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
public class PlayerModel {
	
	private int employeeId;
	
	public PlayerModel(PlayerModelBuilder playerModelBuilder){
		this.employeeId = playerModelBuilder.employeeId;
	}
	
	public int getEmployeeId(){
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId){
		this.employeeId = employeeId;
	}
	
	public static class PlayerModelBuilder{
		
		private int employeeId;
		
		public PlayerModelBuilder(int employeeId){
			this.employeeId = employeeId;
		}
		
		public PlayerModel build(){
			return new PlayerModel(this);
		}
	}
}
