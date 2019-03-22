package com.capinfo.engine.promotion;

import com.capinfo.engine.data.DictBean;
import com.capinfo.engine.data.OriginalProduct;
import com.capinfo.engine.data.TaoGaiPromotionSubset;
import com.capinfo.engine.data.VersionInfo;
import com.capinfo.engine.message.MessageCode;
import com.capinfo.engine.message.MessageEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Stack;

public class PromotionTypeBehaviorImpl<T> implements PromotionTypeBehavior<T> {


    private TaoGaiPromotionSubset taoGaiPlan;

    private TaoGaiPromotionSubset promotionSubset;

    private List<DictBean> dictBeans;

    @Override
    public VersionInfo getVersion() {
        return VersionInfo.VERSION_2019;
    }

    /**
     *
     * TODO:代码未完成
     * @param data
     * @return
     */
    @Override
    public PromotionType judgementType(T data) throws Exception {
        OriginalProduct product = (OriginalProduct)data;
        PromotionType promotionType = product.getPromotionType();
        switch (promotionType) {
            case TAOGAI_STATUS:
                break;
            case FIRST_PROMOTION_STATUS:
                break;
            case SECOND_PROMOTION_STATUS:
                break;
            case NORMAL_PROMOTION_STATUS:
                break;
            case ERROR_STATUS:
                break;
            case UNCERTAIN_STATUS:
                break;
        }
        throw new Exception(new MessageCode().failMessage(MessageEnum.ERROR_CODE_UNKNOWN).getMsg());
    }

    @Override
    public MessageCode validate(T data) {
        if (data == null)
            return new MessageCode().failMessage(MessageEnum.LI_CODE_04);
        if (!(data instanceof OriginalProduct))
            return new MessageCode().failMessage(MessageEnum.LI_CODE_03, OriginalProduct.class.getName(), data.getClass());

        OriginalProduct product = (OriginalProduct)data;
        //if ()通过当前年 判断是否有新的套改规则。
        StringBuffer message = new StringBuffer("");
        if(!product.isCoverModel()){
            message.append(new MessageCode().failMessage(MessageEnum.LI_CODE_05,product.getName(),product.getIdCode()).getMsg()+"\n");
        };
        Stack<TaoGaiPromotionSubset> taoGaiHisList = product.getTaoGaiHisList();
        if (taoGaiHisList==null||taoGaiHisList.isEmpty()) {
            message.append(new MessageCode().failMessage(MessageEnum.LI_CODE_07,product.getName(),product.getIdCode()).getMsg()+"\n");
        }else{
            taoGaiPlan = taoGaiHisList.peek();
            int upStatus = taoGaiPlan.getUpStatus();//
            if(upStatus!=PromotionType.TAOGAI_STATUS.getCode()){
                message.append(new MessageCode().failMessage(MessageEnum.LI_CODE_08,product.getName(),product.getIdCode()).getMsg()+"\n");
            };

            dictBeans = loadDict("县处级以下之类云云");
            if (dictBeans == null) {
                message.append(new MessageCode().failMessage(MessageEnum.ERROR_CODE_NULL_DIC,"县处级以下之类云云").getMsg()+"\n");
            }
            if (StringUtils.isNotBlank(taoGaiPlan.getRank())){
                message.append(new MessageCode().failMessage(MessageEnum.LI_CODE_06,"没有套改前职级").getMsg()+"\n");
            }





//            if (taoGaiPlan.isLeader()){
//                message.append(new MessageCode().failMessage(MessageEnum.LI_CODE_06,"没有套改前职级").getMsg()+"\n");
//            }
        }



        if(StringUtils.isNotBlank(message.toString())){
            return new MessageCode().failMessage(message);
        }

        return new MessageCode().successMessage();
    }


    @Override
    public List<DictBean> loadDict(String type) {
        return null;
    }
}
