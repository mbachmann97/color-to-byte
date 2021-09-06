import java.awt.Color;

public class ColorToByte {
	public static void main(String[] args) {
		Color c = new Color(80, 150, 255);
		int rgb = c.getRGB();
		
		byte packed = packRgb(rgb);
		Color unpackedColor = unpackRgb(packed);
		
		System.out.println("ORIGINAL -> " + c.toString());
		System.out.println("BYTE -> " + unpackedColor.toString());
	}
	
	public static byte packRgb(int rawRgb) {
		int r = (rawRgb >> 0x10) & 0xFF;
		int g = (rawRgb >> 0x8) & 0xFF;
		int b = rawRgb & 0xFF;
		
		byte packedRgb = 0x0;
		
		int rx = (int) Math.floor(r / 0x20);
		int gx = (int) Math.floor(g / 0x20);
		int bx = (int) Math.floor(b / 0x4F);
		
		packedRgb |= rx << 0x5;
		packedRgb |= gx << 0x2;
		packedRgb |= bx;
		
		// System.out.println(bx);
		// System.out.println(Integer.toBinaryString(rx));
		
		return packedRgb;
	}
	
	public static Color unpackRgb(byte packedRgb) {
		int r = (packedRgb >> 0x5) & 0x7;
		int g = (packedRgb >> 0x2) & 0x7;
		int b = packedRgb & 0x3;
		
		int rx = (int) Math.floor(r * (0x24 + .5));
		int gx = (int) Math.floor(g * (0x24 + .5));
		int bx = b * 0x55;
		
		// System.out.println("R " + Integer.toBinaryString(r) + " " + rx);
		// System.out.println("G " + Integer.toBinaryString(g) + " " + gx);
		// System.out.println("B " + Integer.toBinaryString(b) + " " + bx);
		
		return new Color(rx, gx, bx);
	}
}
