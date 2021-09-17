package com.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GpsUtils {

	private static final Logger logger = LoggerFactory.getLogger(GpsUtils.class);
	private static final double PI = Math.PI;
	private static final double X_PI = Math.PI * 3000.0 / 180.0;
	private static final double EARTH_RADIUS1 = 6378245.0;
	private static final double EE = 0.00669342162296594323;
	public static final double EARTH_RADIUS = 6378.1370;
	/**
	 * GPS纬度格式化（度分秒）（）
	 * <p>将GPS纬度转换成 ==》(格式ddmm.mmmm:即dd 度，mm.mmmm 分)，例如 34.112233 ==》
	 * @param latitude
	 * @param formater    目前支持dm(度分) 或dms(度分秒)
	 * @return    格式dd,mm.mmmm:即dd 度，mm.mmmm 分
	 */
	public static String formateLatitude(String latitude, String formater) {
//        formater = "dm";
//        String fdd = "°";
//        String fmm = "′";
//        String fss = "″";
		String fdd = ",";
		String fmm = ",";
		String fss = ",";
		String newLatitude = "";

		try {
			if (latitude != null && !"".equals(latitude)) {
				int idx = latitude.lastIndexOf(".");// 查找小数点的位置
				String dd = latitude.substring(0, idx);
				String mm = latitude.substring(idx);

				if (formater.equals("dm")) {
					// 度分形式返回，分可能有小数位
					Double newFen = Double.valueOf(mm) * 60;

					// newLatitude = dd + "°" + String.valueOf(newFen);
					newLatitude = dd + fdd + String.valueOf(newFen) + fmm;
					newLatitude = dd + fdd + String.valueOf(newFen);
				} else if (formater.equals("dms")) {
					Double newFen = Double.valueOf(mm) * 60;
					String fen = String.valueOf(newFen);
					int ssIndex = fen.lastIndexOf(".");
					String newfen = fen.substring(0, ssIndex);
					String ss = fen.substring(ssIndex);
					Double newss = Double.valueOf(ss) * 60;
//                    newLatitude = dd + fdd + newfen + fmm + String.valueOf(newss) + fss;
					newLatitude = dd + fdd + newfen + fmm + String.valueOf(newss);
				}

			}
		} catch (NumberFormatException e) {
			logger.error("转换纬度时异常:纬度" + latitude);
			e.printStackTrace();
		}
		return newLatitude;
	}

	public static double[] gps84_To_Gcj02(double lat, double lon) {
		if (outOfChina(lat, lon)) {
			return new double[]{lat, lon};
		}
		double dLat = transformLat(lon - 105.0, lat - 35.0);
		double dLon = transformLon(lon - 105.0, lat - 35.0);
		double radLat = lat / 180.0 * PI;
		double magic = Math.sin(radLat);
		magic = 1 - EE * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((EARTH_RADIUS1 * (1 - EE)) / (magic * sqrtMagic) * PI);
		dLon = (dLon * 180.0) / (EARTH_RADIUS1 / sqrtMagic * Math.cos(radLat) * PI);
		double mgLat = lat + dLat;
		double mgLon = lon + dLon;
		return new double[]{mgLat, mgLon};
	}
	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y
				+ 0.1 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI))
				* 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0
				/ 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * PI)
				+ 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
		return ret;
	}

	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
				+ 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI))
				* 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0
				/ 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0))
				* 2.0 / 3.0;
		return ret;
	}

	private static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347) {
			return true;
		} else if (lat < 0.8293 || lat > 55.8271) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * GPS经度格式化
	 * <p>将GPS纬度转换成 ==》(格式ddmm.mmmm:即dd 度，mm.mmmm 分)，例如 108.112233 ==》
	 * @param longitude
	 * @param formater    目前支持dm(度分) 或dms(度分秒)
	 * @return    格式ddmm.mmmm:即dd 度，mm.mmmm 分
	 */
	public static String formatLongitude(String longitude, String formater) {
		formater = "dm";
//        String fdd = "°";
//        String fmm = "′";
//        String fss = "″";
		String fdd = ",";
		String fmm = ",";
		String fss = ",";
		String newLngitude = "";

		try {
			if (longitude != null && !"".equals(longitude)) {
				int idx = longitude.lastIndexOf(".");// 查找小数点的位置
				String dd = longitude.substring(0, idx);
				String mm = longitude.substring(idx);

				if (formater.equals("dm")) {
					// 度分形式返回，分可能有小数位
					Double newFen = Double.valueOf(mm) * 60;

					// newLatitude = dd + "°" + String.valueOf(newFen);
					newLngitude = dd + fdd + String.valueOf(newFen) + fmm;
					newLngitude = dd + fdd + String.valueOf(newFen);
				} else if (formater.equals("dms")) {
					Double newFen = Double.valueOf(mm) * 60;
					String fen = String.valueOf(newFen);
					int ssIndex = fen.lastIndexOf(".");
					String newfen = fen.substring(0, ssIndex);
					String ss = fen.substring(ssIndex);
					Double newss = Double.valueOf(ss) * 60;
//                    newLatitude = dd + fdd + newfen + fmm + String.valueOf(newss) + fss;
					newLngitude = dd + fdd + newfen + fmm + String.valueOf(newss);
				}

			}
		} catch (NumberFormatException e) {
			logger.error("转换纬度时异常:纬度" + longitude);
			e.printStackTrace();
		}
		return newLngitude;
	}


	/**
	 * 纬度转换
	 *
	 * @param latitude
	 * @return
	 */
	public static String convertLatitude(String latitude) {
		String newLatitude = "";
		try {
			if (latitude != null && !"".equals(latitude)) {
				String du = latitude.substring(0, 2);
				Double douDu = Double.valueOf(du);
				String fen = latitude.substring(2);
				Double douFen = Double.valueOf(fen);
				Double newFen = douFen / 60;
				Double douLat = douDu + newFen;
				newLatitude = String.valueOf(douLat);
			}
		} catch (NumberFormatException e) {
			logger.error("转换纬度时异常:纬度" + latitude);
			e.printStackTrace();
		}
		return newLatitude;
	}

	/**
	 * 精度转换
	 *
	 * @param longitude
	 * @return
	 */
	public static String convertLongitude(String longitude) {
		String newLongitude = "";
		try {
			if (longitude != null && !"".equals(longitude)) {
				String du = longitude.substring(0, 3);
				Double douDu = Double.valueOf(du);
				String fen = longitude.substring(3);
				Double douFen = Double.valueOf(fen);
				Double newFen = douFen / 60;
				Double douLat = douDu + newFen;
				newLongitude = String.valueOf(douLat);
			}
		} catch (NumberFormatException e) {
			logger.error("转换经度时异常:经度" + longitude);
			e.printStackTrace();
		}
		return newLongitude;
	}


	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(String lat1, String lng1, String lat2, String lng2) {
		return getDistance(Double.valueOf(lat1), Double.valueOf(lng1), Double.valueOf(lat2), Double.valueOf(lng2));
	}

	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {

		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;// 取WGS84标准参考椭球中的地球长半径(单位:m)
		s = (s * 10000) / 10;
		return new BigDecimal(s).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 判断坐标点和圆形的关系
	 * @param lng1 圆心的经度
	 * @param lat1 圆心的纬度
	 * @param lng 坐标点经度
	 * @param lat 坐标点纬度
	 * @return
	 */
	public static Boolean isCircle(Double lng1,Double lat1,Double lng,Double lat,Double raduis){
		Boolean flag = true;
		double dLat = rad(lat1 - lat);
		double dLng = rad(lng1 - lng);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat * Math.PI / 180) * Math.cos(lat1 * Math.PI / 180) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = EARTH_RADIUS * c;
		double dis = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		//点在圆内
		if (dis > raduis){
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断是否在多边形区域内
	 *
	 * @param pointLon
	 *            要判断的点的纵坐标
	 * @param pointLat
	 *            要判断的点的横坐标
	 * @param lon
	 *            区域各顶点的纵坐标数组
	 * @param lat
	 *            区域各顶点的横坐标数组
	 * @return
	 */
	public static boolean isInPolygon(double pointLon, double pointLat, double[] lon,
									  double[] lat) {
		// 将要判断的横纵坐标组成一个点
		Point2D.Double point = new Point2D.Double(pointLon, pointLat);
		// 将区域各顶点的横纵坐标放到一个点集合里面
		List<Point2D.Double> pointList = new ArrayList<Point2D.Double>();
		double polygonPoint_x = 0.0, polygonPoint_y = 0.0;
		for (int i = 0; i < lon.length; i++) {
			polygonPoint_x = lon[i];
			polygonPoint_y = lat[i];
			Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
			pointList.add(polygonPoint);
		}
		return check(point, pointList);
	}

	/**
	 * 一个点是否在多边形内
	 *
	 * @param point
	 *            要判断的点的横纵坐标
	 * @param polygon
	 *            组成的顶点坐标集合
	 * @return
	 */
	private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
		java.awt.geom.GeneralPath peneralPath = new java.awt.geom.GeneralPath();

		Point2D.Double first = polygon.get(0);
		// 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
		peneralPath.moveTo(first.x, first.y);
		polygon.remove(0);
		for (Point2D.Double d : polygon) {
			// 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
			peneralPath.lineTo(d.x, d.y);
		}
		// 将几何多边形封闭
		peneralPath.lineTo(first.x, first.y);
		peneralPath.closePath();
		// 测试指定的 Point2D 是否在 Shape 的边界内。
		return peneralPath.contains(point);
	}


	public static void main(String[] args) {
		// long beignTime = System.currentTimeMillis();
		//
		// Point p1 = new
		// Point(Double.valueOf(convertLatitude("3635.4984")),Double.valueOf(convertLongitude("10929.1191"))
		// );
		// Point p2 = new
		// Point(Double.valueOf(convertLatitude("3635.4980")),Double.valueOf(convertLongitude("10929.1187"))
		// );
		//
		// double trueLeng1 = GpsUtils.calcTwoPointMiles(p2, p1);
		// double trueLeng2 = getDistance(
		// Double.valueOf(convertLatitude("3635.5197"))
		// ,Double.valueOf(convertLongitude("10928.1921"))
		// ,Double.valueOf(convertLatitude("3635.5094"))
		// ,Double.valueOf(convertLongitude("10928.1831")) );
		//
		// System.out.println( "distacne:"+trueLeng2 );
		//
		// System.out.println(System.currentTimeMillis() - beignTime);

	}
}