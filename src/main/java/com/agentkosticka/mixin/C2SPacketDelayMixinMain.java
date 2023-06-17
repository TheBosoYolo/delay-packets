package com.agentkosticka.mixin;

import com.agentkosticka.event.KeyInputHandlerer;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.KeepAliveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class C2SPacketDelayMixinMain {



	@Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
	private void interceptSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
		if (KeyInputHandlerer.saveAndRemoveC2S) {
			if(!packet.getClass().equals(KeepAliveC2SPacket.class)){
				KeyInputHandlerer.interceptedC2SPackets.add(packet);
				callbackInfo.cancel();
			}
		}
	}
}