package com.agentkosticka.modmenu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class PacketDelayWithModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        // Implement the logic to create and return your custom config screen
        return (previous) -> {
            loadVariables();
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(previous)
                    .setTitle(Text.of("Packet Delay Settings"));
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("key.delaypackets.category.delaypackets"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // Retrieve the current value of showClone
            boolean showClone = ConfigValues.showClone;

            // Add the toggle entry to the category
            general.addEntry(entryBuilder.startBooleanToggle(Text.of("Show clone"), showClone)
                    .setDefaultValue(ConfigValues.showClone)
                    .setTooltip(Text.of("ON: Clone will spawn upon delaying outgoing packets\nOFF: Clone will not spawn"))
                    .setSaveConsumer((value) -> {
                        ConfigValues.showClone = value;
                        // Save the configuration to a file or any other persistence mechanism
                    })
                    .build());
            general.addEntry(entryBuilder.startBooleanToggle(Text.of("Send chat messages"), ConfigValues.chatFeedback)
                    .setDefaultValue(ConfigValues.chatFeedback)
                    .setTooltip(Text.of("ON: Chat messages will be shown\nOFF: Chat messages will be hidden"))
                            .setSaveConsumer((value) -> {
                                ConfigValues.chatFeedback = value;
                            })
                    .build());
            general.addEntry(
                    entryBuilder.startEnumSelector(
                            Text.of("Delay outgoing packets activation type"),
                            ConfigValues.thOption.class,
                            ConfigValues.delayOutgoing
                    )
                    .setDefaultValue(ConfigValues.delayOutgoing)
                    .setTooltip(Text.of("TOGGLE: Press the button to stop sending outgoing packets, press again to start sending again\nHOLD: Hold the button in order to delay outgoing packets, releasing will stop delaying"))
                    .setSaveConsumer((value) -> {
                        ConfigValues.delayOutgoing = value;
                    })
                    .build()
            );
            general.addEntry(
                    entryBuilder.startEnumSelector(
                                    Text.of("Disable incoming packets activation type"),
                                    ConfigValues.thOption.class,
                                    ConfigValues.delayIncoming
                            )
                            .setDefaultValue(ConfigValues.delayIncoming)
                            .setTooltip(Text.of("TOGGLE: Press the button to stop receiving incoming packets, press again to start receiving again\nHOLD: Hold the button in order to delay incoming packets, releasing will stop delaying"))
                            .setSaveConsumer((value) -> {
                                ConfigValues.delayIncoming= value;
                            })
                            .build()
            );
            general.addEntry(
                    entryBuilder.startEnumSelector(
                                    Text.of("Remove block update packets activation type"),
                                    ConfigValues.thOption.class,
                                    ConfigValues.delayBU
                            )
                            .setDefaultValue(ConfigValues.delayBU)
                            .setTooltip(Text.of("TOGGLE: Press the button to enable/disable block updates\nHOLD: Hold to disable block updates, release do enable"))
                            .setSaveConsumer((value) -> {
                                ConfigValues.delayBU = value;
                            })
                            .build()
            );
            general.addEntry(
                    entryBuilder.startEnumSelector(
                                    Text.of("Remove entity update packets activation type"),
                                    ConfigValues.thOption.class,
                                    ConfigValues.delayEU
                            )
                            .setDefaultValue(ConfigValues.delayEU)
                            .setTooltip(Text.of("TOGGLE: Press the button to enable/disable entity updates\nHOLD: Hold to disable entity updates, release do enable"))
                            .setSaveConsumer((value) -> {
                                ConfigValues.delayEU = value;
                            })
                            .build()
            );
            builder.setSavingRunnable(() -> {
                storeVariables();
            });
            return builder.build();
        };
    }
    public static String CONFIG_FILE_PATH = "./config/dp-data.json";
    public static void storeVariables() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            SaveLoadConfigValues saveLoadConfigValues = new SaveLoadConfigValues();
            saveLoadConfigValues.LoadFromConfig();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(saveLoadConfigValues, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadVariables() {
        try (FileReader reader = new FileReader(CONFIG_FILE_PATH)) {
            Gson gson = new Gson();
            SaveLoadConfigValues saveLoadConfigValues = gson.fromJson(reader, SaveLoadConfigValues.class);

            // Now update the ConfigValues class with the values from SaveLoadConfigValues
            saveLoadConfigValues.SaveToConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
