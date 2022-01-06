package com.mastery.java.task.exception.userNotFoundException;




public class userNotFoundException extends RuntimeException{

    public userNotFoundException (long id){
        super("user with id=" + id + " not found");
    }
}
