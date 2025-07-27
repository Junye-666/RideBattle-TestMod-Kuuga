package com.jpigeon.testmod.preFabricatedCodes.geckoLibStuff.arcle;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/*
 * 供参考, 动画逻辑不能完全照抄
 * 看不懂就对了, 我也看不懂
 * 搞不明白就把整个复制扔到一个txt文件夹, 通过Ctrl+F搜索并替换"  "(←也就是再搜索框中搜两个空格, 然后替换成啥也没有即可), 消除idea自动生成的一堆吃内存的空格
 * 然后扔给DeepSeek, 描述你想要的结果即可
 * 不然装个通义灵码, 让它写也不是不行
 */
public class ArcleItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private AnimationController<ArcleItem> appearController;
    private AnimationController<ArcleItem> inBodyController;
    private AnimationController<ArcleItem> idleController;

    public enum AnimState {IDLE, INBODY, APPEAR}
    private AnimState animState = AnimState.INBODY; // 初始为INBODY
    private boolean isAppearAnimationPlaying = false;

    public ArcleItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        // 创建控制器并保存引用
        idleController = new AnimationController<>(this, "idle_controller", 0, this::idleController);
        inBodyController = new AnimationController<>(this, "inBody_controller", 0, this::inBodyController);
        appearController = new AnimationController<>(this, "appear_controller", 0, this::appearController);

        controllerRegistrar.add(idleController);
        controllerRegistrar.add(inBodyController);
        controllerRegistrar.add(appearController);
    }

    private PlayState appearController(AnimationState<ArcleItem> animationState) {
        AnimationController<?> controller = animationState.getController();

        if (animState == AnimState.APPEAR) {
            // 首次触发APPEAR状态时设置动画
            if (!isAppearAnimationPlaying) {
                controller.setAnimation(RawAnimation.begin().then("appear", Animation.LoopType.HOLD_ON_LAST_FRAME));
                isAppearAnimationPlaying = true;
            }

            // 检查当前动画帧数
            if (controller.getAnimationState() == AnimationController.State.STOPPED) {
                // 动画播放完成，切换到IDLE状态
                setAnimState(AnimState.IDLE);
                isAppearAnimationPlaying = false;
            }

            return PlayState.CONTINUE;
        }

        // 重置状态，以便下次触发
        isAppearAnimationPlaying = false;
        return PlayState.STOP;
    }

    private PlayState inBodyController(AnimationState<ArcleItem> animationState) {
        if (animState == AnimState.INBODY) {
            animationState.getController().setAnimation(
                    RawAnimation.begin().thenLoop("inBody")
            );
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private PlayState idleController(AnimationState<ArcleItem> animationState) {
        if (animState == AnimState.IDLE) {
            animationState.getController().setAnimation(
                    RawAnimation.begin().thenLoop("idle")
            );
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /**
     * 设置动画状态
     * @param state 要设置的动画状态
     */
    public void setAnimState(AnimState state) {
        if (animState != state) {
            animState = state;

            // 重置相关控制器
            switch (state) {
                case APPEAR -> appearController.forceAnimationReset();
                case INBODY -> inBodyController.forceAnimationReset();
                case IDLE -> idleController.forceAnimationReset();
            }
        }
    }

    /**
     * 触发出现动画（外部调用）
     */
    public void triggerAppear() {
        setAnimState(AnimState.APPEAR);
    }

    /**
     * 重置为身体内动画（外部调用）
     */
    public void resetToInBody() {
        setAnimState(AnimState.INBODY);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
                    @Nullable T livingEntity, ItemStack itemStack,
                    @Nullable EquipmentSlot equipmentSlot, @Nullable HumanoidModel<T> original) {
                if (this.renderer == null)
                    this.renderer = new ArcleRenderer();

                return this.renderer;
            }
        });
    }
}