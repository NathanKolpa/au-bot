package me.kolpa.aubot.lib.core.model;

import me.kolpa.aubot.lib.core.model.enums.PlayerColor;

public class Player
{
	private PlayerColor color;
	private DiscordUser discordUser;
	private String auName;
	private boolean isDead;
	private boolean isExiled;

	public Player(PlayerColor color, String auName, boolean isDead, boolean isExiled)
	{
		this.color = color;
		this.auName = auName;
		this.isDead = isDead;
		this.isExiled = isExiled;
	}

	public PlayerColor getColor()
	{
		if(color == null)
			System.out.println(69);
		
		return color;
	}

	public void setColor(PlayerColor color)
	{
		this.color = color;
	}

	public String getAuName()
	{
		return auName;
	}

	public void setAuName(String auName)
	{
		this.auName = auName;
	}

	public boolean isDead()
	{
		return isDead;
	}

	public void setDead(boolean dead)
	{
		isDead = dead;
	}

	public DiscordUser getDiscordUser()
	{
		return discordUser;
	}

	public void setDiscordUser(DiscordUser discordUser)
	{
		this.discordUser = discordUser;
	}

	public boolean isExiled()
	{
		return isExiled;
	}

	public void exile()
	{
		isExiled = true;
		isDead = true;
	}
}
