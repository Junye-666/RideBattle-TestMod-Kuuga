package com.jpigeon.testmod.preFabricatedCodes.item;

import com.jpigeon.testmod.TestMod;
import com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.arcle.ArcleItem;
import com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.armor.KuugaArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/*
* 最基本的物品注册类
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> CHOGODAI_ELEMENT = ITEMS.register("chogodai_element", ()
    -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MIGHTY_ELEMENT = ITEMS.register("mighty_element", ()
            -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DRAGON_ELEMENT = ITEMS.register("dragon_element", ()
            -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PEGASUS_ELEMENT = ITEMS.register("pegasus_element", ()
            -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TITAN_ELEMENT = ITEMS.register("titan_element", ()
            -> new Item(new Item.Properties()));

    // 盔甲注册
    public static final DeferredItem<KuugaArmorItem> KUUGA_HELMET = ITEMS.register("kuuga_helmet", ()
    -> new KuugaArmorItem(KuugaArmorMaterial.KUUGA_MATERIAL, KuugaArmorItem.Type.HELMET, new Item.Properties()));

    public static final DeferredItem<KuugaArmorItem> KUUGA_CHESTPLATE = ITEMS.register("kuuga_chestplate", ()
    -> new KuugaArmorItem(KuugaArmorMaterial.KUUGA_MATERIAL, KuugaArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final DeferredItem<KuugaArmorItem> KUUGA_BOOTS = ITEMS.register("kuuga_boots", ()
    -> new KuugaArmorItem(KuugaArmorMaterial.KUUGA_MATERIAL, KuugaArmorItem.Type.BOOTS, new Item.Properties()));

    // 需要注意: 腰带注册时不能沿用盔甲物品
    public static final DeferredItem<ArcleItem> ARCLE = ITEMS.register("arcle", ()
    -> new ArcleItem(KuugaArmorMaterial.KUUGA_MATERIAL, ArcleItem.Type.LEGGINGS, new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
