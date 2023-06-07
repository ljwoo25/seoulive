package org.zerock.seoulive.member.join.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UserDTO {
    private String email;
    private String password;
    private String nickname;
    private String birthDate;
    private String gender;
    private String introduction;
    private String joinDate;
    private String profileImgName;

    private boolean rememberMe;
    private MultipartFile file;


} // end class
