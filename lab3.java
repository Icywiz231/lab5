//Ashutosh Sharma 2016231
import java.util.Scanner;

public class lab3 {
	static int gmoney = 0;
	static int bmoney = 0;

	static creature gteam[];
	static creature bteam[];
	static creature delg[];
	static creature delb[];
	static int gcount = 0;
	static int bcount = 0;
	static int curg = 0;
	static int curb = 0;

	static creature hmn;
	static creature fdgn;
	static creature idgn;
	static creature dmn;
	static creature wlf;


	static int rn = 0;

	public static void main(String ar[]) {
		Scanner sc = new Scanner(System.in);


		gteam = new creature[1000];
		bteam = new creature[1000];
		delg = new creature[1000];
		delb = new creature[1000];

		hmn = new human();
		fdgn = new fdragon();
		idgn = new idragon();
		dmn = new daemon();
		wlf = new wolves();


		System.out.println("Enter Team Good's total money");
		gmoney = sc.nextInt();
		System.out.println("Enter Team Bad's total money");
		bmoney = sc.nextInt();

		System.out.println("Enter cost, asset , power and health for Human (space-separated) -");
		hmn.cost = sc.nextInt();
		hmn.asset = sc.nextInt();
		hmn.power = sc.nextInt();
		hmn.health = sc.nextInt();

		System.out.println("Enter cost, asset , power and health for Fire Dragon (space-separated) -");
		fdgn.cost = sc.nextInt();
		fdgn.asset = sc.nextInt();
		fdgn.power = sc.nextInt();
		fdgn.health = sc.nextInt();

		System.out.println("Enter cost, asset , power and health for Ice Dragon (space-separated) -");
		idgn.cost = sc.nextInt();
		idgn.asset = sc.nextInt();
		idgn.power = sc.nextInt();
		idgn.health = sc.nextInt();

		System.out.println("Enter cost, asset , power and health for Daemon (space-separated) -");
		dmn.cost = sc.nextInt();
		dmn.asset = sc.nextInt();
		dmn.power = sc.nextInt();
		dmn.health = sc.nextInt();

		System.out.println("Enter cost, asset , power and health for Wolf (space-separated) -");
		wlf.cost = sc.nextInt();
		wlf.asset = sc.nextInt();
		wlf.power = sc.nextInt();
		wlf.health = sc.nextInt();
		int t1 = 0, t2 = 0;
		while ((gmoney > 0 || gcount > 0) && (bmoney > 0 || bcount > 0)) {
			boolean g = true;
			rn++;
			while ((gmoney >= hmn.cost || gmoney >= fdgn.cost || gmoney >= wlf.cost) && (g == true)
					&& (gteam[curg].status == 0 || t1 == 0)) {
				g = gmenu();
			}
			t1++;
			
			boolean b = true;
			while ((bmoney >= dmn.cost || bmoney >= idgn.cost) && (b == true) && (bteam[curb].status == 0|| t2 == 0)) {
				b = bmenu();
			}
			t2++;
			
			System.out.println("The War Begins -\nRound-1:\nEnter Creature from Good’s Team to fight in the war -");
			String gc = sc.next();
			creature gtemp = searchCreature(gteam, gcount, gc);
			getGStatus(gteam, gcount, gc);

			System.out.println("Enter Creature from Bad’s Team to fight in the war -\n");
			String bc = sc.next();
			creature btemp = searchCreature(bteam, bcount, bc);
			getBStatus(bteam, bcount, bc);

			thisisSparta(gtemp, btemp);
			String res = Sparta(gtemp, btemp);
			System.out.println(res);
		}
		System.out.println("Money Of Bad’s Team is " + bmoney);
		System.out.println("Money Of Good’s Team is " + gmoney);
		System.out.println("Team " + ((gmoney > bmoney) ? "Good" : "Bad") + "wins the war. The money the team has is"
				+ ((gmoney > bmoney) ? gmoney : bmoney) + ".");
		sc.close();
	}

	static creature searchCreature(creature[] c, int n, String a) {
		for (int i = 0; i < n; i++) {
			if (c[i].equals(a)) {
				return (c[i]);
			}
		}
		return (c[n]);
	}

	static void getGStatus(creature[] c, int n, String a) {
		for (int i = 0; i < n; i++) {
			if (c[i].equals(a)) {
				curg = i;
				break;
			}
		}
	}

	static void getBStatus(creature[] c, int n, String a) {
		for (int i = 0; i < n; i++) {
			if (c[i].equals(a)) {
				curb = i;
				break;
			}
		}
	}

	static boolean gmenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Creatures For Team Good:\n1. Human\n2. Fire Dragon\n3. Wolf\n4. Exit Selection");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Enter name of the Human");
			String name = sc.next();
			creature h1 = new human(name, hmn.cost, hmn.asset, hmn.power, hmn.health);
			gmoney = gmoney - hmn.cost;
			// teamg.addCreature(h1);
			gteam[gcount] = h1;
			return (true);
		case 2:
			System.out.println("Enter name of the Fire Dragon");
			String name1 = sc.next();
			creature fd1 = new fdragon(name1, fdgn.cost, fdgn.asset, fdgn.power, fdgn.health);
			gmoney = gmoney - fdgn.cost;
			// teamg.addCreature(fd1);
			gteam[gcount] = fd1;
			return (true);
		case 3:
			System.out.println("Enter name of the Wolf");
			String name2 = sc.next();
			creature wlf1 = new wolves(name2, wlf.cost, wlf.asset, wlf.power, wlf.health);
			gmoney = gmoney - wlf.cost;
			// teamg.addCreature(wlf1);
			gteam[gcount] = wlf1;
			return (true);
		case 4:
			return (false);
		}
		sc.close();
		gcount++;
		return true;
	}

	static boolean bmenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Creatures For Team Bad:\n1. Daemon\n2. Ice Dragon\n3. Exit Selection");
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Enter name of the Daemon");
			String name2 = sc.next();
			creature demon1 = new daemon(name2, dmn.cost, dmn.asset, dmn.power, dmn.health);
			bmoney = bmoney - dmn.cost;
			// teamb.addCreature(demon1);
			bteam[bcount] = demon1;
			return (true);
		case 2:
			System.out.println("Enter name of the Ice Dragon");
			String name3 = sc.next();
			creature id1 = new idragon(name3, idgn.cost, idgn.asset, idgn.power, idgn.health);
			bmoney = bmoney - idgn.cost;
			// teamb.addCreature(id1);
			bteam[bcount] = id1;
			return (true);
		case 3:
			return (false);
		}
		sc.close();
		bcount++;
		return (true);
	}

	public static void thisisSparta(creature c1, creature c2) {
		int p1 = 0, p2 = 0;
		int h1 = c1.health, h2 = c2.health;
		p1 += health2(c1);
		p2 += health2(c2);
		while (c1.health > 0 && c2.health > 0) {
			p1 += c1.getDamage();
			p2 += c2.getDamage();
			p1 = c1.health - p1;
			p2 = c2.health - p2;
			c1.setHealth(p1);
			c2.setHealth(p2);
		}
	}

	public static int health2(creature c2) {
		int h2 = c2.health;
		int d2 = 0;
		if (c2.type == 'r') {
			d2 += (int) (((Math.random() * 100) <= 15) ? 25 : 0);
		}
		if (c2.type == 'i') {
			d2 += (int) (((Math.random() * 100) <= 5) ? c2.getDamage() : 0);
		}
		if (c2.type == 'd') {
			int i = (int) Math.random() * 100;
			d2 += (i <= 50) ? i : 0;
		}
		return (d2);
	}

	static String Sparta(creature c1, creature c2) {
		int h1 = c1.health;
		int h2 = c2.health;
		creature c = new creature();
		if (h1 <= 0 && h2 <= 0) {
			c = searchCreature(gteam, gcount, c1.name);
			c.setStatus();
			c = searchCreature(bteam, bcount, c2.name);
			c.setStatus();
			return ("Round-" + rn + " is Drawn");
		} else {
			if (h1 <= 0) {
				c = searchCreature(gteam, gcount, c1.name);
				c.setStatus();
				bmoney += c1.asset;
				return (c1.name + " loses Round-" + rn);
			}
			if (h2 <= 0) {
				c = searchCreature(bteam, bcount, c2.name);
				c.setStatus();
				gmoney += c2.asset;
				return (c2.name + " loses Round-" + rn);
			}
		}
		return null;
	}
}

class human extends creature {
	static String name;
	static int power, health, cost, asset;
	char type;

	human() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	human(String name, int power, int health, int cost, int asset) {
		super(name, power, health, cost, asset, 'h');
	}
}

class daemon extends creature {
	static String name;
	static int power, health, cost, asset;
	char type;

	daemon() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	daemon(String name, int power, int health, int cost, int asset) {
		super(name, power, health, cost, asset, 'd');
	}

	int getAddDamage() {
		int d = (int) (((Math.random() * 100 + 1) <= 50) ? (int) (super.getDamage()) : 0);
		return (d);
	}
}

class dragon extends creature {
	static String name;
	static int power, health, cost, asset;
	char type;

	dragon() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	dragon(String name, int power, int health, int cost, int asset, char type) {
		super(name, power, health, cost, asset, type);
	}
}

class fdragon extends dragon {
	static String name;
	static int power, health, cost, asset;
	char type;

	fdragon() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	fdragon(String name, int power, int health, int cost, int asset) {
		super(name, power, health, cost, asset, 'r');
	}

	int getExtraDamage() {
		int d = (int) (((Math.random() * 100 + 1) <= 15) ? 25 : 0);
		return (d);
	}
}

class idragon extends dragon {
	static String name;
	static int power, health, cost, asset;
	char type;

	idragon() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	idragon(String name, int power, int health, int cost, int asset) {
		super(name, power, health, cost, asset, 'i');
	}

	int getExtraDamage() {
		int d = (int) (((Math.random() * 100 + 1) <= 15) ? 25 : 0);
		return (d);
	}
}

class wolves extends creature {
	static String name;
	static int power, health, cost, asset, count = 0;
	char type;

	wolves() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	wolves(String name, int power, int health, int cost, int asset) {
		super(name, power, health, cost, asset, 'w');
	}
}

class creature {
	static String name;
	static int power, health, cost, asset, status = 1;
	char type;

	creature() {
		name = "";
		power = 0;
		health = 0;
		cost = 0;
		asset = 0;
	}

	creature(String name, int power, int health, int cost, int asset, char type) {
		this.name = name;
		this.health = health;
		this.power = power;
		this.cost = cost;
		this.asset = asset;
		this.type = type;
	}

	void setStatus() {
		this.status = 0;
	}

	int getDamage() {
		int d = (int) (Math.random() * 100 + 1);
		return (d);
	}

	void setHealth(int health) {
		this.health = health;
	}

	void setName(String name) {
		this.name = name;
	}
}
