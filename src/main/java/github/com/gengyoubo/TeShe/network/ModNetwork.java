package github.com.gengyoubo.TeShe.network;

import github.com.gengyoubo.TeShe.TE;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class ModNetwork
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TE.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId;

    private ModNetwork()
    {
    }

    public static void register()
    {
        CHANNEL.messageBuilder(SmdrtkGunFirePacket.class, packetId++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SmdrtkGunFirePacket::encode)
                .decoder(SmdrtkGunFirePacket::decode)
                .consumerMainThread(SmdrtkGunFirePacket::handle)
                .add();
        CHANNEL.messageBuilder(ItemActivationPacket.class, packetId++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ItemActivationPacket::encode)
                .decoder(ItemActivationPacket::decode)
                .consumerMainThread(ItemActivationPacket::handle)
                .add();
    }

    public static void sendItemActivation(ServerPlayer player, ItemStack stack)
    {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new ItemActivationPacket(stack));
    }
}
