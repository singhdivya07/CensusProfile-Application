package com.capgemini.census.service;

import com.capgemini.census.entity.LogOutPayload;
import com.capgemini.census.entity.Login;
import com.capgemini.census.entity.User;

public interface LoginService {
   /**
    * Sign in customer
    * @param customerMaster
    * @return sign in successful
    * else throw invalid customer
    */
    public String signIn(User user);

    /**
     * Sign out 
     * @param customerMaster
     * @return sign out successful
     */

    public String signOut(LogOutPayload user);
    
    /**
     * Change Password
     * @param customerMaster
     * @param new_password
     * @return changed password
     */

    public String changePassword(User user, String new_password);


}