package net.mca.mixin;

import net.mca.Config;
import net.mca.entity.EntitiesMCA;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class MixinEntityType {
	@SuppressWarnings("ConstantConditions")
	@Inject(method = "isIn", at = @At("HEAD"), cancellable = true)
	private void mca$villagerTagHack(TagKey<EntityType<?>> tag, CallbackInfoReturnable<Boolean> cir) {
		if (Config.getInstance().villagerTagsHacks) {
			if (EntitiesMCA.MALE_VILLAGER.isPresent() && EntitiesMCA.FEMALE_VILLAGER.isPresent()) {
				if ((Object) this == EntitiesMCA.MALE_VILLAGER.get() || (Object) this == EntitiesMCA.FEMALE_VILLAGER.get()) {
					if (EntityType.VILLAGER.isIn(tag)) {
						cir.setReturnValue(true);
					}
				}
			}
		}
	}
}
