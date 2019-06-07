import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<UserData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf inBuf, List<Object> out) throws Exception {
        System.out.println("RequestDecoder: " + "decode");

        int messageCode = inBuf.readInt();     //Достаем код сообщения(Авторизация/регистрация/отправка сообщения)

        if(messageCode == Const.REQUEST_AUTHORIZATION || messageCode == Const.REQUEST_REGISTRATION) {
            authorizationProcess(ctx,inBuf,out);
        }
    }


    void authorizationProcess(ChannelHandlerContext ctx, ByteBuf inBuf, List<Object> out){
        UserData data = new UserData();
        int loginLength = inBuf.readInt();
        data.setLogin(inBuf.readCharSequence(loginLength, charset).toString());
        int passwordLength = inBuf.readInt();
        data.setPassword(inBuf.readCharSequence(loginLength, charset).toString());
        out.add(data);
    }
}