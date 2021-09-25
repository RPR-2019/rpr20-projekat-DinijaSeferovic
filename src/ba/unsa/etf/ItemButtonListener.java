package ba.unsa.etf;

import ba.unsa.etf.dal.CatDAO;
import ba.unsa.etf.dal.DogDAO;
import ba.unsa.etf.dal.User;

public interface ItemButtonListener {

    public void onClickListener(DogDAO dog);
    public void onClickListener(CatDAO cat);
    public void onClickListener(User user);
}
