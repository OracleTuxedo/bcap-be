package mti.com.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionVo implements Serializable {
    private static final long serialVersionUID = -9186253543640336258L;

    // Never change this value
    public static final String SESSION_DATA_KEY = "mtiWebUserSession";

    /** ID */
    private String sUserId;
    /** Name */
    private String sUserNm;
    /** E-Mail */
    private String sEmail;
    /** User Type */
    private String sUserTypeCd;
    /** Organization ID */
    private String orgnztId;
    /** Unique ID */
    private String uniqId;
    /** */
    private String usrIno;
    /** Admin YN */
    private String adm_usr_yn;

    /** MMP Web user */
    private String cdhdNo;
    private String hpgeId;
    private String cdhdNm;

    /** MMP Web Merchant Start */
    private String usrId;
    private String usrNm;
    private String usrClcd;// MTI,Super,Middle,Merchant
    private String mmpPrivCd; // Merchant manager, Merchant normal user, Merchant only search;
    private String telNo;
    private String hpTelNo;

    private String lgrupId;
    private String lgrupNm;
    private String mgrupId;
    private String mgrupNm;
    private String mid;
    private String merNm;
    private String langClcd;
    private String usrCtgoCd;
    private String emailAddr;
    private String mmpsvcApplGrupCd;
    /** MMP Web Merchant End */
}
