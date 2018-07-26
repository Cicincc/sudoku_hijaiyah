package suhi.mesin;

public class toMath {
	private static int z = 12357;
	private static int repeat = 1;

	public static int multiplicativeCRNG(int a, int batas) {
		z = (a * z) % (batas + 1);
//		repeat++;
		return z;
	}

	public static int getZ() {
		return z;
	}

	public static void setZ(int z) {
		toMath.z = z;
	}

	public static int getRepeat() {
		return repeat;
	}

	public static void setRepeat(int repeat) {
		toMath.repeat = repeat;
	}
	
	
}
