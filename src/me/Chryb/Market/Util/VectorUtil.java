package me.Chryb.Market.Util;

import org.bukkit.util.Vector;

public class VectorUtil {
	
	public static Vector parseToVector(String vectorString) {
        String[] stringCoords = vectorString.split("/");
        double[] coords = new double[3];
        for (int i = 0; i < 3; i++) {
            try {
                coords[i] = Double.parseDouble(stringCoords[i]);
            } catch (NumberFormatException e) {
                coords[i] = 0;
                return null;
            }
        }
        return new Vector(coords[0], coords[1], coords[2]);
    }
	
	public static String parseToString(Vector vector) {
        StringBuilder sb = new StringBuilder();
        sb.append(vector.getX() + "/");
        sb.append(vector.getY() + "/");
        sb.append(vector.getZ());
        return sb.toString();
    }

}
