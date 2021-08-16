package safro.oysters.reborn.oysters;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import safro.oysters.reborn.OystersReborn;
import safro.oysters.reborn.util.OysterConfigValues;

import java.util.Iterator;


public class OysterEntity extends BlockEntity implements SidedInventory {

    public DefaultedList<ItemStack> inventory;
    private OysterBreed oysterBreed;
    private long ticksElapased = 0;
    private long tickCheck;

    public OysterEntity(BlockPos pos, BlockState state) {
        super(OystersManager.OYSTER_BLOCK_ENTITY, pos, state);
        inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
        // yeah you should ignore this completely random and non-efficient code :)
        if (state.isOf(OysterBreed.COAL.getOysterBlock())) {
            this.oysterBreed = OysterBreed.COAL;
        }
        if (state.isOf(OysterBreed.FLAWLESS.getOysterBlock())) {
            this.oysterBreed = OysterBreed.FLAWLESS;
        }
        if (state.isOf(OysterBreed.BLEMISHED.getOysterBlock())) {
            this.oysterBreed = OysterBreed.BLEMISHED;
        }
        if (state.isOf(OysterBreed.CLEAN.getOysterBlock())) {
            this.oysterBreed = OysterBreed.CLEAN;
        }
        if (state.isOf(OysterBreed.BONE_MEAL.getOysterBlock())) {
            this.oysterBreed = OysterBreed.BONE_MEAL;
        }
        if (state.isOf(OysterBreed.STRING.getOysterBlock())) {
            this.oysterBreed = OysterBreed.STRING;
        }
        if (state.isOf(OysterBreed.SAND.getOysterBlock())) {
            this.oysterBreed = OysterBreed.SAND;
        }
        if (state.isOf(OysterBreed.GOLD.getOysterBlock())) {
            this.oysterBreed = OysterBreed.GOLD;
        }
        if (state.isOf(OysterBreed.WOOD.getOysterBlock())) {
            this.oysterBreed = OysterBreed.WOOD;
        }
        if (state.isOf(OysterBreed.STONE.getOysterBlock())) {
            this.oysterBreed = OysterBreed.STONE;
        }
        if (state.isOf(OysterBreed.LAPIS.getOysterBlock())) {
            this.oysterBreed = OysterBreed.LAPIS;
        }
        if (state.isOf(OysterBreed.REDSTONE.getOysterBlock())) {
            this.oysterBreed = OysterBreed.REDSTONE;
        }
        if (state.isOf(OysterBreed.DIAMOND.getOysterBlock())) {
            this.oysterBreed = OysterBreed.DIAMOND;
        }
        if (state.isOf(OysterBreed.IRON.getOysterBlock())) {
            this.oysterBreed = OysterBreed.IRON;
        }
        if (state.isOf(OysterBreed.DIRT.getOysterBlock())) {
            this.oysterBreed = OysterBreed.DIRT;
        }
        if (state.isOf(OysterBreed.GLOWSTONE.getOysterBlock())) {
            this.oysterBreed = OysterBreed.GLOWSTONE;
        }
        if (state.isOf(OysterBreed.QUARTZ.getOysterBlock())) {
            this.oysterBreed = OysterBreed.QUARTZ;
        }
        if (state.isOf(OysterBreed.ENDER_PEARL.getOysterBlock())) {
            this.oysterBreed = OysterBreed.ENDER_PEARL;
        }
        if (state.isOf(OysterBreed.EMERALD.getOysterBlock())) {
            this.oysterBreed = OysterBreed.EMERALD;
        }
        tickCheck = OystersReborn.oystersConfig.getProperty(OysterConfigValues.BASE_OYSTER_TIME);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OysterEntity be) {
        if (be.tickCheck <= be.ticksElapased) {
            be.spawnPearl();
            be.ticksElapased = 0;
        } else {
            be.ticksElapased++;
        }
    }

    private void spawnPearl() {
        if (!world.isClient && world.getBlockState(this.pos).get(Properties.WATERLOGGED)) {
            ItemStack itemStack = new ItemStack(oysterBreed.getOysterPearl());
            if (inventory.get(0).isEmpty()) {
                inventory.set(0, itemStack);
                markDirty();
            } else if (inventory.get(0).isItemEqual(itemStack) &&
                    (inventory.get(0).getCount() + itemStack.getCount() <= itemStack.getMaxCount()) &&
                    itemStack.isStackable()) {
                inventory.set(0, new ItemStack(itemStack.getItem(), itemStack.getCount() + inventory.get(0).getCount()));
                markDirty();
            }
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
        return nbt;
    }

    @Override
    public int[] getAvailableSlots(Direction direction) {
        return new int[1];
    }

    @Override
    public boolean canInsert(int i, ItemStack itemStack, Direction direction) {
        return false;
    }

    @Override
    public boolean canExtract(int i, ItemStack itemStack, Direction direction) {
        if (direction == Direction.DOWN && i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        Iterator var1 = this.inventory.iterator();
        ItemStack itemStack_1;
        do {
            if (!var1.hasNext()) {
                return true;
            }
            itemStack_1 = (ItemStack) var1.next();
        } while (itemStack_1.isEmpty());
        return false;
    }

    @Override
    public ItemStack getStack(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack removeStack(int i, int i1) {
        return Inventories.splitStack(this.inventory, i, i1);
    }

    @Override
    public ItemStack removeStack(int i) {
        return Inventories.removeStack(inventory, i);
    }

    @Override
    public void setStack(int i, ItemStack itemStack) {
        inventory.set(i, itemStack);
        this.markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity playerEntity) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        } else {
            return playerEntity.squaredDistanceTo((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clear() {
        inventory.clear();
    }
}
