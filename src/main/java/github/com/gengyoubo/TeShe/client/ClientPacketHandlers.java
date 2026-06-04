package github.com.gengyoubo.TeShe.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public final class ClientPacketHandlers
{
    private ClientPacketHandlers()
    {
    }

    public static void displayItemActivation(ItemStack stack)
    {
        Minecraft.getInstance().gameRenderer.displayItemActivation(stack);
    }
}
