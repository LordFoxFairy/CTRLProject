package com.example.nettydemo.demo1.self.message.common.util;

import com.example.nettydemo.demo1.self.message.common.entity.MessageRecord;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

public class MyEncode extends MessageToByteEncoder<MessageRecord> {
    
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageRecord msg, ByteBuf out) {
        // 对头部数据进行序列化，并设置一些头部数据的值
        ByteBuffer header = encodeHeader(msg);
        out.writeBytes(header);
        // 序列化body数据
        byte[] body = serializable(msg.getBody());
        if (body != null) {
            out.writeBytes(body);
        }
    }
    
    private ByteBuffer encodeHeader(MessageRecord msg) {
        
        // 头部的length字段的长度
        int length = 4;
        
        // 对头部数据进行序列化
        byte[] headerData = serializable(msg.getMessageHeader());
        
        // 累加长度
        if (headerData != null) {
            length += headerData.length;
        }
        
        // 获取到body的长度
        int bodyLength = Objects.requireNonNull(serializable(msg.getBody())).length;
        // 这里类加之后，其实length就是整个报文的长度了，当然，length本身不算在里面
        length += bodyLength;
        // 所以定义一个length + headerLength +headerData 这些数据的buffer
        ByteBuffer result = ByteBuffer.allocate(4 + length  - bodyLength);
        
        // 设置headerLength + headerData + bodyLength的长度
        result.putInt(length);
        
        // 设置headerData的长度
        result.putInt(Objects.requireNonNull(headerData).length);
        
        // 设置header data
        result.put(headerData);
        // 切换成读模式
        result.flip();
        
        return result;
    }
    
    // 序列化方法
    public static  <T> byte[] serializable(T object) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
