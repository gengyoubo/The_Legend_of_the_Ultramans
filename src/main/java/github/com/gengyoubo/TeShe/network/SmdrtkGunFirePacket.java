package github.com.gengyoubo.TeShe.network;

import github.com.gengyoubo.TeShe.item.SmdrtkGunItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SmdrtkGunFirePacket
{
    public static void encode(SmdrtkGunFirePacket packet, FriendlyByteBuf buffer)
    {
    }

    public static SmdrtkGunFirePacket decode(FriendlyByteBuf buffer)
    {
        return new SmdrtkGunFirePacket();
    }

    public static void handle(SmdrtkGunFirePacket packet, Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                SmdrtkGunItem.tryFireFromLeftClick(player);
            }
        });
        context.setPacketHandled(true);
    }
}
