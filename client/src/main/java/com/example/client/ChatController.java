package com.example.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Контроллер окна чата.
 */
public class ChatController implements Initializable {

    /**
     * Область вывода сообщений.
     */
    @FXML
    public TextArea messageArea;

    /**
     * Поле ввода сообщения.
     */
    @FXML
    public TextField messageField;

    /**
     * Форма авторизации.
     */
    @FXML
    public HBox authForm;

    /**
     * Поле для ввода логина.
     */
    @FXML
    public TextField loginField;

    /**
     * Поле для ввода пароля.
     */
    @FXML
    public PasswordField passwordField;

    /**
     * Форма отправки сообщений.
     */
    @FXML
    public HBox messageForm;

    /**
     * Форма регистрации нового пользователя.
     */
    @FXML
    public HBox registerForm;

    /**
     * Поле для ввода логина при регистрации.
     */
    @FXML
    public TextField loginForRegistration;

    /**
     * Поле для ввода пароля при регистрации.
     */
    @FXML
    public PasswordField passwordForRegistration;

    /**
     * Поле для ввода никнейма при регистрации.
     */
    @FXML
    public TextField nicknameForRegistration;

    /**
     * Экземпляр доставщика сообщений с сервера.
     */
    private Messenger messenger;

    /**
     * Метод, автоматически вызываемый при старте приложения.
     * Отдаёт фокус полю ввода сообщения,
     * затем инициализирует экземпляр доставщика для работы с сервером.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> messageArea.requestFocus());
        messenger = new Messenger(this);
        setAuthenticated(false);
    }

    /**
     * Отправка введённого сообщения на сервер.
     */
    @FXML
    public void sendMessage() {
        String message = messageField.getText();

        // Если сообщение пустое, завершаем работу метода.
        if (message.isEmpty()) {
            return;
        }

        messageField.clear();
        try {
            messenger.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageField.requestFocus();
    }

    /**
     * Попытка авторизации.
     */
    @FXML
    public void tryToAuth() {
        String login = loginField.getText();
        String password = passwordField.getText();

        // Если логин или пароль не заполнены, выводим сообщение и завершаем работу метода.
        if (login.isEmpty() || password.isEmpty()) {
            addMessage("Пожалуйста, заполните оба поля.");
            return;
        }

        passwordField.clear();
        try {
            messenger.tryToAuthorise(login, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Изменение параметров окна в зависимости от состояния авторизации пользователя.
     * Авторизован:     отображаем форму отправки сообщения
     *                  скрываем форму авторизации
     *                  очищаем область вывода сообщений
     * Не авторизован:  скрываем форму отправки сообщения
     *                  отображаем форму авторизации
     *                  отображаем "Chat" в заголовке окна вместо ника
     *
     * @param authenticated состояние авторизации пользователя: true, если авторизован
     */
    public void setAuthenticated(boolean authenticated) {
        messageForm.setVisible(authenticated);
        messageForm.setManaged(authenticated);
        authForm.setVisible(!authenticated);
        authForm.setManaged(!authenticated);

        if (authenticated) {
            Platform.runLater(() -> messageArea.clear());
        } else {
            setWindowTitle("Chat");
        }
    }

    /**
     * Метод добавляет сообщение в область вывода сообщений.
     *
     * @param message добавляемое сообщение.
     */
    public void addMessage(String message) {
        messageArea.appendText(message + "\n");
    }

    /**
     * Установка заголовка окна приложения.
     *
     * @param title текст заголовка окна.
     */
    public void setWindowTitle(String title) {
        Platform.runLater(() -> ((Stage) messageArea.getScene().getWindow()).setTitle(title));
    }

    /**
     * Переинициализация доставщика сообщений.
     * Т.к. доставщик работает в отдельном потоке,
     * и уже отработавший поток мы не можем запустить заново,
     * то нам необходим новый доставщик для каждой новой
     * сессии подключения клиента к серверу.
     */
    public void resetMessenger() {
        messenger = new Messenger(this);
    }

    /**
     * Метод отображает форму регистрации и скрывает форму авторизации.
     */
    @FXML
    public void openRegisterForm() {
        registerForm.setVisible(true);
        registerForm.setManaged(true);
        authForm.setVisible(false);
        authForm.setManaged(false);
        Platform.runLater(() -> messageArea.requestFocus());
    }

    /**
     * Метод скрывает форму регистрации и отображает форму авторизации.
     */
    @FXML
    public void closeRegisterForm() {
        registerForm.setVisible(false);
        registerForm.setManaged(false);
        authForm.setVisible(true);
        authForm.setManaged(true);
    }

    /**
     * Попытка регистрации нового пользователя.
     */
    @FXML
    public void tryToRegister() {
        String login = loginForRegistration.getText();
        String password = passwordForRegistration.getText();
        String nickname = nicknameForRegistration.getText();

        // Если какое-то из полей не заполнено, выводим сообщение и завершаем работу метода.
        if (login.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
            addMessage("Пожалуйста, заполните все поля.");
            return;
        }

        try {
            messenger.tryToRegister(login, password, nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}