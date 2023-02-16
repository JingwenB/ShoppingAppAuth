package com.beaconfire.springsecurityauth.dao;

import com.beaconfire.springsecurityauth.domain.entity.Permission;
import com.beaconfire.springsecurityauth.domain.entity.User;
import com.beaconfire.springsecurityauth.exception.SaveFailedException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    public SessionFactory sessionFactory;

    public void add(User user)  {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{

            Permission permission = Permission.builder().status("user").user(user).build();

            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);

            user.setPermissions(permissions);

            session.saveOrUpdate(user);
            session.saveOrUpdate(permission);
        }
        catch(Exception e){
            e.printStackTrace();
            session.clear();
            throw new SaveFailedException(
                    String.format("Save object class: %s,\n" +
                            "with entity: %s", User.class, user));
        }  finally {
            tx.commit();
            session.close();
        }
    }

    public List<User> getAllUsers() {
        Session session;
        List<User> users = null;
        try{
            session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            users = session.createQuery(cq).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (users.isEmpty()) ? null : users;
    }

    public Optional<User> loadUserByUsername(String username){
        List<User> users = getAllUsers();

        Optional<User> userOptional = users.stream().filter(user -> username.equals(user.getUsername())).findAny();

        System.out.println("\n"+userOptional.get().getPermissions());

        return userOptional;
    }

}
