package com.agentkosticka.packets;

import com.agentkosticka.event.KeyInputHandlerer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.*;

import java.util.ArrayList;

import static com.agentkosticka.event.KeyInputHandlerer.interceptedC2SPackets;
import static com.agentkosticka.event.KeyInputHandlerer.sendMessage;

public class PacketStoreNRun {
    public static void releaseC2SPackets(MinecraftClient client) {
        if (interceptedC2SPackets.isEmpty()) {
            return;
        }
        for (Packet<?> packet : interceptedC2SPackets) {
            sendC2SPacket(packet, client);
        }
        interceptedC2SPackets = new ArrayList<>();
    }
    public static void sendC2SPacket(Packet<?> packet, MinecraftClient client){
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if(networkHandler == null) {
            sendMessage("An error occurred when sending a packet! Error: networkHandler = null");
            return;
        }
        try {
            client.getNetworkHandler().sendPacket(packet);
        } catch (Exception e) {
            sendMessage("An error occurred when sending a packet! Error: " + e);
        }
    }

    public static void releaseS2CPackets(MinecraftClient client){
        if(KeyInputHandlerer.interceptedS2CPackets.isEmpty()){
            return;
        }
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if (networkHandler == null){
            return;
        }
        for (Packet<?> packet: KeyInputHandlerer.interceptedS2CPackets) {
            sendS2CPacket(packet, client);
        }
    }
    public static void sendS2CPacket(Packet<?> packet, MinecraftClient client) {
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if(networkHandler == null) {
            sendMessage("An error occurred when sending a packet! Error: networkHandler = null");
            return;
        }
        if(packet instanceof GameJoinS2CPacket){
            networkHandler.onGameJoin((GameJoinS2CPacket) packet);
        } else if(packet instanceof EntitySpawnS2CPacket){
            networkHandler.onEntitySpawn((EntitySpawnS2CPacket) packet);
        } else if(packet instanceof ExperienceOrbSpawnS2CPacket){
            networkHandler.onExperienceOrbSpawn((ExperienceOrbSpawnS2CPacket) packet);
        } else if(packet instanceof EntityVelocityUpdateS2CPacket){
            networkHandler.onEntityVelocityUpdate((EntityVelocityUpdateS2CPacket) packet);
        } else if(packet instanceof EntityTrackerUpdateS2CPacket){
            networkHandler.onEntityTrackerUpdate((EntityTrackerUpdateS2CPacket) packet);
        }  else if(packet instanceof PlayerSpawnS2CPacket){
            networkHandler.onPlayerSpawn((PlayerSpawnS2CPacket) packet);
        } else if(packet instanceof EntityPositionS2CPacket){
            networkHandler.onEntityPosition((EntityPositionS2CPacket) packet);
        } else if(packet instanceof UpdateSelectedSlotS2CPacket){
            networkHandler.onUpdateSelectedSlot((UpdateSelectedSlotS2CPacket) packet);
        } else if(packet instanceof EntityS2CPacket){
            networkHandler.onEntity((EntityS2CPacket) packet);
        } else if(packet instanceof EntitySetHeadYawS2CPacket){
            networkHandler.onEntitySetHeadYaw((EntitySetHeadYawS2CPacket) packet);
        } else if(packet instanceof EntitiesDestroyS2CPacket){
            networkHandler.onEntitiesDestroy((EntitiesDestroyS2CPacket) packet);
        } else if(packet instanceof PlayerPositionLookS2CPacket){
            networkHandler.onPlayerPositionLook((PlayerPositionLookS2CPacket) packet);
        } else if(packet instanceof ChunkDeltaUpdateS2CPacket){
            networkHandler.onChunkDeltaUpdate((ChunkDeltaUpdateS2CPacket) packet);
        } else if(packet instanceof ChunkDataS2CPacket){
            networkHandler.onChunkData((ChunkDataS2CPacket) packet);
        } else if(packet instanceof ChunkBiomeDataS2CPacket){
            networkHandler.onChunkBiomeData((ChunkBiomeDataS2CPacket) packet);
        } else if(packet instanceof UnloadChunkS2CPacket){
            networkHandler.onUnloadChunk((UnloadChunkS2CPacket) packet);
        } else if(packet instanceof BlockUpdateS2CPacket){
            networkHandler.onBlockUpdate((BlockUpdateS2CPacket) packet);
        } else if(packet instanceof DisconnectS2CPacket){
            networkHandler.onDisconnect((DisconnectS2CPacket) packet);
        } else if(packet instanceof ItemPickupAnimationS2CPacket){
            networkHandler.onItemPickupAnimation((ItemPickupAnimationS2CPacket) packet);
        } else if(packet instanceof GameMessageS2CPacket){
            networkHandler.onGameMessage((GameMessageS2CPacket) packet);
        } else if(packet instanceof ChatMessageS2CPacket){
            networkHandler.onChatMessage((ChatMessageS2CPacket) packet);
        } else if(packet instanceof ProfilelessChatMessageS2CPacket){
            networkHandler.onProfilelessChatMessage((ProfilelessChatMessageS2CPacket) packet);
        } else if(packet instanceof RemoveMessageS2CPacket){
            networkHandler.onRemoveMessage((RemoveMessageS2CPacket) packet);
        } else if(packet instanceof EntityAnimationS2CPacket){
            networkHandler.onEntityAnimation((EntityAnimationS2CPacket) packet);
        } else if(packet instanceof DamageTiltS2CPacket){
            networkHandler.onDamageTilt((DamageTiltS2CPacket) packet);
        } else if(packet instanceof WorldTimeUpdateS2CPacket){
            networkHandler.onWorldTimeUpdate((WorldTimeUpdateS2CPacket) packet);
        } else if(packet instanceof PlayerSpawnPositionS2CPacket){
            networkHandler.onPlayerSpawnPosition((PlayerSpawnPositionS2CPacket) packet);
        } else if(packet instanceof EntityPassengersSetS2CPacket){
            networkHandler.onEntityPassengersSet((EntityPassengersSetS2CPacket) packet);
        } else if(packet instanceof EntityAttachS2CPacket){
            networkHandler.onEntityAttach((EntityAttachS2CPacket) packet);
        } else if(packet instanceof EntityStatusS2CPacket){
            networkHandler.onEntityStatus((EntityStatusS2CPacket) packet);
        } else if(packet instanceof EntityDamageS2CPacket){
            networkHandler.onEntityDamage((EntityDamageS2CPacket) packet);
        } else if(packet instanceof HealthUpdateS2CPacket){
            networkHandler.onHealthUpdate((HealthUpdateS2CPacket) packet);
        } else if(packet instanceof ExperienceBarUpdateS2CPacket){
            networkHandler.onExperienceBarUpdate((ExperienceBarUpdateS2CPacket) packet);
        } else if(packet instanceof PlayerRespawnS2CPacket){
            networkHandler.onPlayerRespawn((PlayerRespawnS2CPacket) packet);
        } else if(packet instanceof ExplosionS2CPacket){
            networkHandler.onExplosion((ExplosionS2CPacket) packet);
        } else if(packet instanceof OpenHorseScreenS2CPacket){
            networkHandler.onOpenHorseScreen((OpenHorseScreenS2CPacket) packet);
        } else if(packet instanceof OpenScreenS2CPacket){
            networkHandler.onOpenScreen((OpenScreenS2CPacket) packet);
        } else if(packet instanceof ScreenHandlerSlotUpdateS2CPacket){
            networkHandler.onScreenHandlerSlotUpdate((ScreenHandlerSlotUpdateS2CPacket) packet);
        } else if(packet instanceof InventoryS2CPacket){
            networkHandler.onInventory((InventoryS2CPacket) packet);
        } else if(packet instanceof SignEditorOpenS2CPacket){
            networkHandler.onSignEditorOpen((SignEditorOpenS2CPacket) packet);
        } else if(packet instanceof BlockEntityUpdateS2CPacket){
            networkHandler.onBlockEntityUpdate((BlockEntityUpdateS2CPacket) packet);
        } else if(packet instanceof ScreenHandlerPropertyUpdateS2CPacket){
            networkHandler.onScreenHandlerPropertyUpdate((ScreenHandlerPropertyUpdateS2CPacket) packet);
        } else if(packet instanceof EntityEquipmentUpdateS2CPacket){
            networkHandler.onEntityEquipmentUpdate((EntityEquipmentUpdateS2CPacket) packet);
        } else if(packet instanceof CloseScreenS2CPacket){
            networkHandler.onCloseScreen((CloseScreenS2CPacket) packet);
        } else if(packet instanceof BlockEventS2CPacket){
            networkHandler.onBlockEvent((BlockEventS2CPacket) packet);
        } else if(packet instanceof BlockBreakingProgressS2CPacket){
            networkHandler.onBlockBreakingProgress((BlockBreakingProgressS2CPacket) packet);
        } else if(packet instanceof GameStateChangeS2CPacket){
            networkHandler.onGameStateChange((GameStateChangeS2CPacket) packet);
        } else if(packet instanceof MapUpdateS2CPacket){
            networkHandler.onMapUpdate((MapUpdateS2CPacket) packet);
        } else if(packet instanceof WorldEventS2CPacket){
            networkHandler.onWorldEvent((WorldEventS2CPacket) packet);
        } else if(packet instanceof AdvancementUpdateS2CPacket){
            networkHandler.onAdvancements((AdvancementUpdateS2CPacket) packet);
        } else if(packet instanceof SelectAdvancementTabS2CPacket){
            networkHandler.onSelectAdvancementTab((SelectAdvancementTabS2CPacket) packet);
        } else if(packet instanceof CommandTreeS2CPacket){
            networkHandler.onCommandTree((CommandTreeS2CPacket) packet);
        } else if(packet instanceof StopSoundS2CPacket){
            networkHandler.onStopSound((StopSoundS2CPacket) packet);
        } else if(packet instanceof CommandSuggestionsS2CPacket){
            networkHandler.onCommandSuggestions((CommandSuggestionsS2CPacket) packet);
        } else if(packet instanceof SynchronizeRecipesS2CPacket){
            networkHandler.onSynchronizeRecipes((SynchronizeRecipesS2CPacket) packet);
        } else if(packet instanceof LookAtS2CPacket){
            networkHandler.onLookAt((LookAtS2CPacket) packet);
        } else if(packet instanceof NbtQueryResponseS2CPacket){
            networkHandler.onNbtQueryResponse((NbtQueryResponseS2CPacket) packet);
        } else if(packet instanceof StatisticsS2CPacket){
            networkHandler.onStatistics((StatisticsS2CPacket) packet);
        } else if(packet instanceof UnlockRecipesS2CPacket){
            networkHandler.onUnlockRecipes((UnlockRecipesS2CPacket) packet);
        } else if(packet instanceof EntityStatusEffectS2CPacket){
            networkHandler.onEntityStatusEffect((EntityStatusEffectS2CPacket) packet);
        } else if(packet instanceof SynchronizeTagsS2CPacket){
            networkHandler.onSynchronizeTags((SynchronizeTagsS2CPacket) packet);
        } else if(packet instanceof FeaturesS2CPacket){
            networkHandler.onFeatures((FeaturesS2CPacket) packet);
        } else if(packet instanceof EndCombatS2CPacket){
            networkHandler.onEndCombat((EndCombatS2CPacket) packet);
        } else if(packet instanceof EnterCombatS2CPacket){
            networkHandler.onEnterCombat((EnterCombatS2CPacket) packet);
        } else if(packet instanceof DeathMessageS2CPacket){
            networkHandler.onDeathMessage((DeathMessageS2CPacket) packet);
        } else if(packet instanceof DifficultyS2CPacket){
            networkHandler.onDifficulty((DifficultyS2CPacket) packet);
        } else if(packet instanceof SetCameraEntityS2CPacket){
            networkHandler.onSetCameraEntity((SetCameraEntityS2CPacket) packet);
        } else if(packet instanceof WorldBorderInitializeS2CPacket){
            networkHandler.onWorldBorderInitialize((WorldBorderInitializeS2CPacket) packet);
        } else if(packet instanceof WorldBorderCenterChangedS2CPacket){
            networkHandler.onWorldBorderCenterChanged((WorldBorderCenterChangedS2CPacket) packet);
        } else if(packet instanceof WorldBorderSizeChangedS2CPacket){
            networkHandler.onWorldBorderSizeChanged((WorldBorderSizeChangedS2CPacket) packet);
        } else if(packet instanceof WorldBorderWarningBlocksChangedS2CPacket){
            networkHandler.onWorldBorderWarningBlocksChanged((WorldBorderWarningBlocksChangedS2CPacket) packet);
        } else if(packet instanceof WorldBorderWarningTimeChangedS2CPacket){
            networkHandler.onWorldBorderWarningTimeChanged((WorldBorderWarningTimeChangedS2CPacket) packet);
        } else if(packet instanceof ClearTitleS2CPacket){
            networkHandler.onTitleClear((ClearTitleS2CPacket) packet);
        } else if(packet instanceof ServerMetadataS2CPacket){
            networkHandler.onServerMetadata((ServerMetadataS2CPacket) packet);
        } else if(packet instanceof ChatSuggestionsS2CPacket){
            networkHandler.onChatSuggestions((ChatSuggestionsS2CPacket) packet);
        } else if(packet instanceof OverlayMessageS2CPacket){
            networkHandler.onOverlayMessage((OverlayMessageS2CPacket) packet);
        } else if(packet instanceof TitleS2CPacket){
            networkHandler.onTitle((TitleS2CPacket) packet);
        } else if(packet instanceof SubtitleS2CPacket){
            networkHandler.onSubtitle((SubtitleS2CPacket) packet);
        } else if(packet instanceof TitleFadeS2CPacket){
            networkHandler.onTitleFade((TitleFadeS2CPacket) packet);
        } else if(packet instanceof PlayerListHeaderS2CPacket){
            networkHandler.onPlayerListHeader((PlayerListHeaderS2CPacket) packet);
        } else if(packet instanceof RemoveEntityStatusEffectS2CPacket){
            networkHandler.onRemoveEntityStatusEffect((RemoveEntityStatusEffectS2CPacket) packet);
        } else if(packet instanceof PlayerRemoveS2CPacket){
            networkHandler.onPlayerRemove((PlayerRemoveS2CPacket) packet);
        } else if(packet instanceof PlayerListS2CPacket){
            networkHandler.onPlayerList((PlayerListS2CPacket) packet);
        } else if(packet instanceof PlayerAbilitiesS2CPacket){
            networkHandler.onPlayerAbilities((PlayerAbilitiesS2CPacket) packet);
        } else if(packet instanceof PlaySoundS2CPacket){
            networkHandler.onPlaySound((PlaySoundS2CPacket) packet);
        } else if(packet instanceof PlaySoundFromEntityS2CPacket){
            networkHandler.onPlaySoundFromEntity((PlaySoundFromEntityS2CPacket) packet);
        } else if(packet instanceof ResourcePackSendS2CPacket){
            networkHandler.onResourcePackSend((ResourcePackSendS2CPacket) packet);
        } else if(packet instanceof BossBarS2CPacket){
            networkHandler.onBossBar((BossBarS2CPacket) packet);
        } else if(packet instanceof CooldownUpdateS2CPacket){
            networkHandler.onCooldownUpdate((CooldownUpdateS2CPacket) packet);
        } else if(packet instanceof VehicleMoveS2CPacket){
            networkHandler.onVehicleMove((VehicleMoveS2CPacket) packet);
        } else if(packet instanceof OpenWrittenBookS2CPacket){
            networkHandler.onOpenWrittenBook((OpenWrittenBookS2CPacket) packet);
        } else if(packet instanceof CustomPayloadS2CPacket){
            networkHandler.onCustomPayload((CustomPayloadS2CPacket) packet);
        } else if(packet instanceof ScoreboardObjectiveUpdateS2CPacket){
            networkHandler.onScoreboardObjectiveUpdate((ScoreboardObjectiveUpdateS2CPacket) packet);
        } else if(packet instanceof ScoreboardPlayerUpdateS2CPacket){
            networkHandler.onScoreboardPlayerUpdate((ScoreboardPlayerUpdateS2CPacket) packet);
        } else if(packet instanceof ScoreboardDisplayS2CPacket){
            networkHandler.onScoreboardDisplay((ScoreboardDisplayS2CPacket) packet);
        } else if(packet instanceof TeamS2CPacket){
            networkHandler.onTeam((TeamS2CPacket) packet);
        } else if(packet instanceof ParticleS2CPacket){
            networkHandler.onParticle((ParticleS2CPacket) packet);
        } else if(packet instanceof PlayPingS2CPacket){
            networkHandler.onPing((PlayPingS2CPacket) packet);
        } else if(packet instanceof EntityAttributesS2CPacket){
            networkHandler.onEntityAttributes((EntityAttributesS2CPacket) packet);
        } else if(packet instanceof CraftFailedResponseS2CPacket){
            networkHandler.onCraftFailedResponse((CraftFailedResponseS2CPacket) packet);
        } else if(packet instanceof LightUpdateS2CPacket){
            networkHandler.onLightUpdate((LightUpdateS2CPacket) packet);
        } else if(packet instanceof SetTradeOffersS2CPacket){
            networkHandler.onSetTradeOffers((SetTradeOffersS2CPacket) packet);
        } else if(packet instanceof ChunkLoadDistanceS2CPacket){
            networkHandler.onChunkLoadDistance((ChunkLoadDistanceS2CPacket) packet);
        } else if(packet instanceof SimulationDistanceS2CPacket){
            networkHandler.onSimulationDistance((SimulationDistanceS2CPacket) packet);
        } else if(packet instanceof ChunkRenderDistanceCenterS2CPacket){
            networkHandler.onChunkRenderDistanceCenter((ChunkRenderDistanceCenterS2CPacket) packet);
        } else if(packet instanceof PlayerActionResponseS2CPacket){
            networkHandler.onPlayerActionResponse((PlayerActionResponseS2CPacket) packet);
        } else if(packet instanceof BundleS2CPacket){
            networkHandler.onBundle((BundleS2CPacket) packet);
        }
    }
}
