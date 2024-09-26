package net.neelesh.whackingstick.custom;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.neelesh.whackingstick.config.WhackingStickConfig;

import java.util.List;
import static net.neelesh.whackingstick.WhackingStick.whackingStickCriterion;

public class WhackingStickItem extends Item {
    public WhackingStickItem(Settings settings) {
        super(settings);
    };

    public static AttributeModifiersComponent createAttributeModifiers(float attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)(attackDamage), EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();

    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return switch(WhackingStickConfig.loadEnchantmentGlint()) {
            case 0 -> super.hasGlint(stack);
            case 1 -> true;
            case 2 -> false;
            default ->
                    throw new IllegalStateException("Unexpected value: " + WhackingStickConfig.loadEnchantmentGlint());
        };
    }
    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }



    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.getWorld().playSound(attacker, attacker.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 10f, 1f);
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        target.takeKnockback(5, -target.getX() + attacker.getX(), -target.getZ() + attacker.getZ());
        whackingStickCriterion.trigger((ServerPlayerEntity)attacker, target, null, 0, 0, false);
        if (!(target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE) == 1) && WhackingStickConfig.loadHitSound() != 4) {
            SoundEvent hitSound = switch (WhackingStickConfig.loadHitSound()) {
                case 0 -> SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH;
                case 1 -> SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP;
                case 2 -> SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
                case 3 -> SoundEvents.BLOCK_ANVIL_LAND;
                default -> throw new IllegalStateException("Unexpected value: " + WhackingStickConfig.loadHitSound());
            };
            attacker.getWorld().playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), hitSound, SoundCategory.AMBIENT, 3.0F, 1.0F);
        }
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.matchesKey(Enchantments.KNOCKBACK) || enchantment.matchesKey(Enchantments.UNBREAKING) || enchantment.matchesKey(Enchantments.MENDING);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }


    @Override
    public int getEnchantability() {
        return 2;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("+10 Knockback").formatted(Formatting.AQUA));
        super.appendTooltip(stack, context, tooltip, type);

    }
    
}
