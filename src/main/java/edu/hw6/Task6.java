package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class Task6 {

    private Task6() {
    }

    private static final String PROTOCOL_TCP = "TCP";
    private static final String PROTOCOL_UDP = "UDP";
    private static final String PORT_SPACING = "    ";
    private static final Map<Integer, String> PORT_SERVICES;

    static {
        Map<Integer, String> map = new HashMap<>();
        map.put(20, "FTP - Data Transfer");
        map.put(21, "FTP - Command Control");
        map.put(22, "SSH");
        map.put(23, "Telnet");
        map.put(25, "SMTP");
        map.put(53, "DNS");
        map.put(80, "HTTP");
        map.put(110, "POP3");
        map.put(143, "IMAP");
        map.put(443, "HTTPS");
        map.put(465, "SMTPS");
        map.put(993, "IMAP over SSL");
        map.put(995, "POP3 over SSL");
        map.put(3306, "MySQL Database");
        map.put(3389, "Remote Desktop");
        map.put(5432, "PostgreSQL Database");
        map.put(5900, "VNC");
        map.put(6379, "Redis");
        map.put(8080, "HTTP Alt");
        // Дополнительные порты и сервисы могут быть добавлены здесь

        PORT_SERVICES = Collections.unmodifiableMap(map);
    }

    public static List<String> scanPorts() {
        List<String> openPorts = new ArrayList<>();

        for (int port = 0; port <= 49151; port++) {
            if (isPortOpen(port, PROTOCOL_TCP)) {
                openPorts.add(PROTOCOL_TCP + PORT_SPACING + port + PORT_SPACING + getServiceName(port));
            }
            if (isPortOpen(port, PROTOCOL_UDP)) {
                openPorts.add(PROTOCOL_UDP + PORT_SPACING + port + PORT_SPACING + getServiceName(port));
            }
        }

        return openPorts;
    }

    private static boolean isPortOpen(int port, String protocol) {
        try {
            if (protocol.equals(PROTOCOL_TCP)) {
                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    return true;
                }
            }
            if (protocol.equals(PROTOCOL_UDP)) {
                try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    private static String getServiceName(int port) {
        return PORT_SERVICES.getOrDefault(port, "");
    }
}
