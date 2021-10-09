package pl.conmir.cararchive.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.api.CarCommandService;
import pl.conmir.cararchive.api.CarQueryService;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class CarControllerTest {

    @Mock
    private WebApplicationContext wac;
    @Mock
    private  CarCommandService carCommandService;
    @Mock
    private  CarQueryService carQueryService;
    @InjectMocks
    CarController carController;

    private MockMvc mockMvc;
    private final String BASE_URL = "/api/car";

    @BeforeEach // For Junit5
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(wac).build();

    }

    @Test
    public void saveCar() throws Exception {
        //given
        var dto = CreateCarDto.builder()
                .year(1999)
                .make("make")
                .registration("1fsf2gs")
                .model("model")
                .build();


        //when
//        when(carCommandService.save(any(CreateCarDto.class))).


        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isCreated());
    }





    private CreateCarDto createCarDto(){
        return CreateCarDto.builder()
                .year(1999)
                .make("make")
                .registration("1fsf2gs")
                .model("model")
                .build();
    }

}