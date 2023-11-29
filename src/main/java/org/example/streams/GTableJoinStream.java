package org.example.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.Properties;

public class GTableJoinStream {
    private static String APPLICATION_NAME = "streams-join-application";
    private static String BOOTSTRAP_SERVERS = "my-kafka:9092";
    private static String K_TABLE_NAME = "address_v3";
    private static String K_STREAM_NAME = "order";
    private static String K_STREAM_JOIN_NAMAE = "order_join";
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();

        GlobalKTable<String,String> ktable = builder.globalTable(K_TABLE_NAME);
        KStream<String, String> stream = builder.stream(K_STREAM_NAME);

        stream.join(ktable,(key, value) -> key,(value1, value2) -> value1+" : send to " + value2).to(K_STREAM_JOIN_NAMAE);

        KafkaStreams streams = new KafkaStreams(builder.build(),props);
        streams.start();

    }
}
