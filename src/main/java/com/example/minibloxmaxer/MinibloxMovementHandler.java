package com.example.minibloxmaxer;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Locale;

public class MinibloxMovementHandler {

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.player == mc.thePlayer && mc.thePlayer != null) {
            try {
                String packetName = "PlayerPositionAndRotation";
                String jsonData = String.format(Locale.US, 
                    "{\"x\":%.4f,\"y\":%.4f,\"z\":%.4f,\"yaw\":%.2f,\"pitch\":%.2f,\"onGround\":%b}", 
                    mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, 
                    mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, mc.thePlayer.onGround
                );

                PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
                packetBuffer.writeString(packetName);
                packetBuffer.writeString(jsonData);

                C17PacketCustomPayload customPacket = new C17PacketCustomPayload("miniblox:send_packet", packetBuffer);
                
                if (mc.thePlayer.sendQueue != null) {
                    mc.thePlayer.sendQueue.addToSendQueue(customPacket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
