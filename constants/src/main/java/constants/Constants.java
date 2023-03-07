package constants;

/**
 * Глобальные константы
 *
 * @version 6.0
 * @author Andrey Pomelov
 */
public interface Constants {

    /**
     * IP-адрес, на котором работает сервер
     */
    String IP_ADDRESS = "localhost";

    /**
     * порт, на котором работает сервер
     */
    int PORT = 7777;

    /**
     * Адрес базы данных.
     */
    String DB_ADDRESS = "jdbc:mysql://localhost:3306/chat?user=root&password=mysql77777";
}