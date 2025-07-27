package com.jpigeon.testmod.riderSystem;

import com.jpigeon.ridebattlelib.core.system.form.FormConfig;
import com.jpigeon.ridebattlelib.core.system.henshin.RiderConfig;
import com.jpigeon.ridebattlelib.core.system.henshin.RiderRegistry;
import com.jpigeon.ridebattlelib.core.system.henshin.helper.TriggerType;
import com.jpigeon.testmod.TestMod;
import com.jpigeon.testmod.preFabricatedCodes.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.List;

public class KuugaConfig {
    public static ResourceLocation KAMEN_RIDER_KUUGA = ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "kamen_rider_kuuga");

    public static ResourceLocation KUUGA_MIGHTY_FORM = ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "kuuga_mighty_form");

    public static ResourceLocation KUUGA_SLOT = ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "kuuga_slot");
    private static void registerKuuga(){
        RiderConfig KuugaRider = new RiderConfig(KAMEN_RIDER_KUUGA)
                .setDriverItem(ModItems.ARCLE.get(), EquipmentSlot.LEGS)
                .addDriverSlot(KUUGA_SLOT,
                        List.of(
                                ModItems.MIGHTY_ELEMENT.get()
                        ),
                        true,
                        true
                )
                ;


        FormConfig KuugaMightyForm = new FormConfig(KUUGA_MIGHTY_FORM)
                .setArmor(ModItems.KUUGA_HELMET.get(),
                        ModItems.KUUGA_CHESTPLATE.get(),
                        null,
                        ModItems.KUUGA_BOOTS.get())
                .addEffect(MobEffects.INVISIBILITY,
                        114514,
                        1,
                        true)
                .addAttribute(
                        ResourceLocation.fromNamespaceAndPath("minecraft", "generic.movement.speed"),
                        2.0,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
                .addRequiredItem(KUUGA_SLOT, ModItems.MIGHTY_ELEMENT.get())
                .setTriggerType(TriggerType.KEY)
                ;

        KuugaRider.addForm(KuugaMightyForm);
        KuugaRider.setBaseForm(KuugaMightyForm.getFormId());
        KuugaMightyForm.setShouldPause(true);

        RiderRegistry.registerRider(KuugaRider);
    }

    public static void init(){
        registerKuuga();
    }
}
