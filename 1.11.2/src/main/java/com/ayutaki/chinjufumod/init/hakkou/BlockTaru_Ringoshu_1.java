package com.ayutaki.chinjufumod.init.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.init.ASDecoModHakkou;
import com.ayutaki.chinjufumod.init.TTimeItems;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTaru_Ringoshu_1 extends BlockRotatedPillar {

	/*新酒*/
	public BlockTaru_Ringoshu_1() {
		super(Material.WOOD);

		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));

	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		if (true) {

			world.setBlockState(pos,ASDecoModHakkou.RINGOSHUTARU_2.getDefaultState().withProperty(AXIS, state.getValue(AXIS)));
		}

		world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));
	}

	@Override
	public int tickRate(World world) {
		return 12000;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side,
			float hitX, float hitY, float hitZ) {

        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();

		if (item != TTimeItems.Item_SAKEBOTTLE) {

			return worldIn.setBlockState(pos,ASDecoModHakkou.RINGOSHUTARU_1
					.getDefaultState().withProperty(AXIS, state.getValue(AXIS)));

    	}

    	else if (item == TTimeItems.Item_SAKEBOTTLE) {

            itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				playerIn.setHeldItem(hand, new ItemStack(Item.getItemFromBlock(ASDecoModHakkou.CIDERBOT_1)));
			}
			else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(ASDecoModHakkou.CIDERBOT_1)))) {
				playerIn.dropItem(new ItemStack(Item.getItemFromBlock(ASDecoModHakkou.CIDERBOT_1)), false);
			}

	    	return worldIn.setBlockState(pos, ASDecoModHakkou.HAKKOUTARU.getDefaultState());
        }
    	return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/*ドロップ指定、ピックアップ指定*/
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {

	    return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

	        stack.add(new ItemStack(Items.APPLE, 7, this.damageDropped(state)));
	        stack.add(new ItemStack(ASDecoModHakkou.HAKKOUTARU, 1, this.damageDropped(state)));

	        return stack;
	}
}
