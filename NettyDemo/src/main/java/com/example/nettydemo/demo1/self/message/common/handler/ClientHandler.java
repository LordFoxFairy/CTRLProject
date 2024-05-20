package com.example.nettydemo.demo1.self.message.common.handler;

import com.example.nettydemo.demo1.self.message.common.entity.HeaderData;
import com.example.nettydemo.demo1.self.message.common.entity.MessageHeader;
import com.example.nettydemo.demo1.self.message.common.entity.MessageRecord;
import com.example.nettydemo.demo1.self.message.entity.User;
import com.example.nettydemo.demo1.self.message.common.enums.EnumCode;
import com.example.nettydemo.demo1.self.message.common.enums.EnumReq;
import com.example.nettydemo.demo1.self.message.common.enums.EnumSerializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        /*
             length
             header:headerLength | headerData(version | languageCode | serializableType | reqType)
             body
        */
        for (int i = 0; i < 5; i++) {
            
            MessageRecord messageRecord = new MessageRecord();
            
            MessageHeader header = new MessageHeader();
            
            HeaderData headerData = new HeaderData();
            headerData.setVersion(1);
            headerData.setLanguageCode(EnumCode.JAVA.getCode());
            headerData.setReqType(EnumReq.REQ.getCode());
            headerData.setSerializableType(EnumSerializer.JAVA.getCode());
            
            header.setHeaderData(headerData);
            messageRecord.setMessageHeader(header); // header
            
            User user = new User();
            user.setName("请求数据"+i);
            user.setAge(i);
            
            messageRecord.setBody(user); // body
            
            System.out.println(messageRecord);
            ctx.writeAndFlush(messageRecord);
            
        }
        
        super.channelActive(ctx);
        
    }
}

