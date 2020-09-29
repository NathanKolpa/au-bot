package me.kolpa.aubot.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.kolpa.aubot.lib.core.model.enums.GameState;

public class UpdateGameStateRequest
{
	@JsonProperty("NewState")
	public String newState;
	
	public GameState mapState()
	{
		switch (newState)
		{
			case "0": return GameState.Lobby;
			case "1": return GameState.Free;
			case "2": return GameState.Discussion;
			case "3": return GameState.Menu;
		}
		
		throw new IllegalArgumentException("invalid state");
	}
}
