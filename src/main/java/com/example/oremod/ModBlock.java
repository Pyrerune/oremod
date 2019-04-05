package com.example.oremod.ModBlock;

import com.example.oremod.main;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlock extends Block
      {
        private Item toDrop;
        public ModBlock(Material material, String blockName, Item toDrop)
       {
           super(material);
           this.setBlockName(blockName);
           this.setBlockTextureName(main.MODID + ":" + blockName);
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
