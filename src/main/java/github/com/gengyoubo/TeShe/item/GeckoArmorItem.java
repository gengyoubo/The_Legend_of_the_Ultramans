package github.com.gengyoubo.TeShe.item;

import github.com.gengyoubo.TeShe.client.renderer.GenericGeoArmorRenderer;
import github.com.gengyoubo.TeShe.client.renderer.GenericGeoArmorItemRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class GeckoArmorItem extends ArmorItem implements GeoItem, GeckoModelProvider
{
    private final String geckoModelName;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GeckoArmorItem(String geckoModelName, ArmorMaterial material, Type type, Properties properties)
    {
        super(material, type, properties);
        this.geckoModelName = geckoModelName;
    }

    @Override
    public String getGeckoModelName()
    {
        return geckoModelName;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer)
    {
        consumer.accept(new IClientItemExtensions()
        {
            private GeoArmorRenderer<?> armorRenderer;
            private BlockEntityWithoutLevelRenderer itemRenderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                if (itemRenderer == null) {
                    itemRenderer = new GenericGeoArmorItemRenderer();
                }

                return itemRenderer;
            }

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(
                    LivingEntity livingEntity,
                    ItemStack itemStack,
                    EquipmentSlot equipmentSlot,
                    HumanoidModel<?> original
            )
            {
                if (armorRenderer == null) {
                    armorRenderer = new GenericGeoArmorRenderer<GeckoArmorItem>();
                }

                armorRenderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return armorRenderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}
