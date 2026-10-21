package com.example.minibloxmaxer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "minibloxmaxer", version = "1.0", acceptedMinecraftVersions = "")
public class MinibloxMod {
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MinibloxMovementHandler());
    }
}
