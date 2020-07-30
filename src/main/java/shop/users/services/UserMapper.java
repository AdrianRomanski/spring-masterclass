package shop.users.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shop.users.model.User;
import shop.users.model.UserTransferObject;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "emailAddress", target = "email")
    User toUser(UserTransferObject userTransferObject);

    @Mapping(source = "email", target = "emailAddress")
    UserTransferObject toUserTransferObject(User user);

}
