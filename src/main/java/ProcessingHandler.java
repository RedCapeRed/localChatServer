import Database.DatabaseHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.sql.SQLException;

public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    UserData userData;
    String login;
    String password;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ProcessingHandler: " + "channelRead");
        userData = (UserData) msg;
        login = userData.getLogin();
        password = userData.getPassword();
        ResponseData responseData = new ResponseData();
        if(userData.isRegistered())
            checkUserExistence(responseData,userData,ctx);
        if(!userData.isRegistered()){
            registerUser(userData,responseData,ctx);
        }
    }

    void checkUserExistence(ResponseData responseData,UserData userData,ChannelHandlerContext ctx) throws SQLException {
        if(DatabaseHandler.userFound(login,password)) {
            responseData.setIntValue(Const.SUCCESS_OPERATION);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }else{
            responseData.setIntValue(Const.ERROR_ACCOUNT_NOT_EXIST);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
    void registerUser(UserData userData,ResponseData responseData,ChannelHandlerContext ctx) throws SQLException {
        if(login.length() < 4 || password.length() < 7){
            responseData.setIntValue(Const.INVALID_USERNAME_OR_PASSWORD);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }else if(DatabaseHandler.loginAlreadyTaken(login)){
            responseData.setIntValue(Const.ERROR_LOGIN_ALREADY_TAKEN);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }else{
            DatabaseHandler.registerUser(login,password);
            responseData.setIntValue(Const.SUCCESS_OPERATION);
            ChannelFuture future = ctx.writeAndFlush(responseData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}