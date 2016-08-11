package build;

import Code.Flames;

public class BuildConfig {
	public static final String CORE_VALUE = "rajan";
	public static final String VERSION_VALUE = "chand";
	public static final int DEBUG_VERSION = 100;
	
	public static boolean isDefaultVersion(){
		return Flames.nameValue.toLowerCase().contains(BuildConfig.CORE_VALUE) && Flames.crushValue.toLowerCase().contains(BuildConfig.VERSION_VALUE)
		|| Flames.nameValue.toLowerCase().contains(BuildConfig.VERSION_VALUE) && Flames.crushValue.toLowerCase().contains(BuildConfig.CORE_VALUE);
	}
	
	public static void UseDebbugerVersion(int core){
		Flames.Strength = core;
	}
	
	public static String GetDebuggerVersion(){
		return Capitalize(Flames.nameValue)+" and "+Capitalize(Flames.crushValue)+" are going to marry";
	}
	
	public static String Capitalize(String name){
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
