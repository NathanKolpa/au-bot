package me.kolpa.aubot.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.kolpa.aubot.lib.core.model.Player;
import me.kolpa.aubot.lib.core.model.enums.PlayerColor;

public class UpdatePlayerStateRequest
{
	public enum PlayerAction
	{
		Joined,
		Left,
		Died,
		ChangedColor,
		ForceUpdated,
		Disconnected,
		Exiled
	}
	
	@JsonProperty("Action")
	public String action;
	
	@JsonProperty("Name")
	public String name;

	@JsonProperty("IsDead")
	public boolean isDead;

	@JsonProperty("Disconnected")
	public boolean disconnected;

	@JsonProperty("Color")
	public String color;
	
	public PlayerAction mapAction()
	{
		switch (action)
		{
			case "0": return PlayerAction.Joined;
			case "1": return PlayerAction.Left;
			case "2": return PlayerAction.Died;
			case "3": return PlayerAction.ChangedColor;
			case "4": return PlayerAction.ForceUpdated;
			case "5": return PlayerAction.Disconnected;
			case "6": return PlayerAction.Exiled;
		}
		
		throw new IllegalArgumentException();
	}
	
	public PlayerColor mapColor()
	{
		switch (color)
		{
			case "0": return PlayerColor.Red;
			case "1": return PlayerColor.Blue;
			case "2": return PlayerColor.Green;
			case "3": return PlayerColor.Pink;
			case "4": return PlayerColor.Orange;
			case "5": return PlayerColor.Yellow;
			case "6": return PlayerColor.Black;
			case "7": return PlayerColor.White;
			case "8": return PlayerColor.Purple;
			case "9": return PlayerColor.Brown;
			case "10": return PlayerColor.Cyan;
			case "11": return PlayerColor.Lime;
		}

		throw new IllegalArgumentException();
	}
}
