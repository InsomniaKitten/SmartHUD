package net.sleeplessdev.smarthud.util;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.sleeplessdev.smarthud.SmartHUD;

@EventBusSubscriber(modid = SmartHUD.ID, value = Side.CLIENT)
public final class TickListener {
    private static long ticksElapsed;

    private TickListener() {}

    @SubscribeEvent
    protected static void onClientTick(ClientTickEvent event) {
        if (event.phase == Phase.END && !FMLClientHandler.instance().getClient().isGamePaused()) {
            TickListener.ticksElapsed++;
        }
    }

    public static long getTicksElapsed() {
        return TickListener.ticksElapsed;
    }
}
