import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("ProcessingHandler: " + "channelRead");
        UserData userData = (UserData) msg;
        ResponseData responseData = new ResponseData();
        //if(DatabaseHandler.correctLogin(userData.getLogin)){
            responseData.setIntValue(Const.SUCCESS_OPERATION);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
            System.out.println(userData);
       // }

    }
}