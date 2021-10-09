package pl.conmir.cararchive.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePerformanceModificationDto {
    private int powerAfterModification;
    private int torqueAfterModification;
}
