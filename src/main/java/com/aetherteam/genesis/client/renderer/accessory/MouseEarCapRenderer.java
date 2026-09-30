package com.aetherteam.genesis.client.renderer.accessory;

import com.aetherteam.genesis.client.renderer.GenesisModelLayers;
import com.aetherteam.genesis.client.renderer.accessory.model.MouseEarCapModel;
import com.aetherteam.genesis.item.accessories.miscellaneous.MouseEarCapItem;
import com.aetherteam.genesis.mixin.mixins.client.accessor.ModelPartAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.ArrayList;
import java.util.List;

public class MouseEarCapRenderer implements ICurioRenderer {
    private final MouseEarCapModel mouseEarCap;

    public MouseEarCapRenderer() {
        this.mouseEarCap = new MouseEarCapModel(Minecraft.getInstance().getEntityModels().bakeLayer(GenesisModelLayers.MOUSE_EAR_CAP));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrices, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        MouseEarCapItem mouseEarCapItem = (MouseEarCapItem) stack.getItem();
        int color = DyedItemColor.getOrDefault(stack, 10302259);
        if (renderLayerParent.getModel() instanceof HumanoidModel<?> humanoidModel) {
            transformToModelPart(matrices, humanoidModel.head);
            matrices.mulPose(Axis.XP.rotationDegrees(180));
            matrices.translate(0, 0.5, 0);
            matrices.scale(2, 2, 2);
        }
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(mouseEarCapItem.getEarsTexture()));
        this.mouseEarCap.renderToBuffer(matrices, consumer, light, LivingEntityRenderer.getOverlayCoords(slotContext.entity(), 0.0F), color);
    }

    /**
     * Transforms the rendering context to the center of a {@link ModelPart}.
     *
     * @param poseStack The pose stack to apply the transformation(s) to.
     * @param part      The {@link ModelPart} to transform to.
     * <p>
     * [CODE COPY] - {@code io.wispforest.accessories.api.client.rendering.ModelTransformUtils#transformToModelPart}, with all percentages set to 0.
     */
    private static void transformToModelPart(PoseStack poseStack, ModelPart part) {
        part.translateAndRotate(poseStack);
        Vec3[] aabb = getAABB(part);
        poseStack.scale(1 / 16f, 1 / 16f, 1 / 16f);
        poseStack.translate(
                Mth.lerp(0.5, aabb[0].x, aabb[1].x),
                Mth.lerp(0.5, aabb[0].y, aabb[1].y),
                Mth.lerp(0.5, aabb[0].z, aabb[1].z)
        );
        poseStack.scale(8, 8, 8);
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
    }

    /**
     * Calculates the bounding box of a {@link ModelPart} from its cubes.
     *
     * @param part The {@link ModelPart} to calculate the bounding box of.
     * @return A two element array of the minimum and maximum {@link Vec3} corners of the bounding box.
     * <p>
     * [CODE COPY] - {@code io.wispforest.accessories.api.client.rendering.ModelTransformUtils#getAABB}
     */
    private static Vec3[] getAABB(ModelPart part) {
        Vec3 min = new Vec3(0, 0, 0);
        Vec3 max = new Vec3(0, 0, 0);

        if (part.getClass().getSimpleName().contains("EMFModelPart")) {
            List<ModelPart> parts = new ArrayList<>();

            parts.add(part);
            parts.addAll(((ModelPartAccessor) (Object) part).getChildren().values());

            for (ModelPart modelPart : parts) {
                for (ModelPart.Cube cube : ((ModelPartAccessor) (Object) modelPart).getCubes()) {
                    min = new Vec3(
                            Math.min(min.x, Math.min(cube.minX + modelPart.x, cube.maxX + modelPart.x)),
                            Math.min(min.y, Math.min(cube.minY + modelPart.y, cube.maxY + modelPart.y)),
                            Math.min(min.z, Math.min(cube.minZ + modelPart.z, cube.maxZ + modelPart.z))
                    );
                    max = new Vec3(
                            Math.max(max.x, Math.max(cube.minX + modelPart.x, cube.maxX + modelPart.x)),
                            Math.max(max.y, Math.max(cube.minY + modelPart.y, cube.maxY + modelPart.y)),
                            Math.max(max.z, Math.max(cube.minZ + modelPart.z, cube.maxZ + modelPart.z))
                    );
                }
            }
        } else {
            for (ModelPart.Cube cube : ((ModelPartAccessor) (Object) part).getCubes()) {
                min = new Vec3(
                        Math.min(min.x, Math.min(cube.minX, cube.maxX)),
                        Math.min(min.y, Math.min(cube.minY, cube.maxY)),
                        Math.min(min.z, Math.min(cube.minZ, cube.maxZ))
                );
                max = new Vec3(
                        Math.max(max.x, Math.max(cube.minX, cube.maxX)),
                        Math.max(max.y, Math.max(cube.minY, cube.maxY)),
                        Math.max(max.z, Math.max(cube.minZ, cube.maxZ))
                );
            }
        }

        return new Vec3[]{min, max};
    }
}
