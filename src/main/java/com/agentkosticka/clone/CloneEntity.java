package com.agentkosticka.clone;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.lang.reflect.Field;

public class CloneEntity extends OtherClientPlayerEntity {
    private boolean isSneaking = false;
    private Box defaultBoundingBox;

    public CloneEntity(ClientWorld clientWorld, GameProfile gameProfile) {
        super(clientWorld, gameProfile);
        copyDataFromLocalPlayer();
    }

    public void copyDataFromLocalPlayer() {
        ClientPlayerEntity localPlayer = MinecraftClient.getInstance().player;
        if (localPlayer != null) {
            updateDataFromClientPlayer(localPlayer);
        }
    }
    public void updateDataFromClientPlayer(ClientPlayerEntity localPlayer) {
        this.copyPositionAndRotation(localPlayer);
        this.copyFrom(localPlayer);

        this.setFireTicks(localPlayer.getFireTicks());
        this.setHealth(localPlayer.getHealth());
        this.setSneaking(localPlayer.isSneaking());
        this.setSprinting(false);
        this.noClip = true;


        //this.updateLimbs(0.5F);
        this.limbAnimator.updateLimbs(20, 0);
        defaultBoundingBox = localPlayer.getBoundingBox();

        this.bodyYaw = localPlayer.bodyYaw;
        this.headYaw = localPlayer.headYaw;

        this.activeItemStack = localPlayer.getActiveItem();
        this.capeX = localPlayer.capeX;
        this.capeY = localPlayer.capeY;
        this.capeZ = localPlayer.capeZ;
        this.fallDistance = localPlayer.fallDistance;
        isSneaking = localPlayer.isSneaking();
    }

    @Override
    public void tick() {
        MinecraftClient mc = MinecraftClient.getInstance();

        if(mc.player == null){
            super.tick();
            return;
        }

        float yaw = mc.player.headYaw;
        while (yaw > 180 || yaw < -180) {
            yaw -= 360;
            mc.player.headYaw -= 360;
        }

        double posDifference = getDistance(this.getPos(), mc.player.getPos());
        if(posDifference < 0.9){
            if(yaw < 45 && yaw > -45){
                this.setBoundingBox(defaultBoundingBox.offset(0, 0, 1.9).expand(0, 20, 0));
            }
            else if(yaw < 135 && yaw > 45){
                this.setBoundingBox(defaultBoundingBox.offset(-1.9, 0, 0).expand(0, 20, 0));
            }
            else if(yaw < -45 && yaw > -135){
                this.setBoundingBox(defaultBoundingBox.offset(1.9, 0, 0).expand(0, 20, 0));
            }
            else{
                this.setBoundingBox(defaultBoundingBox.offset(0, 0, -1.9).expand(0, 20, 0));
            }
        }
        else {
            this.setBoundingBox(defaultBoundingBox);
        }


        super.tick();
    }
    private double getDistance(Vec3d pos1, Vec3d pos2){
        double x = pos1.x - pos2.x;
        double z = pos1.z - pos2.z;
        return Math.sqrt(x*x + z*z);
    }

    @Override
    public boolean isInSneakingPose() {
        return isSneaking;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return false;
    }

    public void initialize(ClientWorld world) {
        // Set the world
        this.world = world;
    }
}
