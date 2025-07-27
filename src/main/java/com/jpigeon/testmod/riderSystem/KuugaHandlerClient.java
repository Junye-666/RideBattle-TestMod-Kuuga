package com.jpigeon.testmod.riderSystem;

import com.jpigeon.ridebattlelib.api.RiderManager;
import com.jpigeon.testmod.TestMod;
import com.jpigeon.testmod.preFabricatedCodes.item.ModItems;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
public class KuugaHandlerClient {
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        if (event.getKey() == GLFW.GLFW_KEY_1) {
            // 确保只在客户端处理
            if (Minecraft.getInstance().player != null) {
                TestMod.LOGGER.info("检测到按下1, 尝试塞入mighty_element");
                // 发送数据包给服务端处理实际逻辑
                RiderManager.insertBeltItem(Minecraft.getInstance().player, KuugaConfig.KUUGA_SLOT, ModItems.MIGHTY_ELEMENT.toStack());
            }
        }
    }
}
