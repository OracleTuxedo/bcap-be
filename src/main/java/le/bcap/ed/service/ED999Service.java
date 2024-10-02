package le.bcap.ed.service;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.az.ServiceSupport;
import le.bcap.ed.dto.ED999InDto;
import le.bcap.ed.dto.ED999OutDto;
import le.bcap.ed.dto.ED999OutSub1Vo;
import le.bcap.module.ac.ac02.sac02f452r.SAC02F452RInVo;
import le.bcap.module.ac.ac02.sac02f452r.SAC02F452R;
import le.bcap.module.ac.ac02.sac02f452r.SAC02F452ROutVo;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107RInVo;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107R;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107ROutVo;
import mti.com.telegram.vo.TelegramUserDataOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ED999Service {

    private static final Logger logger = LogManager.getLogger(ED999Service.class);

    @Autowired
    private ServiceSupport support;

    @Autowired
    private SED03F107R sed03f107r;

    @Autowired
    private SAC02F452R sac02F452R;

    public ED999OutDto getListOfEDC(HttpServletRequest request, ED999InDto inDto) throws Exception {


        /// SED03F107R
        SED03F107RInVo sed03F107RInVo = SED03F107RInVo.builder()
                .prd_tp_cd(inDto.getPrd_tp_cd())
                .sno(inDto.getSno())
                .srl_stat_cd(inDto.getSrl_stat_cd())
                .srl_st_cd(inDto.getSrl_st_cd())
                .prd_cd(inDto.getPrd_cd())
                .icc_id(inDto.getIcc_id())
                .build();
        TelegramUserDataOutput<SED03F107ROutVo> sed03f107rResult = sed03f107r.call(request,  sed03F107RInVo, "WAC070100H");
        SED03F107ROutVo sed03F107ROutVo = sed03f107rResult.getOutput();

        /// SAC02F452R
        SAC02F452RInVo sac02F452RInVo = SAC02F452RInVo.builder()
                .page_no(inDto.getPage_no())
                .page_size(inDto.getPage_size())
                .mid(inDto.getMid())
                .auth_strt_date(inDto.getAuth_strt_date())
                .auth_end_date(inDto.getAuth_end_date())
                .build();
        TelegramUserDataOutput<SAC02F452ROutVo> sac02f452rResult = sac02F452R.call(request, sac02F452RInVo, "WAC070200H");
        SAC02F452ROutVo sac02F452ROutVo = sac02f452rResult.getOutput();

        List<ED999OutSub1Vo> sub1Vos = new ArrayList<>();

        // Cara 1: Enhanced for loops
//        for (SAC02F452ROutSub1Vo le : sac02F452ROutVo.sub1Vos){
//            sub1Vos.add(
//                    ED999OutSub1Vo.builder()
//                            .auth_date(le.auth_date)
//                            .pmt_date(le.pmt_date)
//                            .card_no(le.card_no)
//                            .auth_no(le.auth_no)
//                            .sale_amt(le.sale_amt)
//                            .pwcw_csh_amt(le.pwcw_csh_amt)
//                            .dcctrans_yn(le.dcctrans_yn)
//                            .build()
//            );
//        }

        // Cara 2: Stream loop
        if (sac02F452ROutVo.sub1Vos != null)
            sub1Vos = sac02F452ROutVo.sub1Vos.stream().map(le -> ED999OutSub1Vo.builder()
                    .auth_date(le.auth_date)
                    .pmt_date(le.pmt_date)
                    .card_no(le.card_no)
                    .auth_no(le.auth_no)
                    .sale_amt(le.sale_amt)
                    .pwcw_csh_amt(le.pwcw_csh_amt)
                    .dcctrans_yn(le.dcctrans_yn)
                    .build()).toList();

        return ED999OutDto.builder()
                .count(sed03F107ROutVo.count)
                .sno(sed03F107ROutVo.sno)
                .whous_cd(sed03F107ROutVo.whous_cd)
                .rack_no(sed03F107ROutVo.rack_no)
                .icc_id(sed03F107ROutVo.icc_id)
                .sim_no(sed03F107ROutVo.sim_no)
                .srl_stat_cd(sed03F107ROutVo.srl_stat_cd)
                .srl_st_cd(sed03F107ROutVo.srl_st_cd)
                .srl_loca_cd(sed03F107ROutVo.srl_loca_cd)
                .vend_no(sed03F107ROutVo.vend_no)
                .prd_cd(sed03F107ROutVo.prd_cd)
                .prd_nm(sed03F107ROutVo.prd_nm)

                .tot_cnt(sac02F452ROutVo.tot_cnt)
                .sub1Vos(sub1Vos)
                .build();
    }
}
