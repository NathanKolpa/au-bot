package me.kolpa.aubot.web.routes;

import me.kolpa.aubot.discord.DiscordBot;
import me.kolpa.aubot.lib.core.model.Game;
import me.kolpa.aubot.lib.core.model.Player;
import me.kolpa.aubot.lib.core.model.enums.GameState;
import me.kolpa.aubot.lib.core.model.enums.PlayerColor;
import me.kolpa.aubot.lib.core.model.enums.Region;
import me.kolpa.aubot.lib.core.repository.UnitOfWork;
import me.kolpa.aubot.lib.core.repository.UnitOfWorkFactory;
import me.kolpa.aubot.web.requests.UpdateGameStateRequest;
import me.kolpa.aubot.web.requests.UpdateLobbyStateRequest;
import me.kolpa.aubot.web.requests.UpdatePlayerStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateRoutesController
{
	@Autowired
	UnitOfWorkFactory unitOfWorkFactory;

	@Autowired
	DiscordBot discordBot;

	@PutMapping("lobby-state")
	public void UpdateLobby(@RequestBody UpdateLobbyStateRequest request, @RequestHeader("Capture-Code") String code)
			throws Exception
	{
		Region newRegion = request.mapRegion();
		String newLobbyCode = request.lobbyCode;

		try (UnitOfWork unitOfWork = unitOfWorkFactory.create())
		{
			Game game = unitOfWork.getGames().getByConnectCode(code);

			if (game == null)
				return;

			game.setClientConnected(newRegion, newLobbyCode);
			discordBot.updateGame(game);
		}
	}

	@PutMapping("game-state")
	public void UpdateGame(@RequestBody UpdateGameStateRequest request, @RequestHeader("Capture-Code") String code)
			throws Exception
	{
		GameState newState = request.mapState();

		try (UnitOfWork unitOfWork = unitOfWorkFactory.create())
		{
			Game game = unitOfWork.getGames().getByConnectCode(code);

			if (game == null)
				return;
			
			game.setGameState(newState);
			discordBot.updateGame(game);
		}
	}

	@PutMapping("player-state")
	public void UpdatePlayer(@RequestBody UpdatePlayerStateRequest request, @RequestHeader("Capture-Code") String code)
			throws Exception
	{
		UpdatePlayerStateRequest.PlayerAction action = request.mapAction();
		PlayerColor color = request.mapColor();
		
		try (UnitOfWork unitOfWork = unitOfWorkFactory.create())
		{
			Game game = unitOfWork.getGames().getByConnectCode(code);

			if (game == null)
				return;

			if(action == UpdatePlayerStateRequest.PlayerAction.ChangedColor)
			{
				game.getPlayer(request.name).setColor(color);
			}
			else if(!game.hasPlayer(request.name))
			{
				game.addPlayer(new Player(color, request.name, request.isDead, false));
			}
			
			if(action == UpdatePlayerStateRequest.PlayerAction.Disconnected || action == UpdatePlayerStateRequest.PlayerAction.Left)
			{
				game.removePlayer(request.name);
			}
			else if(action == UpdatePlayerStateRequest.PlayerAction.Exiled)
			{
				game.getPlayer(request.name).exile();
			}
			
			discordBot.updateGame(game);
		}
	}
}
