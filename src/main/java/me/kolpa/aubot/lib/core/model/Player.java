package me.kolpa.aubot.lib.core.model;

import me.kolpa.aubot.lib.core.model.enums.PlayerColor;

public class Player
{
	private PlayerColor color;
	private DiscordUser discordUser;
	private String auName;
	private boolean isDead;

	public Player(PlayerColor color, String auName, boolean isDead)
	{
		this.color = color;
		this.auName = auName;
		this.isDead = isDead;
	}

	public PlayerColor getColor()
	{
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
}
