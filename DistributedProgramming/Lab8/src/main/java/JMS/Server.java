package JMS;
import com.ibm.mq.*;
public class Server {
    private MQQueue Q1 = null; // Черга запитів
    private MQQueue Q2 = null; // Черга відповідей

    // Запуск сервера
    public void start(String QMName, String IP, int port, String channel,
                      String Q1_name, String Q2_name)
            throws MQException {
// Настроюю середовище
        MQEnvironment.hostname = IP;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
// Встановлюю з'єднання з менеджером
// Менеджер черг
        MQQueueManager QM = new MQQueueManager(QMName);
// Відкриваю чергу запитів на читання
        Q1 = QM.accessQueue(Q1_name, MQC.MQOO_INPUT_EXCLUSIVE);
// Відкриваю чергу відповідей на запис
        Q2 = QM.accessQueue(Q2_name, MQC.MQOO_OUTPUT);
// Обробляю всі запити
        int i = 0;
        while (processQuery()) i++;
// Завершую роботу
        Q1.close();
        Q2.close();
        QM.disconnect();
        System.out.println("Опрацьовано" + i + "Запитів");
    }

    // Обробка повідомлення-запиту
    public boolean processQuery() {
        try {
// Налаштовую інтервал очікування 3 сек.
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options = MQC.MQGMO_WAIT;
            gmo.waitInterval = 3000;
// Читаю повідомлення з черги запитів
            MQMessage query = new MQMessage();
            Q1.get(query, gmo);
// Обробляю повідомлення
            int oper = query.readInt(); // Операція
            int v1 = query.readInt(); // Число1
            int v2 = query.readInt(); // Число2
// Рахую результат
            int result; // Результат операції
            result = (oper == 0) ? (v1 + v2) : (v1 - v2);

// Формую відповідь
            MQMessage response = new MQMessage();
            response.writeInt(oper);
            response.writeInt(v1);
            response.writeInt(v2);
            response.writeInt(result);
// Відправляю відповідь
            Q2.put(response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) throws MQException {
        (new Server()).start("QM1", "localhost", 1414,
                "SYSTEM.DEF.SVRCONN", "SRV.Q", "CL.Q");
    }
}