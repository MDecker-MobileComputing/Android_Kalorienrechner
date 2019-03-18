package de.mide.kalorienrechner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * Java-Klasse für das Fragment. Es wird ein Energiewert (für eine Mahlzeit) eingeben,
 * der je nach gewählten RadioButton als Kilokalorien- oder Kilojoule-Wert interpretiert
 * wird. Eine Kilokalorie entspricht in etwa 4,18686 Kilojoule.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class EnergieFragment extends Fragment {

    /** RadioButton die ausgewählt ist, wenn Eingabe in Einheit Kilokalorien. */
    protected RadioButton _radioButtonKCal  = null;

    /**
     * UI-Element für Eingabe (Zahl) der Energie der Mahlzeit in der mit RadioButton
     * gewählten Einheit (kCal oder KJ).
     */
    protected EditText _energieTextEdit = null;


    /**
     * Layout-Datei für Fragment mit Inflater "aufblasen" und View daraus erzeugen.
     *
     * @return  Aufgeblasenes Layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.energie_fragment, container, false);
    }


    /**
     * Methode wird nach dem "Aufblasen" der Layout-Datei aufgerufen.
     *
     * @param view  Layout-Objekt, das von Methode
     *              {@link EnergieFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        _radioButtonKCal = view.findViewById( R.id.radiobutton_kcal );

        _energieTextEdit = view.findViewById( R.id.energie_edittext);
    }


    /**
     * Methode zum Einlesen der vom Nutzer eingegebenen Zahl für die Energie der Mahlzeit.
     *
     * @return  Eingegebener Wert, wir wissen aber noch nicht, ob in kCal oder KJ;
     *          Wert ist negativ, wenn nichts eingegeben.
     */
    public int getEnergie() {

        String energieStr = _energieTextEdit.getText().toString().trim();

        if (energieStr.length() == 0) { return -1; }

        // Es kann davon ausgegangen werden, dass eine zulässige
        // Dezimalzahl eingegeben wurde, weil für das EditText-Element
        // das folgende Attribut gesetzt wurde: android:inputType="numberDecimal"
        return (int) Double.parseDouble(energieStr);
    }


    /**
     * Getter für Energie in kCal.
     *
     * @return  Eingegebene Energie in Kilokalorien (kCal).
     */
    public int getKilokalorien() {

        int energieWert = getEnergie();

        if ( _radioButtonKCal.isChecked() ) {

            return energieWert;

        } else { // RadioButton für kJ gewählt

            return (int) ( energieWert * 4.1868 );
        }
    }


    /**
     * Getter für Energie in kJ.
     *
     * @return  Eingegebene Energie in Kilojoule (kJ)
     */
    public int getKilojoule() {

        int energieWert = getEnergie();

        if ( _radioButtonKCal.isChecked() ) {

            return (int) ( energieWert / 4.1868 );

        } else { // RadioButton für kJ gewählt

            return energieWert;
        }
    }

}
