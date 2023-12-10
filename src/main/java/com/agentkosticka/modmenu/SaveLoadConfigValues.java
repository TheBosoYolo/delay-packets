package com.agentkosticka.modmenu;

public class SaveLoadConfigValues {
    public boolean showClone = true;
    public boolean chatFeedback = true;

    public ConfigValues.thOption delayOutgoing = ConfigValues.thOption.HOLD;
    public ConfigValues.thOption delayIncoming = ConfigValues.thOption.HOLD;
    public ConfigValues.thOption delayBU = ConfigValues.thOption.TOGGLE;
    public ConfigValues.thOption delayEU = ConfigValues.thOption.TOGGLE;

    public void LoadFromConfig(){
        this.delayOutgoing = ConfigValues.delayOutgoing;
        this.delayIncoming = ConfigValues.delayIncoming;
        this.chatFeedback = ConfigValues.chatFeedback;
        this.showClone = ConfigValues.showClone;
        this.delayBU = ConfigValues.delayBU;
        this.delayEU = ConfigValues.delayEU;
    }
    public void SaveToConfig(){
        ConfigValues.delayOutgoing = this.delayOutgoing;
        ConfigValues.delayIncoming = this.delayIncoming;
        ConfigValues.chatFeedback = this.chatFeedback;
        ConfigValues.showClone = this.showClone;
        ConfigValues.delayBU = this.delayBU;
        ConfigValues.delayEU = this.delayEU;
    }
}
