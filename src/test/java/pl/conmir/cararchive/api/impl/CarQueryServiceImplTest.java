package pl.conmir.cararchive.api.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.conmir.cararchive.domain.CarRepository;
import pl.conmir.cararchive.exception.ResourseNotFoundException;
import pl.conmir.cararchive.domain.modificationFile.ModificationFile;
import pl.conmir.cararchive.dto.GetModificationFile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarQueryServiceImplTest {

//    @Mock
//    private CarRepository repository;
//
//    @InjectMocks
//    private CarQueryServiceImpl service;
//
//    @Test
//    void shouldReturnModificationFile(){
//        //given
//        var modificationFile = createModificationFile();
//        when(repository.findByModifiedFileId(anyLong(), anyLong())).thenReturn(Optional.of(modificationFile));
//
//        //when
//        GetModificationFile result = service.findByModificationId(anyLong(), anyLong());
//
//        //then
////        assertThat
//
//
//    }
//
//    @Test
//    void shouldThrownExceptionForFindByModificationFileId(){
//        //given
//        when(repository.findByModifiedFileId(anyLong(), anyLong())).thenReturn(Optional.empty());
//
//        //then
//        assertThatThrownBy(() -> {
//            service.findByModificationId(anyLong(), anyLong());
//        }).isInstanceOf(ResourseNotFoundException.class);
//
//    }
//
//    private ModificationFile createModificationFile(){
//        return ModificationFile.builder()
//                .name(ModificationFileName.of("name"))
//                .type("")
//                .data(ModificationFileData.of(new byte[]{1,2,3}))
//                .build();
//    }


}