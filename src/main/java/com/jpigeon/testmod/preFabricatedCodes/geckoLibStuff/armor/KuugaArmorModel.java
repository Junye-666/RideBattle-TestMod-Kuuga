package com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.armor;

import com.jpigeon.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
 * 可直接复制, 将MODID以及文件名替换成你自己的
 * 注意GeoModel<这里面的> 与 getModelResource(这里面的)一致
 * 注意path后面替换成自己的实际文件名/位置
 */
public class KuugaArmorModel extends GeoModel<KuugaArmorItem> {

    public KuugaArmorModel(ResourceLocation resourceLocation) {
        super();
    }

    @Override
    public ResourceLocation getModelResource(KuugaArmorItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "geo/kuuga_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KuugaArmorItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "textures/armor/kuuga_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KuugaArmorItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "animations/kuuga_armor.animation.json");
    }
}
