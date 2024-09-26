package net.neelesh.whackingstick;

import com.mojang.serialization.Codec;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.PlayerHurtEntityCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

import static net.neelesh.whackingstick.WhackingStick.whackingStickCriterion;

public class WhackingStickCriterion extends AbstractCriterion<PlayerHurtEntityCriterion.Conditions> {
    @Override
    public Codec<PlayerHurtEntityCriterion.Conditions> getConditionsCodec() {
        return PlayerHurtEntityCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, Entity entity, DamageSource damage, float dealt, float taken, boolean blocked) {
        LootContext lootContext = EntityPredicate.createAdvancementEntityLootContext(player, entity);
        this.trigger(player, conditions -> conditions.matches(player, lootContext, damage, dealt, taken, blocked));
    }

    public record Conditions(Optional<LootContextPredicate> player, Optional<DamagePredicate> damage, Optional<LootContextPredicate> entity)
            implements AbstractCriterion.Conditions {

        public static AdvancementCriterion<PlayerHurtEntityCriterion.Conditions> create() {
            return whackingStickCriterion.create(new PlayerHurtEntityCriterion.Conditions(Optional.empty(), Optional.empty(), Optional.empty()));
        }



        @Override
        public void validate(LootContextPredicateValidator validator) {
            AbstractCriterion.Conditions.super.validate(validator);
            validator.validateEntityPredicate(this.entity, ".entity");
        }
    }


}
