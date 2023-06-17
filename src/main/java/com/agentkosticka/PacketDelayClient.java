package com.agentkosticka;

import com.agentkosticka.event.KeyInputHandlerer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PacketDelayClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        KeyInputHandlerer.registerMethods();
    }
}
