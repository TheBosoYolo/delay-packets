package com.agentkosticka.clone;


import com.agentkosticka.modmenu.ConfigValues;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;

public class CloneMaster {
    public static CloneEntity clone = null;

    public static void summonClone(boolean firstTime) {
        if(!ConfigValues.showClone){
            return;
        }
        destroyClone();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) {
            return;
        }

        ClientWorld world = MinecraftClient.getInstance().world;
        if (world == null) {
            return;
        }

        GameProfile cloneProfile = player.getGameProfile();
        clone = new CloneEntity(world, cloneProfile);
        clone.initialize(
                MinecraftClient.getInstance().getNetworkHandler(),
                world
        );

        // Set the clone's model to the player's model

        clone.setPos(player.getX(), player.getY(), player.getZ());

        // Copy any additional properties or data you want from the player to the clone

        world.addEntity(clone.getId(), clone);
        if(firstTime){
            summonClone(false);
        }

    }
    public static void summonClone(){
        summonClone(true);
    };
    public static void destroyClone() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && clone != null) {
            clone.setPos(player.getX(), player.getY(), player.getZ());
        }
        try {
            clone.remove(Entity.RemovalReason.DISCARDED);
            clone = null; // Reset the clone reference
        }
        catch (Exception e){

        }
    }
}
