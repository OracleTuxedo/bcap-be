package maas.bcap.screen.filemanager.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import maas.bcap.screen.filemanager.vo.FileInfoInSubVO;
import maas.bcap.screen.filemanager.vo.FileInfoInVO;
import maas.bcap.screen.filemanager.vo.FileInfoOutSubVO;
import maas.bcap.screen.filemanager.vo.FileInfoOutVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/file-manager")
public class FileMngController {

    @Value("${fileProp.File.Path}")
    private String fileUploadPath;


    @Value("${fileProp.File.Ext.FilterList}")
    private String fileExtChkList;

//    @GetMapping("/test")
//    public String getMethodName(HttpServletRequest request) {
//        return new String(fileExtChkList);
//    }

    private static final Logger logger = LogManager.getLogger(FileMngController.class);

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> fileUploadXp(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

        logger.debug("MultiFileUpload Start");

        //upload file data gathering
        String chkMultiPart = request.getContentType();
        MultipartHttpServletRequest multipartRequest;
        Map<String, MultipartFile> multipartFiles = new HashMap<String, MultipartFile>();
        List<MultipartFile> multipartList = new ArrayList<MultipartFile>();

        // multipart file name is unique
        // make multipart file list
        if(chkMultiPart != null && chkMultiPart.contains("multipart/form-data")){
            multipartRequest = (MultipartHttpServletRequest)request;
            multipartFiles = multipartRequest.getFileMap();

            if(multipartFiles.size()>0){
                Iterator<Map.Entry<String, MultipartFile>> itr = multipartFiles.entrySet().iterator();
                while (itr.hasNext()) {
                    MultipartFile file;
                    Map.Entry<String, MultipartFile> entry = itr.next();
                    file = entry.getValue();
                    multipartList.add(file);
                }
            }
        }

        // Get file path from last saved
        String fileDiv = validateFilePath(request.getParameter("fileDiv"));
        String fileDesc = request.getParameter("fileDesc") == null ? "" : request.getParameter("fileDesc");
        String extraPath = validateFilePath(request.getParameter("extraPath") == null ? "" : request.getParameter("extraPath"));
        String attachFileId = request.getParameter("attachFileId");
        String[] delTargetSeqInfo = request.getParameterValues("attachFileSeqNo");


        //create user info
        Map<String, Object> usrInfo = new HashMap<String, Object>();
        String usrId = request.getParameter("usrId");
        String scrId = request.getParameter("scrId");
        String usrIp = request.getRemoteAddr();
        usrInfo.put("usrId", usrId);
        usrInfo.put("scrId", scrId);
        usrInfo.put("usrIp", usrIp);


        //variable definition
        String eMsg 	 = "File Upload Error";
        String eMsgDtl	 = "";
        String filePath  = "";
        boolean fileChk = false;
        Boolean newFlag = true;

        Map<String, Object> resData = new HashMap<>();
        List<Map<String, Object>> ds = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
//        row.put("ds_fileResult", "SUCCESS");
//        ds.add(row);

        FileInfoOutVO fOutVO = new FileInfoOutVO();
        List<FileInfoInSubVO> fInSubVOList = new ArrayList<FileInfoInSubVO>();
        List<FileInfoOutSubVO> fOutSubVOList = new ArrayList<FileInfoOutSubVO>();


        /*if(multipartList!= null) {
            if (multipartList.size() > 0) {
                for (MultipartFile file : multipartList) {

                    String orginFileName = file.getOriginalFilename();
                    if ("".equals(orginFileName)) {
                        continue;
                    }
                    //extension check
                    fileChk = checkFileExt(file);
                    if (!fileChk) {
                        eMsgDtl = "File Extention Error.";
                        throw new Exception(eMsgDtl);
                    }
                    //file info set
                    long _size = file.getSize();
                    //file path
                    if("".equals(extraPath) || extraPath==null){
                        filePath = fileUploadPath + File.separator + fileDiv;
                    }else{
                        filePath = fileUploadPath + File.separator + fileDiv + extraPath;
                    }

//                    FileInfoInSubVO fInSubVO = new FileInfoInSubVO();
//                    fInSubVO.setFile_nm(orginFileName);
//                    fInSubVO.setUpl_file_size(_size);
//                    fInSubVO.setFile_path(filePath);
//                    fInSubVO.setInp_pgm_id(scrId);
//                    fInSubVO.setInp_usr_id(usrId);
//
//
//
//                    fileList.add(fInSubVO);

                    FileInfoInSubVO fInSubVO1 = FileInfoInSubVO.builder()
                        .file_nm(orginFileName)
                        .upl_file_size(_size)
                        .file_path(filePath)
                        .build();
                    fInSubVOList.add(fInSubVO1);

                }
            }
        }*/



       /* FileInfoInVO fInVo = FileInfoInVO.builder()
            .list(fInSubVOList)
            .build();
        realFileUpload(fInVo, multipartList, filePath);

        ds.addAll(makeOutDataSet(fInVo, false, true));
        resData.put("response", ds);*/

        try
        {
            //buisiness code set
            if("".equals(fileDiv) || fileDiv == null){
                eMsgDtl = "Path set eror";
                throw new Exception();
            }

            // Check Register/Update status
            if(!"".equals(attachFileId) && attachFileId != null){
                newFlag = false;
            }

            // CASE : Register New FileList   ------------------------------------------------------------

            if(newFlag){
                List<FileInfoInSubVO> fileList = new ArrayList<FileInfoInSubVO>();

                if(multipartList!= null){
                    if(multipartList.size()>0){
                        for(MultipartFile file :multipartList){

                            String orginFileName = file.getOriginalFilename();
                            if ("".equals(orginFileName)) {
                                continue;
                            }
                            //extension check
                            fileChk = checkFileExt(file);
                            if(!fileChk){
                                eMsgDtl = "File Extention Error.";
                                throw new Exception(eMsgDtl);
                            }

                            //file info set
                            long _size = file.getSize();
                            //file path
                            if("".equals(extraPath) || extraPath==null){
                                filePath = fileUploadPath + File.separator + fileDiv;
                            }else{
                                filePath = fileUploadPath + File.separator + fileDiv + extraPath;
                            }

//                            FileInfoInSubVO fInSubVO = new FileInfoInSubVO();
//                            fInSubVO.setFile_nm(orginFileName);
//                            fInSubVO.setUpl_file_size(_size);
//                            fInSubVO.setFile_path(filePath);
//                            fInSubVO.setInp_pgm_id(scrId);
//                            fInSubVO.setInp_usr_id(usrId);
//                            fileList.add(fInSubVO);

                            FileInfoInSubVO fInSubVO = FileInfoInSubVO.builder()
                                .file_nm(orginFileName)
                                .upl_file_size(_size)
                                .file_path(filePath)
                                .inp_pgm_id(scrId)
                                .inp_usr_id(usrId)
                                .build();
                            fInSubVOList.add(fInSubVO);

                            //for test dummy output
                            FileInfoOutSubVO fOutSubVO = FileInfoOutSubVO.builder()
                                .file_nm(orginFileName)
                                .upl_file_size(_size)
                                .file_path(filePath)
                                .inp_pgm_id(scrId)
                                .inp_usr_id(usrId)
                                .upl_file_nm(orginFileName)
                                .build();
                            fOutSubVOList.add(fOutSubVO);

                        }
                    }
                }

//                fInVO.setAttach_file_clcd(fileDiv);
//                fInVO.setAttach_file_expl(fileDesc);
//                fInVO.setUpd_yn("N");
//                fInVO.setList(fileList);

                FileInfoInVO fInVO = FileInfoInVO.builder()
                    .attach_file_clcd(fileDiv)
                    .attach_file_expl(fileDesc)
                    .upd_yn("N")
                    .list(fInSubVOList)
                    .build();



                debugIO(fInVO);
                //File info data save to db
                //fOutVO = fileUplService.saveFileData(fInVO, usrInfo);

                //dummy test
                fOutVO = FileInfoOutVO.builder()
                    .attach_file_clcd(fileDiv)
                    .attach_file_expl(fileDesc)
                    .upd_yn("N")
                    .list(fOutSubVOList)
                    .build();

                logger.info("FileInfoOutSubVO Created: {}", fOutSubVOList);

                //File save to disk
                realFileUpload(fOutVO, multipartList, filePath);
                //realFileUpload(fInVO, multipartList, filePath);
            }

            // CASE : Update FileList   ------------------------------------------------------------
            else{
                //input file info list
                List<FileInfoInSubVO> fileList = new ArrayList<FileInfoInSubVO>();


                //Getting existed data info **
                FileInfoInVO input = new FileInfoInVO();
                input.setAttach_file_id(attachFileId);
                input.setAttach_file_clcd(fileDiv);

                //Retrieve File list
                //FileInfoOutVO fInfoOutVO  = fileUplService.selectFileList(input, usrInfo); // panggil service
                FileInfoOutSubVO fOutSubVO1 = FileInfoOutSubVO.builder()
                    .attach_file_id("awts")
                    .del_yn("Y")
                    .upl_file_nm("76c4f4206bfc27714022e67a1fa6c5b5.png")
                    .attach_file_seq_no("1")
                    .file_path("E:/Work/SampleDoc/TestGambar/extraPath")
                    .build();
                fOutSubVOList.add(fOutSubVO1);

                FileInfoOutSubVO fOutSubVO2 = FileInfoOutSubVO.builder()
                    .attach_file_id("awts")
                    .del_yn("Y")
                    .upl_file_nm("s670_2k.png")
                    .attach_file_seq_no("1")
                    .file_path("E:/Work/SampleDoc/TestGambar/extraPath")
                    .build();
                fOutSubVOList.add(fOutSubVO2);

                logger.info("FileInfoOutSubVO Created: {}", fOutSubVOList);

                FileInfoOutVO fInfoOutVO = FileInfoOutVO.builder()
                    .list(fOutSubVOList)
                    .build();

                List<FileInfoOutSubVO> fileOutList = fInfoOutVO.getList();
                if(fileOutList!=null && fileOutList.size()>0){
                    filePath = fileOutList.get(0).getFile_path();
                }else{
                    //file path
                    if("".equals(extraPath) || extraPath==null){
                        filePath = fileUploadPath + File.separator + fileDiv;
                    }else{
                        filePath = fileUploadPath + File.separator + fileDiv + extraPath;
                    }
                }

                //when delete target exists
                if(delTargetSeqInfo != null){

                    //add to Deletion list
                    assert fileOutList != null;
                    for(FileInfoOutSubVO fileOutSubVO : fileOutList){
                        int fileSeqNo =  Integer.parseInt((String) fileOutSubVO.getAttach_file_seq_no());

                        for(String tmpDelSeq : delTargetSeqInfo){
                            if(Integer.parseInt(tmpDelSeq) == fileSeqNo){
                                //del target save to fileList
//                                FileInfoInSubVO delFInSubVO = new FileInfoInSubVO();
//                                delFInSubVO.setAttach_file_id(fileOutSubVO.getAttach_file_id());
//                                delFInSubVO.setAttach_file_seq_no(fileOutSubVO.getAttach_file_seq_no());
//                                delFInSubVO.setDel_yn("Y");
//
//                                delFInSubVO.setChng_usr_id(usrId);
//                                delFInSubVO.setChng_pgm_id(scrId);
//
//                                fileList.add(delFInSubVO);

                                System.out.println(fileOutSubVO.getAttach_file_id());

                                FileInfoInSubVO delFInSubVO = FileInfoInSubVO.builder()
                                    .attach_file_id(fileOutSubVO.getAttach_file_id())
                                    .attach_file_seq_no(fileOutSubVO.getAttach_file_seq_no())
                                    .del_yn("Y")
                                    .inp_pgm_id(scrId)
                                    .inp_usr_id(usrId)
                                    .build();
                                fInSubVOList.add(delFInSubVO);

                                //for test dummy
//                                FileInfoOutSubVO fOutSubVO = FileInfoOutSubVO.builder()
//                                    .attach_file_id(fileOutSubVO.getAttach_file_id())
//                                    .attach_file_seq_no(fileOutSubVO.getAttach_file_seq_no())
//                                    .del_yn("Y")
//                                    .inp_pgm_id(scrId)
//                                    .inp_usr_id(usrId)
//                                    .build();
//
//                                logger.info("FileInfoOutSubVO Created: {}", fOutSubVO);
//                                fOutSubVOList.add(fOutSubVO);
//
//                                logger.info("FileInfoOutSubVO Created: {}", fOutSubVOList);
                                break;
                            }
                        }
                    }
                }

                // Add attach file list
                if(multipartList!= null){
                    if(multipartList.size()>0){
                        for(MultipartFile file :multipartList){

                            String orginFileName = file.getOriginalFilename();
                            if ("".equals(orginFileName)) {
                                continue;
                            }
                            //extension check
                            fileChk = checkFileExt(file);
                            if(!fileChk){
                                eMsgDtl = "File Extention Error.";
                                throw new Exception(eMsgDtl);
                            }

                            //file info set
                            long _size = file.getSize();

//                            FileInfoInSubVO fInSubVO = new FileInfoInSubVO();
//                            fInSubVO.setDel_yn("N");
//                            fInSubVO.setFile_nm(orginFileName);
//                            fInSubVO.setUpl_file_size(_size);
//                            fInSubVO.setFile_path(filePath);
//                            fInSubVO.setInp_pgm_id(scrId);
//                            fInSubVO.setInp_usr_id(usrId);
//
//                            fileList.add(fInSubVO);

                            FileInfoInSubVO fInSubVO1 = FileInfoInSubVO.builder()
                                .del_yn("N")
                                .file_nm(orginFileName)
                                .upl_file_size(_size)
                                .file_path(filePath)
                                .inp_pgm_id(scrId)
                                .inp_usr_id(usrId)
                                .build();
                            fInSubVOList.add(fInSubVO1);

                            //for dummy test output
                            FileInfoOutSubVO fOutSubVO = FileInfoOutSubVO.builder()
                                .del_yn("N")
                                .file_nm(orginFileName)
                                .upl_file_size(_size)
                                .file_path(filePath)
                                .inp_pgm_id(scrId)
                                .inp_usr_id(usrId)
                                .build();
                            fOutSubVOList.add(fOutSubVO);
                        }
                    }
                }
//                fInVO.setAttach_file_id(attachFileId);
//                fInVO.setUpd_yn("Y");
//                fInVO.setList(fileList);

                FileInfoInVO fInVO = FileInfoInVO.builder()
                    .attach_file_id(attachFileId)
                    .upd_yn("Y")
                    .list(fInSubVOList)
                    .build();

                debugIO(fInVO);
                //File info data save to db
                //fOutVO = fileUplService.saveFileData(fInVO, usrInfo);

                logger.info("FileInfoOutSubVO Created: {}", fOutSubVOList);

                //for dummy test
                fOutVO = FileInfoOutVO.builder()
                    .attach_file_id(attachFileId)
                    .upd_yn("Y")
                    .list(fOutSubVOList)
                    .build();

                //File transfer to disk
               // realFileUpload(fOutVO, multipartList, filePath);


                deleteFile(fOutVO, filePath);
            }
            //Make return xplatform dataset
            ds.addAll(makeOutDataSet(fOutVO, false, true));
            resData.put("ds_fileResult", ds);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
//            ds.addColumn(new ColumnHeader("ErrorCode", DataTypes.STRING));
//            ds.addColumn(new ColumnHeader("ErrorMsg", DataTypes.STRING));

//            int row= ds.newRow();
//            ds.set(row, "ErrorCode", 500);

            row.put("ErrorCode", "500");

            if("".equals(eMsgDtl) || eMsgDtl == null)
                row.put("ErrorCode", eMsg);
            else
                row.put("ErrorCode", eMsg + " / " +eMsgDtl);

            ds.add(row);
            resData.put("ds_fileResult", ds);
        }

        return ResponseEntity.ok(resData);
    }

    private void realFileUpload(FileInfoOutVO fOutVO, List<MultipartFile> multipartFiles, String filePath) throws Exception {

        if( multipartFiles != null && multipartFiles.size() > 0 ){
            int fileCnt = multipartFiles.size();
            for( int i=0 ; i<fileCnt ; i++ ){
                MultipartFile file = multipartFiles.get(i);

                String orginFileName = file.getOriginalFilename();

                List<FileInfoOutSubVO> fOutList = fOutVO.getList();
                for(FileInfoOutSubVO fOut : fOutList){
                    if(fOut.getFile_nm()!= null){
                        if(fOut.getFile_nm().equals(orginFileName)){   // fileNm 던져줘야함
                            String fileSaveName = fOut.getUpl_file_nm();
                            File saveFolder = new File(filePath);

                            if (!saveFolder.exists() || saveFolder.isFile()) {
                                saveFolder.mkdirs();
                            }
                            String saveFile = saveFolder + File.separator + fileSaveName;
                            file.transferTo(new File(saveFile));

                            break;
                        }
                    }
                }

            }
        }
    }

    public List<Map<String, Object>> makeOutDataSet(FileInfoOutVO fOutVo, boolean multiFlag, boolean showAll) {
        List<Map<String, Object>> dataset = new ArrayList<>();

        List<FileInfoOutSubVO> fOutList = fOutVo.getList();
        for (FileInfoOutSubVO fOut : fOutList) {
            Map<String, Object> row = new HashMap<>();
            if (showAll) {
                row.put("file_name", fOut.getFile_nm());
                row.put("file_size", fOut.getUpl_file_size());
                row.put("file_path", fOut.getFile_path());
            }
            dataset.add(row);
        }

        return dataset;
    }

    private void deleteFile(FileInfoOutVO fOutVO, String filePath){
        List<FileInfoOutSubVO> fOutList = fOutVO.getList();
        for(FileInfoOutSubVO fOut : fOutList){
            String delYn = fOut.getDel_yn();
            if(delYn != null){
                if(delYn.equals("Y")){
                    String fileSaveName = fOut.getUpl_file_nm();
                    String savedFile = filePath + File.separator + fileSaveName;

                    File file = new File(savedFile);
                    if(file.exists()){
                        file.delete();
                    }
                }
            }
        }
    }


    private boolean checkFileExt(MultipartFile file) throws Exception {
        boolean rslt = false;

        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        String allowList = fileExtChkList;
        StringTokenizer tk = new StringTokenizer(allowList, ",");
        Map<String, String> allowMap = new HashMap<String, String>();

        while(tk.hasMoreTokens()){
            String tmpExt = tk.nextToken();
            allowMap.put(tmpExt,tmpExt);
        }

        String chkRslt=allowMap.get(ext.toLowerCase());

        if(chkRslt==null){
            throw new IOException("BAD Extension file upload!");
        }else
            rslt = true;
        return rslt;
    }

    private String validateFilePath(String filePath){
        String result = null;

        if(filePath!=null){
            result = filePath.replaceAll("../", "");
        }

        return result;
    }

    //For debugging
    private void debugIO(FileInfoInVO fInVO){
        logger.debug("======================================================");
        logger.debug(fInVO.toString());
        logger.debug("======================================================");
    }

}


