package com.agentkosticka.clone;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.stat.StatHandler;
import net.minecraft.util.math.Box;

public class CloneEntity extends OtherClientPlayerEntity {
    private boolean isSneaking = false;
    private ClientPlayNetworkHandler networkHandler;

    public CloneEntity(ClientWorld clientWorld, GameProfile gameProfile) {
        super(clientWorld, gameProfile);
        copyDataFromLocalPlayer();
    }

    private void copyDataFromLocalPlayer() {
        ClientPlayerEntity localPlayer = MinecraftClient.getInstance().player;
        if (localPlayer != null) {
            // Copy necessary data from the local player to the clone entity
            this.copyPositionAndRotation(localPlayer);
            this.copyFrom(localPlayer);

            this.setFireTicks(localPlayer.getFireTicks());
            this.setHealth(localPlayer.getHealth());
            this.setSneaking(localPlayer.isSneaking());
            this.setSprinting(false);
            this.noClip = true;

            this.bodyYaw = localPlayer.bodyYaw;
            this.activeItemStack = localPlayer.getActiveItem();
            this.capeX = localPlayer.capeX;
            this.capeY = localPlayer.capeY;
            this.capeZ = localPlayer.capeZ;
            this.fallDistance = localPlayer.fallDistance;
            this.headYaw = localPlayer.headYaw;

            isSneaking = localPlayer.isSneaking();
            // Add any additional data you want to copy
        }
    }
    @Override
    public boolean isInSneakingPose() {
        return isSneaking;
    }

    public void initialize(ClientPlayNetworkHandler networkHandler, ClientWorld world) {
        // Set the network handler
        this.networkHandler = networkHandler;

        // Set the world
        this.world = world;

    }


    public boolean damage(DamageSource source, float amount) {
        // Prevent the clone entity from taking damage
        return false;
    }
}
