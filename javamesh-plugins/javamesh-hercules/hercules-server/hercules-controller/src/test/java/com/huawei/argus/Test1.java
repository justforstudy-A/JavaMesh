package com.huawei.argus;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.ngrinder.model.PerfTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test1 {
	public static void main(String[] args) {
		String perfTestInfos = "{\"targetHosts\":\"www.baidu.com:127.0.0.2\",\"durationHour\":1,\"description\":\"13\",\"rampUpInitCount\":21,\"threshold\":\"D\",\"vuserPerAgent\":14,\"useRampUp\":\"true\",\"duration\":977000,\"samplingInterval\":18,\"param\":20,\"isClone\":false,\"testName\":\"11\",\"processes\":\"1\",\"rampUpStep\":22,\"rampUpInitSleepTime\":23,\"threads\":\"1\",\"scriptRevision\":\"-1\",\"ignoreSampleCount\":19,\"scriptName\":\"mmm11/src/main/java/TestRunner.groovy\",\"tagString\":\"1,12\",\"safeDistribution\":\"\",\"agentCount\":0,\"rampUpType\":\"THREAD\",\"status\":\"SAVED\"}";
		PerfTest perfTest = JSONObject.parseObject(perfTestInfos, PerfTest.class);
		System.out.println(perfTest);
		String tpsStr = "[0,null,0,0,0,1.5,6,6,6,6,6,6,6,6,4.5,6,5.5,6.5,6,6,6,6.5,6,6,6,6.5,5.5,6,6,6,5,6,6,6,6.5,5,4.5,6.5,5.5,6,6.5,6,6,6,6,4.5,6,6.5,5.5,6.5,6,6.5,5.5,6,6]";
//		List<Map<String, String>> tps = getTps(2, 90, 1, tpsStr);

//		tpsStr = "2";
		List<Map<String, Object>> tps = getTps(2, 108, 1, tpsStr);

		System.out.println("tps:" + tps);
		System.out.println(tps.size());
	}

	@Test
	public void testChart1() {
		String str = "[229.5,0,244,247.5,252,255,241,246,250.5,246.5,245.5,240,254.5,259,253.5,256,242.5,260.5,223.5,224.5,238,222,257.5,254,250,239,235,127]";
		List<Map<String, Object>> tps1 = getTps1(2, 90, 1, str);
		System.out.println("tps1-->"  + tps1);
	}
	@Test
	public void testChart() {
		String str = "[4,null,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,1,11,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,12,0,0,12,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]";
		System.out.println(str.split(",").length);
		str = "[12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12]";
		System.out.println(str.split(",").length);
		str = "[0,null,0,12.5,70,69,67,69.5,70,70.5,72,67,70.5,71,68.5,70,69,70.5,70.5,72,69,52,71.5,62,79,61.5,68,79.5,71.5,60.5,78,69.5,43.5,41,51,67,69.5,70,70,69.5,69.5,70.5,60.5,77,63.5,60.5,68.5,71.5,69.5,70,70,67.5,73,70.5,69,50,44.5,65.5,71.5,65.5,58,61.5,70,71,69,52,68.5,70.5,69.5,71.5,69.5,71,70.5,69.5,71,70.5,69,72.5,70.5,69.5,69,70.5,70.5,70,72.5,69,70,54.5,70,49.5,38,49.5,71,69.5,69.5,66,72,67.5,71.5,70.5,68.5,70.5,70,70.5,71,49.5,53,70.5,86,68.5,68.5,52.5,69,87.5,70.5,53.5,84,53,88.5,52,68.5,86.5,71.5,55.5,41.5,38.5,72,67.5,53,68.5,67.5,71.5,71,69,68.5,69,70.5,71.5,70.5,70,70.5,69.5,11,0,0,0,0,0,0,33,69,68.5,72,51,69.5,69.5,70.5,69.5,43,42,52,71,69.5,70.5,72,68,71,69,69,69,67.5,72,70,70,71,51,71,71,71,69.5,69.5,70,69.5,71.5,53.5,67,69.5,87.5,62.5,69,58.5,77.5,48,41.5,41.5,82,54,88,71,53,70,71,68.5,71,70.5,51,89,72.5,52.5,70.5,86,73,69.5,71.5,70.5,68.5,68,53.5,88.5,36,69,76.5,69.5,62,80.5,68,69,38,43.5,50.5,78.5,61,71.5,78.5,63,86.5,53,70,88.5,70.5,50.5,88,71,71,70.5,69,43.5,70,71.5,79,68.5,53,80,60.5,81,70.5,50.5,71.5,72,89,59,49,51,46,70.5,50.5,89,69.5,70.5,71,50,52.5,72,87,71,68,54,87.5,70,52,87.5,52,90,69.5,53.5,85,71,69,53.5,71,87,31,0,0,0,0,0,0,0,47.5,86,69.5,70.5,54.5,88.5,69,69.5,68.5,72.5,71.5,68,54.5,70.5,90,34.5,87,70,70,70.5,54,86,70,72,72,68,67,71,53.5,86.5,53.5,45,54,50,71.5,70,50.5,88.5,52.5,52,88,68.5,53,72.5,69.5,88.5,65.5,53,87.5,70.5,62,78,54.5,89]";
		System.out.println(str.split(",").length);
		str = "[0,null,0,1460.76,178.771,172.428,179.216,175.554,172.621,173.56,170.062,181.672,173.681,171.739,174.526,171.536,176.558,173.915,170.887,169.201,174.522,178.933,170.028,170.855,174.234,173.789,177.419,170.987,173.35,170.579,174.904,175.223,271.345,290.963,247.294,178.993,178.165,173,172.55,174,174.799,173.879,173.149,173.292,170.181,174.711,177.672,171.469,175.086,174.5,173.736,179.741,170.63,167.539,174.558,240.1,273.101,188.542,171.531,185.794,208.302,198.862,174.636,172.183,175.986,174.673,177.073,172.794,171.777,170.741,171.842,172.803,174.234,173.59,170.275,173.61,174.029,170.172,173.39,172.144,176.268,172.021,173.652,170.371,170.586,175.964,174.129,169.872,173.55,237.869,316.408,253.111,172.782,174.532,172.971,184.076,172.951,174.578,171.217,172.936,178.161,173.255,172.629,172.879,172.606,178.273,180.274,174.142,174.541,173.022,181.401,173.2,173.775,175.154,170.872,172.542,180.054,172.264,171.559,171.019,171.664,181.301,172.175,207.694,296.928,313.156,178.396,178.281,172.896,173.117,178.533,172.587,170.796,175.261,175.504,175.087,173.22,170.615,170.794,171.571,174.014,176.46,170.545,0,0,0,0,0,0,708.273,175.065,175.657,172.722,174.745,175.986,174.95,172,173.95,269.814,298.155,240.154,171.627,175.007,171.518,170.931,177.257,171.789,171.891,176.036,172.71,180.459,171.521,172.329,171.029,171.324,177.275,173.993,170.049,174.592,173.022,176.777,172.307,172.41,172.042,170.963,178.746,175.345,171.531,175.344,172.58,179.547,176.439,216.292,285.675,297.518,188.543,168.676,171.574,171.43,172.236,172.843,172.521,175.555,170.711,173.199,177.5,171.865,170.745,173.686,172.95,176.86,168.637,170.496,171.888,173.504,174.759,175.235,171.252,170.853,171.181,177.899,176.098,174.18,171.355,169.379,177.647,170.862,271.605,291.609,245.109,174.414,172.049,170.86,171.701,170.349,175.659,171.16,172.279,172.429,171.262,177.762,174,169.69,170.613,172.816,173.804,173.264,172.786,170.993,171.722,177.905,172.811,172.806,175.818,185.605,176.085,173.139,169.643,169.562,170.966,177.703,213.908,293.314,270.424,171.532,181.762,170.376,173.626,171.546,172.38,179.36,174.79,171.535,171.609,172.937,177.596,171.157,173.606,171.164,178.962,174.291,171.25,169.522,172.266,171.028,179.3,172.592,173.116,171.505,170.042,174.73,172.242,0,0,0,0,0,0,0,549.853,177.599,172.662,171.383,171,171.418,176.971,171.763,175.438,170.014,171.028,176.287,170.404,170.766,173.167,172.768,174.201,173.479,172.743,173.121,172.148,174.994,171.321,170.847,171.125,172.912,183.261,172.218,171.159,171.503,173.738,261.278,286.537,244.79,173.14,171.929,178.545,171.718,170.876,173.173,172.341,179.197,172.453,171.014,173.655,172.927,183.282,171.028,174.417,169.624,172.065,176.455,169.495,171.674]";
		System.out.println(str.split(",").length);
		str = "[0,null,0,1469.76,179.771,172.891,179.582,175.942,173.079,173.879,170.299,181.896,173.957,171.944,174.861,171.764,176.79,174.163,171.191,169.444,174.775,179.298,170.371,171.145,174.399,174.016,177.676,171.283,173.608,170.818,175.205,175.525,271.736,291.561,248.069,179.336,178.381,173.25,172.743,174.165,174.971,174,173.388,173.481,170.402,174.893,177.927,171.741,175.388,174.814,173.971,179.993,170.904,167.887,174.804,240.46,273.36,188.809,171.832,186.45,208.621,199.122,175.136,172.542,176.188,174.99,177.38,173.113,172.043,170.958,172.029,173.049,174.539,173.791,170.521,174.121,174.275,170.469,173.66,172.417,176.609,172.191,173.993,170.586,170.772,176.123,174.443,170.156,173.879,238.192,316.737,253.515,172.972,174.871,173.259,184.28,173.104,174.748,171.413,173.142,178.321,173.44,172.879,173.106,172.873,178.697,180.83,174.369,175.099,173.372,181.65,173.495,174.043,175.446,171.113,172.935,180.387,172.585,171.768,171.26,171.847,181.491,172.392,207.928,297.181,313.442,178.597,178.504,173.142,173.314,178.778,173.042,171.056,175.5,175.781,175.312,173.376,170.769,171.043,171.829,174.227,176.748,170.682,0,0,0,0,0,0,708.47,175.217,175.781,173.035,175.059,176.417,175.108,172.504,174.065,270.035,298.369,240.808,171.789,175.338,171.809,171.097,177.515,172.099,172.261,176.29,173.007,180.763,171.729,172.593,171.636,171.556,177.549,174.493,170.254,174.93,173.144,177.194,172.486,172.612,172.252,171.131,179.022,175.64,171.743,175.672,172.79,179.769,176.658,216.604,285.988,297.892,188.97,168.907,171.852,171.711,172.491,173.086,172.718,175.825,170.944,173.362,177.657,172.101,170.91,173.857,173.213,177.012,168.822,170.676,172.084,173.66,174.993,175.485,171.43,171.051,171.417,178.116,176.379,174.54,171.645,169.733,178.044,171.094,271.974,291.724,245.238,174.599,172.23,171.273,171.885,170.46,175.844,171.302,172.507,172.672,171.482,177.941,174.256,169.88,170.887,173.071,173.971,173.448,172.9,171.091,171.873,178.095,173.075,173.081,176,185.735,176.284,173.297,169.846,169.688,171.084,177.788,214.163,293.569,270.62,171.674,181.931,170.584,174.022,171.78,172.549,179.72,175.343,171.722,171.839,173.176,177.978,171.361,173.823,171.5,179.24,174.623,171.462,169.778,172.403,171.196,179.547,172.775,173.457,171.682,170.162,174.92,172.532,0,0,0,0,0,0,0,550.105,177.82,172.914,171.496,171.147,171.65,177.225,172.05,175.686,170.11,171.336,176.397,170.505,171.007,173.378,172.971,174.42,173.643,172.886,173.277,172.333,175.221,171.664,171.062,171.34,173.39,183.448,172.444,171.336,171.723,173.85,261.567,286.852,245.04,173.385,172.207,178.911,171.977,171.01,173.404,172.534,179.496,172.642,171.221,173.813,173.294,183.588,171.208,174.646,169.851,172.226,176.699,169.688,171.893]";
		System.out.println(str.split(",").length);
	}

	private static List<Map<String, Object>> getTps(int interval, int thisDuration, int thisInterval, String tpsStr) {
		if (StringUtils.isEmpty(tpsStr) || tpsStr.trim().length() <= 2) {
			return null;
		}
		thisDuration = Math.abs(thisDuration);
		List<Map<String, Object>> allTpsInfo = new LinkedList<>();
		tpsStr = tpsStr.replaceAll("null", "0");
		String[] tps = tpsStr.substring(1, tpsStr.length() - 1).split(",");
		int total = tps.length;
		int duration = interval * (total - 1);
		int thisTotal = thisDuration / thisInterval + 1;
		int endTime = duration;
		int thisEndTime = duration;
		int diff = thisDuration - duration;
		for (int i = 0; i < thisTotal; i++) {
			if (i == 55) {
				System.out.println("");
			}
			Map<String, Object> thisTps = new HashMap<>();
			allTpsInfo.add(0, thisTps);
			if (endTime <= 0) {
				// 前端补值
				thisTps.put("tps", 0); // 缺省值补0
				thisTps.put("time", getTime(thisEndTime + diff));
				thisEndTime = thisEndTime - thisInterval;
				continue;
			}
			// 从后取数
			if (thisEndTime >= endTime) {
				thisTps.put("tps", Double.parseDouble(tps[endTime / interval]));
			} else {
				while (thisEndTime < endTime) {
					endTime = endTime - interval;
				}
				thisTps.put("tps", Double.parseDouble(tps[endTime / interval]));
			}
			thisTps.put("time", getTime(thisEndTime + diff));
			thisEndTime = thisEndTime - thisInterval;

		}

		return allTpsInfo;
	}

	private static List<Map<String, Object>> getTps1(int interval, int thisDuration, int thisInterval, String tpsStr) {
		if (StringUtils.isEmpty(tpsStr) || tpsStr.trim().length() <= 2) {
			return null;
		}
		thisDuration = Math.abs(thisDuration);
		List<Map<String, Object>> allTpsInfo = new LinkedList<>();
		tpsStr = tpsStr.replaceAll("null", "0");
		String[] tps = tpsStr.substring(1, tpsStr.length() - 1).split(",");
		int total = tps.length;
		int duration = interval * (total - 1);
		int thisTotal = thisDuration / thisInterval + 1;
		int endTime = duration;
		int thisEndTime = duration;
		int diff = thisDuration - duration;
		for (int i = 0; i < thisTotal; i++) {
			if (i == 55) {
				System.out.println("");
			}
			Map<String, Object> thisTps = new HashMap<>();
			allTpsInfo.add(0, thisTps);
			if (endTime <= 0) {
				// 前端补值
				thisTps.put("tps", 0); // 缺省值补0
				thisTps.put("time", getTime(thisEndTime));
				thisEndTime = thisEndTime - thisInterval;
				continue;
			}
			// 从后取数
			if (thisEndTime >= endTime) {
				thisTps.put("tps", Double.parseDouble(tps[endTime / interval]));
			} else {
				while (thisEndTime < endTime) {
					endTime = endTime - interval;
				}
				thisTps.put("tps", Double.parseDouble(tps[endTime / interval]));
			}
			thisTps.put("time", getTime(thisEndTime));
			thisEndTime = thisEndTime - thisInterval;

		}

		return allTpsInfo;
	}

	private static String getTime(long time) {
		if (time <= 0) {
			return "00:00";
		}
		StringBuilder sb = new StringBuilder();
		long minutes = time / 60;
		if (minutes < 10) {
			sb.append("0");
		}
		sb.append(minutes).append(":");

		long second = time % 60 ;
		if (second < 10) {
			sb.append("0");
		}
		sb.append(second);
		return sb.toString();
	}
}
