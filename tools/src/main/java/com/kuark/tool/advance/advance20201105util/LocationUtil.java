package com.kuark.tool.advance.advance20201105util;

import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * @author rock
 * @detail
 * @date 2020/9/18 19:22
 */
public class LocationUtil {
    /**
     * 经纬度分隔符
     */
    private static final String LA_LT_SEPARATOR=",";


    private static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

    /**
     * 通过经纬度计算距离
     * @param latitudeS 源纬度
     * @param longitudeS 源经度
     * @param latitudeT
     * @param longitudeT
     * @return km距离 保留一位小数
     */
    public static String calculateDistance(Double latitudeS,Double longitudeS,Double latitudeT,Double longitudeT){
        //存在一个为空，直接返回null
        if(!ObjectUtils.allNotNull(latitudeS,longitudeS,latitudeT,longitudeT)){
            return "";
        }
        GlobalCoordinates source = new GlobalCoordinates(latitudeS, longitudeS);
        GlobalCoordinates target = new GlobalCoordinates(latitudeT, longitudeT);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        return String.format("%.1f", meter1/1000);
    }

    /**
     * 拆分经纬度
     * @param location
     */
    public static LaLtDto splitLaAndLt(String location){
        LaLtDto laLtDto=new LaLtDto();
        if(StringUtils.isEmpty(location)){
            return laLtDto;
        }
        //拆分经纬度
        String[] locationArray = location.split(LA_LT_SEPARATOR);
        //纬度
        double latitude=Double.valueOf(locationArray[0]);
        //经度
        double longitude=Double.valueOf(locationArray[1]);
        laLtDto.setLatitude(latitude);
        laLtDto.setLongitude(longitude);
        return laLtDto;
    }

    @Data
    public static class LaLtDto{
        //纬度
        private Double latitude;
        //经度
        private Double longitude;
    }

    public static void main(String[] args) {
        // //121.717594,31.12055    121.817629,31.090867
        GlobalCoordinates source = new GlobalCoordinates(31.12055, 121.717594);
        GlobalCoordinates target = new GlobalCoordinates(31.090867, 121.817629);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        System.out.println("WGS84坐标系计算结果："+meter2 + "米");

        double meter3 =10095.728833929528D;
        String result = String.format("%.1f", meter3/1000);
        System.out.println(result);
    }
}
