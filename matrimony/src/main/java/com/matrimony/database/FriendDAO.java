/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrimony.database;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.matrimony.entity.Friend;
import com.matrimony.entity.User;
import com.matrimony.util.HibernateUtil;
import java.util.ArrayList;

/**
 *
 * @author SON
 */
public class FriendDAO {

    public static void addFriend(Friend friend) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.save(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static void removeFriend(Friend friend) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        ss.delete(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static void AcceptFriend(String nameFormId, String nameToId) {
        Session ss = HibernateUtil.openSession();
        ss.getTransaction().begin();
        Query query = ss.createQuery("FROM friend WHERE userFromId=:userFromId and userToId=:userToId");
        query.setParameter("userFromId", nameToId);
        query.setParameter("userToId", nameFormId);
        Friend friend = (Friend) query.uniqueResult();
        friend.setStatus(2);
        ss.update(friend);
        ss.getTransaction().commit();
        ss.close();
    }

    public static List<User> ListFriend(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status");
        query.setParameter("status", 2);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            if (nameFormId.equals(list.get(i).getUserFromId())) {
                User u = FriendDAO.getUserById(list.get(i).getUserToId());
                listUser.add(u);
            }
            if (nameFormId.equals(list.get(i).getUserToId())) {
                User u = FriendDAO.getUserById(list.get(i).getUserFromId());
                listUser.add(u);
            }
        }
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        for (int i = 0; i < listUser.size(); i++) {
            System.out.println(listUser.get(i).getId());
        }
        session.close();
        return listUser;
    }

    public static List<User> ListRequest(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userFromId=:userFromId");
        query.setParameter("userFromId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            User u = FriendDAO.getUserById(list.get(i).getUserToId());
            listUser.add(u);
        }
        session.close();
        return listUser;
    }

    public static List<User> ListInvite(String nameFormId) {
        List<User> listUser = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE status=:status and userToId=:userToId");
        query.setParameter("userToId", nameFormId);
        query.setParameter("status", 1);
        List<Friend> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            User u = FriendDAO.getUserById(list.get(i).getUserFromId());
            listUser.add(u);
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (int i = 0; i < listUser.size(); i++) {
            System.out.println(listUser.get(i).getId());
        }
        session.close();
        return listUser;
    }

    public static Friend GetFriend(String nameFromId, String nameToId) {
        Friend friend = null;
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("FROM friend WHERE userFromId=:userFromId and userToId=:userToId");
        query.setParameter("userFromId", nameFromId);
        query.setParameter("userToId", nameToId);
        friend = (Friend) query.uniqueResult();
        if (friend == null) {
            query.setParameter("userFromId", nameToId);
            query.setParameter("userToId", nameFromId);
            friend = (Friend) query.uniqueResult();
        }
        session.close();
        return friend;
    }

    public static List<Friend> allFriend() {
        Session ss = HibernateUtil.openSession();
        List<Friend> friends = ss.createQuery("FROM friend").list();
        ss.close();
        return friends;
    }

    public static User getUserById(String userId) {
        Session ss = HibernateUtil.openSession();
        Query query = ss.createQuery("FROM user WHERE id=:id");
        query.setParameter("id", userId);
        System.out.println("ok");
        User u = (User) query.uniqueResult();
        return u;
    }

    public static boolean CheckExist(String userFromId, String userToId) {
        List<Friend> friend = FriendDAO.allFriend();
        boolean b = false;
        for (int i = 0; i < friend.size(); i++) {
            Friend f = friend.get(i);
            if (userFromId.equals(f.getUserFromId()) && userToId.equals(f.getUserToId())) {
                b = true;
                break;
            } else {
                if (userFromId.equals(f.getUserToId()) && userToId.equals(f.getUserFromId())) {
                    b = true;
                    break;
                } else {
                    b = false;
                }
            }
        }
        return b;
    }
}
