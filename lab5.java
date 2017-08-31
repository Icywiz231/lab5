import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class lab5 {
	private static int n, x;

	public static void main(String ar[]) {
		Scanner sc = new Scanner(System.in);
		x = sc.nextInt();
		n = sc.nextInt();
		BSTFilesBuilder cr = new BSTFilesBuilder();
		cr.createBSTFiles(n, x);

		for (int i = 1; i <= x; i++) {
			String filename = "./src/" + i + ".txt";
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				PrintWriter pw = new PrintWriter("./src/output.txt", "UTF-8");
				String s = br.readLine();
				int k = 0;
				if (s.charAt(0) == 'S') {
					BST bst = new BST();
					while ((s = br.readLine()) != null) {
						if (k >= 1) {
							String[] t = s.split("\\s");
							String str = "";
							for (String w : t) {
								bst.insert(w);
								str += w;
							}
							node hd = bst.getHead();
							int d = bst.inorder(bst.getHead(), hd.data, 0);
							if (i == d) {
								pw.println(i + " " + str);
							}
						}
						k++;
					}
				} else if (s.charAt(0) == 'I') {
					BST bst = new BST();
					while ((s = br.readLine()) != null) {
						if (k >= 1) {
							int sum = 0;
							String[] t = s.split("\\s");
							for (String w : t) {
								int n = Integer.parseInt(w);
								bst.insert(n);
								sum += n;
							}
							node hd = bst.getHead();
							int d = bst.inorder(bst.getHead(), hd.data, 0);
							if (i == d) {
								pw.println(i + " " + sum);
							}
						}
						k++;
					}
				} else {
					BST bst = new BST();
					while ((s = br.readLine()) != null) {
						if (k >= 1) {
							float sum = 0.0f;
							String[] t = s.split("\\s");
							for (String w : t) {
								float a = Float.parseFloat(w);
								bst.insert(w);
								sum += a;
							}
							node hd = bst.getHead();
							int d = bst.inorder(bst.getHead(), hd.data, 1);
							if (i == d) {
								pw.println(i + " " + sum);
							}
						}
						k++;
					}
					pw.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

class node<T extends Comparable<T>> {
	T data;
	node right, left;

	node() {
		data = null;
		left = null;
		right = null;
	}

	node(T data) {
		this.data = data;
	}

	node(T data, node left, node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	T getData() {
		return (this.data);
	}
}

class BST<T extends Comparable<T>> {
	node head;

	node getHead() {
		return (head);
	}

	void insert(T data) {
		head = insert(head, data);
	}

	private node insert(node h, T data) {
		if (h == null) {
			h = new node(data);
			return (h);
		} else {
			if (h.data.compareTo(data) >= 0) {
				h.left = (insert(h.left, data));
			} else {
				h.right = (insert(h.right, data));
			}
			return (h);
		}
	}

	int inorder(node h, T key, int pos) {
		if (h != null) {
			if (h.data.compareTo(key) > 0) {
				h = h.left;
				inorder(h, key, pos++);
			} else if (h.data.compareTo(key) < 0) {
				h = h.right;
				inorder(h, key, pos++);
			} else {
				return (pos);
			}
		}
		return -1;
	}
}