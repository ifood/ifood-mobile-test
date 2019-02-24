package test.ifood.uellisson.ifoodandroidtest.presenter;

public class Presenter <V extends Presenter.BaseView> {

    private V view;

    public V getView() {
        return view;
    }

    public void setView(V view) {
        this.view = view;
    }

    public void initialize () {}

    interface BaseView {
        void showLoading();
        void hideLoading();
    }
}
