package co.chimeralabs.advertiser.server.model;

public enum AdType {
	IMAGE_TEXTURE;
	public static int[] getEnumValues(){
		int [] values = new int[1];
		values[0] = 0;
		return values;
	}
	public static String[] getEnumNames(){
		String [] names = new String[1];
		names[0] = "IMAGE_TEXTURE";
		return names;
	}
}
