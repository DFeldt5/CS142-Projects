import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Test of CS 142 Assignment 2 by Martin Hock (Version of 8:00 PM 1/19/2019)
 * 
 * This code is for personal educational use only and may not be redistributed
 * without permission of the author.
 */

public class ManagersCrossingTest {

	public static int[] n(int[] a) {
		int[] b = Arrays.copyOf(a, a.length);
		for (int i = 0; i < b.length; i++) {
			b[i] = (b[i] == 2 ? 1 : 2);
		}
		return b;
	}
	
	public static void printError(int[] current, int[] next, String error, String received, boolean ret, boolean rret) {
		System.out.println();
		System.out.println("I saw a problem when I called isMoveOkay with these parameters,");
		System.out.println("passed in the following order and labeled according to the description:");
		System.out.println("boatBefore = " + current[6]);
		System.out.println("managerABefore = " + current[0] + ", managerBBefore = " + current[1]
				+ ", managerCBefore = " + current[2]);
		System.out.println("engineerABefore = " + current[3] + ", engineerBBefore = "
				+ current[4] + ", engineerCBefore = " + current[5]);
		System.out.println("boatAfter = " + (next.length == 7 ? next[6] : (current[6] == 2 ? 1 : 2)));
		System.out.println("managerAAfter = " + next[0] + ", managerBAfter = " + next[1]
				+ ", managerCAfter = " + next[2]);
		System.out.println("engineerAAfter = " + next[3] + ", engineerBAfter = " + next[4]
				+ ", engineerCAfter = " + next[5]);
		if (error == null) {
			System.out.println("I expected no message and a return of "+ret+".");
			if (received == null)
				System.out.println("I did see no message.");
			else
				System.out.println("I saw the following message: " + received);
		} else {
			System.out.println("I expected a message and a return of "+ret+".");
			System.out.println("Expected message: " + error);
			System.out.println("I saw "+(received == null ? "no message" : ": "+received));
		}
		System.out.println("Return value: "+rret);
	}

	public static boolean checkMove(int[] current, int[] next, String error) {
		PrintStream oldOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stream));
		boolean ret = ManagersCrossing.isMoveOkay(current[6], current[0],current[1],current[2],current[3],current[4],current[5],
                next.length == 7 ? next[6] : (current[6] == 2 ? 1 : 2), next[0], next[1], next[2], next[3], next[4], next[5]);
		System.setOut(oldOut);
		Scanner scanner = new Scanner(new String(stream.toByteArray()));
		String firstLine = null;
		if (scanner.hasNextLine())
			firstLine = scanner.nextLine().trim();
		scanner.close();
		if (error != null) {
			if (error.equals(firstLine)) {
				if (ret) {
					printError(current, next, error, firstLine, false, ret);
					System.out.println("You returned true instead of false!");
					return false;
				} else {
					return true;
				}
			} else {
				printError(current, next, error, firstLine, false, ret);
				System.out.println("The error message differed from what was expected.");
				return false;
			}
		} else {
			if (next == TWO && !"You solved the puzzle!".equals(firstLine)) {
				printError(current, next, "You solved the puzzle!", firstLine, true, ret);
				return false;
			}
			if (!ret) {
				printError(current, next, (next == TWO ? "You solved the puzzle!" : error), firstLine, true, ret);
				System.out.println("You returned false instead of true!");
				return false;
			} else {
				if (next == TWO) error = "You solved the puzzle!";
				if (firstLine != error && (firstLine == null || !firstLine.equals(error))) {
					printError(current, next, error, firstLine, true, ret);
					return false;
				}
				return true;
			}
		}
	}
	private static int[] TWO = { 2, 2, 2, 2, 2, 2 };

	public static void main(String[] args) {
		int score = 0;
		System.out.println("This program indicates the errors it sees.");
		System.out.println("Please check announcements to see if there is a new version. The version is at the top in a comment.");
		System.out.println(
				"This program only looks at the first line of output from your program. Make sure it is the appropriate error.");
		System.out.println("Errors should be checked in the order they are described in the assignment (the manager recruitment in alphabetical order)");
		System.out.println("Make sure your argument names are in the right order!");
		try {
			System.out.println("Testing a solution and its opposite to make sure your program accepts it.");
			int[][] moves = { { 2, 1, 1, 2, 1, 1 },
					{ 1, 1, 1, 2, 1, 1 }, { 1, 1, 1, 2, 2, 2 },
					{ 1, 1, 1, 1, 2, 2 }, { 1, 2, 2, 1, 2, 2 },
					{ 1, 1, 2, 1, 1, 2 }, { 2, 2, 2, 1, 1, 2 },
					{ 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 1, 2 },
					{ 2, 1, 2, 2, 1, 2 }, TWO };
			int[] start = { 1, 1, 1, 1, 1, 1, 1 };
			boolean solution = true;
			for (int i = 0; i < moves.length; i++) {
				if (i > 0) {
					System.arraycopy(moves[i - 1], 0, start, 0, moves[i - 1].length);
					start[6] = (start[6] == 2 ? 1 : 2);
					if (checkMove(n(start), n(moves[i]), null))
						score += 2;
					else solution = false;
				}
				if (checkMove(start, moves[i], null))
					score += 2;
				else solution = false;
			}
			if (solution) score += 10;
			boolean allPos = true;
			System.out.println("Testing some errors.");
			int[] c1 = { 1, 1, 1, 1, 1, 1, 1 },
					n0 = { 1, 1, 1, 1, 1, 1 },
					n1 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c1, n0, "You must move one or two people!"))
				score += 2;
			for (int i = 0; i < n0.length; i++) {
				n0[i] = 3;
				if (!checkMove(c1, n0, "All positions must be 1 or 2!"))
					allPos = false;
				n0[i] = 0;
				if (!checkMove(c1, n0, "All positions must be 1 or 2!"))
					allPos = false;
				n0[i] = 1;
			}
			if (checkMove(c1, new int[]{2, 2, 2, 2, 2, 2, 1}, "The boat must move!")) 
				score += 3;
			if (checkMove(c1, n1, "You must move one or two people!"))
				score += 2;
			if (checkMove(n(c1), n(n1), "You must move one or two people!"))
				score += 2;
			for (int i = 0; i < c1.length; i++) {
				c1[i] = 3;
				if (!checkMove(c1, n1, "All positions must be 1 or 2!"))
					allPos = false;
				c1[i] = 0;
				if (!checkMove(c1, n1, "All positions must be 1 or 2!"))
					allPos = false;
				c1[i] = 1;
			}
			if (allPos) {
				score += 5;
			}
			int[] c2 = { 1, 1, 1, 2, 2, 2, 1 },
					n2 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c2, n2, "You must move one or two people!"))
				score += 1;
			if (checkMove(n(c2), n(n2), "You must move one or two people!"))
				score += 1;
			int[] c2a = { 1, 2, 2, 1, 2, 2, 2 },
					n2a = { 1, 1, 1, 1, 1, 1 };
			if (checkMove(c2a, n2a, "You must move one or two people!"))
				score += 2;
			if (checkMove(n(c2a), n(n2a), "You must move one or two people!"))
				score += 2;
			int[] c3 = { 1, 1, 1, 2, 2, 2, 1 },
					n3 = { 2, 2, 2, 2, 2, 2 };
			if (checkMove(c3, n3, "You must move one or two people!"))
				score += 1;
			if (checkMove(n(c3), n(n3), "You must move one or two people!"))
				score += 1;

			int[] nw = {1, 1, 1, 2, 2, 2, 2};
			int[] nw1 = {2, 1, 1, 2, 2, 2};
			int[] nw2 = {1, 2, 1, 2, 2, 2};
			int[] nw3 = {1, 1, 2, 2, 2, 2};
			boolean notboat = true;
			if (!checkMove(nw, nw1, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw1), "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(nw, nw2, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw2), "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(nw, nw3, "You may not move a person who is not with the boat!"))
				notboat = false;
			if (!checkMove(n(nw), n(nw3), "You may not move a person who is not with the boat!"))
				notboat = false;
			
			if (notboat) score += 8;
			
			int[] ma = {2, 1, 1, 1, 2, 1};
			boolean mancruit = true;
			if (checkMove(c1, ma, "Manager A would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			if (checkMove(n(c1), n(ma), "Manager A would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			int[] ma2 = {1, 2, 1, 2, 1, 1};
			if (checkMove(c1, ma2, "Manager A would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			if (checkMove(n(c1), n(ma2), "Manager A would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			int[] pb = {1, 2, 1, 1, 2, 1, 1};
			int[] mb = {1, 2, 1, 2, 2, 1};
			if (checkMove(pb, mb, "Manager B would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			if (checkMove(n(pb), n(mb), "Manager B would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			int[] pc = {1, 1, 1, 2, 2, 2, 1};
			int[] mc = {1, 1, 2, 2, 2, 2};
			if (checkMove(pc, mc, "Manager C would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			if (checkMove(n(pc), n(mc), "Manager C would try to recruit someone!"))
				score += 2;
			else mancruit = false;
			if (mancruit) score += 2;
		} finally {
			System.out.println("Calculated score: " + score + " / 100");
			System.out.println("Tentative score! Academic dishonesty would affect your score.");
			System.out.println("If any problems are found with the tester, it will be announced and you should retest.");
		}

	}

}
