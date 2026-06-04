package github.com.gengyoubo.TeShe.network;

import github.com.gengyoubo.TeShe.client.ClientPacketHandlers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ItemActivationPacket
{
    private final ItemStack stack;

    public ItemActivationPacket(ItemStack stack)
    {
        this.stack = stack;
    }

    public static void encode(ItemActivationPacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeItem(packet.stack);
    }

    public static ItemActivationPacket decode(FriendlyByteBuf buffer)
    {
        return new ItemActivationPacket(buffer.readItem());
    }

    public static void handle(ItemActivationPacket packet, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(
                Dist.CLIENT,
                () -> () -> ClientPacketHandlers.displayItemActivation(packet.stack)
        ));
        context.setPacketHandled(true);
    }
}
