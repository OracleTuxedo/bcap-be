package maas.bcap.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import maas.bcap.common.ServiceSupport;
import maas.bcap.dto.AuthInfoDto;
import maas.bcap.dto.FileInVo;
import maas.bcap.dto.FileOutVo;
import maas.bcap.dto.UserInfoFileManagerDto;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import mti.com.utility.ExceptionUtil;

@Service
public class FileUploadService {
    private static final Logger log = LogManager.getLogger(FileUploadService.class);

    @Autowired
    private ServiceSupport serviceSupport;

    public FileOutVo saveFilesToDevonC(AuthInfoDto authInfoDto, FileInVo inVo,
            UserInfoFileManagerDto userInfoFileManagerDto) throws Exception {
        log.info("FileUploadService.saveFilesToDevonC");

        inVo.setReq_clcd("U1");

        log.info(inVo);

        TelegramUserDataOutput<FileOutVo> result = interfaceTuxedo(authInfoDto, inVo, userInfoFileManagerDto);

        FileOutVo outVo = FileOutVo.builder().build();
        TelegramMessage telegramMessage = result.getMessage();
        String kind = telegramMessage.getKind();

        if (kind == null) {
            log.error("No Data Returned");
            throw new Exception("No Data Returned");
        } else if (kind.equals("N")) {
            outVo = result.getOutput();
            if (!outVo.getRson_cd().equals("00")) {
                log.error("Error Occurred - Service failed");
                throw new Exception("Error Occurred - Service failed");
            } else {
                log.error("Getting Abnormal Message");
                throw new Exception("Getting Abnormal Message");
            }
        }

        return outVo;
    }

    public FileOutVo selectFileInfoList(AuthInfoDto authInfoDto, FileInVo inVo,
            UserInfoFileManagerDto userInfoFileManagerDto) throws Exception {
        log.info("FileUploadService.selectFileInfoList");

        inVo.setReq_clcd("S2");

        log.info(inVo);

        TelegramUserDataOutput<FileOutVo> result = interfaceTuxedo(authInfoDto, inVo, userInfoFileManagerDto);

        FileOutVo outVo = FileOutVo.builder().build();
        TelegramMessage telegramMessage = result.getMessage();
        String kind = telegramMessage.getKind();

        if (kind == null) {
            log.error("No Data Returned");
            throw new Exception("No Data Returned");
        } else if (kind.equals("N")) {
            outVo = result.getOutput();
            if (!outVo.getRson_cd().equals("00")) {
                log.error("Error Occurred - Service failed");
                throw new Exception("Error Occurred - Service failed");
            } else {
                log.error("Getting Abnormal Message");
                throw new Exception("Getting Abnormal Message");
            }
        }

        return outVo;
    }

    public FileOutVo selectFileInfo(AuthInfoDto authInfoDto, FileInVo inVo,
            UserInfoFileManagerDto userInfoFileManagerDto)
            throws Exception {
        log.info("FileUploadService.selectFileInfo");

        inVo.setReq_clcd("S3");

        log.info(inVo);

        TelegramUserDataOutput<FileOutVo> result = interfaceTuxedo(authInfoDto, inVo, userInfoFileManagerDto);

        FileOutVo outVo = FileOutVo.builder().build();
        TelegramMessage telegramMessage = result.getMessage();
        String kind = telegramMessage.getKind();

        if (kind == null) {
            log.error("No Data Returned");
            throw new Exception("No Data Returned");
        } else if (kind.equals("N")) {
            outVo = result.getOutput();
            if (!outVo.getRson_cd().equals("00")) {
                log.error("Error Occurred - Service failed");
                throw new Exception("Error Occurred - Service failed");
            } else {
                log.error("Getting Abnormal Message");
                throw new Exception("Getting Abnormal Message");
            }
        }
        return outVo;
    }

    public FileOutVo insertFileDownloadHistory(AuthInfoDto authInfoDto, FileInVo inVo,
            UserInfoFileManagerDto userInfoFileManagerDto) throws Exception {
        log.info("FileUploadService.insertFileDonwloadHistory");

        inVo.setReq_clcd("U3");

        log.info(inVo);

        TelegramUserDataOutput<FileOutVo> result = interfaceTuxedo(authInfoDto, inVo, userInfoFileManagerDto);

        FileOutVo outVo = FileOutVo.builder().build();
        TelegramMessage telegramMessage = result.getMessage();
        String kind = telegramMessage.getKind();

        if (kind == null) {
            log.error("No Data Returned");
            throw new Exception("No Data Returned");
        } else if (kind.equals("N")) {
            outVo = result.getOutput();
            if (!outVo.getRson_cd().equals("00")) {
                log.error("Error Occurred - Service failed");
                throw new Exception("Error Occurred - Service failed");
            } else {
                log.error("Getting Abnormal Message");
                throw new Exception("Getting Abnormal Message");
            }
        }
        return outVo;
    }

    private TelegramUserDataOutput<FileOutVo> interfaceTuxedo(AuthInfoDto authInfoDto, FileInVo inVo,
            UserInfoFileManagerDto userInfoFileManagerDto)
            throws Exception {
        log.info("FileUploadService.interfaceTuxedo");

        TelegramUserDataInput userDataInput = new TelegramUserDataInput();

        userDataInput.setTx_code("SAZ06F010U");
        userDataInput.setClient_ip_no(userInfoFileManagerDto.getUserIp());
        userDataInput.setClient_mac("");
        userDataInput.setScrn_id(userInfoFileManagerDto.getScreenId());
        userDataInput.setOp_id(authInfoDto.getUserId());
        userDataInput.setSync_type("A");
        userDataInput.setRspn_svc_code("");
        userDataInput.setAsync_rspn_yn("0");
        userDataInput.setTtl_use_flag(0);
        userDataInput.setLang_type("EN");

        log.info(userDataInput);

        TelegramUserDataOutput<FileOutVo> result = TelegramUserDataOutput.<FileOutVo>builder().build();

        try {
            FileOutVo outVo = FileOutVo.builder().build();
            result = serviceSupport.tuxedoTransaction(userDataInput, inVo, outVo);
            log.info(result.getMessage());
            return result;
        } catch (TelegramNestedRuntimeException e) {
            log.error(e.toString());
            ExceptionUtil.logPrintStackTrace(log, e);
            throw e;
        } catch (Exception e) {
            log.error(e.toString());
            ExceptionUtil.logPrintStackTrace(log, e);
            throw e;
        }
    }
}
