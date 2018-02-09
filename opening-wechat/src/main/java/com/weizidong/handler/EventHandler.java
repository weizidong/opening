package com.weizidong.handler;

import com.weizidong.enums.Subscribe;
import com.weizidong.message.event.*;
import com.weizidong.message.handler.IEventHandler;
import com.weizidong.message.output.DefaultOutputMessage;
import com.weizidong.message.output.OutputMessage;
import com.weizidong.message.output.TextOutputMessage;
import com.weizidong.model.entity.User;
import com.weizidong.service.UserService;
import com.weizidong.wechat.WechatUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.ThreadPoolUtil;

import java.util.Date;

/**
 * 微信事件处理器
 *
 * @author 魏自东
 * @date 2018/2/7 16:59
 */
@Service
public class EventHandler implements IEventHandler {
    @Autowired
    private UserService userService;

    @Override
    public OutputMessage subscribe(SubscribeEventMessage message) {
        // 异步处理，及时回复
        ThreadPoolUtil.execute(() -> {
            // 获取openid
            String openid = message.getFromUserName();
            // 检查是否存在该openid
            if (!userService.check(openid)) {
                // 获取微信用户
                WechatUser wechatUser = WechatUser.getUserInfo(openid);
                // 转换成本地用户
                User u = User.convert(wechatUser);
                u.setSubscribe(Subscribe.已关注.getValue());
                u.setSubscribeTime(new Date());
                // 保存新用户
                userService.create(u);
            }
        });
        return new TextOutputMessage("开始聊天吧。。。");
    }

    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage message) {
        // 异步处理，及时回复
        ThreadPoolUtil.execute(() -> {
            // 获取openid
            String openid = message.getFromUserName();
            // 更新用户信息
            User u = new User();
            u.setOpenid(openid);
            u.setSubscribe(Subscribe.未关注.getValue());
            userService.updateByOpenId(u);
        });
        return new DefaultOutputMessage();
    }

    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage qrsceneSubscribeEventMessage) {
        return null;
    }

    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage qrsceneScanEventMessage) {
        return null;
    }

    @Override
    public OutputMessage location(LocationEventMessage locationEventMessage) {
        return null;
    }

    @Override
    public OutputMessage click(ClickEventMessage clickEventMessage) {
        return null;
    }

    @Override
    public OutputMessage view(ViewEventMessage viewEventMessage) {
        return null;
    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage scanCodePushEventMessage) {
        return null;
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage scanCodeWaitMsgEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage picSysPhotoEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage picPhotoOrAlbumEventMessage) {
        return null;
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage picWeixinEventMessage) {
        return null;
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage locationSelectEventMessage) {
        return null;
    }
}
