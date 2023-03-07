package server.authentication;

import server.exceptions.RegistrationFailedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Простой сервис авторизации, имитирующий получение никнейма пользователя из базы данных.
 * Реального подключения к базе данных не происходит, все данные пользователей хранятся
 * прямо внутри экземпляра данного класса.
 */
public class SimpleAuthService implements AuthService {

    /**
     * Список данных пользователей (имитация базы данных).
     */
    private final List<UserData> users = new ArrayList<>();

    /**
     * Конструктор.
     * При создании экземпляра наполняем нашу "базу данных" тестовыми данными.
     */
    public SimpleAuthService() {
        users.add(new UserData("qwe", "rty", "Fry"));
        users.add(new UserData("asd", "fgh", "Leela"));
        users.add(new UserData("zxc", "vbn", "Bender"));
    }

    /**
     * Получить никнейм пользователя по его логину и паролю.
     *
     * @param login     логин.
     * @param password  пароль.
     * @return          никнейм, если авторизация успешна.
     *                  null, если авторизация неуспешна (ошибка в логине или пароле).
     */
    @Override
    public String getNickname(String login, String password) {
        for (UserData user : users) {
            if (user.login.equals(login) && user.password.equals(password)) {
                // Если обнаружено совпадение логина и пароля, возвращаем никнейм.
                return user.nickname;
            }
        }
        // Если совпадений не обнаружено, возвращаем null.
        // Это говорит о том, что авторизация неуспешна.
        return null;
    }

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param login                         логин.
     * @param password                      пароль.
     * @param nickname                      никнейм.
     * @throws RegistrationFailedException  ошибка регистрации нового пользователя.
     */
    @Override
    public void registerNewUser(String login, String password, String nickname) throws RegistrationFailedException {
        UserData user = new UserData(login, password, nickname);

        // Если такой логин или никнейм уже существуют, выбрасываем исключение.
        if (isLoginOrNicknameExists(user)) {
            throw new RegistrationFailedException("Такой логин или никнейм уже существует.");
        }

        // Добавляем нового пользователя к списку пользователей.
        users.add(user);
    }

    /**
     * Метод проверяет, существует ли уже такой логин или никнейм.
     *
     * @param user  данные пользователя.
     * @return      true, если логин или никнейм уже существует.
     */
    private boolean isLoginOrNicknameExists(UserData user) {
        for (UserData currentUser : users) {
            if (currentUser.login.equals(user.login) || currentUser.nickname.equals(user.nickname)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Данные пользователя.
     */
    private static class UserData {

        /**
         * Логин.
         */
        private final String login;

        /**
         * Пароль.
         */
        private final String password;

        /**
         * Никнейм.
         */
        private final String nickname;

        /**
         * Конструктор.
         *
         * @param login     логин.
         * @param password  пароль.
         * @param nickname  никнейм.
         */
        public UserData(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }
}