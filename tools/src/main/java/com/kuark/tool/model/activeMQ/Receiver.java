package com.kuark.tool.model.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by caoqingyuan on 2016/8/26.
 */
public class Receiver {
    private static ConnectionFactory connectionFactory;
    //ActiveMQ服务器地址和端口
    private static String activeMqUrl = "tcp://localhost:61616";
    //
    private static final String queueSend = "sendQueue";
    //
    private static final String queueReceive = "receiveQueue";

    //该链接可以使用连接池
    public static Connection commonConnection() throws JMSException {
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                activeMqUrl);//ActiveMQ服务地址和端口
        // 构造从工厂得到连接对象，一般只创建一个连接，因为一个连接可以创建多个session
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();
        return connection;
    }

    public static void consumer(String queueName, Connection connection) throws Exception {
        //队列
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        //
        MessageProducer producer;
        // 获取操作连接
        Session session;
        session = connection.createSession(Boolean.FALSE,
                Session.AUTO_ACKNOWLEDGE);
        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
        destination = session.createQueue(queueName);
        consumer = session.createConsumer(destination);
        while(true) {
            TextMessage message = (TextMessage) consumer.receive(30000);//这个时间应该是接收一个消息的最大花费时间
            if (null != message) {
                destination = session.createQueue(queueReceive);
                producer = session.createProducer(destination);
                System.out.println("收到消息" + message.getText());
                sendMessage(session, producer, "responsed");//给出回应
                Thread.sleep(10 * 1000);
            }
        }
    }

    //接收消息
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = commonConnection();
            consumer(queueSend, connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer, String mess)
            throws Exception {
        TextMessage message = session
                .createTextMessage(mess);
        // 发送消息到目的地方
        System.out.println(mess);
        producer.send(message);
    }
}