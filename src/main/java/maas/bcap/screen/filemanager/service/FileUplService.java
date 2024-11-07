package maas.bcap.screen.filemanager.service;

import maas.bcap.screen.filemanager.controller.FileMngController;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.util.InterfaceTelegram;
import mti.com.telegram.vo.TelegramUserDataInput;
import mti.com.telegram.vo.TelegramUserDataOutput;
import maas.bcap.screen.filemanager.vo.FileInfoInVO;
import maas.bcap.screen.filemanager.vo.FileInfoOutVO;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Map;

import static mti.com.telegram.util.InterfaceTelegram.interfaceTuxedo;

@Service
public class FileUplService {

    private static final Logger logger = LogManager.getLogger(FileUplService.class);
    private final static String MSGNORMAL = "N";
    private final static String RETURN_NULL = "No Data Returned";
    private final static String ERRORCD = "Error Occurred - Service failed";
    private final static String TPERR = "Getting Abnormal Message";

    public FileInfoOutVO saveFileData(FileInfoInVO fInVO, Map<String, Object> usrInfo) throws Exception { //
        fInVO.setReq_clcd("U1");
        FileInfoOutVO outputVO = new FileInfoOutVO();
        TelegramUserDataOutput resultMsg = null;

        resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);


        TelegramMessage message = resultMsg.getMessage();

        String msg = message.getKind();

        if(msg == null){
            throw new Exception(RETURN_NULL);
        }else{
            if(MSGNORMAL.equals(msg)){
                outputVO = (FileInfoOutVO) resultMsg.getOutput();
                if(!"00".equals(outputVO.getRson_cd())){
                    throw new Exception(ERRORCD);
                }
            }else{
                throw new Exception(TPERR);

            }
        }

        return outputVO;
    }

    public FileInfoOutVO updateFileData(FileInfoInVO fInVO, Map<String, Object> usrInfo) throws Exception { //
//		fInVO.setReq_clcd("U2");
        FileInfoOutVO outputVO = new FileInfoOutVO();
//		TelegramOutputUserData resultMsg = null;
//
//		resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);
//
//		outputVO = (FileInfoOutVO) resultMsg.getOutput();
//		if(!"00".equals(outputVO.getRson_cd())){
//			throw new Exception("Error");
//		}
        return outputVO;
    }


    public String selectAttachId() throws Exception {
        //String attachId = fileUploadDAO.selectAttachId(); //

        FileInfoInVO fInVO = new FileInfoInVO();
        fInVO.setReq_clcd("S1");
        FileInfoOutVO outputVO = new FileInfoOutVO();
        TelegramUserDataOutput resultMsg = null;

        //	resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);

        outputVO = (FileInfoOutVO) resultMsg.getOutput();

        String id = "";


        TelegramMessage message = resultMsg.getMessage();
        String msg = message.getKind();

        if(msg == null){
            throw new Exception("No Data Returned");
        }else{
            if(MSGNORMAL.equals(msg)){

                id = outputVO.getAttach_file_id();

                if(!"00".equals(outputVO.getRson_cd())){
                    throw new Exception("Error Occurred - Service failed");
                }

            }else{
                throw new Exception("Getting Abnormal Message.");

            }
        }
        return id;
    }

    public FileInfoOutVO selectFileList(FileInfoInVO fInVO, Map<String, Object> usrInfo) throws Exception {

        fInVO.setReq_clcd("S2");
        FileInfoOutVO outputVO = new FileInfoOutVO();
        TelegramUserDataOutput resultMsg = null;

        resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);

        TelegramMessage message = resultMsg.getMessage();

        String msg = message.getKind();

        if(msg == null){
            throw new Exception(RETURN_NULL);
        }else{
            if(MSGNORMAL.equals(msg)){
                outputVO = (FileInfoOutVO) resultMsg.getOutput();
                if(!"00".equals(outputVO.getRson_cd())){
                    throw new Exception(ERRORCD);
                }
            }else{
                throw new Exception(TPERR);

            }
        }


        return outputVO;
    }

    public FileInfoOutVO selectFileInfo(FileInfoInVO fInVO, Map<String, Object> usrInfo) throws Exception {
        fInVO.setReq_clcd("S3");
        FileInfoOutVO outputVO = new FileInfoOutVO();
        TelegramUserDataOutput resultMsg = null;

        resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);

        TelegramMessage message = resultMsg.getMessage();

        String msg = message.getKind();

        if(msg == null){
            throw new Exception(RETURN_NULL);
        }else{
            if(MSGNORMAL.equals(msg)){
                outputVO = (FileInfoOutVO) resultMsg.getOutput();
                if(!"00".equals(outputVO.getRson_cd())){
                    throw new Exception(ERRORCD);
                }
            }else{
                throw new Exception(TPERR);

            }
        }

        return outputVO;
    }

    public FileInfoOutVO insertFileDownHist(FileInfoInVO fInVO, Map<String, Object> usrInfo) throws Exception { //
        fInVO.setReq_clcd("U3");
        FileInfoOutVO outputVO = new FileInfoOutVO();
        TelegramUserDataOutput resultMsg = null;

        resultMsg = interfaceTuxedo(fInVO, outputVO, usrInfo);

        TelegramMessage message = resultMsg.getMessage();

        String msg = message.getKind();

        if(msg == null){
            throw new Exception(RETURN_NULL);
        }else{
            if(MSGNORMAL.equals(msg)){
                outputVO = (FileInfoOutVO) resultMsg.getOutput();
                if(!"00".equals(outputVO.getRson_cd())){
                    throw new Exception(ERRORCD);
                }
            }else{
                throw new Exception(TPERR);

            }
        }


        return outputVO;
    }

    public TelegramUserDataOutput interfaceTuxedo(FileInfoInVO fInVO, FileInfoOutVO fOutVO, Map<String, Object> usrInfo) throws UnknownHostException {
        logger.info("Start Telegram Service (SAZ06F010U) : File Upload Manager");
        TelegramUserDataInput userDatacden = new TelegramUserDataInput();

        userDatacden.setTx_code("SAZ06F010U");
        userDatacden.setClient_ip_no((String) usrInfo.get("usrIp"));
        userDatacden.setClient_mac("");
        userDatacden.setScrn_id((String) usrInfo.get("scrId"));
        userDatacden.setOp_id((String) usrInfo.get("usrId"));
        userDatacden.setSync_type("A");
        userDatacden.setRspn_svc_code("");
        userDatacden.setAsync_rspn_yn("0");
        userDatacden.setTtl_use_flag(0);
        userDatacden.setLang_type("EN");

        FileInfoOutVO outputVO = new FileInfoOutVO();

        TelegramUserDataOutput resultMsg = null;

        try {
            resultMsg = (TelegramUserDataOutput) InterfaceTelegram.interfaceTuxedo(userDatacden, fInVO, outputVO);

            //outputVO = (FileInfoOutVO) resultMsg.getOutput();
            TelegramMessage message = resultMsg.getMessage();
            logger.info("Message Info : ");
            logger.info(message.toString());
        } catch (TelegramNestedRuntimeException e) {
            // TODO Auto-generated catch block
            logger.error(e.toString());
            ExceptionUtil.logPrintStackTrace(logger, e);
        }catch (Exception e1) {
            // TODO Auto-generated catch block
            logger.error(e1.toString());
            ExceptionUtil.logPrintStackTrace(logger, e1);
        }


        return resultMsg;
    }

}
