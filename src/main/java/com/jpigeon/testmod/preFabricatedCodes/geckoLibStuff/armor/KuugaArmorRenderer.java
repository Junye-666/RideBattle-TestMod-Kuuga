package com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.armor;

import com.jpigeon.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// 可以直接复制, 换名即可
public class KuugaArmorRenderer extends GeoArmorRenderer<KuugaArmorItem> {
    public KuugaArmorRenderer() {
        super(new KuugaArmorModel(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "geo/kuuga_armor")));

    }
}
