package net.sleeplessdev.smarthud.util;

import net.minecraft.item.ItemStack;
import net.sleeplessdev.smarthud.config.WhitelistParser;

import java.util.List;

public final class StackHelper {
    private StackHelper() {}

    public static boolean isWhitelisted(ItemStack stack, int dimension) {
        for (final CachedItem item : WhitelistParser.getWhitelist()) {
            if (item.matchesStack(stack, true) && item.matchesDimension(dimension)) {
                return true;
            }
        }
        return false;
    }

    public static void processStack(List<CachedItem> cache, ItemStack stack, boolean mergeDuplicates) {
        final int count = stack.getCount();
        boolean shouldCache = true;


        for (final CachedItem item : cache) {
            if (item.matchesStack(stack, false) && mergeDuplicates) {
                item.setCount(item.getCount() + count);
                shouldCache = false;
                break;
            }
        }

        if (shouldCache) cache.add(new CachedItem(stack, count));
    }
}
