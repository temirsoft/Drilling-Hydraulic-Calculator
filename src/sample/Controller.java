package sample;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField casingInnerDiametr;

    @FXML
    private TextField casingOuterDiametr;

    @FXML
    private TextField openholeInnerDiametr;

    @FXML
    private TextField openholeOuterDiametr;

    @FXML
    private TextField hwdpInnerDiametr;

    @FXML
    private TextField hwdpOuterDiametr;

    @FXML
    private TextField drillcollarInnerDiametr;

    @FXML
    private TextField drillcollarOuterDiametr;

    @FXML
    private TextField firstDpInnerDiametr;

    @FXML
    private TextField firstDpOuterDiametr;

    @FXML
    private TextField secondDpInnerDiametr;

    @FXML
    private TextField secondDpOuterDiametr;

    @FXML
    private TextField pumpRate;

    @FXML
    private Button pressCountButton;

    @FXML
    private TextField txFAnnularVolume;

    @FXML
    private TextField txFInnerVolume;

    @FXML
    private TextField txFDisplacement;

    @FXML
    private TextField txFLagTime;

    @FXML
    private TextField txFRoundTripTime;

    @FXML
    private TextField casingLength;

    @FXML
    private TextField hwdpLength;

    @FXML
    private TextField firstDpLength;

    @FXML
    private TextField openHoleLength;

    @FXML
    private TextField drillCollarLength;

    @FXML
    private TextField secondDpLength;

    @FXML
    void initialize() {
        pressCountButton.setOnAction(event -> {
            //Casing parameters
            String casingID,casingLen;
            //Open Hole parameters
            String openHoleID,openHoleLen;
            //1st DP parameters
            String fDpID,fDpOD,fDpLen;
            //2nd DP parameters
            String sDpID,sDpOD,sDpLen;
            //Drill Collar parameters
            String drillCollarID,drillCollarOD,drillCollarLen;
            //HWDP parameters
            String hwdpID,hwdpOD,hwdpLen;
            //Pump rate
            String pumpR;

            //Variables
            Double volumeCasing, volumeOpenHole, totalVolume;
            Double closeEndDisplacementFDP, openEndDisplacementFDP;
            Double closeEndDisplacementSDP, openEndDisplacementSDP;
            Double closeEndDisplacementDC, openEndDisplacementDC;
            Double closeEndDisplacementHWDP, openEndDisplacementHWDP;

            Double innerVolumeFDP;
            Double innerVolumeSDP;
            Double innerVolumeDC;
            Double innerVolumeHWDP;

            Double totalCloseENDResult;
            Double totalOpenEndResult;
            //Annular volume
            Double annularVolume;
            //Displacement
            Double displacement;
            //Inner volume of Instrument
            Double innerVolume;
            //Lag time
            Double lagTime;
            //Round Trip Time
            Double roundTT;

            casingID = casingInnerDiametr.getText();
            casingLen = casingLength.getText();
            openHoleID = openholeInnerDiametr.getText();
            openHoleLen = openHoleLength.getText();

            //Total Volume Open hole and Casing
            volumeCasing = ((Double.parseDouble(casingID)*Double.parseDouble(casingID))*0.5067)*Double.parseDouble(casingLen);
            volumeOpenHole = ((Double.parseDouble(openHoleID)*Double.parseDouble(openHoleID))*0.5067)*Double.parseDouble(openHoleLen);
            totalCloseENDResult = volumeCasing + volumeOpenHole;

            //Volume instrument 1 st DP
            fDpID = firstDpInnerDiametr.getText();
            fDpOD = firstDpOuterDiametr.getText();
            fDpLen = firstDpLength.getText();
            closeEndDisplacementFDP = ((Double.parseDouble(fDpOD)*Double.parseDouble(fDpOD))*0.5067)*Double.parseDouble(fDpLen);
            innerVolumeFDP = ((Double.parseDouble(fDpID)*Double.parseDouble(fDpID))*0.5067)*Double.parseDouble(fDpLen);
            openEndDisplacementFDP = (closeEndDisplacementFDP) - ((Double.parseDouble(fDpID)*Double.parseDouble(fDpID))*0.5067)*Double.parseDouble(fDpLen);

            //Volume instrument 2 st DP
            sDpID = secondDpInnerDiametr.getText();
            sDpOD = secondDpOuterDiametr.getText();
            sDpLen = secondDpLength.getText();
            closeEndDisplacementSDP = ((Double.parseDouble(sDpOD)*(Double.parseDouble(sDpOD))*0.5067)*Double.parseDouble(fDpLen));
            innerVolumeSDP = ((Double.parseDouble(sDpID)*(Double.parseDouble(sDpID))*0.5067)*Double.parseDouble(sDpLen));
            openEndDisplacementSDP = (closeEndDisplacementFDP) - ((Double.parseDouble(sDpID)*(Double.parseDouble(sDpID))*0.5067)*Double.parseDouble(sDpLen));

            //Volume instrument Drill Collar
            drillCollarID = drillcollarInnerDiametr.getText();
            drillCollarOD = drillcollarOuterDiametr.getText();
            drillCollarLen = drillCollarLength.getText();
            closeEndDisplacementDC = (Double.parseDouble(drillCollarOD)*(Double.parseDouble(drillCollarOD))*0.5067)*Double.parseDouble(drillCollarLen);
            innerVolumeDC = (Double.parseDouble(drillCollarID)*(Double.parseDouble(drillCollarID))*0.5067)*Double.parseDouble(drillCollarLen);
            openEndDisplacementDC = (closeEndDisplacementFDP) - (Double.parseDouble(drillCollarID)*(Double.parseDouble(drillCollarID))*0.5067)*Double.parseDouble(drillCollarLen);

            //Volume instrument Drill Collar
            hwdpID = hwdpInnerDiametr.getText();
            hwdpOD = hwdpOuterDiametr.getText();
            hwdpLen = hwdpLength.getText();
            closeEndDisplacementHWDP = ((Double.parseDouble(hwdpOD)*(Double.parseDouble(hwdpOD))*0.5067)*Double.parseDouble(hwdpLen));
            innerVolumeHWDP = ((Double.parseDouble(hwdpID)*(Double.parseDouble(hwdpID))*0.5067)*Double.parseDouble(hwdpLen));
            openEndDisplacementHWDP = (closeEndDisplacementFDP) - ((Double.parseDouble(hwdpID)*(Double.parseDouble(hwdpID))*0.5067)*Double.parseDouble(hwdpLen));

            annularVolume = totalCloseENDResult -(closeEndDisplacementFDP+closeEndDisplacementSDP
            +closeEndDisplacementDC + closeEndDisplacementHWDP);

            displacement = openEndDisplacementFDP + openEndDisplacementSDP
                    + openEndDisplacementDC + openEndDisplacementHWDP;

            innerVolume = innerVolumeFDP + innerVolumeSDP
                    + innerVolumeDC + innerVolumeHWDP;

            pumpR = pumpRate.getText();

            lagTime = annularVolume / (Double.parseDouble(pumpR));

            roundTT = (annularVolume + innerVolume)/(Double.parseDouble(pumpR));

            DecimalFormat df = new DecimalFormat("#0.00");

            txFAnnularVolume.setText(String.valueOf(df.format(annularVolume)));
            txFInnerVolume.setText(String.valueOf(df.format(innerVolume)));
            txFDisplacement.setText(String.valueOf(df.format(displacement)));
            txFLagTime.setText(String.valueOf(df.format(lagTime)));
            txFRoundTripTime.setText(String.valueOf(df.format(roundTT)));


        });

    }
}

