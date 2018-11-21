package geoblocks.domain;

import java.awt.Color;
import net.morbz.minecraft.blocks.SimpleBlock;

public class Tile {
	
	public Color COLOR;
	public SimpleBlock BLOCK;	

	public Tile (Color c, SimpleBlock b) {
		
		COLOR = c;
		BLOCK = b;
	}
	
	public final static Tile WATER            = new Tile(new Color(181, 208, 208), SimpleBlock.WATER);
	public final static Tile GRASS_0          = new Tile(new Color(240, 240, 216), SimpleBlock.GRASS);
	public final static Tile TREE             = new Tile(new Color(0  , 255, 72 ), SimpleBlock.DIRT);
	public final static Tile ORANGE_AVENUE    = new Tile(new Color(253, 214, 164), SimpleBlock.SPONGE);
	public final static Tile GREEN_AVENUE     = new Tile(new Color(254, 254, 178), SimpleBlock.MELON_BLOCK);
	public final static Tile MEDIUM_GRAY_AREA = new Tile(new Color(220, 220, 220), SimpleBlock.IRON_BLOCK);
	public final static Tile BUILDING         = new Tile(new Color(188, 169, 169), SimpleBlock.DIRT);
	public final static Tile LIGHT_GREEN_AREA = new Tile(new Color(141, 197, 108), SimpleBlock.GRASS);
	public final static Tile DARK_GREEN_AREA  = new Tile(new Color(169, 202, 174), SimpleBlock.GRASS);
	public final static Tile DARK_PINK_AREA   = new Tile(new Color(236, 162, 163), SimpleBlock.REDSTONE_BLOCK);
	public final static Tile FOOT_PATH        = new Tile(new Color(246, 132, 116), SimpleBlock.BRICK_BLOCK);
	public final static Tile BLACK_AREA       = new Tile(new Color(0  , 0  , 0  ), SimpleBlock.OBSIDIAN);
	public final static Tile WHITE_AREA       = new Tile(new Color(255, 255, 255), SimpleBlock.STONE);
	public final static Tile STREET           = new Tile(new Color( 61,  61,  61), SimpleBlock.OBSIDIAN);
	
	
	public final static Tile[] TYPES = {
			
			WATER,
			GRASS_0,
			TREE,
			ORANGE_AVENUE,
			GREEN_AVENUE,
			MEDIUM_GRAY_AREA,
			BUILDING,
			LIGHT_GREEN_AREA,
			DARK_GREEN_AREA,
			DARK_PINK_AREA,
			FOOT_PATH,
			BLACK_AREA,
			WHITE_AREA,
			STREET
	};
}