package wilby.argh.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import wilby.argh.Argh;
import wilby.argh.api.block.ArghContainerBlock;
import wilby.argh.common.gui.ArghGuiHandler;
import wilby.argh.common.tileentity.TileEntityBox;

public class BlockBox extends ArghContainerBlock 
{

	public BlockBox(Material materialIn, String name) 
	{
		super(materialIn, name);
	}

	@Override
	public TileEntity createTileEntity(World worldIn, IBlockState state) 
	{
		return new TileEntityBox();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		IInventory inventory = worldIn.getTileEntity(pos) instanceof IInventory ? (IInventory)worldIn.getTileEntity(pos) : null;

		if (inventory != null){
			// For each slot in the inventory
			for (int i = 0; i < inventory.getSizeInventory(); i++){
				// If the slot is not empty
				if (!inventory.getStackInSlot(i).isEmpty())  // isEmpty
				{
					// Create a new entity item with the item stack in the slot
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));

					// Apply some random motion to the item
					float multiplier = 0.1f;
					float motionX = worldIn.rand.nextFloat() - 0.5f;
					float motionY = worldIn.rand.nextFloat() - 0.5f;
					float motionZ = worldIn.rand.nextFloat() - 0.5f;

					item.motionX = motionX * multiplier;
					item.motionY = motionY * multiplier;
					item.motionZ = motionZ * multiplier;

					// Spawn the item in the world
					worldIn.spawnEntity(item);
				}
			}

			// Clear the inventory so nothing else (such as another mod) can do anything with the items
			inventory.clear();
		}

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
																	EnumFacing side, float hitX, float hitY, float hitZ) {
		// Uses the gui handler registered to your mod to open the gui for the given gui id
		// open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
		if (worldIn.isRemote) return true;

		playerIn.openGui(Argh.argh, ArghGuiHandler.getGuiId(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.SOLID;
	
	
	}
	}
