package geoblocks.main;

import geoblocks.domain.*;
import geoblocks.util.*;
import net.morbz.minecraft.blocks.Material;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.level.FlatGenerator;
import net.morbz.minecraft.level.GameType;
import net.morbz.minecraft.level.IGenerator;
import net.morbz.minecraft.level.Level;
import net.morbz.minecraft.world.DefaultLayers;
import net.morbz.minecraft.world.World;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class BuildMap {

	public static String imagePath = "img/";

	public static int yBase = 11;
	private static int buildingDefaultHeight = 10;
	private static int treeDeafultHeight = 5;

	public static void main(String[] args) throws IOException {
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//Set all the blocks with y = -1 to BEDROCK
		DefaultLayers layers = new DefaultLayers();
		layers.setLayer(0, Material.BEDROCK);
		layers.setLayers(1, 10, Material.GRASS);

		//Generate a flat world based on the layers
		IGenerator generator = new FlatGenerator(layers);

		Level level = new Level(args[3], generator);
		level.setGameType(GameType.CREATIVE);
		level.setSpawnPoint(0, 10, 0);

		World world = new World(level, layers);

		BufferedImage normalMap = ImageIO.read(new File(args[0]));
		BufferedImage heightMap = ImageIO.read(new File(args[1]));

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for (int i = 0; i < normalMap.getWidth(); i++) {
			
			for (int j = 0; j < normalMap.getHeight(); j++) {

				Color pixelColor = new Color(normalMap.getRGB(i, j));
				
				SimpleBlock blockToPlace = SimpleBlock.COBBLESTONE;
				
				//Get block from color
				for (int x = 0; x < Tile.TYPES.length; x++) {
					
					if (Tile.TYPES[x].COLOR.equals(pixelColor)) {
						
						blockToPlace = Tile.TYPES[x].BLOCK;
						break;
					}					
				}
				
				//Gets "pixel height" and fills with blockToPlace
				int tileHeight = 0;
				if (heightMap != null) {
					Color heightColor = new Color(heightMap.getRGB(i, j));
					tileHeight = heightColor.getRed();
					
					for (int y = 0; y < tileHeight; y++) {
						
						world.setBlock(i, y + yBase, j, blockToPlace);						
					}
				}
				
				world.setBlock(i, yBase + tileHeight, j, blockToPlace);
			}
		}
		
		Building.makeBuilding(world, normalMap, heightMap, Tile.BUILDING.COLOR, buildingDefaultHeight, SimpleBlock.WOOL);
		Building.makeTree(world, normalMap, heightMap, Tile.TREE.COLOR, treeDeafultHeight);
		
		world.save(args[2], true);
	}
}

