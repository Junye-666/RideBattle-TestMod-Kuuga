package com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.arcle;

import com.jpigeon.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

/*
* 可直接复制, 将MODID以及文件名替换成你自己的
* 注意GeoModel<这里面的> 与 getModelResource(这里面的)一致
* 注意path后面替换成自己的实际文件名/位置
 */
public class ArcleModel extends GeoModel<ArcleItem> {

    public ArcleModel(ResourceLocation resourceLocation) {
        super();
    }

    @Override
    public ResourceLocation getModelResource(ArcleItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "geo/arcle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArcleItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "textures/armor/arcle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArcleItem arcleItem) {
        return ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "animations/arcle.animation.json");
    }
}
