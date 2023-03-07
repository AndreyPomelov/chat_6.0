package server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static constants.Constants.DB_ADDRESS;

/**
 * Менеджер базы данных.
 */
public class DBManager {

    /**
     * Экземпляр, который служит для отправки запросов в базу данных.
     */
    private static Statement statement;

    /**
     * Наименование колонки, содержащей имя пользователя в базе данных.
     */
    private static final String NICKNAME = "nickname";

    static {
        try {
            // Создаём подключение к базе данных.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_ADDRESS);
            statement = connection.createStatement();

            // Создаём таблицу в базе данных, содержащую информацию о пользователях.
            statement.execute("CREATE TABLE IF NOT EXISTS `user` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT, " +
                    "`login` VARCHAR(45) NOT NULL, " +
                    "`password` VARCHAR(45) NOT NULL, " +
                    "`nickname` VARCHAR(45) NOT NULL, " +
                    "PRIMARY KEY (`id`), " +
                    "UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE, " +
                    "UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE);");

            // Наполняем таблицу тестовыми данными.
            statement.execute("INSERT INTO `user` (`id`, `login`, `password`, `nickname`) VALUES ('1', 'qwe', 'rty', 'Fry');");
            statement.execute("INSERT INTO `user` (`id`, `login`, `password`, `nickname`) VALUES ('2', 'asd', 'fgh', 'Leela');");
            statement.execute("INSERT INTO `user` (`id`, `login`, `password`, `nickname`) VALUES ('3', 'zxc', 'vbn', 'Bender');");
        } catch (Exception e) {
            // Игнорируем ошибку повторного создания тестовых данных.
        }
    }

    /**
     * Получить никнейм пользователя по его логину и паролю.
     *
     * @param login     логин.
     * @param password  пароль.
     * @return          никнейм, если авторизация успешна.
     *                  null, если авторизация неуспешна (ошибка в логине или пароле).
     */
    public static String getNickname(String login, String password) {
        String nickname = null;

        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT nickname FROM user " +
                    "WHERE login = '%s' AND password = '%s';", login, password));

            // Если результат не пустой, возвращаем имя пользователя.
            while (resultSet.next()) {
                return resultSet.getString(NICKNAME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Если результатов в базе данных не нашлось, это означает,
        // что пользователь ввёл несуществующий логин или ошибся в пароле.
        // В таком случае возвращаем null.
        return nickname;
    }
}