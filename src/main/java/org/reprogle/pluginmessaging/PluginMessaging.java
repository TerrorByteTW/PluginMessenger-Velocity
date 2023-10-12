package org.reprogle.pluginmessaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

import java.util.Optional;

@Plugin(
		id = "pluginmessaging",
		name = "PluginMessaging",
		version = "1.0.0"
)
public class PluginMessaging {

	public static final MinecraftChannelIdentifier IDENTIFIER = MinecraftChannelIdentifier.from("demo:main");
	private final Logger logger;
	private final ProxyServer proxy;

	@Inject
	public PluginMessaging(ProxyServer proxy, Logger logger) {
		this.proxy = proxy;
		this.logger = logger;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		proxy.getChannelRegistrar().register(IDENTIFIER);

		logger.info("Plugin Messaging test plugin has started");
	}

	@Subscribe
	public void onPluginMessageFromPlugin(PluginMessageEvent event) {
		if (!(event.getSource() instanceof ServerConnection)) return;

		if (event.getIdentifier() != IDENTIFIER) return;

		@SuppressWarnings("UnstableApiUsage")
		ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());

		String subchannel = in.readUTF();
		if (subchannel.equals("RequestCreeper")) {
			for (Player player : proxy.getAllPlayers()) {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("SpawnCreeper");

				Optional<ServerConnection> connection = player.getCurrentServer();
				connection.ifPresent(serverConnection -> serverConnection.sendPluginMessage(IDENTIFIER, out.toByteArray()));
			}

			this.logger.info("Sending message to all servers to summon a creeper near all players");
		}
	}
}
