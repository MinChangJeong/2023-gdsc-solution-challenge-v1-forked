package gdsc.netwalk.business.user.service;

import gdsc.netwalk.business.user.mapper.UserMapper;
import gdsc.netwalk.common.model.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    public Payload login(Payload request) {
        Payload result = new Payload();

        try {
            // [1] 회원정보 조회
            if(userMapper.selectUserByEmail(request).isEmpty()) {
                userMapper.login(request);
                result.set("user_no", request.getInt("user_no"));
            }

        } catch (Exception e) {
            System.out.println("로그인 오류 발생");
            System.out.println(e);
        }
        return result;
    }
}
