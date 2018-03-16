import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class VocabTrainer {
    List<String []> vocabList = new LinkedList<>();

    public VocabTrainer(String fileName) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                vocabList.add(line.split(csvSplitBy));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        vocabList.remove(0);
        Collections.shuffle(vocabList);
    }

    public int filterVocabList(String filter, int wordsNumber){
        if (filter.compareTo(String.valueOf(0)) > 0) {  // Filter 0 should not filter something. Therefor if filter is 0, the for-loop won´t be exit.
            for (int i = 0; i < vocabList.size(); i++) {
                if (!(vocabList.get(i)[0].compareTo(filter)== 0)) {
                    vocabList.remove(i);
                    i--;
                }
            }
        }
        if (vocabList.isEmpty()) {
            System.out.println("There are no words with this priority. You have to start the program new and choose another priority.");
            System.exit(1);
        }
        if(wordsNumber>vocabList.size()){
            wordsNumber=vocabList.size();
            System.out.println("The number of words is bigger than the number of words in the file. \n" +
                    "In a training session is provided no repeat. For the repeat of words play the game more times.\n" +
                    "The number of words will be "+wordsNumber+".\n");
        }
        return wordsNumber;
    }

    public int writeTraining(int numberOfWords, Scanner sc){
        int rightAnswers = 0;
        sc.nextLine();

        for(int i=0;i<numberOfWords;i++){

            System.out.println((i+1)+". What does \""+vocabList.get(i)[2]+"\"(\""+vocabList.get(i)[3]+"\") mean?" );
            String answer = sc.nextLine();

            if(vocabList.get(i)[1].compareTo(answer)==0){
                System.out.println("Right\n");
                rightAnswers++;
            }else
                System.out.println("That is wrong. \n\""+vocabList.get(i)[2]+"\" means \n\""+vocabList.get(i)[1]+"\"\n");
        }
        return rightAnswers;
    }

    public int understandTraining(int numberOfWords, Scanner sc){
        int rightAnswers=0;
        sc.nextLine();

        for(int i=0;i<numberOfWords;i++){

            System.out.println((i+1)+". Que significa \""+vocabList.get(i)[1]+"\" en aleman?" );
            String answer = sc.nextLine();

            if(vocabList.get(i)[3].compareTo(answer)==0){
                System.out.println("Correcto\n");
                rightAnswers++;
            }else
                System.out.println("Es falso. \n\""+vocabList.get(i)[1]+"\" significa \n\""+vocabList.get(i)[3]+"\"\n");
        }
        return rightAnswers;
    }

}
