
import java.util.Random;
import java.util.Scanner;
public class NumberGame {
// Generate a random number within a specified range, such as 1 to 100.
    int randomNo;
    NumberGame(){
    Random rndm = new Random();
    randomNo = rndm.nextInt(100);
    System.out.println("\nWelcome to the Number Guessing Game!");
    }

public int RandomNo(){
    return randomNo;
}

// Prompt the user to enter their guess for the generated number.
    static int userInput(){
        int number;
        System.out.println("\nEnter any number between 1 to 100");
        Scanner sc=new Scanner(System.in);
        number=sc.nextInt();
        return number;
    }   
// Compare the user's guess with the generated number and provide feedback on whether the guess is correct, too high, or too low.   
    static void isCorrect(int rndm,int number){
        if(rndm==number){
            System.out.println("You've guessed the correct number!");
        }
        else if(rndm>number){
            System.out.println("Your entered number is too low");
        }
        else{
            System.out.println("Your entered number is too high");
        }
    }

    public static void main(String[]args){
        int number=0, randomNo=0, count=0;
        NumberGame g= new NumberGame();
        do{
            randomNo=g.RandomNo();
            number=userInput();
            isCorrect(randomNo, number);
            count++;
        }while(number!=randomNo);
// Limited the number of attempts to 5 the user has to guess the number.
        if(count==5){
            System.out.println("You've exceeded the number of steps \n BETTER LUCK next time!");
        }
        System.out.println("\n   You took "+count+" steps");
    }
}


