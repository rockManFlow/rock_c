package com.kuark.tool.advance.advance20201105util;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * @date 2019/12/19 16:03
 */
public class IpUtils {

    /**
     * 获取网络请求ip地址
     * @return
     */
    public static InetAddress getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
                NetworkInterface item = e.nextElement();
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    if (address.getAddress() instanceof Inet4Address) {
                        return address.getAddress();
                    }
                    if (address.getAddress() instanceof Inet6Address) {
                        return address.getAddress();
                    }
                }
            }
            return InetAddress.getLocalHost();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 循环查找可用端口
     * @param defaultPort
     * @return
     */
    public static int findAvailablePort(int defaultPort) {
        int portTmp;
        for(portTmp = defaultPort; portTmp < 65535; ++portTmp) {
            if (!isPortUsed(portTmp)) {
                return portTmp;
            }
        }

        for(portTmp = defaultPort--; portTmp > 0; --portTmp) {
            if (!isPortUsed(portTmp)) {
                return portTmp;
            }
        }

        throw new RuntimeException("no available port.");
    }

    public static boolean isPortUsed(int port) {
        boolean used = false;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            used = false;
        } catch (IOException var12) {
            System.out.println(">>>>>>>>>>> xxl-rpc, port[{}] is in use."+port);
            used = true;
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException var11) {
                    System.out.println("");
                }
            }

        }

        return used;
    }

    public static void main(String[] args) {
        System.out.println(getLocalIp());
    }

}
