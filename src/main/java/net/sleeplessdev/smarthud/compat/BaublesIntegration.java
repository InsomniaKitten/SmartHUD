package net.sleeplessdev.smarthud.compat;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.sleeplessdev.smarthud.SmartHUD;
import net.sleeplessdev.smarthud.util.CachedItem;
import net.sleeplessdev.smarthud.util.StackHelper;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = SmartHUD.ID, value = Side.CLIENT)
public final class BaublesIntegration {
    private static List<CachedItem> baubles = new ArrayList<>();

    private BaublesIntegration() {}

    public static ImmutableList<CachedItem> getBaubles() {
        return ImmutableList.copyOf(BaublesIntegration.baubles);
    }

    @SubscribeEvent
    @Optional.Method(modid = "baubles")
    protected static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        final IBaublesItemHandler handler = BaublesApi.getBaublesHandler(event.player);
        final int dim = event.player.dimension;
        final List<CachedItem> baubleCache = new ArrayList<>();

        for (int slot = 0; slot < handler.getSlots(); ++slot) {
            final ItemStack bauble = handler.getStackInSlot(slot).copy();

            if (!bauble.isEmpty() && StackHelper.isWhitelisted(bauble, dim)) {
                StackHelper.processStack(baubleCache, bauble);
            }
        }

        BaublesIntegration.baubles = baubleCache;
    }
}
