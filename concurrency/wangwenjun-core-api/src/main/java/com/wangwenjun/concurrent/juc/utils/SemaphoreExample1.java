package com.wangwenjun.concurrent.juc.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class SemaphoreExample1
{
    public static void main(String[] args)
    {
        final int MAX_PERMIT_LOGIN_ACCOUNT = 10;
        final LoginService loginService = new LoginService(MAX_PERMIT_LOGIN_ACCOUNT);

        IntStream.range(0, 20).forEach(i ->
                new Thread(() ->
                {
                    boolean login = loginService.login();
                    if (!login)
                    {
                        System.out.println(currentThread() + " is refused due to exceed max online account.");
                        return;
                    }

                    try
                    {
                        simulateWork();
                    } finally
                    {
                        loginService.logout();
                    }
                }, "User-" + i).start()
        );
    }

    private static void simulateWork()
    {
        try
        {
            TimeUnit.SECONDS.sleep(current().nextInt(10));
        } catch (InterruptedException e)
        {
            //ignore
        }
    }

    private static class LoginService
    {
        private final Semaphore semaphore;

        public LoginService(int maxPermitLoginAccount)
        {
            this.semaphore = new Semaphore(maxPermitLoginAccount, true);
        }

        public boolean login()
        {
            try
            {
                semaphore.acquire();
                System.out.println(currentThread() + " login success.");
            } catch (InterruptedException e)
            {
                return false;
            }
            return true;
        }

        public void logout()
        {
            semaphore.release();
            System.out.println(currentThread() + " logout success.");
        }
    }
}