import java.awt.Color;
import net.morbz.minecraft.blocks.SimpleBlock;

public class Tile {

	public String NAME;
	public Color COLOR;
	public SimpleBlock BLOCK;	

	public Tile (String n, Color c, SimpleBlock b) {
		NAME = n;
		COLOR = c;
		BLOCK = b;
	}
}