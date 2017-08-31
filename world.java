import java.util.Scanner;
import java.util.Random;

public class world {
	// private static Queue queue=new Queue();
	private static int n = 20;
	private static int in = 0, fn = 0;
	private static Animal queue[] = new Animal[100];
	private static Grassland g1 = new Grassland();
	private static Grassland g2 = new Grassland();
	private static Animal h1 = new Herbivore();
	private static Animal h2 = new Herbivore();
	private static Animal c1 = new Carnivore();
	private static Animal c2 = new Carnivore();
	static int max = 0, count = 0;
	static int HC = 2, CC = 2;
	static int animalcount = 4;

	world(int n) {
		this.n = n;
	}

	public static void main(String ar[]) {
//		 Scanner sc = new Scanner(System.in);
//		 System.out.println("Enter Total Final Time for Simulation:");
//		 n = sc.nextInt();
//		 world ob = new world(n);
//		
//		 System.out.println("Enter x, y centre, radius and Grass Available for First Grassland:");
//		 g1.cenx = sc.nextInt();
//		 g1.ceny = sc.nextInt();
//		 g1.rad = sc.nextInt();
//		 g1.grass = sc.nextInt();
//		
//		 System.out.println("Enter x, y centre, radius and Grass Available for Second Grassland:");
//		 g2.cenx = sc.nextInt();
//		 g2.ceny = sc.nextInt();
//		 g2.rad = sc.nextInt();
//		 g2.grass = sc.nextInt();
//		
//		 System.out.println("Enter Health and Grass Capacity for Herbivores:");
//		 int n1 = sc.nextInt();
//		 int n2 = sc.nextInt();
//		 h1.setHealth(n1);
//		 h1.setGC(n2);
//		 h2.setHealth(n1);
//		 h2.setGC(n2);
//		 h1.sno=1;
//		 h2.sno=0;
//		 c1.sno=1;
//		 c2.sno=2;
//		 System.out.println("Enter x, y position and timestamp for First Herbivore:");
//		 h1.posx = sc.nextInt();
//		 h1.posy = sc.nextInt();
//		 h1.ts = sc.nextInt();
//		 h1.type = 'H';
//		 System.out.println("Enter x, y position and timestamp for Second Herbivore:");
//		 h2.posx = sc.nextInt();
//		 h2.posy = sc.nextInt();
//		 h2.ts = sc.nextInt();
//		 h2.type = 'H';
//		
//		 System.out.println("Enter Health for Carnivores:");
//		 n1 = sc.nextInt();
//		 c1.setHealth(n1);
//		 c2.setHealth(n1);
//		
//		 System.out.println("Enter x, y position and timestamp for First Carnivore:");
//		 c1.posx = sc.nextInt();
//		 c1.posy = sc.nextInt();
//		 c1.ts = sc.nextInt();
//		 c1.type = 'C';
//		 System.out.println("Enter x, y position and timestamp for Second Carnivore:");
//		 c2.posx = sc.nextInt();
//		 c2.posy = sc.nextInt();
//		 c2.ts = sc.nextInt();
//		 c2.type = 'C';
		queuing();
		simulator();
	}

	private static int Randgen() {
		Random r = new Random();
		return (r.nextInt(100));
	}

	private static void display() {
		System.out.println(h1.toString() + "\n" + h2.toString() + "\n" + c1.toString() + "\n" + c2.toString());
	}

	private static void queuing() {
		c1.posx = 1;
		c1.posy = 5;
		c1.health = 25;
		c1.type = 'C';
		c1.ts = 12;
		c1.sno = 1;
		c2.posx = 2;
		c2.posy = 7;
		c2.health = 25;
		c2.type = 'C';
		c2.ts = 10;
		c2.sno = 2;
		h1.posx = 2;
		h1.posy = 2;
		h1.health = 15;
		h1.type = 'H';
		h1.ts = 5;
		h1.sno = 1;
		h1.gc = 10;
		h2.posx = 0;
		h2.posy = -5;
		h2.health = 15;
		h2.type = 'H';
		h2.ts = 15;
		h2.gc = 10;
		h2.sno = 2;
		g1.cenx = 5;
		g1.ceny = 5;
		g1.grass = 15;
		g1.rad = 2;
		g2.cenx = 0;
		g2.ceny = 0;
		g2.grass = 5;
		g2.rad = 2;

		Animal sort[] = { c1, c2, h1, h2 };
		Animal temp;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (sort[j].ts > sort[j + 1].ts) {
					temp = sort[j];
					sort[j] = sort[j + 1];
					sort[j + 1] = temp;
				} else {
					if (sort[j].ts == sort[j + 1].ts) {
						if (sort[j].health > sort[j + 1].health) {
							temp = sort[j];
							sort[j] = sort[j + 1];
							sort[j + 1] = temp;
						} else {
							if (sort[j].health == sort[j + 1].health) {
								if (sort[j].type < sort[j + 1].type) {
									temp = sort[j];
									sort[j] = sort[j + 1];
									sort[j + 1] = temp;
								}
							} else {
								if (sort[j].type == sort[j + 1].type) {
									float d1 = distt(sort[j].posx, sort[j].posy, 0, 0);
									float d2 = distt(sort[j + 1].posx, sort[j + 1].posy, 0, 0);
									if (d1 > d2) {
										temp = sort[j];
										sort[j] = sort[j + 1];
										sort[j + 1] = temp;
									}
								}
							}
						}
					}
				}
			}
		}
		enqueue(sort[0]);
		enqueue(sort[1]);
		enqueue(sort[2]);
		enqueue(sort[3]);
	}

	private static float distt(int ix, int iy, int fx, int fy) {
		return ((float) Math.sqrt((fx - ix) * (fx - ix) + ((fy - iy) * (fy - iy))));
	}

	private static void queueup(Animal[] sort) {
		Animal temp;
		for (int i = in; i < fn; i++) {
			for (int j = 0; j < fn - 1; j++) {
				if (sort[j].ts > sort[j + 1].ts) {
					temp = sort[j];
					sort[j] = sort[j + 1];
					sort[j + 1] = temp;
				} else {
					if (sort[j].ts == sort[j + 1].ts) {
						if (sort[j].health > sort[j + 1].health) {
							temp = sort[j];
							sort[j] = sort[j + 1];
							sort[j + 1] = temp;
						} else {
							if (sort[j].health == sort[j + 1].health) {
								if (sort[j].type < sort[j + 1].type) {
									temp = sort[j];
									sort[j] = sort[j + 1];
									sort[j + 1] = temp;
								}
							} else {
								if (sort[j].type == sort[j + 1].type) {
									float d1 = distt(sort[j].posx, sort[j].posy, 0, 0);
									float d2 = distt(sort[j + 1].posx, sort[j + 1].posy, 0, 0);
									if (d1 > d2) {
										temp = sort[j];
										sort[j] = sort[j + 1];
										sort[j + 1] = temp;
									}
								}
							}
						}
					}
				}
			}
		}

	}

	private static void simulator() {
		System.out.println("The Simulation Begins -");
		float d1, d2;
		for (int i = 0; (i < n && animalcount > 0); i++) {
			Animal ob = dequeue();
			switch (ob.type) {
			case 'H':
				System.out.println("It is Herbivore " + ob.sno);
				d1 = distt(ob.posx, ob.posy, g1.cenx, g1.ceny);
				d2 = distt(ob.posx, ob.posy, g2.cenx, g2.ceny);
				if (d1 < d2) {// to check which grassland is nearer
					if (d1 <= g1.rad) { // check whether inside grassland or not
						if (ob.gc <= g1.grass) { // check grass capacity
							int c = Randgen();
							if (c <= 90) { // 90% case decides to have grass
								g1.grass -= ob.gc;
								h1.health += (int) ((0.5) * h1.health);
								ob.health=h1.health;
								updateTimeStamp(ob);
							} else { // 10% case decides to leave
								int a = Randgen();
								if (a <= 50) {// moving away from carnivore
									moveHerbi(ob, 2, 1);
									updateTimeStamp(ob);
								} else {// moving towards a grassland
									moveGrassland(ob, 3, -1);
									updateTimeStamp(ob);
								}
							}
						} else {// if capacity is less
							int a = Randgen();
							if (a <= 20) {// finishes the grassland
								h1.health += (int) ((0.2) * h1.health);
								updateTimeStamp(ob);
								g1.grass = 0;
								ob.health=h1.health;
							} else {
								int b = Randgen();
								if (b <= 70) {
									moveHerbi(ob, 4, 1);
									updateTimeStamp(ob);
								} else {
									moveGrassland(ob, 2, -1);
									updateTimeStamp(ob);
								}
							}
						}
					} else {
						int a = Randgen();
						if (a <= 5) {
						} else {
							int b = Randgen();
							if (b > 65) {// moving away from carnivore
								moveHerbi(ob, 4, 1);
								updateTimeStamp(ob);
							} else {// moving towards a grassland
								moveGrassland(ob, 5, -1);
								updateTimeStamp(ob);
							}
						}
					}
				} else { // to check which grassland is nearer
					if (d2 <= g2.rad) { // check whether inside grassland or not
						if (ob.gc <= g2.grass) { // check grass capacity
							int c = Randgen();
							if (c <= 90) { // 90% case decides to have grass
								g2.grass -= ob.gc;
								h2.health += (0.5) * h2.health;
								h2.health = (int) h2.health;
								ob.health=h2.health;
								updateTimeStamp(ob);
							} else { // 10% case decides to leave
								int a = Randgen();
								if (a <= 50) {// moving away from carnivore
									moveHerbi(ob, 2, 1);
									updateTimeStamp(ob);
								} else {// moving towards a grassland
									moveGrassland(ob, 3, -1);
									updateTimeStamp(ob);
								}
							}
						} else {// gc is more than grass available
							int a = Randgen();
							if (a <= 20) {// finishes the grassland
								h2.health += (int) ((0.2) * h2.health);
								updateTimeStamp(ob);
								g1.grass = 0;
								ob.health=h2.health;
							} else {
								int b = Randgen();
								if (b <= 70) {
									moveHerbi(ob, 4, 1);
									updateTimeStamp(ob);
								} else {
									moveGrassland(ob, 2, -1);
									updateTimeStamp(ob);
								}
							}
						}
					} else {
						int a = Randgen();
						if (a <= 5) {
							updateTimeStamp(ob);
						} else {
							int b = Randgen();
							if (b > 65) {// moving away from carnivore
								moveHerbi(ob, 4, 1);
								updateTimeStamp(ob);
							} else {// moving towards a grassland
								moveGrassland(ob, 5, -1);
								updateTimeStamp(ob);
							}
						}
					}
				}
				if (ob.health <= 0) {
					System.out.println("It is dead");
				} else {
					System.out.println("Its health is " + ob.health);
				}
				break;
			case 'C':
				System.out.println("It is Carnivore " + ob.sno);
				d1 = distt(ob.posx, ob.posy, g1.cenx, g1.ceny);
				d2 = distt(ob.posx, ob.posy, g2.cenx, g2.ceny);
				float f1 = distt(ob.posx, ob.posy, h1.posx, h1.posy);
				float f2 = distt(ob.posx, ob.posy, h2.posx, h2.posy);
				if (f1 <= 1) {
					int h = (int) ((2 / 3) * h2.health);
					h1.setHealth(0);
					updateTimeStamp(ob);
					if (ob.sno == 1) {
						c1.health += h;
						ob.health=c1.health;
					} else {
						c2.health += h;
						ob.health=c2.health;
					}
				} else if (f2 <= 1) {
					int h = (int) ((2 / 3) * h2.health);
					h2.setHealth(0);
					updateTimeStamp(ob);
					if (ob.sno == 1) {
						c1.health += h;
						ob.health=c1.health;
					} else {
						c2.health += h;
						ob.health=c2.health;
					}
				} else {
					if (d1 < d2) {// check which grassland is nearer
						if (d1 <= g1.rad) {// check whether inside grassland or not
							int a = Randgen();
							if (a <= 25) {// stays in grassland
								c1.health -= 30;
								ob.health=c1.health;
								updateTimeStamp(ob);
							} else {// moves towards a herbi
								moveCarni(ob, 2, -1);
								updateTimeStamp(ob);
							}
						} else {// outside grassland
							int a = Randgen();
							if (a <= 8) {// decides to stay
								c1.health -= 60;
								ob.health=c1.health;
								updateTimeStamp(ob);
							} else {// moves
								moveCarni(ob, 4, -1);
								updateTimeStamp(ob);
							}
						}
					} else {// check which grassland is nearer
						if (d2 <= g2.rad) {// check whether inside grassland or not
							int a = Randgen();
							if (a <= 25) {// stays in grassland
								c2.health -= 30;
								ob.health=c2.health;
								updateTimeStamp(ob);
							} else {// moves towards a herbi
								moveCarni(ob, 2, -1);
								updateTimeStamp(ob);
							}
						} else {// outside grassland
							int a = Randgen();
							if (a <= 8) {// decides to stay
								c2.health -= 60;
								ob.health=c2.health;
								updateTimeStamp(ob);
							} else {// moves
								moveCarni(ob, 4, -1);
								updateTimeStamp(ob);
							}
						}
					}
				}
				if (ob.health <= 0) {
					System.out.println("It is dead");
				} else {
					System.out.println("Its health is " + ob.health);
				}
				break;
			}
		}
		// queueup(queue);
	}

	private static void updateTimeStamp(Animal ob) {
		Random r = new Random();
		int ts1 = r.nextInt(n - max) + max;
		// System.out.println("Timestamp is " + ts1);
		if (ts1 < (n - 1)) {
			ob.ts = ts1;
			enqueue(ob);
			if (ts1 > max) {
				max = ts1;
			}
		} else {
			animalcount--;
		}
	}

	private static int coordinate(int x1, int y1, int x2, int y2, int r, int rel) {// rel=1 for move away rel=-1 for //
																					// move towards
		int x;
		x = (int) (x1 + rel * r * ((x2 - x1) / distt(x1, y1, x2, y2)));
		return (x);
	}

	private static void moveHerbi(Animal ob, int r, int rel) {
		int dc1 = (int) distt(ob.posx, ob.posy, c1.posx, c1.posy);
		int dc2 = (int) distt(ob.posx, ob.posy, c2.posx, c2.posy);
		if (dc1 > dc2) {
			int x = coordinate(ob.posx, ob.posy, c1.posx, c1.posy, r, rel);
			int y = coordinate(ob.posy, ob.posx, c1.posy, c1.posx, r, rel);
			ob.posx = x;
			ob.posy = y;
		} else {
			int x = coordinate(ob.posx, ob.posy, c2.posx, c2.posy, r, rel);
			int y = coordinate(ob.posy, ob.posx, c2.posy, c2.posx, r, rel);
			ob.posx = x;
			ob.posy = y;
		}
	}

	private static void moveCarni(Animal ob, int r, int rel) {
		int dc1 = (int) distt(ob.posx, ob.posy, h1.posx, h1.posy);
		int dc2 = (int) distt(ob.posx, ob.posy, h2.posx, h2.posy);
		if (dc1 < dc2) {
			int x = coordinate(ob.posx, ob.posy, h1.posx, c1.posy, r, rel);
			int y = coordinate(ob.posy, ob.posx, h1.posy, h1.posx, r, rel);
			ob.posx = x;
			ob.posy = y;
		} else {
			int x = coordinate(ob.posx, ob.posy, h2.posx, h2.posy, r, rel);
			int y = coordinate(ob.posy, ob.posx, h2.posy, h2.posx, r, rel);
			ob.posx = x;
			ob.posy = y;
		}
	}

	private static void moveGrassland(Animal ob, int r, int rel) {
		int dc1 = (int) distt(ob.posx, ob.posy, g1.cenx, g1.ceny);
		int dc2 = (int) distt(ob.posx, ob.posy, g2.cenx, g2.ceny);
		if (dc1 > dc2) {
			int x = coordinate(ob.posx, ob.posy, g1.cenx, g1.ceny, r, rel);
			int y = coordinate(ob.posy, ob.posx, g1.ceny, g1.cenx, r, rel);
			ob.posx = x;
			ob.posy = y;
		} else {
			int x = coordinate(ob.posx, ob.posy, g1.cenx, g1.ceny, r, rel);
			int y = coordinate(ob.posy, ob.posx, g1.ceny, g1.cenx, r, rel);
			ob.posx = x;
			ob.posy = y;
		}
	}

	private static void enqueue(Animal ob) {
		queue[fn] = ob;
		fn++;
		count++;
	}

	private static Animal dequeue() {
		in++;
		count--;
		return (queue[in - 1]);
	}
}

abstract class Animal {
	int posx, posy, ts, health, gc = 0, sno;
	char type;

	Animal() {
		posx = 0;
		posy = 0;
		ts = 0;
		health = 0;
		sno = 0;
	}

	Animal(int posx, int posy, int ts, int sno) {
		this.posx = posx;
		this.posy = posy;
		this.ts = ts;
		this.sno = sno;
	}

	void setHealth(int health) {
		this.health = health;
	}

	void setGC(int gc) {
		this.gc = gc;
	}

	public String toString() {
		return ((posx + " " + posy + " " + ts + " " + health + " " + gc));
	}
}

class Herbivore extends Animal {
	static int posx, posy, ts, health, gc, sno;

	Herbivore() {
		posx = 0;
		posy = 0;
		ts = 0;
		health = 0;
		gc = 0;
		sno = 0;
	}

	Herbivore(int posx, int posy, int ts, int sno) {
		super(posx, posy, ts, sno);
	}
}

class Carnivore extends Animal {
	static int posx, posy, ts, health, sno;

	Carnivore() {
		posx = 0;
		posy = 0;
		ts = 0;
		health = 0;
		sno = 0;
	}

	Carnivore(int posx, int posy, int ts, int sno) {
		super(posx, posy, ts, sno);
	}
}

class Grassland {
	int cenx, ceny, rad, grass;

	Grassland() {
		cenx = 0;
		ceny = 0;
		rad = 0;
		grass = 0;
	}

	Grassland(int cenx, int ceny, int rad, int grass) {
		this.cenx = cenx;
		this.ceny = ceny;
		this.rad = rad;
		this.grass = grass;
	}

	public String toString() {
		return ((cenx + " " + ceny + " " + grass + " " + rad));
	}
}
//
//class Node {
//	Animal a;
//	Node link;
//
//	Node() {
//		a = null;
//		link = null;
//	}
//
//	Node(Animal a) {
//		this.a = a;
//	}
//
//	void setLink(Node link) {
//		this.link = link;
//	}
//
//	Node getLink() {
//		return (link);
//	}
//}
//
//class Queue {
//	Node head;
//
//	Queue() {
//		head = null;
//	}
//
//	Queue(Node head) {
//		this.head = head;
//	}
//
//	void enqueue(Node temp) {
//		head.setLink(temp);
//		head = temp;
//	}
//
//	void dequeue() {
//		head.link = head.link.link;
//	}
//}
