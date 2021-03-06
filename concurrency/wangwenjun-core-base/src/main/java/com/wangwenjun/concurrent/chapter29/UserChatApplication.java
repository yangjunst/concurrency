package com.wangwenjun.concurrent.chapter29;

import java.util.concurrent.TimeUnit;

public class UserChatApplication
{
    public static void main(String[] args) throws InterruptedException {
        final AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());
        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());
        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());
        new UserChatThread(new User("Leo"), dispatcher).start();
        new UserChatThread(new User("Alex"), dispatcher).start();
        new UserChatThread(new User("Tina"), dispatcher).start();
        TimeUnit.SECONDS.sleep(1);
        dispatcher.shutdown();
    }
}
