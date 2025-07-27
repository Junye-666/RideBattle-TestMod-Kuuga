package com.jpigeon.testmod.preFabricatedCodes.datagen;

import com.jpigeon.testmod.TestMod;
import com.jpigeon.testmod.preFabricatedCodes.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/*
* 自动生成物品模型文件用的
* 可直接复制, 修改MODID
 */
public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // 删掉现有的, 然后给自己的所有物品仍这里就好
        basicItem(ModItems.CHOGODAI_ELEMENT.get());
        basicItem(ModItems.MIGHTY_ELEMENT.get());
        basicItem(ModItems.DRAGON_ELEMENT.get());
        basicItem(ModItems.PEGASUS_ELEMENT.get());
        basicItem(ModItems.TITAN_ELEMENT.get());
        basicItem(ModItems.KUUGA_HELMET.get());
        basicItem(ModItems.KUUGA_CHESTPLATE.get());
        basicItem(ModItems.KUUGA_BOOTS.get());
        basicItem(ModItems.ARCLE.get());
    }
}