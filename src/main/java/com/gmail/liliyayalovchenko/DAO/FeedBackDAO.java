package com.gmail.liliyayalovchenko.DAO;


import com.gmail.liliyayalovchenko.Domains.FeedBack;

import java.util.List;

public interface FeedBackDAO {

    void saveFeedBack(FeedBack feedBack);

    List<FeedBack> getAllFeedBacks();

    List<FeedBack> getFeedBacksByClientId(int ClientId);

    List<FeedBack> getFeedBacksByProductId(int ProductId);

    FeedBack getFeedBackById(int id);

    void saveFeedBack(FeedBack feedBack, int id);

//    void delete(int id, FeedBack feedBack);

    void delete(FeedBack feedBack);
}
