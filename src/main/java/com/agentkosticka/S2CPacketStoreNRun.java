package com.agentkosticka;

import com.agentkosticka.event.KeyInputHandlerer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

public class S2CPacketStoreNRun {
    public static void releaseS2CPackets(MinecraftClient client){
        if(KeyInputHandlerer.interceptedS2CPackets.isEmpty()){
            return;
        }
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        if (networkHandler == null){
            return;
        }
        for (Packet<?> delayedPacket: KeyInputHandlerer.interceptedS2CPackets) {
           if(delayedPacket instanceof GameJoinS2CPacket){
                networkHandler.onGameJoin((GameJoinS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntitySpawnS2CPacket){
               networkHandler.onEntitySpawn((EntitySpawnS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ExperienceOrbSpawnS2CPacket){
               networkHandler.onExperienceOrbSpawn((ExperienceOrbSpawnS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityVelocityUpdateS2CPacket){
               networkHandler.onEntityVelocityUpdate((EntityVelocityUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityTrackerUpdateS2CPacket){
               networkHandler.onEntityTrackerUpdate((EntityTrackerUpdateS2CPacket) delayedPacket);
           }  else if(delayedPacket instanceof PlayerSpawnS2CPacket){
               networkHandler.onPlayerSpawn((PlayerSpawnS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityPositionS2CPacket){
               networkHandler.onEntityPosition((EntityPositionS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof UpdateSelectedSlotS2CPacket){
               networkHandler.onUpdateSelectedSlot((UpdateSelectedSlotS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityS2CPacket){
               networkHandler.onEntity((EntityS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntitySetHeadYawS2CPacket){
               networkHandler.onEntitySetHeadYaw((EntitySetHeadYawS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntitiesDestroyS2CPacket){
               networkHandler.onEntitiesDestroy((EntitiesDestroyS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerPositionLookS2CPacket){
               networkHandler.onPlayerPositionLook((PlayerPositionLookS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChunkDeltaUpdateS2CPacket){
               networkHandler.onChunkDeltaUpdate((ChunkDeltaUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChunkDataS2CPacket){
               networkHandler.onChunkData((ChunkDataS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChunkBiomeDataS2CPacket){
               networkHandler.onChunkBiomeData((ChunkBiomeDataS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof UnloadChunkS2CPacket){
               networkHandler.onUnloadChunk((UnloadChunkS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BlockUpdateS2CPacket){
               networkHandler.onBlockUpdate((BlockUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof DisconnectS2CPacket){
               networkHandler.onDisconnect((DisconnectS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ItemPickupAnimationS2CPacket){
               networkHandler.onItemPickupAnimation((ItemPickupAnimationS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof GameMessageS2CPacket){
               networkHandler.onGameMessage((GameMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChatMessageS2CPacket){
               networkHandler.onChatMessage((ChatMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ProfilelessChatMessageS2CPacket){
               networkHandler.onProfilelessChatMessage((ProfilelessChatMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof RemoveMessageS2CPacket){
               networkHandler.onRemoveMessage((RemoveMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityAnimationS2CPacket){
               networkHandler.onEntityAnimation((EntityAnimationS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof DamageTiltS2CPacket){
               networkHandler.onDamageTilt((DamageTiltS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldTimeUpdateS2CPacket){
               networkHandler.onWorldTimeUpdate((WorldTimeUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerSpawnPositionS2CPacket){
               networkHandler.onPlayerSpawnPosition((PlayerSpawnPositionS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityPassengersSetS2CPacket){
               networkHandler.onEntityPassengersSet((EntityPassengersSetS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityAttachS2CPacket){
               networkHandler.onEntityAttach((EntityAttachS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityStatusS2CPacket){
               networkHandler.onEntityStatus((EntityStatusS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityDamageS2CPacket){
               networkHandler.onEntityDamage((EntityDamageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof HealthUpdateS2CPacket){
               networkHandler.onHealthUpdate((HealthUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ExperienceBarUpdateS2CPacket){
               networkHandler.onExperienceBarUpdate((ExperienceBarUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerRespawnS2CPacket){
               networkHandler.onPlayerRespawn((PlayerRespawnS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ExplosionS2CPacket){
               networkHandler.onExplosion((ExplosionS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof OpenHorseScreenS2CPacket){
               networkHandler.onOpenHorseScreen((OpenHorseScreenS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof OpenScreenS2CPacket){
               networkHandler.onOpenScreen((OpenScreenS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ScreenHandlerSlotUpdateS2CPacket){
               networkHandler.onScreenHandlerSlotUpdate((ScreenHandlerSlotUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof InventoryS2CPacket){
               networkHandler.onInventory((InventoryS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SignEditorOpenS2CPacket){
               networkHandler.onSignEditorOpen((SignEditorOpenS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BlockEntityUpdateS2CPacket){
               networkHandler.onBlockEntityUpdate((BlockEntityUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ScreenHandlerPropertyUpdateS2CPacket){
               networkHandler.onScreenHandlerPropertyUpdate((ScreenHandlerPropertyUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityEquipmentUpdateS2CPacket){
               networkHandler.onEntityEquipmentUpdate((EntityEquipmentUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CloseScreenS2CPacket){
               networkHandler.onCloseScreen((CloseScreenS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BlockEventS2CPacket){
               networkHandler.onBlockEvent((BlockEventS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BlockBreakingProgressS2CPacket){
               networkHandler.onBlockBreakingProgress((BlockBreakingProgressS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof GameStateChangeS2CPacket){
               networkHandler.onGameStateChange((GameStateChangeS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof MapUpdateS2CPacket){
               networkHandler.onMapUpdate((MapUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldEventS2CPacket){
               networkHandler.onWorldEvent((WorldEventS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof AdvancementUpdateS2CPacket){
               networkHandler.onAdvancements((AdvancementUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SelectAdvancementTabS2CPacket){
               networkHandler.onSelectAdvancementTab((SelectAdvancementTabS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CommandTreeS2CPacket){
               networkHandler.onCommandTree((CommandTreeS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof StopSoundS2CPacket){
               networkHandler.onStopSound((StopSoundS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CommandSuggestionsS2CPacket){
               networkHandler.onCommandSuggestions((CommandSuggestionsS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SynchronizeRecipesS2CPacket){
               networkHandler.onSynchronizeRecipes((SynchronizeRecipesS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof LookAtS2CPacket){
               networkHandler.onLookAt((LookAtS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof NbtQueryResponseS2CPacket){
               networkHandler.onNbtQueryResponse((NbtQueryResponseS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof StatisticsS2CPacket){
               networkHandler.onStatistics((StatisticsS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof UnlockRecipesS2CPacket){
               networkHandler.onUnlockRecipes((UnlockRecipesS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityStatusEffectS2CPacket){
               networkHandler.onEntityStatusEffect((EntityStatusEffectS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SynchronizeTagsS2CPacket){
               networkHandler.onSynchronizeTags((SynchronizeTagsS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof FeaturesS2CPacket){
               networkHandler.onFeatures((FeaturesS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EndCombatS2CPacket){
               networkHandler.onEndCombat((EndCombatS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EnterCombatS2CPacket){
               networkHandler.onEnterCombat((EnterCombatS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof DeathMessageS2CPacket){
               networkHandler.onDeathMessage((DeathMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof DifficultyS2CPacket){
               networkHandler.onDifficulty((DifficultyS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SetCameraEntityS2CPacket){
               networkHandler.onSetCameraEntity((SetCameraEntityS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldBorderInitializeS2CPacket){
               networkHandler.onWorldBorderInitialize((WorldBorderInitializeS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldBorderCenterChangedS2CPacket){
               networkHandler.onWorldBorderCenterChanged((WorldBorderCenterChangedS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldBorderSizeChangedS2CPacket){
               networkHandler.onWorldBorderSizeChanged((WorldBorderSizeChangedS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldBorderWarningBlocksChangedS2CPacket){
               networkHandler.onWorldBorderWarningBlocksChanged((WorldBorderWarningBlocksChangedS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof WorldBorderWarningTimeChangedS2CPacket){
               networkHandler.onWorldBorderWarningTimeChanged((WorldBorderWarningTimeChangedS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ClearTitleS2CPacket){
               networkHandler.onTitleClear((ClearTitleS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ServerMetadataS2CPacket){
               networkHandler.onServerMetadata((ServerMetadataS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChatSuggestionsS2CPacket){
               networkHandler.onChatSuggestions((ChatSuggestionsS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof OverlayMessageS2CPacket){
               networkHandler.onOverlayMessage((OverlayMessageS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof TitleS2CPacket){
               networkHandler.onTitle((TitleS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SubtitleS2CPacket){
               networkHandler.onSubtitle((SubtitleS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof TitleFadeS2CPacket){
               networkHandler.onTitleFade((TitleFadeS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerListHeaderS2CPacket){
               networkHandler.onPlayerListHeader((PlayerListHeaderS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof RemoveEntityStatusEffectS2CPacket){
               networkHandler.onRemoveEntityStatusEffect((RemoveEntityStatusEffectS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerRemoveS2CPacket){
               networkHandler.onPlayerRemove((PlayerRemoveS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerListS2CPacket){
               networkHandler.onPlayerList((PlayerListS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerAbilitiesS2CPacket){
               networkHandler.onPlayerAbilities((PlayerAbilitiesS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlaySoundS2CPacket){
               networkHandler.onPlaySound((PlaySoundS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlaySoundFromEntityS2CPacket){
               networkHandler.onPlaySoundFromEntity((PlaySoundFromEntityS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ResourcePackSendS2CPacket){
               networkHandler.onResourcePackSend((ResourcePackSendS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BossBarS2CPacket){
               networkHandler.onBossBar((BossBarS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CooldownUpdateS2CPacket){
               networkHandler.onCooldownUpdate((CooldownUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof VehicleMoveS2CPacket){
               networkHandler.onVehicleMove((VehicleMoveS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof OpenWrittenBookS2CPacket){
               networkHandler.onOpenWrittenBook((OpenWrittenBookS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CustomPayloadS2CPacket){
               networkHandler.onCustomPayload((CustomPayloadS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ScoreboardObjectiveUpdateS2CPacket){
               networkHandler.onScoreboardObjectiveUpdate((ScoreboardObjectiveUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ScoreboardPlayerUpdateS2CPacket){
               networkHandler.onScoreboardPlayerUpdate((ScoreboardPlayerUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ScoreboardDisplayS2CPacket){
               networkHandler.onScoreboardDisplay((ScoreboardDisplayS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof TeamS2CPacket){
               networkHandler.onTeam((TeamS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ParticleS2CPacket){
               networkHandler.onParticle((ParticleS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayPingS2CPacket){
               networkHandler.onPing((PlayPingS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof EntityAttributesS2CPacket){
               networkHandler.onEntityAttributes((EntityAttributesS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof CraftFailedResponseS2CPacket){
               networkHandler.onCraftFailedResponse((CraftFailedResponseS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof LightUpdateS2CPacket){
               networkHandler.onLightUpdate((LightUpdateS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SetTradeOffersS2CPacket){
               networkHandler.onSetTradeOffers((SetTradeOffersS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChunkLoadDistanceS2CPacket){
               networkHandler.onChunkLoadDistance((ChunkLoadDistanceS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof SimulationDistanceS2CPacket){
               networkHandler.onSimulationDistance((SimulationDistanceS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof ChunkRenderDistanceCenterS2CPacket){
               networkHandler.onChunkRenderDistanceCenter((ChunkRenderDistanceCenterS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof PlayerActionResponseS2CPacket){
               networkHandler.onPlayerActionResponse((PlayerActionResponseS2CPacket) delayedPacket);
           } else if(delayedPacket instanceof BundleS2CPacket){
               networkHandler.onBundle((BundleS2CPacket) delayedPacket);
           }
        }
    }
}
