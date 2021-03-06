/**
 *
 */
package com.matrimony.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

/**
 * @author SON
 *
 */
public class GeoIP {

	private final static String COUNTRY_DB_PATH = GeoIP.class.getResource("/resoucres/GeoLite2-Country.mmdb").getFile();
	private final static String CITY_DB_PATH = GeoIP.class.getResource("/resoucres/GeoLite2-City.mmdb").getFile();
	private final static File COUNTRY_DB = new File(COUNTRY_DB_PATH);
	private final static File CITY_DB = new File(CITY_DB_PATH);
//	private static DatabaseReader countryReader, cityReader;
//
//	static {
//		try {
//			countryReader = new DatabaseReader.Builder(COUNTRY_DB).build();
//			cityReader = new DatabaseReader.Builder(CITY_DB).build();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static CountryResponse getCountry(String ipaddr) {
		DatabaseReader countryReader;
		CountryResponse countryResponse = null;
		try {
			countryReader = new DatabaseReader.Builder(COUNTRY_DB).build();
			InetAddress inetAddr = InetAddress.getByName(ipaddr);
			countryResponse = countryReader.country(inetAddr);
			countryReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return countryResponse;
	}

	public static CityResponse getCity(String ipaddr) {
		DatabaseReader cityReader;
		CityResponse cityResponse = null;
		try {
			cityReader = new DatabaseReader.Builder(CITY_DB).build();
			InetAddress inetAddr = InetAddress.getByName(ipaddr);
			cityResponse = cityReader.city(inetAddr);
			cityReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			System.out.println(e.getMessage());
		}
		return cityResponse;
	}
}
