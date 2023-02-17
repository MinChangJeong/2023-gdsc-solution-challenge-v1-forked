package gdsc.netwalk.business.user.mapper;

import gdsc.netwalk.common.model.Payload;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public void login(Payload payload);

    public Payload selectUserByEmail(Payload payload);
}
