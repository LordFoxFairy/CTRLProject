package com.example.nettydemo.demo1.self.message.common.handler;

import com.example.nettydemo.demo1.self.message.common.entity.MessageRecord;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRecord messageRecord = (MessageRecord) msg;
        System.out.println("收到客户端的消息：" + messageRecord);
        super.channelRead(ctx, msg);
    }
}
