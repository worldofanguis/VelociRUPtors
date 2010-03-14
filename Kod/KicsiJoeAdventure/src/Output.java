

/**
 * Class Output:
 * A terminálon történő megjelenítést kezelő osztály.
 */
public class Output{

	// Tabulálások (aktuális) száma.
	public static int tabs = 0;
	// Engedélyezi vagy letiltja az osztály működését.
	private static boolean enabled = true;

/*
 * Egy metódus kezdetekor hívódik meg és kiírja a nevét (és paramétereit, ha vannak).
 *
 * @param id Az objektum neve.
 * @param name A metódus neve és paraméterei.
 */
	public static void methodStarts(String id, String name){

	tabs++;
	if (enabled){
		for (int i=0; i<tabs; i++)
			System.out.print("   ");
		System.out.println("--> " + id + "."+ name);
	}

	}	

/*
 * Egy metódus befejezésekor hívódik meg (amennyiben nincs
 * visszatérési értéke), kiírja a nevét.
 *
 * @param id Az objektum neve.
 * @param name A metódus neve és paraméterei.
 */
	public static void methodEnds(String id, String name){
	if (enabled){
		for (int i=0; i<tabs; i++)
			System.out.print("   ");
		System.out.println("<-- " + id + "." + name);
	}
	tabs--;

	}

 /*
  * Egy metódus befejezésekor hívódik meg, kiírja a nevét és
  * visszatérési értékét.
  *
  * @param id Az objektum neve.
  * @param name A metódus neve és paraméterei.
  * @param ret A visszatérési érték.
 */
	public static void methodEnds(String id, String name, String ret){
	if (enabled){
		for (int i=0; i<tabs; i++)
			System.out.print("   ");
		System.out.println("<-- " + name + " : " + ret);
	}
	tabs--;

	}

/*
 * Az osztály működésének letiltása.
 */
	public static void ignore(){
		enabled = false;
	}

/*
 * Az osztály működésének engedélyezése.
 */
	public static void resume(){
		enabled = true;
	}

}

