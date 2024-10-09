package com.example.bookStore.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStore.entity.Users;
import com.example.bookStore.service.UsersService;
import com.example.bookStore.util.TokenUtility;


@RestController
@RequestMapping("/bookStore/auth")
public class UsersController {

	@Autowired
	private UsersService uService;
	
	@Autowired
	private TokenUtility tokenUtility;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody String json) {

		JSONObject reqJsonObj = new JSONObject(json);
		String phoneNumber = reqJsonObj.isNull("phoneNumber") ? null : reqJsonObj.getString("phoneNumber");
		String password = reqJsonObj.isNull("password") ? null : reqJsonObj.getString("password");
		String userName = reqJsonObj.isNull("userName") ? null : reqJsonObj.getString("userName");
		Users users = uService.createUsers(phoneNumber, password,userName);

		return ResponseEntity.ok("註冊成功");
	}
	
	 @PostMapping("/login")
	    public String login(@RequestBody String json) {
	        JSONObject responseJson = new JSONObject();

	        JSONObject reqJsonObj = new JSONObject(json);
	        String phoneNumber = reqJsonObj.isNull("phoneNumber") ? null : reqJsonObj.getString("phoneNumber");
			String password = reqJsonObj.isNull("password") ? null : reqJsonObj.getString("password");


	        Users dbUser = uService.login(phoneNumber, password);

	        if (dbUser == null) {
	            responseJson.put("success", false);
	            responseJson.put("message", "帳號或密碼錯誤");
	        } else {
	            responseJson.put("success", true);
	            responseJson.put("message", "登入成功");

	            JSONObject user = new JSONObject()
	                    .put("userId", dbUser.getUserId());

	            String token = tokenUtility.createEncryptedToken(user.toString());
	           
	            responseJson.put("token", token);
	            responseJson.put("userId", dbUser.getUserId());
	        }

	        return responseJson.toString();
	    }
	
}
