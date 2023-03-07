package server.authentication;

import server.db.DBManager;

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
}