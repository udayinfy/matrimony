/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.dialect.FirebirdDialect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matrimony.database.FriendDAO;
import com.matrimony.database.Matrimony;
import com.matrimony.database.UserDAO;
import com.matrimony.entity.Friend;
import com.matrimony.entity.User;

/**
 *
 * @author anhdh_a05370
 */
@Controller
public class FriendController {

	@RequestMapping(value = "makeFriend", method = RequestMethod.POST)
	@ResponseBody
	public String makeFriend(HttpServletRequest request, String userBeInviteId) {
		User ssUser = (User) request.getSession().getAttribute("user");
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		System.out.println(ssUser.getName() + " mời " + userBeInviteId + " làm bạn");
		
		Friend friend = new Friend();
		friend.setState("WAITTING");
		friend.setUserInvite(ssUser);
		friend.setUserBeInvite(UserDAO.findById(userBeInviteId));
		friend.setTimeInvited(currentTime);

		FriendDAO.addFriend(friend);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "acceptFriend", method = RequestMethod.POST)
	@ResponseBody
	public String acceptFriend(HttpServletRequest request, String userInviteId) {
		User ssUser = (User) request.getSession().getAttribute("user");
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Friend friend=FriendDAO.getFriendByUsers(userInviteId, ssUser.getId());
		System.out.println("day la friend"+friend);
		friend.setState("FRIEND");
		friend.setTimeAccepted(currentTime);
		FriendDAO.update(friend);
		return "SUCCESS";
	}
	
	@RequestMapping(value = "denyFriend", method = RequestMethod.POST)
	@ResponseBody
	public String denyFriend(HttpServletRequest request, String userInviteId) {
		User ssUser = (User) request.getSession().getAttribute("user");
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		Friend friend=FriendDAO.getFriendByUsers(userInviteId, ssUser.getId());
		System.out.println("day la friend"+friend);
		friend.setState("DENY");
		friend.setTimeAccepted(currentTime);
		FriendDAO.update(friend);
		return "SUCCESS";
	}

	/* HOANG ANH CODE */

	// @RequestMapping(value = "listSuggest", method = RequestMethod.GET,
	// produces = "application/json; charset=utf-8")
	// @ResponseBody
	// public String listSuggest(HttpSession ss) throws IOException {
	// List<User> listSuggest = new ArrayList<User>();
	// User u = (User) ss.getAttribute("user");
	// List<User> list = Matrimony.getSuggestUsers(u);
	// for (int i = 0; i < list.size(); i++) {
	// if (!FriendDAO.CheckExist(u.getId(), list.get(i).getId())) {
	// listSuggest.add(list.get(i));
	// }
	// }
	// ObjectMapper mapper = new ObjectMapper();
	// String data = mapper.writeValueAsString(listSuggest);
	// System.out.println(data);
	// return data;
	// }
	@RequestMapping(value = "allInvite", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String allInvite(HttpSession ss) throws IOException {
		User u = (User) ss.getAttribute("user");
		List<User> list = FriendDAO.ListInvite(u.getId());
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(list);
		System.out.println(data);
		return data;
	}

	@RequestMapping(value = "allRequest", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String allRequest(HttpSession ss) throws IOException {
		User u = (User) ss.getAttribute("user");
		List<User> list = FriendDAO.ListRequest(u.getId());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).getId();
		}
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(list);
		System.out.println(data);
		return data;
	}

	@RequestMapping(value = "allFriend", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String allFriend(HttpSession ss) throws IOException {
		User u = (User) ss.getAttribute("user");
		List<User> list = FriendDAO.ListFriend(u.getId());
		ObjectMapper mapper = new ObjectMapper();
		String data = mapper.writeValueAsString(list);
		System.out.println(data);
		return data;
	}

	// @RequestMapping(value = "addFriend", method = RequestMethod.POST)
	// @ResponseBody
	// public static String addFriend(String id, HttpSession ss) {
	// User u = (User) ss.getAttribute("user");
	// int status = 1;
	// Friend f = new Friend();
	// f.setUserInvite(u);
	// User toUser = new User();
	// toUser.setId(id);
	// f.setUserTo(toUser);
	// f.setStatus(status);
	// f.setTimeInvited(new Timestamp(System.currentTimeMillis()));
	// FriendDAO.addFriend(f);
	// return "success";// mo database ktra lai xem
	// }

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public static String remove(String user, HttpSession ss) {
		User u = (User) ss.getAttribute("user");
		Friend f = FriendDAO.GetFriend(u.getId(), user);
		FriendDAO.removeFriend(f);
		return "success";
	}

//	@RequestMapping(value = "acceptFriend", method = RequestMethod.POST)
//	@ResponseBody
//	public static String acceptFriend(String user, HttpSession ss) {
//		User u = (User) ss.getAttribute("user");
//		FriendDAO.AcceptFriend(u.getId(), user);
//		return "success";// mo database ktra lai xem
//	}
}
