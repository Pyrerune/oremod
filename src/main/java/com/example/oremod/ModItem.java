package com.example.oremod.ModItem;

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


public class ModItem extends Item
      {
       public ModItem(String itemName)
       {
           this.setUnlocalizedName(itemName);
           this.setTextureName(main.MODID + ":" + itemName);
           this.setCreativeTab(CreativeTabs.tabMisc);
       }

   }
