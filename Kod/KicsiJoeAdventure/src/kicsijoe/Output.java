package kicsijoe;

/**
 * Class Output
 * It handles the output on the terminal.
 */
class Output{

	// Number of tabs.
	public static int tabs = 0;
	// Enables or disables the output depending on its value.
	private static boolean enabled = true;

/*
 * Called whenever a method starts and prints out it's event.
 *
 * @param name Name of the method.
 */
	public static void methodStarts(String name){

	tabs++;
	if (enabled){
		for (int i=0; i<tabs; i++)
			System.out.print("   ");
		System.out.println("-> " + name);
	}

	}	

/*
 * Called whenever a method ends and prints out it's event.
 *
 * @param name Name of the method.
 */
	public static void methodEnds(String name){
	if (enabled){
		for (int i=0; i<tabs; i++)
			System.out.print("   ");
		System.out.println("<- " + name);
	}
	tabs--;

	}

/*
 * Output is stopped until its resumed again.
 */
	public static void ignore(){
		enabled = false;
	}

/*
 * Output is resumed.
 */
	public static void resume(){
		enabled = true;
	}

}

