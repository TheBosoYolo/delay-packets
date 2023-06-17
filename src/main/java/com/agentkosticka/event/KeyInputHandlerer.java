package com.agentkosticka.event;

import com.agentkosticka.clone.CloneMaster;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityPose;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static com.agentkosticka.S2CPacketStoreNRun.allDelayedS2CPackets;
import static com.agentkosticka.S2CPacketStoreNRun.sendAllS2CPackets;

public class KeyInputHandlerer {
    public static final String KEY_CATEGORY = "key.delaypackets.category.delaypackets";
    public static final String KEY_DELAY_OUTGOING = "key.delaypackets.keybind.delayoutgoing";
    public static final String KEY_DELAY_INCOMING = "key.delaypackets.keybind.delayincoming";
    public static final String KEY_DELAY_DISABLEDEATH = "key.delaypackets.keybind.disabledeath";
    public static final String KEY_DELAY_DISABLEBU = "key.delaypackets.keybind.disablebu";
    public static final String KEY_DELAY_DISABLEEU = "key.delaypackets.keybind.disableeu";

    public static KeyBinding delayOut;
    public static KeyBinding delayIn;
    public static KeyBinding disableDeath;
    public static KeyBinding disableBU;
    public static KeyBinding disableEU;
    public static boolean disableDeathScreen = false;
    public static boolean disableBlockUpdate = false;
    public static boolean disableEntityUpdate = false;
    public static List<Packet<?>> interceptedC2SPackets = new ArrayList<>();
    private static boolean wasItPressedC2S = false;
    private static boolean wasItReleasedC2S = true;

    public static boolean saveAndRemoveC2S = false;

    private static boolean delayC2S = false;
    public static List<Packet<?>> interceptedS2CPackets = new ArrayList<>();
    private static boolean wasItPressedS2C = false;
    private static boolean wasItReleasedS2C = true;

    public static boolean saveAndRemoveS2C = false;

    private static boolean delayS2C = false;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null){
               return;
            }
            if (delayOut.isPressed()) {
                if (wasItReleasedC2S){
                    sendMessage("info.delaypackets.message.start.s2c", true);
                    CloneMaster.summonClone();
                    //start noting all outgoing (C2S) packets, save them to a list and cancel their send requests
                    startListingC2S = true;
                    wasItReleasedC2S = false;
                }
                wasItPressedC2S = true;
            }
            else if (wasItPressedC2S){
                sendMessage("info.delaypackets.message.stop.s2c", true);
                CloneMaster.destroyClone();
                //send all saved packets at once
                startListingC2S = false;
                wasItPressedC2S = false;
                wasItReleasedC2S = true;
            }
            else{
                wasItReleasedC2S = true;
            }
            delayC2SPackets(client);
            if (disableBU.wasPressed()){
                disableBlockUpdate = !disableBlockUpdate;
                if (disableBlockUpdate) {
                    sendMessage("info.delaypackets.message.stop.bu", true);
                } else {
                    sendMessage("info.delaypackets.message.start.bu", true);
                }
            }
            if (disableEU.wasPressed()){
                disableEntityUpdate = !disableEntityUpdate;
                if (disableEntityUpdate) {
                    sendMessage("info.delaypackets.message.stop.eu", true);
                } else {
                    sendMessage("info.delaypackets.message.start.eu", true);
                }
            }
            if (disableDeath.wasPressed()){
                disableDeathScreen = !disableDeathScreen;
                if (disableDeathScreen) {
                    sendMessage("info.delaypackets.message.stop.death", true);
                } else {
                    sendMessage("info.delaypackets.message.start.death", true);
                }
            }
            if (delayIn.isPressed()) {
                if (wasItReleasedS2C){
                    sendMessage("info.delaypackets.message.start.c2s", true);
                    //start noting all outgoing (S2C) packets, save them to a list and cancel their send requests
                    startListingS2C = true;
                    wasItReleasedS2C = false;
                }
                wasItPressedS2C = true;
            }
            else if (wasItPressedS2C){
                sendMessage("info.delaypackets.message.stop.c2s", true);
                //send all saved packets at once
                startListingS2C = false;
                wasItPressedS2C = false;
                wasItReleasedS2C = true;
            }
            else{
                wasItReleasedS2C = true;
            }
            delayS2CPackets(client);
        });
    }




    private static boolean startListingC2S = false;
    private static void delayC2SPackets(MinecraftClient client){
        if (startListingC2S){
            saveAndRemoveC2S = true;
        }
        else {
            if(interceptedC2SPackets.isEmpty()){
                return;
            }
            saveAndRemoveC2S = false;
            for (Packet<?> packet: interceptedC2SPackets) {
                try{
                    client.getNetworkHandler().sendPacket(packet);
                }
                catch (Exception e){
                    sendMessage("An error occurred when sending a packet! Error: "+e);
                }
            }
            interceptedC2SPackets = new ArrayList<>();
        }
    }
    private static boolean startListingS2C = false;
    private static void delayS2CPackets(MinecraftClient client) {
        if (startListingS2C) {
            saveAndRemoveS2C = true;
        } else {
            saveAndRemoveS2C = false;
            sendAllS2CPackets();
            allDelayedS2CPackets = new ArrayList<>();
        }
    }
    public static void sendMessage(String string){
        sendMessage(string, false);
    }
    public static void sendMessage(String string, boolean isItAKey){
        MinecraftClient client = null;
        try {
            client = MinecraftClient.getInstance();
        }
        catch (Exception e) {
            return;
        }
        if(!isItAKey){
            client.player.sendMessage(Text.of(string));
        }
        else{
            client.player.sendMessage(Text.translatable(string));
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
        disableDeath = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DELAY_DISABLEDEATH,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_HOME,
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
