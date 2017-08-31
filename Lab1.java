import java.util.Scanner;
public class Lab1 {
	public static void main(String ar[]) {
		Scanner sc = new Scanner(System.in);
		int i,n, m, phdc=0, pgc=0, ugc=0;
		n = sc.nextInt();
		m = sc.nextInt();
		int l1=0,l2=0,l3=0;
		Student phd[] = new Student[n];
		Student pg[] = new Student[n];
		Student ug[] = new Student[n];
		Student alotee[] = new Student[m];
		for (i = 0; i < n; i++) {
			String name = sc.next();
			String roll = sc.next();
			String prog = sc.next();
			int dist = sc.nextInt();
			if (prog == "PhD") {	
				phd[l1] = new Student(name, roll, prog, dist,i);
				System.out.println("shoots");
				l1++;
			}			
			if (prog == "PG") {
				pg[l2] = new Student(name, roll, prog, dist,i);
				l2++;
			}
			if (prog == "UG") {
				ug[l3] = new Student(name, roll, prog, dist,i);
				l3++;
			}
		}
		if (phdc < n / 2) {
			for (i = 0; i < phdc; i++) {
				alotee[i] = phd[i];
			}
		} else {
			for (i = 0; i < n / 2; i++) {
				alotee[i] = phd[i];
			}
		}
		if (pgc < n / 2) {
			for (i = 0; i < pgc / 2; i++) {
				alotee[i] = pg[i];
			}
		}
		 else {
			for (i = 0; i < n / 2; i++) {
				alotee[i] = pg[i];
			}
		}
		//System.out.println(phd[0].name);
		for (int k = 0; k < alotee.length; k++) {
			System.out.println("*"+alotee[k].name);
		}
	}
}
