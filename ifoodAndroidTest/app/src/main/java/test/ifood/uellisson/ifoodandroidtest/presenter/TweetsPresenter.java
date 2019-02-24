package test.ifood.uellisson.ifoodandroidtest.presenter;

import java.util.ArrayList;
import java.util.List;

import test.ifood.uellisson.ifoodandroidtest.model.Tweet;

public class TweetsPresenter extends Presenter<TweetsPresenter.View>{
    List<Tweet> lt;

    @Override
    public void initialize() {
        super.initialize();
        //TODO implements request API Twitter
        getView().showLoading();
        Tweet t1 = new Tweet("mensagem 1", "hoje");
        Tweet t2 = new Tweet("mensagem 2", "ontem");
        Tweet t3 = new Tweet("mensagem 3", "quinta");
        lt = new ArrayList<>();
        lt.add(t1);
        lt.add(t2);
        lt.add(t3);
        getView().showTwittes(lt);
        getView().hideLoading();
    }

    public interface View extends Presenter.BaseView {
        void showTwittes (List<Tweet> tweetList);
    }
}
