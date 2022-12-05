package JMS;

import com.ibm.mq.*;
import java.io.IOException;
public class Client {
    private MQQueueManager QM; // Менеджер
    private MQQueue Q1; // Черга запитів
    private MQQueue Q2; // Черга відповідей
    // конструктор
    public Client(String QMName, String IP, int port, String channel,
                  String Q1_name, String Q2_name) throws MQException {
// Настроюю середовище
        MQEnvironment.hostname = IP;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
// Встановлюю з'єднання з менеджером
        QM = new MQQueueManager(QMName);
// Відкриваю чергу запитів на запис
        Q1 = QM.accessQueue(Q1_name, MQC.MQOO_OUTPUT);
// Відкриваю чергу відповідей на читання
        Q2 = QM.accessQueue(Q2_name, MQC.MQOO_INPUT_EXCLUSIVE);
    }
    // відправити запит серверу
    private void sendQuery(int operation, int value1, int value2) throws
            IOException, MQException {
// Створюю повідомлення-запит
        MQMessage query = new MQMessage();
        query.writeInt(operation); // Операція
        query.writeInt(value1); // Число1
        query.writeInt(value2); // Число2
// Записую в чергу запитів
        Q1.put(query);
    }
    // порахувати суму чисел
    public void sum(int value1, int value2) throws IOException,
            MQException {
        sendQuery(0, value1, value2);

    }
    // порахувати різницю чисел
    public void sub(int value1, int value2) throws IOException,
            MQException {
        sendQuery(1, value1, value2);
    }
    // Отримати відповідь від сервера
    public boolean printResult() {
        try {
// Читаю повідомлення з черги відповідей
            MQMessage response = new MQMessage();
            Q2.get(response);
            int oper = response.readInt(); // Операція
            int v1 = response.readInt(); // Число1
            int v2 = response.readInt(); // Число2
            int res = response.readInt(); // Результат
            String s = (oper == 0) ? "+" : "-";
            System.out.println(v1 + s + v2 + "=" + res);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // від'єднатися
    public void disconnect()
            throws MQException {
        Q1.close();
        Q2.close();
        QM.disconnect();
    }
    // ПЕРШИЙ ЗАПУСК КЛІЄНТА
    public static void main1() {
        try {
            Client client =
                    new Client("QM1", "localhost", 1414,
                            "SYSTEM.DEF.SVRCONN", "SRV.Q", "CL.Q");
            client.sum(15, 20);
            client.sub(30, 38);
            client.sum(100, 200);
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ДРУГИЙ ЗАПУСК КЛІЄНТА
    public static void main2() throws MQException {
        Client client = new Client("QM1", "localhost", 1414,
                "SYSTEM.DEF.SVRCONN", "SRV.Q", "CL.Q");
        while (client.printResult()) ;
    }
    public static void main(String[] args) throws IOException, MQException
    {
        main1(); // або main2 ()
    }
}