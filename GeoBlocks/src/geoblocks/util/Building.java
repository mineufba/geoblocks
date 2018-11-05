package geoblocks.util;

import geoblocks.domain.*;
import geoblocks.main.*;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.morbz.minecraft.blocks.SimpleBlock;
import net.morbz.minecraft.world.World;

public class Building {
	
	public static void makeBuilding (World world, BufferedImage map, BufferedImage heightMap, Color col, int defaultHeight, SimpleBlock blockToPlace) {
		
		int widht = map.getWidth();
		int height = map.getHeight();
		
		for (int i = 0; i < widht; i++) {
			
			for (int j = 0; j < height; j++) {

				if (col.equals(new Color(map.getRGB(i, j)))) {
					
					map.setRGB(i, j, (new Color(1, 1, 1)).getRGB());
					
					ArrayList<Point> pointsToCheck = new ArrayList<Point>();
					ArrayList<Point> buildingPoints = new ArrayList<Point>();
					
					buildingPoints.add(new Point(i, j));
					
					pointsToCheck.add(new Point(i + 1, j));
					pointsToCheck.add(new Point(i, j + 1));
					pointsToCheck.add(new Point(i - 1, j));
					pointsToCheck.add(new Point(i, j - 1));
					
					while (pointsToCheck.size() > 0) {
						
						ArrayList<Point> newPointsToCheck = new ArrayList<Point>();
						
						for (Point point : pointsToCheck) {
							
							if (point.x >= widht || point.x < 0) {
								continue;
							}
							
							if (point.y >= height || point.y < 0) {
								continue;
							}
							
							if (col.equals(new Color(map.getRGB(point.x, point.y)))) {
								
								map.setRGB(point.x, point.y, (new Color(1, 1, 1)).getRGB());
								
								buildingPoints.add(new Point(point.x, point.y));
								
								newPointsToCheck.add(new Point(point.x + 1, point.y));
								newPointsToCheck.add(new Point(point.x, point.y + 1));
								newPointsToCheck.add(new Point(point.x - 1, point.y));
								newPointsToCheck.add(new Point(point.x, point.y - 1));
							}							
						}	
						
						pointsToCheck = new ArrayList<Point>();
						pointsToCheck.addAll(newPointsToCheck);
					}	
					
					int higher = 0;
					
					for (Point point : buildingPoints) {
						
						int pointHeight = (new Color(heightMap.getRGB(point.x, point.y))).getRed();
						
						if (pointHeight > higher) {
							higher = pointHeight;
						}
					}
					
					for (Point point : buildingPoints) {
						
						int pointHeight = (new Color(heightMap.getRGB(point.x, point.y))).getRed();
						
						for (int l = pointHeight + 1; l <= higher + defaultHeight; l++) {
							
							world.setBlock(point.x, BuildMap.yBase + l, point.y, blockToPlace);
						}
					}
				}
			}
		}
	}
	
public static void makeTree (World world, BufferedImage map, BufferedImage heightMap, Color col, int stumpHeight) {
		
		final int widht = map.getWidth();
		final int height = map.getHeight();
		
		final SimpleBlock stumpBlock = SimpleBlock.LOG;
		final SimpleBlock leavesBlock = SimpleBlock.LEAVES;
		
		for (int i = 0; i < widht; i++) {
			
			for (int j = 0; j < height; j++) {

				if (col.equals(new Color(map.getRGB(i, j)))) {
					
					map.setRGB(i, j, (new Color(1, 1, 1)).getRGB());
					
					ArrayList<Point> pointsToCheck = new ArrayList<Point>();
					ArrayList<Point> buildingPoints = new ArrayList<Point>();
					
					buildingPoints.add(new Point(i, j));
					
					pointsToCheck.add(new Point(i + 1, j));
					pointsToCheck.add(new Point(i, j + 1));
					pointsToCheck.add(new Point(i - 1, j));
					pointsToCheck.add(new Point(i, j - 1));
					
					while (pointsToCheck.size() > 0) {
						
						ArrayList<Point> newPointsToCheck = new ArrayList<Point>();
						
						for (Point point : pointsToCheck) {
							
							if (point.x >= widht || point.x < 0) {
								continue;
							}
							
							if (point.y >= height || point.y < 0) {
								continue;
							}
							
							if (col.equals(new Color(map.getRGB(point.x, point.y)))) {
								
								map.setRGB(point.x, point.y, (new Color(1, 1, 1)).getRGB());
								
								buildingPoints.add(new Point(point.x, point.y));
								
								newPointsToCheck.add(new Point(point.x + 1, point.y));
								newPointsToCheck.add(new Point(point.x, point.y + 1));
								newPointsToCheck.add(new Point(point.x - 1, point.y));
								newPointsToCheck.add(new Point(point.x, point.y - 1));
							}							
						}	
						
						pointsToCheck = new ArrayList<Point>();
						pointsToCheck.addAll(newPointsToCheck);
					}	
					
					Point minP = new Point(widht, height);
					Point maxP = new Point(0, 0);
					
					for (Point point : buildingPoints) {
						
						if (point.x < minP.x) {
							
							minP.x = point.x;
						} 
						else if (point.y < minP.y) {
							
							minP.y = point.y;
						}
						else if (point.x > maxP.x) {
							
							maxP.x = point.x;
						}
						else if (point.y > maxP.y) {
							
							maxP.y = point.y;
						}						
					}
					
					Point treeSpot = new Point((minP.x + maxP.x) / 2, (minP.y + maxP.y) / 2);
					
					final int spotHeight = (new Color(heightMap.getRGB(treeSpot.x, treeSpot.y))).getRed();
					
					
					//Make Leaves
					for (int x = treeSpot.x - 1; x <= treeSpot.x + 1; x++) {
						
						for (int y = spotHeight + stumpHeight - 1; y <= spotHeight + stumpHeight + 1; y++) {
							
							for (int z = treeSpot.y - 1; z <= treeSpot.y + 1; z++) {
								
								world.setBlock(x, BuildMap.yBase + y, z, leavesBlock);
							}
						}
					}
					
					//Make Stump
					for (int k = spotHeight + 1; k <= spotHeight + stumpHeight; k++) {
						
						world.setBlock(treeSpot.x, BuildMap.yBase + k, treeSpot.y, stumpBlock);
					}
				}
			}
		}
	}
}