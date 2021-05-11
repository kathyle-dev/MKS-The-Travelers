//package com.escaperooms.joninexams;
//
//import com.escaperooms.application.EscapeRoomGame;
//import com.escaperooms.application.Playable;
//import com.escaperooms.application.Traveler;
//import com.escaperooms.application.User;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class JoninExams extends EscapeRoomGame {
//    Traveler traveler;
//    User user;
//    EscapeRoomGame escapeRoomGame;
//
//    public JoninExams () throws IOException {
//
//    }
//    @Override
//    public void run(Traveler traveler, EscapeRoomGame escapeRoomGame) {
//        this.escapeRoomGame = escapeRoomGame;
//        this.traveler = traveler;
//        this.user = traveler.getUser();
//        welcome();
//        battle();
//    }
//
//    Prompter input = new Prompter(new Scanner(System.in));
//    String opponent = null;
//
//    void welcome() {
//
//        Ninja fighter1 = new Ninja("Kakashi", 10);
//        Ninja fighter2 = new Ninja("Gaara", 10);
//        Ninja fighter3 = new Ninja("Killer B", 10);
//
//        ArrayList<String> villages = new ArrayList<String>();
//        villages.add("1: The Hidden Leaf Village");
//        villages.add("2: The Village Hidden in the Stones");
//        villages.add("3. The Village Hidden in the Clouds");
//
//        System.out.println("=====================================================================================");
//        System.out.println("Hello " + user.getName() + "...Wow I use to know a legendary Ninja by that name!!");
//        System.out.println("\nWelcome to the Jonin Exams, there will be some tough opponents today!");
//        System.out.println("\nOk here is a list of villages that can compete in the Jonin Exams today");
//
//        System.out.println("=====================================");
//        System.out.println(villages.get(0));
//        System.out.println(villages.get(1));
//        System.out.println(villages.get(2));
//        System.out.println("=====================================");
//        String home = input.prompt("What village are you from? 1-3");
//
//        while (!home.equals("1") && !home.equals("2") && !home.equals("3")) {
//            System.out.println("Sorry that village is not on the list! Options 1-3, see below.");
//            System.out.println("=====================================");
//            System.out.println(villages.get(0));
//            System.out.println(villages.get(1));
//            System.out.println(villages.get(2));
//            System.out.println("=====================================");
//            home = input.prompt("What village are you from? 1-3");
//        }
//            switch (home) {
//                case "1":
//                    System.out.println("Looks like you will be facing the Legendary..." + fighter1.getNinjaName());
//                    opponent = fighter1.getNinjaName();
//                    break;
//                case "2":
//                    System.out.println("Ok..." + user.getName() + " You will be fighting " + fighter2.getNinjaName());
//                    opponent = fighter2.getNinjaName();
//                    break;
//                case "3":
//                    System.out.println("You will be matched against " + fighter3.getNinjaName());
//                    opponent = fighter3.getNinjaName();
//                    break;
//            }
//
//        System.out.println("=================================================================");
//
//        System.out.println("Ok lets get started!!");
//        String ready = input.prompt(user.getName() + " Are you ready to battle? yes or no");
//
//        while (!ready.equals("yes") && !ready.equals("no")) {
//            System.out.println("\nPlease choose yes or no.");
//        }
//
//        switch (ready) {
//            case "yes":
//                System.out.println("\nOk step into the arena!");
//                break;
//            case "no":
//                System.out.println("\nWelp you better get ready! Get in there!");
//                break;
//        }
//    }
//
//
//    void battle() {
//        System.out.println("Ok now that both opponents are ready lets go!!!!!!");
//        System.out.println("\nOn my count 1.....2....3!!!!!");
//
//        if (opponent.equals("Kakashi")) {
//            System.out.println("\nLooks like " + opponent + " is preparing to use his Chidori attack");
//            String attack = input.prompt("How would you like to attack? \n" +
//                    " 1: Use a Genjutsu attack \n 2: Ninjutsu attack \n 3: Dodge ");
//
//            switch (attack) {
//                case "1":
//                    System.out.println("\nYou used your genjutsu and was able to defeat the opponent!!");
//                    System.out.println("\nWow that was fast!! ....and the winner is " + user.getName());
//                    user.addItem("leaf headband");
//                    break;
//                case "2":
//                    System.out.println("\nKakashi: He thinks he got me with that move lol...");
//                    System.out.println("\nYour opponent has defeated you before you could blink!!!");
//                    System.out.println("\nBetter luck next time!!!");
//                    break;
//                case "3":
//                    System.out.println("\n Kakashi: Haaa I'll see about that!");
//                    System.out.println("\nYou dodged the attack but it was a distraction and your opponent defeated you with a" +
//                            " with a blazing kick to the back of your head!");
//                    System.out.println("\nCall the medics this match is over!!");
//                    break;
//            }
//        } else if (opponent.equals("Gaara")) {
//            System.out.println(opponent + " throws a sand bullet fast!!");
//            String attack = input.prompt("How would you like to attack? \n" +
//                    " 1: Use a Genjutsu attack \n 2: Ninjutsu attack \n 3: Dodge ");
//
//            switch (attack) {
//                case "1":
//                    System.out.println("\nYou attempted to prepare a genjutsu attack but Gaara's sand bullet got to you too fast");
//                    System.out.println("\nWow it tore through your chest!");
//                    System.out.println("\nThe winner is Gaara!!!");
//                    break;
//                case "2":
//                    System.out.println("\nYou attacked Gaara with your flying leg attack while dodging his sand bullet!");
//                    System.out.println("\nGaara is out cold from that attack, you have won the match!!");
//                    user.addItem("leaf headband");
//                    break;
//                case "3":
//                    System.out.println("\n Gaara: Whaaaaat!!!! How are you moving so fast!!!!!!");
//                    System.out.println("\nGreat you dodged it!, then you used your speedy Ninjutsu attacks to defeat " + opponent);
//                    System.out.println("\nYou Won Congrats!!!");
//                    user.addItem("leaf headband");
//                    break;
//            }
//        } else {
//            System.out.println("Killer B starts rapping after the battle starts");
//            String attack = input.prompt("How would you like to attack? \n" +
//                    " 1: Use a Genjutsu attack \n 2: Ninjutsu attack \n 3: Join in and rap also");
//
//            switch (attack) {
//                case "1":
//                    System.out.println("\nYou used your genjutsu and was able to defeat the opponent!!");
//                    System.out.println("\nKiller B Genjutsu is too advanced! He broke out of your visual prowess and used his lightning sword attack to defeat you!"
//                            + " all while he was rapping!");
//                    System.out.println("You lose!!");
//                    break;
//                case "2":
//                    System.out.println("\nKiller B: Yo you trynna disrespect me while I'm spittin my raps!! Take This!!!!");
//                    System.out.println("\n he uses his 8 tails ability to defeat you quickly!!!");
//                    System.out.println("\nYou have a lost this match, try again next year!!");
//                    break;
//                case "3":
//                    System.out.println("\nYou joined Killer B and spit some of your own bars!");
//                    System.out.println("\nKiller B: Yoooo I like this kid those raps were all that!!! ");
//                    System.out.println("\nKiller B: yo I can't compete with that I gotta go think of some more raps!");
//                    System.out.println("\nB forfeits the match and you are the winner!!!");
//                    user.addItem("leaf headband");
//                    break;
//            }
//        }
//        jump();
//    }
//
//    @Override
//    public void terminate() {
//    }
//
//    @Override
//    public Playable playable() throws IOException {
//        return new Playable(getName(), "Welcome to Jonin Exams", new JoninExams());
//    }
//
//    void jump() {
//        traveler.menu();
//    }
//
//    @Override
//    public String getName() {
//        return "Jonin Exams";
//    }
//}
//
//
