package com.weizidong.handler;

import com.weizidong.message.handler.IMessageHandler;
import com.weizidong.message.input.*;
import com.weizidong.message.output.OutputMessage;
import org.springframework.stereotype.Service;

/**
 * 微信消息处理器
 *
 * @author 魏自东
 * @date 2018/2/7 16:58
 */
@Service
public class MessageHandler implements IMessageHandler {
    @Override
    public OutputMessage doText(TextInputMessage textInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doImage(ImageInputMessage imageInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doVoice(VoiceInputMessage voiceInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doVideo(VideoInputMessage videoInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doShortvideo(ShortVideoInputMessage shortVideoInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doLocation(LocationInputMessage locationInputMessage) {
        return null;
    }

    @Override
    public OutputMessage doLink(LinkInputMessage linkInputMessage) {
        return null;
    }
}
