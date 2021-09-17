package com.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.common.model.vo.Constants;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: Jwt工具类</p>
 * <p>Title: WebJwtToken.java</p>
 * <p>version 1.0  目前实现较简单的方式 ，使用服务端公钥 </p>
 * <p>Company: 翊廷科技西安研发中心</p>
 * @author jky
 * <pre>Histroy:
 *          2021年05月11日 上午11:48:06  jky  Create
 *</pre>
 */
public class JwtToken {
	/**
	 * 公用密钥-保存在服务器，客户端不会知道，以防攻击
	 * **/
	public static String SECRET = "demoWeb";
	
	/**
	 * <p>Description: 生成token</p>
	 * <p>Function: createToken</p>
	 * <p>return value:String</p>
	 * <p>@param userId  用户uuid
	 * <p>@param user 用户json格式对象
	 * <p>@return
	 * <p>@throws Exception</p>
	 *
	*/
	public static String createToken(String userId,String userName,String roleIds,String orgUuid,int CalendarType,int time) throws Exception{
		//签发时间
		Date iatDate = new Date();
		
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(CalendarType, time);
		Date expiresDate = nowTime.getTime();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
        
		String token = JWT.create()
				.withHeader(map)
				.withJWTId(SnowflakeIdUtil.newStringId())//设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
				.withClaim(Constants.USERID, userId) //payload
				.withClaim(Constants.USERNAME, userName)
				.withClaim(Constants.ROLEIDS, roleIds)
				.withClaim(Constants.ORGUUID, orgUuid)
				.withSubject(userId)   //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
				.withExpiresAt(expiresDate)  //设置过期时间 --过期时间要大于签发时间
				.withIssuer("demo")  //签发者
				.withIssuedAt(iatDate) //设置签发时间
			//	.withNotBefore(new Date())  //失效时间
				.sign(Algorithm.HMAC256(SECRET));  //加密 算法及密钥
		return token;
	}
	
	/**
	 * <p>Description: 解密 Token 自定义部分</p>
	 * <p>Function: verifyToken</p>
	 * <p>return value:Map<String,Claim></p>
	 * <p>@param token
	 * <p>@return
	 * <p>@throws Exception</p>
	 *
	*/
	public static Map<String,Claim> verifyToken(String token) throws Exception{
		JWTVerifier verifty = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifty.verify(token);
		} catch (Exception e) {
			throw new RuntimeException("登录凭证已过期，请重新登录！");
		}
		return jwt.getClaims();
	}
	
	
	
	/**
     * 解密token
     * @param token
     * @return
     * @throws Exception
     */
	public static DecodedJWT parseJWT(String token) throws Exception{
		JWTVerifier verifty = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifty.verify(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jwt;
	}
	
	
	
	/**
     * 校验token
     * @return
     * @throws Exception
     */
	public static boolean validToken(String token){
		boolean flag = false;
		if(StringUtil.isEmpty(token)){
			return false;
		}
		try {
			JWTVerifier verifty = JWT.require(Algorithm.HMAC256(SECRET)).build();
			verifty.verify(token);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
     * 由字符串生成加密key
     * @return
     */
    public SecretKey generalKey(){
        String stringKey = "7786df7fc3a34e26a61c034d5ec8245d";//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
        System.out.println(encodedKey);//[B@152f6e2
        System.out.println(Base64.encodeBase64URLSafeString(encodedKey));//7786df7fc3a34e26a61c034d5ec8245d
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return key;
    }
	
	
	public static void main(String[] args) throws Exception {
		String uuid = SnowflakeIdUtil.newStringId();
		String userName = "admin";
		String roleIds = "'1'";
		String token = JwtToken.createToken(uuid,userName,roleIds,null,Calendar.MINUTE,60);
		System.out.println("Token:"+token);
		System.out.println("******************"+validToken(token));
		Map<String,Claim> claim = JwtToken.verifyToken(token);
		System.out.println(claim.get(Constants.USERID).asString()); 
		
		//使用过期后的Token进行校验
		String tokenExpire = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIxMTEiOiIxMTExIiwiZXhwIjoxNTI4MDkzNjI2LCJpYXQiOjE1MjgwOTM1NjZ9.5AyUN4FRvj2aW5ouftyfOQYvTwQGMO77hLff5oiRUX0";
		System.out.println("###################"+validToken(tokenExpire));
		Map<String,Claim> claimExpire = JwtToken.verifyToken(tokenExpire);
		
		
	}
	
	
	
	
	
}
