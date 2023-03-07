package server.exceptions;

/**
 * Ошибка регистрации нового пользователя.
 */
public class RegistrationFailedException extends Exception {

    /**
     * Конструктор.
     *
     * @param message текст сообщения об ошибке.
     */
    public RegistrationFailedException(String message) {
        super(message);
    }
}