package com.nnk.springboot.security.oauth2;


//import org.springframework.security.core.userdetails.User;

import com.nnk.springboot.domain.AuthProvider;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exception.OAuth2AuthenticationProcessingException;
import com.nnk.springboot.repositories.UserRepository;

//import com.nnk.springboot.security.CustomOAuth2User;
import com.nnk.springboot.security.UserPrincipal;
import com.nnk.springboot.security.oauth2.user.OAuth2UserInfo;
import com.nnk.springboot.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oA2User = super.loadUser(userRequest);

       try {
           //return new CustomOAuth2User(oA2User);
           return processOAuth2User(userRequest, oA2User);
       } catch (AuthenticationException ex) {
           throw ex;
       } catch (Exception ex) {
           // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
           throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
       }
    }


    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(!StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        User user1 = userRepository.findByUsername(oAuth2UserInfo.getName());
        //Optional<User> userOptional = userRepository.findByUsername(oAuth2UserInfo.getName());
//        User user1;
//        if(userOptional.isPresent()) {
        if(user1 != null) {
//            user1 = userOptional.get();
            if(!user1.getProvider().equals(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user1.getProvider() + " account. Please use your " + user1.getProvider() +
                        " account to login.");
            }
            user1 = updateExistingUser(user1, oAuth2UserInfo);
        } else {
            user1 = registerNewUser(userRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user1, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setUsername(oAuth2UserInfo.getName());
        //user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }
}
