import java.io.*;

public class MainTest {
    public static void main(String args[]) throws IOException
    {
        if (args.length != 1) {
            System.err.println("Usage: java MainInt input-file");
            System.exit(0);
        }
        TextInputStream ifs = new TextInputStream(args[0]);

        while(ifs.ready()) {
            String opnd1 = ifs.readWord();
            String op = ifs.readWord();
            String opnd2 = ifs.readWord();
            String equal = ifs.readWord();
            String resultValue = ifs.readWord();

            LongInt long1 = new LongInt(opnd1);
            LongInt long2 = new LongInt(opnd2);
            LongInt long3 = null;
            LongInt long4 = null;

            switch(op.charAt(0)) {
                case '+':
                    long3 = long1.add(long2); break;
                case '-':
                    long3 = long1.subtract(long2); break;
                case '*':
                    long3 = long1.multiply(long2); break;
            }

            long4 = long3.subtract(new LongInt(resultValue));

            System.out.print("your result : ");long3.print(); System.out.println();
            System.out.print("expected : ");System.out.print(resultValue);System.out.println();

            System.out.print("isCorrect? :  ");
            System.out.println(long4.numString.equals("0"));
            System.out.println("-------------------------------------------------------");
        }
    }
}
