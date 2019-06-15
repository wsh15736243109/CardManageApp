package com.itboye.cardmanage.retrofit;

import com.itboye.cardmanage.bean.BranchBankBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.ArrayList;

public interface CardAPI {

    /**
     * 获取支行信息
     *
     * @param cardNo
     * @param keyword
     * @return
     */
    @GET("/zmfpay/query_branch/{cardNo}/{keyword}")
    Observable<BaseResponse<ArrayList<BranchBankBean>>> getBranchInfo(@Path("cardNo") String cardNo, @Path("keyword") String keyword);

    /**
     * 获取银行卡信息
     *
     * @param card_info
     * @param card_no
     * @return
     */
    @GET("/zmfpay/card_info/{card_no}")
    Observable<BaseResponse<String>> getCardInfo(@Path("card_info") String card_info, @Path("card_no") String card_no);
}
