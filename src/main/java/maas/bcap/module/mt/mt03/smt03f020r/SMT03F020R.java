package maas.bcap.module.mt.mt03.smt03f020r;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maas.bcap.az.ServiceSupport;
import maas.bcap.module.mt.mt01.smt01f009c.SMT01F009CInVo;
import maas.bcap.module.mt.mt03.smt03f010r.SMT03F010R;
import maas.bcap.module.mt.mt03.smt03f010r.SMT03F010ROutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.FIELD;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMT03F020R {
    private static final Logger logger = LogManager.getLogger(SMT03F020R.class);

    @Autowired
    private ServiceSupport support;

    public TelegramUserDataOutput<SMT03F020ROutVo> call(HttpServletRequest request, SMT03F020RInVo inVo, String screenId) {
        TelegramUserDataOutput<SMT03F020ROutVo> result = TelegramUserDataOutput.<SMT03F020ROutVo>builder().build();
        SMT03F020ROutVo outVo = SMT03F020ROutVo.builder().build();

        try {
            TelegramUserDataInput userDataInput = support.tuxedoHeader(request, this.getClass().getSimpleName(), screenId);
            result = support.tuxedoTransaction(userDataInput, inVo, outVo);
        } catch (TelegramNestedRuntimeException e) {
            logger.info(e.toString());
            logger.info(e.getMsg());
        } catch (Exception e) {
            logger.info(e.toString());
            logger.info(e.getClass());
            logger.info(e.getLocalizedMessage());
            logger.info(e.getMessage());
        }

        return result;
    }
}
