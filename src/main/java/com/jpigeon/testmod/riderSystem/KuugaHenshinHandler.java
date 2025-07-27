package com.jpigeon.testmod.riderSystem;

import com.jpigeon.ridebattlelib.core.system.event.HenshinEvent;
import com.jpigeon.ridebattlelib.core.system.event.SlotExtractionEvent;
import com.jpigeon.ridebattlelib.core.system.event.UnhenshinEvent;
import com.jpigeon.ridebattlelib.core.system.henshin.helper.DriverActionManager;
import com.jpigeon.testmod.TestMod;
import com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.arcle.ArcleItem;
import com.jpigeon.testmod.preFabricatedCodes.item.ModItems;
import com.jpigeon.testmod.preFabricatedCodes.playerAnimator.PlayerAnimationTrigger;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

public class KuugaHenshinHandler {
    @SubscribeEvent
    public void onHenshin(HenshinEvent.Pre event) {
        if (!event.getRiderId().equals(KuugaConfig.KAMEN_RIDER_KUUGA)) {
            TestMod.LOGGER.info("不是Kuuga的变身");
            return;
        }
        TestMod.LOGGER.info("Kuuga变身");

        PlayerAnimationTrigger.playAnimation((AbstractClientPlayer) event.getPlayer(), "kuuga_henshin", 0);
        ItemStack legs = event.getPlayer().getItemBySlot(EquipmentSlot.LEGS);
        if (legs.getItem() instanceof ArcleItem arcle){
            arcle.triggerAppear();
        }
        shouldCount = true;
        setPendingPlayer(event.getPlayer());
    }

    private static int timer = 0;
    private static boolean shouldCount = false;
    private static Player pendingPlayer;


    @SubscribeEvent // 简单的计时
    public void onServerTick(ServerTickEvent.Post event) {
        if (shouldCount) {
            timer++;
            if (timer % 20 != 0) return;

            if (timer >= 40) {
                timer = 0;
                shouldCount = false;

                // 确保玩家仍然在线
                if (pendingPlayer != null && !pendingPlayer.isRemoved()) {
                    DriverActionManager.INSTANCE.completeTransformation(pendingPlayer);
                    TestMod.LOGGER.info("变身序列完成");
                }
            }
        }
    }

    public void setPendingPlayer(Player pendingPlayer) {
        KuugaHenshinHandler.pendingPlayer = pendingPlayer;
    }

    @SubscribeEvent
    public void onItemExtraction(SlotExtractionEvent.Pre event) {
        if (event.getExtractedStack() == ModItems.MIGHTY_ELEMENT.toStack()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUnhenshin(UnhenshinEvent.Post event){
        ItemStack legs = event.getPlayer().getItemBySlot(EquipmentSlot.LEGS);
        if (legs.getItem() instanceof ArcleItem arcle) {
            arcle.resetToInBody();
        }
    }
}
