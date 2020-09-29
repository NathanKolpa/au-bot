package me.kolpa.aubot.lib.core.model;

public class DiscordUser
{
	private String name;
	private String id;
	private String pfpUrl;

	public DiscordUser(String name, String id, String pfpUrl)
	{
		this.name = name;
		this.id = id;
		this.pfpUrl = pfpUrl;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPfpUrl()
	{
		return pfpUrl;
	}

	public void setPfpUrl(String pfpUrl)
	{
		this.pfpUrl = pfpUrl;
	}
}
