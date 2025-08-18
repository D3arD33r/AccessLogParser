import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        int countLines = 1;

        String path = new Scanner(System.in).nextLine();


        // создаём счётчики запросов
        long totalRequests = 0;
        long googlebotCount = 0;
        long yandexBotCount = 0;

        // Создаем объекты-константы для сравнения
        final String GOOGLEBOT = "GoogleBot";
        final String YANDEXBOT = "YandexBot";

        try  {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                totalRequests++;

                int length = line.length();// длинка строки

                // проверка ошибка в строке с символами >1024
                if (length > 1024) {
                    throw new RuntimeException("Ошибка в строке " + countLines + " длина строке = " + length);
                }


                // Извлечение содержимого первых скобок
                int start = line.indexOf('(');
                if (start <= 0) continue;
                int end = line.indexOf(')', start); // Ищем закрывающую скобку, начиная с позиции start
                if (end <= 0) continue;

                String firstBrackets = line.substring(start + 1, end);// Берём текст внутри скобок от первой позиции (  равной 0 = start + 1 символ  ДО последней позиции равной ) = end
                String[] parts = firstBrackets.split(";");// Делит строку на части по ';' по parts

                // Обработка User-Agent только при наличии второй части
                if (parts.length >= 2) { //  если частей больше 2 по parts
                    String fragment = parts[1].trim(); // Убирает пробелы: " Android" → "Android"
                    String botName = fragment.split("/")[0]; // Делит по '/' и берёт первый элемент

                    // Сравнение через hashCode() и equals()


                    if (botName.equalsIgnoreCase(GOOGLEBOT)) {
                        googlebotCount++;
                    }
                    else if (botName.equalsIgnoreCase(YANDEXBOT)) {
                        yandexBotCount++;
                    }
                }
            }
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }

        // Расчет и вывод результатов
        long totalBots = googlebotCount + yandexBotCount;
        double ratio =  (double) totalBots / totalRequests;

        System.out.println("Общее количество запросов: " + totalRequests);
        System.out.println("Запросов от Googlebot: " + googlebotCount);
        System.out.println("Запросов от YandexBot: " + yandexBotCount);
        System.out.printf("Доля запросов от ботов:" +  ratio);
    }
}