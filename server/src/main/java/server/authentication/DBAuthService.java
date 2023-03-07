package server.authentication;

import server.db.DBManager;
import server.exceptions.RegistrationFailedException;

/**
 * Сервис авторизации.
 * В своей работе использует DBManager для соединения с базой данных.
 */
public class DBAuthService implements AuthService {

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
        return DBManager.getNickname(login, password);
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
        DBManager.addNewUser(login, password, nickname);
    }
}