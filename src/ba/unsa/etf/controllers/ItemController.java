package ba.unsa.etf.controllers;

import ba.unsa.etf.ItemButtonListener;
import ba.unsa.etf.dal.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ItemController {


    @FXML
    private Label nameUserLabel;

    @FXML
    private Label usernameUserLabel;

    @FXML
    private Label passwordUserLabel;

    @FXML
    private Label emailUserLabel;

    @FXML
    private Label workingUserLabel;

    @FXML
    private Label roomatesUserLabel;

    @FXML
    private Label yardUserLabel;

    @FXML
    private Label homeworkUserLabel;

    @FXML
    private Label petsUserLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label sexLabel;

    @FXML
    private Label breedLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label adviceLabel = new Label();

    @FXML
    private Label adoptionLabel = new Label();

    @FXML
    private Label urgentLabel = new Label();

    @FXML
    private ImageView img;

    @FXML
    private Button heartButton;

    @FXML
    private Button unHeartButton;

    @FXML
    private Button deleteButton;


    private DogDAO dog;
    private CatDAO cat;
    private UserDAO userDao = UserDAO.getInstance();
    private User user;
    private ItemButtonListener myListener;

    @FXML
    public void clickHeart(MouseEvent mouseEvent) {
        if (dog!=null) myListener.onClickListener(dog);
        else if (cat!=null) myListener.onClickListener(cat);
        Image heart=new Image("/img/fulllikeicon.png");
        ImageView iv=new ImageView(heart);
        iv.setFitHeight(30);
        iv.setFitWidth(30);
        heartButton.setGraphic(iv);
    }

    @FXML
    public void unclickHeart(MouseEvent mouseEvent) {
        if (dog!=null) myListener.onClickListener(dog);
        else if (cat!=null) myListener.onClickListener(cat);
    }

    @FXML
    public void deleteUser(MouseEvent mouseEvent) {
        myListener.onClickListener(user);
    }

    @FXML
    public void adoptedClick(MouseEvent mouseEvent) {
        if (dog!=null) {
            DogDAO selectedDog = DogDAO.getInstance();
            selectedDog.setAdopted(dog);
        }
        else if (cat!=null)  {
            CatDAO selectedCat = CatDAO.getInstance();
            selectedCat.setAdopted(cat);
        }
        adoptionLabel.setText("Adoption status: Adopted");
    }

    @FXML
    public void urgentClick(MouseEvent mouseEvent) {
        if (dog!=null) {
            DogDAO selectedDog = DogDAO.getInstance();
            selectedDog.setUrgent(dog);
        }
        else if (cat!=null)  {
            CatDAO selectedCat = CatDAO.getInstance();
            selectedCat.setUrgent(cat);
        }
        adoptionLabel.setText(adoptionLabel.getText()+" (urgent)");
    }

    public void setDogData(DogDAO dog, ItemButtonListener myListener) {
        this.dog = dog;
        this.myListener = myListener;
        nameLabel.setText(dog.getName());
        sexLabel.setText("Sex: " + dog.getSex());
        ageLabel.setText("Age: " + dog.getAge());
        breedLabel.setText("Breed: " + dog.getBreed());
        Image image = new Image(getClass().getResourceAsStream(dog.getImgSrc()));
        img.setImage(image);
        if (dog.getUrgent()==1) urgentLabel.setText("URGENT");
    }

    public void setCatData(CatDAO cat, ItemButtonListener myListener) {
        this.cat = cat;
        this.myListener = myListener;
        nameLabel.setText(cat.getName());
        sexLabel.setText("Sex: " + cat.getSex());
        ageLabel.setText("Age: " + cat.getAge());
        breedLabel.setText("Breed: " + cat.getBreed());
        Image image = new Image(getClass().getResourceAsStream(cat.getImgSrc()));
        img.setImage(image);
        if (cat.getUrgent()==1) urgentLabel.setText("URGENT");
    }

    public void setPetData(Pet pet, ItemButtonListener myListener) {
        if (pet instanceof DogDAO) dog = (DogDAO) pet;
        else if (pet instanceof CatDAO) cat = (CatDAO) pet;
        this.myListener = myListener;
        nameLabel.setText(pet.getName());
        sexLabel.setText("Sex: " + pet.getSex());
        ageLabel.setText("Age: " + pet.getAge());
        breedLabel.setText("Breed: " + pet.getBreed());
        if (getClass().getResourceAsStream(pet.getImgSrc())!=null) {
            Image image = new Image(getClass().getResourceAsStream(pet.getImgSrc()));
            img.setImage(image);
        }
        else {
            try {
                FileInputStream inputstream = new FileInputStream(pet.getImgSrc());
                Image image = new Image(inputstream);
                img.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (pet.getAdopted()==1) adoptionLabel.setText("Adoption status: Adopted");
        else adoptionLabel.setText("Adoption status: Not Adopted");
        if (pet.getUrgent()==1) adoptionLabel.setText(adoptionLabel.getText()+ " (urgent)");

        if (userDao.getCurrentUser()!=null) {
            adviceLabel.setText(calculateAdvice(dog,cat));
        }
    }

    private String calculateAdvice(DogDAO dog, CatDAO cat) {
        if (dog!=null) {
            return petAdvice(dog);
        }
        if (cat!=null) {
            return petAdvice(cat);
        }
        return "";
    }

    private String petAdvice(Pet pet) {
        String result = "";
        int hoursInADay = 24;
        int avgSleepHours = 8;

        if (userDao.getProfileSet(userDao.getCurrentUser().getUsername())==0) {
            result = "Please complete your profile to get an approval";
        }
        //If a pet needs more dedication than user can give it
        else if (pet.getDedicationHours()> hoursInADay - avgSleepHours - userDao.getCurrentUser().getWorkhours() && userDao.getCurrentUser().getHomework()==0) {
            result = "Disapproved: This pet demands more time in a day than you have freetime";
        }
        else if (pet.getPeopleTolerance()==0 && userDao.getCurrentUser().getRoomates()>1) {
            result = "Disapproved: This pet does not tolerate other people in a household";
        }
        else if (pet.getPetTolerance()==0 && userDao.getCurrentUser().getPets()==1) {
            result = "Disapproved: This pet does not tolerate other pets in a household";
        }
        else if (pet.getYardNeed()==1 && userDao.getCurrentUser().getYard()==0) {
            result = "Disapproved: This pet prefers having a yard";
        }
        else {
            result = "Approved";
            userDao.setApproved(userDao.getCurrentUser());
        }
        return result;
    }


    public String  interpretingData (int data) {
        if (data==1) return "Yes";
        else return "No";
    }

    public void setUserData (User user, ItemButtonListener myListener) {
        this.user = user;
        this.myListener = myListener;
        nameUserLabel.setText("name: " + user.getName());
        usernameUserLabel.setText("username: " + user.getUsername());
        passwordUserLabel.setText("pass: " + user.getPassword());
        emailUserLabel.setText("email: " + user.getEmail());
        workingUserLabel.setText("Working: " + user.getWorkhours() + "h");
        roomatesUserLabel.setText("Roomates: " + interpretingData(user.getRoomates()));
        yardUserLabel.setText("Yard: " + interpretingData(user.getYard()));
        homeworkUserLabel.setText("Work from home: " + interpretingData(user.getHomework()));
        petsUserLabel.setText("Other pets: " + interpretingData(user.getPets()));
        if (user.getAppointment()==null) dateLabel.setText("No appointments");
        else dateLabel.setText("Appointment date: " + user.getAppointment().toString());
    }

}
