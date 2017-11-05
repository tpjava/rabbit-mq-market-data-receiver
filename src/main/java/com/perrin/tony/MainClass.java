package com.perrin.tony;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

public class MainClass {
    private static final String HOST = "localhost";

    public static void main(String[] args) throws Exception {

        Connection connection = initConnection();

        Stream.of(args).forEach(t -> {
            try {
                createStockListerners(t, connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private static void createStockListerners(String queueName, Connection connection) throws IOException {
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    private static Connection initConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        return factory.newConnection();
    }
}
