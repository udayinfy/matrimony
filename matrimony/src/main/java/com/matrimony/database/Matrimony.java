package com.matrimony.database;

import com.matrimony.entity.Friend;
import java.util.List;
import java.util.stream.Collectors;

import com.matrimony.entity.User;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class Matrimony {

    public static List<User> getSuggestUsers(User user) {
        List<User> listSuggest = new ArrayList<>();
        List<User> users = UserDAO.allUsers().stream()
                .filter(u -> !u.getId().equals(user.getId()) && !u.getGender().equals(user.getGender()))
                .collect(Collectors.toList());
        for (int i = 0; i < users.size(); i++) {
            if (FriendDAO.GetFriend(user.getId(), users.get(i).getId())==null) {
                listSuggest.add(users.get(i));
            }
        }
        System.out.println(users.size());
        return listSuggest;
    }

    public static void main(String[] args) {
        // List<User>lst1=UserDAO.allAccounts();
        // System.out.println(lst1.size());
        // lst1.forEach(u->System.out.println(u.getUserId()));
        //
        // List<User>lst2=lst1.stream().filter(u->!u.getUserId().equals("990258dd4fcaf596014fcaf5a0d90000")).collect(Collectors.toList());
        // System.out.println(lst2.size());
        // lst2.forEach(u->System.out.println(u.getUserId()));
    }
}
