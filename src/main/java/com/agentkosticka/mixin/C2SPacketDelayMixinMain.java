package com.agentkosticka.mixin;

import com.agentkosticka.clone.CloneMaster;
import com.agentkosticka.event.KeyInputHandlerer;
import com.agentkosticka.packets.PacketStoreNRun;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.KeepAliveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mixin(ClientPlayNetworkHandler.class)
public class C2SPacketDelayMixinMain {
	@Shadow private ClientConnection connection;

	@Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
	private void interceptSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
		if (KeyInputHandlerer.holdC2SPackets) {
			if(!packet.getClass().equals(KeepAliveC2SPacket.class)){
				KeyInputHandlerer.interceptedC2SPackets.add(packet);
				callbackInfo.cancel();
			}
		}
		if(KeyInputHandlerer.delayC2SPackets) {
			MinecraftClient mc = MinecraftClient.getInstance();
			ClientPlayerEntity test = new ClientPlayerEntity(mc, mc.world, mc.getNetworkHandler(), mc.player.getStatHandler(), mc.player.getRecipeBook(), mc.player.isSneaking(), false);
			test.copyFrom(mc.player);

			ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
			service.schedule(() -> {
				connection.send(packet);

				CloneMaster.updateClone(test);
			}, 1000, TimeUnit.MILLISECONDS);

			service.shutdown();

			callbackInfo.cancel();
		}
	}
}