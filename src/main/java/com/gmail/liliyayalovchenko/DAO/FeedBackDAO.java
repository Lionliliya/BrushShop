package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.FeedBack;

import java.util.Date;
import java.util.List;

public interface FeedBackDAO {

    void saveFeedBack(FeedBack feedBack);

    List<FeedBack> getAllFeedBacks();

    List<FeedBack> getFeedBacksByClientId(int ClientId);

    List<FeedBack> getFeedBacksByProductId(int ProductId);

    FeedBack getFeedBackById(int id);

    void delete(FeedBack feedBack);

    void saveFeedBack(int id, Date data, int evaluation, String feedback);

    List<FeedBack> getAllFeedBacksDateUp();

    List<FeedBack> getAllFeedBacksDateDown();

    List<FeedBack> getAllFeedBacksRateDown();

    List<FeedBack> getAllFeedBacksRateUp();

    void remove(FeedBack feedBackById);
}
