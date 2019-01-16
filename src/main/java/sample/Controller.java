package main.java.sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.util.Random;

public class Controller {
        int silaattaki, silakrita, siladef;
        boolean kt = false, df = false;
        MyPers myPers = new MyPers();
        Vrag1 vrag1 = new Vrag1();
        int onesp = 0, twosp = 0, threesp = 0;

        @FXML
        JFXButton butt = new JFXButton();
        @FXML
        JFXRadioButton rbb1, rbb2, rbb3, rbb4 = new JFXRadioButton();
        @FXML
        JFXTextArea taa = new JFXTextArea();
        @FXML
        ToggleGroup rbgroup = new ToggleGroup();

        public void glav(){
            butt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(myPers.hp <= 0){
                        taa.appendText("Вы проиграли!");
                    }else if(vrag1.hp <= 0){
                        taa.appendText("Вы выиграли!!!");
                    }else if((myPers.hp>0)&&(vrag1.hp>0)){

                        boiI();


                    }
                    else if(rbb4.isSelected()){             // -75% твоего урона, этот урон идёт тебе на лечение 3 хода
                        System.out.println("3 способность");
                    }
                }
            });
        }

        public void sposobnost1(){
            silakrita = silakrita+(silakrita/2);
            onesp++;
        }

        public void boiI(){
            sila_attaki(myPers.min_attack, myPers.max_attack);
            shanskrit(myPers.shans_krita);
            if(kt == false){
                silakrita = 0;
            }else{
                silakrita(myPers.min_krit, myPers.max_krit);
            }
            shansdef(myPers.shans_def);
            if(df == false){
                siladef = 0;
            }else{
                siladef(myPers.min_def, myPers.max_def);
            }
            iattack();
        }

        public void boiV(){
            sila_attaki(vrag1.min_attack, vrag1.max_attack);
            shanskrit(vrag1.shans_krita);
            if(kt == false){
                silakrita = 0;
            }else{
                silakrita(vrag1.min_krit, vrag1.max_krit);
            }
            shansdef(vrag1.shans_def);
            if(df == false){
                siladef = 0;
            }else{
                siladef(vrag1.min_def, vrag1.max_def);
            }
            vattack();
        }

        public void sila_attaki(int min_attack, int max_attack){
            Random rand5 = new Random();
            silaattaki = min_attack + rand5.nextInt(max_attack - min_attack);
            //ta.setText(""+silaattaki);
        }

        public void shanskrit(int proc){
            Random rand1 = new Random();
            int r = rand1.nextInt(100);
            //System.out.println(r);
            if(proc >= r){
                kt = true;
            }
        }
        public void shansdef(int proc){
            Random rand3 = new Random();
            int r = rand3.nextInt(100);
            //System.out.println(r);
            if(proc >= r){
                df = true;
            }
        }

        public void silakrita(int a, int b){
            Random rand2 = new Random();
            silakrita = a + rand2.nextInt(b-a);
            System.out.println(silakrita);
            if(rbb2.isSelected()){
                if(onesp >= 3){
                    System.out.println("Нельзя использовать способность");
                    glav();
                }else {                     // +50% КРИТ УРОНА НА 3 ХОДА
                    System.out.println("1 способность");
                    sposobnost1();
                }
            }
        }

        public void siladef(int a, int b){
            Random rand4 = new Random();
            siladef = a + rand4.nextInt(b-a);
        }

        public void vattack(){
            myPers.hp = (myPers.hp-(silaattaki + silakrita)+siladef);
            taa.appendText("\nТебя ударили на "+silaattaki+", Крит: "+silakrita+", защитился ты на "+ siladef+". Осталось хп: "+myPers.hp);
            kt = false;
            df = false;
            glav();
        }

        public void iattack(){
            vrag1.hp = (vrag1.hp-(silaattaki + silakrita)+siladef);
            taa.setText("\nТы ударил на "+silaattaki+", Крит: "+silakrita+", защитился враг на "+ siladef+". Осталось хп: "+vrag1.hp);
            kt = false;
            df = false;
            if(vrag1.hp>0){
                boiV();

            }
        }
}
