package com.project.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.KakaoAPI;
import com.project.service.MemberService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

	@Autowired
	private KakaoAPI kakaoAPI;

	@Autowired
	private MemberService memberService;
	/*
	@RequestMapping(value = "/login/getKakaoAuthUrl")
	public String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		String reqUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id=f6901986138e44bdb93305d1621fd37b"
				+ "&redirect_uri=http://localhost:8080/login/oauth_kakao" + "&response_type=code";
		// + "&redirect_uri=http://61.72.99.219:9130/login/oauth_kakao" +
		// "&response_type=code";

		return reqUrl;
	}
	*/
	/*
	 * front에서 code를 받아와서 kakao에 token을 요청한다.
	 */
	@RequestMapping(value = "/login/oauth_kakao", method = RequestMethod.GET)
	public HashMap<String, String> login(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
		
		System.out.println("host확인 : " + request.getHeader("host"));
		String host = request.getHeader("host");
		System.out.println("code : " + code);

		String access_Token = kakaoAPI.getAccessToken(code, host);
		System.out.println("access_Token : " + access_Token);

		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);

		userInfo.put("id", userInfo.get("id"));
		userInfo.put("nickname", userInfo.get("nickname"));

		/*
		 * if (userInfo.get("email") != null) { userInfo.put("email",
		 * userInfo.get("email")); } if (userInfo.get("gender") != null) {
		 * userInfo.put("gender", userInfo.get("gender")); } if (userInfo.get("age") !=
		 * null) { userInfo.put("age", userInfo.get("age")); }
		 */
		System.out.println();

		//MemberVO member = new MemberVO();

		// 기존 회원인지 중복체크
		int cnt = memberService.getMember(userInfo.get("id"));

		HashMap<String, String> result = new HashMap<String, String>();
		if (cnt > 0) {
			System.out.println("이미 가입 된 회원 정보");

			// 멤버번호 가져오기
			int member_num = memberService.getMemberNum(userInfo.get("id"));
			// 클라이언트의 아이디가 존재할 때 세션에 해당 아이디와 토큰 등록
			/*
			 if (userInfo.get("id") != null) { session.setAttribute("userId",
			  userInfo.get("id")); session.setAttribute("access_Token", access_Token);
			  System.out.println("session == :: " + session);
			 }
			 */
			// String token = access_Token.substring(5, 15);
			String token = access_Token;
			result.put("member_num", String.valueOf(member_num));
			result.put("id", (String) userInfo.get("id"));
			result.put("nickname", (String) userInfo.get("nickname"));
			result.put("token", token);
			System.out.println("result" + result.toString());
			
			return result;
		} else {
			memberService.insertMember(userInfo);
			 
			int member_num = memberService.getMemberNum(userInfo.get("id")); 
			 /*
			 if(userInfo.get("id") != null) { session.setAttribute("userId",
			   userInfo.get("id")); session.setAttribute("access_Token", access_Token);
			   System.out.println("session == :: " + session); 
			 }
			 */
			// String token = access_Token.substring(5, 15);
			String token = access_Token;
			result.put("member_num", String.valueOf(member_num));
			result.put("id", (String) userInfo.get("id"));
			result.put("nickname", (String) userInfo.get("nickname"));
			result.put("token", token);
			System.out.println("result" + result.toString());
					
			return result;

			// 토큰 값 전체, 아이디, 닉네임 값 보내는 걸로 수정하기.
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public HashMap<String, String> logout(@RequestParam("token") String token) throws Exception {
		System.out.println("로그아웃 : " + token);
		HashMap<String, String> message = new HashMap<>();

		if (token == null) {
			System.out.println("token null");
			message.put("msg", "token이 없습니다");
		} else {
			System.out.println("token");
			kakaoAPI.kakaoLogout(token);
			message.put("msg", "로그아웃 되었습니다");
		}

		// message.put("title", "로그아웃");
		// message.put("script", "location.href='/'");
		// message.put("type","alert");

		return message;
	}

}