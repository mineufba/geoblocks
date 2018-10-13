import net.morbz.minecraft.blocks.DoorBlock;
import net.morbz.minecraft.blocks.Material;
import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.blocks.DoorBlock.DoorMaterial;
import net.morbz.minecraft.blocks.DoorBlock.HingeSide;
import net.morbz.minecraft.blocks.states.Facing4State;
import net.morbz.minecraft.level.FlatGenerator;
import net.morbz.minecraft.level.GameType;
import net.morbz.minecraft.level.IGenerator;
import net.morbz.minecraft.level.Level;
import net.morbz.minecraft.world.DefaultLayers;
import net.morbz.minecraft.world.World;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;


public class BuildMap {

	public static String imagePath = "img/";

	public static int yBase = 11;
	private static int buildingDefaultHeight = 10;

	//Colors 
	private static Tile[] TILE_TYPES = {

		// Light Gray  238, 238, 238

		//Water Tile 
		new Tile("WATER",           new Color(170, 211, 223), SimpleBlock.WATER),
		//Orange Avenue Tile
		new Tile("ORANGE_AVENUE",   new Color(252, 214, 164), SimpleBlock.SPONGE),
		//Green Avenue Tile 
		new Tile("GREEN_AVENUE",    new Color(247, 250, 191), SimpleBlock.MELON_BLOCK),


		//Light Gray Areas Tile 
		new Tile("LIGHT_GRAY_AREA", new Color(242, 239, 233), SimpleBlock.CLAY),
		//Medium Gray Areas Tile 
		new Tile("MEDIUM_GRAY_AREA", new Color(238, 238, 238), SimpleBlock.IRON_BLOCK),
		//Dark Gray Areas Tile (Buildings)
		new Tile("DARK_GRAY_AREA", new Color(217, 208, 201), SimpleBlock.BRICK_BLOCK),


		//Light Gray Areas Tile 
		new Tile("LIGHT_GREEN_AREA", new Color(200, 250, 204), SimpleBlock.GRASS),
		//Dark Gray Areas Tile 
		new Tile("DARK_GREEN_AREA",  new Color(173, 209, 158), SimpleBlock.GRASS),


		//Light Gray Areas Tile 
		new Tile("LIGHT_BROWN_AREA", new Color(251, 236, 215), SimpleBlock.SAND),

		//Black Areas Tile 
		new Tile("BLACK_AREA",       new Color(0, 0, 0),       SimpleBlock.OBSIDIAN),


		//White Areas Tile 
		new Tile("WHITE_AREA",      new Color(255, 255, 255), SimpleBlock.WOOL)
	};

	public static void main(String[] args) throws IOException {


		//Set all the blocks with y = -1 to BEDROCK
		DefaultLayers layers = new DefaultLayers();
		layers.setLayer(0, Material.BEDROCK);
		layers.setLayers(1, 10, Material.GRASS);

		//Generate a flat world based on the layers
		IGenerator generator = new FlatGenerator(layers);

		Level level = new Level("Salvador_J2", generator);
		level.setGameType(GameType.CREATIVE);
		level.setSpawnPoint(0, 10, 0);

		World world = new World(level, layers);

		BufferedImage normalMap = ImageIO.read(new File("/home/pedro/MineUFBA/MapJava/GeoBlocks/src/img/amapa.png"));
		BufferedImage heightMap = null; //ImageIO.read(new File("/home/pedro/MineUFBA/MapJava/GeoBlocks/src/img/aterreno.png"));

		System.out.println("\nWidth: " + normalMap.getWidth() + "\nHeight: " + normalMap.getHeight() + "\n");

		for (int i = 0; i < normalMap.getWidth(); i++) {
			
			for (int j = 0; j < normalMap.getHeight(); j++) {

				Color pixelColor = new Color(normalMap.getRGB(i, j));
					
				int tileHeight = 0;
				if (heightMap != null) {
					Color heightColor = new Color(heightMap.getRGB(i, j));
					tileHeight = (int)(100 * heightColor.getRed()) / 255;
					
					for (int y = 0; y < tileHeight; y++) {
						
						world.setBlock(i, y + yBase, j, SimpleBlock.GLASS);						
					}
				}
				
				boolean foundTile = false;

				for (int x = 0; x < TILE_TYPES.length; x++) {

						
					if (TILE_TYPES[x].COLOR.equals(pixelColor)) {

						// Just sets one block for now
						world.setBlock(i, yBase + tileHeight, j, TILE_TYPES[x].BLOCK);

						//System.out.println("Found " + TILE_TYPES[x].NAME);

						if (TILE_TYPES[x].NAME == "DARK_GRAY_AREA") {

							for (int p = tileHeight + yBase + 1; p < 60; p++) {
									
								world.setBlock(i, p, j, TILE_TYPES[x].BLOCK);									
							}	

						}

						foundTile = true;
						break;
					}					
				}
				
				//Default tile value
				if (!foundTile) {

					//System.out.println(pixelColor.getRed() + " " + pixelColor.getGreen() + " " + pixelColor.getBlue());
					world.setBlock(i, yBase + tileHeight, j, SimpleBlock.COBBLESTONE);
				}
				

			}
		}

		world.save("/home/pedro/.minecraft/saves/", true);
	}
}

