package maas.bcap.craniumtest;

import maas.bcap.module.ac.ac04.sac04v127u.SAC04V127UInVo;
import maas.bcap.module.ac.ac04.sac04v227u.SAC04V227UInVo;
import maas.bcap.module.ac.ac04.sac04v227u.SAC04V227UOutVo;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228RInVo;
import maas.bcap.module.ac.ac04.sac04v228r.SAC04V228ROutVo;
import maas.bcap.module.ac.ac06.sac06f244r.SAC06F244RInVo;
import maas.bcap.module.ac.ac06.sac06f244r.SAC06F244ROutVo;
import maas.bcap.module.ac.ac06.sac06f245r.SAC06F245RInVo;
import maas.bcap.module.ac.ac06.sac06f245r.SAC06F245ROutVo;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610RInVo;
import maas.bcap.module.ac.ac06.sac06v610r.SAC06V610ROutVo;
//import maas.bcap.module.ac.ac06.sac06v700r.SAC06V700RInVo;
//import maas.bcap.module.ac.ac06.sac06v700r.SAC06V700ROutVo;
//import maas.bcap.module.ac.ac07.sac07f001r.SAC07F001RInVo;
//import maas.bcap.module.ac.ac07.sac07f001r.SAC07F001ROutVo;
//import maas.bcap.module.ac.ac20.sac20f002u.SAC20F002UInVo;
//import maas.bcap.module.ac.ac20.sac20f002u.SAC20F002UOutVo;
//import maas.bcap.module.az.az05.saz05f041u.SAZ05F041UInSub1Vo;
//import maas.bcap.module.az.az05.saz05f041u.SAZ05F041UInVo;
//import maas.bcap.module.az.az05.saz05f041u.SAZ05F041UOutVo;
import maas.bcap.module.mc.mc02.smc02v051u.SMC02V051UInVo;
import maas.bcap.module.mc.mc02.smc02v051u.SMC02V051UOutVo;
import maas.bcap.module.mc.mc15.smc15v016u.SMC15V016UInVo;
import maas.bcap.module.mc.mc15.smc15v016u.SMC15V016UOutVo;
import maas.bcap.module.mc.mc15.smc15v018u.SMC15V018UInVo;
import maas.bcap.module.mc.mc15.smc15v018u.SMC15V018UOutVo;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.util.TelegramBuilder;
import mti.com.telegram.util.TelegramUtil;
import mti.com.telegram.vo.*;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * InVo -> Request String Panjang -> Weblogic -> Tuxedo -> Weblogic -> Response String panjang -> OutVo
 * Semangat 45 , tutor Github
 */

public class FalahTest {

    private static final Logger logger = LogManager.getLogger(FalahTest.class);

    /// Request
    public static void interfaceTuxedoParseRequest() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Request START  ###################################");
        TelegramUserDataInput userData = new TelegramUserDataInput();
        userData.setTx_code("SMC15V016U");
        userData.setScrn_id("WED030120H");
        userData.setClient_ip_no("172.16.20.11");
        userData.setOp_id("1787130271");
        userData.setSync_type("A");
        userData.setRspn_svc_code("");
        userData.setAsync_rspn_yn("0");
        userData.setTtl_use_flag(0);
        userData.setLang_type("EN");

//        /// SAC04V228R Single
//        SAC04V228RInVo sac04V228RInVo = SAC04V228RInVo.builder()
//            .snd_date("20210701")
//            .ems_seq_num(84075)
//            .build();

//  00000548                                SAC04V228R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010120959235                         00000      00000                                                                        EN                                                                                                                                              00000000                     20210701000000000000084075
//  00000550Falah   202410101207530020000000SAC04V228R              MTI S                        Falah   20241010120753002000000020241010120753000594WEB       172.16.20.11                                WED030120H N1787130271     020241010120753000597                    A000000      00010                                                                        EN                                                                                                                                             D00000047                     20210701000000000000084075@@

        /// SAC06F244R Single
//        SAC06F244RInVo sac06F244RInVo = SAC06F244RInVo.builder()
//            .prem_chk_no("0000000012")
//            .prem_chk_nm("coba")
//            .sql_id("TBACPREMCHKNRSLT_S0004")
//            .purc_sttl_biz_clcd("5")
//            .page_size(2)
//            .build();

//  00000935                                SAC06F244R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010115016306                         00000      00000                                                                        EN                                                                                                                                              00000000                     0000000012coba                                                                                                TBACPREMCHKNRSLT_S0004                  5                                                                                                                                                                                                                                                                000002
//  00000937Falah   202410101233040020000000SAC06F244R              MTI S                        Falah   20241010123304002000000020241010123304000230WEB       172.16.20.11                                WED030120H N1787130271     020241010123304000231                    A000000      00010                                                                        EN                                                                                                                                             D00000434                     0000000012coba                                                                                                TBACPREMCHKNRSLT_S0004                  5                                                                                                                                                                                                                                                                000002@@

        /// SAC06F245R Single
//        SAC06F245RInVo sac06F245RInVo = SAC06F245RInVo.builder()
//            .build();

//  00004618                                SAC06F245R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010132454763                         00000      00000                                                                        EN                                                                                                                                              00000000
//  00004620Falah   202410101326100020000000SAC06F245R              MTI S                        Falah   20241010132610002000000020241010132610000817WEB       172.16.20.11                                WED030120H N1787130271     020241010132610000820                    A000000      00010                                                                        EN                                                                                                                                             D00004117                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     @@

        /// SAC06V610R Single
//        SAC06V610RInVo sac06V610RInVo = SAC06V610RInVo.builder()
//            .mid("70000254969")
//            .acq_mb_no("002")
//            .acct_mgmt_bk_cd("0080017")
//            .pmt_acct_no("1170005384953")
//            .build();

//  00000563                                SAC06V610R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010133838576                         00000      00000                                                                        EN                                                                                                                                              00000000                     7000025496900200800171170005384953
//  00000565Falah   202410101340260020000000SAC06V610R              MTI S                        Falah   20241010134026002000000020241010134026000428WEB       172.16.20.11                                WED030120H N1787130271     020241010134026000430                    A000000      00010                                                                        EN                                                                                                                                             D00000062                     7000025496900200800171170005384953       @@

        /// SAC06V700R Single
//        SAC06V700RInVo sac06V700RInVo = SAC06V700RInVo.builder()
//            .appl_date("20121002")
//            .exrt_prv_inst_cd("901")
//            .exrt_op_no("0")
//            .std_curcy_cd("840")
//            .relt_curcy_cd("360")
//            .mb_no("999")
//            .build();

//  00000543                                SAC06V700R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241010144100353                         00000      00000                                                                        EN                                                                                                                                              00000000                     201210029010840360999
//  00000545Falah   202410101457580020000000SAC06V700R              MTI S                        Falah   20241010145758002000000020241010145758000603WEB       172.16.20.11                                WED030120H N1787130271     020241010145758000604                    A000000      00010                                                                        EN                                                                                                                                             D00000042                     201210029010840360999@@

        /// SAC07F001R Single
//        SAC07F001RInVo sac07F001RInVo = SAC07F001RInVo.builder()
//            .attach_file_id("232")
//            .build();

//  00000530                                SAC07F001R              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241011135400653                         00000      00000                                                                        EN                                                                                                                                              00000000                     232
//

        /// SAC20F002U Single
//        SAC20F002UInVo sac20F002UInVo = SAC20F002UInVo.builder()
//            .pmt_ref_no("0082023110000000816")
//            .pmt_proc_rslt_cd("2")
//            .build();

//  00000542                                SAC20F002U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241011141644949                         00000      00000                                                                        EN                                                                                                                                              00000000                     00820231100000008162
//  00000544Falah   202410111428370020000000SAC20F002U              MTI S                        Falah   20241011142837002000000020241011142837000776WEB       172.16.20.11                                WED030120H N1787130271     020241011142837000778                    A000000      00010                                                                        EN                                                                                                                                             D00000041                     00820231100000008162@@

        /// SAZ05F041U Multi
//        SAZ05F041UInSub1Vo saz05F041UInSub1Vo = SAZ05F041UInSub1Vo.builder()
//            .biz_clcd("U")
//            .usr_id("9999999991")
//            .apvl_mgr_usr_id("9999999990")
//            .apvl_mgr_clcd("A")
//            .data_stat_cd("U")
//            .build();
//
//        SAZ05F041UInVo saz05F041UInVo = SAZ05F041UInVo.builder()
//            .sub1_vo(List.of(saz05F041UInSub1Vo))
//            .build();

//  00000563                                SAZ05F041U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241014125332614                         00000      00000                                                                        EN                                                                                                                                              00000000                     00000001U9999999991     9999999990     AU
//

        /// SAC04V227U Single
//        SAC04V227UInVo sac04V227UInVo = SAC04V227UInVo.builder()
//            .snd_date("20161214")
//            .ems_seq_num(3764)
//            .build();

//  00000548                                SAC04V227U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241014134001612                         00000      00000                                                                        EN                                                                                                                                              00000000                     20161214000000000000003764
//  00000550Falah   202410141342040020000000SAC04V227U              MTI S                        Falah   20241014134204002000000020241014134204000237WEB       172.16.20.11                                WED030120H N1787130271     020241014134204000238                    A000000      00010                                                                        EN                                                                                                                                             D00000047                     20161214000000000000003764@@

        /// SMC02V051U Single
//        SMC02V051UInVo smc02V051UInVo = SMC02V051UInVo.builder()
//            .transaction_no("0110")
//            .npwp("16189433013000")
//            .ktpno("99M999990022811")
//            .pmt_acct_no("1170005384953")
//            .build();

//  00000625                                SMC02V051U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241014141330622                         00000      00000                                                                        EN                                                                                                                                              00000000                     0110                                       16189433013000      99M999990022811     1170005384953
//  00000627Falah   202410141416070020000000SMC02V051U              MTI S                        Falah   20241014141607002000000020241014141607000428WEB       172.16.20.11                                WED030120H N1787130271     020241014141607000429                    A000000      00010                                                                        EN                                                                                                                                             D00000124                     0110                                       16189433013000      99M999990022811     1170005384953       @@

        /// SMC15V018U Single
//        SMC15V018UInVo smc15V018UInVo = SMC15V018UInVo.builder()
//            .transactionNumber("0100")
//            .processingCode("100014")
//            .systemTraceAuditNumber("445686")
//            .authorizationTime("153200")
//            .authorizationDate("20230327")
//            .tid("75412631")
//            .mid("71000612631")
//            .userAppID("987654324")
//            .storeCode("LM01001452")
//            .sn("11272PT62126580")
//            .build();

//  00000610                                SMC15V018U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241014153151162                         00000      00000                                                                        EN                                                                                                                                              00000000                     0100100014445686153200202303277541263171000612631987654324LM0100145211272PT62126580
//  00000612Falah   202410141543220020000000SMC15V018U              MTI S                        Falah   20241014154322002000000020241014154322000697WEB       172.16.20.11                                WED030120H N1787130271     020241014154322000699                    A000000      00010                                                                        EN                                                                                                                                             D00000109                     0100100014445686153200202303277541263171000612631987654324LM0100145211272PT62126580     @@

        /// SMC15V016U Single
        SMC15V016UInVo smc15V016UInVo = SMC15V016UInVo.builder()
            .transactionNumber("0100")
            .processingCode("100019")
            .systemTraceAuditNumber("426856")
            .authorizationTime("152800")
            .authorizationDate("20230508")
            .status("U")
            .mid("71000260723")
            .gid("90000009")
            .storeCode("1234567891")
            .build();

//  00000732                                SMC15V016U              MTI S                                                                            UNIT      192.168.1.16                    088FC37E596F                           020241014162412181                         00000      00000                                                                        EN                                                                                                                                              00000000                     010010001942685615280020230508U71000260723900000091234567891
//  00000734Falah   202410141631220020000000SMC15V016U              MTI S                        Falah   20241014163122002000000020241014163122000282WEB       172.16.20.11                                WED030120H N1787130271     020241014163122000283                    A000000      00010                                                                        EN                                                                                                                                             D00000231                     010010001942685615280020230508U71000260723900000091234567891                                                                                                                                                      @@

        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn<SMC15V016UInVo> telegramIn = TelegramBuilder.getTelegramIn(userData, smc15V016UInVo);

        logger.info(telegramIn.getData().getData().toString());

        byte[] arrayOfByte = byteEncoder.convertObjectToBytes(telegramIn, true);
        String request = new String(arrayOfByte, StandardCharsets.UTF_8);

        logger.info(request);
        logger.info("################################### Request END  ###################################");
    }

    /// Response
    public static void interfaceTuxedoParseResponse() throws Exception {
        logger.atLevel(Level.ALL);

        logger.info("################################### Response START  ###################################");

        /// SAC04V228R
//        String response = "00001180devaps01202410101109470014257200SAC04V228R              MTI R                        devaps0120241010110947001425720020241010110947899   UNIT      192.168.1.16                    088FC37E596F                           020241010110947899   20241010110947385306  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000243                            155057     MSR_40001_MID_71000029413_402_20210701_20210701_1561.csv                                                                                                                                                1   @@";

        /// SAC06F244R
//        String response = "00001489devaps01202410101150150024257200SAC06F244R              MTI R                        devaps0120241010115015002425720020241010115016306   UNIT      192.168.1.16                    088FC37E596F                           020241010115016306   20241010115015853959  0  00        000       NMCAP0001                                                        EN                                                                                                                                             N00000525                     30Inquiry has been completed.                                                                                                                                                                                                                                                                                                                                                                                     01Inquiry has been completed.                                                                         D00000452                                                                                                                                                                                                                                                                                          2       1000000001220170113coba                                                                                                1674110016   5                     20170113@@";

        /// SAC06F245R
//        String response = "00000968devaps01202410101324540034257300SAC06F245R              MTI R                        devaps0120241010132454003425730020241010132454763   UNIT      192.168.1.16                    088FC37E596F                           020241010132454763   20241010132454296852  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                     0000000912@@";

        /// SAC06V610R
//        String response = "00001163devaps01202410101338380044256900SAC06V610R              MTI R                        devaps0120241010133838004425690020241010133838576   UNIT      192.168.1.16                    088FC37E596F                           020241010133838576   20241010133838297945  0  00        000       NAZAP0005                                                        EN                                                                                                                                             N00000625                     30The Transaction Successfully Ended.                                                                                                                                                                                                                                                                                                                                                                             02inquiry process success.                                                                            The Transaction Successfully Ended.                                                                 D00000026                     00009@@";

        /// SAC06V700R
//        String response = "00001004devaps01202410101440590044257300SAC06V700R              MTI R                        devaps0120241010144059004425730020241010144100353   UNIT      192.168.1.16                    088FC37E596F                           020241010144100353   20241010144059924742  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000067                     00010101                  0                  0@@";

        /// SAC07F001R
//          String response = "00001925devaps01202410111354000024260300SAC07F001R              MTI R                        devaps0120241011135400002426030020241011135400653   UNIT      192.168.1.16                    088FC37E596F                           020241011135400653   20241011135400216459  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000988                            1232     1   D:\\MTIDEV\\MaasDev\\workspace\\maas\\src\\main\\resources\\upload\\AZ\\/01/tmp                                                                                                                                                                                                                                                                                                                                                                                                                                               Penguins.jpg                                                                                                                                                                                            Penguins[20160808140401994].jpg                                                                                                                                                                                             000000000000000777835    1U@@";

        /// SAC20F002U
//           String response = "00000968devaps01202410111416440034260000SAC20F002U              MTI R                        devaps0120241011141644003426000020241011141644949   UNIT      192.168.1.16                    088FC37E596F                           020241011141644949   20241011141644462179  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                              1@@";

        /// SAZ05F041U
//        String response = "00000968devaps01202410141253330024257300SAZ05F041U              MTI R                        devaps0120241014125333002425730020241014125332614   UNIT      192.168.1.16                    088FC37E596F                           020241014125332614   20241014125333186110  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000031                              1@@";

        /// SAC04V227U
//        String response = "00001962devaps01202410141340020034257100SAC04V227U              MTI R                        devaps0120241014134002003425710020241014134001612   UNIT      192.168.1.16                    088FC37E596F                           020241014134001612   20241014134002165344  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00001025                     0000SUCCESS                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 @@";

        /// SMC02V051U
//        String response = "00004965devaps01202410141413310034257500SMC02V051U              MTI R                        devaps0120241014141331003425750020241014141330622   UNIT      192.168.1.16                    088FC37E596F                           020241014141330622   20241014141331646013  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00004028                     0110            1413312024101400800Approved                                                                                           16189433013000      99M999990022811     1170005384953                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            @@";

        /// SMC15V018U
//        String response = "00001127devaps01202410141531510044257400SMC15V018U              MTI R                        devaps0120241014153151004425740020241014153151162   UNIT      192.168.1.16                    088FC37E596F                           020241014153151162   20241014153151645099  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000190                     01101000144456861531512024101407File Not Found                                                                                     7541263171000612631987654324LM01001452@@";

        /// SMC15V016U
        String response = "00001100devaps01202410141624120044257500SMC15V016U              MTI R                        devaps0120241014162412004425750020241014162412181   UNIT      192.168.1.16                    088FC37E596F                           020241014162412181   20241014162412953620  0  00        000                                                                        EN                                                                                                                                             N00000425                     30                                                                                                                                                                                                                                                                                                                                                                                                                00D00000163                     01101000194268561624122024101400SUCCESS                                                                                            71000260723@@";

        /// Imitate Response from Tuxedo
        byte[] arrayOfByte = response
            .getBytes();

        SMC15V016UOutVo output = new SMC15V016UOutVo();

        TelegramUserDataOutput<SMC15V016UOutVo> telegramUserDataOutput = parse(arrayOfByte, output);

        output = telegramUserDataOutput.getOutput();

        logger.info(output.getClass().getSimpleName());
        logger.info(output.toString());

        logger.info("################################### Response END  ###################################");

    }

    public static <T> TelegramUserDataOutput<T> parse(byte[] arrayOfByte, T output) throws Exception {
        T object = null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte);
//        logger.info(telegramHeader.toString());
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut<T> telegramOut1 = TelegramBuilder.<T>getTelegramOutData(output);
            ByteDecoder<TelegramOut<T>> byteDecoder1 = new ByteDecoder<>();
            TelegramOut<T> telegramOut2 = byteDecoder1.convertBytes2Object(arrayOfByte, telegramOut1, true);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                throw new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramUserDataOutput<T> telegramUserDataOutput = new TelegramUserDataOutput<T>();
            telegramUserDataOutput.setMessage(telegramMessage1);
            telegramUserDataOutput.setOutput(object);
            telegramUserDataOutput.setHeader(telegramHeader);

            return telegramUserDataOutput;
        }

        throw new TelegramNestedRuntimeException("No Data");
    }

    public static <T> TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
        TelegramHeader telegramHeader = new TelegramHeader();
        try {
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 0, 500);
            ByteDecoder<TelegramHeader> byteDecoder = new ByteDecoder<>();
            telegramHeader = byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramHeader;
    }
}
