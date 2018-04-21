package com.rayenyang.webpj.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * description:
 * Created by rayenyang on 2017/7/3.
 */
public class KafkaProducerTest {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.137.9:9092");
        props.put("acks", "all");
        props.put("retries", 1);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("group-topic", Integer.toString(i), Integer.toString(i)));
            Thread.sleep(1000);
        }
    
        producer.close();
    }
}
