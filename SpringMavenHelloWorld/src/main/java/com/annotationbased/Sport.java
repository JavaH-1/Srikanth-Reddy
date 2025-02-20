package com.annotationbased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sport {

	@Value("Hockey")
	private String sportName;
	@Value("11")
	private int playersCount;
	
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public int getPlayersCount() {
		return playersCount;
	}
	public void setPlayersCount(int playersCount) {
		this.playersCount = playersCount;
	}
	
	public void display() {
		System.out.println("Sport Name:" + sportName);
		System.out.println("No of Players:" + playersCount);
	}
}
