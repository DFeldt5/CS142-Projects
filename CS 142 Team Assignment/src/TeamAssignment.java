import java.util.Random;
import java.util.Scanner;

/*
 * Team QWERTY:
 * Dustin Feldt
 * Jennifer Hayward-Nandra
 * Davis Mutegaya Mugisha
 * Ethan Njuguna
 * 
 * This program is an element-based personality test which asks the user a predetermined
 * number of multiple-choice questions pulled randomly from a larger list, then presents them with their
 * element type based on their responses.
 */

public class TeamAssignment {

	public static void main(String[] args) {
		//scanner to read the user's input
		Scanner s = new Scanner(System.in);
		
		//creates a variable to represent the total number of questions, then creates a string array of that length
		//and calls the database method to fill it with the actual questions
		int numQuestions = 31;
		String[] questions = new String[numQuestions];
		questions = database(questions);
		
		int[] randQs = randomizer(numQuestions); //creates array of random ints sized at the total number of questions
		int qsToAsk = 15; //initial number of questions to ask
		int[] elements = new int[4]; //array to store results for each answer type
//		fire = 0
//		water = 1
//		earth = 2
//		air = 3
		
		//introduction, asks the user's name
		System.out.println("Welcome to the QWERTY element quiz! What's your name? ");
		String name = s.nextLine();
		System.out.println("Hello " + name + "! We're going to ask you a few questions, then tell you your elemental personality type. Ready? Let's go!");
		
		//loop to ask the initial number of questions
		for(int asked = 0; asked < qsToAsk; asked++) {
			userPrompt(s, questions, randQs, asked, elements);
			
		}
		
		//loop asks further questions if there is a tie between element values
		int extraQs = qsToAsk + 1;
		while(check(elements) == false) {
			userPrompt(s, questions, randQs, extraQs, elements);
			extraQs++;
		}
		System.out.println("\n" + name + finalResult(elements));
	}
	
	//pulls a question from the array, displays it as a prompt, and adds the response (if valid) to the appropriate element index
	public static void userPrompt(Scanner s, String[] questions, int[] randQs, int asked, int[] elements) {
		int input = 0;
		while(true) {
			System.out.println("\n" + questions[randQs[asked]]);
			if(s.hasNextInt()) {
				input = s.nextInt();
				s.nextLine();
				if(input >= 1 && input <= 4) {
					break;
				}
				else {
					System.out.println("You have to pick a number 1 - 4!");
					continue;
				}
			}
			s.nextLine();
			System.out.println("You have to pick a number 1 - 4!");
		}
		if(input == 1){
			elements[0]++;
		}
		else if(input == 2){
			elements[1]++;
		}
		else if(input == 3){
			elements[2]++;
		}
		else{
			elements[3]++;
		}
	}
	
	//initializes a random number generator and an integer array, fills the array sequentially from 0,
	//then swaps each index with a random index value to create a randomized array
	public static int[] randomizer(int numQuestions) {
		Random rando = new Random();
		int[] r = new int[numQuestions];
		for (int i = 0; i < r.length; i++) {
	        r[i] = i;
	    }
	    
        for(int i = 0; i < r.length; ++i){
             int newIndex = rando.nextInt(numQuestions-1);
             int contains = r[newIndex];
             r[newIndex] = r[i];
             r[i] = contains;
        }
        return r;
	}
	
	//finds the highest value in the elements array and returns false if it has a "tie" in another index
	//if not, returns true
	public static boolean check(int[] elements) {
		int highest = elements[0];
		for(int i = 0; i < elements.length; i++) {
			if(elements[i] > highest) {
				highest = elements[i];				
			}
		}
		for(int left = 0; left < elements.length; left++) {
			for(int right = left + 1; right < elements.length; right++) {
				if(elements[left] == elements[right] && elements[left] == highest) {
					return false;
				}
			}
		}
		return true;
	}
	
	//finds the highest value in the elements array and returns the matching index from the personalities array
	public static String finalResult(int[] a) {
		int highest = a[0];
		int index = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > highest) {
				highest = a[i];
				index = i;
				
			}
		}
		String[] personalities = {
			", your element is Fire: you have a strong, fiery personality and are often emotional or hot-headed.",
			", your element is Earth: you are calm, grounded and have a strong maternal, protective instinct.",
			", your element is Water: you are fluid and laid back, often more balanced, and excel at helping others.",
			", your element is Air: you are a rational free-thinker who can see the big picture more clearly than the other element types."
		};
		return personalities[index];
		
	}

	//fills the questions array with question strings, then returns the array
	public static String[] database(String[] questions) {
		
		questions[0] = "What is your favorite color?"+"\n"
				+ "1) Red		2) Green" + "\n"
				+ "3) Blue		4) Black";
		
		questions[1] = "What is your favorite eating utensil?"+"\n"
				+ "1) Fork		2) Knife" + "\n"
				+ "3) Spoon		4) Spork";
		
		questions[2] = "What animal would you rather have as a pet?"+"\n"
				+ "1) Dog		2) Cat" + "\n"
				+ "3) Huge tarantula		4) Parrot";
		
		questions[3] = "How do you like to spend a Friday night?"+"\n"
				+ "1) Vigilante crimefighting		2) Partying with friends" + "\n"
				+ "3) At home with a good book		4) Alone, eating your feelings";
		
		questions[4] = "Stop:" + "\n"
				+ "1) Hammer time		2) Collaborate and listen" + "\n"
				+ "3) Drop and roll		4) In the name of love";
				
		questions[5] = "What is your preferred mode of travel?" + "\n"
				+ "1) Bus		2) Hot air balloon" + "\n"
				+ "3) Train		4) Car";

		questions[6] = "Which emotion do you think is hardest for you to control?" + "\n"
				+ "1) Anger		2) Love" + "\n"
				+ "3) Curiosity		4) Pride";

		questions[7] = "When you're in an argument, how do you usually proceed?" + "\n"
				+ "1) I already have a point of view made on almost any subject and I rarely find a motive to change it.		2) I change my views quite often, depending on how good the arguments are." + "\n"
				+ "3) With great care, I need to weigh every statement before I make a counter-argument.		4) Just say whatever I'm feeling at the moment, if they don't agree, I'll make them agree.";

		questions[8] = "Your greatest strength is..." + "\n"
				+ "1) Courage and zeal		2) Reliability and conscientiousness" + "\n"
				+ "3) Intelligence and independence 		4) Compassion and honesty";

		questions[9] = "Your biggest weakness is..." + "\n"
				+ "1) Impulsive		2) Illogical" + "\n"
				+ "3) Idealistic 		4) Stubborn";

		questions[10] = "People describe you as..." + "\n"
				+ "1) A leader		2) Gentle and complex" + "\n"
				+ "3) Down to Earth 		4) A free spirit";

		questions[11] = "You’re at a house party. None of your friends have shown up. You..." + "\n"
				+ "1) Find the host and ask him/her to show you around. 		2) Look around, some other people seem to be in the same situation, maybe we can make our own group." + "\n"
				+ "3) Stand and wait for your friends to show up. 		4) Walk up to the most popular person at the party and spark up a conversation";

		questions[12] = "You're involved in a minor fender-bender. The other driver gets out quite mad even though it's his fault. You..." + "\n"
				+ "1) Threaten him before he manages to utter a single word.		2)Ahh... Forget it, it's just a small scratch, it will probably come out with a wash." + "\n"
				+ "3) You're not to blame, no matter what he says or does. 		4) Listen carefully to his threats, then point out just how many mistakes he has made.";

		questions[13] = "You're with your partner at the movies. The rather large guy behind you starts shouting and annoying everyone. What do you do?" + "\n"
				+ "1) Turn around and tell him, very politely, to shut up or move out! 		2) I don't care that much, he'll tire himself out eventually." + "\n"
				+ "3) Ask him to please stop talking, if he does not agree get up and leave. 		4) I sarcastically turn every comment he makes back at him, hoping he gets the hint and shuts up.";

		questions[14] = "It's the weekend, what are you doing?" + "\n"
				+ "1) Exercising, just venting some frustration!		2) In a comfort chair or a garden relaxing my mind!" + "\n"
				+ "3) Curled up in bed with a good book! 	4) Out with my friends, it's our night on the town!";

		questions[15] = "You finally arranged a meeting for an important project. The main board member is running late but the secondary ones are there. Do you...?" + "\n"
				+ "1) Start the meeting on time, without changing the presentation		2) Try to wait a little more, then start with an altered presentation." + "\n"
				+ "3) Cancel the meeting.		4) Recognize publicly the absence of one member and change the topic to a less important one.";

		questions[16] = "In your groups of friends there is a new person that is doing everything to get you angry. What do you do?" + "\n"
				+ "1) Confront or attack him physically or verbally until he backs off. 		2)He's just jealous of me, he’ll come around once he gets to know me." + "\n"
				+ "3) Avoid him, he's too annoying.		4) Take his comments and make jokes out of them. If my friends are having fun, why spoil it?";

		questions[17] = "When was the last time you went to a party?" + "\n"
				+ "1) Last month		2) Six months ago" + "\n"
				+ "3) Last year		4) Last week";

		questions[18] = "A perfect day would be..." + "\n"
				+ "1) Traveling, doing sport or going on an adventure		2) Gardening, sitting in nature or working on a hobby" + "\n"
				+ "3) Relaxing alone, meditating or doing artwork		4) Watching a movie, reading or spending time with friends";

		questions[19] = "When you were young, you often dreamed of..." + "\n"
				+ "1) Making lots of money 		2) Finding a husband/wife" + "\n"
				+ "3) Traveling the world 		4) Being famous";

		questions[20] = "What is your favorite season?" + "\n"
				+ "1) Summer 		2) Winter" + "\n"
				+ "3) Autumn		4) Spring";

		questions[21] = "When making decisions, do you..." + "\n"
				+ "1) Trust your instincts 		2) Listen to your heart" + "\n"
				+ "3) Use your head		4) Flip a coin";

		questions[22] = "What's most important to you in a relationship?" + "\n"
				+ "1) Passion 		2) Loyalty" + "\n"
				+ "3) Sensitivity		4) Freedom";

		questions[23] = "What is your ideal career?" + "\n"
				+ "1) Entrepreneur 		2) Lumberjack" + "\n"
				+ "3) Nurturing and healing others		4) Building something you're proud of";

		questions[24] = "You're going on a road trip; what is your ideal destination?" + "\n"
				+ "1) Area 51 		2) The beach" + "\n"
				+ "3) The mountains		4) A new city";

		questions[25] = "Which archetype best describes you?" + "\n"
				+ "1) The rebel 		2) The artist" + "\n"
				+ "3) The explorer		4) The rogue";

		questions[26] = "If you could choose a martial art to practice, which would it be?" + "\n"
				+ "1) Muay Thai 		2) Karate" + "\n"
				+ "3) Tai Chi		4) Ninjutsu";

		questions[27] = "You're a general leading an army against a numerically superior foe. What is your command?" + "\n"
				+ "1) Attack head on 		2) Make a stand in a fortified position" + "\n"
				+ "3) Lure them into an ambush		4) Retreat";

		questions[28] = "What is your favorite genre to read?" + "\n"
				+ "1) Romance 		2) Nonfiction" + "\n"
				+ "3) Internet fanfic		4) Fantasy";

		questions[29] = "If you were an artist, what would you use to express yourself?" + "\n"
				+ "1) Performing arts 		2) Written word" + "\n"
				+ "3) Crafts		4) Taxidermy";

		questions[30] = "Which is the superior sandwich?" + "\n"
				+ "1) Grilled cheese 		2) Reuben" + "\n"
				+ "3) PB & J		4) Club";

		
		return questions;
	}
}
