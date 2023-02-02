package com.kuark.tool.advance.advance20200313;

import com.kuark.tool.advance.advance20201105util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 这个就是雪花算法的一个实现
 */
@Slf4j
@Service
public class SerialNumberServiceImpl {

    /**
     * 起始的时间戳
     */
    private final static long START_STAMP = 1480166465631L;

    /**
     * 每一部分占用的位数 序列号 机器标识 数据中心
     */
    private final static long SEQUENCE_BIT = 12;
    private final static long MACHINE_BIT = 8;
    private final static long DATA_CENTER_BIT = 2;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIME_STAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    /**
     * 数据中心 机器标识 序列号 上一次时间戳
     */
    private final static long DATA_CENTER_ID = 1;
    private static long address;
    private long sequence = 0L;
    private long lastStamp = -1L;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    static {
        InetAddress localIp = IpUtils.getLocalIp();
        address = localIp.getAddress()[3] & 0xff;
        log.info("当前系统的 address 为: {}", address);
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    private synchronized long nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;
        // 时间戳部分 数据中心部分 机器标识部分 序列号部分
        return (currStamp - START_STAMP) << TIME_STAMP_LEFT | DATA_CENTER_ID << DATA_CENTER_LEFT
            | address << MACHINE_LEFT | sequence;
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public String getSerialNumber() {

        String localDate = LocalDate.now().format(DATE_TIME_FORMATTER);

        return localDate + nextId();
    }

    public String generate(String serialNumberEnum) {
        return serialNumberEnum + nextId();
    }

    public static void main(String[] args) {
        InetAddress localIp = IpUtils.getLocalIp();
        log.info(localIp.getHostAddress());
        address = localIp.getAddress()[3] & 0xff;
        log.info("当前系统的 address 为: {}", address);
//        long l = ~(-1L << SEQUENCE_BIT);
//        log.info("l="+l);
//        System.out.println(-1L << 2);
//        System.out.println(-1L << 3);
//        System.out.println(-1L << SEQUENCE_BIT);

        SerialNumberServiceImpl numberService=new SerialNumberServiceImpl();
        String serialNumber = numberService.generate("AA");
        System.out.println(serialNumber);
    }

}
