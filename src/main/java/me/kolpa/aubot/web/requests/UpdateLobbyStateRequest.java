package me.kolpa.aubot.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.kolpa.aubot.lib.core.model.enums.Region;

public class UpdateLobbyStateRequest
{
	@JsonProperty("LobbyCode")
	public String lobbyCode;

	@JsonProperty("Region")
	public String region;
	
	public Region mapRegion()
	{
		switch (region)
		{
			case "0": return Region.NorthAmerica;
			case "1": return Region.Asia;
			case "2": return Region.Europe;
		}
		
		throw new IllegalArgumentException();
	}
}
