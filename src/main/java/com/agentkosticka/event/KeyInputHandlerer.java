package com.agentkosticka.event;

import com.agentkosticka.packets.PacketStoreNRun;
import com.agentkosticka.clone.CloneMaster;
import com.agentkosticka.modmenu.ConfigValues;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.Packet;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class KeyInputHandlerer {
    public static final String KEY_CATEGORY = "key.delaypackets.category.main";
    public static final String KEY_HOLD_OUTGOING = "key.delaypackets.keybinding.hold.outgoing";
    public static final String KEY_HOLD_INCOMING = "key.delaypackets.keybinding.hold.incoming";
    public static final String KEY_DISABLE_BU = "key.delaypackets.keybinding.disable.bu";
    public static final String KEY_DISABLE_EU = "key.delaypackets.keybinding.disable.eu";
    public static final String KEY_DELAY_OUTGOING = "key.delaypackets.keybinding.delay.incoming";

    public static List<Packet<?>> interceptedC2SPackets = new ArrayList<>();
    public static List<Packet<?>> interceptedS2CPackets = new ArrayList<>();

    public static KeyBinding holdOut;
    public static KeyBinding holdIn;
    public static KeyBinding disableBU;
    public static KeyBinding disableEU;
    public static KeyBinding delayOut;

    public static boolean disableBUPackets = false;
    public static boolean disableEUPackets = false;
    public static boolean holdC2SPackets = false;
    public static boolean holdS2CPackets = false;
    public static boolean delayC2SPackets = false;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client == null || client.player == null){
                return;
            }
            switch (ConfigValues.delayOutgoing){
                case HOLD -> {
                    if(holdC2SPackets != holdOut.isPressed()) {
                        holdC2SPackets = holdOut.isPressed();

                        if(holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.c2s", true);
                            CloneMaster.summonClone();
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.c2s", true);
                            CloneMaster.destroyClone();
                        }
                        PacketStoreNRun.releaseC2SPackets(client);
                    }
                }
                case TOGGLE -> {
                    if(holdOut.wasPressed()){
                        holdC2SPackets = !holdC2SPackets;

                        if(holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.c2s", true);
                            CloneMaster.summonClone();
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.c2s", true);
                            CloneMaster.destroyClone();
                        }
                        PacketStoreNRun.releaseC2SPackets(client);
                    }
                }
            }
            switch (ConfigValues.delayIncoming){
                case HOLD -> {
                    if(holdS2CPackets != holdIn.isPressed()) {
                        holdS2CPackets = holdIn.isPressed();

                        if (holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.s2c", true);
                        } else {
                            sendMessage("info.delaypackets.message.stop.s2c", true);
                        }
                        PacketStoreNRun.releaseS2CPackets(client);
                    }
                }
                case TOGGLE -> {
                    if(holdIn.wasPressed()){
                        holdS2CPackets = !holdS2CPackets;

                        if(holdS2CPackets){
                            sendMessage("info.delaypackets.message.start.s2c", true);
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.s2c", true);
                        }
                        PacketStoreNRun.releaseS2CPackets(client);
                    }
                    if ((client.player.isDead() || !client.player.isAlive()) && holdS2CPackets){
                        sendMessage("info.delaypackets.message.stop.s2c", true);
                        holdS2CPackets = false;
                        PacketStoreNRun.releaseS2CPackets(client);
                    }
                }
            }
            switch (ConfigValues.delayBU){
                case HOLD -> {
                    if(disableBU.isPressed()) {
                        disableBUPackets = !disableBUPackets;

                        if(disableBUPackets) {
                            sendMessage("info.delaypackets.message.disabled.bu", true);
                        }
                        else {
                            sendMessage("info.delaypackets.message.enable.bu", true);
                        }
                    }
                }
                case TOGGLE -> {
                    if (disableBU.wasPressed()){
                        disableBUPackets = !disableBUPackets;

                        if (disableBUPackets) {
                            sendMessage("info.delaypackets.message.disable.bu", true);
                        } else {
                            sendMessage("info.delaypackets.message.enable.bu", true);
                        }
                    }
                }
            }
            switch (ConfigValues.delayEU){
                case HOLD -> {
                    if (disableEU.isPressed()) {
                        disableEUPackets = !disableEUPackets;

                        if (disableEUPackets) {
                            sendMessage("info.delaypackets.message.disable.eu", true);
                        } else {
                            sendMessage("info.delaypackets.message.enable.eu", true);
                        }
                    }
                }
                case TOGGLE -> {
                    if (disableEU.wasPressed()){
                        disableEUPackets = !disableEUPackets;
                        if (disableEUPackets) {
                            sendMessage("info.delaypackets.message.stop.eu", true);
                        } else {
                            sendMessage("info.delaypackets.message.start.eu", true);
                        }
                    }
                }
            }
            if(delayC2SPackets != delayOut.isPressed()) {
                delayC2SPackets = delayOut.isPressed();
                if(delayC2SPackets) {
                    CloneMaster.summonClone();
                    sendMessage("Delaying");
                }
                else {
                    CloneMaster.destroyClone();
                    sendMessage("Processing normally");
                }
            }
        });
    }
    public static void sendMessage(String string){
        sendMessage(string, false);
    }
    public static void sendMessage(String string, boolean isItAKey){
        if(!ConfigValues.chatFeedback){
            return;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if(player == null){
            return;
        }
        if(!isItAKey){
            player.sendMessage(Text.of("§9[Delay Packets]§r " + string));
        }
        else{
            Text prefix = Text.of("§9[Delay Packets]§r ");
            Text translatable = Text.translatable(string);

            String prefixJson = Text.Serializer.toJson(prefix);
            String translatableJson = Text.Serializer.toJson(translatable);

            String joinedJson = "[" + prefixJson + "," + translatableJson + "]";
            Text joinedText = Text.Serializer.fromJson(joinedJson);

            client.player.sendMessage(joinedText);
        }
    }
    public static void registerMethods(){
        holdOut = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HOLD_OUTGOING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY
        ));
        holdIn = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_HOLD_INCOMING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_CAPS_LOCK,
                KEY_CATEGORY
        ));
        disableBU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISABLE_BU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_INSERT,
                KEY_CATEGORY
        ));
        disableEU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISABLE_EU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_END,
                KEY_CATEGORY
        ));
        delayOut = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_OUTGOING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY
        ));
        registerKeyInputs();
    }
}
