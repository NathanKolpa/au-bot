package me.kolpa.aubot.lib.core.model;

import me.kolpa.aubot.lib.core.model.enums.GameState;
import me.kolpa.aubot.lib.core.model.enums.PlayerColor;
import me.kolpa.aubot.lib.core.model.enums.Region;

import java.util.ArrayList;
import java.util.List;

public class Game
{
	private int gameId;
	private DiscordUser host;

	private String discordMessageId;
	private String discordChannelId;


	private GameState gameState;
	private Region region;
	private String captureCode;
	private String roomCode;
	private final List<Player> players = new ArrayList<Player>();

	public Game(String captureCode)
	{
		this.captureCode = captureCode;
		this.region = null;
		this.roomCode = null;
	}

	public void setClientConnected(Region region, String roomCode)
	{
		this.region = region;
		this.roomCode = roomCode;
		players.clear();
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;

		switch (gameState)
		{
			case Menu:
				endGame();
				break;
		}
	}
	
	public Player getPlayer(String name)
	{
		for (Player player : players)
			if (player.getAuName().equals(name))
				return player;

		return null;
	}

	public boolean hasPlayer(String name)
	{
		return getPlayer(name) != null;
	}
	
	public void addPlayer(Player player)
	{
		if(gameState == GameState.Menu)
			return;;
		
		players.add(player);
	}

	public void removePlayer(String name)
	{
		players.removeIf(player -> player.getAuName().equals(name));
	}

	public void endGame()
	{
		region = null;
		roomCode = null;
		players.clear();
	}

	public GameState getGameState()
	{
		return gameState;
	}

	public Region getRegion()
	{
		return region;
	}

	public String getCaptureCode()
	{
		return captureCode;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public int getGameId()
	{
		return gameId;
	}

	public void setGameId(int gameId)
	{
		this.gameId = gameId;
	}

	public String getDiscordMessageId()
	{
		return discordMessageId;
	}

	public void setDiscordMessageId(String discordMessageId)
	{
		this.discordMessageId = discordMessageId;
	}

	public String getDiscordChannelId()
	{
		return discordChannelId;
	}

	public void setDiscordChannelId(String discordChannelId)
	{
		this.discordChannelId = discordChannelId;
	}

	public DiscordUser getHost()
	{
		return host;
	}

	public void setHost(DiscordUser host)
	{
		this.host = host;
	}
}
