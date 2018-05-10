package com.deliciousapp.hbase.connection;

import com.google.protobuf.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

@Component
@Slf4j
@lombok.Getter
public class HBaseConnection {

    private HTable table;

    private static boolean initialized = false;

    public HBaseConnection(
            @Value("${hbase.table.name}") String hbaseTableName,
            @Value("${hbase.url}") String hbaseUrl) throws IOException, ServiceException {
        if(!initialized){
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", hbaseUrl);
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            HBaseAdmin.checkHBaseAvailable(conf);

            Connection conn = ConnectionFactory.createConnection(conf);
            this.table = (HTable)conn.getTable(TableName.valueOf(hbaseTableName));
            initialized = true;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("hadoop.home.dir", "C:\\Users\\Adrian\\hadoop\\hadoop-3.0.0");
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "ec2-52-15-145-67.us-east-2.compute.amazonaws.com");
        Connection conn = ConnectionFactory.createConnection(conf);
        HTable table = (HTable)conn.getTable(TableName.valueOf("delishapp"));

        Get get = new Get(Bytes.toBytes("adrian.barona@live.com"));
        Result result = table.get(get);

        initialized = true;
    }


//    public static void main(String[] args) throws IOException, ServiceException {
//        Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum", "ec2-35-174-7-40.compute-1.amazonaws.com"/*,ec2-54-144-25-162.compute-1.amazonaws.com,ec2-54-86-123-118.compute-1.amazonaws.com*/);
//        conf.set("hbase.zookeeper.property.clientPort", "2181");
//        HBaseAdmin.checkHBaseAvailable(conf);
//
//        Connection conn = ConnectionFactory.createConnection(conf);
//        HTable table = (HTable)conn.getTable(TableName.valueOf("delishapp"));
//
//        Get get = new Get(Bytes.toBytes("adrian.barona@live.com"));
//        Result result = table.get(get);
//
//        System.out.println(result.isEmpty());
//
//        System.out.println(new String(result.getValue(Bytes.toBytes("profile"), Bytes.toBytes("password"))));
//
//        System.out.println("finally");
//
//
//
//    }

}
