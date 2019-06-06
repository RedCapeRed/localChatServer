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
        int strLen = inBuf.readInt();
        data.setLogin(inBuf.readCharSequence(strLen, charset).toString());
        int charArrayLen = inBuf.readInt();
        char[] password = new char[charArrayLen];
        for(int i = 0;i < charArrayLen;i++){
            password[i] = inBuf.readChar();
        }
        data.setPassword(password);
        out.add(data);
    }
}