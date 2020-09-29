package me.kolpa.aubot.discord;

import me.kolpa.aubot.lib.core.model.DiscordUser;
import me.kolpa.aubot.lib.core.model.Game;
import me.kolpa.aubot.lib.core.repository.UnitOfWork;
import me.kolpa.aubot.lib.core.repository.UnitOfWorkFactory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.UUID;

public class DiscordBot extends ListenerAdapter
{
	private final UnitOfWorkFactory unitOfWorkFactory;
	private JDA jda;

	public DiscordBot(UnitOfWorkFactory unitOfWorkFactory)
	{
		this.unitOfWorkFactory = unitOfWorkFactory;
	}

	public void init() throws LoginException
	{
		JDABuilder builder = JDABuilder.createDefault("NzYwNTY1MzY4NTI3Mzg4Njcz.X3N5sg.b83pS7lzJuAL9_GMZ27OPOYDv4k");

		jda = builder.build();
		jda.addEventListener(this);
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		try
		{
			if (event.getAuthor().isBot())
				return;

			if (event.getMessage().getContentRaw().startsWith("*new"))
				handleCreateGame(event);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void handleCreateGame(MessageReceivedEvent event) throws Exception
	{
		UnitOfWork unitOfWork = unitOfWorkFactory.create();

		Game game = new Game(UUID.randomUUID().toString());
		unitOfWork.getGames().add(game);

		event.getAuthor()
				.openPrivateChannel()
				.flatMap(privateChannel -> privateChannel.sendMessage("Your connect code is `" + game.getCaptureCode() + "`"))
				.flatMap(x -> event.getChannel().sendMessage("Your connect code has been sent!"))
				.queue(x ->
				{
					game.setDiscordMessageId(x.getId());
					game.setDiscordChannelId(x.getChannel().getId());
					game.setHost(new DiscordUser(event.getAuthor().getName(), event.getAuthor().getId()));

					try
					{
						unitOfWork.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				});
	}

	public void updateGame(Game game)
	{
		EmbedBuilder embedBuilder = new EmbedBuilder();
		MessageBuilder builder = new MessageBuilder().setContent("**@" + game.getHost().getName() + "'s game**");

		handleMsgGameState(game, builder, embedBuilder);
		handleMsgGameInfo(game, builder, embedBuilder);

		builder.setEmbed(embedBuilder.build());
		jda.getTextChannelById(game.getDiscordChannelId())
				.editMessageById(game.getDiscordMessageId(), builder.build())
				.queue();
	}

	private void handleMsgGameInfo(Game game, MessageBuilder message, EmbedBuilder embed)
	{
		String roomCode = "Unknown";
		String region = "Unknown";

		if (game.getRoomCode() != null)
			roomCode = game.getRoomCode();

		if (game.getRegion() != null)
			region = game.getRegion().toString();

		embed.addField("Room Code", roomCode, true);
		embed.addField("Region", region, true);
	}

	private void handleMsgGameState(Game game, MessageBuilder message, EmbedBuilder embed)
	{
		String gameStatus = "Unknown";

		switch (game.getGameState())
		{
			case Free:
				gameStatus = "Doing tasks";
				embed.setColor(Color.BLUE);
				break;
			case Lobby:
				gameStatus = "Waiting in the lobby";
				embed.setColor(Color.GREEN);
				break;
			case Discussion:
				gameStatus = "Discussing";
				embed.setColor(Color.BLUE);
				break;
			case Menu:
				gameStatus = "Host is waiting in the main menu";
				embed.setColor(Color.RED);
				break;
		}

		embed.setTitle(gameStatus);
	}
}
