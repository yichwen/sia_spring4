package com.sia.soundsystem.xmlauto;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAutoMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cdplayer.xml");
        MediaPlayer player = context.getBean(MediaPlayer.class);
        player.play();
    }
}
