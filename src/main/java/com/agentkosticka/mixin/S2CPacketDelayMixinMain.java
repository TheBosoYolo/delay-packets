package com.agentkosticka.mixin;


import com.agentkosticka.event.KeyInputHandlerer;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class S2CPacketDelayMixinMain {
    @Inject(method = "onGameJoin", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(GameJoinS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntitySpawn", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onExperienceOrbSpawn", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ExperienceOrbSpawnS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityVelocityUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityVelocityUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }

    }
    @Inject(method = "onEntityTrackerUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityTrackerUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }

    }
    @Inject(method = "onPlayerSpawn", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerSpawnS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityPosition", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityPositionS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onUpdateSelectedSlot", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(UpdateSelectedSlotS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntity", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onEntitySetHeadYaw", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntitySetHeadYawS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onEntitiesDestroy", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntitiesDestroyS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlayerPositionLook", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerPositionLookS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onChunkDeltaUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChunkDeltaUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }

    }
    @Inject(method = "onChunkData", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChunkDataS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onChunkBiomeData", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChunkBiomeDataS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onUnloadChunk", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(UnloadChunkS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onBlockUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BlockUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }

    }
    @Inject(method = "onDisconnect", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(DisconnectS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onItemPickupAnimation", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ItemPickupAnimationS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(GameMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChatMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onProfilelessChatMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ProfilelessChatMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onRemoveMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(RemoveMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityAnimation", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityAnimationS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onDamageTilt", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(DamageTiltS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onWorldTimeUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldTimeUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlayerSpawnPosition", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerSpawnPositionS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onEntityPassengersSet", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityPassengersSetS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onEntityAttach", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityAttachS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onEntityStatus", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityStatusS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
        /*MinecraftClient client = MinecraftClient.getInstance();
        if(client == null || client.player == null || client.player.getEntityWorld() == null){

        }
        else{
            KeyInputHandlerer.sendMessage("Packet with code "+packet.getStatus() + ", is it the player? "+ (packet.getEntity(client.player.getEntityWorld()) == (Entity) client.player));
        }
        if(packet.getStatus() == 3){
            KeyInputHandlerer.sendMessage("Packet with code 3");

            if(client == null || client.player == null || client.player.getEntityWorld() == null){

            }
            else if(packet.getEntity(client.player.getEntityWorld()) == (Entity) client.player){
                KeyInputHandlerer.sendMessage("It was da playa");
                ci.cancel();
            }
        }*/
    }
    @Inject(method = "onEntityDamage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityDamageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onHealthUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(HealthUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onExperienceBarUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ExperienceBarUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlayerRespawn", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerRespawnS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onExplosion", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ExplosionS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onOpenHorseScreen", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(OpenHorseScreenS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onOpenScreen", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(OpenScreenS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onScreenHandlerSlotUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ScreenHandlerSlotUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onInventory", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(InventoryS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSignEditorOpen", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SignEditorOpenS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onBlockEntityUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BlockEntityUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onScreenHandlerPropertyUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ScreenHandlerPropertyUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityEquipmentUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityEquipmentUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onCloseScreen", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CloseScreenS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onBlockEvent", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BlockEventS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onBlockBreakingProgress", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BlockBreakingProgressS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onGameStateChange", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onMapUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(MapUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldEvent", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldEventS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onAdvancements", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(AdvancementUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSelectAdvancementTab", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SelectAdvancementTabS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onCommandTree", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CommandTreeS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onStopSound", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(StopSoundS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onCommandSuggestions", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CommandSuggestionsS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSynchronizeRecipes", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SynchronizeRecipesS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onLookAt", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(LookAtS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onNbtQueryResponse", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(NbtQueryResponseS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onStatistics", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(StatisticsS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onUnlockRecipes", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(UnlockRecipesS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityStatusEffect", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityStatusEffectS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onSynchronizeTags", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SynchronizeTagsS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onFeatures", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(FeaturesS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEndCombat", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EndCombatS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEnterCombat", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EnterCombatS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onDeathMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(DeathMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onDifficulty", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(DifficultyS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSetCameraEntity", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SetCameraEntityS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderInitialize", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderInitializeS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderCenterChanged", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderCenterChangedS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderInterpolateSize", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderInterpolateSizeS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderSizeChanged", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderSizeChangedS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderWarningBlocksChanged", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderWarningBlocksChangedS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onWorldBorderWarningTimeChanged", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(WorldBorderWarningTimeChangedS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onTitleClear", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ClearTitleS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onServerMetadata", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ServerMetadataS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onChatSuggestions", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChatSuggestionsS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onOverlayMessage", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(OverlayMessageS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onTitle", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(TitleS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSubtitle", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SubtitleS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onTitleFade", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(TitleFadeS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlayerListHeader", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerListHeaderS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onRemoveEntityStatusEffect", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(RemoveEntityStatusEffectS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onPlayerRemove", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerRemoveS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
    }
    @Inject(method = "onPlayerList", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerListS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    /*@Inject(method = "handlePlayerListAction", at = @At("HEAD"), cancellable = true)  //Ass method insert here
    private void modifyS2CPacket(PlayerListS2CPacket.Action action, PlayerListS2CPacket.Entry receivedEntry, PlayerListEntry currentEntry, CallbackInfo ci) {
        HandlePacket(action, receivedEntry, currentEntry, ci);

    }

    @Inject(method = "setPublicSession", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerListS2CPacket.Entry receivedEntry, PlayerListEntry currentEntry, CallbackInfo ci) {
        HandlePacket(receivedEntry, currentEntry, ci);

    }

    @Inject(method = "onKeepAlive", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(KeepAliveS2CPacket packet, CallbackInfo ci) {

    }*/
    @Inject(method = "onPlayerAbilities", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerAbilitiesS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlaySound", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlaySoundS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPlaySoundFromEntity", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlaySoundFromEntityS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onResourcePackSend", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ResourcePackSendS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onBossBar", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BossBarS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onCooldownUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CooldownUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onVehicleMove", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(VehicleMoveS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onOpenWrittenBook", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(OpenWrittenBookS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CustomPayloadS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onScoreboardObjectiveUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ScoreboardObjectiveUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onScoreboardPlayerUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ScoreboardPlayerUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onScoreboardDisplay", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ScoreboardDisplayS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onTeam", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(TeamS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onParticle", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ParticleS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onPing", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayPingS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onEntityAttributes", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(EntityAttributesS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableEUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onCraftFailedResponse", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(CraftFailedResponseS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onLightUpdate", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(LightUpdateS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onSetTradeOffers", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SetTradeOffersS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onChunkLoadDistance", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChunkLoadDistanceS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onSimulationDistance", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(SimulationDistanceS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onChunkRenderDistanceCenter", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(ChunkRenderDistanceCenterS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);
        if(KeyInputHandlerer.disableBUPackets){
            ci.cancel();
        }
    }
    @Inject(method = "onPlayerActionResponse", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(PlayerActionResponseS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    @Inject(method = "onBundle", at = @At("HEAD"), cancellable = true)
    private void modifyS2CPacket(BundleS2CPacket packet, CallbackInfo ci) {
        HandlePacket(packet, ci);

    }
    public void HandlePacket(Packet<?> packet, CallbackInfo ci){
        if(KeyInputHandlerer.holdS2CPackets){
            KeyInputHandlerer.interceptedS2CPackets.add(packet);
            ci.cancel();
        }
    }
}
