package com.ym.mapper;

import com.ym.dto.UserDto;
import com.ym.model.AppRole;
import com.ym.model.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public AppUser toAppUser(UserDto userDto){
       if(userDto != null){
           AppUser appUser = new AppUser();
           BeanUtils.copyProperties(userDto, appUser);
           appUser.setAppRole(AppRole.valueOf(userDto.getRole()));
           return appUser;
       }
       return null;
    }

    public UserDto toUserDto(AppUser appUser){
        if(appUser != null){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(appUser, userDto);
            userDto.setRole(appUser.getAppRole().toString());
            return userDto;
        }
        return null;
    }
}
