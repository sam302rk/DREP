package de.gamingcraft;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRP {

    private static Class rpc;

    public static void run(Class _rpc) throws Exception {
        rpc = _rpc;

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("Welcome " + user.username + "#" + user.discriminator + "!");
        }).build();
        DiscordRPC.discordInitialize(rpc.getDeclaredMethod("getApplicationId").invoke(rpc.newInstance()) + "", handlers, true);
        // rpc.getDeclaredMethod("getApplicationId").invoke(rpc.newInstance())

        createNewPresence();

        DiscordRPC.discordRunCallbacks();
    }

    public static void createNewPresence() throws Exception {
        DiscordRichPresence rich = new DiscordRichPresence.Builder(rpc.getDeclaredMethod("getDefaultBottomText").invoke(rpc.newInstance()) + "")
                .setDetails(rpc.getDeclaredMethod("getDefaultTopText").invoke(rpc.newInstance()) + "").build();

        rich.largeImageKey = rpc.getDeclaredMethod("getBigImage").invoke(rpc.newInstance()) + "";
        rich.largeImageText = rpc.getDeclaredMethod("getBigImageDefaultText").invoke(rpc.newInstance()) + "";

        rich.smallImageKey = rpc.getDeclaredMethod("getSmallImage").invoke(rpc.newInstance()) + "";
        rich.smallImageText = rpc.getDeclaredMethod("getSmallImageDefaultText").invoke(rpc.newInstance()) + "";

        DiscordRPC.discordUpdatePresence(rich);
    }

    public static void updatePresence(String topText, String bottomText, String bigImageText, String smallImageText) throws Exception {
        DiscordRichPresence rich = new DiscordRichPresence.Builder(bottomText)
                .setDetails(topText).build();

        rich.largeImageKey = rpc.getDeclaredMethod("getBigImage").invoke(rpc.newInstance()) + "";
        rich.largeImageText = bigImageText;

        rich.smallImageKey = rpc.getDeclaredMethod("getSmallImage").invoke(rpc.newInstance()) + "";
        rich.smallImageText = smallImageText;

        DiscordRPC.discordUpdatePresence(rich);
    }
}
