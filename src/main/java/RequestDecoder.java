import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf inBuf, List<Object> out) throws Exception {

        RequestData data = new RequestData();
        data.setIntValue(inBuf.readInt());
        int strLen = inBuf.readInt();
        data.setStringValue(inBuf.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}