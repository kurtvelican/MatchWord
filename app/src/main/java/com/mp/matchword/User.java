package com.mp.matchword;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {
    private static User user;
    private String userName , email;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    private User(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    public static void setUser(User user) {
        User.user = user;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public static User getInstance(){
        if (user==null){
            user=new User();
        }
        return user;
    }
}
