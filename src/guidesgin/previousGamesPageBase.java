package guidesgin;

import java.lang.String;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class previousGamesPageBase extends AnchorPane {

    protected final VBox vBox;
    protected final Label label;
    protected final VBox vBox0;
    protected final Label label0;
    protected final HBox hBox;
    protected final TextField text;
    protected final Button show;
    protected final ListView listView;

    public previousGamesPageBase() {

        vBox = new VBox();
        label = new Label();
        vBox0 = new VBox();
        label0 = new Label();
        hBox = new HBox();
        text = new TextField();
        show = new Button();
        listView = new ListView();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        getStyleClass().add("anchor");
        getStylesheets().add("/guidesgin/../help/Style.css");

        AnchorPane.setBottomAnchor(vBox, 0.0);
        AnchorPane.setLeftAnchor(vBox, 0.0);
        AnchorPane.setRightAnchor(vBox, 0.0);
        AnchorPane.setTopAnchor(vBox, 0.0);
        vBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox.setLayoutX(128.0);
        vBox.setLayoutY(112.0);
        vBox.setPrefHeight(400.0);
        vBox.setPrefWidth(600.0);
        vBox.getStyleClass().add("anchor");
        vBox.getStylesheets().add("/guidesgin/../help/Style.css");

        label.getStylesheets().add("/guidesgin/../help/Style.css");
        label.setText("Enter Game ID To Show ");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#777777"));
        VBox.setMargin(label, new Insets(20.0));
        label.setPadding(new Insets(10.0));

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setPrefHeight(89.0);
        vBox0.setPrefWidth(600.0);

        label0.setPrefHeight(17.0);
        label0.setPrefWidth(381.0);
        label0.setText("Label");
        label0.setVisible(false);

        hBox.setAlignment(javafx.geometry.Pos.CENTER);
        hBox.setPrefHeight(32.0);
        hBox.setPrefWidth(600.0);

        text.setPrefHeight(57.0);
        text.setPrefWidth(233.0);
        text.setPromptText("Game ID");
        text.getStyleClass().add("radius");
        text.getStylesheets().add("/guidesgin/../help/Style.css");
        HBox.setMargin(text, new Insets(20.0));
        text.setPadding(new Insets(10.0));

        show.setMnemonicParsing(false);
        show.setPrefHeight(31.0);
        show.setPrefWidth(155.0);
        show.getStylesheets().add("/guidesgin/../help/Style.css");
        show.setText("Show");
        show.setTextFill(javafx.scene.paint.Color.WHITE);

        listView.setFocusTraversable(false);
        listView.setPrefHeight(200.0);
        listView.setPrefWidth(200.0);

        vBox.getChildren().add(label);
        vBox0.getChildren().add(label0);
        hBox.getChildren().add(text);
        hBox.getChildren().add(show);
        vBox0.getChildren().add(hBox);
        vBox.getChildren().add(vBox0);
        vBox.getChildren().add(listView);
        getChildren().add(vBox);

    }
}
