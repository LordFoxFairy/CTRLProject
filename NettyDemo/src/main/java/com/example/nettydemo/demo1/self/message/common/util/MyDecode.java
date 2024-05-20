package com.example.nettydemo.demo1.self.message.common.util;

import com.example.nettydemo.demo1.self.message.common.entity.MessageHeader;
import com.example.nettydemo.demo1.self.message.common.entity.MessageRecord;
import com.example.nettydemo.demo1.self.message.entity.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class MyDecode extends LengthFieldBasedFrameDecoder {
    public MyDecode() {
        // 最后一个参数，跳过4个字节长度，不需要length
        super(Integer.MAX_VALUE,0,4,0,4);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        
        ByteBuf byteBuf = (ByteBuf)super.decode(ctx, in);
        
        if(byteBuf == null){
            return null;
        }
        
        ByteBuffer byteBuffer = byteBuf.nioBuffer();
        
        return MyDecode.decode(byteBuffer);
    }
    
    private static Object decode(ByteBuffer byteBuffer) {
        
        // byteBuffer的长度
        int length = byteBuffer.limit();
        
        // headData长度
        int headLength = byteBuffer.getInt();
        // head数据
        byte[] headerData = new byte[headLength];
        // 读取数据到headerData
        byteBuffer.get(headerData);
        
        
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setLength(length);
        // 反序列化得到头的对象
        MessageHeader header = deserializable(MessageHeader.class,headerData);
        messageRecord.setMessageHeader(header);
        
        // 反序列化得到body的对象：length - headDataLength - headLength
        int bodyLength = length - 4 - headLength;
        byte[] bodyData = null;
        if(bodyLength > 0){
            bodyData = new byte[bodyLength];
            byteBuffer.get(bodyData);
        }
        messageRecord.setBody(MyDecode.deserializable(User.class,bodyData));
        return messageRecord;
    }
    
    private static<T> T deserializable(Class<T> clazz ,byte[] data){
        ObjectInputStream ois;
        try {
            try {
                ois = new ObjectInputStream(new ByteArrayInputStream(data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return (T)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
