package me.kolpa.aubot.lib.core.repository.domain;

import me.kolpa.aubot.lib.core.model.Game;

public interface GameRepository extends Repository<Game>
{
	Game getByConnectCode(String code);
	void deleteGamesWithDiscordId(String discordId);
}
