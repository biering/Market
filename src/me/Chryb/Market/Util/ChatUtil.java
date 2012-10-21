package me.Chryb.Market.Util;

import me.Chryb.Market.Util.MessageUtil.MessageType;

import org.bukkit.entity.Player;

public class ChatUtil {

	public static void noPermissionMessage(Player p) {
		MessageUtil message = new MessageUtil(MessageType.NO_PERMISSION);
		message.send(p);
	}
	
}
