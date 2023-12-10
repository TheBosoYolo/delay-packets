package com.agentkosticka.event;

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
import static com.agentkosticka.S2CPacketStoreNRun.releaseS2CPackets;

public class KeyInputHandlerer {
    public static final String KEY_CATEGORY = "key.delaypackets.category.delaypackets";
    public static final String KEY_DELAY_OUTGOING = "key.delaypackets.keybind.delayoutgoing";
    public static final String KEY_DELAY_INCOMING = "key.delaypackets.keybind.delayincoming";
    public static final String KEY_DELAY_DISABLEBU = "key.delaypackets.keybind.disablebu";
    public static final String KEY_DELAY_DISABLEEU = "key.delaypackets.keybind.disableeu";

    public static List<Packet<?>> interceptedC2SPackets = new ArrayList<>();
    public static List<Packet<?>> interceptedS2CPackets = new ArrayList<>();

    public static KeyBinding delayOut;
    public static KeyBinding delayIn;
    public static KeyBinding disableBU;
    public static KeyBinding disableEU;

    public static boolean holdBUPackets = false;
    public static boolean holdEUPackets = false;
    public static boolean holdC2SPackets = false;

    public static boolean holdS2CPackets = false;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null){
                return;
            }
            switch (ConfigValues.delayOutgoing){
                case HOLD -> {
                    if(holdC2SPackets != delayOut.isPressed()) {
                        holdC2SPackets = delayOut.isPressed();

                        if(holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.c2s", true);
                            CloneMaster.summonClone();
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.c2s", true);
                            CloneMaster.destroyClone();
                        }
                        releaseC2SPackets(client);
                    }
                }
                case TOGGLE -> {
                    if(delayOut.wasPressed()){
                        holdC2SPackets = !holdC2SPackets;

                        if(holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.c2s", true);
                            CloneMaster.summonClone();
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.c2s", true);
                            CloneMaster.destroyClone();
                        }
                        releaseC2SPackets(client);
                    }
                }
            }
            switch (ConfigValues.delayIncoming){
                case HOLD -> {
                    if(holdS2CPackets != delayIn.isPressed()) {
                        holdS2CPackets = delayIn.isPressed();

                        if (holdC2SPackets) {
                            sendMessage("info.delaypackets.message.start.s2c", true);
                        } else {
                            sendMessage("info.delaypackets.message.stop.s2c", true);
                        }
                        releaseS2CPackets(client);
                    }
                }
                case TOGGLE -> {
                    if(delayIn.wasPressed()){
                        holdS2CPackets = !holdS2CPackets;

                        if(holdS2CPackets){
                            sendMessage("info.delaypackets.message.start.s2c", true);
                        }
                        else {
                            sendMessage("info.delaypackets.message.stop.s2c", true);
                        }
                        releaseS2CPackets(client);
                    }
                    if ((client.player.isDead() || !client.player.isAlive()) && holdS2CPackets){
                        sendMessage("info.delaypackets.message.stop.s2c", true);
                        holdS2CPackets = false;
                        releaseS2CPackets(client);
                    }
                }
            }
            switch (ConfigValues.delayBU){
                case HOLD -> {
                    if(disableBU.isPressed()) {
                        holdBUPackets = !holdBUPackets;

                        if(holdBUPackets) {
                            sendMessage("info.delaypackets.message.disabled.bu", true);
                        }
                        else {
                            sendMessage("info.delaypackets.message.enable.bu", true);
                        }
                    }
                }
                case TOGGLE -> {
                    if (disableBU.wasPressed()){
                        holdBUPackets = !holdBUPackets;

                        if (holdBUPackets) {
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
                        holdEUPackets = !holdEUPackets;

                        if (holdEUPackets) {
                            sendMessage("info.delaypackets.message.disable.eu", true);
                        } else {
                            sendMessage("info.delaypackets.message.enable.eu", true);
                        }
                    }
                }
                case TOGGLE -> {
                    if (disableEU.wasPressed()){
                        holdEUPackets = !holdEUPackets;
                        if (holdEUPackets) {
                            sendMessage("info.delaypackets.message.stop.eu", true);
                        } else {
                            sendMessage("info.delaypackets.message.start.eu", true);
                        }
                    }
                }
            }
        });
    }
    private static void releaseC2SPackets(MinecraftClient client) {
        if (interceptedC2SPackets.isEmpty()) {
            return;
        }
        for (Packet<?> packet : interceptedC2SPackets) {
            try {
                client.getNetworkHandler().sendPacket(packet);
            } catch (Exception e) {
                sendMessage("An error occurred when sending a packet! Error: " + e);
            }
        }
        interceptedC2SPackets = new ArrayList<>();
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
        delayOut = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_OUTGOING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY
        ));
        delayIn = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_INCOMING,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_CAPS_LOCK,
                KEY_CATEGORY
        ));
        disableBU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_DISABLEBU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_INSERT,
                KEY_CATEGORY
        ));
        disableEU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_DISABLEEU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_END,
                KEY_CATEGORY
        ));
        registerKeyInputs();
    }
}
