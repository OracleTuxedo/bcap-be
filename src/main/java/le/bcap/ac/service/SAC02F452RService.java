package le.bcap.ac.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import le.bcap.ac.dto.SAC02F452RInDto;
import le.bcap.ac.dto.SAC02F452ROutDto;
import le.bcap.az.ServiceSupport;
import mti.ac.ac07.vo.SAC02F452RInVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramOutputUserData;
import java.time.format.DateTimeFormatter;

@Service
public class SAC02F452RService {

    private final ServiceSupport serviceSupport;

    public SAC02F452RService(ServiceSupport serviceSupport) {
        this.serviceSupport = serviceSupport;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public SAC02F452ROutDto getListTransaction(HttpServletRequest request, SAC02F452RInDto dto) throws Exception {

        SAC02F452RInVo input = SAC02F452RInVo.builder()
                .page_no(dto.getPage_no())
                .page_size(dto.getPage_size())
                .mid(dto.getMid())
                .auth_no(dto.getAuth_no())
                .auth_strt_date(dto.getAuth_start_date().format(formatter))
                .auth_end_date(dto.getAuth_end_date().format(formatter))
//                .pmt_strt_date(dto.getPmt_start_date().format(formatter))
//                .pmt_end_date(dto.getPmt_end_date().format(formatter))
                .build();
        
        SAC02F452ROutDto output = SAC02F452ROutDto.builder().build();

        try {
            TelegramInputUserData telegramInputUserData = serviceSupport.getTxHeader(request, "SAC02F452R",
                    "WAC070100H");
            TelegramOutputUserData result = serviceSupport.txTransaction(telegramInputUserData,
                    input, output);
            output = (SAC02F452ROutDto) result.getOutput();

        } catch (TelegramNestedRuntimeException e) {
        } catch (Exception e) {
        }

        return output;
    }
}
