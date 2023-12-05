import java.util.Arrays;
import java.util.Random;

public class Questions {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(randomizer(25)));

	}
	
	public static int[] randomizer(int numQuestions) {
		Random rando = new Random();
		int[] r = new int[numQuestions];
		for (int i = 0; i < r.length; i++) {
	        r[i] = i;
	    }
	    

        // randomize order of values
        for(int i = 0; i < r.length; ++i)
        {
             // select a random index
             int index = rando.nextInt(numQuestions-1);

             // swap values
             int value = r[index];
             r[index] = r[i];
             r[i] = value;
        }
        return r;
	}
	
	public static boolean check(int[] r) {
		for(int left = 0; left < r.length; left++) {
			//each time left hand points to a new index, right hand loops through array to compare
			for(int right = left + 1; right < r.length; right++) {
				if(r[left] == r[right]) {
					//for(int i = 0; i < r.length; i++) {
					//	r[i] = rando.nextInt(numQuestions);
					return false;
				}
			}
		}
		return true;
	}


}
