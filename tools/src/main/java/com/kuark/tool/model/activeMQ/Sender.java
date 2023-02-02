package com.kuark.tool.model.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者可在消息中放入应用程序特有的属性，而消费者可使用基于这些属性的选择标准来表明对消息是否感兴趣。
 * 这就简化了客户端的工作，并避免了向不需要这些消息的消费者传送消息的开销。
 * 然而，它也使得处理选择标准的消息服务增加了一些额外开销
 * Created by caoqingyuan on 2016/8/26.
 */
public class Sender {
    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    private static ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS Provider 的连接
//    private Connection connection = null;
    // Session： 一个发送或接收消息的线程
//    private static Session session;
    // Destination ：消息的目的地;消息发送给谁.
//    private static Destination destination;
    // MessageProducer：消息发送者
//    private static MessageProducer producer;
    //ActiveMQ服务器地址和端口
    private static String activeMqUrl = "tcp://localhost:61616";
    //消息个数
    private static final int SEND_NUMBER = 5;
    //
    private static final String queueSend = "sendQueue";
    //
    private static final String queueReceive = "receiveQueue";

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

    public static void producer(String queueName,Connection connection) throws Exception {
        MessageProducer producer;
        Destination destination;
        // 获取操作连接
        Session session;
        //transacted为使用事务标识false,true，acknowledgeMode为签收模式
        session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
        //一个session的队列名
        destination = session.createQueue(queueName);
        // 得到消息生成者【发送者】
        producer = session.createProducer(destination);
        // 设置不持久化，此处学习，实际根据项目决定
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 构造消息，此处写死，项目就是参数，或者方法获取
        sendMessage(session, producer);
        session.commit();
    }

    public static void consumer(String queueName,Connection connection) throws JMSException {
        //队列
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        // 获取操作连接
        Session session;
        session = connection.createSession(Boolean.FALSE,
                Session.AUTO_ACKNOWLEDGE);
        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
        destination = session.createQueue(queueName);
        consumer = session.createConsumer(destination);
        while (true) {
            TextMessage message = (TextMessage) consumer.receive(30000);
            if (null != message) {
                System.out.println("收到消息" + message.getText());
            }
        }
    }

    //发送消息
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection =commonConnection();
            producer(queueSend,connection);
            consumer(queueReceive,connection);
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

    public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            TextMessage message = session
                    .createTextMessage("ActiveMq 发送的消息[" + i+"]");
            //设置消息选择条件
            message.setStringProperty("JMSXGroupID","["+i+"]");
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
