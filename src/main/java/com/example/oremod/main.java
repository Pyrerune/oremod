package com.example.oremod;


import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.World;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
@Mod(modid = main.MODID, version = main.VERSION)
public class main
{
    public static final String MODID = "oremod";
    public static final String VERSION = "1.11";
    public static ToolMaterial amethystMaterial_pick = EnumHelper.addToolMaterial("materialAmethyst_pick", 10, 10000, 100f, 0, 5);
    public static ToolMaterial amethystMaterial_sword = EnumHelper.addToolMaterial("materialAmethyst_sword", 0, 10000, 100f,10000,10);
    public static ArmorMaterial amethystMaterial_armor = EnumHelper.addArmorMaterial("materialAmethyst_armor", 100, new int[] {7}, 10);
    ItemArmor amethystHelm = new Armor(amethystMaterial_armor, "amethystHelm", 1, 0);

    @EventHandler
      public void init(FMLInitializationEvent event)
      {
        //ITEMS
        Item amethyst = new ModItem("amethyst");
        GameRegistry.registerItem(amethyst, "amethyst");
        Item darkItem = new ModItem("darkItem");
        GameRegistry.registerItem(darkItem, "darkItem");
        //Item stick = Items.stick;
        ItemPickaxe amethystPick = new Pickaxe(amethystMaterial_pick, "amethystPick");
        GameRegistry.registerItem(amethystPick, "amethystPick");
        //BLOCKS
        ItemSword amethystSword = new Sword(amethystMaterial_sword, "amethystSword");
        GameRegistry.registerItem(amethystSword, "amethystSword");

        GameRegistry.registerItem(amethystHelm, "amethystHelm");

        Block amethystOre = new ModBlock(Material.rock, "amethystOre", amethyst);
        GameRegistry.registerBlock(amethystOre, "amethystOre");
        Block darkOre = new ModBlock(Material.rock, "darkOre", darkItem);
        GameRegistry.registerBlock(darkOre, "darkOre");
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(amethystOre, 3), 0);
        GameRegistry.registerWorldGenerator(new ModWorldGenerator(darkOre, 7), 0);
        //GameRegistry.addRecipe(new ItemStack(amethystOre), new Object[] {"DDD", "DDD", "DDD", "D", amethyst});
        GameRegistry.addRecipe(new ItemStack(amethystPick), "XXX", "XYX", "XYX", 'X', /*Items.iron_ingot*/amethyst, 'Y', Items.diamond_pickaxe);
        GameRegistry.addRecipe(new ItemStack(amethystSword), "XXX", "XYX", "XXX", 'X', /*Items.iron_ingot*/amethyst, 'Y', Items.diamond_sword);
      }
      public class ModBlock extends Block
      {
        private Item toDrop;
        public ModBlock(Material material, String blockName, Item toDrop)
       {
           super(material);
           this.setBlockName(blockName);
           this.setBlockTextureName(MODID + ":" + blockName);
           this.setCreativeTab(CreativeTabs.tabBlock);
           this.setHardness(4.0F);
           this.setHarvestLevel("pickaxe", 1);
           this.toDrop = toDrop;
       }
       @Override
        public Item getItemDropped(int i, Random random, int j)
        {
            if(toDrop != null)
            {
                return toDrop;
            }
            else return Item.getItemFromBlock(this);
        }
      }

      public class ModItem extends Item
      {
       public ModItem(String itemName)
       {
           this.setUnlocalizedName(itemName);
           this.setTextureName(MODID + ":" + itemName);
           this.setCreativeTab(CreativeTabs.tabMisc);

       }

      }
      public class ModWorldGenerator implements IWorldGenerator
      {
        private Block block;
        private int veinSize;

        ModWorldGenerator(Block block, int veinSize)
        {
          this.block = block;
          this.veinSize = veinSize;
        }
        @Override
        public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
        {
          if (world.provider.dimensionId == 0) {
              this.runGenerator(new WorldGenMinable(block, veinSize), world, random, chunkX, chunkZ, 20, 0, 64);
            }

          }
          private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight)
          {
            if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            {
                throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
              }

            int heightDiff = maxHeight - minHeight + 1;
            for (int i = 0; i < chancesToSpawn; i ++)
            {
                int x = chunk_X * 16 + rand.nextInt(16);
                int y = minHeight + rand.nextInt(heightDiff);
                int z = chunk_Z * 16 + rand.nextInt(16);
                generator.generate(world, rand, x, y, z);
              }
          }

        }

      public class Pickaxe extends ItemPickaxe {



        public Pickaxe(ToolMaterial material, String itemName) {

          super(material);
          this.setUnlocalizedName(itemName);
          this.setTextureName(MODID + ":" + itemName);
          this.setCreativeTab(CreativeTabs.tabTools);

        }
      }

      public class Sword extends ItemSword {
        public Sword(ToolMaterial material, String itemName) {
          super(material);
          this.setUnlocalizedName(itemName);
          this.setTextureName(MODID + ":" + itemName);
          //this.setCreativeTab(CreativeTabs.tabTools);
        }
      }

      public class Armor extends ItemArmor {
        public Armor(ArmorMaterial material, String itemName, int render_idx, int type) {
          super(material, render_idx, type);
          this.setUnlocalizedName(itemName);
          this.setTextureName(MODID + ":" + itemName);
        }
        public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
          if(stack.getItem() == amethystHelm){
            return "oremod:textures/items/amethystHelm3d.png";
          } else return null;

        }

      }


}
