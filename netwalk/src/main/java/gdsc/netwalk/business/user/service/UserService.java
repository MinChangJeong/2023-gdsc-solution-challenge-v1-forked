package gdsc.netwalk.business.user.service;

import gdsc.netwalk.common.model.Payload;
import gdsc.netwalk.common.service.SuperService;
import lombok.extern.java.Log;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

@Service
public class UserService extends SuperService {

    private static final String MAPPER_NAME = "mybatis.user.user_mapper";

    public Payload login(Payload request) {
        Payload result = new Payload();

        try {
            // [1] 회원정보 조회
            if(select(MAPPER_NAME + "selectUserByEmail", request).isEmpty()) {
//                switch(request.getString("reg_gb")) {
//                    case "UR01": // 구글로그인
//                        result.set("reg_gb", request.getInt("UR01"));
//                        break;
//                    case "UR02":
//                        result.set("reg_gb", request.getInt("UR02"));
//                        break;
//                }
                insert(MAPPER_NAME + "registerUser", request);
                result.set("user_no", request.getInt("user_no"));
            }

        } catch (Exception e) {
            System.out.println("로그인 오류 발생");
            System.out.println(e);
        }
        return result;
    }
}
