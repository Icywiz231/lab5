//Ashutosh Sharma 2016231
import java.util.Scanner;

public class Lab11 {
	static int n, m; // input variables
	static Student phd[]; // input variables
	static Student pg[]; // input variables
	static Student ug[]; // input variables
	static int l1 = 0, l2 = 0, l3 = 0; // input variables

	public static void main(String ar[]) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		phd = new Student[n];
		pg = new Student[n];
		ug = new Student[n];
		int p = 0, q = 0, r = 0;
		for (int i = 0; i < n; i++) { // Input loop
			String name = sc.next();
			String roll = sc.next();
			String prog = sc.next();
			int dist = sc.nextInt();
			if (prog.equals("PhD")) {
				phd[l1] = new Student(name, roll, prog, dist, i);
				l1++;
			} else if (prog.equals("PG")) {
				pg[l2] = new Student(name, roll, prog, dist, i);
				l2++;
			} else {
				ug[l3] = new Student(name, roll, prog, dist, i);
				l3++;
			}
		}
		ug = sort(ug, l3); // Sorting the loops
		pg = sort(pg, l2); // Sorting the loops
		phd = sort(phd, l1); // Sorting the loops
		int count = 0;
		Student fin[] = new Student[m];
		if (phd.length < m / 2) { // Select first half PhD students
			for (int i = 0; i < phd.length; i++) {
				fin[count] = phd[i];
				count++;
			}
		} else {
			for (int i = 0; i < m / 2; i++) {
				fin[count] = phd[i];
				count++;
			}
		}
		if (pg.length < m / 2) { // Select first half PG students
			for (int i = 0; i < pg.length; i++) {
				fin[count] = pg[i];
				count++;
			}
		} else {
			for (int i = 0; i < m / 2; i++) {
				fin[count] = pg[i];
				count++;
			}
		}
		if (count != m) { // Fill remaining rooms
			for (int i = count; i < m; i++) {
				fin[count] = ug[i - count];
			}
		}
		Student temp;
		Student[] arr = new Student[m];
		for (int i = 0; i < m; i++) {
			arr[i] = fin[i];
		}
		for (int i = 0; i < m; i++) { // Sort all existing elements
			for (int j = i + 1; j < m; j++) {
				if (arr[j].index < arr[i].index) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			System.out.println(arr[i].name + " " + arr[i].roll + " " + arr[i].prog + " " + arr[i].dist);
		} 
	}

	public static Student[] sort(Student st[], int a) { // Sorting function
		Student temp;
		Student[] arr = new Student[a];
		for (int i = 0; i < a; i++) {
			arr[i] = st[i];
		}
		for (int i = 0; i < a; i++) {
			for (int j = i + 1; j < a; j++) {
				if (arr[j].dist > arr[i].dist) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return (arr);
	}
}

class Student {
	String name, roll, prog;
	int dist, index;

	public Student(String name, String roll, String prog, int dist, int index) {
		this.name = name;
		this.roll = roll;
		this.prog = prog;
		this.dist = dist;
		this.index = index;
	}
}



