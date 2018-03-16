import java.util.Scanner;

public class Main{

    // Scanner & Int als Attribute
    public static void main(String[] args) {

        System.out.println("Hello");

        Scanner sc = new Scanner(System.in);
        int answer = -1;


        do{
            VocabTrainer currTraining = new VocabTrainer("Vokabeltrainer.csv");
            training(currTraining,methodOfTraining(sc,answer),String.valueOf(settingFilter(sc, answer)),numberOfWords(sc,answer,currTraining.vocabList.size()), sc);
        }while (playingAgain(sc,answer)==1);
    }

    // The training
    private static void training(VocabTrainer currTraining, int trainingMethod, String filter,  int wordNumber, Scanner sc){
        wordNumber=currTraining.filterVocabList(filter, wordNumber);
        int rightAnswers;
        if(trainingMethod==1)
            rightAnswers= currTraining.writeTraining(wordNumber, sc);
        else
            rightAnswers=currTraining.understandTraining(wordNumber, sc);
        calculateResult(rightAnswers, wordNumber);
    }

    // Calculate the result
    private static void calculateResult (int rightAnswers, int wordNumber){
        double resultInPercent =Math.round(rightAnswers*100.0/wordNumber);
        System.out.println("You have "+rightAnswers+" of "+wordNumber+" right. That are "+Math.round(resultInPercent)+"%");
        if(resultInPercent>=69)
            System.out.println("Congratulation!");
    }

    // Ask for the method of Training
    private static int methodOfTraining (Scanner sc, int answer){
        while(!(answer==1 || answer==2)) {
            System.out.println("Which spanish vocabs training would you like to do now?\n" +
                    " 1. Spanish writing training\n" +
                    " 2. Spanish understanding training");
            answer = sc.nextInt();
        }
        return answer;
    }

    // Ask for number of words
    private static int numberOfWords(Scanner sc, int answer, int sizeOfFile){
        while (!(answer>1)) {
            System.out.println("How many words would you like to train?");
            answer = sc.nextInt();
        }

        return answer;
    }

    // Ask for filter
    public static int settingFilter (Scanner sc, int answer){
        while (!(answer>=0 && answer <=3)) {
            System.out.println("Which filter would you like?\n" +
                    "0. All words   1. New words   2. Words for the first repeat   3. Words for the second repeat");
            answer = sc.nextInt();
        }
        return answer;
    }

    // Ask for playing again.
    private static int playingAgain(Scanner sc, int answer) {
        while (!(answer == 1 || answer == 2)) {
            System.out.println("\nDo you want to play again? (1. Yes 2. No)");
            answer = sc.nextInt();
        }
        return answer;
    }

}
