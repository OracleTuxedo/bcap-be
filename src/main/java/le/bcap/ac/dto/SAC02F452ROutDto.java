package le.bcap.ac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
public class SAC02F452ROutDto {
    private Long total_count;

    private List<SAC02F452ROutSub1Dto> sub1_dtos;
}
