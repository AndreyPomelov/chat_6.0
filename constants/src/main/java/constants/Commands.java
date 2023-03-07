package constants;

/**
 * Служебные команды для взаимодействия серверной и клиентской части
 *
 * @version 6.0
 * @author Andrey Pomelov
 */
public interface Commands {

    /**
     * Отключение от сервера
     */
    String EXIT = "/exit";

    /**
     * Запрос авторизации.
     */
    String AUTH = "/auth";

    /**
     * Авторизация успешна.
     */
    String AUTH_OK = "/auth_ok";

    /**
     * Авторизация неуспешна.
     */
    String AUTH_DENIED = "/auth_denied";

    /**
     * Запрос регистрации нового пользователя.
     */
    String REG = "/reg";

    /**
     * Регистрация успешна.
     */
    String REG_OK = "/reg_ok";

    /**
     * Регистрация неуспешна.
     */
    String REG_FAIL = "/reg_fail";
}