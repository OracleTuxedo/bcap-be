package maas.bcap.screen.filemanager.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import maas.bcap.screen.filemanager.vo.FileInfoInSubVO;
import maas.bcap.screen.filemanager.vo.FileInfoInVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
    public void fileUploadXp(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

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

        String fileDiv = validateFilePath(request.getParameter("fileDiv"));
        String extraPath = validateFilePath(request.getParameter("extraPath") == null ? "" : request.getParameter("extraPath"));

        String filePath  = "";
        String eMsgDtl	 = "";
        boolean fileChk = false;

        List<FileInfoInSubVO> fInSubVOList = new ArrayList<>();

        if(multipartList!= null) {
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

                    FileInfoInSubVO fInSubVO1 = FileInfoInSubVO.builder().file_nm(orginFileName).build();
                    fInSubVOList.add(fInSubVO1);


                }
            }
        }

        FileInfoInVO fInVo = FileInfoInVO.builder()
            .list(fInSubVOList)
            .build();
        realFileUpload(fInVo, multipartList, filePath);


    }

    private void realFileUpload(FileInfoInVO fInVo, List<MultipartFile> multipartFiles, String filePath) throws Exception {

        if( multipartFiles != null && multipartFiles.size() > 0 ){
            int fileCnt = multipartFiles.size();
            for( int i=0 ; i<fileCnt ; i++ ){
                MultipartFile file = multipartFiles.get(i);

                String orginFileName = file.getOriginalFilename();

                List<FileInfoInSubVO> fInVoList = fInVo.getList();
                for(FileInfoInSubVO fIn : fInVoList){
                    if(fIn.getFile_nm()!= null){
                        if(fIn.getFile_nm().equals(orginFileName)){   // fileNm 던져줘야함
                            String fileSaveName = fIn.getFile_nm();
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

}
