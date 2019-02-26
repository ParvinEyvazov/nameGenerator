import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    //con = consonants
    //vow = vowels
    public static char[] con = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z', 'w', 'y'};
    public static char[] vow = {'a', 'e', 'u', 'i', 'o'};

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        System.out.println("1-NickName how many you want" +
                         "\n2-NickName one-by-one"+
                         "\n3-Modified NickName");
        int choose = myScan.nextInt();

        switch (choose) {
            case 1 : //nickname how many you want
            {
                System.out.println("Please insert the number of nickNames you want :");
                int numberOfWords = myScan.nextInt();

                //generating words for how many user wanted
                for (int i = 0; i < numberOfWords; i++) {
                    System.out.println(nickNameGenerate());
                }
            break;
            }

            case 2 :    //nickname one-by-one
            {
                int number = 0;
                System.out.println("Please press any key and press enter for continuing ," + "\nIf you want to exit write exit and press enter");
                while (number == 0) {
                    String consoleString = myScan.next();
                    if (consoleString.equals("exit") || consoleString.equals("Exit") || consoleString.equals("EXIT")) {
                        number++;
                        continue;
                    } else {
                        System.out.println(nickNameGenerate());
                    }

                }

                break;
            }
            case 3 : //modified NickName
            {
                System.out.println("Write the lenght of nickName how many you want : ");
                int userWantLenght = myScan.nextInt();
                System.out.println("Write the number of words you want  :");
                int userWantedWordNo = myScan.nextInt();
                for (int i=0;i<userWantedWordNo;i++){
                    System.out.println(nickNameGenerate(userWantLenght));
                }



            }

        }



    }

    //nickName generate randomly lenght
    private static String nickNameGenerate() {
        String str = "";
        int startNum = 1 + ((int) (Math.random() * 2));
        int zero = 0;
        int endNumber = 3 + ((int) (Math.random() * 3));

        //First randomly detect that it start with vowel or con
        if (startNum % 2 == 1) { //start with con
            str = addCon(str);
            str = addVow(str);
        } else {              //start with vow
            str = addVow(str);
            str = addCon(str);
        }

        //after first 2 letter,adding another letters in randomly number
        while (zero < endNumber) {
            str = addLetter(str);
            zero++;
        }
        if (haveNot2Double(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        return nickNameGenerate();
    }

    //nickName generate what user want lenght
    private static String nickNameGenerate(int no) {
        String str = "";
        int startNum = 1 + ((int) (Math.random() * 2));
        int zero = 0;

        //First randomly detect that it start with vowel or con
        if (startNum % 2 == 1) { //start with con
            str = addCon(str);
            str = addVow(str);
        } else {              //start with vow
            str = addVow(str);
            str = addCon(str);
        }

        //after first 2 letter,adding another letters in userWanted number
        //subtract 2 for first 2 letters
        while (zero < no-2) {
            str = addLetter(str);
            zero++;
        }
        if (haveNot2Double(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        return nickNameGenerate(no);
    }

    //check if last is consonant
    private static boolean isCon(String str) { //if last char is con

        if (!(new String(con).indexOf(str.substring(str.length() - 1)) == -1)) {
            return true;

        } else return false;

    }

    //check if last 2 is consonant
    private static boolean isDoubleCon(String str) { //if last double char is con
        if (!(new String(con).indexOf(str.substring(str.length() - 2, str.length() - 1)) == -1)) {
            if (isCon(str)) {
                return true;
            } else return false;

        } else return false;

    }

    //check if last is vowel
    private static boolean isVow(String str) {
        if (!(new String(vow).indexOf(str.substring(str.length() - 1)) == -1)) {
            return true;

        } else return false;


    }

    //check if last 2 is vowel
    private static boolean isDoubleVow(String str) {
        if (!(new String(vow).indexOf(str.substring(str.length() - 2, str.length() - 1)) == -1)) {
            if (isVow(str)) {
                return true;
            } else return false;

        } else return false;


    }

    //adding consonant
    private static String addCon(String strComing) {
        StringBuilder str = new StringBuilder(strComing);
        int randomNum = (int) (Math.random() * 21);
        str.append(con[randomNum]);

        return str.toString();

    }

    //adding vowels
    private static String addVow(String strComing) {
        StringBuilder str = new StringBuilder(strComing);
        int randomNum = (int) (Math.random() * 5);
        str.append(vow[randomNum]);

        return str.toString();

    }

    //adding letters for statisticly
    private static String addLetter(String strComing) {
        if (isDoubleCon(strComing)) { // if last 2 is con add vowel
            String returnStr = addVow(strComing);
            return returnStr;
        }
        if (isDoubleVow(strComing)) { //if last2 is vowel add con
            return addCon(strComing);
        }

        // 20% adding same(vowel and vowel)   80% adding different(vowel and con)
        int randomNum = (int) (Math.random() * 10);
        if (randomNum > 7) {  //20% adding same thing
            if (isCon(strComing)) {
                return addCon(strComing);
            } else return addVow(strComing);
        } else {  //80% adding different thing
            if (isCon(strComing)) {
                return addVow(strComing);
            } else return addCon(strComing);
        }


    }

    //if there are at least two double vow or con
    private static boolean haveNot2Double(String str) {
        boolean check = true;

        if (str.length() >= 4) {
            for (int i = 0; i < str.length() - 3; i++) {
                if (str.substring(i, i + 1).equals(str.substring(i + 1, i + 2))
                        && str.substring(i + 2, i + 3).equals(str.substring(i + 3, i + 4))) {
                    check = false;
                }
            }
        }
        return check;
    }


}



