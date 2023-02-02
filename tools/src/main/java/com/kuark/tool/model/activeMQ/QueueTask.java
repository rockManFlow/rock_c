package com.kuark.tool.model.activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 服务端定时从activeMQ中取
 * Created by caoqingyuan on 2016/9/12.
 */
public class QueueTask extends TimerTask {
    private static final Logger logger=Logger.getLogger(QueueTask.class);
    private static ConnectionFactory connectionFactory;
    private static Connection connection=null;
    //ActiveMQ服务器地址和端口
    private static String activeMqUrl = "tcp://localhost:61616";
    //服务器端接收队列
    private static final String queueReceive = "sendQueue";
    //发送队列
    private static final String queueSend = "receiveQueue";
    //
    private static final Integer queryNum=50;
    static {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                activeMqUrl);//ActiveMQ服务地址和端口
        // 构造从工厂得到连接对象，一般只创建一个连接，因为一个连接可以创建多个session
        try {
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void schedule(){
        Timer timer = new Timer();
        timer.schedule(new QueueTask(),0,60*1000L);
    }
    @Override
    public void run() {
        logger.info("server queue start");
        try {
            consumer(queueReceive,connection);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    //接收消息并给出回应
    public void consumer(String queueName, Connection connection) throws Exception {
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
        //消息选择
        consumer = session.createConsumer(destination,"JMSXGroupID='[4]'");
        for(int i=0;i<queryNum;i++) {
            TextMessage message = (TextMessage) consumer.receive(30000);//这个时间应该是接收一个消息的最大花费时间
            if (null != message) {
                destination = session.createQueue(queueSend);
                producer = session.createProducer(destination);
                //这部分进行消息的处理
                System.out.println("收到消息" + message.getText());
                sendMessage(session, producer, "responsed");//给出回应
                Thread.sleep(10 * 1000);
                System.out.println("message finished");
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
