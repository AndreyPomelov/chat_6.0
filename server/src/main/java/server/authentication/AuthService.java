package server.authentication;

import server.exceptions.RegistrationFailedException;

/**
 * Сервис авторизации.
 */
public interface AuthService {

    /**
     * Получить никнейм пользователя по его логину и паролю.
     *
     * @param login     логин.
     * @param password  пароль.
     * @return          никнейм, если авторизация успешна.
     *                  null, если авторизация неуспешна (ошибка в логине или пароле).
     */
    String getNickname(String login, String password);

    /**
     * Зарегистрировать нового пользователя.
     *
     * @param login                         логин.
     * @param password                      пароль.
     * @param nickname                      никнейм.
     * @throws RegistrationFailedException  ошибка регистрации нового пользователя.
     */
    void registerNewUser(String login, String password, String nickname) throws RegistrationFailedException;
}