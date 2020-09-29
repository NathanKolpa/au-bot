package me.kolpa.aubot.lib.impl.repository.inmemory.domain;

import me.kolpa.aubot.lib.core.model.Game;
import me.kolpa.aubot.lib.core.repository.domain.GameRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameRepository extends InMemoryRepository<Game> implements GameRepository
{
	private int autoIncrement = 1;
	private Map<String, Game> captureCodeMap = new HashMap<>();

	@Override
	public void add(Game entity)
	{
		super.add(entity);
		captureCodeMap.put(entity.getCaptureCode(), entity);
		entity.setGameId(autoIncrement);
		autoIncrement++;
	}

	@Override
	public Game getByConnectCode(String code)
	{
		return captureCodeMap.get(code);
	}

	@Override
	public void deleteGamesWithDiscordId(String discordId)
	{
		getValues().removeIf(game ->
		{
			if(game.getHost() != null)
				return game.getHost().getId().equals(discordId);
			
			return false;
		});
	}
}
