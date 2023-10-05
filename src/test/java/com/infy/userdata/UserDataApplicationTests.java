package com.infy.userdata;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.infy.userdata.exception.UserDataException;
import com.infy.userdata.repository.UserDataRepository;
import com.infy.userdata.service.UserDataService;
import com.infy.userdata.service.UserDataServiceImpl;
@SpringBootTest
public class UserDataApplicationTests {
@Mock
private UserDataRepository userDataRepository;

@InjectMocks
private UserDataService userDataService = new UserDataServiceImpl();


@Test
public void getDetailsByUserNameNoDetailsFound() {
Mockito.when(userDataRepository.findByUserName(Mockito.anyString())).thenReturn(List.of());
String userName = "some_user_name";
UserDataException e= Assertions.assertThrows(UserDataException.class, ()-> userDataService.getDetailsByUserName(userName));
Assertions.assertEquals("Service.NO_DETAILS_FOUND", e.getMessage());

}


}
