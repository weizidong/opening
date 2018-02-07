package com.weizidong.handler;

import com.weizidong.message.event.*;
import com.weizidong.message.handler.IEventHandler;
import com.weizidong.message.output.base.OutputMessage;
import org.springframework.stereotype.Component;

/**
 * 微信事件处理器
 *
 * @author 魏自东
 * @date 2018/2/7 16:59
 */
@Component
public class EventHandler implements IEventHandler {
    @Override
    public OutputMessage subscribe(SubscribeEventMessage subscribeEventMessage) {
        return null;
    }

    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage unSubscribeEventMessage) {
        return null;
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
