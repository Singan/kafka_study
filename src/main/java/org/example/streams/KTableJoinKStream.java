package org.example.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Properties;

public class KTableJoinKStream {
    private static String APPLICATION_NAME = "streams-join-application";
    private static String BOOTSTRAP_SERVERS = "my-kafka:9092";
    private static String K_TABLE_NAME = "address";
    private static String K_STREAM_NAME = "order";
    private static String K_STREAM_JOIN_NAMAE = "order_join";
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();

        KTable<String,String> ktable = builder.table(K_TABLE_NAME);
        KStream<String, String> stream = builder.stream(K_STREAM_NAME);

        stream.join(ktable,(order, address) -> order + "send to" + address).to(K_STREAM_JOIN_NAMAE);

        KafkaStreams streams = new KafkaStreams(builder.build(),props);
        streams.start();

    }
}
