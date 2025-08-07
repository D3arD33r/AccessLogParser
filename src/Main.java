import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countLines = 1;

        System.out.print("Введите путь к файлу: ");

        while (true) {



            String path = new Scanner(System.in).nextLine();


            try { // начало большого цикла try-catch

                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;

                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                while ((line = reader.readLine()) != null) {

                    int length = line.length();// длинка строки

                    if(length >1024)
                    {throw new LongStringException("Ошибка в строке " +countLines +" длина строке = " + length);} //ошибка в строке с символами >1024


                    System.out.println("Длина строки: " + length);

                    countLines++;


                    if (length < min) { // проверки на минимально и масимальное значения
                        min = line.length();
                    } else if (length > max) {
                        max = line.length();
                    }


                }
                System.out.println("Количество строк: " + countLines);
                System.out.println("Минимальная длина строки: " + min);
                System.out.println("Масксимальная длина строки: " + max);

            }
            catch (FileNotFoundException  ex)
            {System.out.println("Файл не существует или это папка");
                ex.printStackTrace();
            }
            catch (IOException ex) {
                System.out.println("Ошибка чтения");
                ex.printStackTrace();


        } catch (LongStringException ex) {
               ex.printStackTrace();
            }
        }
    }
}