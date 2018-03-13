package com.weizidong.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weizidong.utils.AESUtil;
import com.weizidong.utils.Base64Util;
import com.weizidong.utils.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.restful.api.filter.exception.ResponseCode;
import org.restful.api.filter.exception.WebException;
import org.springframework.stereotype.Service;
import org.wzd.framwork.utils.*;
import org.wzd.framwork.utils.clientUtil.RestClientUtil;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;

/**
 * XXBAutoService
 *
 * @author 魏自东
 * @date 2018/2/9 11:04
 */
@Service
public class XxbAutoService {
    private Random r = new Random();
    private static final String BASE_PATH = "http://app.xuexunbao.com.cn";
    private static final String ACCESS_TOKEN = "08e76ebd52f9ad31a3e434d24f7a9b37";
    private static Map<Integer, String> TOKEN_MAP = new HashMap<>();
    private static PropertiesUtil pu = new PropertiesUtil("/configs/signature.properties");
    private static String privateKey = pu.getProperty("Signature.PRIVATE_KEY");
    private static List<String> nonceList = new ArrayList<>();

    static {
        TOKEN_MAP.put(224, "0a09708194a56d4f8b3c64c0cfd0cd50");
        TOKEN_MAP.put(155, "58f1f593759bfed1e25c4769491c3120");
        TOKEN_MAP.put(208, "cb15c4ff14ca9d0cade473919ace0850");
        TOKEN_MAP.put(159, "e725fe22455eb0d14d78f31b0ca25112");
        nonceList.add("SeJSlGO89fk2sUZ3C%2FtZYKG1JsjFEIykXB8fgVOAOTD%2F3Qoijb4XFbNLoYrtH%2BIiNZVCgJLQxwP3PPuBw%2FNpCWmpwt9rs8uX2X7dfeYgr0l8uYLQmQKo4qZmf4QXebbDGUEc9fQh9T0XQB%2BFy%2Fb21gCztGiFJdn5qYMWgrImmH0%3D");
        nonceList.add("XKSBaGw4WEHo3H4tj1V25Gn8BCgkgDiX%2BAGvXIrmQlPGBFNt2MiVk7pwH6RLsKCUHTY%2B97F4iKkfgXP2fJMW3fVJicF2eWbap1za78unKUa3BjB5NBUHEMt5VjV9QcBqK6OLdfNuV%2B5tLC31LN50SJ%2BKL4m1s4zp65KnzYxCPao%3D");
        nonceList.add("dxPTJz81dP%2FaNnND%2FFedT5xkGeC1om%2FaMdp7xtzgBx5ngMU0p4I4zCzZTwBnzVoOPZjwbdr%2Fk6SwWdUpIzbdFf07mfkU4IefVYJsRHmTrMLlQSUHIsAv%2BLXtde%2FxNgbuowVtdiaxK1ejTIWLcbjbK7p7qxvL9SW0slaxa6DTnw4%3D");
        nonceList.add("GgItfrAKLmmqrtOmrtRpE1rnxgAfReJ%2BdT10hL8oE6Q25K1DpPHmRb%2F8zHee2kNKbjN6O8xST27mntesRDSyja8dmTIXlRbtAcneYgD0NAwKZQuFkyk6vOy81rS0pX%2Frg99Tn0LD0LfQXAe3OvR6cdxiYuzfirG9YW8RIKPUDlw%3D");
        nonceList.add("ReV9kPysDBS4axnyO%2FJUc8rNqEi0n4pR4sM4YnvFcGBCbXISFsmJjOQ9%2BrHvTK6ipQru8hwzDhKuHPC%2Fc1SFXkz3%2BGYWP7cUWw8U3eCjklulB9kGTQlzjJYmn2ig2cxFzbLCQF61uLn1X94tDMUT8OD11PDHWX5CY9FUhK5Auyw%3D");
        nonceList.add("CiC81xy2q%2BP%2FIeDUzY1XHpf6J5n0YVrZZ5d3YkzTc4VJOsZ6082QNsRUHOgmotu73E6Ft0BR8%2BVUZWAGf7tLWJA1%2FDz67mzCwkcfyXS3qNzLmqDkJ0%2FigsbNzedeASQgEd68dRPHmeMYhNh1cn%2FLilK0nfOW%2BbGFFzXFZeKtuew%3D");
        nonceList.add("QG6i3PId2ahDgkA3jBMQqfEz%2BWdrlN5UXSBge9h9sPHYzAqGUSWGGb%2BnafzCkCPV3N8W9%2BCk4ZvnZsq2HMNNyOcGQUucL2XBh3DXuVgEIP04rb7BLNnkf1WP1wpjotBxbr8uSL4MH3RemcH95xv%2BcRnOGn640ULjOUREoEFnojA%3D");
        nonceList.add("pAYh6aZ5zQmeNhLOu7%2BH%2Bjrm%2B3Za5AJXXuZyTbVCyKrEDYon%2FKd3fjF6nSEvz3S3MWpmdzJ2TME8%2B1BbSZ3UoLzQ3bgVwBpLzZXkde4BJ9bIsa8R5kU3MmMiHokRTa5VoNyB9G6Lcv%2FFQcAKyuPFZEOMy%2BualHgkp87DXS6s2vI%3D");
        nonceList.add("sH0p994bFIkv%2FMAcz2TdZpcf%2FdvufyMwY9HaoaxvShVfUxP8JI67lSSHXZkxV%2B9MvDEKvKubIZvW3XcrYUPqrcvt%2B%2B065aoMDr7s8xZnZ4Vcxi4kRL4yEKSRA5U3nwR8ya3WQB6whtnlJjIhFsiFgXNeVKWC7CO7m5aOCwzV25c%3D");
        nonceList.add("ohF0wliV%2Bxbdbi%2B4SA%2FnVZGD1hGNEEQPt7gk47a72RqskR5N2nepGvWhF8JvLFbqsoBA5ymS0hse1jVgql8adjAQKfeIJNvLpTDi7iZxMrfQxC0ixzaHeqa6wDCm9B3h4oBVkfwaE%2BkjHZigBXN7LmhSBwuOVeWFp8o6ZLXSHGs%3D");
        nonceList.add("rgpc8uMElSD6FU%2BDUz4dAsP0wzP%2BKQ0%2F5U0YDNuZHsh2%2ByPEdCpie44R4D9fXh2SbFNqRr3G%2BogUziPxRMQ%2BT64c0uGAC8o4xmO8wF57l3TXhEXyMTdqD47Bq%2Bxunj3GLvhnvAChWALBfuedasCRvWGPb9x88j8fIkumi9iGrmo%3D");
        nonceList.add("lgFnEXW8Yzy7RM0ks3t9zPEHOxpObaMSfux6v3bnw9x0RFxruN1NUiuVyj6jFAQBOJk5uPScz%2B1Y43RXBfjbT%2FH722RKZQtcpwSEY%2BMsQ7GjEqb6aCB9t9A3l%2BzltKet8E3Ah%2BOLUycXgVb8l7QneQnkWTcVax8XCH%2FY8vy1Rh0%3D");
        nonceList.add("LjID8CIM6lB1RQ5cEiAE0iIPvtktMsYcndxQY4IW3hzk04n4lMtn2xbvPptezzi7VHcLtghVOGJdwUVUO476jHwGxCW8i%2BMt2LG1DPJU5r7ETP%2BzANBym%2F6BZIi8f26d2BOooRQhpcoODJHttMGoXP128D3ImtoW%2BHZBbIodqt8%3D");
        nonceList.add("etQ7onZaXnAYb%2FErjnPjb3sPVCZGAf%2BQuJd3lHWHyaw4gTVImMQAirHWMFC36%2FxasXEYyGvMKIPKzK6tpgGnIWoekiHKJTov4oE0E6%2BsaPA0nMKOgsqvLbkkjLoNrcMfYmNzxpaBS5WgMVagm%2F0AdYcl0f2E49YsJKadDnh4vD4%3D");
        nonceList.add("m2vMdbD4ypIp6GFjKROE0Qt1QtGnQPoylpf4ny%2BmCnuwHVrBPhD9LFCovC%2Btf7VEnTrY4jHkIJlc2BNk21vtIXpdobVi08v3vjNDzWnK4wi%2FuyBmWQ1%2BdGZT6dUI3Ov%2BcrR75fG%2FkBwvHn19prZpwPa9ntbJOKUZFpcgHoC0R2o%3D");
        nonceList.add("pDrh8Xx2sNwsOwPnI7V7mGspnnaAavkZtRu5LH4gxxjcdHH4xHGB3GsGcn4NKlfetVAGfLa8kiE5M%2Fi2TPG%2FtaWz6lJVwLtmfxHuifh%2FnZ9ybAmefrsWW%2B894slYMzzgKlm8PiZZEPyeslMIEDmO97d%2BNyy8P8LVT%2BFPIbSrUZ8%3D");
        nonceList.add("jd%2BxG6zzx%2BmNeVuspFsFGJ1AHJePD6jvXfOIpB3tQuFITZMPft99dSgGmPk6bCD7BkzMTTScerF3oQyHO%2FiYtCujkGC5GKYpYl0lsT%2BS741%2FeeZCDNjlCcXVMNCFaO7AqokWUMQUBrbWavw8FEnmKbiHIXKRG9PoaH6%2BlEi4pUc%3D");
        nonceList.add("sUEbc%2BtBo4S8ALfAM8FsJArAVpXC23r%2FYxw2cgHUN0dIbjtyImrsNzE5krlaJvHKn7HB6Cx5hSk4uv%2BKEpSHYJjyzViP2pgYjPOCr6DjVfdi%2Ba%2BD7%2FfPCcBtRK2FPyKRdQ5SjjvfqDZnKVyRKUJHJKSaz0HSmPsogG3CtOz73Pg%3D");
        nonceList.add("lZmQLm6Gww93Qkytd32Tt%2B5yyACJckYGziXAMAx6Z1yoXSbRFZWen1LbRC6WCUEienF4E41Ie8b1RiDWBzBD5LFTNwyg8NQRdiECONfaSJuw8rM7%2FRRW0BLbA0BAwFGKv6Zqm048T2H6sHAmWCABHRuc2McfNUJk6bqfXOXmu48%3D");
        nonceList.add("UqsdJX7uObFrIrmbq5pS8lxYCCd3iw49KSA8ISg0ABR18hdWhxFnfiPdDQZWaCUoZABta7W7XmZIZ5SAZvYw8%2BxgpK6gadG%2F%2BCWsxwm4%2BiUFOok7o%2ByjCTJHwZ0tx4oMfsZVSF0JTbZeFksjvNbq9qdZit%2Bl15AxDSipMZhYt7o%3D");
        nonceList.add("BArCZ9RN4MyEC4rkqUZV0K8MOIBXL00vlqk8Gfs5JWIuI5x5zSgd%2Fx8tMaWdJ3PnLWQ%2B%2B4tgjNPyK03xfC6i94Aac6blyVKPPrZMuP7A2jIjndo2b3ob5N21%2Bhr9LZzZZAOrJFg0TZKVaXKtJf9NtKtoOb5qkSJRW2R5%2FBOaJqU%3D");
        nonceList.add("THvhGQrawIqmBQQMFHa8vB0LbqQINjAqEIQvRNFHKZoLpIlrX1C1aR4g%2Fs614aECnAWbRXl9FNxkWs0Jz0wzDZBWKJpouLonPx4ipWBFt4%2BOxqX6fpFbRVGQzo%2FGiFTHMlTLjPoUBkQ436AY5MrWvgbexY4YiRdkTS230UTe6eE%3D");
        nonceList.add("ezOXvsMdbckG23x1bVaBvjZQ9Mt3IWVfWVrWpmkaYQLB5MskZAc8jc8N2SVznQIV%2BvChewckJtqhkbTH0Sw3L13KtWc%2BQq103ereSQrabn3Lp1hzBTSJxbV7y7Bd06RCw8RVY%2F3FwBpkrRYo2YFSLrWYAm%2FWifWQPjTMhVYTNaA%3D");
        nonceList.add("BlQEHFjlU%2F4%2FXbOrbmRCQrzkuXzcVdw3W%2BgOIMkwJo7%2BGy4FcnGZR11oFf5ExQYQsann4BrBFYwLoWEzXqMbIPgwdqKB51FRztBU4dTlihSl7OBla%2BbwqwU%2BYVBlotlrpP4WKpiI1bGPiO5bsJNQjVhTqPtXZt9XNa3HlsDiSeg%3D");
        nonceList.add("NjNMhOhHpvs3FzPcUtWCOFUdw0qrwmcSwBhUMN7nuzC8KEYT2GTJDpFUl%2B7AQnbv4r6wmfvSVdhGSwOM%2BLK1Z4XXQEiQHbKcuqQAoGRD9d4OIE04%2B8RgbDlMPT3qD5wcYM4%2BLRXvU8IQ0NrFtiijhk1Jfq602nmXczvnr5IYQ4I%3D");
        nonceList.add("ouUDVhfJdj5BIJ5lqEssrDV9riWLL4bh%2BlsCuREHzfGees05DdIn%2FUO6w5L9oj440lvEjvgSSvs2XxzyTLlbZJBvg1E45vTHfqaclL%2BD9E3YubmKW36ua5xtuimcu3cwAF2HaY83BSTtVRWA%2F7bg4uvyl93QI8zxv2dk9zy8lG0%3D");
        nonceList.add("qpnX9v%2FcmS32TKpCU4gNpIsy0Tnois6YMlPHoly6jOyirb%2B77LvElT1P0gK7PIwqKGW8LHOnBXvT11PFog0WXzN4bF8iL%2Flp1%2F4dJwiACcPdaiap6nJry6n1quJi%2B1nOo8bmLn3%2F0ts7C5AK2HDUxbUOHOc2TwTW77fgJewi2Hk%3D");
        nonceList.add("MxnKewOH1croxCmH6bVbkWxt9l2rPJtY5O6QOacW4ZDJ8JBx9Di1p3vXg6Lbo3LhnpWDWeDs%2F35NYuL3W0pmnb7agMFe%2BwpmqCfIztYli0%2BZGkE7XvEUkskliFFvcP74ufQRW96TMd59XikC7YNCLYkzKNrn5%2F%2F%2BPZf%2BbJldG2M%3D");
        nonceList.add("Ytzbx38QIDESnDEJxypWIcQhlHSlTAyeGU7NwGAx%2FWyf6YrYK2hdc8GIEdV7CFtsu7dZoZk%2F4YPvq%2FmQGAkkeFuv67vozeFvFbysE7KKDtHcPYYP7Gr8oYUlSyDjingMCRbg8E943BvPhPuMI%2FOxpPtAZT%2Bq59U34ddBJf2LgJA%3D");
        nonceList.add("j0eezii08s%2BP1sLzIHu5XhcfAIQjlMFs%2Fy4SHLkyHrYvvEwIzkebOjdUKg9kgrgxxweNVAqHIfZLhdFH5CfmQOu77hItzw0fO8wRj2%2Bkvk5Xv27k6nGSLzH0kFOLM9xDYjGyJUiz46Ek5jAUiHkLU6VZeZhSFraOPW11VJWotWQ%3D");
        nonceList.add("X7xEHfyPjpi2iXluZ7qzinPwG5BVwKRQfwdvpIizepzBRV%2F1hukH9pYEt1XZ0UVmt92a0Ii0hRM4WbK73W6JvR%2BdRlj0gMEVLrB1b77PiHZFEL%2BoGGDBM%2FEYMI87VkTHk762oY4mMzZS5y9N0Slk9uZctGxtLjtqFb8lY2Qxu0I%3D");
        nonceList.add("c4KlqdMOR8copbO%2FWDl5dEpwp5zb7FJK05oN%2B4Jnbk7Grx7j6olhpdAhXYUwzWbT0pl8NlCd%2F7MEOPQEeUj8rMjYPNaNMWLC3qnu2rHjsbm53t0Ysqzvc5%2FpFP9CAd8bTKxdQ%2BgYqwLa1dQG3B4MAjyFddLBsHb%2BlVwLkNeFT5k%3D");
        nonceList.add("Dbd8DcVP263OHWG1zgEC0aFDMHUcrH0tBx5P6jrE%2BQsHkD2dEkEkI3xVjhv%2FVaMxa64AnGBOqbnyOtyvQwav5udrSgC0Jc3jmqgdtNwfw2BVP9Hr3xc%2BTiAMyFFNBzsslSQOr%2BsoMcziv98wadMfzl11eVeiCrEWhIClOhZR73M%3D");
    }

    /**
     * 学训宝自动化
     *
     * @param teacherId  教师ID
     * @param studentId  学生ID
     * @param courseName 课程名称
     * @param courseImg  课程图片
     */
    public void doMain(Integer teacherId, Integer studentId, String courseName, String courseImg) throws InterruptedException {
        LoggerUtils.info(getClass(), "--------------------------------------------------------------start:    " + teacherId);
        // 提现
        createCash(teacherId);
        // 创建课程
        createCourse(teacherId, courseName, courseImg);
        // 等待
        Thread.sleep((r.nextInt(5) + 6) * 1000);
        // 约课
        createSubscribe(teacherId, studentId);
        // 处理约课
        findSubscribe(teacherId);
        LoggerUtils.info(getClass(), "--------------------------------------------------------------end:    " + teacherId);
    }

    /**
     * 处理约课
     *
     * @param teacherId 教师ID
     */
    private void findSubscribe(Integer teacherId) {
        JSONObject resp = RestClientUtil.postJson("http://manage.xuexunbao.com.cn/rest/subscribe/list?ACCESS_TOKEN=" + ACCESS_TOKEN,
                new JSONObject().fluentPut("status", 0).fluentPut("teacherId", teacherId).fluentPut("page", 1).fluentPut("pageSize", 10), JSONObject.class);
        List<JSONObject> list = resp.getJSONObject("data").getJSONArray("list").toJavaList(JSONObject.class);
        for (JSONObject obj : list) {
            String param = "<xml><return_code><![CDATA[SUCCESS]]></return_code><result_code><![CDATA[SUCCESS]]></result_code><out_trade_no><![CDATA[" + obj.getString("orderNumber") + "]]></out_trade_no><transaction_id><![CDATA[42000000" + MathUtil.getRandomString(2, "5678") + DateUtil.formatDate(new Date(), DateUtil.PDATETIME) + MathUtil.getRandomString(6, "0123456789") + "]]></transaction_id></xml>";
            String json = RestClientUtil.buildWebTarget(BASE_PATH + "/rest/order/payback").request(MediaType.TEXT_XML_TYPE).post(Entity.entity(param, MediaType.TEXT_XML_TYPE), String.class);
            LoggerUtils.info(getClass(), "约课处理结果：" + json);
        }
    }

    /**
     * 约课
     *
     * @param teacherId 教师ID
     * @param studentId 学生ID
     */
    private void createSubscribe(Integer teacherId, Integer studentId) throws InterruptedException {
        JSONObject resp = RestClientUtil.postJson(signPath("/rest/course/getTeacherCourseDetail/" + teacherId + "/0", TOKEN_MAP.get(teacherId)),
                new JSONObject().fluentPut("page", 1).fluentPut("pageSize", 10), JSONObject.class);
        List<JSONObject> list = resp.getJSONObject("data").getJSONArray("list").toJavaList(JSONObject.class);
        for (JSONObject obj : list) {
            LoggerUtils.info(getClass(), "约课的课程：" + JSON.toJSONString(obj));
            resp = RestClientUtil.postJson(signPath("/rest/order/createSubscribe", TOKEN_MAP.get(studentId)),
                    new JSONObject().fluentPut("courseId", obj.getInteger("id")).fluentPut("payType", 1).fluentPut("deviceType", 1), JSONObject.class);
            LoggerUtils.info(getClass(), "约课结果：" + JSON.toJSONString(resp));
            Thread.sleep((r.nextInt(5) + 6) * 1000);
        }
    }

    /**
     * 创建课程
     *
     * @param teacherId  教师ID
     * @param courseName 课程名称
     * @param courseImg  课程图片
     */
    private void createCourse(Integer teacherId, String courseName, String courseImg) throws InterruptedException {
        Date d = DateUtil.getCurrentDayStartTime();
        for (int i = 10; i < 20; i += 2) {
            Long starTime = d.getTime() + i * 3600 * 1000;
            Long endTime = d.getTime() + (i + 2) * 3600 * 1000;
            JSONObject param = new JSONObject();
            param.put("courseName", courseName);
            param.put("grade", 22);
            param.put("starTime", starTime);
            param.put("endTime", endTime);
            param.put("longitude", "114.421432");
            param.put("latitude", "30.471236");
            param.put("courseImg", courseImg);
            param.put("courseLabels", "快速学习");
            param.put("itemId", 91);
            JSONObject resp = RestClientUtil.postJson(signPath("/rest/course/createCourse", TOKEN_MAP.get(teacherId)), param, JSONObject.class);
            LoggerUtils.info(getClass(), "创建结果：" + JSON.toJSONString(resp));
            Thread.sleep((r.nextInt(5) + 6) * 1000);
        }
    }

    /**
     * 提现
     *
     * @param teacherId 教师ID
     */
    private void createCash(Integer teacherId) {
        JSONObject resp = RestClientUtil.get(signPath("/rest/user/getUser", TOKEN_MAP.get(teacherId)), JSONObject.class);
        BigDecimal money = resp.getJSONObject("data").getBigDecimal("money");
        LoggerUtils.info(getClass(), "可提现金额：" + money);
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            resp = RestClientUtil.postJson(signPath("/rest/account/createCashRecord", TOKEN_MAP.get(teacherId)), new JSONObject().fluentPut("cashMoney", money), JSONObject.class);
            LoggerUtils.info(getClass(), "提现结果：" + JSON.toJSONString(resp));
        }
    }

    /**
     * 加密请求
     *
     * @param url   请求地址
     * @param token token
     * @return 加密后的url
     */
    private String signPath(String url, String token) {
//        if (StringUtils.isBlank(key) || System.currentTimeMillis() - keyTime > 12 * 3600 * 1000) {
//            JSONObject resp = RestClientUtil.get("http://manage2.jhtswkj.cn/rest/system/get/ad5f82e879a9c5d6b5b442eb37e50551", JSONObject.class);
//            if (resp.getInteger("code") != 200) {
//                throw new RuntimeException(resp.getString("msg"));
//            }
//            key = resp.getString("data");
//            keyTime = System.currentTimeMillis();
//        }
        if (StringUtils.isBlank(token)) {
            token = "0";
        }
        String ts = MD5Util.getMD5ofStr(String.valueOf(System.currentTimeMillis()));
        Integer index = Integer.valueOf(pu.getProperty("index"));
        if (index >= nonceList.size()) {
            index = 0;
        }
        String nonce = nonceList.get(index);
//        String md5Token = MD5Util.getMD5ofStr(token, time);
//        String str = "u=" + url + "&t=" + ts + "&n=" + nonce + "&k=" + key + "&token=" + md5Token;
//        String sign = MD5Util.getMD5ofStr(str, time);
        String sign = getSignature(nonce, ts);
        index++;
        pu.writeProperties("index", index.toString());
        return BASE_PATH + url + "?ACCESS_TOKEN=" + token + "&nonce=" + nonce + "&ts=" + ts + "&sign=" + sign;
    }

    /**
     * @param ts 随机字符串
     * @return 成功返回授权签名，否则返回空
     */
    private static String getSignature(String nonce, String ts) {
        try {
            if (nonce.indexOf("%") > 0) {
                nonce = URLDecoder.decode(nonce, "UTF-8");
            }
            byte[] decodedData = RSAUtils.decryptByPrivateKey(Base64Util.decodeString(nonce), privateKey);
            String passwordKey = new String(decodedData);//得出密码表
            String beforeString = passwordKey.substring(0, 5);//前五位
            String afterString = passwordKey.substring(11, 16);//后五位
            String key = MD5Util.getMD5ofStr(MD5Util.getMD5ofStr(beforeString + afterString) + "Anner2018");//AES密钥
            return AESUtil.encrypt(key.substring(8, 24), ts);
        } catch (Exception e) {
            throw new WebException(ResponseCode.不允许此方法, "加密错误！");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < nonceList.size(); i++) {
            System.out.println(i + 1);
            System.out.println(nonceList.get(i));
            System.out.println(getSignature(nonceList.get(i), "123"));
        }
    }
}
