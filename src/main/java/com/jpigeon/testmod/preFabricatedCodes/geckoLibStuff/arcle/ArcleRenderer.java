package com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.arcle;

import com.jpigeon.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// 可以直接复制, 换名即可
public class ArcleRenderer extends GeoArmorRenderer<ArcleItem> {
    public ArcleRenderer() {
        super(new ArcleModel(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "geo/arcle")));
    }
}
